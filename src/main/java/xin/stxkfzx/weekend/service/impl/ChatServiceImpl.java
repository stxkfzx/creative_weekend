package xin.stxkfzx.weekend.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xin.stxkfzx.weekend.annotation.CheckUserIsExist;
import xin.stxkfzx.weekend.annotation.ParamCheck;
import xin.stxkfzx.weekend.entity.ChatRoom;
import xin.stxkfzx.weekend.entity.User;
import xin.stxkfzx.weekend.entity.UserJoinChatRoom;
import xin.stxkfzx.weekend.enums.CheckTypeEnum;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.exception.SqlException;
import xin.stxkfzx.weekend.expand.ActivityExpand;
import xin.stxkfzx.weekend.manager.ChatManager;
import xin.stxkfzx.weekend.mapper.ChatRoomMapper;
import xin.stxkfzx.weekend.mapper.UserJoinChatRoomMapper;
import xin.stxkfzx.weekend.service.ChatService;

import java.util.Date;


/**
 * @author fmy
 * @date 2019-04-24 17:28
 */
@Service
public class ChatServiceImpl implements ChatService {
    private static final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);

    private final ChatRoomMapper chatRoomMapper;
    private final UserJoinChatRoomMapper joinChatRoomMapper;
    private final ChatManager chatManager;

    @Autowired
    public ChatServiceImpl(ChatRoomMapper chatRoomMapper, UserJoinChatRoomMapper joinChatRoomMapper, ChatManager chatManager) {
        this.chatRoomMapper = chatRoomMapper;
        this.joinChatRoomMapper = joinChatRoomMapper;
        this.chatManager = chatManager;
    }

    @ParamCheck(checkType = CheckTypeEnum.NOT_NULL)
    @CheckUserIsExist
    @Transactional(rollbackFor = SqlException.class)
    @Override
    public ActivityExpand joinRoom(User joiner, ChatRoom room) {
        UserJoinChatRoom record = chatManager.addJoinRecord(joiner, room);
        return new ActivityExpand().setRecord(record);
    }

}
