package com.diditech.common.entity.obd;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * 设备OBD车况数据
 * @author hefan
 * @date 2020/3/11 10:07
 */
@Data
@ToString
public class DeviceObdData implements Serializable {

    private static final long serialVersionUID = 5554923515518406525L;

    /**
     * 设备编号
     */
    private String deviceNum;

    /**
     * OBD车速
     */
    private String obdSpeed;

    /**
     * OBD发动机转速
     */
    private String obdRotation;

    /**
     * OBD剩余油量
     */
    private String obdOil;

    /**
     * OBD剩余油量
     */
    private String obdOilUnit;

    /**
     * OBD总里程
     */
    private String obdMileage;

    /**
     * OBD环境温度  ambient temperature ℃
     */
    private String obdAt;

    /**
     * OBD水温/冷却液温度 ℃
     */
    private String obdWt;

    /**
     * 冷却液液位  coolant level
     */
    private String obdCl;

    /**
     * 胎压报警 brake fluid level status
     * ex：(0：当前无警告;1：存在胎压失压;其他：不可用)
     */
    private String obdAlmTirePres;

    /**
     * 发动机负荷值 engine load %
     */
    private String obdEl;

    /**
     * 制动液液位 brake fluid level status
     * ex：(0:不正常;1:正常;其他:不可用)
     */
    private String obdBfls;

    /**
     * 前刹车片磨损 Front brake pad wear level
     * ex：(0 正常/否则  显示对应数据,单位：级)
     */
    private String obdFbpwl;

    /**
     * 后刹车片磨损 Front brake pad wear level
     * ex：(0 正常/否则  显示对应数据,单位：级)
     */
    private String obdBbpwl;

    /**
     * 机油液位 Engine Oil level 毫米
     */
    private String obdEol;

    /**
     * OBD故障码个数
     */
    private String obdFaultNum;

    /**
     * 故障码状态
     */
    private String obdFaultStatus;

    /**
     * 故障行驶里程 fault mileage (m)
     */
    private String obdFm;

    /**
     * 发动机启动时间 engine start time（sec）
     */
    private String obdEst;

    /**
     * BD进气口温度  air intake temperature ℃
     */
    private String obdAit;

    /**
     * 空气流量 airflow rate g/s
     */
    private String obdAirflow;

    /**
     * OBD大气压  atmospheric pressure
     */
    private String obdAp;

    /**
     * OBD进气压力  inlet pressure kpa
     */
    private String obdInp;

    /**
     * OBD加速踏板位置  Accelerator Pedal Position
     */
    private String obdApp;

    /**
     * OBD燃油压力  fuel pressure
     */
    private String obdFp;

    /**
     * 长期燃油修正(气缸列1和3)	Long-term fuel amended(Cylinder Columns 1,3) %
     */
    private String obdLtfa;

    /**
     * 第一缸点火正时提前角 The first cylinder Ignition Advance Angle %
     */
    private String obdFciaa;

    /**
     * 绝对节气门位置 Absolute Throttle Position %
     */
    private String obdAtp;

    /**
     * 更新时间
     */
    private Date updateTime;

}