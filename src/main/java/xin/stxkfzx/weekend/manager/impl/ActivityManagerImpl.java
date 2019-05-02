package xin.stxkfzx.weekend.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xin.stxkfzx.weekend.entity.Activity;
import xin.stxkfzx.weekend.entity.UserJoinActivity;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.manager.ActivityManager;
import xin.stxkfzx.weekend.mapper.ActivityMapper;
import xin.stxkfzx.weekend.mapper.UserJoinActivityMapper;

import java.util.Date;

/**
 * @author fmy
 * @date 2019-04-28 16:00
 */
@Component
public class ActivityManagerImpl implements ActivityManager {
    private static final Logger log = LoggerFactory.getLogger(ActivityManagerImpl.class);
    private final ActivityMapper activityMapper;
    private final UserJoinActivityMapper joinActivityMapper;

    @Autowired
    public ActivityManagerImpl(ActivityMapper activityMapper, UserJoinActivityMapper joinActivityMapper) {
        this.activityMapper = activityMapper;
        this.joinActivityMapper = joinActivityMapper;
    }

    @Override
    public Activity addActivity(Activity activity) {
        // 初始化活动
        activity.setStatus(StatusEnum.REVIEW.getCode().shortValue());
        activity.setCreateTime(new Date());
        activity.setUpdateTime(new Date());

        activityMapper.insert(activity);
        log.info("添加活动: {}", activity.getTbId());

        return activity;
    }

    @Override
    public int deleteJoinActivityRecord(Activity activity) {
        UserJoinActivity updated = new UserJoinActivity();
        updated.setActivityId(activity.getTbId());
        updated.setStatus(StatusEnum.DELETE);
        updated.setUpdateTime(new Date());

        int count = joinActivityMapper.updateByActivityId(updated);
        log.debug("删除用户加入活动记录：{}条", count);
        return count;
    }
}
