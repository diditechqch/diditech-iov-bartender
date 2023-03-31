package com.diditech.iov.gps.app.device.config;

import dd.monitor.dm.model.DeviceData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class DeviceDataConfig {

    @Bean("wifiKeys")
    public String[] getWifiKeys() {
        return new String[]{DeviceData.KEY_MACS_CNT, DeviceData.KEY_MACS};
    }

    @Bean("lbsKeys")
    public String[] getLbsKeys() {
        return new String[]{DeviceData.KEY_MCC, DeviceData.KEY_MNC, "基站信息", DeviceData.KEY_STATION_COUNT};
    }

    @Bean("obdKeys")
    public String[] getObdKeys() {
        return new String[]{DeviceData.KEY_OBD_ACC, DeviceData.KEY_OBD_WT, DeviceData.KEY_OBD_V,
                DeviceData.KEY_OBD_SPEED, DeviceData.KEY_OBD_ROTATION, DeviceData.KEY_OBD_AVGOIL,
                DeviceData.KEY_OBD_INSOIL, DeviceData.KEY_OBD_MILEAGE, DeviceData.KEY_OBD_FUELCONSUM,
                DeviceData.KEY_OBD_OIL1, DeviceData.KEY_OBD_OIL2, DeviceData.KEY_OBD_INSOIL1,
                DeviceData.KEY_OBD_INSOIL2, DeviceData.KEY_OBD_OIL, DeviceData.KEY_OBD_FUEL, DeviceData.KEY_OBD_ERROR};
    }

    @Bean("gpsKeys")
    public String[] getGpsKeys() {
        return new String[]{DeviceData.KEY_DEVICE_NUM, DeviceData.KEY_GPS_TIME,
                DeviceData.KEY_SAT_COUNT, DeviceData.KEY_LAT, DeviceData.KEY_LNG, DeviceData.KEY_SPEED,
                DeviceData.KEY_AV, DeviceData.KEY_ALT, DeviceData.KEY_DIRECTION, DeviceData.KEY_DIRECTION_NAME};
    }

    @Bean("journeyKeys")
    public String[] getJourneyKeys() {
        return new String[]{DeviceData.KEY_ACC_START_TIME, DeviceData.KEY_ACC_STOP_TIME, DeviceData.KEY_TRIP_MILEAGE,
                DeviceData.KEY_TRIP_FUEL, DeviceData.KEY_TRIP_AVOIL, DeviceData.KEY_TRIP_MAXVEO, DeviceData.KEY_TRIP_AVVEO,
                DeviceData.KEY_TRIP_JIASU, DeviceData.KEY_TRIP_JIANSU, DeviceData.KEY_TRIP_ZHUANWAN, DeviceData.KEY_TRIP_DURATION,
                DeviceData.KEY_TRIP_IDEL_DURATION, DeviceData.KEY_TRIP_MOVING_DURATION};
    }

    @Bean("almKeys")
    public String[] getAlmKeys() {
        return new String[]{DeviceData.KEY_ALM_SOS, DeviceData.KEY_ALM_LOWPOWER,
                DeviceData.KEY_ALM_VEH_LOWBATTERY, DeviceData.KEY_ALM_CUTPOWER, DeviceData.KEY_ALM_PSEUDO_STATION,
                DeviceData.KEY_DETECT_PSEUDO_STATION, DeviceData.KEY_ALM_SHAKE, DeviceData.KEY_ALM_CUTWIRE,
                DeviceData.KEY_ALM_CRASH, DeviceData.KEY_ALM_DISPLACE, DeviceData.KEY_ALM_STATUS,
                DeviceData.KEY_ALM_REMOVED, DeviceData.KEY_ISCHARGING, DeviceData.KEY_CUT_FUEL_ELECTRICITY, DeviceData.KEY_ACC};
    }

    @Bean("statusKeys")
    public String[] getStatusKeys() {
        return new String[]{DeviceData.KEY_SIGNAL, DeviceData.KEY_SIGNAL_LEVEL, DeviceData.KEY_TIMEZONE_TYPE, DeviceData.KEY_TIMEZONE_VAL,
                DeviceData.KEY_SEQ, DeviceData.KEY_REPORT_TYPE,
                DeviceData.KEY_PROTOCOL_VER, DeviceData.KEY_ORI_MILEAGE, DeviceData.KEY_TODAY_MILEAGE,
                DeviceData.KEY_TOTAL_MILEAGE, DeviceData.KEY_HARD_MILEAGE, DeviceData.KEY_MSG_READABLE,
                DeviceData.KEY_AUTH_KEY, DeviceData.KEY_CMD_MARK, DeviceData.KEY_CMD_TYPE,
                DeviceData.KEY_CMD_RESULT, DeviceData.KEY_VIN, DeviceData.KEY_LOC_MODE,
                DeviceData.KEY_WIRELESS_MODE, DeviceData.KEY_IS_RESEND, DeviceData.KEY_POWER, DeviceData.KEY_POWER_LEVEL, "电压等级"};
    }

    @Bean("redundantKeys")
    public Set<String> getRedundantKeys() {
        Set<String> redundantKeys = new HashSet<>(40);
        redundantKeys.add(DeviceData.KEY_MEDIA_STATUS);
        redundantKeys.add(DeviceData.KEY_MESSAGE);
        redundantKeys.add(DeviceData.KEY_REPLY_DATA);
        redundantKeys.add(DeviceData.KEY_REPORT_TYPE);
        redundantKeys.add(DeviceData.KEY_DATA_TYPE);
        redundantKeys.add(DeviceData.KEY_DEVICE_TYPE);
        redundantKeys.add(DeviceData.KEY_PROTOCOL_TYPE);
        redundantKeys.add(DeviceData.KEY_ALM_MODE);
        redundantKeys.add(DeviceData.KEY_DIRECTION_NAME);
        redundantKeys.add(DeviceData.KEY_EW);
        redundantKeys.add(DeviceData.KEY_NS);
        redundantKeys.add(DeviceData.KEY_MEDIA_STATUS);
        redundantKeys.add(DeviceData.KEY_LAC);
        redundantKeys.add(DeviceData.KEY_CELL_ID);
        redundantKeys.add(DeviceData.KEY_CELL_DBM);
        redundantKeys.add(DeviceData.KEY_LAC);
        redundantKeys.add(DeviceData.KEY_CELL_ID);
        redundantKeys.add(DeviceData.KEY_CELL_DBM);
        redundantKeys.add(DeviceData.KEY_LAC1);
        redundantKeys.add(DeviceData.KEY_CELL_ID1);
        redundantKeys.add(DeviceData.KEY_CELL_DBM1);
        redundantKeys.add(DeviceData.KEY_LAC2);
        redundantKeys.add(DeviceData.KEY_CELL_ID2);
        redundantKeys.add(DeviceData.KEY_CELL_DBM2);
        redundantKeys.add(DeviceData.KEY_LAC3);
        redundantKeys.add(DeviceData.KEY_CELL_ID3);
        redundantKeys.add(DeviceData.KEY_CELL_DBM3);
        redundantKeys.add(DeviceData.KEY_LAC4);
        redundantKeys.add(DeviceData.KEY_CELL_ID4);
        redundantKeys.add(DeviceData.KEY_CELL_DBM4);
        redundantKeys.add(DeviceData.KEY_LAC5);
        redundantKeys.add(DeviceData.KEY_CELL_ID5);
        redundantKeys.add(DeviceData.KEY_CELL_DBM5);
        redundantKeys.add(DeviceData.KEY_LAC6);
        redundantKeys.add(DeviceData.KEY_CELL_ID6);
        redundantKeys.add(DeviceData.KEY_CELL_DBM6);
        redundantKeys.add(DeviceData.KEY_DEVICE_NUM_HEX);
        return redundantKeys;
    }

    @Bean("allKeys")
    public List<String[]> getAllKeys(@Qualifier("wifiKeys") String[] wifiKeys,
                                     @Qualifier("lbsKeys") String[] lbsKeys,
                                     @Qualifier("obdKeys") String[] obdKeys,
                                     @Qualifier("gpsKeys") String[] gpsKeys,
                                     @Qualifier("journeyKeys") String[] journeyKeys,
                                     @Qualifier("almKeys") String[] almKeys,
                                     @Qualifier("statusKeys") String[] statusKeys) {
        List<String[]> allKeys = new ArrayList<>(7);
        allKeys.add(gpsKeys);
        allKeys.add(lbsKeys);
        allKeys.add(wifiKeys);
        allKeys.add(almKeys);
        allKeys.add(statusKeys);
        allKeys.add(obdKeys);
        allKeys.add(journeyKeys);
        return allKeys;
    }

    @Bean("allKeysPlat")
    public Set<String> getAllKeysPlat(@Qualifier("allKeys") List<String[]> allKeys) {
        return allKeys.stream().flatMap(Arrays::stream).collect(Collectors.toSet());
    }
}
