package com.diditech.iov.gps.api.trace.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhjd <br>
 * @date 2020/12/1 <br>
 */
@ToString
@Data
@NoArgsConstructor
@EqualsAndHashCode
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
     * 速度，单位km/h，无小数，优先使用OBD速度
     */
    private Integer speed;

    /**
     * 原始坐标，用于计算距离，解决坐标系不一致距离计算误差
     */
    @JSONField(serialize = false)
    private Double primaryLng;
    /**
     * 原始坐标，用于计算距离，解决坐标系不一致距离计算误差
     */
    @JSONField(serialize = false)
    private Double primaryLat;

    /**
     * 点火熄火状态，1点火0熄火 20230412
     */
    private Integer acc;

}
