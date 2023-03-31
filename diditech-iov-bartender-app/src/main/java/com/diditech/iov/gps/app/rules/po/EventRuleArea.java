package com.diditech.iov.gps.app.rules.po;

import java.util.Date;

public class EventRuleArea {
    private Integer id;

    private Integer ruleId;

    private Integer areaId;

    private Byte areaType;

    private Integer ruleAreaHistoryId;

    private String clientId;

    private Date updateTime;

    private String points;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Byte getAreaType() {
        return areaType;
    }

    public void setAreaType(Byte areaType) {
        this.areaType = areaType;
    }

    public Integer getRuleAreaHistoryId() {
        return ruleAreaHistoryId;
    }

    public void setRuleAreaHistoryId(Integer ruleAreaHistoryId) {
        this.ruleAreaHistoryId = ruleAreaHistoryId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points == null ? null : points.trim();
    }
}