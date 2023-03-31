package com.diditech.iov.gps.common.domain;

import java.io.Serializable;

public class DeviceData implements Serializable {
    private static final long serialVersionUID = 8533019263382093636L;


    /**
     * 设备号（设备上传）
     */
    public static final String KEY_DEVICE_NUM = "DEVICE_NUM";

    /**
     * GPS时间
     */
    public static final String KEY_GPS_TIME = "GPS_TIME";

    /**
     * 数据接收时间
     */
    public static final String KEY_CREATE_TIME = "CREATE_TIME";

    /**
     * 设备电量百分比，整数
     */
    public static final String KEY_POWER = "POWER";
    public static final String KEY_POWER_LEVEL = "POWER_LEVEL";

    /**
     * GSM信号强度
     */
    public static final String KEY_SIGNAL = "SIGNAL";

    /**
     * GPS定位卫星个数
     */
    public static final String KEY_SAT_COUNT = "SAT_COUNT";

    /**
     * WIFI精度半径，单位米，整型
     * wifi accuracy radius by zhjd 20191021 start
     */
    public static final String KEY_WIFI_RADIUS = "WIFI_RADIUS";

    /**
     * 设备原始纬度
     */
    public static final String KEY_LAT = "LAT";

    /**
     * 设备原始经度
     */
    public static final String KEY_LNG = "LNG";

    /**
     * 纠偏坐标类型 0：地球坐标 1：火星坐标 2：百度坐标
     */
    public static final String KEY_GISFIX_TYPE = "GIXFIX_TYPE";

    // add by zhjd for bk200 20170715 start
    /**
     * 海拔高度（米）
     */
    public static final String KEY_ALT = "ALT";
    // add by zhjd for bk200 20170715 end

    /**
     * 国测纠偏纬度
     */
    public static final String KEY_LAT_GC = "LAT_GC";

    /**
     * 国测纠偏经度
     */
    public static final String KEY_LNG_GC = "LNG_GC";

    /**
     * 百度纠偏纬度
     */
    public static final String KEY_LAT_BD = "LAT_BD";

    /**
     * 百度纠偏经度
     */
    public static final String KEY_LNG_BD = "LNG_BD";

    /**
     * 设备类型
     */
    public static final String KEY_DEVICE_TYPE = "DEVICE_TYPE";

    /**
     * 数据类型
     */
    public static final String KEY_PROTOCOL_TYPE = "PROTOCOL_TYPE";

    /**
     * 时区东西
     */
    public static final String KEY_TIMEZONE_TYPE = "TIMEZONE_TYPE";

    /**
     * 时区值
     */
    public static final String KEY_TIMEZONE_VAL = "TIMEZONE_VAL";

    /**
     * 信息序列号
     */
    public static final String KEY_SEQ = "SEQ";

    /**
     * 速度
     */
    public static final String KEY_SPEED = "SPEED";

    /**
     * 2个连续实时定位点的平均速
     */
    public static final String KEY_AV_SPEED = "AV_SPEED";

    /**
     * 方向
     */
    public static final String KEY_DIRECTION = "DIRECTION";

    /**
     * 方向名称
     */
    public static final String KEY_DIRECTION_NAME = "DIRECTION_NAME";

    /**
     * 是否定位
     */
    public static final String KEY_AV = "AV";

    /**
     * 南北
     */
    public static final String KEY_NS = "NS";

    /**
     * 东西
     */
    public static final String KEY_EW = "EW";

    /**
     * 国家代码 MCC
     */
    public static final String KEY_MCC = "MCC";

    /**
     * 网络代码 MNC
     */
    public static final String KEY_MNC = "MNC";

    /**
     * 位置区码LAC
     */
    public static final String KEY_LAC = "LAC";

    /**
     * 基站的编号 CELL ID
     */
    public static final String KEY_CELL_ID = "CELL_ID";

    /**
     * 信号强度
     */
    public static final String KEY_CELL_DBM = "CELL_DBM";

    /**
     * ACC
     */
    public static final String KEY_ACC = "ACC";

    /**
     * 上报模式
     * 基于康凯斯上报模式定义，并添加自定义
     */
    public static final String KEY_REPORT_TYPE = "REPORT_TYPE";

    /**
     * monitor自定义上报模式: 有线设备200米停车位置重用
     */
    public static final String CORRECTION_Z0 = "Z0";

    /**
     * monitor自定义上报模式: 无线设备轨迹修正
     */
    public static final String CORRECTION_Z1 = "Z1";

    /**
     * monitor自定义上报模式: 有线设备停车状态修正
     */
    public static final String CORRECTION_Z2 = "Z2";

    /**
     * monitor自定义上报模式: 行驶并换算时速>240km/h 则为异常定位，经纬度及位置信息不变
     */
    public static final String CORRECTION_Z3 = "Z3";

