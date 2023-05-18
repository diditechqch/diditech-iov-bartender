package com.diditech.iov.gps.api.device.domain;

/**
 * 设备指令状态
 * @author zhjd <br>
 * @date 2021/2/23 <br>
 */
public class CmdStatus {
    /**
     * 新指令
     */
    public static final int NEW = 0;
    /**
     * 指令执行中
     */
    public static final int SUCCESS_UNSURE = 7;
    /**
     * 已经缓存待上线
     */
    public static final int CACHED = 8;
    /**
     * 离线
     */
    public static final int OFFLINE = 9;
    /**
     * 已下发
     */
    public static final int SEND = 10;
    /**
     * 执行成功
     */
    public static final int SUCCESS = 11;
    /**
     * 暂不实用
     */
    public static final int DEVICE_FAIL = 12;
    /**
     * 超时
     */
    public static final int TIMEOUT = 13;
    /**
     * 错误
     */
    public static final int ERROR = 14;
    /**
     * 多媒体专用：等待设备上传
     */
    public static final int MEDIA_UPLOADING = 15;
}
