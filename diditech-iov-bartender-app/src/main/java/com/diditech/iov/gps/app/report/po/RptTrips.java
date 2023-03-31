package com.diditech.iov.gps.app.report.po;

import java.math.BigDecimal;
import java.util.Date;

public class RptTrips extends RptTripsKey {
    private String dayTag;

    private Date endTime;

    private BigDecimal distance;

    private Integer duration;

    private Short speedMax;

    private Short speedAve;

    private Integer fuelConsumption;

    private BigDecimal startLngBd;

    private BigDecimal startLatBd;

    private BigDecimal endLngBd;

    private BigDecimal endLatBd;

    private BigDecimal startLngGc;

    private BigDecimal startLatGc;

    private BigDecimal endLngGc;

    private BigDecimal endLatGc;

    private BigDecimal startLng;

    private BigDecimal startLat;

    private BigDecimal endLng;

    private BigDecimal endLat;

    private String startAddress;

    private String endAddress;

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

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Short getSpeedMax() {
        return speedMax;
    }

    public void setSpeedMax(Short speedMax) {
        this.speedMax = speedMax;
    }

    public Short getSpeedAve() {
        return speedAve;
    }

    public void setSpeedAve(Short speedAve) {
        this.speedAve = speedAve;
    }

    public Integer getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Integer fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public BigDecimal getStartLngBd() {
        return startLngBd;
    }

    public void setStartLngBd(BigDecimal startLngBd) {
        this.startLngBd = startLngBd;
    }

    public BigDecimal getStartLatBd() {
        return startLatBd;
    }

    public void setStartLatBd(BigDecimal startLatBd) {
        this.startLatBd = startLatBd;
    }

    public BigDecimal getEndLngBd() {
        return endLngBd;
    }

    public void setEndLngBd(BigDecimal endLngBd) {
        this.endLngBd = endLngBd;
    }

    public BigDecimal getEndLatBd() {
        return endLatBd;
    }

    public void setEndLatBd(BigDecimal endLatBd) {
        this.endLatBd = endLatBd;
    }

    public BigDecimal getStartLngGc() {
        return startLngGc;
    }

    public void setStartLngGc(BigDecimal startLngGc) {
        this.startLngGc = startLngGc;
    }

    public BigDecimal getStartLatGc() {
        return startLatGc;
    }

    public void setStartLatGc(BigDecimal startLatGc) {
        this.startLatGc = startLatGc;
    }

    public BigDecimal getEndLngGc() {
        return endLngGc;
    }

    public void setEndLngGc(BigDecimal endLngGc) {
        this.endLngGc = endLngGc;
    }

    public BigDecimal getEndLatGc() {
        return endLatGc;
    }

    public void setEndLatGc(BigDecimal endLatGc) {
        this.endLatGc = endLatGc;
    }

    public BigDecimal getStartLng() {
        return startLng;
    }

    public void setStartLng(BigDecimal startLng) {
        this.startLng = startLng;
    }

    public BigDecimal getStartLat() {
        return startLat;
    }

    public void setStartLat(BigDecimal startLat) {
        this.startLat = startLat;
    }

    public BigDecimal getEndLng() {
        return endLng;
    }

    public void setEndLng(BigDecimal endLng) {
        this.endLng = endLng;
    }

    public BigDecimal getEndLat() {
        return endLat;
    }

    public void setEndLat(BigDecimal endLat) {
        this.endLat = endLat;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress == null ? null : startAddress.trim();
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress == null ? null : endAddress.trim();
    }
}