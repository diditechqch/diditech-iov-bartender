package com.diditech.iov.gps.api.obd.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备定位实体类
 * @author zhaist
 * @date 2020/06/16 14:30
 **/
@Data
public class ObdFault implements Serializable {

    private static final long serialVersionUID = -526048465462441774L;

    /**
     * 设备编号
     */
    @JSONField(serialize = false)
    private String deviceNum;

    /**
     * 状态 0：异常 1：正常
     */
    @JSONField(serialize = false)
    private Integer status;

    /**
     * 故障码（多个故障码用逗号隔开）
     */
    private String errorCode;

    /**
     * 故障时间（报文时间）
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gpsTime;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 故障码
     */
    @JSONField(serialize = false)
    private String code;

    /**
     * 故障码中文描述
     */
    private String codeDescCn;

    /**
     * 故障码英文描述
     */
    private String codeDescEn;

    /**
     * 范畴
     */
    private String category;

    /**
     * 是否为通用代码
     */
    @JSONField(serialize = false)
    private Integer isCommonCode;

    /**
     * 车牌车系
     */
    @JSONField(serialize = false)
    private String vehicleBrand;

    /**
     * 错误码汽车背景知识（描述）
     */
    private String background;

    /**
     * 入库日期
     */
    @JSONField(serialize = false)
    private Date updateTime;
}
