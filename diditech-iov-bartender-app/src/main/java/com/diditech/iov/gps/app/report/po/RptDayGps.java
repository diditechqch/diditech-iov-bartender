package com.diditech.iov.gps.app.report.po;

import java.math.BigDecimal;
import java.util.Date;

public class RptDayGps extends RptDayGpsKey {
    private Date gpsTime;

    private BigDecimal lat;

    private BigDecimal lng;

    private BigDecimal latBd;

    private BigDecimal lngBd;

    private BigDecimal latGc;

    private BigDecimal lngGc;

    private BigDecimal dayMileage;

    private Integer fuelConsumption;

    private Byte ismoving;

    private Byte almAccon;

    private Byte locMode;

    private String address;

    private String status;

    private String directionName;

    public Date getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(Date gpsTime) {
        this.gpsTime = gpsTime;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLatBd() {
        return latBd;
    }

    public void setLatBd(BigDecimal latBd) {
        this.latBd = latBd;
    }

    public BigDecimal getLngBd() {
        return lngBd;
    }

    public void setLngBd(BigDecimal lngBd) {
        this.lngBd = lngBd;
    }

    public BigDecimal getLatGc() {
        return latGc;
    }

    public void setLatGc(BigDecimal latGc) {
        this.latGc = latGc;
    }

    public BigDecimal getLngGc() {
        return lngGc;
    }

    public void setLngGc(BigDecimal lngGc) {
        this.lngGc = lngGc;
    }

    public BigDecimal getDayMileage() {
        return dayMileage;
    }

    public void setDayMileage(BigDecimal dayMileage) {
        this.dayMileage = dayMileage;
    }

    public Integer getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Integer fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Byte getIsmoving() {
        return ismoving;
    }

    public void setIsmoving(Byte ismoving) {
        this.ismoving = ismoving;
    }

    public Byte getAlmAccon() {
        return almAccon;
    }

    public void setAlmAccon(Byte almAccon) {
        this.almAccon = almAccon;
    }

    public Byte getLocMode() {
        return locMode;
    }

    public void setLocMode(Byte locMode) {
        this.locMode = locMode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName == null ? null : directionName.trim();
    }
}