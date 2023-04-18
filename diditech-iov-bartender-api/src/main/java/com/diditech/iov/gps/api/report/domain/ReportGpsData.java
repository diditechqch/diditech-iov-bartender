package com.diditech.iov.gps.api.report.domain;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * 里程报表
 * @author zhjd <br>
 * @date 2023/3/16 <br>
 */
@Data
public class ReportGpsData {

    private String deviceNum;

    @JSONField(format = "yyyy-MM-dd")
    private Date dayTag;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gpsTime;

    /**
     * 行驶距离，里程，km
     */
    private double mileage;

    /**
     * 油耗（单位毫升）
     */
    private double fuelConsumption;

    private String status;

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
    /**
     * 方向
     */
    private String direction;

    /**
     * 是否点火
     */
    private Boolean isAccOn;
    /**
     * 是否行驶
     */
    private Boolean isMoving;

    public void buildStatus() {
        String status = isMoving ? "行驶" : "停车";
        status += StrUtil.SPACE;
        status += isMoving ? "点火" : "熄火";
        this.status = status;
    }
}
