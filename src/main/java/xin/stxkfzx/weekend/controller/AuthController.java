package xin.stxkfzx.weekend.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xin.stxkfzx.weekend.annotation.PassToken;
import xin.stxkfzx.weekend.config.JwtProperties;
import xin.stxkfzx.weekend.entity.ResultBean;
import xin.stxkfzx.weekend.entity.UserBase;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.exception.WeekendException;
import xin.stxkfzx.weekend.service.impl.AuthServiceImpl;
import xin.stxkfzx.weekend.util.JwtUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 处理授权的一些接口
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
@RestController
@RequestMapping("/auth")
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthServiceImpl authServiceImpl;

    @Autowired
    public AuthController(AuthServiceImpl authServiceImpl, JwtProperties jwtProperties, RedisTemplate redisTemplate) {
        this.authServiceImpl = authServiceImpl;
        this.jwtProperties = jwtProperties;
        this.redisTemplate = redisTemplate;
    }

    private final JwtProperties jwtProperties;
    private final RedisTemplate redisTemplate;

    /**
     * 登录授权
     * 将token加在cookie中，并设置过期时间
     *
     * @param username 用户名
     * @param password 密码
     * @param response response
     * @return Void
     * @author ViterTian
     * @date 2019-04-13
     */
    @PassToken
    @PostMapping("/accredit")
    public ResponseEntity<ResultBean<UserBase>> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletResponse response
    ) {
        UserBase userBase = authServiceImpl.login(username, password);
        // 生成Token
        String token = JwtUtils.generateToken(userBase, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
        if (StringUtils.isBlank(token)) {
            logger.error("【授权中心】token为空");
            throw new WeekendException(ExceptionEnum.INVALID_USER);
        }
        logger.info("【授权中心】生成token为：{}", token);
        response.setHeader("Authorization", token);
        redisTemplate.opsForValue().set(token, userBase, 30000, TimeUnit.HOURS);
        return ResponseEntity.ok(new ResultBean<>(StatusEnum.SUCCESS, userBase));
    }

    /**
     * 验证用户信息
     *
     * @param token 浏览器传过来的token流
     * @return ResponseEntity<UserBase>
     * @author ViterTian
     * @date 2019-04-13
     */
    @PassToken
    @GetMapping("verify")
    public ResponseEntity<UserBase> verifyUser(@RequestHeader("Authorization") String token, HttpServletResponse response) {
        try {
            UserBase userBase = authServiceImpl.verifyUser(token);
            // 成功，刷新Token
            String newToken = JwtUtils.generateToken(userBase, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            // 将新的Token信息设置在Header的Authorization里,并保存在Redis中
            response.setHeader("Authorization", newToken);
            redisTemplate.opsForValue().set(token, userBase, 30000, TimeUnit.HOURS);
            return ResponseEntity.ok(userBase);
        } catch (Exception e) {
            // Token无效
            logger.error("【授权中心】Token:{}无效", token);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
