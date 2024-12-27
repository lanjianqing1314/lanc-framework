package com.lanc.business.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author Administrator
 */
@Slf4j
@Data
@Configuration
public class RedissonConfig {

    @Resource
    private RedissonProperties redissonProperties;

    /**
     * redisson 单机配置
     *
     * @return {@link RedissonClient}单机实例
     */
    @ConditionalOnProperty(prefix = "redisson.single", name = "enabled", havingValue = "true")
    @Bean(destroyMethod = "shutdown", value = "singleRedissonClient")
    public RedissonClient singleRedissonClient() {
        log.info("正在初始化singleRedissonClient");
        // 创建配置
        Config config = new Config();
        // 编码
        config.setCodec(StringCodec.INSTANCE);
        // 使用NIO传输模式
        config.setTransportMode(TransportMode.NIO);

        RedissonProperties.SingleRedissonConfigProperties single = redissonProperties.getSingle();
        // 单机版服务配置
        config.useSingleServer()
                .setPassword(single.getPassword())
                .setAddress(single.getAddress())
                .setDatabase(single.getDatabase());

        // 创建客户端
        return Redisson.create(config);
    }

    /**
     * redisson 集群配置
     *
     * @return {@link RedissonClient}集群实例
     */
    @ConditionalOnProperty(prefix = "redisson.cluster", name = "enabled", havingValue = "true")
    @Bean(destroyMethod = "shutdown", value = "clusterRedissonClient")
    public RedissonClient clusterRedissonClient() {
        log.info("正在初始化clusterRedissonClient");
        RedissonProperties.ClusterRedissonConfigProperties cluster = redissonProperties.getCluster();
        Set<String> addresses = cluster.getAddresses();
        if (addresses == null || addresses.isEmpty()) {
            throw new RuntimeException("请检查redisson.cluster的配置");
        }

        Config config = new Config();
        // 对象编码选择纯字符串编码
        config.setCodec(StringCodec.INSTANCE);
        config.useClusterServers()
                // 节点地址
                .addNodeAddress(addresses.toArray(new String[0]))
                // 设置密码
                .setPassword(cluster.getPassword())
                // redis连接心跳检测，防止一段时间过后，与redis的连接断开
                .setPingConnectionInterval(cluster.getPingConnectionInterval());

        return Redisson.create(config);
    }
}
