package com.diditech.iov.gps.app.geo.address.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * 百度请求配置项
 * @author zhjd <br>
 * @date 2023/3/7 <br>
 */
@Configuration
@ConfigurationProperties(prefix = "geo.address.baidu")
public class BaiduMapConfig extends MapConfigBase {
    @PostConstruct
    public void init() {
        keyStatusMap = new HashMap<>();
        for (String k : keyList) {
            keyStatusMap.put(k, OK);
        }
        availableKey = keyList.get(0);
    }
}