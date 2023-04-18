package com.diditech.iov.gps.app.core.config;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * HTTP请求日期转换器，支持多种日期格式
 * @author zhjd
 * @date 2021/3/5
 */
@Slf4j
public class DateConverter implements Converter<String, Date> {

    /**
     * date format 格式
     */
    private static final String[] PATTERNS = {"yyyy-MM-dd HH:mm:ss",
            "yyyyMMdd", "yyyy-MM-dd", "yyyyMMddHHmmss", "yyMMddHHmmss", "EEE MMM dd HH:mm:ss Z yyyy"};

    @Override
    public Date convert(String s) {
        return parseDate(s);
    }

    public static Date parseDate(String s) {
        Date date;
        for (String p : PATTERNS) {
            try {
                date = DateUtil.parse(s, p);
            } catch (Exception ex) {
                continue;
            }
            if (date != null && s.length() == p.length()) {
                return date;
            }
        }
        return null;
    }
}