package com.diditech.iov.gps.app.geo.address.service;

import com.diditech.iov.gps.app.geo.address.baidu.vo.GeoHash;
import com.diditech.iov.gps.app.geo.address.service.impl.BaiduService;

import java.util.List;

/**
 * 百度反地理编码接口类
 * @author zhjd <br>
 * @date 2023/3/7 <br>
 */
public interface BaiduServiceI {

    /**
     * 0 正常
     * 1 服务器内部错误
     * 2 请求参数非法
     * 3 权限校验失败
     * 4 配额校验失败
     * 5 ak不存在或者非法
     * 101 服务禁用
     * 102 不通过白名单或者安全码不对
     * 2xx 无权限
     * 3xx 配额错误
     */
    int BAIDU_STATUS_OK = 0;

    String loadAddress(double lat, double lng);

    int checkBaiduKeyStatus(String key);

    String crash(List<GeoHash> addressList, BaiduService.DoCrash o);
}
