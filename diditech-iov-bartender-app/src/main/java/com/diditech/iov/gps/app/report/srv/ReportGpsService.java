package com.diditech.iov.gps.app.report.srv;

import com.diditech.iov.gps.api.report.domain.ReportGpsData;
import com.diditech.iov.gps.app.report.po.RptTrips;

import java.util.Date;
import java.util.List;

/**
 * 里程服务-接口类
 * @author zhjd <br>
 * @date 2023/3/16 <br>
 */
public interface ReportGpsService {

    /**
     * 根据分段数据存储日里程
     * @return int 存储成功条数
     * @date 2023/3/24
     * @author zhjd
     */
    int saveDayGps(List<RptTrips> trips, boolean mergeLastTrip);

    /**
     * 批量设备里程统计
     * @date 2023/3/23
     * @author zhjd
     */
    List<ReportGpsData> getReport(List<String> deviceNumList,
                                  Date beginTime,
                                  Date endTime,
                                  String coorType);

    /**
     * 批量设备日里程查询
     * @date 2023/3/23
     * @author zhjd
     */
    List<ReportGpsData> getDayReport(List<String> deviceNumList,
                                     Date beginTime,
                                     Date endTime,
                                     String coorType);
}
