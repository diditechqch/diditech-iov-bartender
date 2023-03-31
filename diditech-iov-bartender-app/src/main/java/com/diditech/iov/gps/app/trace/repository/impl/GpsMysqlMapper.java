package com.diditech.iov.gps.app.trace.repository.impl;

import com.diditech.iov.gps.api.trace.entity.GpsInfoTripMin;
import com.diditech.iov.gps.api.trace.entity.TraceInfo;
import com.diditech.iov.gps.app.trace.config.GpsCassandraConfig;
import com.diditech.iov.gps.app.trace.po.GpsEntity;
import com.diditech.iov.gps.app.trace.po.GpsSearchCondition;
import com.diditech.iov.gps.app.trace.repository.GpsDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.util.List;

@ConditionalOnMissingBean(GpsCassandraConfig.class)
public interface GpsMysqlMapper extends GpsDao {

    int countGpsData(@Param("gpsTableConditionsList") List<GpsSearchCondition> gpsTableConditionsList);

    List<String> loadGpsTableListFromDb(@Param("beginTableName") String beginTableName, @Param("endTableName") String endTableName);

    List<TraceInfo> getTrace(@Param("gpsTableConditionsList") List<GpsSearchCondition> gpsTableConditionsList);

    List<GpsInfoTripMin> getTraceTripMin(@Param("gpsTableConditionsList") List<GpsSearchCondition> gpsTableConditionsList,
                                         @Param("coordinate") String coordinate);

    GpsEntity getGpsEntity(@Param("condition") GpsSearchCondition condition);
}
