# Spring Boot Raw WebSocket Demo

A minimal Spring Boot project that demonstrates raw (frame-level) WebSocket handlers — an echo handler and a simple chat/broadcast handler — together with basic CORS and security configuration and unit tests.

This README is cleaned, reorganized, and focused on quick setup, usage, and common configuration points.

---

## Table of contents

- [What this is](#what-this-is)
- [Features](#features)
- [Requirements](#requirements)
- [Quick start](#quick-start)
- [WebSocket endpoints & examples](#websocket-endpoints--examples)
- [Testing](#testing)
- [CORS & Security](#cors--security)
- [Logging](#logging)
- [Project structure](#project-structure)
- [Extending the project](#extending-the-project)
- [Contributing](#contributing)
- [License](#license)

---

## What this is

A compact demo showing how to write raw WebSocket handlers with Spring Boot:

- Echo handler that replies with the same message prefixed by `echo: `.
- Chat handler that broadcasts messages to all connected sessions.
- Unit tests that use mocks to validate handler behavior.
- Minimal security configuration that allows unauthenticated WebSocket handshakes.

This is intended for learning and small experiments (not production-ready).

---

## Features

- Raw WebSocket message handling (TextMessage).
- Session management for broadcasting.
- Simple CORS configuration for WebSockets.
- Example Spring Security configuration allowing WebSocket use without authentication.
- Unit tests with JUnit 5 and Mockito.

---

## Requirements

- Java 17 or later
- Maven (or adapt to Gradle)
- (Optional) Postman, wscat, or a browser for manual testing

---

## Quick start

1. Clone the repository:
```bash
git clone https://github.com/Arkrly/WebSocker-Demo.git
cd WebSocker-Demo
```

2. Build:
```bash
mvn clean install
```

3. Run:
```bash
mvn spring-boot:run
```

The server will listen on: http://localhost:8080 by default.

---

## WebSocket endpoints & examples

Endpoints provided:
- Echo: `/ws/echo` — replies with `echo: <your message>`
- Chat: `/ws/chat` — broadcasts incoming messages to all connected clients (includes session id in logs)

Examples:

- Using wscat:
```bash
# install wscat (npm)
npm i -g wscat

# connect to echo
wscat -c ws://localhost:8080/ws/echo
# send a message, expect "echo: <message>"

# connect two clients to chat
wscat -c ws://localhost:8080/ws/chat
wscat -c ws://localhost:8080/ws/chat
# send in one client => both clients receive message
```

- Using browser console:
```js
// Echo
const s = new WebSocket('ws://localhost:8080/ws/echo');
s.onmessage = e => console.log('recv', e.data);
s.onopen = () => s.send('hello');

// Chat
const c = new WebSocket('ws://localhost:8080/ws/chat');
c.onmessage = e => console.log('chat recv', e.data);
c.onopen = () => c.send('hi everyone');
```

---

## Testing

- Unit tests use JUnit 5 and Mockito to simulate WebSocket sessions.
- Run tests:
```bash
mvn test
```

Relevant test file: `WebSocketHandlersTest.java` (contains tests for echo and chat handlers).

---

## CORS & Security

- CORS for the WebSocket handlers is permissive in this demo (`setAllowedOrigins("*")`) so you can test from different origins. Tighten origins for production.
- Security config allows unauthenticated WebSocket handshake while keeping other HTTP endpoints protected (example configuration file: `SecurityConfig.java`).
- CSRF is typically not applicable to raw WebSocket frames, but check handshake endpoints if you enable CSRF elsewhere.

---

## Logging

- Handlers log incoming and outgoing messages with session identifiers to help trace activity and broadcast behavior.
- Use logging configuration (application.properties / logback) to adjust log levels and formats.

---

## Project structure

A simplified view of the source layout:

```
src/
├── main/
│   ├── java/
│   │   └── web_socket/demo/
│   │       ├── handler/
│   │       │   ├── EchoHandler.java
│   │       │   └── ChatHandler.java
│   │       ├── config/
│   │       │   └── RawWsConfig.java
│   │       └── SecurityConfig.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── web_socket/demo/
            └── WebSocketHandlersTest.java
```

---

## Extending the project

Ideas to expand this demo:

- Add STOMP and SockJS support for higher-level messaging.
- Implement authentication/authorization for chat rooms.
- Persist chat history or add user nicknames.
- Add integration tests with an embedded WebSocket client.

If you want, I can add a STOMP example, tighten the security config, or create integration tests.

---

## Contributing

Contributions, fixes and suggestions are welcome. Open a PR or start an issue describing what you want to change.

---

## License

Choose a license for your project (e.g., MIT, Apache 2.0) and add a LICENSE file if you plan to share publicly.

---
