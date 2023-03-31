package com.diditech.iov.gps.app.trace.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class GpsSearchCondition {

    private String gpsTable;

    private String deviceNum;

    private String beginTime;

    private String endTime;

    private String wifiFlag; // add for wiredevice without power by zhjd 20180412
}
