package com.diditech.iov.gps.app.trace.srv;

import com.diditech.iov.gps.api.trace.entity.GpsInfoTripMin;
import com.diditech.iov.gps.api.trace.entity.TraceInfo;
import com.diditech.iov.gps.app.trace.po.GpsEntity;
import com.diditech.iov.gps.app.trace.repository.GpsDao;

import java.util.Date;
import java.util.List;

/**
 * 轨迹查库-接口类
 * @author zhjd
 * @date 2022/3/1
 */
public interface TraceDbService {

    String conv2DbTime(Date pageTime);

    String createTableNameByGpsTime(String gpsTime);

    GpsDao getMapper();

    List<TraceInfo> getTrace(String deviceNum, Date beginTime, Date endTime);

    List<GpsInfoTripMin> getTraceTripMin(String deviceNum, Date beginTime, Date endTime, String coorType);

    GpsEntity getGpsEntity(String deviceNum, Date gpsTime);
}
