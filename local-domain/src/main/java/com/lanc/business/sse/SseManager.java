package com.lanc.business.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SSE管理器
 *
 * @author lanjianqing
 */
@Service
@Slf4j
public class SseManager {

    public static final Map<Object, SseEmitter> SSE_CLIENTS = new ConcurrentHashMap<>();
    private final RedisTemplate<String, String> redisTemplate;

    public static final String CHANNEL = "sse_channel";

    public SseManager(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        // 订阅 Redis 频道
        redisTemplate.convertAndSend(CHANNEL, "Server started listening!");
    }

    /**
     * 客户端连接
     *
     * @param clientId 客户端标识
     * @return SseEmitter
     */
    public <T> SseEmitter connect(T clientId) {
        // 长时间保持连接
        SseEmitter emitter = new SseEmitter(0L);
        SSE_CLIENTS.put(clientId, emitter);
        log.info("sse clients count: {}", SSE_CLIENTS.size());

        emitter.onCompletion(() -> SSE_CLIENTS.remove(clientId));
        emitter.onTimeout(() -> SSE_CLIENTS.remove(clientId));
        emitter.onError((e) -> SSE_CLIENTS.remove(clientId));
        return emitter;
    }

    /**
     * 发布广播消息
     *
     * @param message 消息
     */
    public void sendMessage(String message) {
        redisTemplate.convertAndSend(CHANNEL, message);
    }

    /**
     * 接收到消息，并推送给客户端
     *
     * @param message 事件
     */
    public static void handleMessage(String message) {
        log.info("message: {}", message);
        log.info("sse clients count: {}", SSE_CLIENTS.size());
        SSE_CLIENTS.forEach((clientId, emitter) -> {
            try {
                log.info("clientId: {}", clientId);
                emitter.send(SseEmitter.event().data(message));
            } catch (IOException e) {
                emitter.completeWithError(e);
                SSE_CLIENTS.remove(clientId);
            }
        });
    }
}
