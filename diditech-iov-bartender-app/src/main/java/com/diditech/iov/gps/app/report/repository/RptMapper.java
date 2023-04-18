package com.diditech.iov.gps.app.report.repository;

import com.diditech.iov.gps.app.report.po.RptAcc;
import com.diditech.iov.gps.app.report.po.RptDayGps;
import com.diditech.iov.gps.app.report.po.RptStops;
import com.diditech.iov.gps.app.report.po.RptTrips;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author zhjd <br>
 * @date 2023/3/9 <br>
 */
public interface RptMapper {

    int batchInsertTrips(@Param("list") List<RptTrips> list);

    int batchInsertStops(@Param("list") List<RptStops> list);

    int batchInsertAcc(@Param("list") List<RptAcc> acc);

    RptTrips selectRptTripsBefore(@Param("deviceNum") String deviceNum, @Param("startTime") Date startTime);

    RptAcc selectRptAccBefore(@Param("deviceNum") String deviceNum, @Param("startTime") Date startTime);

    int batchInsertDayGps(@Param("list") List<RptDayGps> gpsList);

}
