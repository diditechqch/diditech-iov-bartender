package com.diditech.iov.gps.app.report.provider;

import cn.hutool.core.collection.CollUtil;
import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.report.ReportApi;
import com.diditech.iov.gps.api.report.domain.ReportGpsData;
import com.diditech.iov.gps.api.report.domain.ReportStopData;
import com.diditech.iov.gps.api.report.domain.ReportTripsData;
import com.diditech.iov.gps.app.core.service.CoreService;
import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.report.srv.ReportGpsService;
import com.diditech.iov.gps.app.report.srv.ReportStopsService;
import com.diditech.iov.gps.app.report.srv.ReportTripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author zhjd <br>
 * @date 2023/3/13 <br>
 */
@RestController
public class ReportProvider implements ReportApi {

    @Autowired
    private ReportTripsService tripsService;

    @Autowired
    private ReportStopsService stopsService;

    @Autowired
    private ReportGpsService gpsService;

    @Autowired
    private CoreService coreService;

    @Override
    public ResponseMessage getTripsReport(
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime,
            @RequestParam(value = "coorType", required = false, defaultValue = "bd09") String coorType,
            @RequestParam(value = "minDuration", required = false, defaultValue = "1") Integer minDuration,
            @RequestParam(value = "minDistance", required = false, defaultValue = "0.2") Double minDistance,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestBody String devices) {
        minDuration = minDuration < 1 ? 1 : minDuration;
        minDistance = minDistance < 0.2 ? 0.2 : minDistance;

        List<String> deviceNumList = Arrays.asList(devices.split(Const.SEP_COMMA));
        List<ReportTripsData> tripList
                = tripsService.getReport(deviceNumList, beginTime, endTime,
                coorType, minDuration, minDistance);

        if (CollUtil.isEmpty(tripList)) {
            return ResponseMessage.ok();
        }

        Comparator<ReportTripsData> comparator = (o1, o2) ->
                o1.getDeviceNum().compareTo(o2.getDeviceNum()) + o1.getStartTime().compareTo(o2.getStartTime());
        if (pageSize != null && pageNo != null) {
            return ResponseMessage.ok(
                    coreService.getPaged(tripList, comparator, pageSize, pageNo));
        }
        tripList.sort(comparator);
        return ResponseMessage.ok(tripList);
    }

    @Override
    public ResponseMessage getStopsReport(
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime,
            @RequestParam(value = "coorType", required = false, defaultValue = "bd09") String coorType,
            @RequestParam(value = "minDuration", required = false, defaultValue = "15") Integer minDuration,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestBody String devices) {
        minDuration = minDuration < 15 ? 15 : minDuration;

        List<String> deviceNumList = Arrays.asList(devices.split(Const.SEP_COMMA));
        List<ReportStopData> stops
                = stopsService.getReport(deviceNumList, beginTime, endTime,
                coorType, minDuration, null);
        if (CollUtil.isEmpty(stops)) {
            return ResponseMessage.ok();
        }

        Comparator<ReportStopData> comparator = (o1, o2) ->
                o1.getDeviceNum().compareTo(o2.getDeviceNum()) + o1.getStartTime().compareTo(o2.getStartTime());
        if (pageSize != null && pageNo != null) {
            return ResponseMessage.ok(
                    coreService.getPaged(stops, comparator, pageSize, pageNo));
        }
        stops.sort(comparator);
        return ResponseMessage.ok(stops);
    }

    @Override
    public ResponseMessage getMileageReport(
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime,
            @RequestParam(value = "coorType", required = false, defaultValue = "bd09") String coorType,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestBody String devices) {
        List<ReportGpsData> list = gpsService.getReport(
                Arrays.asList(devices.split(Const.SEP_COMMA)),
                beginTime, endTime, coorType);
        if (CollUtil.isEmpty(list)) {
            return ResponseMessage.ok();
        }

        Comparator<ReportGpsData> comparator = Comparator.comparing(ReportGpsData::getDeviceNum);
        if (pageSize != null && pageNo != null) {
            return ResponseMessage.ok(
                    coreService.getPaged(list, comparator, pageSize, pageNo));
        }
        list.sort(comparator);
        return ResponseMessage.ok(list);
    }

    @Override
    public ResponseMessage getMileageDayReport(
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime,
            @RequestParam(value = "coorType", required = false, defaultValue = "bd09") String coorType,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestBody String devices) {
        List<ReportGpsData> list = gpsService.getDayReport(
                Arrays.asList(devices.split(Const.SEP_COMMA)),
                beginTime, endTime, coorType);
        if (CollUtil.isEmpty(list)) {
            return ResponseMessage.ok();
        }
        Comparator<ReportGpsData> comparator = (o1, o2) ->
                o1.getDeviceNum().compareTo(o2.getDeviceNum()) + o1.getDayTag().compareTo(o2.getDayTag());
        if (pageSize != null && pageNo != null) {
            return ResponseMessage.ok(
                    coreService.getPaged(list, comparator, pageSize, pageNo));
        }
        list.sort(comparator);
        return ResponseMessage.ok(list);
    }


}
