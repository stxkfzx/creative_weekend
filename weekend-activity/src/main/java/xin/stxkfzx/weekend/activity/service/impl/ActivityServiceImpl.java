package xin.stxkfzx.weekend.activity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.stxkfzx.weekend.activity.entity.Activity;
import xin.stxkfzx.weekend.activity.mapper.ActivityMapper;
import xin.stxkfzx.weekend.activity.service.ActivityService;

/**
 * @author fmy
 * @date 2019-04-15 10:46
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityMapper activityMapper;

    @Autowired
    public ActivityServiceImpl(ActivityMapper activityMapper) {
        this.activityMapper = activityMapper;
    }

    @Override
    public PageInfo<Activity> selectByUserIdWithPage(int page, int pageSize, Integer userId) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(activityMapper.selectByUserIdWithPage(userId));
    }

}
