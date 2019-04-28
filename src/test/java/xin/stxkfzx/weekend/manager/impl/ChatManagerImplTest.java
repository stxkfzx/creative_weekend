package xin.stxkfzx.weekend.manager.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xin.stxkfzx.weekend.BaseTest;
import xin.stxkfzx.weekend.entity.Activity;
import xin.stxkfzx.weekend.entity.ChatRoom;
import xin.stxkfzx.weekend.entity.User;
import xin.stxkfzx.weekend.manager.ChatManager;

import static org.junit.Assert.*;

/**
 * @author fmy
 * @date 2019-04-28 17:37
 */
public class ChatManagerImplTest extends BaseTest {
    @Autowired
    private ChatManager chatManager;

    @Test
    public void addChatRoom() {
        Activity activity = new Activity();
        activity.setTbId(1);
        User creator = new User();
        creator.setTbId(1);

        ChatRoom chatRoom = chatManager.addChatRoom(activity, creator);
        assertNotNull(chatRoom.getTbId());
    }


}