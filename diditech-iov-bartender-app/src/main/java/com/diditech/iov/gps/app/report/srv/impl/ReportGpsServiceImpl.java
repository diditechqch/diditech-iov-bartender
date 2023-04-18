package com.diditech.iov.gps.app.report.srv.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.api.report.domain.ReportGpsData;
import com.diditech.iov.gps.api.trace.entity.CoordinateType;
import com.diditech.iov.gps.api.trace.entity.TripGps;
import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.core.util.MapUtil;
import com.diditech.iov.gps.app.report.po.RptDayGps;
import com.diditech.iov.gps.app.report.po.RptDayGpsExample;
import com.diditech.iov.gps.app.report.po.RptTrips;
import com.diditech.iov.gps.app.report.repository.RptDayGpsMapper;
import com.diditech.iov.gps.app.report.repository.RptMapper;
import com.diditech.iov.gps.app.report.srv.ReportGpsService;
import com.diditech.iov.gps.app.report.srv.ReportTripsService;
import com.diditech.iov.gps.app.trace.po.GpsEntity;
import com.diditech.iov.gps.app.trace.srv.TraceRequest;
import com.diditech.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 里程服务-实体类
 * @author zhjd <br>
 * @date 2023/3/16 <br>
 */
@Slf4j
@Service
public class ReportGpsServiceImpl implements ReportGpsService {

    @Autowired
    private RptDayGpsMapper gpsMapper;

    @Autowired
    private RptMapper rptMapper;

    @Autowired
    private ReportTripsService tripsService;

    @Override
    public int saveDayGps(List<RptTrips> trips, boolean mergeLastTrip) {
        List<RptDayGps> gpsList = buildRptDayGpsList(trips);
        if (mergeLastTrip) {
            gpsList.sort(Comparator.comparing(RptDayGps::getDayTag));
            RptDayGps index0 = gpsList.get(0);
            gpsList.remove(0);

            RptDayGpsExample example = new RptDayGpsExample();
            example.createCriteria()
                    .andDeviceNumEqualTo(index0.getDeviceNum())
                    .andDayTagEqualTo(index0.getDayTag());

            List<RptDayGps> result = gpsMapper.selectByExample(example);
            if (CollUtil.isNotEmpty(result)) {
                RptDayGps data = result.get(0);
                // 与上一次定时任务合并数据的情况下，gpsList第一天的里程数据可能需要重新计算
                if (DateUtil.between(data.getGpsTime(), index0.getGpsTime(), DateUnit.MS) > 0
                        || !data.getLat().equals(index0.getLat())
                        || !data.getLng().equals(index0.getLng())) {
                    // 最后定位时间不同或坐标不同，则重新统计这天数据，不需要考虑跨天分段
                    List<RptTrips> rebuild = tripsService.buildRptTripListByDynamicTrip(index0.getDeviceNum(),
                            DateUtil.beginOfDay(index0.getGpsTime()), DateUtil.endOfDay(index0.getGpsTime()));
                    gpsList.add(buildRptDayGps(rebuild));
                }
            }
        }
        gpsList.sort(Comparator.comparing(RptDayGps::getDayTag));
        RptDayGps from = gpsList.get(0);
        RptDayGps to = gpsList.get(gpsList.size() - 1);

        RptDayGpsExample example = new RptDayGpsExample();
        example.createCriteria()
                .andDeviceNumEqualTo(from.getDeviceNum())
                .andDayTagBetween(from.getDayTag(), to.getDayTag());
        gpsMapper.deleteByExample(example);

        return rptMapper.batchInsertDayGps(gpsList);
    }

