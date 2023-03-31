package com.diditech.iov.gps.common.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * redis缓存-事件规则数据
 * @author zhjd <br>
 * @date 2020/7/2 <br>
 */
@Data
@ToString
public class EventRule implements Serializable {

    private static final long serialVersionUID = -5260098908441774L;

    private Integer ruleId;

    private Integer ruleType;

    private String ruleName;

    private int timeEnable;

    private int areaEnable;

    private Integer ruleAreaHistoryId;

    private Integer areaId;

    private Integer areaType;

    private String points;

    private Date enableTimeFrom;

    private Date enableTimeTo;

    private String clientId;

    private String tenantId;

    private String threshold1;

    private String threshold2;

    private String threshold3;

    private String threshold4;

    private String threshold5;
}
