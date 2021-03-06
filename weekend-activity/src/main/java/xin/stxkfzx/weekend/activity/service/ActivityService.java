package xin.stxkfzx.weekend.activity.service;

import xin.stxkfzx.weekend.activity.entity.Activity;
import xin.stxkfzx.weekend.activity.expand.ActivityExpand;
import xin.stxkfzx.weekend.common.enums.StatusEnum;
import xin.stxkfzx.weekend.user.entity.User;

/**
 * 线下活动服务
 *
 * @author fmy
 * @date 2019-04-14 14:33
 */
public interface ActivityService {

    /**
     * 创建活动
     *
     * @param activity
     * @return
     * @author fmy
     * @date 2019-04-16 19:54
     */
    ActivityExpand createActivity(Activity activity);

    /**
     * 加入活动
     *
     * @param user
     * @param activity
     * @return
     * @author fmy
     * @date 2019-04-16 19:54
     */
    ActivityExpand joinActivity(User user, Activity activity);

    /**
     * 退出活动
     *
     * @param user
     * @param activity
     * @return
     * @author fmy
     * @date 2019-04-16 19:55
     */
    ActivityExpand exitActivity(User user, Activity activity);

    /**
     * 根据状态和Id获取指定活动
     *
     * @param activityId
     * @param status
     * @return 指定活动，如果没有则返回null
     * @author fmy
     * @date 2019-04-16 19:56
     */
    ActivityExpand getActivity(Integer activityId, StatusEnum status);

    /**
     * 根据条件获取活动列表,状态是审核及正常
     *
     * @param condition
     * @param page
     * @param pageSize
     * @return
     * @author fmy
     * @date 2019-04-16 19:57
     */
    ActivityExpand listActivityWithPage(Activity condition, int page, int pageSize);

    /**
     * 删除活动
     *
     * @param user
     * @param activity
     * @return
     * @author fmy
     * @date 2019-04-18 19:32
     */
    ActivityExpand deleteActivity(User user, Activity activity);

    /**
     * 删除活动记录，如果user是活动创建者，将删除整个活动记录
     *
     * @param user
     * @param activity
     * @return
     * @author fmy
     * @date 2019-04-19 9:27
     */
    ActivityExpand deleteJoinRecord(User user, Activity activity);

    /**
     * 获取活动加入记录
     *
     * @param activityId
     * @param page
     * @param pageSize
     * @return
     * @author fmy
     * @date 2019-04-19 9:32
     */
    ActivityExpand listJoinRecord(Integer activityId, int page, int pageSize);
}
