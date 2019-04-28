package xin.stxkfzx.weekend.manager.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xin.stxkfzx.weekend.BaseTest;
import xin.stxkfzx.weekend.entity.Activity;
import xin.stxkfzx.weekend.manager.ActivityManager;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

/**
 * @author fmy
 * @date 2019-04-28 16:22
 */
public class ActivityManagerImplTest extends BaseTest {
    @Autowired
    private ActivityManager activityManager;

    @Test
    public void addActivity() {
        Activity activity = new Activity();
        activity.setUserId(1);
        activity.setCoordinate("100, 300, 50");
        activity.setRange((short) 1000);
        activity.setTitle("创建活动测试标题");
        activity.setMaxCount(10);
        activity.setStartTime(new Date());
        activity.setDescription("创建活动测试内容");

        Activity addActivity = activityManager.addActivity(activity);
        assertNotNull(addActivity);

    }
}