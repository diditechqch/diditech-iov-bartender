package com.diditech.iov.gps.app.core.config;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Lionel
 * @Description 整数集合解析器
 * @date 2017年12月19日 上午10:58:02
 */
public class IntegerArray4GetConverter implements Converter<String, Set<Integer>> {

    public static final Logger logger = LoggerFactory.getLogger(IntegerArray4GetConverter.class);

    @Override
    public Set<Integer> convert(String s) {
        try {
            if (StrUtil.isNotBlank(s)) {
                String[] keys = s.split(",");
                return Arrays.stream(keys)
                        .map(idStr -> StrUtil.isBlank(idStr) ? 0 : Integer.valueOf(idStr))
                        .collect(Collectors.toSet());
            }
        } catch (Exception e) {
            logger.error("整数集合参数解析异常", e);
        }
        return null;
    }
}