    /**
     * monitor自定义上报模式: 关闭LBS定位
     */
    public static final String CORRECTION_Z4 = "Z4";

    /**
     * 原始里程差
     */
    public static final String KEY_ORI_MILEAGE = "ORI_MILEAGE";

    /**
     * 当日里程
     */
    public static final String KEY_TODAY_MILEAGE = "TODAY_MILEAGE";

    /**
     * 总里程
     */
    public static final String KEY_TOTAL_MILEAGE = "TOTAL_MILEAGE";

    /**
     * 硬件里程
     */
    public static final String KEY_HARD_MILEAGE = "HARD_MILEAGE";

    /**
     * 原始数据
     */
    public static final String KEY_MESSAGE = "MESSAGE";

    /**
     * 可读内容
     */
    public static final String KEY_MSG_READABLE = "MSG_READABLE";

    /**
     * 位置
     */
    public static final String KEY_ADDRESS = "ADDRESS";

    /**
     * 位置
     */
    public static final String KEY_CITY = "CITY";

    /**
     * 状态
     */
    public static final String KEY_STATUS = "STATUS";

    /**
     * OBD原始数据
     */
    public static final String KEY_OBD_STATUS = "OBD_STATUS";

    /**
     * OBDACC
     */
    public static final String KEY_OBD_ACC = "OBD_ACC";

    /**
     * OBD水温/冷却液温度 ℃
     */
    public static final String KEY_OBD_WT = "OBD_WT";

    /**
     * OBD进气口温度  air intake temperature ℃
     */
    public static final String KEY_OBD_AIT = "OBD_AIT";

    /**
     * OBD环境温度  ambient temperature ℃
     */
    public static final String KEY_OBD_AT = "OBD_AT";

    /**
     * OBD进气压力  inlet pressure kpa
     */
    public static final String KEY_OBD_INP = "OBD_INP";

    /**
     * OBD大气压  atmospheric pressure
     */
    public static final String KEY_OBD_AP = "OBD_AP";

    /**
     * OBD燃油压力  fuel pressure
     */
    public static final String KEY_OBD_FP = "OBD_FP";

    /**
     * OBD加速踏板位置  Accelerator Pedal Position
     */
    public static final String KEY_OBD_APP = "OBD_APP";

    /**
     * OBD故障码个数
     */
    public static final String KEY_OBD_FAULT_NUM = "OBD_FAULT_NUM";

    /**
     * 空气流量 airflow rate g/s
     */
    public static final String KEY_OBD_AIRFLOW = "OBD_AIRFLOW";

    /**
     * 绝对节气门位置 Absolute Throttle Position %
     */
    public static final String KEY_OBD_ATP = "OBD_ATP";

    /**
     * 发动机启动时间 engine start time（sec）
     */
    public static final String KEY_OBD_EST = "OBD_EST";

    /**
     * 发动机负荷值 engine load %
     */
    public static final String KEY_OBD_EL = "OBD_EL";

    /**
     * 长期燃油修正(气缸列1和3)	Long-term fuel amended(Cylinder Columns 1,3) %
     */
    public static final String KEY_OBD_LTFA = "OBD_LTFA";

    /**
     * 第一缸点火正时提前角 The first cylinder Ignition Advance Angle %
     */
    public static final String KEY_OBD_FCIAA = "OBD_FCIAA";

    /**
     * 前刹车片磨损 Front brake pad wear level (0 正常/否则  显示对应数据,单位：级)
     */
    public static final String KEY_OBD_FBPWL = "OBD_FBPWL";

    /**
     * 后刹车片磨损 Front brake pad wear level (0 正常/否则  显示对应数据,单位：级)
     */
    public static final String KEY_OBD_BBPWL = "OBD_BBPWL";

    /**
     * 制动液液位 brake fluid level status  (0:不正常;1:正常;其他:不可用)
     */
    public static final String KEY_OBD_BFLS = "OBD_BFLS";

    /**
     * 冷却液液位  coolant level
     */
    public static final String KEY_OBD_CL = "OBD_CL";

    /**
     * 胎压报警 brake fluid level status  (0：当前无警告;1：存在胎压失压;其他：不可用)
     */
    public static final String KEY_ALM_TIRE_PRES = "ALM_TIRE_PRES";

    /**
     * 机油液位 Engine Oil level 毫米
     */
    public static final String KEY_OBD_EOL = "OBD_EOL";

    /**
     * 故障行驶里程 fault mileage (m)
     */
    public static final String KEY_OBD_FM = "OBD_FM";

    /**
     * OBD电压，单位V，2位小数
     */
    public static final String KEY_OBD_V = "OBD_V";

    /**
     * OBD车速
     */
    public static final String KEY_OBD_SPEED = "OBD_SPEED";

    /**
     * OBD转速
     */
    public static final String KEY_OBD_ROTATION = "OBD_ROTATION";

