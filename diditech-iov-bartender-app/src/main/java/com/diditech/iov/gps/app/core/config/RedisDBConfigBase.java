package com.diditech.iov.gps.app.core.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;

public abstract class RedisDBConfigBase {

    protected String host;

    protected int port;

    protected String username;

    protected String password;

    protected int database;

    protected int minIdle;

    protected int maxIdle;

    protected int maxActive;

    protected int maxWait;

    protected int timeout;

    public abstract void setHost(String host);

    public abstract void setPort(int port);

    public abstract void setPassword(String password);

    public abstract void setDatabase(int database);

    public abstract void setMinIdle(int minIdle);

    public abstract void setMaxIdle(int maxIdle);

    public abstract void setTimeout(int timeout);

    public abstract void setMaxActive(int maxActive);

    public abstract void setMaxWait(int maxWait);

    private static final ObjectMapper om = new ObjectMapper();

    static {
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
    }

    protected RedisTemplate<String, Object> redisTemplate() {
        return new FastJsonRedisTemplate(connectionFactory());
    }

    protected RedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setDatabase(database);
        config.setHostName(host);
        config.setPort(port);
        config.setPassword(RedisPassword.of(password));

        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .poolConfig(poolConfig(maxIdle, minIdle, maxWait))
                .build();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(config, clientConfig);
        return factory;
    }

    protected GenericObjectPoolConfig poolConfig(int maxIdle, int minIdle, long maxWaitMillis) {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMinIdle(minIdle);
        genericObjectPoolConfig.setMaxWaitMillis(maxWaitMillis);
        return genericObjectPoolConfig;
    }
}
