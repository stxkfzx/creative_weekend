package xin.stxkfzx.weekend.activity.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xin.stxkfzx.weekend.activity.annotation.LinkStatus;
import xin.stxkfzx.weekend.activity.enums.CheckTypeEnum;
import xin.stxkfzx.weekend.activity.annotation.ParamCheck;
import xin.stxkfzx.weekend.activity.entity.Activity;
import xin.stxkfzx.weekend.activity.entity.UserJoinActivity;
import xin.stxkfzx.weekend.activity.enums.LinkTypeEnum;
import xin.stxkfzx.weekend.activity.expand.ActivityExpand;
import xin.stxkfzx.weekend.activity.mapper.ActivityMapper;
import xin.stxkfzx.weekend.activity.mapper.UserJoinActivityMapper;
import xin.stxkfzx.weekend.activity.service.ActivityService;
import xin.stxkfzx.weekend.auth.config.CheckUserIsExist;
import xin.stxkfzx.weekend.common.enums.ExceptionEnum;
import xin.stxkfzx.weekend.common.enums.StatusEnum;
import xin.stxkfzx.weekend.common.exception.NoPermissionException;
import xin.stxkfzx.weekend.common.exception.SqlException;
import xin.stxkfzx.weekend.common.exception.WeekendException;
import xin.stxkfzx.weekend.common.util.CheckUtils;
import xin.stxkfzx.weekend.user.entity.User;

import java.util.Date;

