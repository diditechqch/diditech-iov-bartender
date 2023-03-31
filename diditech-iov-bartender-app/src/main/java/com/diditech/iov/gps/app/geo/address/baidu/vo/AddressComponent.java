package com.diditech.iov.gps.app.geo.address.baidu.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class AddressComponent {

    private String country;

    private String province;

    private String city;

    private String district;

    private String street;

    private String street_number;

    private String adcode;

    private String country_code;

    private String direction;

    private double distance;
}
