package com.example.backend.controller;

import lombok.NonNull;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebSocketHandler extends TextWebSocketHandler {
    private static final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();
        handleMessage(payload);
    }

    private void handleMessage(String message) {
        // 解析消息类型和内容
        // 假设消息格式为 JSON 格式，包含 type 和 content 字段
        // 根据 type 分发消息到不同的处理方法
        // 例如：{ "type": "syncPosition", "content": { "x": 10, "y": 20 } }
        //      { "type": "chat", "content": "Hello!" }

        // 解析 JSON
        // ...

        // 根据消息类型分发到不同的处理方法
        // 例如：
        // if (messageType.equals("syncPosition")) {
        //     handleSyncPositionMessage(content);
        // } else if (messageType.equals("chat")) {
        //     handleChatMessage(content);
        // }
    }

    private void handleSyncPositionMessage(String content) {
        // 处理同步位置消息
        // ...
    }

    private void handleChatMessage(String content) {
        // 处理聊天消息
        // ...
    }

    private void broadcastMessage(String message) {
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
