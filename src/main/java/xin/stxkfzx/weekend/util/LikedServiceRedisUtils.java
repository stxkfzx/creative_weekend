package xin.stxkfzx.weekend.util;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import xin.stxkfzx.weekend.dto.LikedCountDTO;
import xin.stxkfzx.weekend.entity.Liked;
import xin.stxkfzx.weekend.enums.LikedStatusEnum;

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
public class LikedServiceRedisUtils {
    private LikedServiceRedisUtils() {
    }

    private static final RedisTemplate<String, ?> redisTemplate = (RedisTemplate<String, ?>) ApplicationContextUtil.getBean("redisTemplate");

    /**
     * 保存用户点赞数据的key
     */
    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";
    /**
     * 保存用户被点赞数量的key
     */
    public static final String MAP_KEY_USER_LIKED_COUNT = "MAP_USER_LIKED_COUNT";
    /**
     * 保存视频分享内容被点赞数量的key
     */
    public static final String MAP_KEY_CONTENT_LIKED_COUNT = "MAP_KEY_CONTENT_LIKED_COUNT";


    /**
     * 拼接被点赞的模块+点赞的用户id+点赞的内容的id作为key。格式 video::userId::videoId
     *
     * @param module        被点赞的模块
     * @param likedUserId   被点赞的人id
     * @param likeContentId 点赞内容id
     * @return String
     */
    public static <T> String getLikedKey(T module, T likedUserId, T likeContentId) {
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
    public static <T> void saveLiked2Redis(T module, T likedUserId, T likeContentId) {
        String key = getLikedKey(String.valueOf(module), String.valueOf(likedUserId), String.valueOf(likeContentId));
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
    public static <T> void unlikeFromRedis(T module, T likedUserId, T likeContentId) {
        String key = getLikedKey(String.valueOf(module), String.valueOf(likedUserId), String.valueOf(likeContentId));
        redisTemplate.opsForHash().put(MAP_KEY_USER_LIKED, key, LikedStatusEnum.UNLIKE.getCode());
    }

    /**
     * 从Redis中删除一条点赞数据
     *
     * @param module        被点赞模块
     * @param likedUserId   点赞的人id
     * @param likeContentId 点赞内容id
     * @author ViterTian
     * @date 2019-05-02
     */
    public static <T> void deleteLikedFromRedis(T module, T likedUserId, T likeContentId) {
        String key = getLikedKey(String.valueOf(module), String.valueOf(likedUserId), String.valueOf(likeContentId));
        redisTemplate.opsForHash().delete(MAP_KEY_USER_LIKED, key);
    }

    /**
     * 该用户的被点赞数加1
     *
     * @param likedUserId 被点赞的人id
     * @author ViterTian
     * @date 2019-05-02
     */
    public static <T> void incrementLikedCount(T likedUserId) {
        redisTemplate.opsForHash().increment(MAP_KEY_USER_LIKED_COUNT, String.valueOf(likedUserId), 1);
    }

    /**
     * 该用户的被点赞数减1
     *
     * @param likedUserId 被点赞的人id
     * @author ViterTian
     * @date 2019-05-02
     */
    public static <T> void decrementLikedCount(T likedUserId) {
        redisTemplate.opsForHash().increment(MAP_KEY_USER_LIKED_COUNT, String.valueOf(likedUserId), -1);
    }

    /**
     * 该内容的被点赞数加1
     *
     * @param likeContentId 被点赞的模块id
     * @author ViterTian
     * @date 2019-05-02
     */
    public static <T> void incrementContentLikedCount(T likeContentId) {
        redisTemplate.opsForHash().increment(MAP_KEY_CONTENT_LIKED_COUNT, String.valueOf(likeContentId), 1);
    }

    /**
     * 该内容的被点赞数减1
     *
     * @param likeContentId 被点赞的模块id
     * @author ViterTian
     * @date 2019-05-02
     */
    public static <T> void decrementContentLikedCount(T likeContentId) {
        redisTemplate.opsForHash().increment(MAP_KEY_CONTENT_LIKED_COUNT, String.valueOf(likeContentId), -1);
    }


    /**
     * 获取Redis中存储的所有点赞数据
     *
     * @author ViterTian
     * @date 2019-05-02
     */
    public static List<Liked> getLikedDataFromRedis() {
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
    public static List<LikedCountDTO> getLikedCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(MAP_KEY_USER_LIKED_COUNT, ScanOptions.NONE);
        List<LikedCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> map = cursor.next();
            // 将点赞数量存储在 LikedCountDT
            String key = (String) map.getKey();
            LikedCountDTO dto = new LikedCountDTO(key, (Integer) map.getValue());
            list.add(dto);
            // 从Redis中删除这条记录
            redisTemplate.opsForHash().delete(MAP_KEY_USER_LIKED_COUNT, key);
        }
        return list;
    }

    /**
     * 获取分享视频的点赞数
     *
     * @param id 视频id
     * @return 点赞数量
     * @author ViterTian
     * @date 2019-05-02
     */
    public static Integer getLikeNum(Integer id) {
        Integer integer = (Integer) redisTemplate.opsForHash().get(MAP_KEY_CONTENT_LIKED_COUNT, id.toString());
        return integer == null ? 0 : integer;
    }
}
