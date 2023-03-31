package com.diditech.iov.gps.app.geo.wifi.service;

import com.diditech.iov.gps.app.geo.common.ILink;

public interface WifiService extends ILink {

    boolean checkParameter(String imei, String macs, String mcc, String mnc, String lbsInfo);

    String[] convertLbsInfo(String mcc, String mnc, String lbsInfo);

}
