package com.diditech.iov.gps.app.geo.lbs.service.impl;

import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.geo.wifi.util.CelLocationHttpUtils;
import com.diditech.iov.gps.app.geo.wifi.vo.CelLocationResult;
import dd.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("CelLocationLbsService")
public class CelLocationLbsServiceImpl extends GaodeLbsServiceImpl {

    @Autowired
    private CelLocationHttpUtils celLocationHttpUtils;

    @Override
    public String getCoordinate(String mcc, String mnc, String bts, String nearBts) {
        String countryInfo = mcc + Const.SEP_COMMA + mnc + Const.SEP_COMMA;
        StringBuilder clBuilder = new StringBuilder(countryInfo);
        clBuilder.append(bts);
        if (!Util.isEmpty(nearBts)) {
            clBuilder.append(getSeparator());
            String[] nearBtsArr = nearBts.split(Const.REGEX_LINE);
            for (String sub : nearBtsArr) {
                clBuilder.append(countryInfo)
                        .append(sub)
                        .append(getSeparator());
            }
        }
        String cl = clBuilder.substring(0, clBuilder.length() - 1);
        CelLocationResult rlt;
        try {
            rlt = celLocationHttpUtils.getRequestForObject(cl, "");
        } catch (Throwable e) {
            log.error("Throwable:{}", e.getMessage());
            return "";
        }

        if (rlt == null || rlt.isFail()) {
            return "";
        }
        // lng, lat, radius
        String result = rlt.getLon() + Const.SEP_COMMA
                + rlt.getLat() + Const.SEP_COMMA
                + rlt.getRadius();

        log.debug("result={}", result);
        return result;
    }

    @Override
    public String getSeparator() {
        return Const.SEP_SEMICOLON;
    }

}
