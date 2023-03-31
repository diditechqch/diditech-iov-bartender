package com.diditech.iov.gps.api.rules.domain;

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
    ALM_DR(3, "断电拆除"),
    NO_TASK_DRIVING(4, "无任务行驶"),
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
