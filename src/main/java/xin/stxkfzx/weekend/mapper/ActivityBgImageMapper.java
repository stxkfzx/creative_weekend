package xin.stxkfzx.weekend.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import xin.stxkfzx.weekend.entity.ActivityBgImage;

/**
 * @author fmy
 * @date 2019-04-29 14:42
 */
@Mapper
public interface ActivityBgImageMapper {
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
    int insert(ActivityBgImage record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(ActivityBgImage record);

    /**
     * select by primary key
     *
     * @param tbId primary key
     * @return object by primary key
     */
    ActivityBgImage selectByPrimaryKey(Integer tbId);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ActivityBgImage record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(ActivityBgImage record);

    /**
     * 批量插入
     *
     * @param list 插入列表
     * @return 插入记录
     * @author fmy
     * @date 2019-04-29 15:03
     */
    int insertList(@Param("list") List<ActivityBgImage> list);

}