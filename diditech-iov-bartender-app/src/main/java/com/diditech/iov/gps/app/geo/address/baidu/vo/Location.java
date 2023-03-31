package com.diditech.iov.gps.app.geo.address.baidu.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Location {
	private double lng;
	private double lat;
}
