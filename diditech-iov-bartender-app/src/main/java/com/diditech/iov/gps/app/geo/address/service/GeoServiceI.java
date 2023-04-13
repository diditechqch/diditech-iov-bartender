package com.diditech.iov.gps.app.geo.address.service;

import java.util.List;

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

    /**
     * 批量反地理编码
     * @param reqStr 坐标组成的字符串，每组坐标格式为：lat+","+lng，每组坐标之间用竖线“|”分隔。坐标系必须是高德（国测）坐标系。
     * @return 批量地址：地址1|城市1，地址2|城市2，地址3|城市3
     */
    String geoBatch(String reqStr, String coorType);

    List<String> geoBatchReturnList(String reqStr, String coorType);
}
