package com.diditech.iov.gps.app.devicedata.provider;

import cn.hutool.core.util.ArrayUtil;
import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.devicedata.DeviceDataApi;
import com.diditech.iov.gps.api.devicedata.domain.PackageData;
import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.devicedata.service.DeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhjd <br>
 * @date 2022/3/1 <br>
 */
@RestController
public class DeviceDataProvider implements DeviceDataApi {

    @Autowired
    private DeviceDataService service;

    @Override
    public ResponseMessage decodeDeviceData(@RequestParam(value = "protocolType") String protocolType,
                                            @RequestParam(value = "rawMessage") String rawMessage) {
        return ResponseMessage.ok(service.decodeDeviceData(protocolType, rawMessage));
    }

    @Override
    public ResponseMessage batchDecodeDeviceData(@RequestParam(value = "protocolType") String protocolType,
                                                 @RequestBody String rawMessages) {
        rawMessages = rawMessages.trim().replace("\r\n", "");
        String[] messageArray = rawMessages.split(Const.SEP_COMMA);
        if (ArrayUtil.isEmpty(messageArray)) {
            return ResponseMessage.error("报文不可为空");
        }
        List<PackageData> result = Arrays.stream(messageArray)
                .map(item -> service.decodeDeviceData(protocolType, item))
                .collect(Collectors.toList());
        return ResponseMessage.ok(result);
    }
}
