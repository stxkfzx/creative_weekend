package xin.stxkfzx.weekend.expand;

import xin.stxkfzx.weekend.entity.ChatRoom;
import xin.stxkfzx.weekend.entity.UserJoinChatRoom;

/**
 * @author fmy
 * @date 2019-04-24 17:27
 */
public class ChatExpand {
    private ChatRoom chatRoom;
    private UserJoinChatRoom record;

    public ChatExpand setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        return this;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public ChatExpand setRecord(UserJoinChatRoom record) {
        this.record = record;
        return this;
    }

    public UserJoinChatRoom getRecord() {
        return record;
    }
}
