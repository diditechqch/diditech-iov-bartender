package com.diditech.iov.gps.api.report.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.diditech.iov.gps.api.cmd.domain.CmdStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 指令报表数据
 * @author zhjd
 * @date 2023/4/18
 */
@Data
public class ReportCmdData implements Serializable {

    private String deviceNum;
    private String cmdName;
    private String result;
    @JSONField(serialize = false)
    private Byte status;
    private String statusDesc;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date cmdTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date resultTime;

    public void setStatus(Byte status) {
        this.status = status;
        this.statusDesc = CmdStatus.getDesc(this.status);
    }
}
