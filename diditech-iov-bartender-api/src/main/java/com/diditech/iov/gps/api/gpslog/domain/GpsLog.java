package com.diditech.iov.gps.api.gpslog.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class GpsLog implements Serializable {

    private static final long serialVersionUID = -526048465462441774L;

    /**
     * 设备编号
     */
    private String deviceNum;

    /**
     * 设备类型
     */
    private String protocolType;

    /**
     * 系统时间
     */
    private String createTime;

    /**
     * GPS时间
     */
    private String gpsTime;

    /**
     * 请求报文
     */
    private String message;

    /**
     * 响应报文
     */
    private String replyMessage;

    /**
     * 协议类型
     */
    private String packageType;

}
