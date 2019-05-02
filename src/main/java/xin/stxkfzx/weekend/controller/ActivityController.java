package xin.stxkfzx.weekend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xin.stxkfzx.weekend.convert.ActivityConvert;
import xin.stxkfzx.weekend.convert.PageConvert;
import xin.stxkfzx.weekend.entity.Activity;
import xin.stxkfzx.weekend.entity.ActivityDetail;
import xin.stxkfzx.weekend.entity.User;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.exception.CheckException;
import xin.stxkfzx.weekend.expand.ActivityExpand;
import xin.stxkfzx.weekend.service.ActivityService;
import xin.stxkfzx.weekend.util.UserUtils;
import xin.stxkfzx.weekend.vo.PageVO;
import xin.stxkfzx.weekend.vo.activity.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.util.List;

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
    private final static ObjectMapper mapper = new ObjectMapper();
    private final ActivityConvert activityConvert;
    private final PageConvert pageConvert;
    private static final Logger log = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    public ActivityController(ActivityService activityService, ActivityConvert activityConvert, PageConvert pageConvert) {
        this.activityService = activityService;
        this.activityConvert = activityConvert;
        this.pageConvert = pageConvert;
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
        ActivityExpand expand = activityService.getActivity(activityId, StatusEnum.NORMAL);
        ActivityDetailVO out = activityConvert.fromActivityAndChatRoom(expand.getActivity(), expand.getChatRoom());
        return ResponseEntity.ok(out);
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
    public ResponseEntity<?> createActivity(@RequestBody @Valid ActivityParam vo) {
        Activity activity = activityConvert.toActivityParam(vo);

        ActivityExpand expand = activityService.createActivity(activity, vo.getImageList());

        ActivityDetailVO out = activityConvert.fromActivityAndChatRoom(expand.getActivity(), expand.getChatRoom());

        return ResponseEntity.status(HttpStatus.CREATED).body(out);
    }

    /**
     * 获取活动列表
     *
     * @param condition 条件
     * @param page      分页位置
     * @param pageSize  分页大小
     * @return 活动列表
     * @author fmy
     * @date 2019-04-30 22:32
     */
    @GetMapping
    public ResponseEntity<?> pageActivity(@RequestParam(name = "condition", required = false) String condition,
                                          @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "10") Integer pageSize) {
        Activity activityCondition = convertCondition(condition);

        ActivityExpand expand = activityService.listActivityWithPage(activityCondition, page, pageSize);

        PageInfo<ActivityDetail> detailPageInfo = expand.getPage();
        List<ActivityDetail> list = detailPageInfo.getList();
        List<ActivityVO> voList = activityConvert.fromActivityDetailList(list);
        PageVO vo = pageConvert.fromPageInfo(detailPageInfo, voList);

        return ResponseEntity.ok(vo);
    }

    private Activity convertCondition(String condition) {
        if (condition != null && !"".equals(condition.trim())) {
            try {
                ActivityConditionParam vo = mapper.readValue(condition, ActivityConditionParam.class);
                log.info("查询活动列表条件： {}", vo);
                return activityConvert.toActivityConditionParam(vo);
            } catch (IOException e) {
                throw new CheckException(ExceptionEnum.CHECK_FAIL);
            }
        }
        return null;
    }

    /**
     * 加入活动
     *
     * @param activityId 活动Id
     * @return 创建的活动记录
     * @author fmy
     * @date 2019-04-18 17:27
     */
    @PostMapping("/{activityId}/join")
    public ResponseEntity<?> join(@PathVariable @Min(1) Integer activityId) {
        ActivityExpand expand = activityService.joinActivity(getUserByParameter(),
                getActivityByParameter(activityId));
        return ResponseEntity.ok(activityConvert.fromJoinActivityRecord(expand.getJoinRecord()));
    }

    /**
     * 退出活动
     *
     * @param activityId 活动Id
     * @return 退出状态 true or false
     * @author fmy
     * @date 2019-04-18 17:27
     */
    @PostMapping("/{activityId}/exit")
    public ResponseEntity<?> exit(@PathVariable @Min(1) Integer activityId) {
        ActivityExpand expand = activityService.exitActivity(getUserByParameter(),
                getActivityByParameter(activityId));
        return ResponseEntity.ok(expand.getSuccess());
    }

    /**
     * 删除活动
     *
     * @param activityId 活动Id
     * @return 删除状态 true or false
     * @author fmy
     * @date 2019-04-18 20:23
     */
    @DeleteMapping("/{activityId}")
    public ResponseEntity<?> delete(@PathVariable @Min(1) Integer activityId) {
        ActivityExpand expand = activityService.deleteActivity(getUserByParameter(),
                getActivityByParameter(activityId));
        return ResponseEntity.ok(expand.getSuccess());
    }

    private Activity getActivityByParameter(Integer activityId) {
        Activity activity = new Activity();
        activity.setTbId(activityId);
        return activity;
    }

    private User getUserByParameter() {
        User user = new User();
        user.setTbId(UserUtils.getUserId());
        return user;
    }
}
