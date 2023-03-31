package com.diditech.iov.gps.app.report.po;

import java.math.BigDecimal;
import java.util.Date;

public class RptStops extends RptTripsKey {
    private String dayTag;

    private Date endTime;

    private Integer duration;

    private BigDecimal lngBd;

    private BigDecimal latBd;

    private BigDecimal lngGc;

    private BigDecimal latGc;

    private BigDecimal lng;

    private BigDecimal lat;

    private String address;

    public String getDayTag() {
        return dayTag;
    }

    public void setDayTag(String dayTag) {
        this.dayTag = dayTag == null ? null : dayTag.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getLngBd() {
        return lngBd;
    }

    public void setLngBd(BigDecimal lngBd) {
        this.lngBd = lngBd;
    }

    public BigDecimal getLatBd() {
        return latBd;
    }

    public void setLatBd(BigDecimal latBd) {
        this.latBd = latBd;
    }

    public BigDecimal getLngGc() {
        return lngGc;
    }

    public void setLngGc(BigDecimal lngGc) {
        this.lngGc = lngGc;
    }

    public BigDecimal getLatGc() {
        return latGc;
    }

    public void setLatGc(BigDecimal latGc) {
        this.latGc = latGc;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}