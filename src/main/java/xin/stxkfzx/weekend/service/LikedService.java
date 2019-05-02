package xin.stxkfzx.weekend.service;

import xin.stxkfzx.weekend.entity.Liked;
import xin.stxkfzx.weekend.entity.PageResult;

import java.util.List;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/2
 */
public interface LikedService {
    /**
     * 保存点赞记录
     * @param userLike
     * @return
     */
    Liked save(Liked userLike);

    /**
     * 批量保存或修改
     * @param list
     */
    List<Liked> saveAll(List<Liked> list);


    /**
     * 根据被点赞人的id查询点赞列表（即查询都谁给这个人点赞过）
     * @param likedUserId 被点赞人的id
     * @return
     */
    PageResult<Liked> getLikedListByLikedUserId(String likedUserId);

    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     * @param likedPostId
     * @return
     */
    PageResult<Liked> getLikedListByLikedPostId(String likedPostId);

    /**
     * 通过被点赞人和点赞人id查询是否存在点赞记录
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    Liked getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId);

    /**
     * 将Redis里的点赞数据存入数据库中
     */
    void transLikedFromRedis2DB();

    /**
     * 将Redis中的点赞数量数据存入数据库
     */
    void transLikedCountFromRedis2DB();

}
