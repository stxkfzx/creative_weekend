package xin.stxkfzx.weekend.expand;

import xin.stxkfzx.weekend.entity.Activity;
import xin.stxkfzx.weekend.entity.ChatRoom;
import xin.stxkfzx.weekend.entity.UserJoinActivity;
import xin.stxkfzx.weekend.entity.UserJoinChatRoom;

/**
 * 线下活动拓展
 *
 * @author fmy
 * @date 2019-04-16 19:48
 */
public class ActivityExpand {
    private Activity activity;
    private UserJoinActivity joinRecord;
    private int updateCount;
    private ChatRoom chatRoom;
    private UserJoinChatRoom chatRoomRecord;

    public Activity getActivity() {
        return activity;
    }

    public ActivityExpand setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public ActivityExpand setJoinRecord(UserJoinActivity joinRecord) {
        this.joinRecord = joinRecord;
        return this;
    }

    public UserJoinActivity getJoinRecord() {
        return joinRecord;
    }

    public ActivityExpand setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
        return this;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public ActivityExpand setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        return this;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public ActivityExpand setRecord(UserJoinChatRoom record) {
        this.chatRoomRecord = record;
        return this;
    }

    public UserJoinChatRoom getRecord() {
        return chatRoomRecord;
    }
}
