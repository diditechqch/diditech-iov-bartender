package com.diditech.iov.gps.app.geo.address.baidu.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class AddressResponseWrap {

    private int status;

    private String message;

    private ResponseResult result;

}
