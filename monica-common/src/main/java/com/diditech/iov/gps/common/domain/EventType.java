package com.diditech.iov.gps.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 事件/规则类型枚举
 * @author zhjd
 * @date 2020/6/24
 */
@Getter
@AllArgsConstructor
public enum EventType {
    IN_AREA(1, "驶入围栏"),
    OUT_AREA(2, "驶出围栏"),
    STATION(3, "原地驻防"),
    ROUTE(4, "路线偏离"),
    ACC_ON(5, "车辆点火"),
    ONLINE(6, "上线"),
    STOP(7, "停车超时"),
    OFFLINE(8, "离线超时"),
    CUT_POWER(9, "光敏拆除"),
    FATIGUE(10, "疲劳驾驶"),
    MOVING(11, "行车时段"),
    OVER_SPEED(12, "超速"),
    //(13,"里程超限"),
    LOW_BATTERY(14, "电瓶低电压"),
    //(15,""),
    LOW_OIL(16, "油量低水平"),
    INS_OIL(17, "瞬时油耗"),
    //(18,"日均油耗"),
    SHAKE(19, "停车异动"),
    DISTANCE(20, "设备分离"),
    PSEUDO_LBS(21, "伪基站"),
    LOC_FAIL(22, "定位失效"),
    RISK_AREA(23, "风险区域停留"),
    LOW_POWER(24, "低电量"),
    CUT_WIRE(25, "断电拆除"),
    CRASH(26, "车辆碰撞"),
    OBD_FAULT(29, "故障码"),
    UNPLUG(30, "终端拔除"),
    ;

    /**
     * 事件类型
     */
    private int code;

    /**
     * 事件名称
     */
    private String desc;
}
