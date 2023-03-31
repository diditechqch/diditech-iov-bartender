package com.diditech.iov.gps.app.geo.address.config;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * 请求地图商接口配置项基类
 * @author zhjd
 * @date 2023/3/8
 */
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Slf4j
public class MapConfigBase {

    private String url;

    protected List<String> keyList;

    /**
     * 存储key可用状态
     */
    protected Map<String, String> keyStatusMap;

    /**
     * "1"可用，"0"不可用
     */
    public static final String OK = "1";
    public static final String NA = "0";

    protected String availableKey;

    /**
     * 是否显示指定位置周边的poi，false为不显示，true为显示。当值为true时，默认显示周边1000米内的poi。
     */
    private boolean usePoi;

    /**
     * 坐标的类型，目前支持的坐标类型包括：
     * bd09ll（百度经纬度坐标）、bd09mc（百度米制坐标）、gcj02ll（国测局经纬度坐标）、wgs84ll（ GPS经纬度）
     */
    private String coordtype;

    private String distanceUnit;

    public String getAvailableKey() {
        if (availableKey == null) {
            log.error("NO MORE AVAILABLE KEYS...");
            return getKeyList().get(RandomUtil.randomInt(0, getKeyList().size() - 1));
        }
        if (keyStatusMap.get(availableKey).equals(NA)) {
            log.debug("CHECK CHECK !!! {}", availableKey);
            for (String k : keyStatusMap.keySet()) {
                if (keyStatusMap.get(k).equals(OK)) {
                    availableKey = k;
                    return availableKey;
                }
            }
        }
        return availableKey;
    }

    public void markKeyNA(String key) {
        if (!keyStatusMap.containsKey(key)) {
            return;
        }

        keyStatusMap.put(key, NA);

        for (String k : keyStatusMap.keySet()) {
            if (keyStatusMap.get(k).equals(OK)) {
                availableKey = k;
                return;
            }
        }
        availableKey = null;
    }

    public void markKeyOK(String key) {
        if (!keyStatusMap.containsKey(key)) {
            return;
        }
        keyStatusMap.put(key, OK);
        if (availableKey == null) {
            availableKey = key;
        }
    }

    public boolean noMoreAvailableKey() {
        for (String k : keyStatusMap.keySet()) {
            if (keyStatusMap.get(k).equals(OK)) {
                return false;
            }
        }
        return true;
    }
}
