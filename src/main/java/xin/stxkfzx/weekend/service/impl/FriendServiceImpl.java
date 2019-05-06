package xin.stxkfzx.weekend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import xin.stxkfzx.weekend.convert.Friend2FriendVO;
import xin.stxkfzx.weekend.convert.PageConvert;
import xin.stxkfzx.weekend.convert.User2UserVO;
import xin.stxkfzx.weekend.entity.Friend;
import xin.stxkfzx.weekend.entity.User;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.exception.WeekendException;
import xin.stxkfzx.weekend.mapper.FriendMapper;
import xin.stxkfzx.weekend.service.FriendService;
import xin.stxkfzx.weekend.service.UserService;
import xin.stxkfzx.weekend.vo.PageVO;
import xin.stxkfzx.weekend.vo.UserVO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/3
 */
@Service
public class FriendServiceImpl implements FriendService {
    private final User2UserVO user2UserVO;
    private final PageConvert pageConvert;
    private final Friend2FriendVO friend2FriendVO;
    private final FriendMapper friendMapper;
    private final UserService userService;

    public FriendServiceImpl(User2UserVO user2UserVO, PageConvert pageConvert, Friend2FriendVO friend2FriendVO, FriendMapper friendMapper, UserService userService) {
        this.user2UserVO = user2UserVO;
        this.pageConvert = pageConvert;
        this.friend2FriendVO = friend2FriendVO;
        this.friendMapper = friendMapper;
        this.userService = userService;
    }

    /**
     * 通过当前用户id和要关注用户id来添加关注信息
     *
     * @param uid 当前用户id
     * @param fid 关注用户id
     * @return 是否关注成功
     * @author ViterTian
     * @date 2019-05-03
     */
    @Override
    public Boolean addAttentionById(Integer uid, Integer fid) {
        if (userService.checkUserId(uid) && userService.checkUserId(fid)) {
            Friend friend = new Friend();
            friend.setUid(uid);
            friend.setFid(fid);
            friend.setCreateTime(new Date());
            friend.setUpdateTime(new Date());
            // 查询是否已经关注
            if (friendMapper.queryExist(uid, fid) == null) {
                return friendMapper.insertSelective(friend) == 1;
            } else {
                // 已经关注，不能继续添加关注
                throw new WeekendException(ExceptionEnum.USER_IS_ATTENTION);
            }
        }
        throw new WeekendException(ExceptionEnum.USER_OR_FRIEND_NOT_EXIST);
    }


    @Override
    public PageVO queryMyFans(Integer uid, Integer page, Integer rows) {
        List<Integer> userIds = new LinkedList<>();
        if (userService.checkUserId(uid)) {
            List<Friend> list = friendMapper.queryMyFans(uid);
            for (Friend friend : list) {
                userIds.add(friend.getUid());
            }
            return getPageVO(page, rows, userIds);
        }
        throw new WeekendException(ExceptionEnum.USER_NOT_EXIST);
    }

    @Override
    public PageVO queryMyAttention(Integer uid, Integer page, Integer rows) {
        List<Integer> userIds = new LinkedList<>();
        if (userService.checkUserId(uid)) {
            List<Friend> list = friendMapper.queryMyAttention(uid);
            for (Friend friend : list) {
                userIds.add(friend.getFid());
            }
            return getPageVO(page, rows, userIds);
        }
        throw new WeekendException(ExceptionEnum.USER_NOT_EXIST);
    }

    private PageVO getPageVO(Integer page, Integer rows, List<Integer> userIds) {
        PageHelper.startPage(page, rows);
        List<User> attentions = userService.queryUserListByIds(userIds);
        List<UserVO> userBases = user2UserVO.userToUserUserVO(attentions);
        PageInfo<User> pageInfo = new PageInfo<>(attentions);
        return pageConvert.fromPageInfo(pageInfo, userBases);
    }
}
