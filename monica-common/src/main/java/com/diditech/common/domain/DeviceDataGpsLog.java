package com.diditech.common.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 报文对象
 * @author zhjd
 * @date 2019/12/10
 */
@Data
@ToString
@NoArgsConstructor
public class DeviceDataGpsLog implements Serializable {
    private String dataType;
    private int deviceId;
    private int vehicleId;
    private String deviceNum;
    private String deviceType;
    private String createTime;
    private String gpsTime;
    private String message;
    private String replyMessage;
    private String protocolType;
    private String tag;
}


