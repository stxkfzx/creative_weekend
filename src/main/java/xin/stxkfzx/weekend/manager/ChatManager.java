package xin.stxkfzx.weekend.manager;

import xin.stxkfzx.weekend.entity.Activity;
import xin.stxkfzx.weekend.entity.ChatRoom;
import xin.stxkfzx.weekend.entity.User;
import xin.stxkfzx.weekend.entity.UserJoinChatRoom;

/**
 * 聊天通用服务
 *
 * @author fmy
 * @date 2019-04-28 17:17
 */
public interface ChatManager {

    /**
     * 添加聊天室
     *
     * @param activity 活动
     * @param creator  创建者
     * @return 如果创建成功则返回聊天室；否则抛出异常
     * @author fmy
     * @date 2019-04-28 17:29
     */
    ChatRoom addChatRoom(Activity activity, User creator);

    /**
     * 添加加入聊天室记录
     *
     * @param joiner 加入者
     * @param room   聊天室
     * @return 加入记录
     * @author fmy
     * @date 2019-04-28 19:34
     */
    UserJoinChatRoom addJoinRecord(User joiner, ChatRoom room);

    /**
     * 退出聊天室记录
     *
     * @param exiter 退出者
     * @param room   聊天室
     * @return 退出记录条数
     * @author fmy
     * @date 2019-05-02 11:06
     */
    int addExitRecord(User exiter, ChatRoom room);

    /**
     * 删除聊天室
     *
     * @param activity	 活动
     * @return 删除状态
     * @author fmy
     * @date 2019-05-02 16:18
     */
    boolean deleteChatRoom(Activity activity);

    /**
     * 删除加入聊天室记录
     *
     * @param chatRoom	 聊天室
     * @return 删除条数
     * @author fmy
     * @date 2019-05-02 16:18
     */
    int deleteJoinChatRecord(ChatRoom chatRoom);
}
