package com.diditech.iov.gps.api.devicedata.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class PackageData implements Serializable {

    private String packageType;
    private String[] details;

    public PackageData() {
        this.packageType = "";
        this.details = new String[]{};
    }

}
