package xin.stxkfzx.weekend.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import xin.stxkfzx.weekend.entity.Activity;
import xin.stxkfzx.weekend.entity.ChatRoom;
import xin.stxkfzx.weekend.entity.User;
import xin.stxkfzx.weekend.entity.UserJoinChatRoom;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.manager.ChatManager;
import xin.stxkfzx.weekend.mapper.ChatRoomMapper;
import xin.stxkfzx.weekend.mapper.UserJoinChatRoomMapper;

import java.util.Date;
import java.util.List;

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
        room.setStatus(StatusEnum.NORMAL.getCode().shortValue());

        chatRoomMapper.insert(room);
        log.info("聊天室创建成功,id:{}", room.getTbId());

        return room;
    }

    @Override
    public UserJoinChatRoom addJoinRecord(User joiner, ChatRoom room) {
        List<UserJoinChatRoom> userJoinChatRoom = joinChatRoomMapper.selectByUserIdAndRoomIdAndStatus(joiner.getTbId(), room.getTbId(),
                StatusEnum.RECORD_JOIN.getCode().shortValue());

        if (!CollectionUtils.isEmpty(userJoinChatRoom)) {
            log.debug("之前加入过聊天室");
            return userJoinChatRoom.get(userJoinChatRoom.size() - 1);
        }

        UserJoinChatRoom record = new UserJoinChatRoom();
        record.setCreateTime(new Date());
        record.setJoinTime(new Date());
        record.setUpdateTime(new Date());
        record.setRoomId(room.getTbId());
        record.setStatus(StatusEnum.RECORD_JOIN);
        record.setUserId(joiner.getTbId());

        joinChatRoomMapper.insert(record);
        log.debug("{} 加入聊天室", room.getUserId());

        return record;
    }

    @Override
    public int addExitRecord(User exiter, ChatRoom room) {
        UserJoinChatRoom updated = new UserJoinChatRoom();
        updated.setStatus(StatusEnum.RECORD_EXIT);
        updated.setUpdateTime(new Date());

        int count = joinChatRoomMapper.updateByRoomIdAndUserId(updated, room.getTbId(), exiter.getTbId());
        log.debug("退出聊天室：{}", room.getTbId());
        return count;
    }

    @Override
    public boolean deleteChatRoom(ChatRoom room) {
        ChatRoom updated = new ChatRoom();
        updated.setUpdateTime(new Date());
        updated.setStatus(StatusEnum.DELETE.getCode().shortValue());
        updated.setTbId(room.getTbId());

        int count = chatRoomMapper.updateByPrimaryKeySelective(updated);
        log.debug("删除聊天室:{}", count > 0);
        return count > 0;
    }

    @Override
    public int deleteJoinChatRecord(ChatRoom chatRoom) {
        UserJoinChatRoom updated = new UserJoinChatRoom();
        updated.setUpdateTime(new Date());
        updated.setStatus(StatusEnum.DELETE);

        int count = joinChatRoomMapper.updateByRoomId(updated, chatRoom.getTbId());
        log.debug("删除加入聊天室记录:{}条", count);
        return count;
    }
}
