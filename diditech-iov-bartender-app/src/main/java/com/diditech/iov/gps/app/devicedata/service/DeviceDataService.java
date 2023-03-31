package com.diditech.iov.gps.app.devicedata.service;


import com.diditech.iov.gps.api.devicedata.domain.PackageData;

/**
 * 设备报文解析服务接口类
 * @author zhjd
 * @date 2019/11/12
 */
public interface DeviceDataService {

    /**
     * 解析原始报文，返回报文详情map
     */
    PackageData decodeDeviceData(String protocolType, String rawMessage);
}
