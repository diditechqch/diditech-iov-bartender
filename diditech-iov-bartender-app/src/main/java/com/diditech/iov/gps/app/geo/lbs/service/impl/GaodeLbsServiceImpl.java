package com.diditech.iov.gps.app.geo.lbs.service.impl;

import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.geo.lbs.service.LbsService;
import com.diditech.iov.gps.app.geo.wifi.util.GaodeHttpUtils;
import com.diditech.iov.gps.app.geo.wifi.vo.GaodeApiResult;
import dd.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("GaodeLbsService")
public class GaodeLbsServiceImpl implements LbsService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private GaodeHttpUtils gaodeHttpUtils;

    @Value("${geo.lbs.gaode.url}")
    private String lbsGaoDeApi;

    @Value("${geo.lbs.gaode.key}")
    private String lbsGaoDeKey;

    /**
     * 利用redis获取指定高德key，调用高德API获取基站信息
     * @author zhjd
     * @date 20200211
     */
    @Override
    public String getCoordinate(String mcc, String mnc, String bts, String nearBts) {
        log.debug("Get GaoDe key: {}", lbsGaoDeKey);
        String countryInfo = mcc + Const.SEP_COMMA + mnc + Const.SEP_COMMA;
        String nearBtsInfo = "";
        if (!Util.isEmpty(nearBts)) {
            StringBuilder builder = new StringBuilder();
            String[] nearBtsArr = nearBts.split(Const.REGEX_LINE);
            for (String sub : nearBtsArr) {
                builder.append(countryInfo)
                        .append(sub)
                        .append(getSeparator());
            }
            nearBtsInfo = builder.substring(0, builder.length() - 1);
        }
        String btsInfo = countryInfo.concat(bts);
        GaodeApiResult rlt;
        try {
            rlt = gaodeHttpUtils.getRequestForObject(lbsGaoDeApi, lbsGaoDeKey, btsInfo, nearBtsInfo);
        } catch (Throwable e) {
            log.error("Throwable:{}", e.getMessage());
            return "";
        }

        if (rlt == null || rlt.isFail()) {
            return "";
        }
        String[] coor = rlt.getResult().getLocation().split(Const.SEP_COMMA);
        if (coor.length < 2) {
            return "";
        }
        // lng, lat, radius
        String result = rlt.getResult().getLocation().concat(Const.SEP_COMMA) + rlt.getResult().getRadius();

        log.debug("result={}, using gaode key={}", result, lbsGaoDeKey);
        return result;
    }

}
