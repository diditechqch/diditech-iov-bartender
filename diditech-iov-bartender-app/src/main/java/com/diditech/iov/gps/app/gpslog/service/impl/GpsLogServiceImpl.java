package com.diditech.iov.gps.app.gpslog.service.impl;

import com.diditech.iov.gps.api.gpslog.domain.GpsLog;
import com.diditech.iov.gps.app.gpslog.repository.GpsLogMapper;
import com.diditech.iov.gps.app.gpslog.service.GpsLogService;
import dd.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhjd <br>
 * @date 2022/3/11 <br>
 */
@Service
public class GpsLogServiceImpl implements GpsLogService {

    @Value("${gpslog.table.schema}")
    private String tableSchema;

    @Value("${gpslog.table.prefix}")
    private String tablePrefix;

    @Value("${gpslog.table.format}")
    private String tableFormat;

    @Autowired
    private GpsLogMapper gpsLogMapper;

    @Override
    public List<GpsLog> getGpsLog(String deviceNum, Date beginTime, Date endTime) {
        String beginTableName = tablePrefix + Util.asString(beginTime, tableFormat);
        String endTableName = tablePrefix + Util.asString(endTime, tableFormat);
        List<String> tableNameList = gpsLogMapper.loadGpsLogTableListFromDb(tableSchema, beginTableName, endTableName);
        if (Util.isEmpty(tableNameList)) {
            return null;
        }
        List<GpsLog> gpsLogList = tableNameList.parallelStream()
                .map(item -> gpsLogMapper.getGpsLogList(deviceNum,
                        Util.asString(beginTime, Util.STR_SHORT_DT_FORMAT),
                        Util.asString(endTime, Util.STR_SHORT_DT_FORMAT),
                        item))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        if (Util.isEmpty(gpsLogList)) {
            return new ArrayList<>();
        }

        gpsLogList.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
        return gpsLogList;
    }
}
