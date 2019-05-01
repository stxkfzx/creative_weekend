package xin.stxkfzx.weekend.mapper;


import org.springframework.stereotype.Repository;
import xin.stxkfzx.weekend.entity.VideoShare;

import java.util.List;

/**
 * @author fmy
 * @date 2019-04-10 22:09
 */
@Repository
public interface VideoShareMapper {
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
    int insert(VideoShare record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(VideoShare record);

    /**
     * select by primary key
     *
     * @param tbId primary key
     * @return object by primary key
     */
    VideoShare selectByPrimaryKey(Integer tbId);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(VideoShare record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(VideoShare record);

    /**
     * 查询所有数据
     *
     * @return List<VideoShare>
     * @author ViterTian
     * @date 2019-04-30
     */
    List<VideoShare> queryAll();

    /**
     * 根据用户id查询此用户发表的所有视频分享
     *
     * @param userId 用户id
     * @return List<VideoShare>
     */
    List<VideoShare> selectByUserId(Integer userId);
}