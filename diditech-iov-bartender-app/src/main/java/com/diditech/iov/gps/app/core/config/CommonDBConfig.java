package com.diditech.iov.gps.app.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhjd <br>
 * @date 2023/3/7 <br>
 */
@Data
@Configuration("defaultConfig")
@ConfigurationProperties("datasource.common")
public class CommonDBConfig extends BaseDBConfig {

}
