package com.diditech.iov.gps.app.core.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author Robin
 * @ClassName: FastJsonRedisSerializer
 * @Description: FastJson的Redis序列器
 * @date 2017年3月11日 下午7:05:21
 */
public class FastJsonRedisSerializer implements RedisSerializer<Object> {

    static {
        //实体类包地址
        ParserConfig.getGlobalInstance().addAccept("com.diditech");
    }

    @Override
    public byte[] serialize(Object o) {
        if (o == null) {
            return null;
        }
        return JSON.toJSONBytes(o, SerializerFeature.WriteClassName);
    }

    @Override
    public Object deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return JSON.parse(bytes);
    }
}
