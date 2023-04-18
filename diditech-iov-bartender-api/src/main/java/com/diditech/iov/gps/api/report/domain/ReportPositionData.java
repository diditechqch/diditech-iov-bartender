package com.diditech.iov.gps.api.report.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * 设备定位数据
 * @author zhjd <br>
 * @date 2023/4/18 <br>
 */
@Data
public class ReportPositionData {
    private String deviceNum;
    /**
     * 定位时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gpsTime;
    /**
     * 接收时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date receiveTime;
    /**
     * 纬度，坐标系根据请求返回
     */
    private Double lat;
    /**
     * 经度，坐标系根据请求返回
     */
    private Double lng;
    /**
     * 时速
     */
    private int speed;
    /**
     * 方向
     */
    private String direction;
    /**
     * 是否定位
     */
    private Boolean isValid;

    /**
     * 是否点火
     */
    private Boolean isAccOn;
    /**
     * 是否行驶
     */
    private Boolean isMoving;

    /**
     * 是否离线
     */
    private Boolean isOffline;
    /**
     * isAccOn=true时，此时间为开始点火时间，isAccOn=false时，此时间为开始熄火时间
     */
    private Date startTimeAcc;
    /**
     * isMoving=true时，此时间为开始行驶时间，isMoving=false时，此时间为开始停车时间
     */
    private Date startTimeMoving;
    /**
     * isOffline=true时，此时间为开始离线时间，isOffline=false时，此时间为开始上线时间
     */
    private Date startTimeOffline;
    /**
     * 地址
     */
    private String address;

    private String status;

}
