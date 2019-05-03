package xin.stxkfzx.weekend.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import xin.stxkfzx.weekend.entity.VideoShare;
import xin.stxkfzx.weekend.vo.VideoShareVO;

import java.util.List;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/2
 */

@Mapper(componentModel = "spring")
public interface VideoShare2VideoShareVO {
    /**
     * 转换VideoShareVO
     *
     * @param videoShare videoShare
     * @return VideoShareVO
     * @author ViterTian
     * @date 2019-05-02
     */
    @Mapping(target = "likeNum", expression = "java(xin.stxkfzx.weekend.util.LikedServiceRedisUtils.getLikeNum(videoShare.getTbId()))")
    VideoShareVO videoShareToVideoShareVO(VideoShare videoShare);

    /**
     * 转换 List<VideoShareVO>
     *
     * @param videoShare List<VideoShare>
     * @return List<VideoShareVO>
     * @author ViterTian
     * @date 2019-05-02
     */
    List<VideoShareVO> videoShareToVideoShareVO(List<VideoShare> videoShare);
}
