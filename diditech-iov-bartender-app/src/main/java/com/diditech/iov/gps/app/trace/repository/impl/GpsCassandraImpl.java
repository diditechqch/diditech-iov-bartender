package com.diditech.iov.gps.app.trace.repository.impl;

import com.diditech.iov.gps.api.trace.entity.CoordinateType;
import com.diditech.iov.gps.api.trace.entity.GpsInfoTripMin;
import com.diditech.iov.gps.api.trace.entity.TraceInfo;
import com.diditech.iov.gps.app.core.util.StringTools;
import com.diditech.iov.gps.app.trace.config.GpsCassandraConfig;
import com.diditech.iov.gps.app.trace.po.GpsEntity;
import com.diditech.iov.gps.app.trace.po.GpsSearchCondition;
import com.diditech.iov.gps.app.trace.repository.GpsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Primary
@Repository
@ConditionalOnBean(GpsCassandraConfig.class)
public class GpsCassandraImpl implements GpsDao {

    @Value("${datasource.trace.cassandra.keyspace-name}")
    private String keySpaceName;

    private static final String COUNT_GPS_DATA_SQL = "SELECT\n" +
            " count(1)\n" +
            " FROM %s\n" +
            " WHERE\n" +
            " DEVICE_NUM='%s'\n" +
            " AND GPS_TIME>='%s'\n" +
            " AND GPS_TIME<='%s'";

    private static final String LOAD_GPS_TABLES_SQL =
            "select table_name from system_schema.tables where keyspace_name = '%s'";


    private static final String GET_TRACE_SQL = "SELECT \n" +
            " GPS_TIME AS gpsTime,\n" +
            " LNG_BD AS lngBd,\n" +
            " LAT_BD AS latBd,\n" +
            " LNG_GC AS lngGc,\n" +
            " LAT_GC AS latGc,\n" +
            " DIRECTION,\n" +
            " DIRECTION_NAME AS directionName,\n" +
            " ADDRESS AS address,\n" +
            " STATUS AS STATUS,\n" +
            " CREATE_TIME AS createTime,\n" +
            " IS_RESEND AS isOld,\n" +
            " ORI_MILEAGE AS oriMileage,\n" +
            " TOTAL_MILEAGE AS totalMileage,\n" +
            " OBD_SPEED AS obdSpeed,\n" +
            " OBD_MESSAGE AS obdMessage,\n" +
            " SPEED AS SPEED\n" +
            " FROM %s\n" +
            " WHERE\n" +
            " DEVICE_NUM='%s'\n" +
            " AND GPS_TIME>='%s'\n" +
            " AND GPS_TIME<='%s'\n" +
            " ORDER BY GPS_TIME ASC";

    private static final String GET_TRACE_TRIP_MIN_SQL = "SELECT \n" +
            " GPS_TIME AS gpsTime, \n" +
            " %s " +
            " OBD_SPEED AS obdSpeed,\n" +
            " SPEED AS speed,\n" +
            " TOTAL_MILEAGE AS totalMileage,\n" +
            " ACC \n" + // add by zhjd 查询增加点火字段
            " FROM %s \n" +
            " WHERE \n" +
            " DEVICE_NUM='%s'\n" +
            " AND GPS_TIME>='%s'\n" +
            " AND GPS_TIME<='%s'\n" +
            " ORDER BY GPS_TIME ASC";

    private static final String GET_GPS_SQL = "SELECT \n" +
            " DEVICE_NUM AS deviceNum,\n" +
            " GPS_TIME AS gpsTime,\n" +
            " LNG,\n" +
            " LAT,\n" +
            " LNG_BD AS lngBd,\n" +
            " LAT_BD AS latBd,\n" +
            " LNG_GC AS lngGc,\n" +
            " LAT_GC AS latGc,\n" +
            " SPEED,\n" +
            " OBD_SPEED AS obdSpeed,\n" +
            " ACC,\n" +
            " OBD_ACC AS obdAcc,\n" +
            " OBD_MESSAGE AS locMode\n" +
            " FROM %s\n" +
            " WHERE\n" +
            " DEVICE_NUM='%s'\n" +
            " AND GPS_TIME<='%s'\n" +
            " ORDER BY GPS_TIME DESC LIMIT 1";

