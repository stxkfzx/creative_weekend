package xin.stxkfzx.weekend.user.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xin.stxkfzx.weekend.auth.utils.CodecUtils;
import xin.stxkfzx.weekend.common.enums.ExceptionEnum;
import xin.stxkfzx.weekend.common.exception.WeekendException;
import xin.stxkfzx.weekend.sms.config.SmsProperties;
import xin.stxkfzx.weekend.user.vo.UserVO;
import xin.stxkfzx.weekend.user.entity.User;
import xin.stxkfzx.weekend.user.mapper.UserMapper;
import xin.stxkfzx.weekend.user.service.UserService;

import java.util.Date;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/12
 */
@Service
public class UserServiceImpl implements UserService {
    private final RedisTemplate<String,String> redisTemplate;
    private final UserMapper userMapper;
    private final SmsProperties smsProperties;

    public UserServiceImpl(UserMapper userMapper, SmsProperties smsProperties, RedisTemplate<String,String> redisTemplate) {
        this.userMapper = userMapper;
        this.smsProperties = smsProperties;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public UserVO addUser(User user) {
        String key = smsProperties.getKeyPerfix() + user.getPhoneNum();
        // 从redis中取出验证码
        String cacheCode = redisTemplate.opsForValue().get(key);
        if (cacheCode == null) {
            throw new WeekendException(ExceptionEnum.INVALID_VERIFY_CODE);
        }
        // 防止用户自定义id
        user.setTbId(null);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        // 生成一段盐值
        String salt = CodecUtils.generateSalt(user.getCreateTime());

        // 对密码进行加密
        user.setPassword(CodecUtils.md5Hex(user.getPassword(),salt));

        int i = userMapper.insertSelective(user);
        if (i != 1) {
            throw new WeekendException(ExceptionEnum.USER_SAVE_ERROR);
        }
        redisTemplate.delete(key);
        UserVO userVO = new UserVO();

        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
}
