package com.diditech.iov.gps.app.device.util;

import com.diditech.utils.Util;

import java.util.HashMap;
import java.util.Map;

public class DecodeUtil {

    public static String getReportTypeDesc(Object code) {
        switch (Util.asInt(code)) {
            case (0):
                return "定时上报";
            case (1):
                return "定距上报";
            case (2):
                return "拐点补偿";
            case (3):
                return "ACC状态改变上报";
            case (4):
                return "静止后补传（从运动变为静止状态后补传最后定位点）";
            case (5):
                return "网络断开上报（重连后，上报之前最后一个有效上传点）";
            case (6):
                return "星历更新上报";
            case (7):
                return "按键上报";
            case (8):
                return "开机上报";
            case (10):
                return "静止后上报（设备静止后上报最后的经纬度，时间更新）";
            case (11):
                return "WIFI解析经纬度上传包";
            case (12):
                return "LJDW（立即定位）指令上报";
            case (13):
                return "设备静止后上报最后的经纬度";
            case (14):
                return "GPSDUP上传（下静止状态定时上传）";
            case (15):
                return "新的故障码上报";
            case (16):
                return "车身状态变化上报";
            case (17):
                return "正常定位省电模式LBS上报";
            case (18):
                return "精确定位省电模式GPS上报";
            case (19):
                return "紧急定位追踪上报	";
            default:
                return null;
        }
    }

    public static String getSignalDesc(Object code) {
        switch (Util.asInt(code)) {
            case (1):
                return "极差";
            case (2):
                return "差";
            case (3):
                return "中";
            case (4):
                return "强";
            default:
                return Util.asString(code);
        }
    }

    public static Map<String, String> aboutBsj() {
        Map<String, String> about = new HashMap<>();
        about.put("80", "GPS包");
        about.put("85", "终端响应");
        about.put("d6", "校时包");
        about.put("d8", "上发申请设置参数");
        return about;
    }

    public static Map<String, String> aboutBk() {
        Map<String, String> about = new HashMap<>();
        about.put("01", "位置上报");
        about.put("12", "指令应答");
        return about;
    }

    public static Map<String, String> aboutG21() {
        Map<String, String> about = new HashMap<>();
        about.put("0081", "登录包");
        about.put("0082", "心跳包");
        about.put("0101", "点名应答");
        about.put("0181", "监控数据");
        about.put("0182", "报警包");
        about.put("0183", "GPS包");
        about.put("4183", "GPS包");
        about.put("0184", "盲区补传");
        return about;
    }

    public static Map<String, String> aboutKks() {
        Map<String, String> about = new HashMap<>();
        about.put("01", "登录包");
        about.put("13", "心跳包");
        about.put("21", "终端响应包");
        about.put("22", "GPS定位包");
        about.put("26", "报警包 （单围栏）");
        about.put("27", "报警包 （多围栏）");
        about.put("28", "LBS多基站扩展信息包");
        about.put("2C", "WIFI包");
        about.put("8a", "校时包");
        about.put("94", "终端向服务器发送数据包");
        about.put("8c", "OBD包");
        about.put("15", "终端向服务器发送的字符串信息");
        about.put("16", "报警包");
        about.put("2a", "查询地址");
        about.put("1c", "终端响应");
        about.put("18", "多基站包");
        return about;
    }

    public static Map<String, String> aboutJt808() {
        Map<String, String> about = new HashMap<>();
        about.put("0001", "终端响应");
        about.put("0002", "心跳包");
        about.put("0003", "注销包");
        about.put("0100", "注册包");
        about.put("0102", "鉴权包");
        about.put("0104", "查询终端参数应答");
        about.put("0107", "查询指定终端参数");
        about.put("0108", "终端升级结果通知");
        about.put("0200", "gps包");
        about.put("0201", "位置信息查询应答");
        about.put("0301", "事件报告");
        about.put("0302", "提问应答");
        about.put("0303", "信息点播/取消");
        about.put("0500", "车辆控制应答");
        about.put("0700", "行驶记录数据上传");
        about.put("0701", "电子运单上报");
        about.put("0702", "驾驶员身份信息");
        about.put("0704", "盲区补传");
        about.put("0705", "can总线数据上传");
        about.put("0800", "多媒体事件信息");
        about.put("0801", "多媒体数据");
        about.put("0802", "存储多媒体数据检索");
        about.put("0805", "多媒体数据上传应答");
        about.put("0900", "数据上行透传");
        about.put("0901", "数据压缩上报");
        about.put("0a00", "终端rsa公钥");
        about.put("0f08", "校时包");
        about.put("0ff4", "查询工作模式响应包");
        return about;
    }

    public static Map<String, String> aboutGolo() {
        Map<String, String> about = new HashMap<>();
        about.put("0001", "终端响应");
        about.put("0002", "心跳包 ");
        about.put("0102", "登录包");
        about.put("0104", "查询参数应答");
        about.put("0200", "GPS包");
        about.put("0201", "报警包");
        about.put("0202", "位置信息查询应答");
        about.put("0205", "行程包");
        about.put("0206", "基本信息包");
        about.put("0208", "CAN数据连续包");
        about.put("0209", "故障码信息");
        about.put("0212", "休眠唤醒");
        about.put("0213", "休眠进入");
        about.put("0214", "车身状态信息");
        about.put("0215", "拐点补偿数据");
        about.put("0220", "一键检测应答");
        about.put("0221", "全车扫描应答");
        about.put("0900", "设备上行");
        about.put("0003", "GPS包");
        about.put("0004", "OBD数据流");
        about.put("0005", "故障码数据");
        about.put("0006", "配置信息");
        about.put("0007", "报警包");
        about.put("0008", "行程统计数据");
        about.put("0009", "打包数据");
        about.put("V1", "登录包");
        about.put("V4", "终端响应");
        about.put("V4*", "设置应答包");
        about.put("V8", "心跳包");
        about.put("V9", "行程开始包");
        about.put("V10", "行程结束包");
        about.put("I7", "透传包上行");
        about.put("I10", "一键检测数据");
        about.put("I11", "全车检测数据");
        about.put("I14", "终端参数包");
        about.put("$", "定时上报数据包");
        return about;
    }

}
