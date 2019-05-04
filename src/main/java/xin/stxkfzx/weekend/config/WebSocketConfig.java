package xin.stxkfzx.weekend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import xin.stxkfzx.weekend.handle.SocketHandler;
import xin.stxkfzx.weekend.interceptor.SocketInterceptor;

/**
 * web socket配置
 *
 * @author fmy
 * @date 2018-09-09 15:21
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final SocketInterceptor postSocketInterceptor;
    private final SocketHandler handler;

    @Autowired
    public WebSocketConfig(SocketInterceptor postSocketInterceptor, SocketHandler handler) {
        this.postSocketInterceptor = postSocketInterceptor;
        this.handler = handler;
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(handler, "/chatroom/{roomId}")
                .setAllowedOrigins("*")
                .addInterceptors(postSocketInterceptor);
    }
}
