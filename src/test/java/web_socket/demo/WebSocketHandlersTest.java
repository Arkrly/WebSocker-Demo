package web_socket.demo;

import web_socket.demo.handler.EchoHandler;
import web_socket.demo.handler.ChatHandler;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketHandlersTest {

    private EchoHandler echoHandler;
    private ChatHandler chatHandler;

    private WebSocketSession session1;
    private WebSocketSession session2;

    @BeforeEach
    public void setup() {
        echoHandler = new EchoHandler();

        // For ChatHandler, instantiate with a threadsafe set for sessions
        chatHandler = new ChatHandler();

        session1 = mock(WebSocketSession.class);
        session2 = mock(WebSocketSession.class);
        when(session1.getId()).thenReturn("s1");
        when(session2.getId()).thenReturn("s2");
        when(session1.isOpen()).thenReturn(true);
        when(session2.isOpen()).thenReturn(true);
    }

    @Test
    public void testEchoHandlerEchoesMessage() throws Exception {
        // Test echo handler echoes back the same message with prefix

        // Mock sendMessage to capture output message
        ArgumentCaptor<TextMessage> messageCaptor = ArgumentCaptor.forClass(TextMessage.class);
        doNothing().when(session1).sendMessage(messageCaptor.capture());

        echoHandler.handleTextMessage(session1, new TextMessage("test-echo"));

        TextMessage sent = messageCaptor.getValue();
        assertEquals("echo: test-echo", sent.getPayload());
    }

    @Test
    public void testChatHandlerBroadcastsToMultipleSessions() throws Exception {
        // Add mock sessions to ChatHandler's session set (simulate connections)
        chatHandler.afterConnectionEstablished(session1);
        chatHandler.afterConnectionEstablished(session2);

        ArgumentCaptor<TextMessage> messageCaptor1 = ArgumentCaptor.forClass(TextMessage.class);
        ArgumentCaptor<TextMessage> messageCaptor2 = ArgumentCaptor.forClass(TextMessage.class);

        doNothing().when(session1).sendMessage(messageCaptor1.capture());
        doNothing().when(session2).sendMessage(messageCaptor2.capture());

        chatHandler.handleTextMessage(session1, new TextMessage("hello chat"));

        // Validate both sessions got the message broadcast with session1 ID prefix
        TextMessage sentTo1 = messageCaptor1.getValue();
        TextMessage sentTo2 = messageCaptor2.getValue();

        String expectedPayload = "user-s1: hello chat";

        assertEquals(expectedPayload, sentTo1.getPayload());
        assertEquals(expectedPayload, sentTo2.getPayload());

        // Cleanup
        chatHandler.afterConnectionClosed(session1, null);
        chatHandler.afterConnectionClosed(session2, null);
    }
}
