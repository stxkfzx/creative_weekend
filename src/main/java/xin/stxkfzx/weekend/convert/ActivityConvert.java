package xin.stxkfzx.weekend.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.util.CollectionUtils;
import xin.stxkfzx.weekend.entity.*;
import xin.stxkfzx.weekend.vo.activity.*;

import java.util.List;

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
        return CollectionUtils.isEmpty(imageList) ? null : imageList.get(0).getUrl();
    }

    List<ActivityVO> fromActivityDetailList(List<ActivityDetail> detailList);

    @Mappings({
            @Mapping(target = "createTime", source = "activity.createTime"),
            @Mapping(target = "tbId", source = "activity.tbId"),
            @Mapping(target = "chatRoomId", source = "room.tbId"),
    })
    ActivityDetailVO fromActivityAndChatRoom(Activity activity, ChatRoom room);

    Activity toActivityParam(ActivityParam param);


    Activity toActivityConditionParam(ActivityConditionParam condition);


    @Mapping(target = "exitTime", ignore = true)
    JoinActivityRecordVO fromJoinActivityRecord(UserJoinActivity record);

    @Mappings({
            @Mapping(target = "joinTime", ignore = true),
            @Mapping(target = "exitTime", source = "updateTime")
    })
    JoinActivityRecordVO fromExitActivityRecord(UserJoinActivity record);
}
