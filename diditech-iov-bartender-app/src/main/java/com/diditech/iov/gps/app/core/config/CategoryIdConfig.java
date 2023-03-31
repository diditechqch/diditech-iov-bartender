package com.diditech.iov.gps.app.core.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 设备类型ID配置
 * @author zhaist
 * @date 2022/10/18 10:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix="obd.fuel")
public class CategoryIdConfig {
    private List<Integer> categoryIds;
}
