package xin.stxkfzx.weekend.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xin.stxkfzx.weekend.BaseTest;
import xin.stxkfzx.weekend.entity.Activity;
import xin.stxkfzx.weekend.expand.ActivityExpand;
import xin.stxkfzx.weekend.service.ChatService;

import static org.junit.Assert.assertNotNull;

/**
 * @author fmy
 * @date 2019-04-25 14:06
 */
public class ChatServiceImplTest extends BaseTest {

    @Autowired
    private ChatService chatService;

    @Test
    public void createChatRoom() {
        Activity activity = new Activity();
        activity.setUserId(1);
        activity.setTbId(1);

        ActivityExpand chatRoom = chatService.createChatRoom(activity);
        assertNotNull(chatRoom.getChatRoom());
        assertNotNull(chatRoom.getChatRoom().getTbId());
    }
}