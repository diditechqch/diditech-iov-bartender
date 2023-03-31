package com.diditech.iov.gps.app.core.config;

import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Robin
 * @ClassName: FastJsonRedisTemplate
 * @Description: FastJson的Redis访问模板
 * @date 2017年3月11日 下午7:06:24
 */
public class FastJsonRedisTemplate extends RedisTemplate<String, Object> {

    @SuppressWarnings("unchecked")
    public FastJsonRedisTemplate() {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        FastJsonRedisSerializer redisSerializer = new FastJsonRedisSerializer();
        setKeySerializer(stringSerializer);
        setValueSerializer(redisSerializer);
        setHashKeySerializer(stringSerializer);
        setHashValueSerializer(redisSerializer);
    }


    public FastJsonRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }

    @Override
    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }
}
