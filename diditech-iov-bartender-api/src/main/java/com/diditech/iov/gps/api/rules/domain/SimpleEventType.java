package com.diditech.iov.gps.api.rules.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 简单事件类型枚举，支持终端级开启
 * @author zhjd
 * @date 2020/8/17
 */
@Getter
@AllArgsConstructor
public enum SimpleEventType {
//    ACC_ON(5, "车辆点火"),
//    ONLINE(6, "上线"),
//    CUT_POWER(9, "光敏拆除"),
//    LOW_BATTERY(14, "电瓶低电压"),
//    SHAKE(19, "停车异动"),
//    LOW_POWER(24, "低电量"),
    CUT_WIRE(3, "断电拆除"),
//    CRASH(26, "车辆碰撞"),
//    OBD_FAULT(29, "故障码"),
//    UNPLUG(30, "终端拔除"),
    ;

    /**
     * 事件类型
     */
    private int code;

    /**
     * 事件名称
     */
    private String desc;

    public static boolean has(int code) {
        return Arrays.stream(SimpleEventType.values())
                .filter(item -> item.getCode() == code).count() == 1;
    }
}
