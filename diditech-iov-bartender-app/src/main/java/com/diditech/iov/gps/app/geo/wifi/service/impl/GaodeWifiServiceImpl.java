package com.diditech.iov.gps.app.geo.wifi.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.geo.wifi.service.WifiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("GaodeWifiService")
public class GaodeWifiServiceImpl implements WifiService {
    /**
     * 每个LBS信息参数个数最小值：2
     */
    private static final int LBS_PROPERTIES_COUNT = 2;

    @Value("${geo.wifi.gaode.imei.min-length}")
    private int imeiMinLength;

    @Value("${geo.wifi.gaode.mac.min-count}")
    private int macMinCount;

    @Override
    public boolean checkParameter(String imei, String macs, String mcc, String mnc, String lbsInfo) {
        // macs=22:27:1d:20:08:d5,-55,CMCCEDU|5c:63:bf:a4:bf:56,-86,TP-LINK|d8:c7:c8:a8:1a:13,-42,TP-LINK

        if (StringUtils.isEmpty(imei) || StringUtils.isEmpty(macs)) {
            throw new RuntimeException("parameters is invalidate");
        }

        if (imei.length() < imeiMinLength) {
            throw new RuntimeException("parameters is invalidate");
        }

        String[] macArr = macs.split(Const.REGEX_LINE);

        if (!StringUtils.isEmpty(lbsInfo)) {
            isInvalidThrowE(lbsInfo);

            String[] lbsArr = lbsInfo.split(Const.REGEX_LINE);
            if (Const.LBS_MCC != Integer.valueOf(mcc)
                    || (1 != Integer.valueOf(mnc) && 0 != Integer.valueOf(mnc))
                    || lbsArr.length < 1) {
                if (macArr.length < macMinCount) {
                    throw new RuntimeException("parameters is invalidate");
                }
            }
        }

        String[] macInfo;
        String[] macCells;
        for (String mac : macArr) {

            if (StringUtils.isEmpty(mac)) {
                throw new RuntimeException("mac parameters is invalidate");
            }

            macInfo = mac.split(Const.SEP_COMMA);
            if (macInfo.length != 3 && macInfo.length != 2) {
                // MACS信息参数个数有效值：2、3
                throw new RuntimeException("mac parameters is invalidate");
            }

            Integer.parseInt(macInfo[1]); // number type check

            macCells = macInfo[0].split(":");
            if (macCells.length != 6) {
                throw new RuntimeException("mac parameters is invalidate");
            }

            for (String macCell : macCells) {
                if (StringUtils.isEmpty(macCell) || macCell.length() != 2) {
                    throw new RuntimeException("mac parameters is invalidate");
                }
            }
        }

        return true;
    }

    @Override
    public String[] convertLbsInfo(String mcc, String mnc, String lbsInfo) {
        if (StringUtils.isEmpty(lbsInfo)) {
            return new String[2];
        }

        String[] lbsInfoArray = lbsInfo.split(Const.REGEX_LINE);

        StringBuilder sideLbs = new StringBuilder();
        String mainLbs = null;
        boolean isMainLbs = true;
        StringBuffer infoBuffer;
        for (String info : lbsInfoArray) {
            if (StringUtils.isEmpty(info)) {
                continue;
            }

            String[] properties = info.split(Const.SEP_COMMA);
            if (LBS_PROPERTIES_COUNT > properties.length) {
                continue;
            }

            infoBuffer = new StringBuffer();
            infoBuffer.append(mcc);
            infoBuffer.append(Const.SEP_COMMA);
            infoBuffer.append(mnc);
            infoBuffer.append(Const.SEP_COMMA);

            if (LBS_PROPERTIES_COUNT == properties.length) {
                infoBuffer.append(info);
                infoBuffer.append(Const.SEP_COMMA);
                infoBuffer.append(Const.LBS_DEFAULT_DBM);
            } else {
                int dbm = Integer.valueOf(properties[2]);
                if (dbm > Const.LBS_MAX_DBM || dbm < Const.LBS_MIN_DBM) {
                    properties[2] = Const.LBS_DEFAULT_DBM;
                }
                infoBuffer.append(properties[0]);
                infoBuffer.append(Const.SEP_COMMA);
                infoBuffer.append(properties[1]);
                infoBuffer.append(Const.SEP_COMMA);
                infoBuffer.append(properties[2]);
            }

            if (isMainLbs) {
                mainLbs = infoBuffer.toString();
                isMainLbs = false;
                continue;
            }
            sideLbs.append(infoBuffer.toString());
            sideLbs.append(getSeparator());
        }

        if (StringUtils.isEmpty(mainLbs)) {
            throw new RuntimeException("no valid main lbs info");
        }

        if (sideLbs.length() < 1) {
            return new String[]{mainLbs, ""};
        }

        return new String[]{mainLbs, sideLbs.substring(0, sideLbs.length() - 1)};
    }
}
