<img src="https://r2cdn.perplexity.ai/pplx-full-logo-primary-dark%402x.png" style="height:64px;margin-right:32px"/>

# document the whole project and give me readme file

```markdown
# Spring Boot WebSocket Project

This project demonstrates basic WebSocket communication using **Spring Boot** with two WebSocket endpoints:

- **Echo Endpoint (`/ws/echo`)**: A simple WebSocket handler that echoes back any received text message prefixed with "echo: ".
- **Chat Endpoint (`/ws/chat`)**: A WebSocket chat handler that broadcasts messages to all connected clients with session IDs.

The project also includes unit tests to verify the behavior of both handlers.

---

## Table of Contents
- [Project Overview](#project-overview)
- [Technologies](#technologies)
- [Setup and Running](#setup-and-running)
- [WebSocket Endpoints](#websocket-endpoints)
- [Testing](#testing)
- [CORS](#cors)
- [Security](#security)
- [Logging](#logging)
- [Project Structure](#project-structure)

---

## Project Overview

This project teaches WebSocket basics in Spring Boot by providing:
- Raw WebSocket handlers that operate at the protocol frame level.
- Session management for multi-client broadcasting.
- Clear separation of concerns with distinct handlers for echo and chat.
- Simple CORS configuration for cross-origin support.
- Basic Spring Security configuration allowing unauthenticated WebSocket connections.
- Unit tests using JUnit and Mockito for WebSocket handlers.

---

## Technologies

- Java 17+
- Spring Boot 3.x
- Spring Framework WebSocket support
- Spring Security 6.x (optional for security)
- JUnit 5 (Jupiter) and Mockito for testing
- Postman (for WebSocket client testing)

---

## Setup and Running

1. Clone or copy the project.
2. Ensure you have Java 17+ and Maven/Gradle installed.
3. Build the project:
```

mvn clean install

```
4. Run the Spring Boot application:
```

mvn spring-boot:run

```
5. By default, the server listens on `http://localhost:8080`.

---

## WebSocket Endpoints

| Endpoint       | Description                      | Path         |
|----------------|---------------------------------|--------------|
| EchoHandler    | Echoes messages with “echo:” prefix | `/ws/echo`   |
| ChatHandler    | Broadcasts messages to all connected chat clients | `/ws/chat` |

---

## Testing WebSocket Endpoints

### Using Postman

- Open a new WebSocket request with the URL `ws://localhost:8080/ws/echo`.
- Send a message; you should receive the same message back prefixed by `echo: `.
- Open two WebSocket connections to `ws://localhost:8080/ws/chat` in Postman.
- Send a chat message in one tab; it broadcasts to all connected chat tabs.

### Unit Tests

Testing uses mocks to simulate WebSocket sessions:

- `WebSocketHandlersTest.java` contains tests for both echo and chat functionality.
- To run tests:
```

mvn test

```

---

## CORS (Cross-Origin Resource Sharing)

- Configured via `.setAllowedOrigins("*")` on handlers to allow connections from any origin.
- Modify if needed to restrict origins in production.

---

## Security

- Spring Security configured to allow unauthenticated access to WebSocket endpoints.
- CSRF protection disabled for WebSocket handshake.
- HTTP Basic Auth enabled for other endpoints (customize as needed).
- Adjust or remove security config for your requirements.

---

## Logging

- Both handlers log received and sent messages with session IDs for traceability.
- Logs help verify separate endpoint communication and broadcast behavior.

---

## Project Structure

```

src/
├── main/
│   ├── java/
│   │   └── web_socket/demo/
│   │       ├── handler/
│   │       │   ├── EchoHandler.java      \# Echo WebSocket handler
│   │       │   └── ChatHandler.java      \# Chat WebSocket handler
│   │       ├── config/
│   │       │   └── RawWsConfig.java      \# WebSocket handler registration
│   │       └── SecurityConfig.java       \# Spring Security configuration
│   └── resources/
│       └── application.properties        \# Optional config
└── test/
└── java/
└── web_socket/demo/
└── WebSocketHandlersTest.java  \# Unit tests for handlers

```

---

## Additional Notes

- This project can be extended by adding STOMP messaging over WebSocket.
- Use real WebSocket clients or browser clients for integration testing STOMP.
- Adjust CORS and security configurations for production readiness.

---

Feel free to ask if you want detailed guide to add STOMP, improve tests, or deploy the app.

---
```

