package xin.stxkfzx.weekend.entity;

import java.util.List;
import java.util.StringJoiner;

/**
 * 活动详情
 *
 * @author fmy
 * @date 2019-04-29 20:36
 */
public class ActivityDetail {
    private List<ActivityTag> tagList;
    private List<ActivityBgImage> imageUrlList;
    private Activity activity;

    public List<ActivityTag> getTagList() {
        return tagList;
    }

    public void setTagList(List<ActivityTag> tagList) {
        this.tagList = tagList;
    }

    public List<ActivityBgImage> getImageUrlList() {
        return imageUrlList;
    }

    public void setImageUrlList(List<ActivityBgImage> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ActivityDetail.class.getSimpleName() + "[", "]")
                .add("tagList=" + tagList)
                .add("imageUrlList=" + imageUrlList)
                .add("activity=" + activity)
                .toString();
    }
}
