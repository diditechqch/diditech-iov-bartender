package com.diditech.common.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName DevicePacket
 * @Description 设备报文对象
 * @Author zhjd
 * @Date 2019/1/18 15:12
 * @Version 1.0
 **/
public class DevicePacket implements Serializable{

    private static final long serialVersionUID = 1960328775878732967L;

    /**
     * 协议名称
     * 用于匹配测试环境端口号
     */
    private String protocolName;

    /**
     * 原始报文
     */
    private String message;

    /**
     * remote address
     */
    private String address;

    /**
     * deviceNum
     */
    private String deviceNum;

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevicePacket packet = (DevicePacket) o;
        return Objects.equals(protocolName, packet.protocolName) &&
                Objects.equals(message, packet.message) &&
                Objects.equals(address, packet.address) &&
                Objects.equals(deviceNum, packet.deviceNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(protocolName, message, address, deviceNum);
    }

    @Override
    public String toString() {
        return "DevicePacket{" +
                "protocolName='" + protocolName + '\'' +
                ", message='" + message + '\'' +
                ", address='" + address + '\'' +
                ", deviceNum='" + deviceNum + '\'' +
                '}';
    }
}
