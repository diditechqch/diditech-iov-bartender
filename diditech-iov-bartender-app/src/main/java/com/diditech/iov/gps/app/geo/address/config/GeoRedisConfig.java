package com.diditech.iov.gps.app.geo.address.config;

import com.diditech.iov.gps.app.core.config.RedisDBConfigBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class GeoRedisConfig extends RedisDBConfigBase {

    @Bean("geoRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        return super.redisTemplate();
    }

    @Bean("geoRedisConnectionFactory")
    public RedisConnectionFactory redisConnectionFactory() {
        return this.redisTemplate().getConnectionFactory();
    }

    @Bean("geoCacheManager")
    public CacheManager cacheManager(@Qualifier("geoRedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        return new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                RedisCacheConfiguration.defaultCacheConfig());
    }

    @Override
    @Autowired
    public void setHost(@Value("${redis.geo-cache.host}") String host) {
        this.host = host;
    }

    @Override
    @Autowired
    public void setPort(@Value("${redis.geo-cache.port}") int port) {
        this.port = port;
    }

    @Override
    @Autowired
    public void setPassword(@Value("${redis.geo-cache.password}") String password) {
        this.password = password;
    }

    @Override
    @Autowired
    public void setDatabase(@Value("${redis.geo-cache.database}") int database) {
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