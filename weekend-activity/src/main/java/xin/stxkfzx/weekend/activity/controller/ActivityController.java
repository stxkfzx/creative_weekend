package xin.stxkfzx.weekend.activity.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xin.stxkfzx.weekend.activity.entity.Activity;
import xin.stxkfzx.weekend.activity.expand.ActivityExpand;
import xin.stxkfzx.weekend.activity.service.ActivityService;
import xin.stxkfzx.weekend.activity.vo.ActivityVO;
import xin.stxkfzx.weekend.common.enums.StatusEnum;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * 下线活动接口
 *
 * @author fmy
 * @date 2019-04-18 11:30
 */
@Validated
@RestController
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * 获取指定活动
     *
     * @param activityId 活动Id
     * @return 指定活动
     * @author fmy
     * @date 2019-04-18 11:39
     */
    @GetMapping("/{activityId}")
    public ResponseEntity<?> getActivity(@PathVariable @Min(1) Integer activityId) {
        return ResponseEntity.ok(activityService.getActivity(activityId, StatusEnum.NORMAL).getActivity());
    }

    /**
     * 添加活动
     *
     * @param vo 创建参数
     * @return 创建的活动
     * @author fmy
     * @date 2019-04-18 15:40
     */
    @PostMapping
    public ResponseEntity<?> addActivity(@RequestBody @Valid ActivityVO vo) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(vo, activity);
        ActivityExpand expand = activityService.createActivity(activity);

        return ResponseEntity.status(HttpStatus.CREATED).body(expand.getActivity());
    }
}
