package xin.stxkfzx.weekend.service;

import xin.stxkfzx.weekend.entity.VideoShare;
import xin.stxkfzx.weekend.vo.PageVO;
import xin.stxkfzx.weekend.vo.VideoShareVO;

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
    VideoShareVO insertVideoShare(VideoShare videoShare);

    /**
     * 查询商品分享
     *
     * @param page 要查询的页数 默认为1
     * @param rows 每页显示条数 默认为10
     * @return PageInfo<VideoShare>
     * @author ViterTian
     * @date 2019-04-30
     */
    PageVO queryVideoShare(Integer page, Integer rows);

    /**
     * 通过id查询视频分享详情
     *
     * @param id 查询id
     * @return VideoShare
     * @author ViterTian
     * @date 2019-04-30
     */
    VideoShareVO queryById(Integer id);

    /**
     * 通过用户id查询当前用户发布的所有视频分享
     *
     * @param userId 用户id
     * @param page   查询页数
     * @param rows   每页显示条数
     * @return 此用户发布过的视频分享分页结果
     * @author ViterTian
     * @date 2019-05-01
     */
    PageVO queryByUserId(Integer userId, Integer page, Integer rows);

    /**
     * 点赞功能，默认为false 点击为true 再次点击为false
     *
     * @param id     视频分享id
     * @param userId 用户id
     * @return boolean
     */
    Boolean likeVideo(Integer id, Integer userId);
}
