package xin.stxkfzx.weekend.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import xin.stxkfzx.weekend.dto.LikedCountDTO;
import xin.stxkfzx.weekend.entity.Liked;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.exception.WeekendException;
import xin.stxkfzx.weekend.mapper.LikedCountMapper;
import xin.stxkfzx.weekend.mapper.LikedMapper;
import xin.stxkfzx.weekend.service.LikedService;
import xin.stxkfzx.weekend.util.LikedServiceRedisUtils;
import xin.stxkfzx.weekend.vo.PageVO;

import java.util.List;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/2
 */
@Service
@EnableScheduling
public class LikedServiceImpl implements LikedService {
    private Logger logger = LoggerFactory.getLogger(LikedServiceImpl.class);
    private final LikedCountMapper likedCountMapper;
    private final LikedMapper likedMapper;

    public LikedServiceImpl(LikedMapper likedMapper, LikedCountMapper likedCountMapper) {
        this.likedMapper = likedMapper;
        this.likedCountMapper = likedCountMapper;
    }

    /**
     * 保存点赞记录
     *
     * @param liked 点赞记录
     * @return Liked 点赞记录
     */
    @Override
    public Liked save(Liked liked) {
        int i = likedMapper.insertSelective(liked);
        if (i == 1) {
            logger.info("新增{}成功", liked);
            return liked;
        } else {
            logger.error("新增{}失败", liked);
            throw new WeekendException(ExceptionEnum.USER_SAVE_ERROR);
        }
    }

    /**
     * 批量保存或修改
     *
     * @param list
     */
    @Override
    public List<Liked> saveAll(List<Liked> list) {
        return null;
    }

    /**
     * 根据被点赞人的id查询点赞列表（即查询都谁给这个人点赞过）
     *
     * @param likedUserId 被点赞人的id
     * @return
     */
    @Override
    public PageVO<Liked> getLikedListByLikedUserId(String likedUserId) {
        return null;
    }

    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     *
     * @param likedPostId
     * @return
     */
    @Override
    public PageVO<Liked> getLikedListByLikedPostId(String likedPostId) {
        return null;
    }

    /**
     * 通过被点赞人和点赞人id查询是否存在点赞记录
     *
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    @Override
    public Liked getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId) {
        return null;
    }

    /**
     * 将Redis里的点赞数据存入数据库中
     */
    @Override
    public void transLikedFromRedis2DB() {
        List<Liked> list = LikedServiceRedisUtils.getLikedDataFromRedis();
        for (Liked like : list) {
            Liked liked = getByLikedUserIdAndLikedPostId(like.getLikedPostId(), like.getLikedContentId());
            if (liked == null) {
                //没有记录，直接存入
                save(like);

            } else {
                //有记录，需要更新
                liked.setStatus(like.getStatus());
                save(liked);
            }
        }

    }

    /**
     * 将Redis中的点赞数量数据存入数据库
     */
    @Override
    public void transLikedCountFromRedis2DB() {
       /*List<LikedCountDTO> list = LikedServiceRedisUtils.getLikedCountFromRedis();
        for (LikedCountDTO likedCountDTO : list) {
            //更新点赞数量
            if (likedCountMapper.selectByPrimaryKey(likedCountDTO.getId()) != null) {
                likedCountMapper.updateByPrimaryKeySelective(likedCountDTO)
            }
        }*/
    }
}
