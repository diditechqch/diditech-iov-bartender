package com.diditech.iov.gps.app.geo.common;

import com.diditech.iov.gps.app.core.util.Const;
import dd.utils.Util;

public interface IServiceProvider {

    /**
     * wifi定位功能
     * @author hefan
     * @date 2021/10/21 15:10
     */
    String wifi(String imei, String macs, String mcc, String mnc, String lbs);

    /**
     * lbs定位功能
     * @author hefan
     * @date 2021/10/21 15:10
     */
    String lbs(String mcc, String mnc, String bts, String nearBts);

    /**
     * 设备上传只有主基站数据时，偶尔会上报没有信号强度的数据，此时需要补零
     * @author hefan
     * @date 2021/10/22 11:07
     */
    default String fillMissingSignalNumber(String bts, String nearBts) {
        if (Util.isEmpty(nearBts)) {
            String[] info = bts.split(Const.SEP_COMMA);
            if (2 == info.length) {
                return bts.concat(Const.SEP_COMMA).concat("0");
            }
        }
        return bts;
    }

}
