package xin.stxkfzx.weekend.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import xin.stxkfzx.weekend.config.JwtProperties;
import xin.stxkfzx.weekend.convert.ChatConvert;
import xin.stxkfzx.weekend.entity.ChatMessage;
import xin.stxkfzx.weekend.entity.ResultBean;
import xin.stxkfzx.weekend.entity.UserBase;
import xin.stxkfzx.weekend.entity.UserJoinChatRoom;
import xin.stxkfzx.weekend.enums.ExceptionEnum;
import xin.stxkfzx.weekend.enums.StatusEnum;
import xin.stxkfzx.weekend.mapper.ChatMessageMapper;
import xin.stxkfzx.weekend.mapper.UserJoinChatRoomMapper;
import xin.stxkfzx.weekend.util.JwtUtils;
import xin.stxkfzx.weekend.vo.SocketMessageVO;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * 聊天室处理
 *
 * @author fmy
 * @date 2019-04-18 16:27
 */
@Component
public class SocketHandler extends AbstractWebSocketHandler {
    private static final Logger log = LogManager.getLogger(SocketHandler.class);
    private final ChatConvert chatConvert;
    private final ChatMessageMapper chatMessageMapper;
    private final UserJoinChatRoomMapper joinChatRoomMapper;
    private final JwtProperties jwtProperties;
    private final static Map<Integer, CopyOnWriteArraySet<WebSocketSession>> SESSION_MAP = new ConcurrentHashMap<>(36);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public SocketHandler(ChatConvert chatConvert, ChatMessageMapper chatMessageMapper, UserJoinChatRoomMapper joinChatRoomMapper, JwtProperties jwtProperties) {
        this.chatConvert = chatConvert;
        this.chatMessageMapper = chatMessageMapper;
        this.joinChatRoomMapper = joinChatRoomMapper;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        log.info("建立socket连接");
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        log.info("interceptor 开始处理数据");

        if (webSocketMessage instanceof TextMessage) {
            handleTextMessage(webSocketSession, (TextMessage) webSocketMessage);
        } else if (webSocketMessage instanceof BinaryMessage) {
            handleBinaryMessage(webSocketSession, (BinaryMessage) webSocketMessage);
        } else {
            throw new IllegalArgumentException(webSocketMessage + " type is unknown");
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        log.error("interceptor 连接错误, 关闭Session会话");

        removeSession(webSocketSession);
    }

    private void removeSession(WebSocketSession webSocketSession) throws IOException {
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }

        Map<String, Object> attributes = webSocketSession.getAttributes();
        Integer chatId = (Integer) attributes.get("chatId");
        Integer userId = (Integer) attributes.get("userId");

        CopyOnWriteArraySet<WebSocketSession> webSocketSessions = SESSION_MAP.get(chatId);
        Optional.ofNullable(webSocketSessions).ifPresent(set -> {
            set.removeIf(session -> userId.equals(session.getAttributes().get("userId")));
            log.debug("从聊天室{}中删除用户{}， 当前聊天室在线人数：{}", chatId, userId, set.size());
        });
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        log.info("socket连接 结束");
        if (!CloseStatus.NORMAL.equals(closeStatus)) {
            log.warn("socket异常关闭: {}", closeStatus.getReason());
            sendMessage2Personal(webSocketSession, closeStatus.getReason());
        }
        removeSession(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("interceptor 开始处理文本数据");
        // 获取消息体
        SocketMessageVO param = mapper.readValue(message.getPayload(), SocketMessageVO.class);

        if (auth(session, param)) {
            return;
        }

        // 构建消息
        ChatMessage chatMessage = getChatMessage(session, param);
        // 全聊天室推送消息
        sendMessage2AllNumbers(chatMessage);

        // 存入数据库中
        chatMessageMapper.insert(chatMessage);
    }

    private void sendMessage2AllNumbers(ChatMessage chatMessage) throws Exception {
        SocketMessageVO vo = chatConvert.fromChatMessage(chatMessage);

        Integer id = chatMessage.getUserId();
        Integer chatRoomId = chatMessage.getChatRoomId();

        CopyOnWriteArraySet<WebSocketSession> webSocketSessions = SESSION_MAP.get(chatRoomId);
        for (WebSocketSession item :
                webSocketSessions) {
            Object userId = item.getAttributes().get("userId");
            vo.setMyMessage(id.equals(userId));
            // 发送给每一个人
            sendMessage2Personal(item, vo);
            log.debug("给用户:{} 发送消息：[{}], 发送者:{}", userId, vo.getContent(), id);
        }
    }

    private void sendMessage2Personal(WebSocketSession session, Object message) throws Exception {
        session.sendMessage(new TextMessage(mapper.writeValueAsString(new ResultBean<>(StatusEnum.SUCCESS, message))));
    }

    private void sendMessage2Personal(WebSocketSession session, String errMsg) throws Exception {
        session.sendMessage(new TextMessage(mapper.writeValueAsString(new ResultBean<>(ExceptionEnum.WEB_SOCKET_FAIL.getCode(), errMsg))));
    }

    private ChatMessage getChatMessage(WebSocketSession session, SocketMessageVO param) {
        ChatMessage chatMessage = chatConvert.toSocketMessageVO(param);
        chatMessage.setUpdateTime(new Date());
        chatMessage.setCreateTime(new Date());
        Map<String, Object> attributes = session.getAttributes();
        chatMessage.setUserId((Integer) attributes.get("userId"));
        return chatMessage;

    }

    private boolean auth(WebSocketSession session, SocketMessageVO param) throws Exception {
        if (!StatusEnum.CHAT_AUTH.getCode().equals(param.getType())) {
            return false;
        }
        log.debug("验证用户身份");

        // 从Token中获取用户Id
        UserBase userBase;
        try {
            userBase = JwtUtils.getUserBase(jwtProperties.getPublicKey(), param.getContent());
        } catch (Exception e) {
            log.warn("验证用户信息错误，token:[{}], 错误信息：[{}]", param.getContent(), e.getLocalizedMessage());
            afterConnectionClosed(session, CloseStatus.BAD_DATA.withReason("验证用户信息错误"));
            return true;
        }

        // 判断用户是否在当前聊天室中
        List<UserJoinChatRoom> recordList = joinChatRoomMapper.selectByUserIdAndRoomIdAndStatus(userBase.getId(), param.getChatRoomId(), StatusEnum.RECORD_JOIN.getCode().shortValue());
        if (CollectionUtils.isEmpty(recordList)) {
            log.warn("用户:[{}]没有权限加入聊天室:[{}]中", userBase.getId(), param.getChatRoomId());
            afterConnectionClosed(session, CloseStatus.BAD_DATA.withReason("没有权限加入聊天室中"));
            return true;
        }

        Map<String, Object> attributes = session.getAttributes();
        attributes.put("userId", userBase.getId());

        putSession2List(session);

        return true;
    }

    @SuppressWarnings("all")
    private void putSession2List(WebSocketSession session) throws Exception {
        Map<String, Object> attributes = session.getAttributes();
        Integer chatId = (Integer) attributes.get("chatId");
        Integer userId = (Integer) attributes.get("userId");

        CopyOnWriteArraySet<WebSocketSession> webSocketSessions = SESSION_MAP.get(chatId);
        if (webSocketSessions == null) {
            synchronized (this) {
                if (webSocketSessions == null) {
                    webSocketSessions = new CopyOnWriteArraySet<>();
                    SESSION_MAP.put(chatId, webSocketSessions);
                }
            }
        }

        // 检测用户是否已经在聊天室中
        boolean exist = webSocketSessions.parallelStream().anyMatch(item -> userId.equals(item.getAttributes().get("userId")));
        if (exist) {
            log.debug("用户：{} 已经在聊天室：{}中", userId, chatId);
            return;
        }

        webSocketSessions.add(session);
        log.debug("聊天室：{} 中添加在线用户：{}", chatId, attributes.get("userId"));
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        // TODO: 2019/5/3 处理二进制消息
        throw new Exception("暂不支持二进制消息处理");
    }
}
