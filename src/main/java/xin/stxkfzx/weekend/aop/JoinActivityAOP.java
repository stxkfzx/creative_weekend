package xin.stxkfzx.weekend.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xin.stxkfzx.weekend.annotation.LinkStatus;
import xin.stxkfzx.weekend.entity.Activity;
import xin.stxkfzx.weekend.enums.LinkTypeEnum;
import xin.stxkfzx.weekend.expand.ActivityExpand;
import xin.stxkfzx.weekend.service.ChatService;

/**
 * 关联线下活动
 *
 * @author fmy
 * @date 2019-04-24 17:45
 */
@Component
@Aspect
public class JoinActivityAOP {
    private static final Logger log = LoggerFactory.getLogger(JoinActivityAOP.class);

    private final ChatService chatService;

    /**
     * 关联操作切点
     *
     * @author fmy
     * @date 2019-04-24 17:24
     */
    @Pointcut("@annotation(xin.stxkfzx.weekend.annotation.LinkStatus)")
    public void linkStatus() {
    }

    @Pointcut("target(xin.stxkfzx.weekend.service.ActivityService)")
    public void activityOperation() {

    }

    @Autowired
    public JoinActivityAOP(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * 关联创建活动
     *
     * @param point      JoinPoint
     * @param activityEx ActivityExpand
     * @author fmy
     * @date 2019-04-27 12:59
     */
    @AfterReturning(pointcut = "linkStatus() && activityOperation()", returning = "activityEx")
    public void joinCreateActivity(JoinPoint point, ActivityExpand activityEx) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        LinkStatus status = signature.getMethod().getAnnotation(LinkStatus.class);
        if (!LinkTypeEnum.ADD.equals(status.type())) {
            return;
        }

        Activity activity = activityEx.getActivity();
        // 创建聊天室
        ActivityExpand expand = chatService.createChatRoom(activity);
        log.debug("创建活动关联创建聊天室：{}", expand.getChatRoom() != null);
        activityEx.setChatRoom(expand.getChatRoom());

        // 加入聊天室
        chatService.joinRoom(activity.getUserId(), expand.getChatRoom());
    }
}
