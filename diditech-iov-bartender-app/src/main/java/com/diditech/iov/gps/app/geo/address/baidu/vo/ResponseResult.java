package com.diditech.iov.gps.app.geo.address.baidu.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ResponseResult {

    private Location location;

    private String formatted_address;

    private String business;

    private AddressComponent addressComponent;

    private List<Poi> pois = new ArrayList<>();

    private String sematic_description;

    private List<PoiRegion> poiRegions = new ArrayList<>();

    private String cityCode;

}
