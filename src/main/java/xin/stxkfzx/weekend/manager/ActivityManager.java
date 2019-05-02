package xin.stxkfzx.weekend.manager;

import xin.stxkfzx.weekend.entity.Activity;

/**
 * 下线活动通用服务
 *
 * @author fmy
 * @date 2019-04-28 15:29
 */
public interface ActivityManager {

    /**
     * 添加活动
     *
     * @param activity 创建活动参数
     * @return 如果创建成功，则返回成功创建的活动；否则抛出异常
     * @author fmy
     * @date 2019-04-28 15:38
     */
    Activity addActivity(Activity activity);


    /**
     * 删除活动加入记录
     *
     * @param activity 活动
     * @return 删除记录条数
     * @author fmy
     * @date 2019-05-02 16:13
     */
    int deleteJoinActivityRecord(Activity activity);
}
