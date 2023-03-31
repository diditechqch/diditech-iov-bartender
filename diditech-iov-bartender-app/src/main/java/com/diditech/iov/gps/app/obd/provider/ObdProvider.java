package com.diditech.iov.gps.app.obd.provider;

import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.obd.ObdApi;
import com.diditech.iov.gps.app.core.po.TimeFrameQuery;
import com.diditech.iov.gps.app.core.service.CoreService;
import com.diditech.iov.gps.app.obd.service.ObdService;
import dd.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ObdProvider implements ObdApi {

    @Autowired
    private CoreService coreService;

    @Autowired
    private ObdService obdService;

    @Override
    public ResponseMessage getObdFaultList(@RequestParam(value = "deviceNum") String deviceNum,
                                           @RequestParam(value = "vehicleBrand", required = false) String vehicleBrand) {
        return ResponseMessage.ok(
                obdService.getObdFaultList(deviceNum, vehicleBrand));
    }

    @Override
    public ResponseMessage getObdData(@RequestParam(value = "deviceNum") String deviceNum) {
        return ResponseMessage.ok(obdService.getObdData(deviceNum));
    }

    @Override
    public ResponseMessage getObdFuel(@RequestParam(value = "deviceNum") String deviceNum,
                                      @RequestParam(value = "startTime") Date startTime,
                                      @RequestParam(value = "endTime") Date endTime) {
        coreService.dateValidation(startTime, endTime);
        Integer obdFuels = obdService.getObdFuel(deviceNum, startTime, endTime);
        return ResponseMessage.ok(obdFuels);
    }

    @Override
    public ResponseMessage getObdFuelMulti(@RequestParam(value = "deviceNum") String deviceNum,
                                           @RequestParam(value = "timeFrames") String timeFrames) {
        String[] timeFramesItems = timeFrames.split("\\|");
        if (timeFramesItems.length < 1) {
            return ResponseMessage.error("缺少查询时间");
        }
        Date startTime;
        Date endTime;
        TimeFrameQuery query;
        List<TimeFrameQuery> queries = new ArrayList<>();
        for (String item : timeFramesItems) {
            String[] dateStrings = item.split(",");
            if (dateStrings.length != 2) {
                continue;
            }
            startTime = Util.asDate(dateStrings[0], Util.STR_YYYY_MM_DD_HH_MM_SS);
            endTime = Util.asDate(dateStrings[1], Util.STR_YYYY_MM_DD_HH_MM_SS);
            coreService.dateValidation(startTime, endTime);
            query = new TimeFrameQuery();
            query.setStartTime(startTime);
            query.setEndTime(endTime);
            queries.add(query);
        }

        Integer[] obdFuels = obdService.getObdFuelMulti(deviceNum, queries);

        return ResponseMessage.ok(obdFuels);
    }
}
