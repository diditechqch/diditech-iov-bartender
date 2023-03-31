package com.diditech.iov.gps.app.geo.lbs.service;

import com.diditech.iov.gps.app.geo.common.ILink;

public interface LbsService extends ILink {

    String getCoordinate(String mcc, String mnc, String bts, String nearBts);

}
