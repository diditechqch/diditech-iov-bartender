package com.diditech.iov.gps.api.trace.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 根据轨迹查询进出区域状态
 * @author zhjd <br>
 * @date 2021/10/19 <br>
 */
@Data
public class GpsAreaQuery implements Serializable {

    private static final long serialVersionUID = -526048465492441774L;

    /**
     * 唯一标识
     */
    @NotBlank(message = "唯一标识不能为空")
    private String id;

    /**
     * 设备号
     */
    @NotBlank(message = "设备编号不能为空")
    private String deviceNum;
    /**
     * 查询开始时间 yyyyMMddHHmmss
     */
    @NotBlank(message = "查询开始时间不可为空")
    private String beginTime;
    /**
     * 查询结束时间 yyyyMMddHHmmss
     */
    @NotBlank(message = "查询结束时间不可为空")
    private String endTime;
    /**
     * 查询进出状态，1进区域0出区域
     */
    @NotNull(message = "查询进出状态不可为空")
    private Integer inFlag;
    /**
     * 区域圆心坐标系以及返回坐标系，默认高德/国测，其他需指定，参照 {@link CoordinateType}
     */
    private String coorType = CoordinateType.GCJ02.name();
    /**
     * 圆心坐标经度
     */
    @NotNull(message = "查询区域圆心坐标经度不可为空")
    private Double centreLng;
    /**
     * 圆心坐标纬度
     */
    @NotNull(message = "查询区域圆心坐标纬度不可为空")
    private Double centreLat;
    /**
     * 半径 米
     */
    @NotNull(message = "查询区域半径不可为空")
    private Integer radius;
}
