package xin.stxkfzx.weekend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import xin.stxkfzx.weekend.convert.PageConvert;
import xin.stxkfzx.weekend.convert.VideoShare2VideoShareVO;
import xin.stxkfzx.weekend.entity.Liked;
import xin.stxkfzx.weekend.entity.VideoShare;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.exception.WeekendException;
import xin.stxkfzx.weekend.mapper.LikedMapper;
import xin.stxkfzx.weekend.mapper.VideoShareMapper;
import xin.stxkfzx.weekend.service.ShareCategoryService;
import xin.stxkfzx.weekend.service.UserService;
import xin.stxkfzx.weekend.service.VideoShareService;
import xin.stxkfzx.weekend.util.LikedServiceRedisUtils;
import xin.stxkfzx.weekend.vo.PageVO;
import xin.stxkfzx.weekend.vo.VideoShareVO;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static xin.stxkfzx.weekend.util.LikedServiceRedisUtils.MAP_KEY_USER_LIKED;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/29
 */
@Service
public class VideoShareServiceImpl implements VideoShareService {
    private Logger logger = LoggerFactory.getLogger(VideoShareServiceImpl.class);
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;
    private final LikedMapper likedMapper;
    private final VideoShare2VideoShareVO videoShareVO;
    private final PageConvert pageConvert;
    private final ShareCategoryService shareCategoryService;
    private final VideoShareMapper videoShareMapper;
    private final UserService userService;

    public VideoShareServiceImpl(PageConvert pageConvert, VideoShareMapper videoShareMapper, ShareCategoryService shareCategoryService, UserService userService, VideoShare2VideoShareVO videoShareVO, LikedMapper likedMapper) {
        this.pageConvert = pageConvert;
        this.videoShareMapper = videoShareMapper;
        this.shareCategoryService = shareCategoryService;
        this.userService = userService;
        this.videoShareVO = videoShareVO;
        this.likedMapper = likedMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VideoShareVO insertVideoShare(VideoShare videoShare) {
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
            logger.info("新增视频分享{}成功.", videoShare);
            return videoShareVO.videoShareToVideoShareVO(videoShare);
        } else {
            logger.error("新增视频分享{}失败.", videoShare);
            throw new WeekendException(ExceptionEnum.SHARE_VIDEO_FAILED);
        }
    }

    @Override
    public PageVO queryVideoShare(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<VideoShare> list = videoShareMapper.queryAll();
        if (CollectionUtils.isEmpty(list)) {
            throw new WeekendException(ExceptionEnum.CONTENT_NOT_FOUND);
        }
        List<VideoShareVO> voList = videoShareVO.videoShareToVideoShareVO(list);
        PageInfo<VideoShare> pageInfo = new PageInfo<>(list);
        return pageConvert.fromPageInfo(pageInfo, voList);
    }

    @Override
    public VideoShareVO queryById(Integer id) {
        // 先通过redis查询
        VideoShare videoShare = (VideoShare) redisTemplate.opsForValue().get(id.toString());
        // 如果查不到再通过数据库查询
        if (videoShare == null) {
            logger.warn("视频{}未从redis中获取数据", id);
            videoShare = videoShareMapper.selectByPrimaryKey(id);
            if (videoShare == null) {
                throw new WeekendException(ExceptionEnum.CONTENT_NOT_FOUND);
            }
            // 设置redis过期时间为6小时
            redisTemplate.opsForValue().set(id.toString(), videoShare, 6, TimeUnit.HOURS);
        }
        // 开始转换VO，并从redis中查询当前视频内容点赞数并放入VO
        return this.videoShareVO.videoShareToVideoShareVO(videoShare);
    }

    @Override
    public PageVO queryByUserId(Integer userId, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<VideoShare> list = videoShareMapper.selectByUserId(userId);
        if (CollectionUtils.isEmpty(list)) {
            logger.warn("当前用户：{}未发表过分享", userId);
            throw new WeekendException(ExceptionEnum.CONTENT_NOT_FOUND);
        }
        List<VideoShareVO> videoShareVOS = videoShareVO.videoShareToVideoShareVO(list);
        PageInfo<VideoShare> pageInfo = new PageInfo<>(list);
        return pageConvert.fromPageInfo(pageInfo, videoShareVOS);
    }

    @Override
    public Boolean likeVideo(Integer id, Integer userId) {
        String key = LikedServiceRedisUtils.getLikedKey("video", userId, id);
        // 首先从redis中查询是否有点赞记录
        if (redisTemplate.opsForHash().hasKey(MAP_KEY_USER_LIKED, key)) {
            // 说明此用户已经为这条视频点过赞了，再点为取消点赞
            LikedServiceRedisUtils.unlikeFromRedis("video", userId, id);
            // 为当前内容的总点赞数减一
            LikedServiceRedisUtils.decrementContentLikedCount(id);
            // 取消点赞后将该记录删除
            LikedServiceRedisUtils.deleteLikedFromRedis("video", userId, id);
            logger.info("用户{}为{}视频取消点赞成功", userId, id);
            return true;
        }

        // 如果在redis中没有查询到有点赞记录，继续从数据库查询
        Liked liked = likedMapper.selectByUserIdAndCid(id,userId);
        if (liked != null && liked.getStatus() == 0) {
            // 说明从数据库中查询到了这条记录，同样说明此用户已经为这条视频点过赞了，再点为取消点赞
            liked.setStatus(-1);
            likedMapper.updateByPrimaryKeySelective(liked);
            // 为当前内容的总点赞数减一
            LikedServiceRedisUtils.decrementContentLikedCount(id);
            logger.info("用户{}为{}视频从数据库取消点赞成功", userId, id);
            return true;
        }

        // 至此，说明此用户没有为这个视频内容分享点赞

        // 设置为已点赞状态
        LikedServiceRedisUtils.saveLiked2Redis("video", userId, id);
        // 为当前内容的总点赞数加一
        LikedServiceRedisUtils.incrementContentLikedCount(id);
        logger.info("用户{}为{}视频点赞成功", userId, id);
        return true;
    }

}
