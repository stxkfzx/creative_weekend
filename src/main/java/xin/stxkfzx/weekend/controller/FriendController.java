package xin.stxkfzx.weekend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xin.stxkfzx.weekend.entity.ResultBean;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.service.FriendService;
import xin.stxkfzx.weekend.vo.PageVO;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/5/3
 */
@RequestMapping("/friend")
@RestController
public class FriendController {
    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    /**
     * 添加关注用户
     *
     * @param uid 当前用户id
     * @param fid 关注用户id
     * @return 是否关注成功
     * @author ViterTian
     * @date 2019-05-03
     */
    @PostMapping("attention")
    public ResponseEntity<ResultBean<Boolean>> addAttentionById(@RequestParam("uid") Integer uid, @RequestParam("fid") Integer fid) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultBean<>(StatusEnum.SUCCESS, friendService.addAttentionById(uid, fid)));
    }

    /**
     * 查询我的fans
     *
     * @param uid  用户id
     * @param page 查询页数
     * @param rows 每页显示条数
     * @return 我的粉丝列表
     * @author ViterTian
     * @date 2019-05-05
     */
    @GetMapping("/fans")
    public ResponseEntity<ResultBean<PageVO>> queryMyFans(@RequestParam("uid") Integer uid, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResultBean<>(StatusEnum.SUCCESS, friendService.queryMyFans(uid, page, rows)));
    }

    /**
     * 查询我的关注
     *
     * @param uid  用户id
     * @param page 查询页数
     * @param rows 每页显示条数
     * @return 我的关注列表
     * @author ViterTian
     * @date 2019-05-05
     */
    @GetMapping("/my/attention")
    public ResponseEntity<ResultBean<PageVO>> queryMyAttention(@RequestParam("uid") Integer uid, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResultBean<>(StatusEnum.SUCCESS, friendService.queryMyAttention(uid, page, rows)));
    }
}
