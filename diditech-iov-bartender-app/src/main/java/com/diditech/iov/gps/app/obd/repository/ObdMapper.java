package com.diditech.iov.gps.app.obd.repository;

import com.diditech.iov.gps.api.obd.domain.ObdData;
import com.diditech.iov.gps.api.obd.domain.ObdFault;
import com.diditech.iov.gps.api.obd.domain.ObdFaultDesc;
import com.diditech.iov.gps.app.core.po.TimeFrameQuery;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author zhaist
 * @date 2020-11-19
 */
public interface ObdMapper {

    /**
     * 根绝设备编号查询故障码
     * @param deviceNum 设备编号
     * @return ObdFault OBD故障码实体类
     * @author zhaist
     * @date 2020/11/19
     */
    List<ObdFault> getObdFaultList(@Param("deviceNum") String deviceNum);

    /**
     * 查询OBD驾驶数据
     * @param deviceNum 设备编号
     * @return ObdData OBD行车数据实体类
     * @author zhaist
     * @date 2020/11/20
     */
    ObdData getObdData(@Param("deviceNum") String deviceNum);

    /**
     * 查询时间范围的油耗
     * @param deviceNum 设备编号
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return ObdData OBD行车数据实体类
     * @author zhaist
     * @date 2020/11/20
     */
    Integer getObdFuel(@Param("deviceNum") String deviceNum,
                       @Param("startTime") Date startTime,
                       @Param("endTime") Date endTime);

    /**
     * 查询单设备多个时间范围的油耗
     * @param deviceNum       设备号
     * @param timeFramesItems 时间段字符串表示 每一组含开始结束时间，中间逗号间隔，例如 2022-01-09 15:06:32,2022-01-09 15:17:22
     * @date 2022/1/12
     * @author zhjd
     */
    Integer[] getObdFuelMulti(@Param("deviceNum") String deviceNum,
                              @Param("timeFramesItems") List<TimeFrameQuery> timeFramesItems);

    /**
     * 根据故障码+品牌车系查询描述
     * @param errorCodeList 设备编号列表
     * @param vehicleBrand  品牌车系
     * @return ObdFault OBD故障码实体类
     * @author zhjd
     * @date 2022/5/7
     */
    List<ObdFaultDesc> getObdFaultDescList(@Param("errorCodeList") List<String> errorCodeList,
                                           @Param("vehicleBrand") String vehicleBrand);

    /**
     * 查询时间范围的油耗对象
     * @param deviceNum 设备编号
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return ObdData OBD行车数据实体类
     * @author zhaist
     * @date 2020/11/20
     */
    List<Integer> getAllFuel(@Param("deviceNum") String deviceNum,
                             @Param("startTime") Date startTime,
                             @Param("endTime") Date endTime);

    /**
     * 查询时间范围的油耗对象
     * @param deviceNum 设备编号
     * @return ObdData OBD行车数据实体类
     * @author zhaist
     * @date 2020/11/20
     */
    Integer getCategoryId(@Param("deviceNum") String deviceNum);
}
