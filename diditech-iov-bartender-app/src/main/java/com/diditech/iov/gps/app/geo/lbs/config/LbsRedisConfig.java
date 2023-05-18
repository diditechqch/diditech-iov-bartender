package com.diditech.iov.gps.app.geo.lbs.config;

import com.diditech.iov.gps.app.core.config.RedisDBConfigBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class LbsRedisConfig extends RedisDBConfigBase {

    @Bean("lbsRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        return super.redisTemplate();
    }

    @Override
    @Autowired
    public void setHost(@Value("${redis.lbs-key.host}") String host) {
        this.host = host;
    }

    @Override
    @Autowired
    public void setPort(@Value("${redis.lbs-key.port}") int port) {
        this.port = port;
    }

    @Override
    @Autowired
    public void setPassword(@Value("${redis.lbs-key.password}") String password) {
        this.password = password;
    }

    @Override
    @Autowired
    public void setDatabase(@Value("${redis.lbs-key.database}") int database) {
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