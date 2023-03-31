package com.diditech.iov.gps.api.obd.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * OBD行车数据实体类
 * @author zhaist
 * @date 2020-11-20
 **/
@Data
public class ObdData implements Serializable {

    private static final long serialVersionUID = -526048465462441774L;

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
     * OBD总里程
     */
    private String obdMileage;

    /**
     * OBD环境温度
     */
    private String obdAt;

    /**
     * OBD水温/冷却液温度 ℃
     */
    private String obdWt;

    /**
     * 冷却液液位
     */
    private String obdCl;

    /**
     * 胎压报警
     */
    private String obdAlmTirePres;

    /**
     * 发动机负荷值
     */
    private String obdEl;

    /**
     * 制动液液位
     */
    private String obdBfls;

    /**
     * 前刹车片磨损
     */
    private String obdFbpwl;

    /**
     * 后刹车片磨损
     */
    private String obdBbpwl;

    /**
     * 机油液位
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
     * 故障行驶里程
     */
    private String obdFm;

    /**
     * 发动机启动时间
     */
    private String obdEst;

    /**
     * BD进气口温度
     */
    private String obdAit;

    /**
     * 空气流量
     */
    private String obdAirflow;

    /**
     * OBD大气压
     */
    private String obdAp;

    /**
     * OBD进气压力
     */
    private String obdInp;

    /**
     * OBD加速踏板位置
     */
    private String obdApp;

    /**
     * OBD燃油压力
     */
    private String obdFp;

    /**
     * 长期燃油修正(气缸列1和3)
     */
    private String obdLtfa;

    /**
     * 第一缸点火正时提前角
     */
    private String obdFciaa;

    /**
     * 绝对节气门位置
     */
    private String obdAtp;

    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
