package com.diditech.iov.gps.app.trace.srv.impl;

import com.diditech.iov.gps.api.trace.entity.CoordinateType;
import com.diditech.iov.gps.api.trace.entity.GpsInfoTripMin;
import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.trace.config.GpsDruidDBConfig;
import com.diditech.iov.gps.app.trace.po.GpsSearchCondition;
import com.diditech.iov.gps.app.trace.repository.GpsDao;
import com.diditech.iov.gps.app.trace.repository.impl.GpsMysqlMapper;
import com.diditech.iov.gps.app.trace.srv.AbstractTraceDbService;
import dd.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@ConditionalOnBean(GpsDruidDBConfig.class)
public class MysqlTraceDbServiceImpl extends AbstractTraceDbService {

    private static final int[] TABLE_NAME_INDEX_RANG_GPS_TIME = new int[]{0, 6};

    @Autowired(required = false)
    private GpsMysqlMapper mapper;

    @Override
    public GpsDao getMapper() {
        return mapper;
    }

    @Override
    public String createTableNameByGpsTime(String gpsTime) {
        return Const.GPS_TABLE_PREFIX
                .concat(gpsTime.substring(TABLE_NAME_INDEX_RANG_GPS_TIME[0], TABLE_NAME_INDEX_RANG_GPS_TIME[1]));
    }

    @Override
    public List<GpsInfoTripMin> getTraceTripMin(String deviceNum,
                                                Date beginTime,
                                                Date endTime,
                                                String coorType) {
        List<GpsSearchCondition> gpsTableConditionsList = getSearchCondition(deviceNum, beginTime, endTime);
        if (Util.isEmpty(gpsTableConditionsList)) {
            return new ArrayList<>();
        }

        List<GpsInfoTripMin> basicGpsData = new ArrayList<>();
        if (mapper.countGpsData(gpsTableConditionsList) != 0) {
            basicGpsData = mapper.getTraceTripMin(gpsTableConditionsList, getSqlColumn(coorType));
        }

        return basicGpsData;
    }

    private String getSqlColumn(String coorType) {
        switch (CoordinateType.get(coorType)) {
            case GCJ02:
                return " LNG_GC AS lng,\n LAT_GC AS lat,\n";
            case WGS84:
                return " LNG AS lng,\n LAT AS lat,\n";
            default:
                return " LNG_BD AS lng,\n LAT_BD AS lat,\n";
        }
    }

}
