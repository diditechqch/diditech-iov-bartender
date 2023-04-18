package com.diditech.iov.gps.app.trace.po;

import lombok.Data;

/**
 * 定位轨迹
 * @author zhjd <br>
 * @date 2023/3/16 <br>
 */
@Data
public class GpsEntity {
    private String deviceNum;
    private String gpsTime;
    /**
     * 原始坐标
     */
    private String lng;

    /**
     * 原始坐标
     */
    private String lat;
    /**
     * 百度坐标
     */
    private String lngBd;

    /**
     * 百度坐标
     */
    private String latBd;

    /**
     * 百度坐标
     */
    private String lngGc;

    /**
     * 百度坐标
     */
    private String latGc;

    /**
     * 速度，优先使用OBD速度
     */
    private String speed;
    private String obdSpeed;

    private String acc;
    private String obdAcc;

    private String almAccon;

    private int ismoving;

    private String locMode;

    private String directionName;
}
