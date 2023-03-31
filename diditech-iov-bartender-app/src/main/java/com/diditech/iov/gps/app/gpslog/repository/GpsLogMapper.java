package com.diditech.iov.gps.app.gpslog.repository;

import com.diditech.iov.gps.api.gpslog.domain.GpsLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhjd <br>
 * @date 2022/3/11 <br>
 */
public interface GpsLogMapper {

    /**
     * gps log库按月分表，根据时间范围获取表名
     * @date 2022/3/11
     * @author zhjd
     */
    List<String> loadGpsLogTableListFromDb(@Param("schema") String schema,
                                           @Param("beginTableName") String beginTableName,
                                           @Param("endTableName") String endTableName);

    /**
     * 根据tableSchema名查询所有表的日期后缀集合
     * @author zhaist
     * @date 创建时间：2020年8月10日 下午2:09:38
     */
    List<GpsLog> getGpsLogList(@Param("deviceNum") String deviceNum,
                               @Param("beginTime") String beginTime,
                               @Param("endTime") String endTime,
                               @Param("tableName") String tableName);
}
