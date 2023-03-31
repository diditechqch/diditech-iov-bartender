package com.diditech.iov.gps.app.stop.provider;

import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.stop.StopPointApi;
import com.diditech.iov.gps.app.core.service.CoreService;
import com.diditech.iov.gps.app.stop.service.StopPointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 停留点查询服务提供者类
 * @author zhangquanbo
 * @date 2019/3/12
 */
@Slf4j
@RestController
public class StopPointProvider implements StopPointApi {

    @Autowired
    private CoreService coreService;


    @Autowired
    private StopPointService stopPointService;


    @Override
    public ResponseMessage getStopPointList(@RequestParam(value = "deviceNum") String deviceNum,
                                            @RequestParam(value = "startTime") Date startTime,
                                            @RequestParam(value = "endTime") Date endTime,
                                            @RequestParam(value = "minStaySeconds", required = false, defaultValue = "600") int minStaySeconds) {
        coreService.dateValidation(startTime, endTime);
        return ResponseMessage.ok(stopPointService.getStopPointList(deviceNum, startTime, endTime, minStaySeconds));
    }
}
