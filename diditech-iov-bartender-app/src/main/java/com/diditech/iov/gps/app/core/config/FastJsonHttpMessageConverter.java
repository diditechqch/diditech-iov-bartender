package com.diditech.iov.gps.app.core.config;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.diditech.iov.gps.api.core.ResponseMessage;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Robin
 * @ClassName: FastJsonHttpMessageConverter
 * @Description: 重写 SpringMVC的MessageConverter，请求为JSON格式时，自动转换参数
 * @date 2017年3月9日 上午11:30:40
 */
public class FastJsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    private Charset charset = StandardCharsets.UTF_8;

    private SerializerFeature[] features = new SerializerFeature[0];

    public FastJsonHttpMessageConverter() {
        super(new MediaType("application", "json", StandardCharsets.UTF_8),
                new MediaType("application", "*+json", StandardCharsets.UTF_8));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public SerializerFeature[] getFeatures() {
        return features;
    }

    public void setFeatures(SerializerFeature... features) {
        this.features = features;
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream in = inputMessage.getBody();
        byte[] buf = new byte[1024];
        for (; ; ) {
            int len = in.read(buf);
            if (len == -1) {
                break;
            }

            if (len > 0) {
                baos.write(buf, 0, len);
            }
        }
        byte[] bytes = baos.toByteArray();
        if (clazz == String.class) {
            return new String(bytes, charset);
        }
        return JSON.parseObject(bytes, 0, bytes.length, charset.newDecoder(), clazz);
    }

    public String converter(Object obj) {
        if (obj instanceof String) return (String) obj;
        String text;
        String callback = ThreadLocalUtils.get("jsonp-callback");
        ThreadLocalUtils.remove("jsonp-callback");
        if (obj instanceof ResponseMessage) {
            ResponseMessage message = (ResponseMessage) obj;
            if (message.isSuccess() && message.isOnlyData())
                obj = message.getData();
            if (obj instanceof String)
                text = ((String) obj);
            else
                text = JSON.toJSONString(obj, parseFilter(message), features);
            if (callback == null) callback = message.getCallback();
        } else {
            text = JSON.toJSONString(obj, features);
        }
        if (!StrUtil.isBlank(callback)) {
            text = callback + "(" + text + ")";
        }
        return text;
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException {
        OutputStream out = outputMessage.getBody();

        byte[] bytes = converter(obj).getBytes(charset);
        out.write(bytes);
        out.flush();
    }

    protected PropertyPreFilter[] parseFilter(ResponseMessage responseMessage) {
        List<PropertyPreFilter> filters = new ArrayList<>();
        if (responseMessage.getIncludes() != null)
            for (Map.Entry<Class<?>, Set<String>> classSetEntry : responseMessage.getIncludes().entrySet()) {
                SimplePropertyPreFilter filter = new SimplePropertyPreFilter(classSetEntry.getKey());
                filter.getIncludes().addAll(classSetEntry.getValue());
                filters.add(filter);
            }
        if (responseMessage.getExcludes() != null)
            for (Map.Entry<Class<?>, Set<String>> classSetEntry : responseMessage.getExcludes().entrySet()) {
                SimplePropertyPreFilter filter = new SimplePropertyPreFilter(classSetEntry.getKey());
                filter.getExcludes().addAll(classSetEntry.getValue());
                filters.add(filter);
            }
        return filters.toArray(new PropertyPreFilter[0]);
    }

}
