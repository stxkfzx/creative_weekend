package xin.stxkfzx.weekend.activity.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import xin.stxkfzx.weekend.activity.entity.Activity;
import xin.stxkfzx.weekend.activity.expand.ActivityExpand;
import xin.stxkfzx.weekend.activity.expand.ChatExpand;
import xin.stxkfzx.weekend.activity.service.ChatService;

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
     * 创建活动切点
     *
     * @author fmy
     * @date 2019-04-24 17:24
     */
    @Pointcut("@annotation(xin.stxkfzx.weekend.activity.annotation.LinkStatus)")
    public void createActivity() {
    }

    @Autowired
    public JoinActivityAOP(ChatService chatService) {
        this.chatService = chatService;
    }

    @AfterReturning(pointcut = "createActivity()",
            returning = "activityEx")
    public void joinCreateActivity(ActivityExpand activityEx) {
        Activity activity = activityEx.getActivity();
        ChatExpand expand = chatService.createChatRoom(activity);
        log.debug("创建活动关联创建聊天室：{}", expand.getChatRoom() != null);
    }
}
