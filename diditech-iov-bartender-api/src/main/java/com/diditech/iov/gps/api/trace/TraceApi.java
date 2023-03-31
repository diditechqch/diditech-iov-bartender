package com.diditech.iov.gps.api.trace;

import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.trace.entity.GpsAreaQuery;
import com.diditech.iov.gps.api.trace.entity.TripCalculate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * 轨迹查询
 * @author zhjd <br>
 * @date 2022/3/1 <br>
 */
@RequestMapping("/trace")
public interface TraceApi {

    /**
     * 根据设备号、时间范围查询轨迹
     * @param deviceNum 设备号
     * @param beginTime 开始时间 兼容多种格式
     * @param endTime   结束时间 兼容多种格式
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值类型为 List&lt;{@link com.diditech.iov.gps.api.trace.entity.TraceInfo}&gt;
     * @date 2022/2/14
     * @author zhjd
     */
    @GetMapping
    ResponseMessage getTrace(
            @RequestParam(value = "deviceNum") String deviceNum,
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime);

    /**
     * 根据设备号、时间范围查询一段轨迹的里程
     * @param deviceNum 设备号
     * @param beginTime 开始时间 兼容多种格式
     * @param endTime   结束时间 兼容多种格式
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值类型为double
     * @date 2022/2/14
     * @author zhjd
     */
    @GetMapping("/distance")
    ResponseMessage getTraceDistance(
            @RequestParam(value = "deviceNum") String deviceNum,
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime);

    /**
     * 轨迹分段
     * @param deviceNum         设备
     * @param beginTime         定位时间 开始时间 兼容多种格式
     * @param endTime           定位时间 结束时间 兼容多种格式
     * @param coorType          非必填，坐标系，默认bd09（百度），其他：gcj02（国测），wgs84（原始）
     * @param minTripDistance   非必填，分段最小距离，默认0.01KM
     * @param minNoDataDuration 非必填，Gaps in reported positions longer than the value are considered as stops. Default 900 second.
     * @param includeAddress    非必填，是否需要返回地址，1是0否，默认0
     * @param order             非必填，排序策略，默认0，按gps正序，1按gps倒序
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 List&lt;{@link TripCalculate}&gt;
     * @date 2021/3/2
     * @author zhjd
     */
    @GetMapping("/trip")
    ResponseMessage getTraceTrip(
            @RequestParam(value = "deviceNum") String deviceNum,
            @RequestParam(value = "beginTime") Date beginTime,
            @RequestParam(value = "endTime") Date endTime,
            @RequestParam(value = "coorType", required = false, defaultValue = "bd09") String coorType,
            @RequestParam(value = "minTripDistance ", required = false, defaultValue = "0.01") Double minTripDistance,
            @RequestParam(value = "minNoDataDuration ", required = false, defaultValue = "900") Integer minNoDataDuration,
            @RequestParam(value = "includeAddress", required = false, defaultValue = "0") Integer includeAddress,
            @RequestParam(value = "order", required = false, defaultValue = "0") Integer order);

    /**
     * 根据时间段内轨迹判断是否进出某区域
     * @date 2021/10/19
     * @author zhjd
     */
    @PostMapping("/area")
    ResponseMessage checkInArea(@Valid @RequestBody GpsAreaQuery[] queries);
}
