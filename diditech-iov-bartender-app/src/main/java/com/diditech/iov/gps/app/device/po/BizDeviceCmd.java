package com.diditech.iov.gps.app.device.po;

import java.util.Date;

public class BizDeviceCmd {
    private Integer id;

    private String deviceNum;

    private String clientId;

    private String cmdName;

    private String cmd;

    private String cmdStr;

    private Date cmdTime;

    private Date sendTime;

    private String result;

    private Date resultTime;

    private Byte status;

    private Short exeCount;

    private Integer batId;

    private String tokenId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum == null ? null : deviceNum.trim();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getCmdName() {
        return cmdName;
    }

    public void setCmdName(String cmdName) {
        this.cmdName = cmdName == null ? null : cmdName.trim();
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd == null ? null : cmd.trim();
    }

    public String getCmdStr() {
        return cmdStr;
    }

    public void setCmdStr(String cmdStr) {
        this.cmdStr = cmdStr == null ? null : cmdStr.trim();
    }

    public Date getCmdTime() {
        return cmdTime;
    }

    public void setCmdTime(Date cmdTime) {
        this.cmdTime = cmdTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public Date getResultTime() {
        return resultTime;
    }

    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Short getExeCount() {
        return exeCount;
    }

    public void setExeCount(Short exeCount) {
        this.exeCount = exeCount;
    }

    public Integer getBatId() {
        return batId;
    }

    public void setBatId(Integer batId) {
        this.batId = batId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId == null ? null : tokenId.trim();
    }
}