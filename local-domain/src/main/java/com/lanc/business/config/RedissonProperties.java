package com.lanc.business.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @author Administrator
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(RedissonProperties.PREFIX)
public class RedissonProperties {

    public static final String PREFIX = "redisson";

    private SingleRedissonConfigProperties single;
    private ClusterRedissonConfigProperties cluster;

    /**
     * 单机redisson配置属性
     */
    @Data
    public static class SingleRedissonConfigProperties {
        private Integer database = 0;
        private String address;
        private String password;
        private Boolean enabled;
    }

    /**
     * 集群redisson配置属性
     */
    @Data
    public static class ClusterRedissonConfigProperties {
        private Set<String> addresses;
        private String password;
        private Integer pingConnectionInterval;
        private Boolean enabled;
    }

    @PostConstruct
    private void initLog() {
        log.info("单机版初始化：{}", single);
        log.info("集群版初始化：{}", cluster);
        checkConfig();
    }

    private void checkConfig() {
        if (single != null) {
            String address = single.getAddress();
            if (address == null) {
                throw new RuntimeException("请检查配置属性 redisson.single.address");
            }
            String password = single.getPassword();
            if (password == null) {
                throw new RuntimeException("请检查配置属性 redisson.single.password");
            }
        }
    }
}
