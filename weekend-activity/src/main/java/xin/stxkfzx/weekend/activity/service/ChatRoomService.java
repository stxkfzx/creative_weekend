package xin.stxkfzx.weekend.activity.service;

import xin.stxkfzx.weekend.activity.entity.Activity;
import xin.stxkfzx.weekend.activity.expand.ChatRoomExpand;
import xin.stxkfzx.weekend.user.entity.User;

/**
 * 聊天室服务
 *
 * @author fmy
 * @date 2019-04-16 20:08
 */
public interface ChatRoomService {

    ChatRoomExpand createChat(User user);

    ChatRoomExpand createChat(Activity activity);

    ChatRoomExpand joinChat(User user, Integer chatId);

    ChatRoomExpand exitChat(User user, Integer chatId);


}
