package xin.stxkfzx.weekend.activity.mapper;

import xin.stxkfzx.weekend.activity.entity.Activity;

/**
 * 
 * 
 * @author fmy
 * @date 2019-04-10 22:09 
 */
public interface ActivityMapper {
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
    int insert(Activity record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Activity record);

    /**
     * select by primary key
     * @param tbId primary key
     * @return object by primary key
     */
    Activity selectByPrimaryKey(Integer tbId);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Activity record);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Activity record);
}