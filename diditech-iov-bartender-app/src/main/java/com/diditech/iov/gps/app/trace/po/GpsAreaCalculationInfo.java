package com.diditech.iov.gps.app.trace.po;

import com.diditech.iov.gps.api.trace.entity.CoordinateType;
import com.diditech.iov.gps.api.trace.entity.TraceInfo;
import lombok.Data;

import java.util.List;

/**
 * 轨迹区域计算对象
 * @author zhjd <br>
 * @date 2021/10/19 <br>
 */
@Data
public class GpsAreaCalculationInfo {
    /**
     * 唯一标识
     */
    private String id;
    /**
     * 设备号
     */
    private String deviceNum;
    /**
     * 查询开始时间 yyyyMMddHHmmss
     */
    private String beginTime;
    /**
     * 查询结束时间 yyyyMMddHHmmss
     */
    private String endTime;
    /**
     * 查询进出状态，1进区域0出区域
     */
    private Integer inFlag;
    /**
     * 区域圆心坐标系以及返回坐标系，默认高德/国测，其他需指定，参照 {@link CoordinateType}
     */
    private String coorType = CoordinateType.GCJ02.name();
    /**
     * 圆心坐标经度
     */
    private Double centreLng;
    /**
     * 圆心坐标纬度
     */
    private Double centreLat;
    /**
     * 半径 米
     */
    private Integer radius;

    List<TraceInfo> traceInfos;

    List<double[]> area;
}
