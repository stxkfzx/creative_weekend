package xin.stxkfzx.weekend.mapper;

import org.springframework.stereotype.Repository;
import xin.stxkfzx.weekend.entity.Friend;

import java.util.List;

/**
 * @author fmy
 * @date 2019-05-03 20:28
 */
@Repository
public interface FriendMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Friend record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Friend record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Friend selectByPrimaryKey(Integer id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Friend record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Friend record);

    /**
     * 查询我的粉丝
     *
     * @param uid 用户id
     * @return List<Friend>
     * @author ViterTian
     * @date 2019-05-03
     */
    List<Friend> queryMyFans(Integer uid);

    /**
     * 查询我的关注
     *
     * @param uid 用户id
     * @return List<Friend>
     * @author ViterTian
     * @date 2019-05-03
     */
    List<Friend> queryMyAttention(Integer uid);

    /**
     * 查询是否已经关注
     *
     * @param uid 用户id
     * @param fid 关注id
     * @return 是狗已经关注 true-已经关注 false-未关注
     * @author ViterTian
     * @date 2019-05-03
     */
    boolean queryExist(Integer uid, Integer fid);
}