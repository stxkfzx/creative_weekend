package xin.stxkfzx.weekend.util;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xin.stxkfzx.weekend.dto.LikedCountDTO;
import xin.stxkfzx.weekend.entity.Liked;
import xin.stxkfzx.weekend.enums.LikedStatusEnum;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 点赞模块工具类
 * <p>
 * 说明：
 * <p>
 * 点赞人的 id 为 likedUserId
 * 被点赞内容的 id 为  likeContentId
 * 点赞时状态为 1，取消点赞状态为 0
 * 将点赞模块 和点赞人 id 、被点赞内容 id 作为键，三个 字符串 中间用 :: 隔开，点赞状态作为值
 * 如果点赞模块，用户点赞，存储的键为：video::likedUserId::likeContentId，对应的值为 1
 * 取消点赞，存储的键为：video::likedUserId::likeContentId，对应的值为 0
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/29
 */
@Service
public class LikedServiceRedisUtils {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 保存用户点赞数据的key
     */
    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";
    /**
     * 保存用户被点赞数量的key
     */
    public static final String MAP_KEY_USER_LIKED_COUNT = "MAP_USER_LIKED_COUNT";

    /**
     * 拼接被点赞的模块+点赞的用户id+点赞的内容的id作为key。格式 video::userId::videoId
     *
     * @param module        被点赞的模块
     * @param likedUserId   被点赞的人id
     * @param likeContentId 点赞内容id
     * @return String
     */
    public static String getLikedKey(String module, String likedUserId, String likeContentId) {
        return module + "::" + likedUserId + "::" + likeContentId;
    }

    /**
     * 点赞。状态为1
     *
     * @param module        被点赞模块
     * @param likedUserId   被点赞的人id
     * @param likeContentId 点赞内容id
     * @author ViterTian
     * @date 2019-05-02
     */
    public void saveLiked2Redis(String module, String likedUserId, String likeContentId) {
        String key = getLikedKey(module, likedUserId, likeContentId);
        redisTemplate.opsForHash().put(MAP_KEY_USER_LIKED, key, LikedStatusEnum.UNLIKE.getCode());
    }

    /**
     * 取消点赞。将状态改变为0
     *
     * @param module        被点赞模块
     * @param likedUserId   被点赞的人id
     * @param likeContentId 点赞内容id
     * @author ViterTian
     * @date 2019-05-02
     */
    public void unlikeFromRedis(String module, String likedUserId, String likeContentId) {
        String key = getLikedKey(module, likedUserId, likeContentId);
        redisTemplate.opsForHash().put(MAP_KEY_USER_LIKED, key, LikedStatusEnum.UNLIKE.getCode());
    }

    /**
     * 从Redis中删除一条点赞数据
     *
     * @param module        被点赞模块
     * @param likedUserId   被点赞的人id
     * @param likeContentId 点赞内容id
     * @author ViterTian
     * @date 2019-05-02
     */
    public void deleteLikedFromRedis(String module, String likedUserId, String likeContentId) {
        String key = getLikedKey(module, likedUserId, likeContentId);
        redisTemplate.opsForHash().delete(MAP_KEY_USER_LIKED, key);
    }

    /**
     * 该用户的点赞数加1
     *
     * @param module      被点赞模块
     * @param likedUserId 被点赞的人id
     * @author ViterTian
     * @date 2019-05-02
     */
    public void incrementLikedCount(String module, String likedUserId) {
        redisTemplate.opsForHash().increment(MAP_KEY_USER_LIKED_COUNT, likedUserId, 1);
    }

    /**
     * 该用户的点赞数减1
     *
     * @param module      被点赞模块
     * @param likedUserId 被点赞的人id
     * @author ViterTian
     * @date 2019-05-02
     */
    public void decrementLikedCount(String module, String likedUserId) {
        redisTemplate.opsForHash().increment(MAP_KEY_USER_LIKED_COUNT, likedUserId, -1);
    }

    /**
     * 获取Redis中存储的所有点赞数据
     *
     * @author ViterTian
     * @date 2019-05-02
     */
    public List<Liked> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<Liked> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            // 分离出 likedUserId，likeContentId
            String[] split = key.split("::");
            String likedModule = split[0];
            String likedUserId = split[1];
            String likeContentId = split[2];
            Integer value = (Integer) entry.getValue();

            // 组装成 Like 对象
            Liked userLiked = new Liked(likedModule, likedUserId, likeContentId, value);
            list.add(userLiked);

            // 存到 list 后从 Redis 中删除
            redisTemplate.opsForHash().delete(MAP_KEY_USER_LIKED, key);
        }

        return list;
    }

    /**
     * 获取Redis中存储的所有点赞数量
     *
     * @author ViterTian
     * @date 2019-05-02
     */
    public List<LikedCountDTO> getLikedCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(MAP_KEY_USER_LIKED_COUNT, ScanOptions.NONE);
        List<LikedCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> map = cursor.next();
            //将点赞数量存储在 LikedCountDT
            String key = (String) map.getKey();
            LikedCountDTO dto = new LikedCountDTO(key, (Integer) map.getValue());
            list.add(dto);
            //从Redis中删除这条记录
            redisTemplate.opsForHash().delete(MAP_KEY_USER_LIKED_COUNT, key);
        }
        return list;
    }
}
