package com.diditech.iov.gps.app.device.po;

import java.util.Date;

public class BizDeviceCategory {
    private Integer id;

    private String categoryNo;

    private String categoryNoShort;

    private Byte mileageType;

    private Byte obdFlag;

    private Byte wifiFlag;

    private Byte isdel;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo == null ? null : categoryNo.trim();
    }

    public String getCategoryNoShort() {
        return categoryNoShort;
    }

    public void setCategoryNoShort(String categoryNoShort) {
        this.categoryNoShort = categoryNoShort == null ? null : categoryNoShort.trim();
    }

    public Byte getMileageType() {
        return mileageType;
    }

    public void setMileageType(Byte mileageType) {
        this.mileageType = mileageType;
    }

    public Byte getObdFlag() {
        return obdFlag;
    }

    public void setObdFlag(Byte obdFlag) {
        this.obdFlag = obdFlag;
    }

    public Byte getWifiFlag() {
        return wifiFlag;
    }

    public void setWifiFlag(Byte wifiFlag) {
        this.wifiFlag = wifiFlag;
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
}