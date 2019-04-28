package xin.stxkfzx.weekend.service;

import org.springframework.http.ResponseEntity;
import xin.stxkfzx.weekend.entity.UserBase;

import javax.servlet.http.HttpServletResponse;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/28
 */
public interface AuthService {
    /**
     *
     * @param username
     * @param password
     * @param response
     */
    void login(String username, String password, HttpServletResponse response);
    /**
     *
     * @param token
     * @param response
     * @return
     */
    ResponseEntity<UserBase> verifyUser(String token, HttpServletResponse response);

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
