package com.diditech.iov.gps.common.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * 用于更新position表的对象
 * @author zhjd
 * @date 2020-06-29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionInfo implements Serializable {
    static final long serialVersionUID = 1L;

    private String deviceNum;
    private Date createTime;
    private Date gpsTime;
    private Date startTimeAcc;
    private Date startTimeAv;
    private Date startTimeCutPower;
    private Date startTimeCutWire;
    private Date startTimeLowPower;
    private Date startTimeMovingStop;
    private Date startTimeObdFault;
    private Date startTimeOnOffLine;
    private Date startTimeOverSpeed;
    private Date startTimePbs;
    private Date startTimeSleep;
    private Date startTimeSos;
    private Double lat;
    private Double latBd;
    private Double latGc;
    private Double lng;
    private Double lngBd;
    private Double lngGc;
    private Double obdFuel;
    private Double obdMileage;
    private Double totalMileage;
    private Double hardMileage;
    private Integer almAccOn;
    private Integer almCutPower;
    private Integer almCutWire;
    private Integer almLowPower;
    private Integer almObdFault;
    private Integer almOffline;
    private Integer almOverSpeed;
    private Integer almPseudoStation;
    private Integer almSos;
    private Integer isCharging;
    private Integer isMoving;
    private Integer isSleeping;
    private Integer locMode;
    private Integer obdInsOil;
    private Integer obdOil;
    private Integer obdRotation;
    private Integer obdSpeed;
    private Integer obdV;
    private Integer obdWt;
    private Integer power;
    private Integer satCount;
    private Integer signalLevel;
    private Integer wifiRadius;
    private Integer direction;
    private Integer speed;
    private String address;
    private String av;
    private String city;
    private String directionName;
    private String ew;
    private String ns;
    private String status;
}
