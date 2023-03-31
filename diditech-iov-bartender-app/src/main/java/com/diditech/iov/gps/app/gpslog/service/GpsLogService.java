package com.diditech.iov.gps.app.gpslog.service;

import com.diditech.iov.gps.api.gpslog.domain.GpsLog;

import java.util.Date;
import java.util.List;

/**
 * @author zhjd <br>
 * @date 2022/3/11 <br>
 */
public interface GpsLogService {

    List<GpsLog> getGpsLog(String deviceNum, Date beginTime, Date endTime);
}
