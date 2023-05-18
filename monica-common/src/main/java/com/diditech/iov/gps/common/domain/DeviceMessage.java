package com.diditech.iov.gps.common.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * 事件详情
 * @author zhjd
 * @date 2020/7/6
 */
@Data
@ToString
public class DeviceMessage implements Serializable {

    private static final long serialVersionUID = -2315450676544555265L;

    // 唯一标识
    private String key;

    // 报警类型（中间版）
    private int ruleType;

    // 报警类型（中间版）
    private int ruleId;

    // 设备号
    private String deviceNum;

    // 定位时间 yyMMddHHmmss
    private String gpsTime;

    // 定位时间 yyMMddHHmmss
    private String createTime;

    // 百度经度
    private String lngBd;

    // 百度纬度
    private String latBd;

    // 高德经度
    private String lngGc;

    // 高德纬度
    private String latGc;

    // 报警/解除 1：报警 0：解除
    private int isAlarm;

    private Integer areaId;

    // 设备最新报文对象
    private Map<String, Object> deviceData;

    // 设备历史报文对象
    private Map<String, Object> lastDeviceData;

    private String threshold1;

    private String threshold2;

    private String threshold3;

    private String threshold4;

    private String threshold5;

}
