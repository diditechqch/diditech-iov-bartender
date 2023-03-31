package com.diditech.iov.gps.app.geo.address.baidu.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Poi {

    private String addr;

    private String cp;

    private String direction;

    private double distance;

    private String name;

    private String poiType;

    private Point point;

    private String tel;

    private String uid;

    private String zip;

    private String tag;
}
