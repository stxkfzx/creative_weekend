package xin.stxkfzx.weekend.activity.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xin.stxkfzx.weekend.activity.ActivityBaseTest;
import xin.stxkfzx.weekend.activity.entity.Activity;
import xin.stxkfzx.weekend.activity.expand.ActivityExpand;
import xin.stxkfzx.weekend.activity.service.ActivityService;
import xin.stxkfzx.weekend.common.exception.CheckException;
import xin.stxkfzx.weekend.common.exception.SqlException;
import xin.stxkfzx.weekend.user.entity.User;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
/**
 * @author fmy
 * @date 2019-04-15 11:05
 */
public class ActivityServiceImplTest extends ActivityBaseTest {
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

        service.createActivity(activity);
        assertNotNull(activity.getTbId());

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