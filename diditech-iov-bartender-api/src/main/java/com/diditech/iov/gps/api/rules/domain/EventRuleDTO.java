package com.diditech.iov.gps.api.rules.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 规则DTO
 * @author zhjd
 * @date 2020/6/24
 */
@Data
@ToString
public class EventRuleDTO implements Serializable {

    private static final long serialVersionUID = -526048465462441774L;

    private Integer ruleId;

    /**
     * 事件类型，1进区域，2出区域，25断电，30拔除，等参看{@link EventType}
     */
    @NotNull
    private Integer ruleType;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 是否使用区域类报警（比如围栏），1是，0否，默认否
     */
    private int areaEnable;

    private Integer areaId;
    /**
     * shapeType=1时必填，区域点集[[百度lng1,百度lat1],[百度lng2,百度lat2],[百度lng3,百度lat3]]
     */
    private String areaPoints;
    /**
     * shapeType=2时必填，centerLng1,centerLat1,radius1 百度坐标系，半径单位为km
     */
    private String circles;

    /**
     * areaEnable=1时必填，区域类型:1:不规则区域 2：圆形
     */
    private Integer shapeType;

    /**
     * 区域相关的规则，请求成功后返回，区域类型 1：区域 2：路线 3：标记  9:原地驻防
     */
    private Integer areaType;

    /**
     * 区域相关的规则，请求成功后返回，规则区域关系履历ID，用于查询区域
     */
    private Integer ruleAreaHistoryId;

    /**
     * 是否启用时间限制，1是，0否，默认否
     */
    private int timeEnable;

    /***
     * timeEnable=1时必填
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date enableTimeFrom;

    /***
     * timeEnable=1时必填
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date enableTimeTo;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 阈值，与具体事件类型搭配使用
     */
    private String threshold1;
    private String threshold2;
    private String threshold3;
    private String threshold4;
    private String threshold5;

    /**
     * 是否可用 0：可用 1：删除
     */
    private Byte isdel = 0;

    /**
     * 使能开关，1开 0关，默认1
     */
    private Byte isEnable = 1;

    private String clientId;

    /**
     * 租户ID，建议使用企业唯一标识，最长50字符
     */
    @NotBlank
    private String tenantId;

}
