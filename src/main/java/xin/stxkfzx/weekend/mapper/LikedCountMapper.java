package xin.stxkfzx.weekend.mapper;

import org.springframework.stereotype.Repository;
import xin.stxkfzx.weekend.entity.LikedCount;

/**
 * @author fmy
 * @date 2019-05-02 13:07
 */
@Repository
public interface LikedCountMapper {
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
    int insert(LikedCount record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(LikedCount record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    LikedCount selectByPrimaryKey(Integer id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(LikedCount record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(LikedCount record);
}