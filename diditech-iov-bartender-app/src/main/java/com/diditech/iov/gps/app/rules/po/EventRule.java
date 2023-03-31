package com.diditech.iov.gps.app.rules.po;

import java.util.Date;

public class EventRule {
    private Integer id;

    private Integer ruleType;

    private String ruleName;

    private Byte areaEnable;

    private Byte isdel;

    private Date enableTimeFrom;

    private Date enableTimeTo;

    private Byte timeEnable;

    private String clientId;

    private String tenantId;

    private Date createTime;

    private Date updateTime;

    private String threshold1;

    private String threshold2;

    private String threshold3;

    private String threshold4;

    private String threshold5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRuleType() {
        return ruleType;
    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public Byte getAreaEnable() {
        return areaEnable;
    }

    public void setAreaEnable(Byte areaEnable) {
        this.areaEnable = areaEnable;
    }

    public Byte getIsdel() {
        return isdel;
    }

    public void setIsdel(Byte isdel) {
        this.isdel = isdel;
    }

    public Date getEnableTimeFrom() {
        return enableTimeFrom;
    }

    public void setEnableTimeFrom(Date enableTimeFrom) {
        this.enableTimeFrom = enableTimeFrom;
    }

    public Date getEnableTimeTo() {
        return enableTimeTo;
    }

    public void setEnableTimeTo(Date enableTimeTo) {
        this.enableTimeTo = enableTimeTo;
    }

    public Byte getTimeEnable() {
        return timeEnable;
    }

    public void setTimeEnable(Byte timeEnable) {
        this.timeEnable = timeEnable;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getThreshold1() {
        return threshold1;
    }

    public void setThreshold1(String threshold1) {
        this.threshold1 = threshold1 == null ? null : threshold1.trim();
    }

    public String getThreshold2() {
        return threshold2;
    }

    public void setThreshold2(String threshold2) {
        this.threshold2 = threshold2 == null ? null : threshold2.trim();
    }

    public String getThreshold3() {
        return threshold3;
    }

    public void setThreshold3(String threshold3) {
        this.threshold3 = threshold3 == null ? null : threshold3.trim();
    }

    public String getThreshold4() {
        return threshold4;
    }

    public void setThreshold4(String threshold4) {
        this.threshold4 = threshold4 == null ? null : threshold4.trim();
    }

    public String getThreshold5() {
        return threshold5;
    }

    public void setThreshold5(String threshold5) {
        this.threshold5 = threshold5 == null ? null : threshold5.trim();
    }
}