package com.diditech.common.domain;

import java.math.BigDecimal;

import lombok.Data;

/**
 * gps轨迹数据(补传包里程处理)
 * @author hefan
 * @date 2019/7/25 11:05
 */
@Data
public class GpsData {

    private Integer id;
    private String dataType;
    private Integer deviceId;
    private Integer vehicleId;
    private String deviceNum;
    private String createTime;
    private String gpsTime;
    private String lng;
    private String lat;
    private String lngGc;
    private String latGc;
    private String lngBd;
    private String latBd;
    private String deviceType;
    private String timezoneType;
    private Integer timezoneVal;
    private Integer seq;
    private Integer power;
    private Integer signel;
    private Integer planetNum;
    private Integer speed;
    private Integer direction;
    private String directionName;
    private String av;
    private String ns;
    private String ew;
    private String address;
    private String status;
    private String acc;
    private String reportType;
    private String isResend;
    private BigDecimal hardMileage;
    private BigDecimal oriMileage;
    private BigDecimal mileage;
    private BigDecimal todayMileage;
    private BigDecimal totalMileage;
    private BigDecimal oriOil1;
    private BigDecimal oriOil2;
    private BigDecimal oil1;
    private BigDecimal oil2;
    private Integer temperature;
    private BigDecimal obdAcc;
    private BigDecimal obdWt;
    private BigDecimal obdV;
    private BigDecimal obdSpeed;
    private BigDecimal obdRotation;
    private BigDecimal obdAvgoil;
    private BigDecimal obdInsoil;
    private BigDecimal obdMileage;
    private String obdError;
    private String deviceMessage;
    private String obdMessage;

    private Integer timeDif;
    private BigDecimal distance;


}
