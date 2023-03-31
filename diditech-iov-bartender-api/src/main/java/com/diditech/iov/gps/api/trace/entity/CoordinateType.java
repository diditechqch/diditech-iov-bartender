package com.diditech.iov.gps.api.trace.entity;


import java.util.Arrays;

/**
 * 坐标系
 * @author zhjd <br>
 * @date 2021/2/20 <br>
 */
public enum CoordinateType {
    BD09, // 百度
    GCJ02, // 国测、高德
    WGS84; // 火星（原始）

    public static CoordinateType get(String value) {
        if (value == null) {
            return BD09;
        }
        return Arrays.stream(CoordinateType.values())
                .filter(item -> item.name().equals(value.toUpperCase()))
                .findFirst()
                .orElse(BD09);
    }
}
