package web_socket.demo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(EchoHandler.class);

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("EchoHandler received from session {}: {}", session.getId(), message.getPayload());
        session.sendMessage(new TextMessage("echo: " + message.getPayload()));
        logger.info("EchoHandler sent to session {}: echo: {}", session.getId(), message.getPayload());
    }
}

