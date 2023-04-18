package com.diditech.iov.gps.app.report.po;

import java.util.Date;

public class RptAccKey {
    private String deviceNum;

    private Date startTime;

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum == null ? null : deviceNum.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}