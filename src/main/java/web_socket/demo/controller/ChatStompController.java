package web_socket.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatStompController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String send(String inbound) {
        return inbound; // broadcast to subscribers
    }
}

