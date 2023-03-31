package com.diditech.iov.gps.demo;

import com.diditech.iov.gps.demo.apache.rocketmq.OrderedConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiServiceApplication implements CommandLineRunner {

    @Autowired
    private OrderedConsumerService consumer;

    @Override
    public void run(String... args) throws Exception {
        consumer.start();
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiServiceApplication.class, args);
    }

}