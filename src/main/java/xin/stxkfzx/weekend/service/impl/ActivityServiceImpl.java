package xin.stxkfzx.weekend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xin.stxkfzx.weekend.annotation.CheckUserIsExist;
import xin.stxkfzx.weekend.entity.*;
import xin.stxkfzx.weekend.enums.CheckTypeEnum;
import xin.stxkfzx.weekend.annotation.ParamCheck;
import xin.stxkfzx.weekend.expand.ActivityExpand;
import xin.stxkfzx.weekend.manager.ActivityManager;
import xin.stxkfzx.weekend.manager.ChatManager;
import xin.stxkfzx.weekend.mapper.ActivityBgImageMapper;
import xin.stxkfzx.weekend.mapper.ActivityMapper;
import xin.stxkfzx.weekend.mapper.ChatRoomMapper;
import xin.stxkfzx.weekend.mapper.UserJoinActivityMapper;
import xin.stxkfzx.weekend.service.ActivityService;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.exception.NoPermissionException;
import xin.stxkfzx.weekend.exception.SqlException;
import xin.stxkfzx.weekend.util.CheckUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author fmy
 * @date 2019-04-15 10:46
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    private static final Logger log = LoggerFactory.getLogger(ActivityServiceImpl.class);

    private final ActivityMapper activityMapper;
    private final UserJoinActivityMapper joinActivityMapper;
    private final ActivityBgImageMapper activityBgImageMapper;
    private final ChatRoomMapper chatRoomMapper;

    private final ActivityManager activityManager;
    private final ChatManager chatManager;

    @Autowired
    public ActivityServiceImpl(ActivityMapper activityMapper, UserJoinActivityMapper joinActivityMapper, ActivityBgImageMapper activityBgImageMapper, ChatRoomMapper chatRoomMapper, ActivityManager activityManager, ChatManager chatManager) {
        this.activityMapper = activityMapper;
        this.joinActivityMapper = joinActivityMapper;
        this.activityBgImageMapper = activityBgImageMapper;
        this.chatRoomMapper = chatRoomMapper;
        this.activityManager = activityManager;
        this.chatManager = chatManager;
    }

    @CheckUserIsExist
    @Transactional(rollbackFor = SqlException.class)
    @Override
    public ActivityExpand createActivity(Activity activity, List<String> imageUrlList) {
        CheckUtils.checkBean(activity);
        // 创建活动
        Activity create = activityManager.addActivity(activity);

        if (imageUrlList != null) {
            // 插入背景图
            List<ActivityBgImage> bgImageList = convertImage(imageUrlList, create);
            int count = activityBgImageMapper.insertList(bgImageList);
            log.debug("插入背景图片数量：{}", count);
        }

        // 关联创建聊天室
        User creator = new User();
        creator.setTbId(create.getUserId());
        ChatRoom chatRoom = chatManager.addChatRoom(activity, creator);

        // 关联加入聊天室
        UserJoinChatRoom record = chatManager.addJoinRecord(creator, chatRoom);

        log.info("成功创建活动");
        return new ActivityExpand().setActivity(activity).setChatRoom(chatRoom).setRecord(record);
    }

    private List<ActivityBgImage> convertImage(List<String> imageUrlList, Activity create) {
        List<ActivityBgImage> bgImageList = new ArrayList<>(imageUrlList.size());
        ActivityBgImage img;
        for (String url :
                imageUrlList) {
            img = new ActivityBgImage();
            img.setActivityId(create.getTbId());
            img.setCreateTime(new Date());
            img.setStatus(StatusEnum.NORMAL);
            img.setUpdateTime(new Date());
            img.setUrl(url);
            bgImageList.add(img);
        }
        return bgImageList;
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

    @ParamCheck(checkType = CheckTypeEnum.NOT_NULL)
    @Override
    public ActivityExpand getActivity(Integer activityId, StatusEnum status) {
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        checkActivityNotDelete(activity);
        ChatRoom chatRoom = chatRoomMapper.selectOneByActivateId(activityId);
        return new ActivityExpand().setActivity(activity).setChatRoom(chatRoom);
    }

    private void checkActivityNotDelete(Activity activity) {
        CheckUtils.notNull(activity, ExceptionEnum.ACTIVATE_NOT_EXIST);
        CheckUtils.check(activity.getStatus() >= StatusEnum.DELETE.getCode().shortValue(),
                ExceptionEnum.ACTIVATE_NOT_EXIST);
    }

    @Override
    public ActivityExpand listActivityWithPage(Activity condition, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        PageInfo<ActivityDetail> pageInfo =
                new PageInfo<>(activityMapper.selectActivityDetailAndNormalByConditionWithPage(condition));
        return new ActivityExpand().setPage(pageInfo);
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
            updateCount = joinActivityMapper.updateByActivityId(record);
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
