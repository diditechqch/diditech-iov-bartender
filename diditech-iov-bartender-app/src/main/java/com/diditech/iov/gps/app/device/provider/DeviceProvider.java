package com.diditech.iov.gps.app.device.provider;

import cn.hutool.core.util.StrUtil;
import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.device.DevicesApi;
import com.diditech.iov.gps.api.device.domain.Device;
import com.diditech.iov.gps.api.device.domain.DeviceLocation;
import com.diditech.iov.gps.app.core.aspect.RequestHelper;
import com.diditech.iov.gps.app.device.po.BizDeviceCategory;
import com.diditech.iov.gps.app.device.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DeviceProvider implements DevicesApi {

    @Autowired
    private DeviceService deviceService;

    /**
     * 通过设备编号查询定位信息
     * @author zhaist
     * @date 2020/06/17
     */
    @Override
    public ResponseMessage getDeviceLocation(@RequestParam(value = "deviceNum") String deviceNum) {
        return ResponseMessage.ok(deviceService.getDeviceLocation(deviceNum));
    }

    /**
     * 通过设备编号和里程类型查询里程
     * @author zhaist
     * @date 2020/06/17
     */
    @Override
    public ResponseMessage getDeviceMileage(@RequestParam(value = "deviceNum") String deviceNum,
                                            @RequestParam(value = "mileageType") Integer mileageType) {
        return ResponseMessage.ok(
                deviceService.getDeviceMileage(deviceNum, mileageType));
    }

    @Override
    public ResponseMessage updateDeviceMileage(@RequestParam(value = "deviceNum") String deviceNum,
                                               @RequestParam(value = "correctMileage") Double correctMileage,
                                               @RequestParam(value = "forceFlag", required = false, defaultValue = "0") int forceFlag) {
        DeviceLocation location = deviceService.getDeviceLocation(deviceNum);
        if (forceFlag == 0 && location != null && (location.getLatGc() == null || location.getLngGc() == null)) {
            return ResponseMessage.error("设备未上线，若此时校准，总里程会在此时的里程基础上累加。若确认这样操作，可传参forceFlag=1。");
        }

        int count = deviceService.updateDeviceMileage(deviceNum, correctMileage);
        if (count > 0) {
            return ResponseMessage.ok();
        }

        return ResponseMessage.error("里程校准失败！");
    }

    /**
     * 设备命令信息插入，已废弃，使用GpsApi.saveCmd
     * @author zhaist
     * @date 2020/06/23
     */
    @Deprecated
    @Override
    public ResponseMessage insertDeviceCmd(@RequestParam(value = "deviceNum") String deviceNum,
                                           @RequestParam(value = "cmdName") String cmdName,
                                           @RequestParam(value = "cmd") String cmd,
                                           @RequestParam(value = "cmdStr") String cmdStr) {
        return ResponseMessage.error("接口已废弃");
    }

    /**
     * 设备批量启停接口
     * @author zhaist
     * @date 2020/07/02
     */
    @Override
    public ResponseMessage batchStartOrStop(@RequestParam(value = "devices") String[] deviceNum,
                                            @RequestParam(value = "isEnable") Integer isEnable) {
        // add by zhjd 20220510 start
        String invalidNum = deviceService.getInvalidDeviceNum(RequestHelper.getClientId(), deviceNum);
        if (StrUtil.isNotBlank(invalidNum)) {
            return ResponseMessage.error("设备号不存在或无权限：" + invalidNum);
        }
        // add by zhjd 20220510 end
        deviceService.batchStartOrStop(deviceNum, RequestHelper.getClientId(), isEnable);
        return ResponseMessage.ok();
    }

    /**
     * 新增设备
     * @author zhaist
     * @date 2020/08/17
     */
    @Override
    public ResponseMessage addDevice(@Valid @RequestBody Device device) {
        if (deviceService.isExistDevice(device.getDeviceNum())) {
            return ResponseMessage.ok("该设备已存在：" + device.getDeviceNum());
        }
        if (StrUtil.isBlank(device.getCategoryNo()) && device.getCategoryId() == null) {
            return ResponseMessage.error("设备型号或型号ID至少提供一个");
        }
        Integer categoryId = null;
        if (device.getCategoryId() == null) {
            categoryId = deviceService.isValidCategoryNo(device.getCategoryNo());
            if (categoryId == null) {
                return ResponseMessage.error("设备型号不存在：" + device.getCategoryNo());
            }
        }
        try {
            device.setCategoryId(categoryId);
            device.setClientId(RequestHelper.getClientId());
            deviceService.addDevice(device);
        } catch (Exception ex) {
            return ResponseMessage.error("新增设备异常！");
        }
        return ResponseMessage.ok();
    }

    /**
     * 删除设备
     * @author zhaist
     * @date 2020/08/17
     */
    @Override
    public ResponseMessage deleteDevice(@RequestParam(value = "deviceNum") String deviceNum) {
        try {
            int count = deviceService.deleteDevice(deviceNum);
            if (count > 0) {
                return ResponseMessage.ok();
            }
            return ResponseMessage.ok("删除失败");
        } catch (Exception ex) {
            return ResponseMessage.error("删除设备异常！");
        }
    }

    /**
     * 更新设备
     * @author zhaist
     * @date 2020/08/17
     */
    @Override
    public ResponseMessage updateDevice(@RequestBody Device device) {
        Integer categoryId = null;
        if (StrUtil.isNotBlank(device.getCategoryNo())) {
            categoryId = deviceService.isValidCategoryNo(device.getCategoryNo());
            if (categoryId == null) {
                return ResponseMessage.error("设备型号不存在：" + device.getCategoryNo());
            }
        }
        device.setCategoryId(categoryId);
        deviceService.updateDevice(device);
        return ResponseMessage.ok();
    }

    @Override
    public ResponseMessage addSimpleDevice(@RequestParam("deviceNums") String[] deviceNums,
                                           @RequestParam("categoryNo") String categoryNo,
                                           @RequestParam(value = "wifiFlag", required = false) Integer wifiFlag,
                                           @RequestParam("clientId") String clientId) {
        BizDeviceCategory deviceCategory = deviceService.getDeviceCategory(categoryNo);
        if (deviceCategory == null) {
            return ResponseMessage.error("设备型号不存在：" + categoryNo);
        }

        deviceService.addSimpleDevice(deviceNums, deviceCategory, wifiFlag, clientId);
        return ResponseMessage.ok();
    }

}
