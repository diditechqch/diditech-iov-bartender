package com.diditech.iov.gps.app.core.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * @author Robin
 * @ClassName: MessageConverterConfiguration
 * @Description: 注入自定义MessageConverter
 * @date 2017年3月11日 上午9:46:33
 */
@Configuration
public class MessageConverterConfiguration {

    @Bean
    public HttpMessageConverter<Object> converter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setFeatures(
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect
        );
        return converter;
    }

    @Bean
    public DateConverter dateConverter() {
        return new DateConverter();
    }

    @Bean
    public IntegerArray4GetConverter integerArray4GetConverter() {
        return new IntegerArray4GetConverter();
    }
}
