package xin.stxkfzx.weekend.socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

// import org.apache.commons.lang3.StringUtils;

/**
 * socket处理
 *
 * @author fmy
 * @date 2019-04-18 16:27
 */
@Component
public class SocketHandler implements WebSocketHandler {
    private static final Logger log = LogManager.getLogger(SocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {

    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
/*
    private final PostService postService;
    private static final PostSocketInfoBO socketInfo;
    private final UserService userService;
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        socketInfo = new PostSocketInfoBO();
    }

    @Autowired
    public SocketHandler(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("建立socket连接");
        Integer currentUserId = getUserId(webSocketSession);
        log.debug("currentUserId: {}", currentUserId);
        String postId = getPostId(webSocketSession);
        log.debug("postId: {}", postId);

        if (postId != null) {
            socketInfo.addUserInfo(new PostSocketUserInfo(currentUserId, webSocketSession, postId));
            log.debug("postId: {}, userId: {} 加入socket中", postId, currentUserId);
        }
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        log.info("socket 开始处理数据");
        // 读取消息
        Object payload = webSocketMessage.getPayload();
        RequestSocketMessage socketMessage = mapper.readValue(payload.toString(), RequestSocketMessage.class);
        String message = socketMessage.getMessage();
        if (StringUtils.isEmpty(message)) {
            return;
        }
        userChat(webSocketSession, message);

        log.info("socket 处理数据结束");
    }


    private void userChat(WebSocketSession webSocketSession, String message) throws IOException {
        Integer userId = getUserId(webSocketSession);
        String postId = getPostId(webSocketSession);
        JsonResponse jsonResponse = new JsonResponse();

        // 构建帖子消息,存到数据库中
        PostInformation information = new PostInformation();
        information.setUserId(userId);
        information.setInfoContent(message);
        information.setPostId(Integer.valueOf(postId));

        ResponseSocketMessage responseSocketMessage = new ResponseSocketMessage();
        try {
            log.debug("信息存到数据库中");
            PostDTO postDTO = postService.addPostInformation(information);
            responseSocketMessage.setInfoId(postDTO.getInfoId());
        } catch (PostServiceException e) {
            log.error(e.getMessage());
            jsonResponse.setMessage("系统内部错误");
            jsonResponse.setSuccess(false);
            String errJson = mapper.writeValueAsString(jsonResponse);
            webSocketSession.sendMessage(new TextMessage(errJson));
        }

        responseSocketMessage.setMessage(message);
        responseSocketMessage.setPostId(Integer.valueOf(postId));
        responseSocketMessage.setUserInfo(getUserVo(Long.valueOf(userId)));
        jsonResponse.setSuccess(true);
        jsonResponse.setMessage("成功");
        jsonResponse.setData(responseSocketMessage);

        // 给房间其他人广播消息
        String json = mapper.writeValueAsString(jsonResponse);
        log.debug("给房间中其他人发送的消息: {}", json);
        sendToPostRoom(postId, json, userId);

        //给自己广播
        responseSocketMessage.setMyMessage(true);
        json = mapper.writeValueAsString(jsonResponse);
        webSocketSession.sendMessage(new TextMessage(json));
        log.debug("给自己发送的消息: {}", json);
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        log.error("socket 连接错误, 关闭Session会话");

        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }

        socketInfo.removeUserInfo(getPostId(webSocketSession), getUserId(webSocketSession));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        log.info("socket连接 结束");

        socketInfo.removeUserInfo(getPostId(webSocketSession), getUserId(webSocketSession));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private String getPostId(WebSocketSession webSocketSession) {
        return (String) webSocketSession.getAttributes().get("postId");
    }

    private Integer getUserId(WebSocketSession webSocketSession) {
        Long currentUserId = (Long) webSocketSession.getAttributes().get("currentUserId");
        return currentUserId.intValue();
    }

    private UserVO getUserVo(Long userId) {
        UserInformation userDetail = userService.getUserDetail(userId);
        User user = userService.getUser(userId);
        UserVO userVO = new UserVO();
        userVO.setUserName(user.getUserName());
        userVO.setUserId(userId);
        userVO.setHeadPortraitAddr(userDetail.getHeadPortraitAddr());

        return userVO;
    }


    private boolean sendToPostRoom(String postId, String msg, Integer sendUserId) {
        boolean flag = true;

        if (postId == null) {
            return false;
        }

        Vector<PostSocketUserInfo> postUserList = socketInfo.getPostUserList(postId);
        TextMessage webSocketMessage = new TextMessage(msg);

        for (PostSocketUserInfo item : postUserList) {
            if (Objects.equals(item.getUserId(), sendUserId)) {
                log.debug("item.getUserId() = {}, sendUserId = {}", item.getUserId(), sendUserId);
                continue;
            }

            try {
                item.getWebSocketSession().sendMessage(webSocketMessage);
            } catch (IOException e) {
                flag = false;
                log.error("sendToPostRoom 发生错误: {}", e.getMessage());
            }
        }

        return flag;
    }*/
}
