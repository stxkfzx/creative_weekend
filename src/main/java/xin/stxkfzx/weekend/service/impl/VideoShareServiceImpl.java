package xin.stxkfzx.weekend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xin.stxkfzx.weekend.entity.PageResult;
import xin.stxkfzx.weekend.entity.VideoShare;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.exception.WeekendException;
import xin.stxkfzx.weekend.mapper.VideoShareMapper;
import xin.stxkfzx.weekend.service.ShareCategoryService;
import xin.stxkfzx.weekend.service.UserService;
import xin.stxkfzx.weekend.service.VideoShareService;
import xin.stxkfzx.weekend.util.RedisUtil;

import java.util.Date;
import java.util.List;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/29
 */
@Service
public class VideoShareServiceImpl implements VideoShareService {
    private Logger logger = LoggerFactory.getLogger(VideoShareServiceImpl.class);
    private final RedisUtil redisUtil;
    private final ShareCategoryService shareCategoryService;
    private final VideoShareMapper videoShareMapper;
    private final UserService userService;

    public VideoShareServiceImpl(VideoShareMapper videoShareMapper, ShareCategoryService shareCategoryService, UserService userService, RedisUtil redisUtil) {
        this.videoShareMapper = videoShareMapper;
        this.shareCategoryService = shareCategoryService;
        this.userService = userService;
        this.redisUtil = redisUtil;
    }

    @Override
    public VideoShare insertVideoShare(VideoShare videoShare) {
        // 校验用户id和分类id及是否合理
        boolean flag = shareCategoryService.checkCategoryId(videoShare.getCategoryId()) && userService.checkUserId(videoShare.getUserId());
        if (!flag) {
            logger.error("此用户：{}提交数据不合理不存在，有可能是用户id或分类id不存在", videoShare.getUserId());
            throw new WeekendException(ExceptionEnum.CATEGORY_ID_NOT_EXIST);
        }
        // 防止用户自己操作这些数据
        videoShare.setTbId(null);
        videoShare.setStatus((short) 1);
        videoShare.setCreateTime(new Date());
        videoShare.setUpdateTime(new Date());
        int insert = videoShareMapper.insertSelective(videoShare);
        if (insert == 1) {
            logger.info("新增商品分享{}成功", videoShare);
            return videoShare;
        } else {
            logger.error("新增商品分享{}失败", videoShare);
            throw new WeekendException(ExceptionEnum.SHARE_VIDEO_FAILED);
        }
    }

    @Override
    public PageResult<VideoShare> queryVideoShare(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<VideoShare> list = videoShareMapper.queryAll();
        if (CollectionUtils.isEmpty(list)) {
            throw new WeekendException(ExceptionEnum.CONTENT_NOT_FOUND);
        }
        PageInfo<VideoShare> pageInfo = new PageInfo<>(list);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getPages(), list);
    }

    @Override
    public VideoShare queryById(Integer id) {
        // 先通过redis查询
        VideoShare videoShare = redisUtil.get(id+"");
        // 如果查不到再通过数据库查询
        if (videoShare == null) {
            logger.warn("视频{}未从redis中获取数据",id);
            videoShare = videoShareMapper.selectByPrimaryKey(id);
            if (videoShare == null) {
                throw new WeekendException(ExceptionEnum.CONTENT_NOT_FOUND);
            }
            redisUtil.set(id+"", videoShare);
        }
        return videoShare;
    }
}
