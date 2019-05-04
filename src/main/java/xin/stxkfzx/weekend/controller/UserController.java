package xin.stxkfzx.weekend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xin.stxkfzx.weekend.annotation.PassToken;
import xin.stxkfzx.weekend.entity.ResultBean;
import xin.stxkfzx.weekend.entity.User;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.service.UserService;
import xin.stxkfzx.weekend.vo.UserVO;

import javax.validation.Valid;

/**
 * 处理用户注册、校验用户信息等
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/12
 */
@RestController
@RequestMapping("/user")
public class UserController {
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
    public ResponseEntity<ResultBean<UserVO>> register(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResultBean<>(StatusEnum.SUCCESS, userService.addUser(user)));
    }

    /**
     * 根据id查询用户信息是否完整
     *
     * @param id 要检验用户的id
     * @return 用户信息是否完善，如果不完善，请调用信息完善接口
     * @author ViterTian
     * @date 2019-04-16
     */
    @GetMapping("/whole")
    public ResponseEntity<Boolean> checkUserWhole(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(userService.checkUserWhole(id));
    }

    /**
     * 用户实名认证
     *
     * @param user 用户对象
     * @return 完善信息后用户id
     * @author ViterTian
     * @date 2019-04-16
     */
    @PostMapping("/auth")
    public ResponseEntity<Integer> authUser(/*@Valid*/ @RequestBody User user) {
        // TODO: 2019/4/16 发布时记得去掉上面注释
        return ResponseEntity.ok(userService.authUser(user));
    }

    /**
     * 检查用户名是否重复
     *
     * @param nickname 用户名
     * @return 是否可用     true - 可用     false - 不可用
     * @author ViterTian
     * @date 2019-04-16
     */
    @PassToken
    @GetMapping("/nickname")
    public ResponseEntity<Boolean> checkNickName(@RequestParam("nickname") String nickname) {
        return ResponseEntity.ok(userService.checkNickName(nickname));
    }

    /**
     * 查询用户收到点赞数
     *
     * @param uid 用户id
     * @return 用户收到点赞数
     * @author ViterTian
     * @date 2019-05-04
     */
    @GetMapping("/like")
    public ResponseEntity<ResultBean<Integer>> getMyLikedNum(@RequestParam("uid") Integer uid) {
        return ResponseEntity.ok(new ResultBean<>(StatusEnum.SUCCESS, userService.getUserLiked(uid)));
    }
}
