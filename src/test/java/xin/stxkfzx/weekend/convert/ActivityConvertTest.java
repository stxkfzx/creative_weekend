package xin.stxkfzx.weekend.convert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xin.stxkfzx.weekend.BaseTest;
import xin.stxkfzx.weekend.entity.*;
import xin.stxkfzx.weekend.vo.activity.ActivityDetailVO;
import xin.stxkfzx.weekend.vo.activity.ActivityParam;
import xin.stxkfzx.weekend.vo.activity.ActivityTagVO;
import xin.stxkfzx.weekend.vo.activity.ActivityVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author fmy
 * @date 2019-04-30 16:08
 */
public class ActivityConvertTest extends BaseTest {
    @Autowired
    private ActivityConvert activityConvert;

    @Test
    public void fromActivityTag() {
        ActivityTag tag = new ActivityTag();
        tag.setActivityId(1);
        tag.setContent("测试");
        tag.setCreateTime(new Date());
        tag.setStatus((short) 1);
        tag.setTbId(1);
        tag.setUpdateTime(new Date());

        ActivityTagVO vo = activityConvert.fromActivityTag(tag);

        assertNotNull(vo);
        assertEquals(vo.getContent(), tag.getContent());
    }

    @Test
    public void fromActivityTagList() {
        List<ActivityTag> tagList = new ArrayList<>(16);
        for (int i = 0; i < 10; i++) {
            ActivityTag e = new ActivityTag();
            e.setTbId(i);
            e.setContent("测试" + i);
            tagList.add(e);
        }

        List<ActivityTagVO> voList = activityConvert.fromActivityTagList(tagList);
        assertNotNull(voList);
        assertEquals(voList.size(), tagList.size());

        for (int i = 0; i < tagList.size(); i++) {
            assertEquals(voList.get(i).getContent(), tagList.get(i).getContent());
            assertEquals(voList.get(i).getTbId(), tagList.get(i).getTbId());
        }
    }

    @Test
    public void fromActivityDetail() {
        ActivityDetail detail = getDetail(0);
        ActivityVO vo = activityConvert.fromActivityDetail(detail);
        check(detail, vo);

        detail.setImageUrlList(null);
        vo = activityConvert.fromActivityDetail(detail);
    }

    private void check(ActivityDetail detail, ActivityVO vo) {
        assertNotNull(vo);
        assertNotNull(vo.getImgUrl());
        assertNotNull(vo.getTbId());
        assertNotNull(vo.getTitle());

        for (int i = 0; i < detail.getTagList().size(); i++) {
            assertEquals(vo.getTagList().get(i).getContent(), detail.getTagList().get(i).getContent());
            assertEquals(vo.getTagList().get(i).getTbId(), detail.getTagList().get(i).getTbId());
        }
    }

    private ActivityDetail getDetail(int index) {
        ActivityDetail detail = new ActivityDetail();

        Activity activity = new Activity();
        activity.setTbId(123 + index);
        activity.setStartTime(new Date());
        activity.setTitle("标题" + index);

        ArrayList<ActivityBgImage> imageUrlList = new ArrayList<>();
        ActivityBgImage e = new ActivityBgImage();
        e.setUrl("bbbb" + index);
        imageUrlList.add(e);
        e = new ActivityBgImage();
        e.setUrl("aaaa" + index);
        imageUrlList.add(e);
        detail.setImageUrlList(imageUrlList);

        List<ActivityTag> tagList = new ArrayList<>(16);
        for (int i = 0; i < 13; i++) {
            ActivityTag tag = new ActivityTag();
            tag.setTbId(i);
            tag.setContent("第 "+index+" 测试" + i);
            tagList.add(tag);
        }
        detail.setTagList(tagList);

        detail.setActivity(activity);
        return detail;
    }

    @Test
    public void fromActivityAndChatRoom() {

        Activity activity = new Activity();
        activity.setTitle("sdadadsa");
        ChatRoom room = new ChatRoom();
        room.setTbId(123123);

        ActivityDetailVO vo = activityConvert.fromActivityAndChatRoom(activity, room);
        assertEquals(vo.getChatRoomId(), room.getTbId());
        assertEquals(vo.getTitle(), activity.getTitle());
    }

    @Test
    public void toActivityParam() {

        ActivityParam param = new ActivityParam();
        param.setDescription("sadasd");
        param.setStartTime(new Date(System.currentTimeMillis() + 30*24*60*60));

        Activity activity = activityConvert.toActivityParam(param);
        assertNotNull(activity);
        assertEquals(activity.getDescription(), param.getDescription());
        assertEquals(activity.getStartTime(), param.getStartTime());

    }

    @Test
    public void fromActivityDetailList() {
        List<ActivityDetail> list = new ArrayList<>(16);
        for (int i = 0; i < 16; i++) {
            list.add(getDetail(i));
        }

        List<ActivityVO> voList = activityConvert.fromActivityDetailList(list);
        for (int i = 0; i < list.size(); i++) {
            check(list.get(i), voList.get(i));
            System.out.println(voList.get(i).getImgUrl());
        }

    }
}