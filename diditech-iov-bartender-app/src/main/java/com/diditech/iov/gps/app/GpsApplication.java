package com.diditech.iov.gps.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * bartender微服务启动类
 * @author zhjd
 * @date 2019/2/14
 */
@SpringBootApplication(exclude = {CassandraDataAutoConfiguration.class, CassandraAutoConfiguration.class})
public class GpsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GpsApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
