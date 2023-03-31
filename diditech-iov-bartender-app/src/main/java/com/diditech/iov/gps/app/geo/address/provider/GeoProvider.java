package com.diditech.iov.gps.app.geo.address.provider;

import com.diditech.iov.gps.api.geo.address.GeoAddressApi;
import com.diditech.iov.gps.api.trace.entity.CoordinateType;
import com.diditech.iov.gps.app.geo.address.service.GeoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 反地理编码服务实体类
 * @author zhjd
 * @date 2023/3/8
 */
@RestController
public class GeoProvider implements GeoAddressApi {

    @Autowired
    private GeoServiceI geoService;

    @Override
    public String geo(@RequestParam String lat, @RequestParam String lng) {
        return geoService.geo(lat, lng, CoordinateType.GCJ02.name());
    }

    @Override
    public String geoWithCoordinateType(@RequestParam String lat,
                                        @RequestParam String lng,
                                        @PathVariable String coorType) {
        return geoService.geo(lat, lng, coorType);
    }

    @Override
    public String geoBatchGcj02(@RequestBody String reqStr) {
        return geoService.geoBatch(reqStr, CoordinateType.GCJ02.name());

    }

    @Override
    public String geoBatch(@RequestBody String reqStr, @PathVariable String coorType) {
        return geoService.geoBatch(reqStr, coorType);
    }

}
