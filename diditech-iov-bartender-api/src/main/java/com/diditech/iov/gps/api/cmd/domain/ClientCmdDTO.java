package com.diditech.iov.gps.api.cmd.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 平台指令详情，接口调用方使用
 * @author zhjd
 * @date 2022/3/22
 */
@Data
public class ClientCmdDTO {
    @NotBlank
    private String deviceNum;
    private String cmdName;
    @NotBlank
    private String cmd;
    private String cmdStr;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date cmdTime;
    /**
     * 是否缓存指令，默认是。当设备当前不在线，requireCache=false可防止设备再上线时执行该指令。
     */
    private boolean requireCache = true;
    /**
     * 是否为实时指令，默认否。当设备当前在线时实时下发。
     */
    private boolean realTime = false;

    private int id;

    @JSONField(serialize = false)
    private String host;
    @JSONField(serialize = false)
    private int port;
    @JSONField(serialize = false)
    private int status;

}
