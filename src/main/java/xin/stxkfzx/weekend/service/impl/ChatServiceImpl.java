package xin.stxkfzx.weekend.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xin.stxkfzx.weekend.annotation.ParamCheck;
import xin.stxkfzx.weekend.config.CheckUserIsExist;
import xin.stxkfzx.weekend.entity.Activity;
import xin.stxkfzx.weekend.entity.ChatRoom;
import xin.stxkfzx.weekend.entity.UserJoinChatRoom;
import xin.stxkfzx.weekend.enums.CheckTypeEnum;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.exception.SqlException;
import xin.stxkfzx.weekend.expand.ActivityExpand;
import xin.stxkfzx.weekend.mapper.ChatRoomMapper;
import xin.stxkfzx.weekend.mapper.UserJoinChatRoomMapper;
import xin.stxkfzx.weekend.service.ChatService;
import xin.stxkfzx.weekend.util.CheckUtils;

import java.util.Date;

import static xin.stxkfzx.weekend.util.UserUtils.getUserId;

/**
 * @author fmy
 * @date 2019-04-24 17:28
 */
@Service
public class ChatServiceImpl implements ChatService {
    private static final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);

    private final ChatRoomMapper chatRoomMapper;
    private final UserJoinChatRoomMapper joinChatRoomMapper;

    @Autowired
    public ChatServiceImpl(ChatRoomMapper chatRoomMapper, UserJoinChatRoomMapper joinChatRoomMapper) {
        this.chatRoomMapper = chatRoomMapper;
        this.joinChatRoomMapper = joinChatRoomMapper;
    }

    @Override
    public ActivityExpand createChatRoom(Activity activity) {
        ChatRoom room = new ChatRoom();
        room.setActivateId(activity.getTbId());
        room.setCreateTime(new Date());
        room.setUpdateTime(new Date());
        room.setUserId(getUserId());

        int insert = chatRoomMapper.insert(room);
        CheckUtils.check(insert > 0, ExceptionEnum.ACTIVATE_CREATE_FAIL);

        log.info("聊天室创建成功：{}", room.getTbId());
        return new ActivityExpand().setChatRoom(room);
    }

    @ParamCheck(checkType = CheckTypeEnum.NOT_NULL)
    @CheckUserIsExist
    @Transactional(rollbackFor = SqlException.class)
    @Override
    public ActivityExpand joinRoom(Integer userId, ChatRoom room) {
        UserJoinChatRoom record = joinChatRoomMapper.selectOneByUserIdAndRoomId(userId, room.getTbId());
        if (record != null) {
            record.setUpdateTime(new Date());
            record.setStatus(StatusEnum.NORMAL);

            joinChatRoomMapper.updateByPrimaryKey(record);
        } else {
            record = new UserJoinChatRoom();
            record.setCreateTime(new Date());
            record.setJoinTime(new Date());
            record.setUpdateTime(new Date());
            record.setRoomId(room.getTbId());
            record.setStatus(StatusEnum.NORMAL);
            record.setUserId(userId);
            joinChatRoomMapper.insert(record);
        }
        return new ActivityExpand().setRecord(record);
    }

}
