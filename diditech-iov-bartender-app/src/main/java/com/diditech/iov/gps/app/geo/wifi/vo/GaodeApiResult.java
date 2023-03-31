package com.diditech.iov.gps.app.geo.wifi.vo;

public class GaodeApiResult implements IResult {

    /**
     * 返回状态
     * 0: false
     * 1: true
     */
    private int status;

    /**
     * 返回状态说明
     * OK: 正常
     * 其他: 异常
     */
    private String info;

    /**
     * 返回状态码
     */
    private String infocode;

    /**
     * 定位结果
     */
    private AddressComponent result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public AddressComponent getResult() {
        return result;
    }

    public void setResult(AddressComponent result) {
        this.result = result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((info == null) ? 0 : info.hashCode());
        result = prime * result + ((infocode == null) ? 0 : infocode.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
        result = prime * result + status;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GaodeApiResult other = (GaodeApiResult) obj;
        if (info == null) {
            if (other.info != null)
                return false;
        } else if (!info.equals(other.info))
            return false;
        if (infocode == null) {
            if (other.infocode != null)
                return false;
        } else if (!infocode.equals(other.infocode))
            return false;
        if (result == null) {
            if (other.result != null)
                return false;
        } else if (!result.equals(other.result))
            return false;
        return status == other.status;
    }

    @Override
    public String toString() {
        return "GaodeApiResult [status=" + status + ", info=" + info + ", infocode=" + infocode + ", result=" + result
                + "]";
    }

    @Override
    public boolean isFail() {
        return this.result == null || this.result.getLocation() == null;
    }

}
