package com.iov.gps.auth.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import lombok.Data;

/**
 * oauth client配置
 * @author hefan
 * @date 2020/8/18 10:22
 */
@Data
@Configuration
@ConfigurationProperties("application.security.oauth")
public class ClientDetails {
    private List<BaseClientDetails> client;
}
