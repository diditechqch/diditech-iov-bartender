package com.diditech.iov.gps.api.device.domain;


import lombok.Data;

import java.io.Serializable;

@Data
public class ClientConfig implements Serializable {

    private static final long serialVersionUID = -526048465462441774L;

    /**
     * 设备型号ID
     */
    private String categoryNo;

    /**
     * 是否开启LBS定位 1开 0关
     */
    private Integer lbsEnable;

    /**
     * 离线超时阈值，单位秒
     */
    private Integer timeoutThreshold;

    /**
     * 终端ID
     */
    private String clientId;




}
