package com.diditech.iov.gps.api.trace.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhjd <br>
 * @date 2023/4/13 <br>
 */
@ToString
@Data
@NoArgsConstructor
public class TripBase {
    /**
     * 日里程，单位米
     */
    private BigDecimal mileage;
    /**
     * 均速，单位km/h，1位小数
     */
    private BigDecimal aveSpeed;
    /**
     * 极速，单位km/h，1位小数
     */
    private BigDecimal maxSpeed;
    /**
     * 出发时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 停止时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 出发位置，坐标系根据查询条件而定
     */
    private double startLng;
    /**
     * 出发位置，坐标系根据查询条件而定
     */
    private double startLat;
    /**
     * 停止位置，坐标系根据查询条件而定
     */
    private double endLng;
    /**
     * 停止位置，坐标系根据查询条件而定
     */
    private double endLat;
    /**
     * 分段的轨迹点个数
     */
    private int gpsNumber;

    /**
     * 分段的开始轨迹点
     */
    @JSONField(serialize = false)
    private GpsInfoTripMin startGps;

    /**
     * 分段的结束轨迹点
     */
    @JSONField(serialize = false)
    private GpsInfoTripMin endGps;


    @JSONField(serialize = false)
    private String deviceNum;

    /**
     * 分段起始点地址
     */
    private String startAddress;
    /**
     * 分段终止点地址
     */
    private String endAddress;

    /**
     * 油耗（单位毫升）
     */
    private int fuelConsumption;
}
