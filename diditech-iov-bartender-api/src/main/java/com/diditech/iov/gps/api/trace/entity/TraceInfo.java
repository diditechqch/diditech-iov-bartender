package com.diditech.iov.gps.api.trace.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class TraceInfo implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 定位时间
     */
    private String gpsTime;

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
     * 方向描述
     */
    private String direction;

    /**
     * 方向描述
     */
    private String directionName;

    /**
     * 状态描述
     */
    private String status;

    /**
     * 速度
     */
    private String speed;

    /**
     * 速度类型：GPS表示设备计算所得，OBD表示采集行车电脑所得
     */
    private String speedType;

    /**
     * 定位方式 0-GPS；1-LBS单基站；2-LBS多基站；5-WIFI；9-不定位
     */
    private String locMode;

    /**
     * 服务器时间
     */
    private String createTime;

    /**
     * 是否为补传包 0否1是
     */
    private int isOld;

    /**
     * 里程差
     */
    private String oriMileage;

    /**
     * 总里程
     */
    private String totalMileage;

    /**
     * OBD信息
     */
    @JSONField(serialize = false)
    private Integer obdMessage;

    /**
     * obd速度
     */
    @JSONField(serialize = false)
    private String obdSpeed;

    public TraceInfo fillSpeedData() {
        // edit by zhjd 20200324
        if (null == this.speed || "".equals(this.speed)) {
            this.speed = this.obdSpeed;
            this.speedType = "OBD";
        } else {
            this.speedType = "GPS";
        }
        return this;
    }

    public TraceInfo fillLocMode() {
        if (null == this.obdMessage) {
            this.locMode = "不定位";
            return this;
        }
        switch (this.obdMessage) {
            case 0:
                this.locMode = "GPS";
                break;
            case 1:
                this.locMode = "LBS单基站";
                break;
            case 2:
                this.locMode = "LBS多基站";
                break;
            case 5:
                this.locMode = "WIFI";
                break;
            default:
                this.locMode = "不定位";
                break;
        }
        return this;
    }
}
