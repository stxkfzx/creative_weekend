package xin.stxkfzx.weekend.mapper;


import org.springframework.stereotype.Repository;
import xin.stxkfzx.weekend.entity.RaidersCategory;

/**
 * 
 * 
 * @author fmy
 * @date 2019-04-10 22:09 
 */
@Repository
public interface RaidersCategoryMapper {
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
    int insert(RaidersCategory record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(RaidersCategory record);

    /**
     * select by primary key
     * @param tbtbId primary key
     * @return object by primary key
     */
    RaidersCategory selectByPrimaryKey(Integer tbId);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(RaidersCategory record);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(RaidersCategory record);
}