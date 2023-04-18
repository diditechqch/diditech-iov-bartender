package com.diditech.iov.gps.app.report.srv;

import com.diditech.iov.gps.api.report.domain.ReportAccData;
import com.diditech.iov.gps.app.report.po.RptAcc;

import java.util.Date;
import java.util.List;

/**
 * 点火报表服务-接口类
 * @author zhjd <br>
 * @date 2023/4/14 <br>
 */
public interface ReportAccService {

    List<RptAcc> buildRptTripListByDynamicTrip(String device, Date start, Date end);

    int saveReport(List<RptAcc> acc, boolean mergeLastTrip, RptAcc lastAcc);

    List<ReportAccData> getReport(List<String> deviceNumList,
                                  Date beginTime,
                                  Date endTime,
                                  String coorType,
                                  Integer minDuration,
                                  Double minDistance);
}
