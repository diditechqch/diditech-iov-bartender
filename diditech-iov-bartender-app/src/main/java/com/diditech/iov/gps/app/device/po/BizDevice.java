package com.diditech.iov.gps.app.device.po;

import java.util.Date;

public class BizDevice {
    private Integer id;

    private String deviceNum;

    private Integer categoryId;

    private Byte wifiFlag;

    private Byte lbsEnable;

    private Integer timeoutThreshold;

    private Byte isenable;

    private Byte isdel;

    private Date createTime;

    private Date updateTime;

    private String clientId;

    private String tenantId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum == null ? null : deviceNum.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Byte getWifiFlag() {
        return wifiFlag;
    }

    public void setWifiFlag(Byte wifiFlag) {
        this.wifiFlag = wifiFlag;
    }

    public Byte getLbsEnable() {
        return lbsEnable;
    }

    public void setLbsEnable(Byte lbsEnable) {
        this.lbsEnable = lbsEnable;
    }

    public Integer getTimeoutThreshold() {
        return timeoutThreshold;
    }

    public void setTimeoutThreshold(Integer timeoutThreshold) {
        this.timeoutThreshold = timeoutThreshold;
    }

    public Byte getIsenable() {
        return isenable;
    }

    public void setIsenable(Byte isenable) {
        this.isenable = isenable;
    }

    public Byte getIsdel() {
        return isdel;
    }

    public void setIsdel(Byte isdel) {
        this.isdel = isdel;
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
}