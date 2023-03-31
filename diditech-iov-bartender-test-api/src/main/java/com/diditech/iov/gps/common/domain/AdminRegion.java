package com.diditech.iov.gps.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

/**
 * @author zhjd <br>
 * @date 2020/9/2 <br>
 */
@Data
@AllArgsConstructor
public class AdminRegion {

    /**
     * 区划等级 1市级 2省级
     **/
    int regionLevel;

    /**
     * 区划名称
     **/
    String regionName;

    /**
     * mongo geo polygon
     **/
    GeoJsonPolygon points;
}
