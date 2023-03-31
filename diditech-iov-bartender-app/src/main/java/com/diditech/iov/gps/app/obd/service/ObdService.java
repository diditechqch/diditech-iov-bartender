package com.diditech.iov.gps.app.obd.service;

import com.diditech.iov.gps.api.obd.domain.ObdData;
import com.diditech.iov.gps.api.obd.domain.ObdFault;
import com.diditech.iov.gps.app.core.po.TimeFrameQuery;

import java.util.Date;
import java.util.List;

/**
 * @author zhaist
 * @date 2020/11/19
 */
public interface ObdService {

    /**
     * 查询设备的OBD故障码信息
     * @param deviceNum 设备编号
     * @param vehicleBrand 车辆品牌
     * @return ObdFault 设备定位实体信息
     * @date 2022/5/7
     * @author zhjd
     */
    List<ObdFault> getObdFaultList(String deviceNum, String vehicleBrand);

    /**
     * 查询设备的OBD行驶数据
     * @param deviceNum 设备编号
     * @return ObdFault 设备定位实体信息
     * @author zhaist
     * @date 2020/06/16
     */
    ObdData getObdData(String deviceNum);

    /**
     * 查询时间范围的油耗
     * @param deviceNum 设备编号
     * @return ObdFault 设备定位实体信息
     * @author zhaist
     * @date 2020/06/16
     */
    Integer getObdFuel(String deviceNum, Date startTime, Date endTime);

    /**
     * 查询单设备多个时间范围的油耗
     * @param deviceNum       设备号
     * @param timeFramesItems 时间段字符串表示 每一组含开始结束时间，中间逗号间隔，例如 2022-01-09 15:06:32,2022-01-09 15:17:22
     * @date 2022/1/12
     * @author zhjd
     */
    Integer[] getObdFuelMulti(String deviceNum, List<TimeFrameQuery> timeFramesItems);
}
