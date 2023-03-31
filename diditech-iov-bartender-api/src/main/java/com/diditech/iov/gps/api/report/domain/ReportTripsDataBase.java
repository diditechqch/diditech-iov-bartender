package com.diditech.iov.gps.api.report.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author zhjd <br>
 * @date 2023/3/15 <br>
 */
@Data
public class ReportTripsDataBase {

    /**
     * 设备号
     */
    private String deviceNum;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

}
