package com.diditech.iov.gps.app.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 核心业务存储Redis配置类
 * @author zhjd
 * @date 2023/3/6
 */
@Configuration
public class CoreRedisConfig extends RedisDBConfigBase {

    @Override
    @Primary
    @Bean("coreRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        return super.redisTemplate();
    }

    @Primary
    @Bean("coreRedisConnectionFactory")
    public RedisConnectionFactory redisConnectionFactory() {
        return this.redisTemplate().getConnectionFactory();
    }

    @Override
    @Autowired
    public void setHost(@Value("${redis.core.host}") String host) {
        this.host = host;
    }

    @Override
    @Autowired
    public void setPort(@Value("${redis.core.port}") int port) {
        this.port = port;
    }

    @Override
    @Autowired
    public void setPassword(@Value("${redis.core.password}") String password) {
        this.password = password;
    }

    @Override
    @Autowired
    public void setDatabase(@Value("${redis.core.database}") int database) {
        this.database = database;
    }

    @Override
    @Autowired
    public void setMinIdle(@Value("${redis.common.pool.min-idle}") int minIdle) {
        this.minIdle = minIdle;
    }

    @Override
    @Autowired
    public void setMaxIdle(@Value("${redis.common.pool.max-idle}") int maxIdle) {
        this.maxIdle = maxIdle;
    }

    @Override
    @Autowired
    public void setTimeout(@Value("${redis.common.timeout}") int timeout) {
        this.timeout = timeout;
    }

    @Override
    @Autowired
    public void setMaxActive(@Value("${redis.common.pool.max-active}") int maxActive) {
        this.maxActive = maxActive;
    }

    @Override
    @Autowired
    public void setMaxWait(@Value("${redis.common.pool.max-wait}") int maxWait) {
        this.maxWait = maxWait;
    }

}