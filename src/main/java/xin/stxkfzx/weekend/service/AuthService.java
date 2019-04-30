package xin.stxkfzx.weekend.service;

import xin.stxkfzx.weekend.entity.UserBase;

/**
 * 用户授权中心
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/28
 */
public interface AuthService {
    /**
     * 登录授权
     * 将token加在cookie中，并设置过期时间
     *
     * @param username 用户名
     * @param password 密码
     * @return Void
     * @author ViterTian
     * @date 2019-04-13
     */
    UserBase login(String username, String password);

    /**
     * 验证用户信息
     *
     * @param token 浏览器传过来的token流
     * @return UserBase
     * @author ViterTian
     * @date 2019-04-13
     */
    UserBase verifyUser(String token);

    /**
     * 通过id查找
     *
     * @param userId id
     * @return UserBase
     * @author ViterTian
     * @date 2019-04-14
     */
    UserBase findUserById(Integer userId);
}
