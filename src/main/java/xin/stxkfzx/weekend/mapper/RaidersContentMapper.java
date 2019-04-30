package xin.stxkfzx.weekend.mapper;


import xin.stxkfzx.weekend.entity.RaidersContent;

/**
 * 
 * 
 * @author fmy
 * @date 2019-04-10 22:09 
 */
public interface RaidersContentMapper {
    /**
     * delete by primary key
     * @param tbtbId primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer tbId);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(RaidersContent record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(RaidersContent record);

    /**
     * select by primary key
     * @param tbtbId primary key
     * @return object by primary key
     */
    RaidersContent selectByPrimaryKey(Integer tbId);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(RaidersContent record);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(RaidersContent record);
}