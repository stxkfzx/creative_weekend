package xin.stxkfzx.weekend.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xin.stxkfzx.weekend.activity.annotation.ParamCheck;
import xin.stxkfzx.weekend.activity.entity.Activity;
import xin.stxkfzx.weekend.activity.entity.ChatRoom;
import xin.stxkfzx.weekend.activity.entity.UserJoinChatRoom;
import xin.stxkfzx.weekend.activity.enums.CheckTypeEnum;
import xin.stxkfzx.weekend.activity.expand.ChatExpand;
import xin.stxkfzx.weekend.activity.mapper.ChatRoomMapper;
import xin.stxkfzx.weekend.activity.mapper.UserJoinChatRoomMapper;
import xin.stxkfzx.weekend.activity.service.ChatService;
import xin.stxkfzx.weekend.auth.config.CheckUserIsExist;
import xin.stxkfzx.weekend.common.enums.StatusEnum;
import xin.stxkfzx.weekend.common.exception.SqlException;
import xin.stxkfzx.weekend.user.entity.User;

import java.util.Date;

/**
 * @author fmy
 * @date 2019-04-24 17:28
 */
@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRoomMapper chatRoomMapper;
    private final UserJoinChatRoomMapper joinChatRoomMapper;

    @Autowired
    public ChatServiceImpl(ChatRoomMapper chatRoomMapper, UserJoinChatRoomMapper joinChatRoomMapper) {
        this.chatRoomMapper = chatRoomMapper;
        this.joinChatRoomMapper = joinChatRoomMapper;
    }

    @Override
    public ChatExpand createChatRoom(Activity activity) {
        ChatRoom room = new ChatRoom();
        room.setActivateId(activity.getTbId());
        room.setCreateTime(new Date());
        room.setUpdateTime(new Date());
        room.setUserId(activity.getUserId());

        chatRoomMapper.insert(room);

        return new ChatExpand().setChatRoom(room);
    }

    @ParamCheck(checkType = CheckTypeEnum.NOT_NULL)
    @CheckUserIsExist
    @Transactional(rollbackFor = SqlException.class)
    @Override
    public ChatExpand joinRoom(User user, ChatRoom room) {
        UserJoinChatRoom record = joinChatRoomMapper.selectOneByUserIdAndRoomId(user.getTbId(), room.getTbId());
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
            record.setUserId(user.getTbId());
            joinChatRoomMapper.insert(record);
        }
        return new ChatExpand().setRecord(record);
    }

}
