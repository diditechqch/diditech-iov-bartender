package com.diditech.iov.gps.api.devicedata;

import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.devicedata.domain.PackageData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 设备报文解析接口类
 * @author zhjd
 * @date 2019/11/12
 */
@RequestMapping("/device-data")
public interface DeviceDataApi {

    /**
     * 解析原始报文
     * @param protocolType 协议类型
     * @param rawMessage   原始报文
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为{@link PackageData}
     * @date 2019/11/12
     **/
    @RequestMapping(method = RequestMethod.GET)
    ResponseMessage decodeDeviceData(@RequestParam(value = "protocolType") String protocolType,
                                     @RequestParam(value = "rawMessage") String rawMessage);

    /**
     * 批量解析原始报文
     * @param protocolType 协议类型
     * @param rawMessages  原始报文List&lt;String&gt
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为List&lt;{@link PackageData}&gt;
     * @date 2019/11/12
     **/
    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    ResponseMessage batchDecodeDeviceData(@RequestParam(value = "protocolType") String protocolType,
                                          @RequestBody String rawMessages);
}
