package com.diditech.common.entity.obd;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * 设备OBD故障码
 * @author hefan
 * @date 2020/3/11 10:16
 */
@Data
@ToString
public class DeviceObdFault implements Serializable {

    private static final long serialVersionUID = 3571895584890921238L;

    /**
     * 设备编号
     */
    private String deviceNum;

    /**
     * 状态 0：故障 1：正常
     */
    private Integer status;

    /**
     * 故障码，以逗号分隔
     */
    private String errorCode;

    /**
     * 故障时间（报文时间）
     */
    private String gpsTime;

    /**
     * 创建时间（服务器时间）
     */
    private Date createTime;

}
