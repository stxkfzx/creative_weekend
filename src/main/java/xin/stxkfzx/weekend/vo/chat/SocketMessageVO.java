package xin.stxkfzx.weekend.vo.chat;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 封装聊天信息
 *
 * @author fmy
 * @date 2019-05-03 17:15
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SocketMessageVO {
    @JsonAlias("message")
    private String content;
    private Integer type;
    private Boolean myMessage;
    private Integer chatRoomId;

    public Integer getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Integer chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getMyMessage() {
        return myMessage;
    }

    public void setMyMessage(Boolean myMessage) {
        this.myMessage = myMessage;
    }
}