    /**
     * OBD平均油耗
     */
    public static final String KEY_OBD_AVGOIL = "OBD_AVGOIL";

    /**
     * OBD瞬时油耗
     */
    public static final String KEY_OBD_INSOIL = "OBD_INSOIL";

    /**
     * OBD总里程
     */
    public static final String KEY_OBD_MILEAGE = "OBD_MILEAGE";

    // add by caoyan 20160721 start

    /**
     * OBD总油耗
     */
    public static final String KEY_OBD_FUEL = "KEY_OBD_FUEL";

    /**
     * 计算总油耗
     */
    public static final String KEY_CAL_FUEL = "KEY_CAL_FUEL";

    /**
     * OBD剩余油量单位
     */
    public static final String KEY_OBD_OIL_UNIT = "OBD_OIL_UNIT";

    /**
     * OBD剩余油量
     */
    public static final String KEY_OBD_OIL = "OBD_OIL";

    /**
     * 鉴权码
     */
    public static final String KEY_AUTH_KEY = "AUTH_KEY";

    // add by caoyan 20160721 end

    /**
     * OBD故障报警
     */
    public static final String KEY_OBD_ERROR = "OBD_ERROR";

    /**
     * 服务器标志位
     */
    public static final String KEY_CMD_MARK = "KEY_CMD_MARK";

    /**
     * 命令类型
     */
    public static final String KEY_CMD_TYPE = "CMD_TYPE";

    /**
     * 命令执行结果
     */
    public static final String KEY_CMD_RESULT = "KEY_CMD_RESULT";

    /**
     * 解析时就可以知道需要回复什么数据
     */
    public static final String KEY_REPLY_DATA = "REPLY_DATA";

    public static final String KEY_IS_REPLIED = "IS_REPLIED";

    /**
     * 是否为登录数据
     */
    public static final String KEY_ISREG = "KEY_ISREG";

    /**
     * 行驶：1;停车：0
     */
    public static final String KEY_ISMOVING = "ISMOVING";

    /**
     * 是否为GPS数据
     */
    public static final String KEY_DATA_TYPE = "DATA_TYPE";

    /**
     * 数据包类型：gps数据包
     */
    public static final String DATA_TYPE_GPS = "0";
    /**
     * 数据包类型：OBD数据包
     */
    public static final String DATA_TYPE_OBD = "1";

    /**
     * 数据包类型：登陆/注册包
     */
    public static final String DATA_TYPE_REGISTER = "2";

    /**
     * 数据包类型：心跳包
     */
    public static final String DATA_TYPE_HEART = "3";

    /**
     * 数据包类型：注销
     */
    public static final String DATA_TYPE_LOGOFF = "4";

    /**
     * 数据包类型：鉴权
     */
    public static final String DATA_TYPE_AUTH = "5";

    /**
     * 数据包类型：故障码
     */
    public static final String DATA_TYPE_ERROR = "6";

    /**
     * 数据包类型：报警包
     */
    public static final String DATA_TYPE_ALARM = "7";

    /**
     * 数据包类型：分段里程
     */
    public static final String DATA_TYPE_ACC_OBD = "8";

    /**
     * 数据包类型：响应包
     */
    public static final String DATA_TYPE_REPLY = "9";


    /**
     * 数据包类型：打包数据
     */
    public static final String DATA_TYPE_PACKAGE = "A";

    /**
     * 数据包类型：CAN数据连续包
     */
    public static final String DATA_TYPE_CAN = "B";

    /**
     * 数据包类型：休眠唤醒
     */
    public static final String DATA_TYPE_SLEEP_WAKE = "C";

    /**
     * 数据包类型：休眠进入
     */
    public static final String DATA_TYPE_SLEEP = "D";

    /**
     * 数据包类型：车身状态
     */
    public static final String DATA_TYPE_STATUS = "E";

    /**
     * 数据包类型：校时
     */
    public static final String DATA_TYPE_TIMING = "F";

    /**
     * 数据包类型：LBS
     */
    public static final String DATA_TYPE_LBS = "G";

    /**
     * 数据包类型：WIFI
     */
    public static final String DATA_TYPE_WIFI = "W";

    /**
     * 数据包类型：离线
     */
    public static final String DATA_TYPE_OFFLINE = "O";

    // add for multimedia by zhjd 20170928 start
    /**
     * 数据包类型：多媒体数据包
     */
    public static final String DATA_TYPE_MULTIMEDIA = "M";
    // add for multimedia by zhjd 20170928 end

    // add by zhjd 20181122 start
    /**
     * 数据包类型：主动申请指令
     */
    public static final String DATA_TYPE_SETTING_REQUEST = "R";
    // add by zhjd 20181122 end

