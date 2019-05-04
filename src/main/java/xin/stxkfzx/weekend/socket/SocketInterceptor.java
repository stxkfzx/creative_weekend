package xin.stxkfzx.weekend.socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import xin.stxkfzx.weekend.entity.ChatRoom;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.mapper.ChatRoomMapper;

import java.util.Map;

/**
 * 聊天拦截器
 *
 * @author fmy
 * @date 2018-09-09 15:04
 */
@Component
public class SocketInterceptor implements HandshakeInterceptor {
    private static final Logger log = LogManager.getLogger(SocketInterceptor.class);
    private final ChatRoomMapper chatRoomMapper;

    public SocketInterceptor(ChatRoomMapper chatRoomMapper) {
        this.chatRoomMapper = chatRoomMapper;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) {
        log.info("进入webSocket 拦截器");

        // 获取聊天室Id
        String[] split = serverHttpRequest.getURI().getPath().split("/", 4);
        String chatId = split[split.length - 1];
        log.info("进入聊天室id: {}", chatId);

        // 检测聊天室是否存在
        try {
            ChatRoom chatRoom = chatRoomMapper.selectByPrimaryKey(Integer.valueOf(chatId));
            if (chatRoom == null || chatRoom.getStatus().equals(StatusEnum.DELETE.getCode().shortValue())) {
                return false;
            }
        } catch (NumberFormatException e) {
            serverHttpResponse.setStatusCode(HttpStatus.BAD_REQUEST);
            return false;
        }

        map.put("chatId", Integer.valueOf(chatId));

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        log.info("afterHandshake");

    }


}
