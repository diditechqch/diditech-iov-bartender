package com.diditech.iov.gps.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

/**
 * @author zhjd <br>
 * @date 2021/9/2 <br>
 */
@Data
@AllArgsConstructor
public class GreenArea {
    private Integer areaId;
    private String areaName;
    private Integer type;
    private Integer category;
    private GeoJsonPolygon points;
    private GeoJsonPoint dbLoc;
}
