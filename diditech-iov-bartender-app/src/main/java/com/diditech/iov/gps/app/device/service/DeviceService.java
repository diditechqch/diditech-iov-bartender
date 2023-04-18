package com.diditech.iov.gps.app.device.service;

import com.diditech.iov.gps.api.device.domain.Device;
import com.diditech.iov.gps.api.device.domain.DeviceLocation;
import com.diditech.iov.gps.api.device.domain.DeviceMileage;
import com.diditech.iov.gps.api.report.domain.ReportPositionData;
import com.diditech.iov.gps.app.device.po.BizDeviceCategory;

import java.util.Date;
import java.util.List;

/**
 * @author zhjd
 * @date 2020/6/18
 */
public interface DeviceService {

    /**
     * 查询设备最新位置状态
     * @param deviceNum 设备编号
     * @return DeviceLocation 设备定位实体信息
     * @author zhaist
     * @date 2020/06/16
     */
    DeviceLocation getDeviceLocation(String deviceNum);

    /**
     * 查询设备里程信息
     * @param deviceNum   设备编号
     * @param mileageType 里程类型
     * @return DeviceMileage 设备里程实体类
     * @author zhaist
     * @date 2020/06/17
     */
    DeviceMileage getDeviceMileage(String deviceNum, int mileageType);

    /**
     * 更新正确里程到里程校准表
     * @param deviceNum      设备编号
     * @param correctMileage 里程类型
     * @return int 受影响的记录数
     * @author zhaist
     * @date 2020/06/17
     */
    int updateDeviceMileage(String deviceNum, double correctMileage);

    /**
     * 检查客户端是否有该设备号权限，返回第一个有问题设备号，校验通过时返回null
     * @date 2020/7/1
     * @author zhjd
     */
    String getInvalidDeviceNum(String clientId, String... deviceNums);

    /**
     * 设备指令查询
     * @param devices  设备集合（设备编号）
     * @param isEnable 设备启用禁用状态（1：启用 0：禁用）
     * @author zhaist
     * @date 2020/07/02
     */
    void batchStartOrStop(String[] devices, String clientId, Integer isEnable);

    /**
     * 新增设备
     * @author zhaist
     * @date 2020/08/17
     */
    int addDevice(Device device);

    /**
     * 删除设备
     * @author zhaist
     * @date 2020/08/17
     */
    int deleteDevice(String deviceNum);

    /**
     * 更新设备
     * @author zhaist
     * @date 2020/08/17
     */
    int updateDevice(Device device);

    /**
     * 校验设备编号是否已存在
     * @date 2020/08/19
     * @author zhaist
     */
    boolean isExistDevice(String deviceNum);

    /**
     * 校验设备型号是否有效
     * @date 2020/08/19
     * @author zhaist
     */
    Integer isValidCategoryNo(String categoryNo);

    /**
     * 获取设备型号实体
     * @date 2022/4/6
     * @author zhjd
     */
    BizDeviceCategory getDeviceCategory(String categoryNo);

    /**
     * 新增设备，支持相同型号批量添加，使用默认参数
     * @date 2022/4/6
     * @author zhjd
     */
    void addSimpleDevice(String[] deviceNums, BizDeviceCategory deviceCategory, Integer wifiFlag, String clientId);

    /**
     * 批量查询设备最新定位
     * @date 2023/4/18
     * @author zhjd
     */
    List<ReportPositionData> getPositionReport(List<String> deviceNums, Date beginTime, Date endTime, String coorType);
}
