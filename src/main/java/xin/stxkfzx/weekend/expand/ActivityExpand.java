package xin.stxkfzx.weekend.expand;

import com.github.pagehelper.PageInfo;
import xin.stxkfzx.weekend.entity.*;

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
    private PageInfo<ActivityDetail> page;
    private boolean success;

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

    public ActivityExpand setPage(PageInfo<ActivityDetail> page) {
        this.page = page;
        return this;
    }

    public PageInfo<ActivityDetail> getPage() {
        return page;
    }

    public ActivityExpand setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public boolean getSuccess() {
        return success;
    }
}
