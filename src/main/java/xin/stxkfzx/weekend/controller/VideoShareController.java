package xin.stxkfzx.weekend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xin.stxkfzx.weekend.annotation.PassToken;
import xin.stxkfzx.weekend.entity.PageResult;
import xin.stxkfzx.weekend.entity.ResultBean;
import xin.stxkfzx.weekend.entity.VideoShare;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.service.VideoShareService;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/29
 */
@RestController
@RequestMapping("/online/share/video")
public class VideoShareController {
    private final VideoShareService videoShareService;

    public VideoShareController(VideoShareService videoShareService) {
        this.videoShareService = videoShareService;
    }

    /**
     * 新增分享视频
     *
     * @param videoShare 分享的视频信息
     * @return ResponseEntity<ResultBean < VideoShare>>
     * @author ViterTian
     * @date 2019-04-30
     */
    @PostMapping("/insert")
    public ResponseEntity<ResultBean<VideoShare>> insertShareVideo(@RequestBody VideoShare videoShare) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultBean<>(StatusEnum.SUCCESS,
                videoShareService.insertVideoShare(videoShare)));
    }

    /**
     * 分页查询视频分享
     *
     * @param page 查询页数
     * @param rows 每页显示条数
     * @return ResponseEntity<ResultBean < PageResult < VideoShare>>>
     * @author ViterTian
     * @date 2019-04-30
     */
    @PassToken
    @GetMapping("/query")
    public ResponseEntity<ResultBean<PageResult<VideoShare>>> queryVideoShare(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                              @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        return ResponseEntity.ok(new ResultBean<>(StatusEnum.SUCCESS, videoShareService.queryVideoShare(page, rows)));
    }

    /**
     * 根据id查询视频分享
     *
     * @param id 视频分享id
     * @return 视频分享对象
     * @author ViterTian
     * @date 2019-05-01
     */
    @PassToken
    @GetMapping("{id}")
    public ResponseEntity<ResultBean<VideoShare>> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(new ResultBean<>(StatusEnum.SUCCESS, videoShareService.queryById(id)));
    }

    /**
     * 根据用户id查询当前用户发布的视频分享
     *
     * @param userId 用户id
     * @param page 查询页数
     * @param rows 每页显示条数
     * @return 此用户发布过的视频分享分页结果
     * @author ViterTian
     * @date 2019-05-01
     */
    @GetMapping("/myvideo")
    public ResponseEntity<ResultBean<PageResult<VideoShare>>> queryByUserId(@RequestParam("id") Integer userId,@RequestParam(value = "page",defaultValue = "1") Integer page,@RequestParam(value = "rows",defaultValue = "10")Integer rows) {
        return ResponseEntity.ok(new ResultBean<>(StatusEnum.SUCCESS, videoShareService.queryByUserId(userId,page,rows)));
    }

    @GetMapping("/like/video")
    public ResponseEntity<ResultBean<Boolean>> likeVideo(@RequestParam("id") Integer id){
        return ResponseEntity.ok(new ResultBean<>(StatusEnum.SUCCESS,videoShareService.likeVideo(id)));
    }
}
