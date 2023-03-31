package com.diditech.iov.gps.app.trace.srv.impl;

import com.diditech.iov.gps.api.trace.entity.GpsInfoTripMin;
import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.trace.config.GpsCassandraConfig;
import com.diditech.iov.gps.app.trace.po.GpsSearchCondition;
import com.diditech.iov.gps.app.trace.repository.GpsDao;
import com.diditech.iov.gps.app.trace.srv.AbstractTraceDbService;
import dd.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Primary
@Service
@ConditionalOnBean(GpsCassandraConfig.class)
public class CassandraTraceDbServiceImpl extends AbstractTraceDbService {

    private static final int[] TABLE_NAME_INDEX_RANG_GPS_TIME = new int[]{0, 4};

    @Autowired
    private GpsDao mapper;

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

        List<GpsInfoTripMin> traceList = new ArrayList<>();
        if (mapper.countGpsData(gpsTableConditionsList) != 0) {
            traceList = mapper.getTraceTripMin(gpsTableConditionsList, coorType);
        }

        traceList.parallelStream().forEach(item -> {
            if (StringUtils.isEmpty(item.getSpeed())) {
                item.setSpeed(0);
            }
        });

        return traceList;
    }

}
