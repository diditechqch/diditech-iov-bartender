package com.diditech.iov.gps.api.geo.address;

import org.springframework.web.bind.annotation.*;

/**
 * 地址解析微服务
 * @author zhjd <br>
 * @date 2022/3/2 <br>
 */
@RequestMapping("/geo/address")
public interface GeoAddressApi {

    @GetMapping
    String geo(@RequestParam String lat, @RequestParam String lng);

    /**
     * 支持指定坐标系（bd09，gcj02，wgs84），默认高德
     * @date 2021/5/13
     * @author zhjd
     */
    @GetMapping("/{coorType}")
    String geoWithCoordinateType(@RequestParam String lat,
                                 @RequestParam String lng,
                                 @PathVariable String coorType);

    /**
     * 默认批量反地理编码接口，请求坐标为高德坐标系
     * @return 返回参数为字符串（非json对象）。返回地址、描述顺序与请求坐标的前后顺序一致，
     * 每组地址描述格式为：地址+"|"+城市名，每组之间用逗号","分隔。
     * 若某组解析失败，改组结果为空字符串（e.g. 地址1|城市1，地址2|城市2，地址3|城市3，，地址5|城市5）
     */
    @PostMapping("/batch")
    String geoBatchGcj02(@RequestBody String reqStr);

    /**
     * 批量反地理编码接口，支持指定坐标系（bd09，gcj02，wgs84），默认高德
     * 坐标组成的字符串，每组坐标格式为：lat+","+lng，每组坐标之间用竖线“|”分隔。
     * @return 返回参数为字符串（非json对象）。返回地址描述顺序与请求坐标的前后顺序一致，
     * 每组地址描述格式为：地址+"|"+城市名，每组之间用逗号","分隔。
     * 若某组解析失败，改组结果为空字符串（e.g. 地址1|城市1，地址2|城市2，地址3|城市3，，地址5|城市5）
     * @date 2021/1/20
     * @author zhjd
     */
    @PostMapping("/batch/{coorType}")
    String geoBatch(@RequestBody String reqStr, @PathVariable String coorType);
}
