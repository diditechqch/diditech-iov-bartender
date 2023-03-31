package com.diditech.common.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 事件规则阈值
 * TODO 和EventRuleDTO中的阈值字段有重复，考虑移除EventRuleDTO中的字段，继承EventRuleThreshold
 */
@Data
public class EventRuleThreshold implements Serializable {

    private static final long serialVersionUID = 407067271169925482L;

    private String threshold1;

    private String threshold2;

    private String threshold3;

    private String threshold4;

    private String threshold5;

}
