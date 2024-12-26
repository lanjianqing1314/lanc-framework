package com.lanc.business.sse;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.nio.charset.StandardCharsets;

/**
 * @author Administrator
 */
public class RedisMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 处理接收到的消息
        String messageBody = new String(message.getBody(), StandardCharsets.UTF_8);
        SseManager.handleMessage(messageBody);
    }
}

