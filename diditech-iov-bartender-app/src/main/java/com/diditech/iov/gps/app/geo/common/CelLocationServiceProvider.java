package com.diditech.iov.gps.app.geo.common;

import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.geo.lbs.service.LbsService;
import com.diditech.iov.gps.app.geo.wifi.service.WifiService;
import com.diditech.iov.gps.app.geo.wifi.util.CelLocationHttpUtils;
import com.diditech.iov.gps.app.geo.wifi.vo.CelLocationResult;
import lombok.extern.slf4j.Slf4j;

/**
 * CelLocation服务提供者
 * @author hefan
 * @date 2021/10/21 15:09
 */
@Slf4j
public class CelLocationServiceProvider extends GaodeServiceProvider {

    private CelLocationHttpUtils celLocationHttpUtils;

    public CelLocationServiceProvider(WifiService celLocationService,
                                      CelLocationHttpUtils celLocationHttpUtils,
                                      LbsService lbsService) {
        super(celLocationService, null, lbsService);
        this.celLocationHttpUtils = celLocationHttpUtils;
    }

    @Override
    public String wifi(String imei, String macs, String mcc, String mnc, String lbs) {
        // 分隔符替换
        rsService.checkParameter(imei, macs, mcc, mnc, lbs);

        String[] lbsArr = rsService.convertLbsInfo(mcc, mnc, lbs);
        String tmp = macs.replaceAll("\\,\\|", Const.SEP_SEMICOLON);
        String wl = tmp.substring(0, tmp.length() - 1);
        StringBuilder sb = new StringBuilder();
        for (String lbsStr : lbsArr) {
            sb.append(lbsStr)
                    .append(Const.SEP_SEMICOLON);
        }
        String cl = sb.substring(0, sb.length() - 1);
        CelLocationResult rlt;
        try {
            rlt = celLocationHttpUtils.getRequestForObject(cl, wl);
            if (rlt == null || rlt.getErrcode() != 0) {
                return "";
            }
        } catch (Throwable e) {
            log.error("Throwable:{}", e.getMessage());
            return "";
        }

        return rlt.getAddress() + Const.SEP_LINE
                + "" + Const.SEP_LINE
                + rlt.getLon() + Const.SEP_LINE
                + rlt.getLat() + Const.SEP_LINE
                + rlt.getRadius();
    }

}