    /**
     * 行程: 开始时间 存库需要Date类型
     */
    public static final String KEY_ACC_START_TIME = "ACC_START_TIME";
    /**
     * 行程: 开始坐标
     */
    public static final String KEY_ACC_START_LNG = "ACC_START_LNG";
    /**
     * 行程: 开始坐标
     */
    public static final String KEY_ACC_START_LAT = "ACC_START_LAT";

    /**
     * 行程: 结束时间 存库需要Date类型
     */
    public static final String KEY_ACC_STOP_TIME = "ACC_STOP_TIME";
    /**
     * 行程: 结束坐标
     */
    public static final String KEY_ACC_STOP_LNG = "ACC_STOP_LNG";
    /**
     * 行程: 结束坐标
     */
    public static final String KEY_ACC_STOP_LAT = "ACC_STOP_LAT";
    /**
     * 行程: 行驶里程m
     */
    public static final String KEY_TRIP_MILEAGE = "TRIP_MILEAGE";

    /**
     * 行程: 行程油耗ml
     */
    public static final String KEY_TRIP_FUEL = "TRIP_FUEL";

    /**
     * 行程: 平均油耗L
     */
    public static final String KEY_TRIP_AVOIL = "TRIP_AVOIL";

    /**
     * 行程: 最高速度
     */
    public static final String KEY_TRIP_MAX_SPEED = "TRIP_MAXVEO";

    /**
     * 行程: 平均速度
     */
    public static final String KEY_TRIP_AVE_SPEED = "TRIP_AVVEO";

    /**
     * 行程: 加速次数
     */
    public static final String KEY_TRIP_JIASU = "TRIP_JIASU";

    /**
     * 行程: 减速次数
     */
    public static final String KEY_TRIP_JIANSU = "TRIP_JIANSU";

    /**
     * 行程: 转弯次数
     */
    public static final String KEY_TRIP_ZHUANWAN = "TRIP_ZHUANWAN";

    /**
     * 行程: 总时间s
     */
    public static final String KEY_TRIP_DURATION = "TRIP_DURATION";

    /**
     * 行程: 怠速时间s
     */
    public static final String KEY_TRIP_IDEL_DURATION = "TRIP_IDEL_DURATION";

    /**
     * 行程: 行驶时间s
     */
    public static final String KEY_TRIP_MOVING_DURATION = "TRIP_MOVING_DURATION";

    /**
     * 报警时间
     */
    public static final String KEY_ALM_TIME = "ALM_TIME";

    /**
     * 用户
     */
    public static final String KEY_USER_ID = "USER_ID";

    /**
     * 用户类型：1普通，2车辆
     */
    public static final String KEY_USER_TYPE = "USER_TYPE";

    /**
     * 报警类型
     */
    public static final String KEY_ALM_TYPE = "ALM_TYPE";

    /**
     * 监控计划ID
     */
    public static final String KEY_PLAN_ID = "PLAN_ID";

    /**
     * 点线面ID
     */
    public static final String KEY_MARKER_ID = "MARKER_ID";

    /**
     * 新增的报警字符串
     */
    public static final String KEY_ALM_ADD = "ALM_ADD";

    /**
     * 解除报警字符串
     */
    public static final String KEY_ALM_REMOVE = "ALM_REMOVE";

    /**
     * 电池低电报警
     */
    public static final String KEY_ALM_LOWPOWER = "ALM_LOWPOWER";

    /**
     * 车辆电瓶低电报警
     */
    public static final String KEY_ALM_VEH_LOWBATTERY = "ALM_VEH_LOWBATTERY";

    /**
     * 无线光敏报警
     */
    public static final String KEY_ALM_CUTPOWER = "ALM_CUTPOWER";

    /**
     * 超速
     */
    public static final String KEY_ALM_OVERSPEED = "ALM_OVERSPEED";

    /**
     * 点火
     */
    public static final String KEY_ALM_ACCON = "ALM_ACCON";

    /**
     * 盗警
     */
    public static final String KEY_ALM_STEAL = "ALM_STEAL";

    /**
     * 出区域
     */
    public static final String KEY_ALM_OUTRANGE = "ALM_OUTRANGE";

    /**
     * 入区域
     */
    public static final String KEY_ALM_INRANGE = "ALM_INRANGE";

    // add by zhanghejundai 20170308 start
    /**
     * 伪基站报警
     */
    public static final String KEY_ALM_PSEUDO_STATION = "ALM_PSEUDO_STATION";
    // add by zhanghejundai 20170308 end

    /**
     * 紧急报警状态（0:报警解除，1:报警，-：未知
     */
    public static final String KEY_ALM_SOS = "ALM_SOS";

    /**
     * 风险区域报警
     */
    public static final String KEY_ALM_DANGER_RANGE = "ALM_DANGER_RANGE";

    /**
     * 风险区域报警，触发的第一个风险点
     */
    public static final String KEY_ALM_DANGER_RANGE_POINT = "ALM_DANGER_RANGE_POINT";

