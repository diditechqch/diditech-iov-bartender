package com.diditech.iov.gps.app.rules.po;

import java.util.Date;

public class EventArea {
    private Integer id;

    private Byte areaType;

    private Byte isdel;

    private Integer areaHistoryId;

    private Date createTime;

    private Date updateTime;

    private String clientId;

    private String tenantId;

    private String points;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getAreaType() {
        return areaType;
    }

    public void setAreaType(Byte areaType) {
        this.areaType = areaType;
    }

    public Byte getIsdel() {
        return isdel;
    }

    public void setIsdel(Byte isdel) {
        this.isdel = isdel;
    }

    public Integer getAreaHistoryId() {
        return areaHistoryId;
    }

    public void setAreaHistoryId(Integer areaHistoryId) {
        this.areaHistoryId = areaHistoryId;
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

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points == null ? null : points.trim();
    }
}