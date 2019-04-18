package xin.stxkfzx.weekend.activity.expand;

import xin.stxkfzx.weekend.activity.entity.Activity;
import xin.stxkfzx.weekend.activity.entity.UserJoinActivity;

/**
 * 线下活动拓展
 *
 * @author fmy
 * @date 2019-04-16 19:48
 */
public class ActivityExpand {
    private Activity activity;
    private UserJoinActivity joinRecord;

    public Activity getActivity() {
        return activity;
    }

    public ActivityExpand setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public ActivityExpand setJoinRecord(UserJoinActivity joinRecord) {
        this.joinRecord = joinRecord;
        return this;
    }

    public UserJoinActivity getJoinRecord() {
        return joinRecord;
    }
}