    /**
     * 离线报警状态（0:报警解除，1:报警，-：未知
     */
    public static final String KEY_ALM_OFFLINE = "ALM_OFFLINE";

    /**
     * OBD故障(状态同上）
     */
    public static final String KEY_ALM_OBD_FAULT = "ALM_OBD_FAULT";

    /**
     * 原地驻防报警
     */
    public static final String KEY_ALM_STATIONED = "ALM_STATIONED";

    /**
     * 路线偏离报警
     */
    public static final String KEY_ALM_ROUTE = "ALM_ROUTE";

    /**
     * 车辆上线报警
     */
    public static final String KEY_ALM_ONLINE = "ALM_ONLINE";

    /**
     * 停车超时报警
     */
    public static final String KEY_ALM_STOP_TIMEOUT = "ALM_STOP_TIMEOUT";

    /**
     * 疲劳驾驶报警
     */
    public static final String KEY_ALM_TIREDRIVE = "ALM_TIREDRIVE";

    /**
     * 行车时段报警
     */
    public static final String KEY_ALM_MOVEING_TIME = "ALM_MOVEING_TIME";

    /**
     * 油量低水平报警
     */
    public static final String KEY_ALM_LOW_OIL = "ALM_LOW_OIL";

    /**
     * 瞬时油耗报警
     */
    public static final String KEY_ALM_INSOIL = "ALM_INSOIL";

    /**
     * 停车异动报警
     */
    public static final String KEY_ALM_STOP_CHANGE = "ALM_STOP_CHANGE";

    /**
     * 有线无线距离
     */
    public static final String KEY_ALM_DISTANCE = "ALM_DISTANCE";

    /**
     * 检测到伪基站
     */
    public static final String KEY_DETECT_PSEUDO_STATION = "DETECT_PSEUDO_STATION";

    /**
     * 定位失效报警
     */
    public static final String KEY_ALM_LOCATION_FAIL = "ALM_LOCATION_FAIL";

    // add by zhjd for bk200 20170715 start
    /**
     * 震动报警
     */
    public static final String KEY_ALM_SHAKE = "ALM_SHAKE";
    // add by zhjd for bk200 20170715 end

    // add by hf for gv25 20190114 start
    /**
     * 位移报警
     */
    public static final String KEY_ALM_DISPLACE = "ALM_DISPLACE";

    /**
     * 位移开始时间
     */
    public static final String KEY_START_TIME_DISPLACE = "START_TIME_DISPLACE";

    /**
     * 位移时长
     */
    public static final String KEY_ALM_DISPLACE_TIME = "ALM_DISPLACE_TIME";
    // add by hf for gv25 20190114 end

    // add by zhjd for M230 OBD device start
    public static final String KEY_ALM_OBD_UNPLUG = "ALM_OBD_UNPLUG";
    // add by zhjd for M230 OBD device end

    // add by caoyan 20160721 start

    /**
     * 报文内补传标志 1:补传 0：实时上传
     * TODO 目前用于报警、存储的过滤条件，是否需要与HISTORY_GPS区分？
     */
    public static final String KEY_IS_OLD = "IS_OLD";

    /**
     * 系统判断当前GPS时间是否早于最新GPS时间 1:是 0：否
     */
    public static final String KEY_IS_HISTORY_GPS = "IS_HISTORY_GPS";

    /**
     * 是否无效数据（GPS_TIME无效）
     */
    public static final String KEY_IS_INVALID = "IS_INVALID";

    // add by caoyan 20160721 end

    //-----------------------------GPS扩展信息-----------------------------------
    /**
     * 定位方式：0：GPS定位 1：单基站定位 2：多基站定位 9：未定位
     */
    public static final String KEY_LOC_MODE = "LOC_MODE";

    /**
     * 基站数量
     */
    public static final String KEY_STATION_COUNT = "STATION_COUNT";

    /**
     * 位置区码 LAC1
     */
    public static final String KEY_LAC1 = "LAC1";

    /**
     * 基站的编号CELL ID1
     */
    public static final String KEY_CELL_ID1 = "CELL_ID1";

    public static final String KEY_CELL_DBM1 = "CELL_DBM1";

    /**
     * 位置区码 LAC2
     */
    public static final String KEY_LAC2 = "LAC2";

    /**
     * 基站的编号CELL ID2
     */
    public static final String KEY_CELL_ID2 = "CELL_ID2";

    public static final String KEY_CELL_DBM2 = "CELL_DBM2";

    /**
     * 位置区码 LAC3
     */
    public static final String KEY_LAC3 = "LAC3";

    /**
     * 基站的编号CELL ID3
     */
    public static final String KEY_CELL_ID3 = "CELL_ID3";

    public static final String KEY_CELL_DBM3 = "CELL_DBM3";

    /**
     * 位置区码 LAC4
     */
    public static final String KEY_LAC4 = "LAC4";

