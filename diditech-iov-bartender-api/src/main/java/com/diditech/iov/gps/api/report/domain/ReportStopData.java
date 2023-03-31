package com.diditech.iov.gps.api.report.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * 停车车报表数据
 * @author zhjd <br>
 * @date 2023/3/13 <br>
 */
@Data
public class ReportStopData extends ReportTripsDataBase {

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 停车时长，分钟
     */
    private long duration;
    /**
     * 坐标
     */
    private double lng;
    /**
     * 坐标
     */
    private double lat;
    /**
     * 地址
     */
    private String address;
}
