package xin.stxkfzx.weekend.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xin.stxkfzx.weekend.auth.config.CheckUserIsExist;
import xin.stxkfzx.weekend.auth.utils.CodecUtils;
import xin.stxkfzx.weekend.common.enums.ExceptionEnum;
import xin.stxkfzx.weekend.common.exception.CheckException;
import xin.stxkfzx.weekend.common.exception.WeekendException;
import xin.stxkfzx.weekend.sms.config.SmsProperties;
import xin.stxkfzx.weekend.user.entity.User;
import xin.stxkfzx.weekend.user.mapper.UserMapper;
import xin.stxkfzx.weekend.user.service.UserService;
import xin.stxkfzx.weekend.user.vo.UserVO;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/12
 */
@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final RedisTemplate<String, String> redisTemplate;
    private final UserMapper userMapper;
    private final SmsProperties smsProperties;

    public UserServiceImpl(UserMapper userMapper, SmsProperties smsProperties, RedisTemplate<String, String> redisTemplate) {
        this.userMapper = userMapper;
        this.smsProperties = smsProperties;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public UserVO addUser(User user) {
        logger.info("用户{}开始注册", user);
        String key = smsProperties.getKeyPerfix() + user.getPhoneNum();
        // 从redis中取出验证码
        String cacheCode = redisTemplate.opsForValue().get(key);
        // TODO: 2019/4/16 节约成本，先注释了
        /*if (cacheCode == null) {
            throw new WeekendException(ExceptionEnum.INVALID_VERIFY_CODE);
        }*/
        // 防止用户自定义id
        user.setTbId(null);
        // 防止用户名重复
        if (checkNickName(user.getNickName())) {
            throw new WeekendException(ExceptionEnum.DUPLICATE_USER_NAME);
        }

        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        // 生成一段盐值
        String salt = CodecUtils.generateSalt(user.getNickName());

        // 对密码进行加密
        user.setPassword(CodecUtils.md5Hex(user.getPassword(), salt));

        int i = userMapper.insertSelective(user);
        if (i != 1) {
            throw new WeekendException(ExceptionEnum.USER_SAVE_ERROR);
        }
        redisTemplate.delete(key);

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        logger.info("用户{}注册成功", user);
        return userVO;
    }

    @Override
    public Boolean checkUserWhole(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        // 得到对象属性数组
        Field[] fields = user.getClass().getDeclaredFields();
        boolean flag = true;
        // 遍历对象所有属性
        for (Field field : fields) {
            // 设置每个private属性的可访问性
            field.setAccessible(true);
            try {
                Object val = field.get(user);
                // 如果有一项属性为空，则需要返回false，并让用户进行实名认证
                if (val == null) {
                    logger.warn("用户{}的{}属性为空", user.getNickName(), field.toString());
                    flag = false;
                    break;
                }
            } catch (IllegalAccessException e) {
                logger.error(e.getLocalizedMessage());
                throw new CheckException(e.getMessage());
            }
        }
        return flag;
    }

    @Override
    @CheckUserIsExist
    public Integer authUser(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);
        logger.info("执行完善用户信息SQL语句受影响的条数为：{}", i);
        if (i != 1) {
            throw new WeekendException(ExceptionEnum.AUTH_USER_SAVE_ERROR);
        }
        return user.getTbId();
    }

    @Override
    public Boolean checkNickName(String nickname) {
        return userMapper.selectByNickName(nickname) != null;
    }
}
