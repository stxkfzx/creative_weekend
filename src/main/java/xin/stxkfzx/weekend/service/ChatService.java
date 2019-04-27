package xin.stxkfzx.weekend.service;

import xin.stxkfzx.weekend.entity.Activity;
import xin.stxkfzx.weekend.entity.ChatRoom;
import xin.stxkfzx.weekend.expand.ChatExpand;
import xin.stxkfzx.weekend.user.entity.User;

/**
 * 聊天室服务
 *
 * @author fmy
 * @date 2019-04-24 17:25
 */
public interface ChatService {

    /**
     * 创建聊天室
     *
     * @param activity
     * @return
     * @author fmy
     * @date 2019-04-24 17:42
     */
    ChatExpand createChatRoom(Activity activity);

    /**
     * 加入聊天室
     *
     * @param user
     * @param room
     * @return
     * @author fmy
     * @date 2019-04-27 0:38
     */
    ChatExpand joinRoom(User user, ChatRoom room);
}
