package com.diditech.iov.gps.app.geo.wifi.provider;

import com.diditech.iov.gps.api.geo.wifi.GeoWifiApi;
import com.diditech.iov.gps.app.geo.common.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhjd <br>
 * @date 2022/3/2 <br>
 */
@RestController
public class WifiProvider implements GeoWifiApi {

    @Autowired
    private IServiceProvider serviceProvider;

    /**
     * @param macs 热点信息
     * @param mcc  国家号
     * @param mnc  网点号
     * @param lbs  基站信息
     * @author zhjd
     * @date 创建时间：2017年6月13日 下午6:06:27
     */
    @Override
    public String geo(
            @RequestParam String imei,
            @RequestParam String macs,
            @RequestParam(required = false) String mcc,
            @RequestParam(required = false) String mnc,
            @RequestParam(required = false) String lbs) {
        return serviceProvider.wifi(imei, macs, mcc, mnc, lbs);
    }
}