/**
 * @author fmy
 * @date 2019-04-15 10:46
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    private static final Logger log = LoggerFactory.getLogger(ActivityServiceImpl.class);

    private final ActivityMapper activityMapper;
    private final UserJoinActivityMapper joinActivityMapper;

    @Autowired
    public ActivityServiceImpl(ActivityMapper activityMapper, UserJoinActivityMapper joinActivityMapper) {
        this.activityMapper = activityMapper;
        this.joinActivityMapper = joinActivityMapper;
    }

    @LinkStatus(type = LinkTypeEnum.ADD)
    @ParamCheck(checkType = CheckTypeEnum.BEAN_VALIDATION)
    @CheckUserIsExist
    @Transactional(rollbackFor = SqlException.class)
    @Override
    public ActivityExpand createActivity(Activity activity) throws WeekendException {
        // 初始化活动
        activity.setStatus(StatusEnum.REVIEW.getCode().shortValue());
        activity.setCreateTime(new Date());
        activity.setUpdateTime(new Date());

        activityMapper.insert(activity);

        return new ActivityExpand().setActivity(activity);
    }

    @ParamCheck(checkType = CheckTypeEnum.NOT_NULL)
    @CheckUserIsExist
    @Transactional(rollbackFor = SqlException.class)
    @Override
    public ActivityExpand joinActivity(User user, Activity activity) {
        // 创建者不能加入
        joinAndExitActivityCondition(user, activity);

        UserJoinActivity record = joinActivityMapper.selectOneByUserIdAndActivityId(user.getTbId(), activity.getTbId());

        if (record != null) {
            updateJoinRecord(record);
        } else {
            record = insertJoinRecord(user, activity);
        }

        return new ActivityExpand().setJoinRecord(record);
    }

    private void joinAndExitActivityCondition(User user, Activity activity) {
        // 活动创建者不能加入或退出活动
        CheckUtils.check(!user.getTbId().equals(activity.getUserId()), "field.invalid", user.getTbId());
        // 活动是否存在
        Activity findActivity = activityMapper.selectByPrimaryKey(activity.getTbId());
        CheckUtils.notNull(findActivity, "id.error", activity.getTbId());
        CheckUtils.check(findActivity.getStatus() != StatusEnum.DELETE.getCode().shortValue(), ExceptionEnum.ACTIVATE_NOT_EXIST);
        CheckUtils.check(findActivity.getStatus() != StatusEnum.REVIEW.getCode().shortValue(), ExceptionEnum.ACTIVITY_IS_REVIEW);
    }

    private UserJoinActivity insertJoinRecord(User user, Activity activity) {
        UserJoinActivity record;
        record = new UserJoinActivity();
        record.setUpdateTime(new Date());
        record.setJoinTime(new Date());
        record.setStatus(UserJoinActivity.JOIN);
        record.setCreateTime(new Date());
        record.setActivityId(activity.getTbId());
        record.setUserId(user.getTbId());
        // TODO: 2019/4/18 付款状态应该通过支付成功的回调通知获取，这里简单处理下
        record.setPaymentStatus(UserJoinActivity.PAY_SUCCESS);
        log.debug("insert join activity:{} status", activity.getTbId());

        joinActivityMapper.insert(record);
        return record;
    }

    private void updateJoinRecord(UserJoinActivity record) {
        record.setUpdateTime(new Date());
        record.setJoinTime(new Date());
        record.setStatus(UserJoinActivity.JOIN);
        log.debug("update join activity:{} status", record.getActivityId());

        joinActivityMapper.updateByPrimaryKey(record);
    }


    @Transactional(rollbackFor = SqlException.class)
    @ParamCheck(checkType = CheckTypeEnum.NOT_NULL)
    @CheckUserIsExist
    @Override
    public ActivityExpand exitActivity(User user, Activity activity) {
        joinAndExitActivityCondition(user, activity);

        UserJoinActivity record = joinActivityMapper.selectOneByUserIdAndActivityId(user.getTbId(), activity.getTbId());
        CheckUtils.notNull(record, "value.is.null");

        record.setStatus(UserJoinActivity.EXIT);
        record.setUpdateTime(new Date());
        joinActivityMapper.updateByPrimaryKeySelective(record);

        return new ActivityExpand().setJoinRecord(record);
    }

    @Override
    public ActivityExpand getActivity(Integer activityId, StatusEnum status) {
        CheckUtils.notNull(activityId, "value.is.null");
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        checkActivityNotDelete(activity);
        return new ActivityExpand().setActivity(activity);
    }

    private void checkActivityNotDelete(Activity activity) {
        CheckUtils.check(activity.getStatus() >= StatusEnum.DELETE.getCode().shortValue(),
                ExceptionEnum.ACTIVATE_NOT_EXIST);
    }

    @Override
    public ActivityExpand listActivityWithPage(Activity condition, int page, int pageSize) {
        // PageHelper.startPage(page, pageSize);
        // activityMapper.selectByConditionWithPage(condition);

        return null;
    }

    @Transactional(rollbackFor = SqlException.class)
    @ParamCheck(checkType = CheckTypeEnum.NOT_NULL)
    @CheckUserIsExist
    @Override
    public ActivityExpand deleteActivity(User user, Activity activity) {
        Activity select = activityMapper.selectByPrimaryKey(activity.getTbId());

        // 删除人必须是活动创建者
        if (!activity.getUserId().equals(user.getTbId())) {
            throw new NoPermissionException(ExceptionEnum.NO_PERMISSION);
        }

        select.setStatus(StatusEnum.DELETE.getCode().shortValue());
        select.setUpdateTime(new Date());
        activityMapper.updateByPrimaryKeySelective(select);

        return new ActivityExpand().setActivity(select);
    }

    @Transactional(rollbackFor = SqlException.class)
    @ParamCheck(checkType = CheckTypeEnum.NOT_NULL)
    @CheckUserIsExist
    @Override
    public ActivityExpand deleteJoinRecord(User user, Activity activity) {
        Activity select = activityMapper.selectByPrimaryKey(activity.getTbId());
        checkActivityNotDelete(select);

        UserJoinActivity record = new UserJoinActivity();
        record.setUpdateTime(new Date());
        record.setStatus(StatusEnum.DELETE);
        record.setActivityId(select.getTbId());

        int updateCount;
        if (activity.getUserId().equals(user.getTbId())) {
            // 删除全部记录
           updateCount  = joinActivityMapper.updateByActivityId(record);
        } else {
            record.setUserId(user.getTbId());
            updateCount = joinActivityMapper.updateByActivityIdAndUserId(record);
        }

        return new ActivityExpand().setUpdateCount(updateCount);
    }

    @Override
    public ActivityExpand listJoinRecord(Integer activityId, int page, int pageSize) {
        return null;
    }
}
