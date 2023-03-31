package com.diditech.iov.gps.app.trace.repository;

import com.diditech.iov.gps.api.trace.entity.GpsInfoTripMin;
import com.diditech.iov.gps.api.trace.entity.TraceInfo;
import com.diditech.iov.gps.app.trace.po.GpsEntity;
import com.diditech.iov.gps.app.trace.po.GpsSearchCondition;

import java.util.List;

public interface GpsDao {

    int countGpsData(List<GpsSearchCondition> gpsTableConditionsList);

    List<String> loadGpsTableListFromDb(String beginTableName, String endTableName);

    List<TraceInfo> getTrace(List<GpsSearchCondition> conditionList);

    List<GpsInfoTripMin> getTraceTripMin(List<GpsSearchCondition> gpsTableConditionsList, String coorType);

    GpsEntity getGpsEntity(GpsSearchCondition conditionList);
}
