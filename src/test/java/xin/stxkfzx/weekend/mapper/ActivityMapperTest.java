package xin.stxkfzx.weekend.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xin.stxkfzx.weekend.BaseTest;
import xin.stxkfzx.weekend.entity.ActivityDetail;

import java.util.List;

/**
 * @author fmy
 * @date 2019-04-29 21:50
 */
public class ActivityMapperTest extends BaseTest {
    @Autowired
    private ActivityMapper activityMapper;

    @Test
    public void selectNormalByConditionWithPage() {

        List<ActivityDetail> activityDetails = activityMapper.selectActivityDetailAndNormalByConditionWithPage(null);
        activityDetails.forEach(item -> {
            System.out.println(String.format("activityId=%d", item.getActivity().getTbId()));
            // item.getImageUrlList().forEach(url -> System.out.println(String.format("urlId=%d, url=%s", url.getTbId(), url.getUrl())));
            item.getTagList().forEach(tag -> System.out.println(String.format("tagId=%d, content=%s", tag.getTbId(), tag.getContent())));
            System.out.println("-----------------------------------------------");
        });
    }
}