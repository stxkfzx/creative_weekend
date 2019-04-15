package xin.stxkfzx.weekend.user.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xin.stxkfzx.weekend.common.enums.ExceptionEnum;
import xin.stxkfzx.weekend.common.exception.WeekendException;
import xin.stxkfzx.weekend.user.dto.UserDTO;
import xin.stxkfzx.weekend.user.entity.User;
import xin.stxkfzx.weekend.user.mapper.UserMapper;
import xin.stxkfzx.weekend.user.service.UserService;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/12
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO addUser(User user) {
        int i = userMapper.insertSelective(user);
        if (i != 1){
            throw new WeekendException(ExceptionEnum.USER_SAVE_ERROR);
        }
        UserDTO userDTO = new UserDTO();

        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }
}
