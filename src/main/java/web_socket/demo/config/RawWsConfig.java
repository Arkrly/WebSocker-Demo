package web_socket.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import web_socket.demo.handler.ChatHandler;
import web_socket.demo.handler.EchoHandler;

@Configuration
@EnableWebSocket
public class RawWsConfig implements WebSocketConfigurer {

    @Bean
    public EchoHandler echoHandler() { return new EchoHandler(); }

    @Bean
    public ChatHandler chatHandler() { return new ChatHandler(); }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoHandler(), "/ws/echo").setAllowedOrigins("*");
        registry.addHandler(chatHandler(), "/ws/chat").setAllowedOrigins("*");
    }
}

