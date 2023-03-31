package com.diditech.iov.gps.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 停车过程数据
 * @author zhjd
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class StopData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Object id;
    private String deviceNum;
    private Date beginTime;
    private Date endTime;
    private Object totalSecond;
    private Object lngGc;
    private Object latGc;
    private Object lngBd;
    private Object latBd;
    private boolean inserted;
    private Object locMode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopData stopData = (StopData) o;
        return deviceNum.equals(stopData.deviceNum) &&
                beginTime.equals(stopData.beginTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceNum, beginTime);
    }
}
