package com.diditech.iov.gps.app.report.srv;

import com.diditech.iov.gps.api.report.domain.ReportStopData;
import com.diditech.iov.gps.app.report.po.RptTrips;

import java.util.Date;
import java.util.List;

/**
 * 停留点服务-接口类
 * @author zhjd <br>
 * @date 2023/3/14 <br>
 */
public interface ReportStopsService {

    List<ReportStopData> getReport(List<String> deviceNumList,
                                   Date beginTime,
                                   Date endTime,
                                   String coorType,
                                   Integer minDuration,
                                   Double minDistance);

    int saveStops(List<RptTrips> trips, boolean mergeLastTrip, RptTrips before);
}
