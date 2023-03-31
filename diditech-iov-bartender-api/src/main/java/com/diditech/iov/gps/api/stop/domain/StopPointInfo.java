package com.diditech.iov.gps.api.stop.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 停留点实体类
 *
 * @auth zhaist
 * @date 2021/07/02
 */
@Data
public class StopPointInfo implements Serializable {

    private static final long serialVersionUID = -526048465462441774L;

    /**
     * 停留开始时间
     */
    private String beginTime;

    /**
     * 停留结束时间
     */
    private String endTime;

    /**
     * 离开时间（停留结束时间）
     */
    private int totalSecond;

    /**
     * 百度坐标经纬度信息
     */
    private double lngBd;

    /**
     * 百度坐标经纬度信息
     */
    private double latBd;
    /**
     * 高德坐标经纬度信息
     */
    private double lngGc;

    /**
     * 高德坐标经纬度信息
     */
    private double latGc;


}