    /**
     * 基站的编号CELL ID4
     */
    public static final String KEY_CELL_ID4 = "CELL_ID4";

    public static final String KEY_CELL_DBM4 = "CELL_DBM4";

    /**
     * 位置区码 LAC5
     */
    public static final String KEY_LAC5 = "LAC5";

    /**
     * 基站的编号CELL ID5
     */
    public static final String KEY_CELL_ID5 = "CELL_ID5";

    public static final String KEY_CELL_DBM5 = "CELL_DBM5";

    /**
     * 位置区码 LAC6
     */
    public static final String KEY_LAC6 = "LAC6";

    /**
     * 基站的编号CELL ID6
     */
    public static final String KEY_CELL_ID6 = "CELL_ID6";

    public static final String KEY_CELL_DBM6 = "CELL_DBM6";

    /**
     * 移动台信号到达基站的实际时间和假设该移动站与基站距离为0时移动台信号到达基站的时间的差值
     */
    public static final String KEY_CELL_ACC_TIME = "CELL_ACC_TIME";

    /**
     * WIFI的数量
     */
    public static final String KEY_MACS_CNT = "MACS_CNT";

    /**
     * WIFI热点信息，格式：mac,信号强度|mac,信号强度... 例如：f0:7d:68:9e:7d:18,-41|c0:6d:68:9e:7d:12,-100
     */
    public static final String KEY_MACS = "MACS";

    public static final String KEY_CAL_ACC = "CAL_ACC";

    /**
     * 当日油耗
     */
    public static final String KEY_OIL_TODAY = "OIL_TODAY";

    /**
     * 当日平均油耗
     */
    public static final String KEY_AVG_OIL_TODAY = "AVG_OIL_TODAY";

    // (硬件)当日急加速次数
    public static final String KEY_GPS_URGENT_ACCTIMES = "GPS_URGENT_ACCTIMES";
    // (硬件)当日急减速次数
    public static final String KEY_GPS_URGENT_DECTIMES = "GPS_URGENT_DECTIMES";
    // (硬件)当日急拐弯次数
    public static final String KEY_GPS_URGENT_TURNTIMES = "GPS_URGENT_TURNTIMES";
    // 在线/离线起始时刻
    public static final String KEY_START_TIME_ON_OFF_LINE = "START_TIME_ON_OFF_LINE";
    // 定位/未定位  起始时刻
    public static final String KEY_START_TIME_AV = "START_TIME_AV";
    //行驶/停车  起始时刻
    public static final String KEY_START_TIME_MOVING_STOP = "START_TIME_MOVING_STOP";
    //点火/熄火  起始时刻
    public static final String KEY_START_TIME_ACC = "START_TIME_ACC";
    //无线设备光敏  起始时刻
    public static final String KEY_START_TIME_CUTPOWER = "START_TIME_CUTPOWER";
    //低电报警  起始时刻
    public static final String KEY_START_TIME_LOWPOWER = "START_TIME_LOWPOWER";
    // add by zhjd for veh battery alarm 20180910 start
    /**
     * 电瓶电压 起始时刻
     */
    public static final String KEY_START_TIME_LOWBATTERY = "START_TIME_LOWBATTERY";
    // add by zhjd for veh battery alarm 20180910 end
    //紧急报警  起始时刻
    public static final String KEY_START_TIME_SOS = "START_TIME_SOS";
    // add by zhjd for bk200 20170715 start
    //震动报警  起始时刻
    public static final String KEY_START_TIME_SHAKE = "START_TIME_SHAKE";
    // add by zhjd for bk200 20170715 end
    //超速  起始时刻
    public static final String KEY_START_TIME_OVERSPEED = "START_TIME_OVERSPEED";
    //伪基站报警  起始时刻
    public static final String KEY_START_TIME_PBS = "START_TIME_PBS";
    //OBD故障报警  起始时刻
    public static final String KEY_START_TIME_OBD_FAULT = "START_TIME_OBD_FAULT";

