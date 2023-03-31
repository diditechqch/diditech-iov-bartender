package com.diditech.iov.gps.app.device.repository;

import com.diditech.iov.gps.api.device.domain.ClientConfig;
import com.diditech.iov.gps.api.device.domain.DeviceLocation;
import com.diditech.iov.gps.api.device.domain.DeviceMileage;
import com.diditech.iov.gps.app.device.po.BizDeviceCmd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhjd
 * @date 2020/6/18
 */
public interface DeviceMapper {

    /**
     * 查询设备最新位置状态
     * @param deviceNum 设备编号
     * @return DeviceLocation 设备定位实体信息
     * @author zhaist
     * @date 2020/06/16
     */
    DeviceLocation getDeviceLocation(@Param("deviceNum") String deviceNum);

    /**
     * 查询设备里程信息
     * @param deviceNum 设备编号
     * @return DeviceMileage 设备里程实体类
     * @author zhaist
     * @date 2020/06/17
     */
    DeviceMileage getDeviceMileage(@Param("deviceNum") String deviceNum);

    /**
     * 更新历程差到里程表
     * @param deviceNum 设备编号
     * @param zqycDiff  政企用车里程差
     * @return int 受影响的记录数
     * @author zhaist
     * @date 2020/06/17
     */
    int updateDeviceMileage(@Param("deviceNum") String deviceNum, @Param("zqycDiff") int zqycDiff);

    /**
     * 根据设备号和校准里程查询里程差
     * @param deviceNum      设备编号
     * @param correctMileage 校准里程
     * @return DeviceMileage 受影响的记录数
     * @author zhaist
     * @date 2020/06/17
     */
    DeviceMileage getDeviceMileageDiffer(@Param("deviceNum") String deviceNum, @Param("correctMileage") int correctMileage);

    /**
     * 根据设备号和客户端统计
     * @date 2020/7/1
     * @author zhjd
     */
    int countByClientNDeviceNum(@Param("clientId") String clientId, @Param("deviceNum") String num);

    /**
     * 批量更新设备启停状态
     * @date 2020/7/2
     * @author zhaist
     */
    int batchStartOrStop(@Param("devices") String[] devices,
                         @Param("clientId") String clientId,
                         @Param("isEnable") int isEnable);

    /**
     * 根据设备型号全称查询设备ID
     * @param categoryNo 设备型号全称
     * @return Integer 设备型号ID
     * @author zhaist
     * @date 2020/08/17
     */
    Integer getCateIdByCateNo(@Param("categoryNo") String categoryNo);

    /**
     * 服务器启动时加载配置缓存
     * @return int 受影响的记录数
     * @author zhaist
     * @date 2020/08/17
     */
    List<ClientConfig> getDeviceConfigData();

    /**
     * 根据设备型号全称查询设备ID
     * @param deviceNum 设备编号
     * @return Integer 存在该设备编号条数
     * @author zhaist
     * @date 2020/08/19
     */
    Integer isExistDevice(@Param("deviceNum") String deviceNum);

    /**
     * 批量存储指令
     * @date 2022/3/22
     * @author zhjd
     */
    void insertCmdList(List<BizDeviceCmd> list);

    /**
     * 查询近几天定位的设备
     * @param interval 近几天
     * @return List&lt;String&gt; 设备号list
     * @date 2023/3/9
     * @author zhjd
     */
    List<String> getDevicesByGpsTime(int interval);
}
