package com.diditech.iov.gps.app.gpslog.provider;

import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.gpslog.GpsLogApi;
import com.diditech.iov.gps.api.gpslog.domain.GpsLog;
import com.diditech.iov.gps.app.gpslog.service.GpsLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author zhjd <br>
 * @date 2022/3/11 <br>
 */
@Slf4j
@RestController
public class GpsLogProvider implements GpsLogApi {

    @Autowired
    private GpsLogService gpsLogService;

    @Override
    public ResponseMessage getGpsLog(
            @RequestParam(value = "deviceNum") String deviceNum,
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime) {
        List<GpsLog> gpsLogs = gpsLogService.getGpsLog(deviceNum, beginTime, endTime);
        return ResponseMessage.ok(gpsLogs);
    }
}
