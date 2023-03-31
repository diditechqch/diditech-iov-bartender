package com.diditech.iov.gps.app.opt.controller;

import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.device.domain.DeviceLocation;
import com.diditech.iov.gps.app.device.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 运维接口，非业务，可绕过gateway访问
 * @author zhjd <br>
 * @date 2022/3/23 <br>
 */
@RestController
@RequestMapping(value = "/opt")
public class OptController {

    @Autowired
    private DeviceService deviceService;

    /**
     * 查询设备最新位置状态
     * @param deviceNum 设备编号
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 {@link DeviceLocation com.diditech.iov.gps.api.gps.domain.DeviceLocation } 查询设备最新位置状态。
     **/
    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public ResponseMessage getDeviceLocation(@RequestParam(value = "deviceNum") String deviceNum) {
        if (!deviceService.isExistDevice(deviceNum)) {
            return ResponseMessage.error("设备号不存在" + deviceNum);
        }
        return ResponseMessage.ok(deviceService.getDeviceLocation(deviceNum));
    }

}
