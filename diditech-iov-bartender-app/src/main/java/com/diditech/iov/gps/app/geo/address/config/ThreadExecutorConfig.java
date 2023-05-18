package com.diditech.iov.gps.app.geo.address.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhjd <br>
 * @date 2021/6/21 <br>
 */
@Component
public class ThreadExecutorConfig {

    @Value("${geo.address.batch-thread-num}")
    private int threadNum;

    @Bean
    public ExecutorService getGeoBatchExecutorService() {
        return Executors.newFixedThreadPool(threadNum);
    }
}
