package com.diditech.iov.gps.app.report.po;

public class RptDayGpsKey {
    private String deviceNum;

    private String dayTag;

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum == null ? null : deviceNum.trim();
    }

    public String getDayTag() {
        return dayTag;
    }

    public void setDayTag(String dayTag) {
        this.dayTag = dayTag == null ? null : dayTag.trim();
    }
}