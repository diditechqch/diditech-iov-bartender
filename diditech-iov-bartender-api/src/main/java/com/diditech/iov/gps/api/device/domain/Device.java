package com.diditech.iov.gps.api.device.domain;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class Device implements Serializable {

    private static final long serialVersionUID = -526048465462441774L;

    /**
     * 设备编号
     */
    @NotBlank(message = "设备编号不能为空")
    private String deviceNum;

    /**
     * 设备型号全称
     */
    private String categoryNo;

    /**
     * 设备型号ID
     */
    private Integer categoryId;

    /**
     * 设备类型 1有线 2无线 3OBD
     */
    @Max(value = 3, message = "无效数值，可用数值：1有线 2无线 3OBD")
    @Min(value = 1, message = "无效数值，可用数值：1有线 2无线 3OBD")
    @NotNull(message = "设备类型不能为空")
    private Integer wifiFlag;

    /**
     * 是否开启LBS定位 1开 0关
     */
    @Max(value = 1, message = "无效数值，可用数值：1开 0关")
    @Min(value = 0, message = "无效数值，可用数值：1开 0关")
    private Integer lbsEnable;

    /**
     * 离线超时阈值，单位秒
     */
    private Integer timeoutThreshold;

    /**
     * 终端是否接收设备信息 1启用 0停用
     */
    @Max(value = 1, message = "无效数值，可用数值：1启用 0停用")
    @Min(value = 0, message = "无效数值，可用数值：1启用 0停用")
    private int isenable = 1;

    /**
     * 终端ID
     */
    private String clientId;

    /**
     * 租户ID
     */
    private String tenantId;

}
