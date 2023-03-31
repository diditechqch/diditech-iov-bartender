package com.diditech.common.entity.obd;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 设备OBD车况数据
 * @author hefan
 * @date 2020/3/11 10:07
 */
@Data
@ToString
public class DeviceObdFuel implements Serializable {

    /**
     * 设备编号
     */
    private String deviceNum;

    /**
     * 定位时间
     */
    private Date gpsTime;

    /**
     * 服务器时间
     */
    private Date createTime;

    /**
     * 累计油耗 单位升
     */
    private BigDecimal fuelAccumulative;

}