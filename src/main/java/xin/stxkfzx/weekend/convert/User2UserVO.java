package xin.stxkfzx.weekend.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import xin.stxkfzx.weekend.entity.User;
import xin.stxkfzx.weekend.vo.UserVO;

import java.util.List;

/**
 * User对象转换为UserVO
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/3
 */
@Mapper(componentModel = "spring")
public interface User2UserVO {
    /**
     * 转换UserBase
     *
     * @param friend friend对象
     * @return userVO
     * @author ViterTian
     * @date 2019-05-02
     */
/*    @Mappings({
    })
    User2UserBase userToUserBase(Friend friend);*/

    /**
     * 转换 List<UserBase>
     *
     * @param friend List<user>
     * @return List<UserVO>
     * @author ViterTian
     * @date 2019-05-02
     */
    @Mappings({})
    List<UserVO> userToUserUserVO(List<User> friend);

}

