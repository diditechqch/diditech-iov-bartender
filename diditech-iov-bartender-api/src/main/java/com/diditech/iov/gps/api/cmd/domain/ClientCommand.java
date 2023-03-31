package com.diditech.iov.gps.api.cmd.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * monica指令对象，用于实时指令下发
 * @author zhjd
 * @date 2022/4/11
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class ClientCommand implements Serializable {
    private static final long serialVersionUID = -787674792518546250L;

    private Integer id;

    private String cmd;

    private Date cmd_time;

    private Date send_time;

    private String result;

    private Date result_time;

    private Integer status;

    private String device_num;

    private String device_type;

    private String device_command;

    private int ext_cnt;

    private String cmdType;

    private String[] cmdParams;

    /**
     * 标识服务器发到设备的命令ID,服务器在收到设备回复后，可以根据命令ID找到对应的原始指令
     */
    private String cmdId;

    private String deviceCmdType;

    private String tokenId;

    private String categoryNo;

    // add by zhjd start 20220330 start
    /**
     * 平台流水号标识
     */
    private String snMark;

    private String clientId;

    private String cmdName;

    private String cmdStr;
    // add by zhjd start 20220330 end

    @JSONField(serialize = false)
    private String host;
    @JSONField(serialize = false)
    private int port;

    public void setCmd(String cmd) {
        this.cmd = cmd;
        splitCmd(cmd);
    }

    private void splitCmd(String command) {
        if (command != null) {
            String[] parts = command.split(",");
            if (parts.length > 0) {
                cmdType = parts[0];
                if (parts.length > 1) {
                    cmdParams = new String[parts.length - 1];
                    System.arraycopy(parts, 1, cmdParams, 0, parts.length - 1);
                }
            }
        }
    }

    public String getCmdParams(int index) {
        if (index < 0) {
            return null;
        }
        if (cmdParams == null || index >= cmdParams.length) {
            return null;
        }
        return cmdParams[index];
    }

    public boolean isRtCmd() {
        return getTokenId() != null && getTokenId().startsWith("gps");
    }

}