    @Autowired
    private CassandraTemplate cassandraTemplate;

    @Override
    public int countGpsData(List<GpsSearchCondition> conditionList) {
        return conditionList.stream().mapToInt(this::countGpsData)
                .reduce(0, Integer::sum);
    }

    @Override
    public List<String> loadGpsTableListFromDb(String beginTableName, String endTableName) {
        final Integer beginNumber = StringTools.getGpsTableNumber(beginTableName);
        final Integer endNumber = StringTools.getGpsTableNumber(endTableName);
        return cassandraTemplate.stream(String.format(LOAD_GPS_TABLES_SQL, keySpaceName), String.class)
                .filter(tableName -> StringTools.filterGpsTableName(beginNumber, endNumber, tableName))
                .collect(Collectors.toList());
    }

    @Override
    public List<TraceInfo> getTrace(List<GpsSearchCondition> conditionList) {
        return conditionList.stream()
                .map(this::getTrace)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<TraceInfo> getTrace(GpsSearchCondition condition) {
        String cql = String.format(GET_TRACE_SQL,
                condition.getGpsTable(), condition.getDeviceNum(),
                condition.getBeginTime(), condition.getEndTime());
        return cassandraTemplate.stream(cql, TraceInfo.class)
                .peek(detail -> detail.fillSpeedData().fillLocMode())
                .collect(Collectors.toList());
    }


    public int countGpsData(GpsSearchCondition condition) {
        String cql = String.format(COUNT_GPS_DATA_SQL, condition.getGpsTable(),
                condition.getDeviceNum(), condition.getBeginTime(),
                condition.getEndTime());
        return cassandraTemplate.selectOne(cql, Integer.class);
    }

    @Override
    public List<GpsInfoTripMin> getTraceTripMin(List<GpsSearchCondition> conditionList, String coorType) {
        return conditionList.stream()
                .map(condition -> getTraceTripMin(condition, coorType))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public GpsEntity getGpsEntity(GpsSearchCondition condition) {
        String cql = String.format(GET_GPS_SQL,
                condition.getGpsTable(), condition.getDeviceNum(),
                condition.getBeginTime());
        return cassandraTemplate.stream(cql, GpsEntity.class)
                .filter(item -> !ObjectUtils.isEmpty(item.getLng()) && !ObjectUtils.isEmpty(item.getLat()))
                .peek(detail -> {
                    detail.setSpeed(ObjectUtils.isEmpty(detail.getObdSpeed()) ? detail.getSpeed() : detail.getObdSpeed());
                    detail.setAlmAccon(ObjectUtils.isEmpty(detail.getObdAcc()) ? detail.getAcc() : detail.getObdAcc());
                })
                .collect(Collectors.toList())
                .get(0);
    }

    public List<GpsInfoTripMin> getTraceTripMin(GpsSearchCondition condition, String coorType) {
        String cql = String.format(GET_TRACE_TRIP_MIN_SQL, getSqlColumn(coorType),
                condition.getGpsTable(), condition.getDeviceNum(),
                condition.getBeginTime(), condition.getEndTime());
        return cassandraTemplate.stream(cql, GpsInfoTripMin.class)
                .filter(item -> !ObjectUtils.isEmpty(item.getLng()) && !ObjectUtils.isEmpty(item.getLat())
                        && !ObjectUtils.isEmpty(item.getTotalMileage()))
                .peek(detail -> {
                    detail.setSpeed(ObjectUtils.isEmpty(detail.getObdSpeed()) ? detail.getSpeed() : detail.getObdSpeed());
                    detail.setTotalMileage(detail.getTotalMileage().setScale(3, BigDecimal.ROUND_HALF_UP));
                })
                .collect(Collectors.toList());
    }

    private String getSqlColumn(String coorType) {
        switch (CoordinateType.get(coorType)) {
            case GCJ02:
                return " LNG_GC AS lng,\n LAT_GC AS lat,\n";
            case WGS84:
                return " LNG AS lng,\n LAT AS lat,\n";
            default:
                return " LNG_BD AS lng,\n LAT_BD AS lat,\n";
        }
    }
}
