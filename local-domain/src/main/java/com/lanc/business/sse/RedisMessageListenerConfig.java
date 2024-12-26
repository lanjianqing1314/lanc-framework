package com.lanc.business.sse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * 订阅消息通道
 *
 * @author lanjianqing
 */
@Configuration
public class RedisMessageListenerConfig {

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);

        // 订阅频道
        container.addMessageListener(new RedisMessageListener(), new ChannelTopic(SseManager.CHANNEL));

        return container;
    }
}

