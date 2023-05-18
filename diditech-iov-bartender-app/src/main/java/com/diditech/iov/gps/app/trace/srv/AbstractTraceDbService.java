package com.diditech.iov.gps.app.trace.srv;

import com.diditech.iov.gps.api.trace.entity.TraceInfo;
import com.diditech.iov.gps.app.trace.po.GpsEntity;
import com.diditech.iov.gps.app.trace.po.GpsSearchCondition;
import dd.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 轨迹查库-抽象类
 * @author zhjd <br>
 * @date 2022/3/1 <br>
 */
@Slf4j
public abstract class AbstractTraceDbService implements TraceDbService {

    protected String[] checkAndCovPageTime(Date beginTime, Date endTime) {
        String begin = conv2DbTime(beginTime);
        String end = conv2DbTime(endTime);

        if (end.compareTo(begin) < 0) {
            throw new RuntimeException("the endtime must be greater than the begintime;\r\n");
        }
        return new String[]{begin, end};
    }

    protected List<String> getTableList(String[] timePair) {
        return getMapper().loadGpsTableListFromDb(
                createTableNameByGpsTime(timePair[0]),
                createTableNameByGpsTime(timePair[1]));
    }

    protected List<GpsSearchCondition> getSearchCondition(String deviceNum, Date beginTime, Date endTime) {
        String[] timePair = checkAndCovPageTime(beginTime, endTime);

        List<String> gpsTableList = getTableList(timePair);
        if (Util.isEmpty(gpsTableList)) {
            return new ArrayList<>();
        }

        List<GpsSearchCondition> gpsTableConditionsList = new ArrayList<>();
        for (String tableName : gpsTableList) {
            GpsSearchCondition c = new GpsSearchCondition();
            c.setGpsTable(tableName);
            c.setDeviceNum(deviceNum);
            c.setBeginTime(timePair[0]);
            c.setEndTime(timePair[1]);
            gpsTableConditionsList.add(c);
        }
        return gpsTableConditionsList;
    }

    @Override
    public List<TraceInfo> getTrace(String deviceNum, Date beginTime, Date endTime) {
        List<GpsSearchCondition> gpsTableConditionsList = getSearchCondition(deviceNum, beginTime, endTime);
        if (Util.isEmpty(gpsTableConditionsList)) {
            return new ArrayList<>();
        }

        List<TraceInfo> traceList = new ArrayList<>();
        if (getMapper().countGpsData(gpsTableConditionsList) != 0) {
            traceList = getMapper().getTrace(gpsTableConditionsList);
        }

        traceList.parallelStream().forEach(item -> {
            if (StringUtils.isEmpty(item.getSpeed())) {
                item.setSpeed("0");
            }
            if (StringUtils.isEmpty(item.getLocMode())) {
                item.fillLocMode();
            }
            if (StringUtils.isEmpty(item.getSpeedType())) {
                item.fillSpeedData();
            }
        });

        return traceList;
    }

    @Override
    public String conv2DbTime(Date pageTime) {
        if (StringUtils.isEmpty(pageTime)) {
            log.trace("pageTime is empty");
            return "";
        }
        return Util.asString(pageTime, Util.STR_SHORT_DT_FORMAT);
    }

    @Override
    public GpsEntity getGpsEntity(String deviceNum, Date gpsTime) {
        List<GpsSearchCondition> conditions = getSearchCondition(deviceNum, gpsTime, gpsTime);
        if (Util.isEmpty(conditions)) {
            return null;
        }
        return getMapper().getGpsEntity(conditions.get(0));
    }

}
