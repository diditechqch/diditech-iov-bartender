package com.iov.gps.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 对称加密配置
 * @author hefan
 * @date 2020/8/18 10:22
 */
@Data
@Configuration
@ConfigurationProperties("keypair")
public class KeyPairConfig {
    private String filename;

    private String alias;

    private String password;
}
