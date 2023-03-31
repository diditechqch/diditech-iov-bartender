package com.diditech.iov.gps.app.geo.address.repository;

import com.diditech.iov.gps.app.geo.address.baidu.vo.GeoHash;

import java.util.List;

/**
 * @author zhjd <br>
 * @date 2022/3/2 <br>
 */
public interface GeoMapper {

    List<GeoHash> searchGeoLib(String geoCode8);

    void insertData(GeoHash geoHash);

    void deleteData(String geoCode8);
}
