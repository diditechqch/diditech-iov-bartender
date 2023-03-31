package com.diditech.iov.gps.app.report.srv.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.diditech.iov.gps.api.report.domain.ReportTripsDataBase;
import com.diditech.iov.gps.app.core.util.MapUtil;
import com.diditech.iov.gps.app.report.po.RptTripsKey;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author zhjd <br>
 * @date 2023/3/14 <br>
 */
public abstract class TripServiceBase<T extends ReportTripsDataBase, V extends RptTripsKey> {

    static final int minNoDataDuration = 900;

    /**
     * 获取统计数据
     * @date 2023/3/23
     * @author zhjd
     */
    public List<T> getReport(List<String> deviceNumList,
                             Date beginTime,
                             Date endTime,
                             String coorType,
                             Integer minDuration,
                             Double minDistance) {
        Map<String, List<T>> historyMap =
                loadReportData(deviceNumList,
                        beginTime,
                        endTime,
                        coorType,
                        minDuration,
                        minDistance);
        Date beginOfToday = DateUtil.beginOfDay(new Date());
        boolean mixedUp = beginTime.before(beginOfToday) && beginOfToday.before(endTime);
        if (mixedUp) {
            Map<String, List<T>> dynamicMap
                    = loadDynamicTrip(deviceNumList,
                    beginOfToday,
                    endTime,
                    coorType,
                    minDuration,
                    minDistance,
                    historyMap);
            MapUtil.mergeMap(dynamicMap, historyMap);
        }
        return historyMap.keySet().stream()
                .map(historyMap::get)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    /**
     * 根据轨迹动态数据生成结果
     * @date 2023/3/21
     * @author zhjd
     */
    protected abstract Map<String, List<T>> loadDynamicTrip(List<String> deviceNumList,
                                                            Date beginTime,
                                                            Date endTime,
                                                            String coorType,
                                                            Integer minDuration,
                                                            Double minDistance,
                                                            Map<String, List<T>> resultMap);

    /**
     * 获取数据库数据
     * @date 2023/3/23
     * @author zhjd
     */
    protected abstract List<V> selectEntity(List<String> deviceNumList,
                                            Date beginTime,
                                            Date toTime,
                                            Integer minDuration,
                                            Double minDistance);

    /**
     * 根据数据库对象构建接口查询对象
     * @date 2023/3/23
     * @author zhjd
     */
    protected abstract T buildReportDto(V item, String coorType);

    /**
     * 根据数据库中报表静态数据生成结果
     * @date 2023/3/21
     * @author zhjd
     */
    private Map<String, List<T>> loadReportData(List<String> deviceNumList,
                                                Date beginTime,
                                                Date endTime,
                                                String coorType,
                                                Integer minDuration,
                                                Double minDistance) {
        final Date beginOfToday = DateUtil.beginOfDay(new Date());
        boolean mixedUp = beginTime.before(beginOfToday) && beginOfToday.before(endTime);
        Date toTime = mixedUp ? beginOfToday : endTime;
        List<V> entityList
                = selectEntity(deviceNumList,
                beginTime,
                toTime,
                minDuration,
                minDistance);
        if (CollUtil.isEmpty(entityList)) {
            return new TreeMap<>();
        }

        return entityList.stream()
                .map(item -> buildReportDto(item, coorType))
                .collect(Collectors.groupingBy(ReportTripsDataBase::getDeviceNum));
    }

}
