package web_socket.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SharedMessageBroker {
    private final Set<WebSocketSession> echoSessions = ConcurrentHashMap.newKeySet();
    private final Set<WebSocketSession> chatSessions = ConcurrentHashMap.newKeySet();

    public void registerEchoSession(WebSocketSession session) { echoSessions.add(session); }
    public void unregisterEchoSession(WebSocketSession session) { echoSessions.remove(session); }

    public void registerChatSession(WebSocketSession session) { chatSessions.add(session); }
    public void unregisterChatSession(WebSocketSession session) { chatSessions.remove(session); }

    public void sendToEchoSessions(TextMessage msg) {
        echoSessions.forEach(s -> { if (s.isOpen()) try { s.sendMessage(msg); } catch (Exception e) {} });
    }

    public void sendToChatSessions(TextMessage msg) {
        chatSessions.forEach(s -> { if (s.isOpen()) try { s.sendMessage(msg); } catch (Exception e) {} });
    }
}

