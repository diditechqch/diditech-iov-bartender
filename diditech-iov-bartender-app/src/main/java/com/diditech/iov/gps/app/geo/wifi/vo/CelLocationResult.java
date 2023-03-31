package com.diditech.iov.gps.app.geo.wifi.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CelLocationResult implements IResult {

    /**
     * 0：正常 非0：不正常
     */
    private Integer errcode;
    private String lat;
    private String lon;
    private String radius;
    private String address;
    /**
     * type is "cell"
     */
    private String type;

    @Override
    public boolean isFail() {
        return 0 != this.errcode;
    }
}
