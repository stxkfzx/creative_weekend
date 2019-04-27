package xin.stxkfzx.weekend.activity.socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import xin.stxkfzx.weekend.common.util.UserUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 聊天拦截器
 *
 * @author fmy
 * @date 2018-09-09 15:04
 */
@Component
public class SocketInterceptor extends HttpSessionHandshakeInterceptor {
    private static final Logger log = LogManager.getLogger(SocketInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {

        log.info("进入webSocket 拦截器");
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
            String postId = request.getURI().toString().split("postId=")[1];
            log.debug("进入论坛房间Id: {}", postId);

            HttpSession session = request.getServletRequest().getSession(false);
            if (session == null) {
                return false;
            }
            Long userId = (Long)session.getAttribute(UserUtils.KEY_USER);
            // UserUtils.setUserId(userId);

            // map 是ConcurrentMap类,key-value 都不能为空
            map.put("currentUserId", userId);
            map.put("postId", postId);
        }

        return true;
    }



}
