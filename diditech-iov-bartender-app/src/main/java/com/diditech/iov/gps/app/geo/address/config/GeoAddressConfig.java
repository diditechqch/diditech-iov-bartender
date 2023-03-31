package com.diditech.iov.gps.app.geo.address.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 反地理编码存储配置项
 * @author zhjd <br>
 * @date 2023/3/7 <br>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "geo.address")
public class GeoAddressConfig {
    private int batchThreadNum;
    private boolean useLocalDb;
    private String version;
    private String source;

    @Primary
    @Bean("geoExecutor")
    public ExecutorService getExecutor() {
        return Executors.newFixedThreadPool(batchThreadNum);
    }

}
