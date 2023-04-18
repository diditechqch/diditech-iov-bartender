package com.diditech.iov.gps.app.device.config;

import dd.monitor.dm.model.KEY;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class DeviceDataConfig {

    @Bean("wifiKeys")
    public String[] getWifiKeys() {
        return new String[]{KEY.MACS_CNT, KEY.MACS};
    }

    @Bean("lbsKeys")
    public String[] getLbsKeys() {
        return new String[]{KEY.MCC, KEY.MNC, "基站信息", KEY.STATION_COUNT};
    }

    @Bean("obdKeys")
    public String[] getObdKeys() {
        return new String[]{KEY.OBD_ACC, KEY.OBD_WT, KEY.OBD_V,
                KEY.OBD_SPEED, KEY.OBD_ROTATION, KEY.OBD_AVGOIL,
                KEY.OBD_INSOIL, KEY.OBD_MILEAGE, KEY.OBD_FUELCONSUM,
                KEY.OBD_OIL1, KEY.OBD_OIL2, KEY.OBD_INSOIL1,
                KEY.OBD_INSOIL2, KEY.OBD_OIL, KEY.OBD_FUEL, KEY.OBD_ERROR};
    }

    @Bean("gpsKeys")
    public String[] getGpsKeys() {
        return new String[]{KEY.DEVICE_NUM, KEY.GPS_TIME,
                KEY.SAT_COUNT, KEY.LAT, KEY.LNG, KEY.SPEED,
                KEY.AV, KEY.ALT, KEY.DIRECTION, KEY.DIRECTION_NAME};
    }

    @Bean("journeyKeys")
    public String[] getJourneyKeys() {
        return new String[]{KEY.ACC_START_TIME, KEY.ACC_STOP_TIME, KEY.TRIP_MILEAGE,
                KEY.TRIP_FUEL, KEY.TRIP_AVOIL, KEY.TRIP_MAXVEO, KEY.TRIP_AVVEO,
                KEY.TRIP_JIASU, KEY.TRIP_JIANSU, KEY.TRIP_ZHUANWAN, KEY.TRIP_DURATION,
                KEY.TRIP_IDEL_DURATION, KEY.TRIP_MOVING_DURATION};
    }

    @Bean("almKeys")
    public String[] getAlmKeys() {
        return new String[]{KEY.ALM_SOS, KEY.ALM_LOWPOWER,
                KEY.ALM_VEH_LOWBATTERY, KEY.ALM_CUTPOWER, KEY.ALM_PSEUDO_STATION,
                KEY.DETECT_PSEUDO_STATION, KEY.ALM_SHAKE, KEY.ALM_CUTWIRE,
                KEY.ALM_CRASH, KEY.ALM_DISPLACE, KEY.ALM_STATUS,
                KEY.ALM_REMOVED, KEY.ISCHARGING, KEY.CUT_FUEL_ELECTRICITY, KEY.ACC};
    }

    @Bean("statusKeys")
    public String[] getStatusKeys() {
        return new String[]{KEY.SIGNAL, KEY.SIGNAL_LEVEL, KEY.TIMEZONE_TYPE, KEY.TIMEZONE_VAL,
                KEY.SEQ, KEY.REPORT_TYPE,
                KEY.PROTOCOL_VER, KEY.ORI_MILEAGE, KEY.TODAY_MILEAGE,
                KEY.TOTAL_MILEAGE, KEY.HARD_MILEAGE, KEY.MSG_READABLE,
                KEY.AUTH_KEY, KEY.CMD_MARK, KEY.CMD_TYPE,
                KEY.CMD_RESULT, KEY.VIN, KEY.LOC_MODE,
                KEY.WIRELESS_MODE, KEY.IS_RESEND, KEY.POWER, KEY.POWER_LEVEL, "电压等级"};
    }

    @Bean("redundantKeys")
    public Set<String> getRedundantKeys() {
        Set<String> redundantKeys = new HashSet<>(40);
        redundantKeys.add(KEY.MESSAGE);
        redundantKeys.add(KEY.REPLY_DATA);
        redundantKeys.add(KEY.REPORT_TYPE);
        redundantKeys.add(KEY.DATA_TYPE);
        redundantKeys.add(KEY.DEVICE_TYPE);
        redundantKeys.add(KEY.PROTOCOL_TYPE);
        redundantKeys.add(KEY.ALM_MODE);
        redundantKeys.add(KEY.DIRECTION_NAME);
        redundantKeys.add(KEY.EW);
        redundantKeys.add(KEY.NS);
        redundantKeys.add(KEY.MEDIA_STATUS);
        redundantKeys.add(KEY.LAC);
        redundantKeys.add(KEY.CELL_ID);
        redundantKeys.add(KEY.CELL_DBM);
        redundantKeys.add(KEY.LAC1);
        redundantKeys.add(KEY.CELL_ID1);
        redundantKeys.add(KEY.CELL_DBM1);
        redundantKeys.add(KEY.LAC2);
        redundantKeys.add(KEY.CELL_ID2);
        redundantKeys.add(KEY.CELL_DBM2);
        redundantKeys.add(KEY.LAC3);
        redundantKeys.add(KEY.CELL_ID3);
        redundantKeys.add(KEY.CELL_DBM3);
        redundantKeys.add(KEY.LAC4);
        redundantKeys.add(KEY.CELL_ID4);
        redundantKeys.add(KEY.CELL_DBM4);
        redundantKeys.add(KEY.LAC5);
        redundantKeys.add(KEY.CELL_ID5);
        redundantKeys.add(KEY.CELL_DBM5);
        redundantKeys.add(KEY.LAC6);
        redundantKeys.add(KEY.CELL_ID6);
        redundantKeys.add(KEY.CELL_DBM6);
        redundantKeys.add(KEY.DEVICE_NUM_HEX);
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
