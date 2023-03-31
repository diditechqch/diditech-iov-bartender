package com.diditech.iov.gps.app.report.srv;

import com.diditech.iov.gps.api.report.domain.ReportTripsData;
import com.diditech.iov.gps.app.report.po.RptTrips;

import java.util.Date;
import java.util.List;

/**
 * 行程服务-接口类
 * @author zhjd <br>
 * @date 2023/3/14 <br>
 */
public interface ReportTripsService {

    /**
     * 存储行程报表
     * @return int 成功条数
     * @date 2023/3/24
     * @author zhjd
     */
    int saveTrip(List<RptTrips> trips, boolean mergeLastTrip, RptTrips before);

    List<ReportTripsData> getReport(List<String> deviceNumList,
                                    Date beginTime,
                                    Date endTime,
                                    String coorType,
                                    Integer minDuration,
                                    Double minDistance);


    /**
     * 根据轨迹分段动态数据生成行程数据（数据库对象）
     * @param deviceNum 设备号
     * @param start     开始时间
     * @param end       结束时间
     * @return {@link List}&lt;{@link RptTrips}&gt; 行程数据（数据库对象）
     * @date 2023/3/23
     * @author zhjd
     */
    List<RptTrips> buildRptTripListByDynamicTrip(String deviceNum,
                                                 Date start,
                                                 Date end);

    List<ReportTripsData> loadDynamicTripByDevice(String deviceNum,
                                                  Date beginTime,
                                                  Date endTime,
                                                  String coorType,
                                                  List<ReportTripsData> historyList);

    RptTrips rebuildRptTripByTime(RptTrips rptTrip);
}
