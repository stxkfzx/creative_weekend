package xin.stxkfzx.weekend.auth.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import xin.stxkfzx.weekend.auth.entity.UserBase;
import xin.stxkfzx.weekend.auth.mapper.UserBaseMapper;
import xin.stxkfzx.weekend.auth.properties.JwtProperties;
import xin.stxkfzx.weekend.auth.utils.CodecUtils;
import xin.stxkfzx.weekend.auth.utils.JwtUtils;
import xin.stxkfzx.weekend.common.enums.ExceptionEnum;
import xin.stxkfzx.weekend.common.exception.WeekendException;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 授权中心的一些方法
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
@Service
public class AuthService {
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final UserBaseMapper userBaseMapper;
    private final JwtProperties jwtProperties;
    private final RedisTemplate redisTemplate;

    @Autowired
    public AuthService(JwtProperties jwtProperties, UserBaseMapper userBaseMapper, RedisTemplate redisTemplate) {
        this.jwtProperties = jwtProperties;
        this.redisTemplate = redisTemplate;
        this.userBaseMapper = userBaseMapper;
    }

    @SuppressWarnings("unchecked")
    public void login(String username, String password, HttpServletResponse response) {
        try {
            UserBase user = userBaseMapper.selectByNickName(username);
            // 拿到用户密码利用盐值加密，并与数据库保存的加密密码进行对比
            String md5Password = CodecUtils.md5Hex(password, CodecUtils.generateSalt(user.getCreateTime()));
            if (!user.getPassword().equals(md5Password)) {
                throw new WeekendException(ExceptionEnum.INVALID_USER);
            }

            UserBase userBase = new UserBase(user.getId(), user.getNickName(), user.getStatus());
            // 生成Token
            String token = JwtUtils.generateToken(userBase, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            if (StringUtils.isBlank(token)) {
                // TODO: 2019/4/13 抛出异常
                logger.error("【授权中心】token为空");
            }
            logger.info("【授权中心】生成token为：{}", token);
            response.setHeader("Authorization", token);
            redisTemplate.opsForValue().set(token, userBase, 30, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("【授权中心】用户名或密码错误，用户名：{}", username, e);
            // TODO: 2019/4/14 异常封装
        }
    }

    @SuppressWarnings("unchecked")
    public ResponseEntity<UserBase> verifyUser(String token, HttpServletResponse response) {
        // 先从redis中获取用户信息
        UserBase userBase = (UserBase) redisTemplate.opsForValue().get(token);
        // 如果缓存中没有，对token进行解析
        if (userBase == null) {
            synchronized (AuthService.class) {
                // 从Token中获取用户信息
                userBase = JwtUtils.getUserBase(jwtProperties.getPublicKey(), token);
                logger.warn("【授权中心】用户{}未从redis中获取信息", userBase);
                // 成功，刷新Token
                String newToken = JwtUtils.generateToken(userBase, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
                // 将新的Token信息设置在Header的Authorization里,并保存在Redis中
                response.setHeader("Authorization", newToken);
                redisTemplate.opsForValue().set(token, userBase, 30, TimeUnit.MINUTES);
            }
        }
        return ResponseEntity.ok(userBase);
    }

    /**
     * 通过id查找
     *
     * @param userId id
     * @return UserBase
     * @author ViterTian
     * @date 2019-04-14
     */
    public UserBase findUserById(String userId) {
        return userBaseMapper.selectByPrimaryKey(Integer.valueOf(userId));
    }
}
