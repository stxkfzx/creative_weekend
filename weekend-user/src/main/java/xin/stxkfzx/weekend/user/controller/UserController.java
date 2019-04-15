package xin.stxkfzx.weekend.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xin.stxkfzx.weekend.auth.config.UserLoginToken;
import xin.stxkfzx.weekend.common.entity.ResultBean;
import xin.stxkfzx.weekend.common.enums.ExceptionEnum;
import xin.stxkfzx.weekend.common.enums.StatusEnum;
import xin.stxkfzx.weekend.common.exception.WeekendException;
import xin.stxkfzx.weekend.user.dto.UserDTO;
import xin.stxkfzx.weekend.user.entity.User;
import xin.stxkfzx.weekend.user.service.UserService;
import xin.stxkfzx.weekend.auth.config.PassToken;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/12
 */
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户注册
     *
     * @param user User对象
     * @return ResponseEntity <ResultBean<Void>>
     * @author VicterTian
     * @date 2019-04-13
     */
    @PassToken
    @PostMapping("/register")
    public ResponseEntity<ResultBean<UserDTO>> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error(bindingResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("|")));
            throw new WeekendException(ExceptionEnum.USER_SAVE_ERROR);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultBean<>(StatusEnum.SUCCESS, userService.addUser(user)));
    }
}
