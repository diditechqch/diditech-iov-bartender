package com.diditech.iov.gps.api.device.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 设备里程实体类
 *
 * @author zhaist
 * @date 2020/06/16 14:30
 **/
@Data
public class DeviceMileage implements Serializable {

    private static final long serialVersionUID = -526048465462441774L;

    /**
     * 设备编号
     */
    private String deviceNum;

    /**
     * 里程类型
     */
    private Integer mileageType;

    /**
     * 硬件里程
     */
    @JSONField(serialize = false)
    private Double hardMileage;

    /**
     * OBD设备里程
     */
    @JSONField(serialize = false)
    private Double obdMileage;

    /**
     * 总里程
     */
    @JSONField(serialize = false)
    private Double totalMileage;

    /**
     * 里程差值
     */
    @JSONField(serialize = false)
    private Double zqycDiff;

    /**
     * 返回的最终里程数
     */
    private Double resultMileage;
}
