package com.diditech.iov.gps.app.trace.srv;

import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.trace.entity.*;
import com.diditech.iov.gps.app.trace.po.GpsEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TraceService {

    /**
     * 根据设备号、时间范围查询轨迹
     * @param deviceNum 设备号
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 返回对象 List&lt;{@link TraceInfo}&gt;
     * @date 2022/2/14
     * @author zhjd
     */
    List<TraceInfo> getTrace(String deviceNum,
                             Date beginTime,
                             Date endTime);

    /**
     * 根据时间范围查询轨迹做分段计算，不限制开始结束时间，当查询时间范围不同，分段结果可能不同，<br>
     * 例如，当实际分段跨天但仅查询当天，则分段从当天0点开始，当查询时间包含完整分段则返回完整数据。<br>
     * @param deviceNum         设备号
     * @param beginTime         定位时间 开始时间
     * @param endTime           定位时间 结束时间
     * @param coorType          非必填，坐标系，默认bd09（百度），其他：gcj02（国测），wgs84（原始）
     * @param minNoDataDuration 非必填，Gaps in reported positions longer than the value are considered as stops. Default 900 second.
     * @param minTripDistance   非必填，分段最小距离，默认0.01KM
     * @param includeAddress    非必填，是否解析地址
     * @param order             非必填，排序，默认按时间正序
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 List&lt;{@link TripGps}&gt;
     * @date 2021/3/2
     * @author zhjd
     */
    List getTrip(String deviceNum,
                 Date beginTime,
                 Date endTime,
                 String coorType,
                 Integer minNoDataDuration,
                 Double minTripDistance,
                 Integer includeAddress,
                 Integer order);

    /**
     * 使用TraceTripRequest.Builder灵活构建查询条件
     * @date 2023/3/17
     * @author zhjd
     */
    List<TripGps> getTrip(TraceRequest request);

    List<TripAcc> getAcc(TraceRequest request);

    /**
     * 根据轨迹获取首次进/出区域的定位时间
     * @param queries 查询对象
     * @return Map&lt;String, Date&gt; key为查询对象中唯一ID，value为首次进/出区域的定位时间
     * @date 2021/10/19
     * @author zhjd
     */
    Map<String, Date> calculateGpsAreaInfo(GpsAreaQuery[] queries);

    BigDecimal getTripDistance(List<GpsInfoTripMin> oneTrip);

    /**
     * 计算轨迹里程
     * @date 2023/3/16
     * @author zhjd
     */
    BigDecimal getTraceDistance(String deviceNum, Date beginTime, Date endTime);

    /**
     * 查询轨迹
     * @date 2023/3/16
     * @author zhjd
     */
    GpsEntity getGpsEntity(String deviceNum, Date gpsTime);

    /**
     * 使用TraceTripRequest.Builder灵活构建查询条件
     * @date 2023/3/17
     * @author zhjd
     */
    GpsEntity getGpsEntity(TraceRequest request);

    List<GpsInfoTripMin> getTraceTripMin(String deviceNum, Date beginTime, Date endTime, String coorType);

}