    //在线时长
    public static final String KEY_ONLINE_TIME = "ONLINE_TIME";
    //离线 时长
    public static final String KEY_OFFLINE_TIME = "OFFLINE_TIME";
    //定位时长
    public static final String KEY_AV_A_TIME = "AV_A_TIME";
    //未定位 时长
    public static final String KEY_AV_V_TIME = "AV_V_TIME";
    //行驶时长
    public static final String KEY_MOVING_TIME = "MOVING_TIME";
    //停车 时长
    public static final String KEY_STOP_TIME = "STOP_TIME";
    //点火时长
    public static final String KEY_ACC_ON_TIME = "ACC_ON_TIME";
    //熄火 时长
    public static final String KEY_ACC_OFF_TIME = "ACC_OFF_TIME";
    //拆除 时长
    public static final String KEY_ALM_CUTPOWER_TIME = "ALM_CUTPOWER_TIME";
    // add by zhjd for veh battery alarm 20180910 start
    /**
     * 电瓶低电压 时长
     */
    public static final String KEY_ALM_LOWBATTERY_TIME = "ALM_LOWBATTERY_TIME";
    // add by zhjd for veh battery alarm 20180910 end
    //低电报警 时长
    public static final String KEY_ALM_LOWPOWER_TIME = "ALM_LOWPOWER_TIME";
    //紧急报警 时长
    public static final String KEY_ALM_SOS_TIME = "ALM_SOS_TIME";
    // add by zhjd for bk200 20170715 start
    // 震动报警 时长
    public static final String KEY_ALM_SHAKE_TIME = "ALM_SHAKE_TIME";
    // add by zhjd for bk200 20170715 end
    //超速 时长
    public static final String KEY_ALM_OVERSPEED_TIME = "ALM_OVERSPEED_TIME";
    //伪基站报警 时长
    public static final String KEY_ALM_PBS_TIME = "ALM_PBS_TIME";
    //OBD故障报警 时长
    public static final String KEY_ALM_OBD_FAULT_TIME = "ALM_OBD_FAULT_TIME";

    // 报警方式：1：ALM_MODE_STATUS_CHANGE 2：ALM_MODE_STATUS_ON
    public static final String KEY_ALM_MODE = "ALM_MODE";

    // 里程类型 monitor记录用
    public static final String KEY_MILEAGE_TYPE = "MILEAGE_TYPE";

    // add for multimedia by zhjd 20170927 start
    /**
     * 多媒体ID
     */
    public static final String KEY_MEDIA_ID = "MEDIA_ID";

    /**
     * 总包数
     */
    public static final String KEY_PACKAGE_COUNT = "PACKAGE_COUNT";

    /**
     * 当前包号
     */
    public static final String KEY_PACKAGE_INDEX = "PACKAGE_INDEX";

    /**
     * 多媒体数据流
     */
    public static final String KEY_MEDIA_STREAM = "MEDIA_STREAM";

    /**
     * 多媒体类型
     */
    public static final String KEY_MEDIA_TYPE = "MEDIA_TYPE";

    /**
     * 多媒体格式编码
     */
    public static final String KEY_MEDIA_FORMAT = "MEDIA_FORMAT";

    /**
     * 多媒体拍摄失败详情
     */
    public static final String KEY_MEDIA_FAILINFO = "MEDIA_FAILINFO";
    // add for multimedia by zhjd 20170927 end

    // add for lg30 by zhjd 20171215 start
    /**
     * 鉴权成功标志位
     */
    public static final String KEY_AUTH_SUCCESS = "AUTH_SUCCESS";
    // add for lg30 by zhjd 20471215 end

    // add for gt06 by gbp 20171226 start
    /**
     * 油电状态
     */
    public static final String KEY_OILCUTOFF = "OILCUTOFF";
    // add for gt06 by gbp 20171226 end

    // add by gbp 20171121 start
    /**
     * 车架号
     */
    public static final String KEY_VIN = "VIN";
    // add by gbp 20171121 end

    // add by zhjd for cutwire 20180326 start
    /**
     * 【有线】拆除预警（硬件）；断电拆除预警计划
     */
    public static final String KEY_ALM_CUTWIRE = "ALM_CUTWIRE";
    // 有线设备拆除 起始时刻
    public static final String KEY_START_TIME_CUTWIRE = "START_TIME_CUTWIRE";
    // 有线设备拆除 时长
    public static final String KEY_ALM_CUTWIRE_TIME = "ALM_CUTWIRE_TIME";
    // add by zhjd for cutwire 20180326 end

    // add by zhjd 20200408 start
    // 【有线】断电预警（硬件）
    public static final String KEY_ALM_POWEROFF = "ALM_POWEROFF";
    public static final String KEY_START_TIME_POWEROFF = "START_TIME_POWEROFF";
    public static final String KEY_ALM_POWEROFF_TIME = "ALM_POWEROFF_TIME";
    // add by zhjd 20200408 end

    // add by zhjd for crash alarm 20180709 start
    /**
     * 碰撞报警
     */
    public static final String KEY_ALM_CRASH = "ALM_CRASH";
    /**
     * 碰撞报警 起始时刻
     */
    public static final String KEY_START_TIME_CRASH = "START_TIME_CRASH";
    /**
     * 碰撞报警 时长
     */
    public static final String KEY_ALM_CRASH_TIME = "ALM_CRASH_TIME";
    // add by zhjd for crash alarm 20180709 end

    // add to mark packages which need to drop by zhjd 20181105 start
    public static final String KEY_DROP_THIS = "DROP_THIS";
    // add to mark packages which need to drop by zhjd 20181105 end

