package xin.stxkfzx.weekend.service;

import xin.stxkfzx.weekend.entity.PageResult;
import xin.stxkfzx.weekend.entity.VideoShare;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/29
 */
public interface VideoShareService {
    /**
     * 添加纯视频分享
     *
     * @param videoShare videoShare对象
     * @return VideoShare
     * @author ViterTian
     * @date 2019-04-30
     */
    VideoShare insertVideoShare(VideoShare videoShare);

    /**
     * 查询商品分享
     *
     * @param page 要查询的页数 默认为1
     * @param rows 每页显示条数 默认为10
     * @return PageInfo<VideoShare>
     * @author ViterTian
     * @date 2019-04-30
     */
    PageResult<VideoShare> queryVideoShare(Integer page, Integer rows);

    /**
     * 通过id查询视频分享详情
     * @param id 查询id
     * @return VideoShare
     * @author ViterTian
     * @date 2019-04-30
     */
    VideoShare queryById(Integer id);
}
