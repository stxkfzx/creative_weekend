package xin.stxkfzx.weekend.service;

import xin.stxkfzx.weekend.entity.ChatRoom;
import xin.stxkfzx.weekend.entity.User;
import xin.stxkfzx.weekend.expand.ActivityExpand;

/**
 * 聊天服务
 *
 * @author fmy
 * @date 2019-04-24 17:25
 */
public interface ChatService {


    /**
     * 加入聊天室
     *
     * @param joiner 加入者
     * @param room   聊天室
     * @return
     * @author fmy
     * @date 2019-04-27 0:38
     */
    ActivityExpand joinRoom(User joiner, ChatRoom room);
}
