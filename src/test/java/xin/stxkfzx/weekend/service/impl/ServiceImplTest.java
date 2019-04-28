package xin.stxkfzx.weekend.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xin.stxkfzx.weekend.BaseTest;
import xin.stxkfzx.weekend.entity.Activity;
import xin.stxkfzx.weekend.entity.User;
import xin.stxkfzx.weekend.exception.CheckException;
import xin.stxkfzx.weekend.exception.SqlException;
import xin.stxkfzx.weekend.expand.ActivityExpand;
import xin.stxkfzx.weekend.service.ActivityService;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
/**
 * @author fmy
 * @date 2019-04-15 11:05
 */
public class ServiceImplTest extends BaseTest {
    @Autowired
    private ActivityService service;

    @Test
    public void createActivity() {
        Activity activity = new Activity();
        activity.setUserId(1);
        activity.setCoordinate("100, 300, 50");
        activity.setRange((short) 1000);
        activity.setTitle("创建活动测试标题");
        activity.setMaxCount(10);
        activity.setStartTime(new Date());
        activity.setDescription("创建活动测试内容");

        ActivityExpand expand = service.createActivity(activity);
        assertNotNull(expand.getActivity());

        // 测试关联创建聊天室
        assertNotNull(expand.getChatRoom());

        // 测试关联加入聊天室
        assertNotNull(expand.getRecord());

        // 测试check AOP
        exception.expect(CheckException.class);
        service.createActivity(null);
    }

    @Test
    public void joinActivity() {
        User user = new User();
        user.setTbId(1);

        Activity activity = new Activity();
        activity.setTbId(123);

        exception.expect(CheckException.class);
        exception.expectMessage("Invalid id:" + activity.getTbId());
        ActivityExpand expand = service.joinActivity(user, activity);

        activity.setTbId(1);
        expand = service.joinActivity(user, activity);
        assertNotNull(expand.getJoinRecord());
    }

    @Test
    public void exitActivity() {
    }

    @Test
    public void getActivity() {
    }

    @Test
    public void listActivityWithPage() {
    }

    @Test
    public void deleteActivity() {
        User user = new User();
        user.setTbId(1);

        Activity activity = new Activity();
        activity.setTbId(1);
        activity.setUserId(1);

        exception.expect(SqlException.class);
        service.deleteActivity(user, activity);
    }

    @Test
    public void deleteJoinRecord() {
    }

    @Test
    public void listJoinRecord() {
    }
}