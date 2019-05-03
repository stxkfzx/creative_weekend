package xin.stxkfzx.weekend.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import xin.stxkfzx.weekend.entity.Friend;
import xin.stxkfzx.weekend.vo.FriendVO;

import java.util.List;

/**
 * User对象转换为UserVO
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/3
 */
@Mapper(componentModel = "spring")
public interface Friend2FriendVO {
    /**
     * 转换VideoShareVO
     *
     * @param friend friend对象
     * @return userVO
     * @author ViterTian
     * @date 2019-05-02
     */
    @Mappings({
    })
    FriendVO friendToFriendVO(Friend friend);

    /**
     * 转换 List<VideoShareVO>
     *
     * @param friend List<user>
     * @return List<UserVO>
     * @author ViterTian
     * @date 2019-05-02
     */
    @Mappings({})
    List<FriendVO> friendToFriendVO(List<Friend> friend);

}
