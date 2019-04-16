package xin.stxkfzx.weekend.user.service;

import xin.stxkfzx.weekend.user.entity.User;
import xin.stxkfzx.weekend.user.vo.UserVO;

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

    /**
     * 根据id查询用户信息是否完整
     *
     * @param id 要检验用户的id
     * @return 用户信息是否完善 true-完善 false-未完善
     * @author ViterTian
     * @date 2019-04-16
     */
    Boolean checkUserWhole(Integer id);

    /**
     * 用户实名认证接口
     *
     * @param user User对象
     * @return 完善信息后用户id
     * @author ViterTian
     * @date 2019-04-16
     */
    Integer authUser(User user);
}
