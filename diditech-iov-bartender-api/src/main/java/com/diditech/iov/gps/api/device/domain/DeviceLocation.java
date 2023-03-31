package com.diditech.iov.gps.api.device.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备定位实体类
 * @author zhaist
 * @date 2020/06/16 14:30
 **/
@Data
public class DeviceLocation implements Serializable {

    private static final long serialVersionUID = -526048465462441774L;

    /**
     * 设备编号
     */
    private String deviceNum;

    /**
     * 有线无线码值 WIFI 1:有线,2:无线
     */
    private Integer wifiFlag;

    /**
     * 设备型号全称
     */
    private String categoryNo;

    /**
     * 设备型号短名称
     */
    private String categoryNoShort;

    /**
     * 经度-高德
     */
    private Double lngGc;

    /**
     * 纬度-高德
     */
    private Double latGc;

    /**
     * 经度-百度
     */
    private Double lngBd;

    /**
     * 纬度-百度
     */
    private Double latBd;

    /**
     * 定位方式码值 0-GPS；1-LBS单基站；2-LBS多基站；5-WIFI；9-不定位
     */
    private Integer locMode;

    /**
     * 卫星数
     */
    private Integer satCount;

    /**
     * 网络信号强度等级
     */
    @JSONField(serialize = false)
    private Integer signalLevel;

    /**
     * 定位时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gpsTime;

    /**
     * 服务器时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * gps速度
     */
    private Integer speed;

    /**
     * 是否行驶 1行驶0停车
     */
    private Integer isMoving;

    /**
     * 方向
     */
    private Integer direction;

    /**
     * 方向名称
     */
    private String directionName;

    /**
     * 是否离线 1离线0在线
     */
    @JSONField(serialize = false)
    private Integer almOffline;

    /**
     * 是否休眠 1休眠0非休眠，仅对无线设备有效
     */
    @JSONField(serialize = false)
    private Integer isSleeping;

    /**
     * 开始离线/在线时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTimeOnOffLine;

    /**
     * 开始行驶/停车时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTimeMovingStop;

    @JSONField(serialize = false)
    private String tag;
}
