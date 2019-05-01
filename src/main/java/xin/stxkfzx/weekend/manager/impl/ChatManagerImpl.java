package xin.stxkfzx.weekend.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xin.stxkfzx.weekend.entity.Activity;
import xin.stxkfzx.weekend.entity.ChatRoom;
import xin.stxkfzx.weekend.entity.User;
import xin.stxkfzx.weekend.entity.UserJoinChatRoom;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.manager.ChatManager;
import xin.stxkfzx.weekend.mapper.ChatRoomMapper;
import xin.stxkfzx.weekend.mapper.UserJoinChatRoomMapper;

import java.util.Date;

/**
 * @author fmy
 * @date 2019-04-28 17:32
 */
@Component
public class ChatManagerImpl implements ChatManager {
    private static final Logger log = LoggerFactory.getLogger(ChatManagerImpl.class);
    private final ChatRoomMapper chatRoomMapper;
    private final UserJoinChatRoomMapper joinChatRoomMapper;

    @Autowired
    public ChatManagerImpl(ChatRoomMapper chatRoomMapper, UserJoinChatRoomMapper joinChatRoomMapper) {
        this.chatRoomMapper = chatRoomMapper;
        this.joinChatRoomMapper = joinChatRoomMapper;
    }

    @Override
    public ChatRoom addChatRoom(Activity activity, User creator) {
        ChatRoom room = new ChatRoom();
        room.setActivateId(activity.getTbId());
        room.setCreateTime(new Date());
        room.setUpdateTime(new Date());
        room.setUserId(creator.getTbId());

        chatRoomMapper.insert(room);
        log.debug("聊天室创建成功：{}", room.getTbId());

        return room;
    }

    @Override
    public UserJoinChatRoom addJoinRecord(User joiner, ChatRoom room) {
        UserJoinChatRoom record = new UserJoinChatRoom();
        record.setCreateTime(new Date());
        record.setJoinTime(new Date());
        record.setUpdateTime(new Date());
        record.setRoomId(room.getTbId());
        record.setStatus(StatusEnum.NORMAL);
        record.setUserId(joiner.getTbId());

        joinChatRoomMapper.insert(record);
        log.debug("{} 加入聊天室", room.getUserId());

        return record;
    }
}
