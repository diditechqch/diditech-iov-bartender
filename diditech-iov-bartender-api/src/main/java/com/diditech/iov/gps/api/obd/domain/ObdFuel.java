package com.diditech.iov.gps.api.obd.domain;

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
public class ObdFuel implements Serializable {

    private static final long serialVersionUID = -526048465462441774L;

    /**
     * 设备编号
     */
    private String deviceNum;

    /**
     * GPS时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gpsTime;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 累计油耗 单位:毫升
     */
    private Integer fuelAccumulative;
}
