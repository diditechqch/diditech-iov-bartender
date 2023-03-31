package com.diditech.iov.gps.api.device;

import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.device.domain.Device;
import com.diditech.iov.gps.api.device.domain.DeviceLocation;
import com.diditech.iov.gps.api.device.domain.DeviceMileage;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 设备信息查询接口
 * @author zhjd
 * @date 2019/2/18
 */
@RequestMapping(value = "/devices")
public interface DevicesApi {

    /**
     * 查询设备最新位置状态
     * @param deviceNum 设备编号
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 {@link DeviceLocation com.diditech.iov.gps.api.gps.domain.DeviceLocation } 查询设备最新位置状态。
     **/
    @RequestMapping(value = "/location", method = RequestMethod.GET)
    ResponseMessage getDeviceLocation(@RequestParam(value = "deviceNum") String deviceNum);

    /**
     * 查询设备的当前里程
     * @param deviceNum   设备编号
     * @param mileageType 里程类型  1：硬件里程 2:OBD里程 3:总里程/软件里程
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 {@link DeviceMileage com.diditech.iov.gps.api.gps.domain.DeviceMileage } 查询设备最新位置状态。
     **/
    @RequestMapping(value = "/mileage", method = RequestMethod.GET)
    ResponseMessage getDeviceMileage(@RequestParam(value = "deviceNum") String deviceNum,
                                     @RequestParam(value = "mileageType") Integer mileageType);

    /**
     * 校准总里程/软件里程
     * @param deviceNum      设备编号
     * @param correctMileage 正确里程 km
     * @param forceFlag      非必填，强制校准，当设备未定位时也可校准，之后里程在此数据基础上累加
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 boolean 是否更新成功。
     * @date 2022/3/14
     * @author zhjd
     */
    @RequestMapping(value = "/mileage", method = RequestMethod.PUT)
    ResponseMessage updateDeviceMileage(@RequestParam(value = "deviceNum") String deviceNum,
                                        @RequestParam(value = "correctMileage") Double correctMileage,
                                        @RequestParam(value = "forceFlag", required = false, defaultValue = "0") int forceFlag);

    /**
     * 插入设备指令信息，已废弃，使用GpsApi.saveCmd
     * @param deviceNum 设备编号
     * @param cmdName   指令名称
     * @param cmd       指令内容
     * @param cmdStr    指令描述
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 boolean 是否插入成功
     **/
    @Deprecated
    @RequestMapping(value = "/cmd/insert", method = RequestMethod.GET)
    ResponseMessage insertDeviceCmd(@RequestParam(value = "deviceNum") String deviceNum,
                                    @RequestParam(value = "cmdName") String cmdName,
                                    @RequestParam(value = "cmd") String cmd,
                                    @RequestParam(value = "cmdStr") String cmdStr);

    /**
     * 设备批量启用停用接口
     * @param devices  设备集合（设备编号）
     * @param isEnable 设备启用禁用状态（1：启用 0：禁用）
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 boolean 是否更新成功
     **/
    @RequestMapping(value = "/batch", method = RequestMethod.GET)
    ResponseMessage batchStartOrStop(@RequestParam(value = "devices") String[] devices,
                                     @RequestParam(value = "isEnable") Integer isEnable);

    /**
     * 新增设备
     * @param deviceInfo 设备对象
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 boolean 是否新增成功
     **/
    @PostMapping
    ResponseMessage addDevice(@Valid @RequestBody Device deviceInfo);

    /**
     * 删除设备
     * @param deviceNum 设备编号
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 boolean 是否删除成功
     **/
    @DeleteMapping
    ResponseMessage deleteDevice(@RequestParam(value = "deviceNum") String deviceNum);

    /**
     * 更新设备
     * @param deviceInfo 设备对象
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 boolean 是否更新成功
     **/
    @PutMapping
    ResponseMessage updateDevice(@RequestBody Device deviceInfo);

    /**
     * 新增设备，支持相同型号批量添加，使用默认参数，可绕过鉴权
     * @param deviceNums 设备号，传参逗号间隔
     * @param categoryNo 设备型号，全称
     * @param wifiFlag   设备类型，默认与设备型号配置一致，1:有线,2:无线,3:OBD,4:行车记录仪
     * @param clientId   客户端ID
     */
    @GetMapping("syn")
    ResponseMessage addSimpleDevice(@RequestParam("deviceNums") String[] deviceNums,
                                    @RequestParam("categoryNo") String categoryNo,
                                    @RequestParam(value = "wifiFlag", required = false) Integer wifiFlag,
                                    @RequestParam("clientId") String clientId);

}
