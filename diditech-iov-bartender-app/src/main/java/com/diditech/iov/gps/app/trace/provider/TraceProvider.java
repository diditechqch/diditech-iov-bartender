package com.diditech.iov.gps.app.trace.provider;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.trace.TraceApi;
import com.diditech.iov.gps.api.trace.entity.GpsAreaQuery;
import com.diditech.iov.gps.api.trace.entity.TripCalculate;
import com.diditech.iov.gps.app.core.aspect.RequestHelper;
import com.diditech.iov.gps.app.core.service.CoreService;
import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.device.service.DeviceService;
import com.diditech.iov.gps.app.trace.srv.TraceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 轨迹查询实体类
 * @author zhjd
 * @date 2023/3/8
 */
@Slf4j
@RestController
public class TraceProvider implements TraceApi {

    @Autowired
    private TraceService traceService;

    @Autowired
    private CoreService coreService;

    @Autowired
    private DeviceService deviceService;

    @Override
    public ResponseMessage getTrace(
            @RequestParam(value = "deviceNum") String deviceNum,
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime) {
        return ResponseMessage.ok(traceService.getTrace(deviceNum, beginTime, endTime));
    }

    @Override
    public ResponseMessage getTraceDistance(
            @RequestParam(value = "deviceNum") String deviceNum,
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime) {
        return ResponseMessage.ok(traceService.getTraceDistance(deviceNum, beginTime, endTime));
    }

    @Override
    public ResponseMessage getTraceTrip(
            @RequestParam(value = "deviceNum") String deviceNum,
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime,
            @RequestParam(value = "coorType", required = false, defaultValue = "bd09") String coorType,
            @RequestParam(value = "minTripDistance ", required = false, defaultValue = "0.01") Double minTripDistance,
            @RequestParam(value = "minNoDataDuration ", required = false, defaultValue = "900") Integer minNoDataDuration,
            @RequestParam(value = "includeAddress", required = false, defaultValue = "0") Integer includeAddress,
            @RequestParam(value = "order", required = false, defaultValue = "0") Integer order) {
        List<TripCalculate> trips = traceService.getTraceTrip(deviceNum, beginTime, endTime,
                coorType, minNoDataDuration, minTripDistance, includeAddress, order);
        if (CollUtil.isEmpty(trips)) {
            return ResponseMessage.ok();
        }
        return ResponseMessage.ok(trips);
    }

    @Override
    public ResponseMessage getTraceTripAcc(
            @RequestParam(value = "deviceNum") String deviceNum,
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime,
            @RequestParam(value = "coorType", required = false, defaultValue = "bd09") String coorType,
            @RequestParam(value = "includeAddress", required = false, defaultValue = "0") Integer includeAddress,
            @RequestParam(value = "order", required = false, defaultValue = "0") Integer order) {
        return ResponseMessage.ok();
    }

    @Override
    public ResponseMessage checkInArea(@Valid @RequestBody GpsAreaQuery[] queries) {
        // add by zhjd 20220512 start
        String invalidNum = deviceService.getInvalidDeviceNum(RequestHelper.getClientId(),
                Arrays.stream(queries).map(GpsAreaQuery::getDeviceNum).toArray(String[]::new));
        if (StrUtil.isNotBlank(invalidNum)) {
            return ResponseMessage.error("设备号不存在或无权限：" + invalidNum);
        }
        // add by zhjd 20220512 end
        for (GpsAreaQuery query : queries) {
            coreService.dateValidation(query.getBeginTime(), query.getEndTime(),
                    Const.DATE_FORMAT_GPS_TABLE, null);
        }
        if (queries == null || queries.length < 1) {
            return ResponseMessage.error("查询对象为空");
        }
        return ResponseMessage.ok(traceService.calculateGpsAreaInfo(queries));
    }

}
