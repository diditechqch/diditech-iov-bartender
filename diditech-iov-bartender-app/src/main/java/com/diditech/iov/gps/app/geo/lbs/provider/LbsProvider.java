package com.diditech.iov.gps.app.geo.lbs.provider;

import com.diditech.iov.gps.api.geo.lbs.GeoLbsApi;
import com.diditech.iov.gps.app.geo.common.IServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhjd <br>
 * @date 2022/3/2 <br>
 */
@Slf4j
@RestController
public class LbsProvider implements GeoLbsApi {

    @Autowired
    private IServiceProvider service;

    /**
     * @param mcc     移动国家号码
     * @param mnc     移动网号
     * @param bts     主基站
     * @param nearBts 辅助基站
     */
    @Override
    public String getLbsInfo(@RequestParam(value = "mcc") String mcc,
                             @RequestParam(value = "mnc") String mnc,
                             @RequestParam(value = "bts") String bts,
                             @RequestParam(value = "nearBts", required = false) String nearBts) {
        try {
            String result = service.lbs(mcc, mnc, bts, nearBts);
            if (null == result) {
                return "";
            }
            return result;
        } catch (Throwable e) {
            log.error("", e);
            return "";
        }
    }
}
