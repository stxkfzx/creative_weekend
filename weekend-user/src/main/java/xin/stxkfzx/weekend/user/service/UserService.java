package xin.stxkfzx.weekend.user.service;

import xin.stxkfzx.weekend.user.vo.UserVO;
import xin.stxkfzx.weekend.user.entity.User;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/12
 */
public interface UserService {
    /**
     * 添加用户
     *
     * @param user User对象
     * @return UserVO
     * @author ViterTian
     * @date 2019-04-14
     */
    UserVO addUser(User user);
}
