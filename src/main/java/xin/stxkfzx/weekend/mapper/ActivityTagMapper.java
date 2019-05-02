package xin.stxkfzx.weekend.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import xin.stxkfzx.weekend.entity.ActivityTag;

/**
 * 
 * 
 * @author fmy
 * @date 2019-04-29 19:27 
 */
@Mapper
public interface ActivityTagMapper {
    /**
     * delete by primary key
     * @param tbId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer tbId);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(ActivityTag record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(ActivityTag record);

    /**
     * select by primary key
     * @param tbId primary key
     * @return object by primary key
     */
    ActivityTag selectByPrimaryKey(Integer tbId);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ActivityTag record);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(ActivityTag record);

    List<ActivityTag> selectByActivityId(@Param("activityId")Integer activityId);


}