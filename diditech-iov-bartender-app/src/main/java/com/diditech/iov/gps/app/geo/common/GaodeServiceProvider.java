package com.diditech.iov.gps.app.geo.common;

import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.geo.lbs.service.LbsService;
import com.diditech.iov.gps.app.geo.wifi.service.WifiService;
import com.diditech.iov.gps.app.geo.wifi.util.GaodeHttpUtils;
import com.diditech.iov.gps.app.geo.wifi.vo.GaodeApiResult;
import dd.utils.Util;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * 高德服务提供者
 * @author hefan
 * @date 2021/10/21 15:09
 */
@Slf4j
@AllArgsConstructor
public class GaodeServiceProvider implements IServiceProvider {

    protected WifiService rsService;

    protected GaodeHttpUtils gaodeHttpUtils;

    protected LbsService lbsService;

    @Override
    public String lbs(String mcc, String mnc, String bts, String nearBts) {
        if (!StringUtils.isEmpty(nearBts) && nearBts.endsWith(Const.SEP_LINE)) {
            nearBts = nearBts.substring(0, nearBts.length() - 1);
        }
        boolean invalidBts = StringUtils.isEmpty(bts) || lbsService.isInvalid(bts);
        if (invalidBts || lbsService.isInvalid(nearBts)
                || Util.asInt(mcc) != Const.LBS_MCC) {
            return null;
        }
        bts = fillMissingSignalNumber(bts, nearBts);
        return lbsService.getCoordinate(mcc, mnc, bts, nearBts);
    }

    @Override
    public String wifi(String imei, String macs, String mcc, String mnc, String lbs) {
        // 分隔符替换
        rsService.checkParameter(imei, macs, mcc, mnc, lbs);

        String[] lbsArr = rsService.convertLbsInfo(mcc, mnc, lbs);

        GaodeApiResult rlt;
        try {
            rlt = gaodeHttpUtils.getRequestForObject(imei, macs, lbsArr);
            if (rlt == null || rlt.getResult() == null || StringUtils.isEmpty(rlt.getResult().getDesc())) {
                return "";
            }
        } catch (Throwable e) {
            log.error("Throwable:{}", e.getMessage());
            return "";
        }

        return rlt.getResult().getDesc() + Const.SEP_LINE
                + rlt.getResult().getCity() + Const.SEP_LINE
                + rlt.getResult().getLocation() + Const.SEP_LINE
                + rlt.getResult().getRadius();
    }

}
