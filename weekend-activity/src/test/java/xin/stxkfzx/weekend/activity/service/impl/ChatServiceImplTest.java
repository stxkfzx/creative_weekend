package xin.stxkfzx.weekend.activity.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xin.stxkfzx.weekend.activity.ActivityBaseTest;
import xin.stxkfzx.weekend.activity.entity.Activity;
import xin.stxkfzx.weekend.activity.expand.ChatExpand;
import xin.stxkfzx.weekend.activity.service.ChatService;

import static org.junit.Assert.assertNotNull;

/**
 * @author fmy
 * @date 2019-04-25 14:06
 */
public class ChatServiceImplTest extends ActivityBaseTest {

    @Autowired
    private ChatService chatService;

    @Test
    public void createChatRoom() {
        Activity activity = new Activity();
        activity.setUserId(1);
        activity.setTbId(1);

        ChatExpand chatRoom = chatService.createChatRoom(activity);
        assertNotNull(chatRoom.getChatRoom());
        assertNotNull(chatRoom.getChatRoom().getTbId());
    }
}