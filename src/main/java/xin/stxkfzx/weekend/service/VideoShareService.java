package xin.stxkfzx.weekend.service;

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
}
