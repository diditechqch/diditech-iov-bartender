package com.diditech.iov.gps.app.geo.address.service;

/**
 * 反地理编码接口类
 * @author zhjd <br>
 * @date 2022/3/2 <br>
 */
public interface GeoServiceI {

    int GEO_HASH_CODE_8 = 8;
    String ADDRESS_TYPE_BAIDU = "1";

    /**
     * 对一组高德坐标反地理编码
     * @author zhjd
     */
    String geo(String lat, String lng, String coorType);

    String geoBatch(String reqStr, String coorType);
}
