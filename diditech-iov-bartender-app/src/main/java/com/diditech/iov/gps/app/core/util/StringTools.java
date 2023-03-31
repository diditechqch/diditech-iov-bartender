package com.diditech.iov.gps.app.core.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringTools {

    public static Integer getGpsTableNumber(String tableName) {
        return Integer.valueOf(tableName.split("_")[1]);
    }

    public static boolean filterGpsTableName(int beginNumber, int endNumber, String tableName) {
        Integer tableNumber = null;
        try {
            tableNumber = getGpsTableNumber(tableName);
        } catch (NumberFormatException e) {
            log.error("filterGpsTableName error!tableName:{}", tableName);
        }
        if (null == tableNumber) {
            return false;
        }
        return tableNumber >= beginNumber
                && tableNumber <= endNumber;
    }

}
