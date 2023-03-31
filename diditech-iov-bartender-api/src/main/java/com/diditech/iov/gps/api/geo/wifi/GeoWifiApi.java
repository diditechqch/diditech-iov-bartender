package com.diditech.iov.gps.api.geo.wifi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 热点解析
 * @author zhjd <br>
 * @date 2022/3/2 <br>
 */
@RequestMapping("/geo/wifi")
public interface GeoWifiApi {

    /**
     * @param macs 热点信息
     * @param mcc  国家号
     * @param mnc  网点号
     * @param lbs  基站信息
     * @author zhjd
     * @date 创建时间：2017年6月13日 下午6:06:27
     */
    @GetMapping
    String geo(
            @RequestParam String imei,
            @RequestParam String macs,
            @RequestParam(required = false) String mcc,
            @RequestParam(required = false) String mnc,
            @RequestParam(required = false) String lbs);
}
