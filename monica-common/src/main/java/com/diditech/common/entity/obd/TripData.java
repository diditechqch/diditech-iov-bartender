package com.diditech.common.entity.obd;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 设备行程数据
 * @author zhjd
 * @date 2020/3/13
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TripData implements Serializable {

    protected static final long serialVersionUID = -7082520617143733999L;

    // 设备编号
    protected String deviceNum;
    // 行程开始时间
    protected Date beginTime;
    // 行程结束时间
    protected Date endTime;
    // 行程时长sec
    protected Long tripDuration;
    // 行程怠速（停车）时长sec
    protected Long tripIdelDuration;
    // 行程运动时长sec
    protected Long tripMovingDuration;
    // 行程里程m
    protected Integer tripMileage;
    // 行程油耗ml
    protected Integer tripFuel;
    // 急加速次数
    protected Integer jiasu;
    // 急减速次数
    protected Integer jiansu;
    // 急转弯次数
    protected Integer zhuanwan;
    // 最大时速km/h
    protected Double maxSpeed;
    // 平均时速km/h
    protected Double aveSpeed;
    // 开始坐标百度坐标系lng,lat
    protected String beginPosition;
    // 结束坐标百度坐标系lng,lat
    protected String endPosition;
    // monitor服务器时间
    protected String createTime;
    // 日期
    protected String dayTag;

}
