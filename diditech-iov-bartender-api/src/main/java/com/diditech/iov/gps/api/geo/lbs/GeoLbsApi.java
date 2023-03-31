package com.diditech.iov.gps.api.geo.lbs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 基站解析
 * @author zhjd <br>
 * @date 2022/3/2 <br>
 */
@RequestMapping("/geo/lbs")
public interface GeoLbsApi {

    /**
     * @param mcc     移动国家号码
     * @param mnc     移动网号
     * @param bts     主基站
     * @param nearBts 辅助基站
     */
    @GetMapping
    String getLbsInfo(@RequestParam(value = "mcc") String mcc,
                      @RequestParam(value = "mnc") String mnc,
                      @RequestParam(value = "bts") String bts,
                      @RequestParam(value = "nearBts", required = false) String nearBts);
}
