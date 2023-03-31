package com.diditech.iov.gps.app.core.util;

import java.util.regex.Pattern;

/**
 * 静态变量
 * @author zhjd
 * @date 2019/2/18
 */
public class Const {

    private Const() {
    }

    public static final String DATE_FORMAT_GPS_TABLE = "yyyyMMddHHmmss";
    public static final String LOG_SCHEMA_NAME = "gps_log";
    public static final String LOG_TABLE_NAME = "gps_log_%s";
    public static final String GPS_TABLE_PREFIX = "GPS_";
    public static final String GPS_TABLE_NAME = "dd_%s.gps_%s";

    public static final int IS_OFFLINE = 1;
    public static final int IS_ONLINE = 0;
    public static final int IS_MOVING = 1;
    public static final int IS_STOP = 0;

    public static final int NONE_ACC = 1;
    public static final int ACC = 2;

    public static final String REGEX_LINE = "\\|";
    public static final String REGEX_TAB = "\t";
    public static final String REGEX_NEWLINE = "\n";
    public static final String REGEX_RETURN = "\r";
    public static final String SEP_COMMA = ",";
    public static final String SEP_LINE = "|";
    public static final String SPACE = " ";
    public static final String EMPTY = "";

    public static final String SEP_SEMICOLON = ";";

    /**
     * LBS有效国家代码：460
     */
    public static final int LBS_MCC = 460;


    /**
     * LBS信息格式校验
     */
    public static final Pattern LBS_FORMAT = Pattern.compile("^([0-9]{1,5}\\,[0-9]{1,9}(\\,\\-?[0-9]{1,3}\\|?))*?$");

    /**
     * LBS信号强度默认值
     */
    public static final String LBS_DEFAULT_DBM = "-65";

    /**
     * LBS信号强度最大值
     */
    public static final int LBS_MAX_DBM = 140;

    /**
     * LBS信号强度最小值
     */
    public static final int LBS_MIN_DBM = -140;
}