    @Override
    public List<ReportGpsData> getReport(List<String> deviceNumList,
                                         Date beginTime,
                                         Date endTime,
                                         String coorType) {

        if (DateUtil.betweenMs(endTime, DateUtil.beginOfDay(endTime)) == 0) {
            endTime = DateUtil.offset(endTime, DateField.SECOND, -1);
        }
        // 1. 查询数据库统计数据
        Map<String, List<RptDayGps>> deviceRptDayGpsMap = selectEntity(deviceNumList, beginTime, endTime);
        Map<String, List<ReportGpsData>> dtoMap = new HashMap<>();
        if (!deviceRptDayGpsMap.isEmpty()) {
            dtoMap.putAll(
                    deviceRptDayGpsMap.keySet().stream()
                            .map(deviceRptDayGpsMap::get)
                            .map(this::buildMileageByEntity)
                            .collect(Collectors.groupingBy(ReportGpsData::getDeviceNum))
            );
        }

        // 2. 合并动态统计数据
        Date finalEndTime = endTime;
        deviceNumList.parallelStream()
                .forEach(
                        key -> {
                            List<RptDayGps> dbList = deviceRptDayGpsMap.get(key);
                            List<ReportGpsData> dtoList = dtoMap.get(key);
                            List<ReportGpsData> result = buildGpsByDynamicTrip(key, beginTime, finalEndTime,
                                    coorType, dbList, dtoList);
                            result.removeIf(Objects::isNull);
                            if (CollUtil.isNotEmpty(result)) {
                                dtoMap.put(key, result);
                            }
                        });

        // 3. 累计以上数据
        return dtoMap.keySet()
                .parallelStream()
                .map(key -> {
                    List<ReportGpsData> list = dtoMap.get(key);
                    if (list.size() == 1) {
                        return list.get(0);
                    }
                    list.sort(Comparator.comparing(ReportGpsData::getGpsTime));
                    ReportGpsData last = list.get(list.size() - 1);
                    last.setMileage(list.stream().mapToDouble(ReportGpsData::getMileage).sum());
                    last.setFuelConsumption(list.stream().mapToDouble(ReportGpsData::getFuelConsumption).sum());
                    return last;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ReportGpsData> getDayReport(List<String> deviceNumList,
                                            Date beginTime,
                                            Date endTime,
                                            String coorType) {
        // 1. 查询数据库历史统计T-1
        Map<String, List<RptDayGps>> deviceRptDayGpsMap = selectEntity(deviceNumList, beginTime, endTime);
        Date today = DateUtil.beginOfDay(new Date());
        if (!endTime.before(today)) {
            // 2. 若查询范围包含今天，则根据动态分段生成
            Map<String, List<RptDayGps>> todayGpsMap =
                    deviceNumList.parallelStream()
                            .map(device -> buildRptDayGpsByDynamicTrip(device, today, endTime))
                            .filter(list -> !list.isEmpty())
                            .flatMap(Collection::stream)
                            .collect(Collectors.groupingBy(RptDayGps::getDeviceNum));
            MapUtil.mergeMap(todayGpsMap, deviceRptDayGpsMap);
        }
        if (deviceRptDayGpsMap.isEmpty()) {
            return null;
        }
        return deviceRptDayGpsMap.keySet().stream()
                .map(deviceRptDayGpsMap::get)
                .map(list -> buildReportGpsData(list, CoordinateType.get(coorType)))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * 根据数据库行程统计生成日里程存储对象
     * @date 2023/3/23
     * @author zhjd
     */
    private List<RptDayGps> buildRptDayGpsList(List<RptTrips> trips) {
        List<RptTrips> list = splitRptTripByDay(trips);
        Map<String, List<RptTrips>> dayTripMap = list.stream()
                .collect(Collectors.groupingBy(RptTrips::getDayTag));
        return dayTripMap.keySet().stream()
                .map(dayTripMap::get)
                .map(this::buildRptDayGps)
                .collect(Collectors.toList());
    }

    private RptDayGps buildRptDayGps(List<RptTrips> trips) {
        RptDayGps data = new RptDayGps();
        trips.sort(Comparator.comparing(RptTrips::getStartTime));
        RptTrips finalGps = trips.get(trips.size() - 1);

        BeanUtil.copyProperties(finalGps, data);
        data.setGpsTime(finalGps.getEndTime());
        data.setAddress(finalGps.getEndAddress());
        data.setDayMileage(trips.stream().map(RptTrips::getDistance).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        data.setFuelConsumption(trips.stream().mapToInt(RptTrips::getFuelConsumption).sum());

        GpsEntity gps = TraceRequest.Builder
                .create(data.getDeviceNum(), data.getGpsTime()).getGps();
        if (gps == null) {
            log.error("查询轨迹失败");
            return data;
        }
        data.setIsmoving((byte) (Util.asInt(gps.getSpeed()) > 5 ? 1 : 0));
        BeanUtil.copyProperties(gps, data);
        data.setLocMode((byte) Util.asInt(gps.getLocMode()));
        data.setGpsTime(DateUtil.parse(gps.getGpsTime(), Util.STR_SHORT_DT_FORMAT));
        data.setIsmoving((byte) gps.getIsmoving());
        data.setAlmAccon((byte) Util.asInt(gps.getAlmAccon()));
        buildStatus(data);
        return data;
    }

    private void buildStatus(RptDayGps data) {
        String status = Util.asInt(data.getIsmoving()) > 0 ? "行驶" : "停车";
        status += Const.SPACE;
        status += Util.asInt(data.getAlmAccon()) > 0 ? "点火" : "熄火";
        data.setStatus(status);
    }

    /**
     * 将跨天行程数据分割为2条数据
     * @date 2023/3/24
     * @author zhjd
     */
    private List<RptTrips> splitRptTripByDay(List<RptTrips> trips) {
        List<RptTrips> dateResetList = trips.stream()
                .filter(item -> DateUtil.betweenDay(item.getStartTime(), item.getEndTime(), true) >= 1)
                .collect(Collectors.toList());

        List<RptTrips> add = new ArrayList<>();
        if (CollUtil.isNotEmpty(dateResetList)) {
            dateResetList.parallelStream()
                    .forEach(item -> {
                        RptTrips upper = new RptTrips();
                        RptTrips lower = new RptTrips();
                        BeanUtil.copyProperties(item, upper);
                        BeanUtil.copyProperties(item, lower);
                        upper.setEndTime(DateUtil.endOfDay(upper.getStartTime()));
                        lower.setStartTime(DateUtil.beginOfDay(lower.getEndTime()));
                        if (upper.getStartTime().before(upper.getEndTime())) {
                            add.add(tripsService.rebuildRptTripByTime(upper));
                        } else {
                            log.debug("跨天行程分割后起始时间相同 upper={}", JSON.toJSONString(upper));
                        }
                        if (lower.getStartTime().before(lower.getEndTime())) {
                            add.add(tripsService.rebuildRptTripByTime(lower));
                        } else {
                            log.debug("跨天行程分割后起始时间相同 lower={}", JSON.toJSONString(lower));
                        }
                    });
        }
        List<RptTrips> copy = new ArrayList<>(trips);
        copy.removeAll(dateResetList);
        copy.addAll(add);
        copy.removeIf(Objects::isNull);
        copy.sort(Comparator.comparing(RptTrips::getStartTime));
        return copy;
    }

    /**
     * 查询数据库静态数据
     * @date 2023/3/24
     * @author zhjd
     */
    private Map<String, List<RptDayGps>> selectEntity(List<String> deviceNumList, Date beginTime, Date endTime) {
        RptDayGpsExample example = new RptDayGpsExample();
        example.createCriteria()
                .andDeviceNumIn(deviceNumList)
                .andDayTagBetween(DateUtil.format(beginTime, Util.STR_DATE_FORMAT),
                        DateUtil.format(endTime, Util.STR_DATE_FORMAT));
        List<RptDayGps> entities = gpsMapper.selectByExample(example);
        if (CollUtil.isEmpty(entities)) {
            return new HashMap<>();
        }
        return entities.stream()
                .collect(Collectors.groupingBy(RptDayGps::getDeviceNum));
    }

    /**
     * 综合查询动态数据
     * @date 2023/3/23
     * @author zhjd
     */
    private List<ReportGpsData> buildGpsByDynamicTrip(String key,
                                                      Date beginTime,
                                                      Date endTime,
                                                      String coorType,
                                                      List<RptDayGps> dbList,
                                                      List<ReportGpsData> dtoList) {

        if (CollUtil.isEmpty(dbList) || CollUtil.isEmpty(dtoList)) {
            // 1. 数据库无结果，使用分段轨迹计算里程
            dtoList = new ArrayList<>();
            dtoList.add(buildGpsByDynamicTrip(key, beginTime, endTime, coorType));
            return dtoList;
        }

        Date dbFrom = DateUtil.parse(dbList.get(0).getDayTag() + " 00:00:00", Util.STR_YYYY_MM_DD_HH_MM_SS);
        Date dbTo = DateUtil.parse(dbList.get(dbList.size() - 1).getDayTag() + " 23:59:59", Util.STR_YYYY_MM_DD_HH_MM_SS);

        if (DateUtil.betweenMs(dbFrom, beginTime) == 0 && DateUtil.betweenMs(dbTo, endTime) == 0) {
            // 2. 查询范围与数据库返回一致，无需处理
            return dtoList;
        }

        if (DateUtil.between(beginTime, endTime, DateUnit.MINUTE) <= 24 * 60L) {
            // 3. 查询小于24小时的里程，使用分段轨迹计算里程
            dtoList = new ArrayList<>();
            dtoList.add(buildGpsByDynamicTrip(key, beginTime, endTime, coorType));
            return dtoList;
        }

        // 比较数据库返回数据时间范围与beginTime,endTime
        if (dbFrom.after(beginTime)) {
            // 4. 数据库中缺失前段里程，累计数据库数据+分段里程
            dtoList.add(0, buildGpsByDynamicTrip(key, beginTime, dbFrom, coorType));
        }
        if (dbTo.before(endTime)) {
            // 5. 数据库中缺失后段里程，累计数据库数据+分段里程
            dtoList.add(buildGpsByDynamicTrip(key, dbTo, endTime, coorType));
        }

        Date dbFromPlus = DateUtil.offsetDay(dbFrom, 1);
        Date dbToMinus = DateUtil.offsetDay(dbTo, -1);
        if (dbFrom.before(beginTime) && !dbFromPlus.before(beginTime)) {
            // 6. 数据库返回超过开始时间，即开始时间落在一天之中，需移除数据库返回第一天的里程，并累计开始时间至当天（dbFrom加一天，或beginTime当天最后时间）分段里程
            dbList.remove(0);
            dbList.addAll(buildRptDayGpsByDynamicTrip(key, beginTime, dbFromPlus));
            dbList.sort(Comparator.comparing(RptDayGps::getGpsTime));
            dtoList = new ArrayList<>();
            dtoList.add(buildMileageByEntity(dbList));
        }
        if (dbTo.after(endTime) && !dbToMinus.after(endTime)) {
            // 7. 数据库返回超过结束时间，即结束时间落在一天之中，需移除数据库返回最后一天的里程，并累计dbTo减一天至结束时间的分段里程
            if (dbList.size() > 1) {
                dbList.remove(dbList.size() - 1);
            }
            dbList.addAll(buildRptDayGpsByDynamicTrip(key, dbToMinus, endTime));
            dbList.sort(Comparator.comparing(RptDayGps::getGpsTime));
            dtoList = new ArrayList<>();
            dtoList.add(buildMileageByEntity(dbList));
        }

        return dtoList;
    }

    /**
     * 使用动态数据生成里程报表查询对象
     * @date 2023/3/23
     * @author zhjd
     */
    private ReportGpsData buildGpsByDynamicTrip(String deviceNum,
                                                Date beginTime,
                                                Date endTime,
                                                String coorType) {
        List<TripGps> tripCalculates = TraceRequest.Builder
                .create(deviceNum, beginTime, endTime)
                .coorType(coorType)
                .getTrips();
        if (CollUtil.isEmpty(tripCalculates)) {
            return null;
        }
        ReportGpsData gps = new ReportGpsData();
        TripGps finalTrip = tripCalculates.get(tripCalculates.size() - 1);
        BeanUtil.copyProperties(finalTrip, gps);
        gps.setGpsTime(finalTrip.getEndTime());
        gps.setLat(finalTrip.getEndLat());
        gps.setLng(finalTrip.getEndLng());
        gps.setAddress(finalTrip.getEndAddress());
        gps.setFuelConsumption(tripCalculates.stream().mapToInt(TripGps::getFuelConsumption).sum());
        gps.setMileage(tripCalculates.stream().mapToDouble(item -> item.getMileage().doubleValue()).sum());
        return gps;
    }

    /**
     * 使用静态数据生成里程统计查询对象
     * @date 2023/3/23
     * @author zhjd
     */
    private ReportGpsData buildMileageByEntity(List<RptDayGps> list) {
        ReportGpsData gps = new ReportGpsData();
        if (CollUtil.isEmpty(list)) {
            return gps;
        }
        RptDayGps finalEntity = list.get(list.size() - 1);
        BeanUtil.copyProperties(finalEntity, gps);
        gps.setFuelConsumption(list.stream().mapToInt(RptDayGps::getFuelConsumption).sum());
        gps.setMileage(list.stream().mapToDouble(item -> item.getDayMileage().doubleValue()).sum());
        return gps;
    }

    /**
     * 使用静态数据生成日里程报表查询对象
     * @date 2023/3/23
     * @author zhjd
     */
    private List<ReportGpsData> buildReportGpsData(List<RptDayGps> list, CoordinateType coorType) {
        return list.stream()
                .map(entity -> {
                    ReportGpsData gps = new ReportGpsData();
                    BeanUtil.copyProperties(entity, gps);
                    gps.setDayTag(gps.getGpsTime());
                    if (CoordinateType.BD09 == coorType) {
                        gps.setLng(entity.getLngBd().doubleValue());
                        gps.setLat(entity.getLatBd().doubleValue());
                    }
                    if (CoordinateType.GCJ02 == coorType) {
                        gps.setLng(entity.getLngGc().doubleValue());
                        gps.setLat(entity.getLatGc().doubleValue());
                    }
                    gps.setMileage(entity.getDayMileage().doubleValue());
                    gps.setIsMoving(Util.asInt(entity.getIsmoving()) > 0);
                    gps.setIsAccOn(Util.asInt(entity.getAlmAccon()) > 0);
                    gps.setDirection(entity.getDirectionName());
                    gps.buildStatus();
                    return gps;
                })
                .collect(Collectors.toList());
    }

    /**
     * 根据动态轨迹分段生成日里程存储对象
     * @date 2023/3/24
     * @author zhjd
     */
    private List<RptDayGps> buildRptDayGpsByDynamicTrip(String deviceNum, Date beginTime, Date endTime) {
        List<RptTrips> trips = tripsService.buildRptTripListByDynamicTrip(deviceNum, beginTime, endTime);
        if (CollUtil.isEmpty(trips)) {
            return new ArrayList<>();
        }
        return buildRptDayGpsList(trips);
    }
}
