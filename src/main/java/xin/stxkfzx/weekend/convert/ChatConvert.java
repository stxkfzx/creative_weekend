package xin.stxkfzx.weekend.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import xin.stxkfzx.weekend.entity.ChatMessage;
import xin.stxkfzx.weekend.vo.chat.SocketMessageVO;

/**
 * @author fmy
 * @date 2019-05-03 17:38
 */
@Mapper(componentModel = "spring")
public interface ChatConvert {

    @Mapping(target = "myMessage", ignore = true)
    SocketMessageVO fromChatMessage(ChatMessage message);

    ChatMessage toSocketMessageVO(SocketMessageVO vo);
}
