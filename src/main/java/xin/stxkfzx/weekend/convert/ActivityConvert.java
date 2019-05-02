package xin.stxkfzx.weekend.convert;

import org.mapstruct.*;
import xin.stxkfzx.weekend.entity.*;
import xin.stxkfzx.weekend.vo.activity.*;

import java.util.List;
import java.util.Optional;

/**
 * @author fmy
 * @date 2019-04-30 15:51
 */
@Mapper(componentModel = "spring")
public interface ActivityConvert {

    ActivityTagVO fromActivityTag(ActivityTag tag);

    List<ActivityTagVO> fromActivityTagList(List<ActivityTag> tagList);

    @Mappings({
            @Mapping(target = "tbId", source = "activity.tbId"),
            @Mapping(target = "title", source = "activity.title"),
            @Mapping(target = "startTime", source = "activity.startTime"),
            @Mapping(target = "imgUrl", expression = "java(convertActivityBgImage(detail.getImageUrlList()))"),
    })
    ActivityVO fromActivityDetail(ActivityDetail detail);

    default String convertActivityBgImage(List<ActivityBgImage> imageList) {
        if (imageList != null && imageList.size() > 0) {
            return imageList.get(0).getUrl();
        }
        return null;
    }

    List<ActivityVO> fromActivityDetailList(List<ActivityDetail> detailList);

    @Mapping(target = "createTime", source = "activity.createTime")
    @Mapping(target = "tbId", source = "activity.tbId")
    @Mapping(target = "chatRoomId", source = "room.tbId")
    ActivityDetailVO fromActivityAndChatRoom(Activity activity, ChatRoom room);

    Activity toActivityParam(ActivityParam param);


    Activity toActivityConditionParam(ActivityConditionParam condition);
}