    /**
     * （无线设备）已休眠：1;未休眠：0
     */
    public static final String KEY_ISSLEEPING = "ISSLEEPING";
    /**
     * 数据包类型：无线设备休眠包（系统生成）
     */
    public static final String DATA_TYPE_WIRELESS_SLEEP = "S";
    /**
     * 休眠 起始时刻
     */
    public static final String KEY_START_TIME_SLEEP = "START_TIME_SLEEP";
    /**
     * 碰撞报警 时长
     */
    public static final String KEY_SLEEP_TIME = "SLEEP_TIME";

    public static final String KEY_WIRELESS_MODE = "WIRELESS_MODE";

    /**
     * 车架号异常
     */
    public static final String KEY_ALM_ABNORMAL_VIN = "ALM_ABNORMAL_VIN";

    /**
     * 文本消息
     */
    public static final String KEY_TEXT_MSG = "TEXT_MSG";

    public static final String KEY_USE_LAST_POSITION = "USE_LAST_POSITION";

    /**
     * 多媒体状态，控制是否生成用于拍照的唯一ID
     */
    public static final String KEY_MEDIA_STATUS = "MEDIA_STATUS";

    /**
     * 多媒体报警key
     */
    public static final String KEY_MEDIA_ALM_KEY = "MEDIA_ALM_KEY";

    /**
     * 每天的第一条报文
     */
    public static final String KEY_TODAY_FIRST_PACKAGE = "TODAY_FIRST_PACKAGE";

    // add by hf 20190611 2.7.8 start
    /**
     * didi专用碰撞报警
     */
    public static final String KEY_SPECIAL_ALM_CRASH = "SPECIAL_ALM_CRASH";
    /**
     * 主动下发指令
     */
    public static final String KEY_CMD = "CMD";
    // add by hf 20190611 2.7.8 END

    /**
     * 报警状态，指报文中表示报警的状态标志
     */
    public static final String KEY_ALM_STATUS = "ALM_STATUS";

    public static final String KEY_SERVER_TAG = "SERVER_TAG";

    // add for cl10p by zhjd 20190718 start
    /**
     * 报警标志：设备翻转报警 device removed detection
     */
    public static final String KEY_ALM_REMOVED = "ALM_REMOVED";

    /**
     * 设备翻转报警 时长
     */
    public static final String KEY_ALM_REMOVED_TIME = "ALM_REMOVED_TIME";

    /**
     * 设备翻转报警 开始时间
     */
    public static final String KEY_START_TIME_REMOVED = "START_TIME_REMOVED";

    /**
     * 数据包协议版本号 CL10P设备添加
     */
    public static final String KEY_PROTOCOL_VER = "PROTOCOL_VER";

    /**
     * CL10P协议：连续模式上报间隔
     */
    public static final String KEY_CL10P_INTERVAL = "CL10P_INTERVAL";

    /**
     * CL10P协议：省电模式、闹钟模式  预计下次上报时间
     */
    public static final String KEY_CL10P_NEXT_LOCATION_TIME = "CL10P_NEXT_LOCATION_TIME";

    /**
     * CL10P协议：连续模式超时事件-设备自动进入省电模式， 预计切换成省电模式后的下一次开机时间
     */
    public static final String KEY_CL10P_NEXT_WAKEUP_TIME = "CL10P_NEXT_WAKEUP_TIME";
    // add for cl10p by zhjd 20190718 end

    /**
     * CL10P设备-定位报文 +RESP:GTLOC 的上报模式
     */
    public static final String KEY_CL10P_LOCATION_REPORT_MODE = "CL10P_LOCATION_REPORT_MODE";

    // add by zhjd 20190816 start
    public static final String DECODE_TIME = "DECODE_TIME";
    // add by zhjd 20190816 end

    // add by zhjd 20191031 start
    /**
     * 判断设备是否连接外电
     * 康凯斯设备：心跳包接外电1，未接外电0
     * 博实结KM02(V3): 心跳包未接外电且外电电压<3为0，其他情况为1
     */
    public static final String KEY_ISCHARGING = "IS_CHARGING";

    public static final String KEY_CAL_ISCHARGING = "CAL_ISCHARGING";

    /**
     * 判断设备是否断开油电：1断开，0正常
     */
    public static final String KEY_CUT_FUEL_ELECTRICITY = "CUT_FUEL_ELECTRICITY";
    // add by zhjd 20191031 end
    // is in house flag by zhjd 20191023 start
    /**
     * 判断轨迹点是否在公司范围内
     */
    public static final String KEY_IS_INHOUSE = "IS_INHOUSE";
    // is in house flag by zhjd 20191023 end

    // add for GSM SIGNAL by zhjd 20191126 start
    public static final String KEY_SIGNAL_LEVEL = "SIGNAL_LEVEL";
    // add for GSM SIGNAL by zhjd 20191126 end

    // add by hf 20200312 start
    public static final String KEY_OBD_DATA_TYPE = "OBD_DATA_TYPE";
}
