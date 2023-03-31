package com.diditech.iov.gps.api.report.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * 行车报表数据
 * @author zhjd <br>
 * @date 2023/3/13 <br>
 */
@Data
public class ReportTripsData extends ReportTripsDataBase {

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 行驶时长，分钟
     */
    private long duration;

    /**
     * 行驶距离，里程，km
     */
    private double distance;
    /**
     * 最大速度km/h
     */
    private int speedMax;
    /**
     * 平均速度km/h
     */
    private int speedAve;
    /**
     * 油耗（单位毫升）
     */
    private double fuelConsumption;
    /**
     * 起点坐标
     */
    private double startLng;
    /**
     * 起点坐标
     */
    private double startLat;
    /**
     * 终点坐标
     */
    private double endLng;
    /**
     * 终点坐标
     */
    private double endLat;
    /**
     * 终点地址
     */
    private String startAddress;
    /**
     * 终点地址
     */
    private String endAddress;
}
