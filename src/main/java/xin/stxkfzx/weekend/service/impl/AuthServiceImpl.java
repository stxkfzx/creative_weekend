package xin.stxkfzx.weekend.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xin.stxkfzx.weekend.config.JwtProperties;
import xin.stxkfzx.weekend.entity.UserBase;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.exception.CheckException;
import xin.stxkfzx.weekend.mapper.UserBaseMapper;
import xin.stxkfzx.weekend.service.AuthService;
import xin.stxkfzx.weekend.util.CodecUtils;
import xin.stxkfzx.weekend.util.JwtUtils;

/**
 * 授权中心的一些方法
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserBaseMapper userBaseMapper;
    private final JwtProperties jwtProperties;
    private final RedisTemplate redisTemplate;

    @Autowired
    public AuthServiceImpl(JwtProperties jwtProperties, UserBaseMapper userBaseMapper, RedisTemplate redisTemplate) {
        this.jwtProperties = jwtProperties;
        this.redisTemplate = redisTemplate;
        this.userBaseMapper = userBaseMapper;
    }

    @Override
    public UserBase login(String username, String password) {

        UserBase user = userBaseMapper.selectByNickName(username);
        // 拿到用户密码利用盐值加密，并与数据库保存的加密密码进行对比
        String md5Password = CodecUtils.md5Hex(password, CodecUtils.generateSalt(user.getNickName()));
        if (!user.getPassword().equals(md5Password)) {
            logger.error("【授权中心】用户名或密码错误，用户名：{}", username);
            throw new CheckException(ExceptionEnum.INVALID_USER);
        }
        return new UserBase(user.getId(), user.getNickName(), user.getStatus());
    }

    @Override
    public UserBase verifyUser(String token) {
        // 先从redis中获取用户信息
        UserBase userBase = (UserBase) redisTemplate.opsForValue().get(token);
        // 如果缓存中没有，对token进行解析
        if (userBase == null) {
            synchronized (AuthServiceImpl.class) {
                // 从Token中获取用户信息
                userBase = JwtUtils.getUserBase(jwtProperties.getPublicKey(), token);
                logger.warn("【授权中心】用户{}未从redis中获取信息", userBase);
            }
        }
        return userBase;
    }

    @Override
    public UserBase findUserById(Integer userId) {
        return userBaseMapper.selectByPrimaryKey(userId);
    }
}
