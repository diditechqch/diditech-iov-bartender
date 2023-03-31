package com.diditech.iov.gps.app.rules.po;

public class EventAreaHistory {
    private Integer id;

    private Integer areaId;

    private Byte areaType;

    private String clientId;

    private String tenantId;

    private String points;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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