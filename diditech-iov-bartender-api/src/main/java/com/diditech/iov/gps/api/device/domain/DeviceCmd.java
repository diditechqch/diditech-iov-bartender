package com.diditech.iov.gps.api.device.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DeviceCmd implements Serializable {

    private static final long serialVersionUID = -526048465462441774L;

    private String id;

    /**
     * 设备编号
     */
    private String deviceNum;

    /**
     * 指令名称
     */
    private String cmdName;

    /**
     * 客户端指令
     */
    private String cmd;

    /**
     * 客户端指令描述
     */
    private String cmdStr;

    /**
     * 客户端指令提交时间
     */
    private Date cmdTime;

    /**
     * 向设备发送时间
     */
    private Date sendTime;

    /**
     * 设备回复内容
     */
    private String result;

    /**
     * 设备回复时间
     */
    private Date resultTime;

    /**
     * 状态
     */
    private Integer status;

}
