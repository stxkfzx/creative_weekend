package xin.stxkfzx.weekend.service;

import xin.stxkfzx.weekend.entity.Activity;
import xin.stxkfzx.weekend.expand.ActivityExpand;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.entity.User;

import java.util.List;

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
     * @param activity     创建活动参数
     * @param imageUrlList 背景图片列表
     * @return 包含创建的活动、聊天室和加入聊天室记录
     * @author fmy
     * @date 2019-04-16 19:54
     */
    ActivityExpand createActivity(Activity activity, List<String> imageUrlList);

    /**
     * 加入活动
     *
     * @param user     加入者
     * @param activity 加入活动
     * @return 加入活动记录
     * @author fmy
     * @date 2019-04-16 19:54
     */
    ActivityExpand joinActivity(User user, Activity activity);

    /**
     * 退出活动
     *
     * @param user     退出者
     * @param activity 退出的活动
     * @return
     * @author fmy
     * @date 2019-04-16 19:55
     */
    ActivityExpand exitActivity(User user, Activity activity);

    /**
     * 根据状态和Id获取指定活动
     *
     * @param activityId 活动Id
     * @param status     状态
     * @return 指定活动，如果没有则返回null
     * @author fmy
     * @date 2019-04-16 19:56
     */
    ActivityExpand getActivity(Integer activityId, StatusEnum status);

    /**
     * 根据条件获取活动列表,状态是正常
     *
     * @param condition 查询条件：范围、用户ID。如果condition为空，则查询全部
     * @param page      页数
     * @param pageSize  每页大小
     * @return 活动列表
     * @author fmy
     * @date 2019-04-16 19:57
     */
    ActivityExpand listActivityWithPage(Activity condition, int page, int pageSize);

    /**
     * 删除活动
     *
     * @param user 删除者
     * @param activity 删除的活动
     * @return 删除状态
     * @author fmy
     * @date 2019-04-18 19:32
     */
    ActivityExpand deleteActivity(User user, Activity activity);

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
