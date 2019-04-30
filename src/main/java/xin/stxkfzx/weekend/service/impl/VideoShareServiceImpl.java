package xin.stxkfzx.weekend.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import xin.stxkfzx.weekend.entity.VideoShare;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.exception.WeekendException;
import xin.stxkfzx.weekend.mapper.VideoShareMapper;
import xin.stxkfzx.weekend.service.ShareCategoryService;
import xin.stxkfzx.weekend.service.VideoShareService;
import xin.stxkfzx.weekend.util.ObjectUtils;

import java.util.Date;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/29
 */
@Service
public class VideoShareServiceImpl implements VideoShareService {
    private Logger logger = LoggerFactory.getLogger(VideoShareServiceImpl.class);
    private final ShareCategoryService shareCategoryService;
    private final VideoShareMapper videoShareMapper;

    public VideoShareServiceImpl(VideoShareMapper videoShareMapper, ShareCategoryService shareCategoryService) {
        this.videoShareMapper = videoShareMapper;
        this.shareCategoryService = shareCategoryService;
    }

    @Override
    public VideoShare insertVideoShare(VideoShare videoShare) {
        boolean flag = shareCategoryService.checkCategoryId(videoShare.getCategoryId());
        if (!flag) {
            logger.error("此用户：{}分享的视频分类{}不存在", videoShare.getUserId(), videoShare.getCategoryId());
            throw new WeekendException(ExceptionEnum.CATEGORY_ID_NOT_EXIST);
        }
        ObjectUtils.disposeObject(videoShare);
        videoShare.setTbId(null);
        videoShare.setStatus((short) 1);
        videoShare.setCreateTime(new Date());
        videoShare.setUpdateTime(new Date());
        int insert = videoShareMapper.insertSelective(videoShare);
        if (insert == 1) {
            logger.info("新增商品分享{}成功", videoShare);
            return videoShare;
        } else {
            throw new WeekendException(ExceptionEnum.SHARE_VIDEO_FAILED);
        }
    }
}
