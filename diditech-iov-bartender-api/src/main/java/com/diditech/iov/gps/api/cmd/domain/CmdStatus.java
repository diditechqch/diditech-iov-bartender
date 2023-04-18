package com.diditech.iov.gps.api.cmd.domain;

import lombok.Getter;

import java.util.Arrays;

/**
 * 设备指令状态描述
 * @author zhjd <br>
 * @date 2023/4/18 <br>
 */
@Getter
public enum CmdStatus {
    CACHED(8, "已经缓存待上线"),
    SEND(10, "已下发"),
    SUCCESS(11, "执行成功"),
    TIMEOUT(13, "超时"),
    ERROR(14, "错误"),
    MEDIA_UPLOADING(15, "等待设备上传");

    private int code;
    private String desc;

    CmdStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(int code) {
        return Arrays.stream(CmdStatus.values())
                .filter(item -> item.code == code)
                .findFirst()
                .orElse(SEND)
                .getDesc();
    }
}
