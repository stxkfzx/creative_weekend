package xin.stxkfzx.weekend.service;

import xin.stxkfzx.weekend.entity.User;
import xin.stxkfzx.weekend.vo.UserVO;

/**
 * 用户模块
 *
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

    /**
     * 检查用户名是否重复
     *
     * @param nickname 用户名
     * @return 是否可用     true - 可用(不存在）     false - 不可用（存在）
     * @author ViterTian
     * @date 2019-04-16
     */
    Boolean checkNickName(String nickname);

    /**
     * 检测用户id是否存在
     *
     * @param userId userId
     * @return boolean
     */
    Boolean checkUserId(Integer userId);
}
