package xin.stxkfzx.weekend.service.impl;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import xin.stxkfzx.weekend.dto.LikedCountDTO;
import xin.stxkfzx.weekend.entity.Liked;
import xin.stxkfzx.weekend.entity.PageResult;
import xin.stxkfzx.weekend.service.LikedService;
import xin.stxkfzx.weekend.util.LikedServiceRedisUtils;

import java.util.List;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/2
 */
@Service
@EnableScheduling
public class LikedServiceImpl implements LikedService {
    private final LikedServiceRedisUtils redisUtils;

    public LikedServiceImpl(LikedServiceRedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    /**
     * 保存点赞记录
     *
     * @param userLike
     * @return
     */
    @Override
    public Liked save(Liked userLike) {
        return null;
    }

    /**
     * 批量保存或修改
     *
     * @param list
     */
    @Override
    public List<Liked> saveAll(List<Liked> list) {
        return null;
    }

    /**
     * 根据被点赞人的id查询点赞列表（即查询都谁给这个人点赞过）
     *
     * @param likedUserId 被点赞人的id
     * @return
     */
    @Override
    public PageResult<Liked> getLikedListByLikedUserId(String likedUserId) {
        return null;
    }

    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     *
     * @param likedPostId
     * @return
     */
    @Override
    public PageResult<Liked> getLikedListByLikedPostId(String likedPostId) {
        return null;
    }

    /**
     * 通过被点赞人和点赞人id查询是否存在点赞记录
     *
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    @Override
    public Liked getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId) {
        return null;
    }

    /**
     * 将Redis里的点赞数据存入数据库中
     */
    @Override
    public void transLikedFromRedis2DB() {
        List<Liked> list = redisUtils.getLikedDataFromRedis();
        for (Liked like : list) {
            Liked ul = getByLikedUserIdAndLikedPostId(like.getLikedContentId(), like.getLikedPostId());
            if (ul == null) {
                //没有记录，直接存入
                save(like);
            } else {
                //有记录，需要更新
                ul.setStatus(like.getStatus());
                save(ul);
            }
        }

    }

    /**
     * 将Redis中的点赞数量数据存入数据库
     */
    @Override
    public void transLikedCountFromRedis2DB() {
        List<LikedCountDTO> list = redisUtils.getLikedCountFromRedis();
        for (LikedCountDTO dto : list) {
            //更新点赞数量
        }
    }
}
