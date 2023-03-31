package com.diditech.iov.gps.common.domain;

import lombok.Data;

/**
 * @author zhjd <br>
 * @date 2021/9/10 <br>
 */
@Data
public class AccSiteInfo {
    private Integer id;
    private Integer hubType;
    private String hubAddress;
    private String hubName;
    private double hubLat;
    private double hubLng;
    private String points;
    private String hubArea;
}
