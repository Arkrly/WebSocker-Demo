// src/main/java/web_socket/demo/handler/ChatHandler.java
package web_socket.demo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatHandler extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(ChatHandler.class);
    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        logger.info("ChatHandler: session {} connected", session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        logger.info("ChatHandler: session {} disconnected", session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("ChatHandler received from session {}: {}", session.getId(), message.getPayload());
        String broadcast = STR."user-\{session.getId()}: \{message.getPayload()}";
        for (WebSocketSession s : sessions) {
            if (s.isOpen()) {
                s.sendMessage(new TextMessage(broadcast));
                logger.info("ChatHandler sent to session {}: {}", s.getId(), broadcast);
            }
        }
    }
}

