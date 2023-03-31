package com.diditech.iov.gps.api.trace.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhjd <br>
 * @date 2020/12/1 <br>
 */
@ToString
@Data
@NoArgsConstructor
public class GpsInfoTripMin implements Serializable {
    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;
    /**
     * 定位时间 yyMMddHHmmss
     */
    private String gpsTime;
    /**
     * 服务器时间 yyMMddHHmmss
     */
    private String createTime;
    /**
     * 轨迹坐标，坐标系根据查询条件而定
     */
    private Double lng;
    /**
     * 轨迹坐标，坐标系根据查询条件而定
     */
    private Double lat;
    /**
     * 累计总里程（系统计算），单位km，3位小数
     */
    private BigDecimal totalMileage;
    /**
     * 速度，单位km/h，无小数，优先使用OBD速度
     */
    private Integer speed;
    /**
     * OBD速度，单位km/h，无小数
     */
    @JSONField(serialize = false)
    private Integer obdSpeed;

    /**
     * 原始里程，单位km，3位小数
     */
    private BigDecimal oriMileage;

}
