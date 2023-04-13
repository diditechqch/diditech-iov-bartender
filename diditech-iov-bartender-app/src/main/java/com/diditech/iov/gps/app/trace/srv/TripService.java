package com.diditech.iov.gps.app.trace.srv;

import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.trace.entity.TripBase;
import com.diditech.iov.gps.api.trace.entity.TripGps;

import java.util.Date;
import java.util.List;

/**
 * 分段服务接口类
 * @author zhjd <br>
 * @date 2023/4/13 <br>
 */
public interface TripService<T extends TripBase> {

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
    List<T> getTrip(String deviceNum,
                    Date beginTime,
                    Date endTime,
                    String coorType,
                    Integer minNoDataDuration,
                    Double minTripDistance,
                    Integer includeAddress,
                    Integer order);
}
