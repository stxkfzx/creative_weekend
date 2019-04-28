package xin.stxkfzx.weekend.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xin.stxkfzx.weekend.entity.UserJoinActivity;

/**
 * @author fmy
 * @date 2019-04-10 22:09
 */
@Mapper
public interface UserJoinActivityMapper {
    /**
     * delete by primary key
     *
     * @param tbId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer tbId);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(UserJoinActivity record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(UserJoinActivity record);

    /**
     * select by primary key
     *
     * @param tbId primary key
     * @return object by primary key
     */
    UserJoinActivity selectByPrimaryKey(Integer tbId);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(UserJoinActivity record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(UserJoinActivity record);

    /**
     * 通过用户Id和活动Id查找用户加入活动记录
     *
     * @param userId
     * @param activityId
     * @return
     * @author fmy
     * @date 2019-04-18 8:46
     */
    UserJoinActivity selectOneByUserIdAndActivityId(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    /**
     * 根据活动Id更新记录
     *
     * @param updated
     * @return
     * @author fmy
     * @date 2019-04-19 10:11
     */
    int updateByActivityId(@Param("updated") UserJoinActivity updated);

    /**
     * 更新参加活动人的记录
     *
     * @param updated
     * @return
     * @author fmy
     * @date 2019-04-19 10:47
     */
    int updateByActivityIdAndUserId(@Param("updated") UserJoinActivity updated);

}