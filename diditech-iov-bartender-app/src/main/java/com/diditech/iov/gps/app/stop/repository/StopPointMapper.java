package com.diditech.iov.gps.app.stop.repository;

import com.diditech.iov.gps.api.stop.domain.StopPointInfo;
import org.apache.ibatis.annotations.Param;


import java.util.Date;
import java.util.List;

/**
 * @author zhaist
 * @date 2020-11-19
 */
public interface StopPointMapper {

    List<StopPointInfo> getStopPointList(@Param("deviceNum") String deviceNum,
                                         @Param("startTime") Date startTime,
                                         @Param("endTime") Date endTime,
                                         @Param("minStaySeconds") int minStaySeconds);
}
