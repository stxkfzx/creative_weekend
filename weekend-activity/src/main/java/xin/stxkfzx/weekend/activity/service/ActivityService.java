package xin.stxkfzx.weekend.activity.service;

import com.github.pagehelper.PageInfo;
import xin.stxkfzx.weekend.activity.entity.Activity;

/**
 * 线下活动服务
 *
 * @author fmy
 * @date 2019-04-14 14:33
 */
public interface ActivityService {
    PageInfo<Activity> selectByUserIdWithPage(int page, int pageSize, Integer userId);
}
