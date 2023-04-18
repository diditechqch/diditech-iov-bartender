package com.diditech.iov.gps.api.report;

import com.diditech.iov.gps.api.core.Page;
import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.report.domain.ReportGpsData;
import com.diditech.iov.gps.api.report.domain.ReportStopData;
import com.diditech.iov.gps.api.report.domain.ReportTripsData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * 报表查询
 * @author zhjd <br>
 * @date 2023/3/13 <br>
 */
@RequestMapping("/report")
public interface ReportApi {

    /**
     * 行驶统计报表<br>
     * 查询多设备行驶统计报表，以车辆开始行驶到停车为一条行驶数据并统计里程、时长、油耗、速度等数据
     * @param beginTime   定位时间 开始时间 兼容多种格式
     * @param endTime     定位时间 结束时间 兼容多种格式
     * @param coorType    非必填，坐标系，默认bd09（百度），其他：gcj02（国测），wgs84（原始）
     * @param minDuration 非必填，最小行驶时长，单位分钟，默认/最小值1分钟
     * @param minDistance 非必填，最小行驶距离，单位km，默认/最小值0.2km
     * @param pageSize    非必填，分页查询，每页数据条数，默认不带分页
     * @param pageNo      非必填，分页查询，页数，默认不带分页
     * @param devices     设备号，取自body中文本数据，多个设备使用逗号分隔
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值类型为{@link Page Page}&lt;{@link ReportTripsData ReportTripsData}&gt;
     * @date 2023/3/16
     * @author zhjd
     */
    @PostMapping("/trips")
    ResponseMessage getTripsReport(
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime,
            @RequestParam(value = "coorType", required = false, defaultValue = "bd09") String coorType,
            @RequestParam(value = "minDuration", required = false, defaultValue = "1") Integer minDuration,
            @RequestParam(value = "minDistance", required = false, defaultValue = "0.2") Double minDistance,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestBody String devices);

    /**
     * 点火统计报表<br>
     * 查询多设备点火统计报表，以车辆点火到熄火为一条行驶数据并统计里程、时长、油耗、速度等数据
     * @param beginTime   定位时间 开始时间 兼容多种格式
     * @param endTime     定位时间 结束时间 兼容多种格式
     * @param coorType    非必填，坐标系，默认bd09（百度），其他：gcj02（国测），wgs84（原始）
     * @param minDuration 非必填，最小行驶时长，单位分钟，默认/最小值1分钟
     * @param minDistance 非必填，最小行驶距离，单位km，默认/最小值0.2km
     * @param pageSize    非必填，分页查询，每页数据条数，默认不带分页
     * @param pageNo      非必填，分页查询，页数，默认不带分页
     * @param devices     设备号，取自body中文本数据，多个设备使用逗号分隔
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值类型为{@link Page Page}&lt;{@link ReportTripsData ReportTripsData}&gt;
     * @date 2023/3/16
     * @author zhjd
     */
    @PostMapping("/acc")
    ResponseMessage getAccReport(
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime,
            @RequestParam(value = "coorType", required = false, defaultValue = "bd09") String coorType,
            @RequestParam(value = "minDuration", required = false, defaultValue = "1") Integer minDuration,
            @RequestParam(value = "minDistance", required = false, defaultValue = "0.2") Double minDistance,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestBody String devices);


    /**
     * 停车统计报表<br>
     * 查询多设备停车统计报表，以车辆开始停车到行驶为一条行驶数据并统计时长、位置
     * @param beginTime   定位时间 开始时间 兼容多种格式
     * @param endTime     定位时间 结束时间 兼容多种格式
     * @param coorType    非必填，坐标系，默认bd09（百度），其他：gcj02（国测），wgs84（原始）
     * @param minDuration 非必填，最小停车时长，单位分钟，默认/最小值15分钟
     * @param pageSize    非必填，分页查询，每页数据条数，默认不带分页
     * @param pageNo      非必填，分页查询，页数，默认不带分页
     * @param devices     设备号，取自body中文本数据，多个设备使用逗号分隔
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值类型为{@link Page Page}&lt;{@link ReportStopData ReportStopData}&gt;
     * @date 2023/3/16
     * @author zhjd
     */
    @PostMapping("/stops")
    ResponseMessage getStopsReport(
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime,
            @RequestParam(value = "coorType", required = false, defaultValue = "bd09") String coorType,
            @RequestParam(value = "minDuration", required = false, defaultValue = "15") Integer minDuration,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestBody String devices);

    /**
     * 里程统计报表<br>
     * 查询多设备里程统计报表
     * @param beginTime 定位时间 开始时间 兼容多种格式
     * @param endTime   定位时间 结束时间 兼容多种格式
     * @param coorType  非必填，坐标系，默认bd09（百度），其他：gcj02（国测），wgs84（原始）
     * @param pageSize  非必填，分页查询，每页数据条数，默认不带分页
     * @param pageNo    非必填，分页查询，页数，默认不带分页
     * @param devices   设备号，取自body中文本数据，多个设备使用逗号分隔
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值类型为{@link Page Page}&lt;{@link ReportGpsData ReportGpsData}&gt;
     * @date 2023/3/16
     * @author zhjd
     */
    @PostMapping("/mileage")
    ResponseMessage getMileageReport(
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime,
            @RequestParam(value = "coorType", required = false, defaultValue = "bd09") String coorType,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestBody String devices);

    /**
     * 日里程查询<br>
     * 返回设备按天统计日里程数据，若某日无上报则无该日数据
     * @param beginTime 定位时间 开始时间 yyyy-MM-dd
     * @param endTime   定位时间 结束时间 yyyy-MM-dd
     * @param coorType  非必填，坐标系，默认bd09（百度），其他：gcj02（国测），wgs84（原始）
     * @param pageSize  非必填，分页查询，每页数据条数，默认不带分页
     * @param pageNo    非必填，分页查询，页数，默认不带分页
     * @param devices   设备号，取自body中文本数据，多个设备使用逗号分隔
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值类型为{@link Page Page}&lt;{@link ReportGpsData ReportGpsData}&gt;
     * @date 2023/3/16
     * @author zhjd
     */
    @PostMapping("/mileage/day")
    ResponseMessage getMileageDayReport(
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime,
            @RequestParam(value = "coorType", required = false, defaultValue = "bd09") String coorType,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo,
            @RequestBody String devices);
}
