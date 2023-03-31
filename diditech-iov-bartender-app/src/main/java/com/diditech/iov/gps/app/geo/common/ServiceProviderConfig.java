package com.diditech.iov.gps.app.geo.common;

import com.diditech.iov.gps.app.geo.lbs.service.LbsService;
import com.diditech.iov.gps.app.geo.wifi.service.WifiService;
import com.diditech.iov.gps.app.geo.wifi.util.CelLocationHttpUtils;
import com.diditech.iov.gps.app.geo.wifi.util.GaodeHttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 服务提供商配置
 * @author hefan
 * @date 2021/10/21 14:53
 */
@Configuration
public class ServiceProviderConfig {

    @Autowired
    @Qualifier("GaodeWifiService")
    private WifiService gaodeWifiService;

    @Autowired
    @Qualifier("CelLocationWifiService")
    private WifiService celLocationWifiService;

    @Autowired
    private GaodeHttpUtils gaodeHttpUtils;

    @Autowired
    private CelLocationHttpUtils celLocationHttpUtils;

    @Autowired
    @Qualifier("GaodeLbsService")
    private LbsService gaodeLbsService;

    @Autowired
    @Qualifier("CelLocationLbsService")
    private LbsService celLocationLbsService;

    @Bean("serviceProvider")
    @ConditionalOnProperty(prefix = "geo.wifi", name = "service-provider", havingValue = "cellocation")
    public IServiceProvider celLocationServiceProvider() {
        return new CelLocationServiceProvider(celLocationWifiService, celLocationHttpUtils, celLocationLbsService);
    }

    @Bean("serviceProvider")
    @ConditionalOnProperty(prefix = "geo.wifi", name = "service-provider",havingValue = "gaode")
    public IServiceProvider gaodeServiceProvider() {
        return new GaodeServiceProvider(gaodeWifiService, gaodeHttpUtils, gaodeLbsService);
    }

    @Bean("serviceProvider")
    @ConditionalOnMissingBean(IServiceProvider.class)
    public IServiceProvider defaultServiceProvider() {
        return new GaodeServiceProvider(gaodeWifiService, gaodeHttpUtils, gaodeLbsService);
    }

}
