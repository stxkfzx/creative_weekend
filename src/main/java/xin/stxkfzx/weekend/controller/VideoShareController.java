package xin.stxkfzx.weekend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
     * @param videoShare 分享的视频信息
     * @return ResponseEntity<ResultBean<VideoShare>>
     * @author ViterTian
     * @date 2019-04-30
     */
    @PostMapping("/insert")
    public ResponseEntity<ResultBean<VideoShare>> insertShareVideo(@RequestBody VideoShare videoShare) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultBean<>(StatusEnum.SUCCESS,
                videoShareService.insertVideoShare(videoShare)));
    }
}
