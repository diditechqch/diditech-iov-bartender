package com.diditech.iov.gps.common.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Date;

/**
 * 客户流失点
 * @author zhjd <br>
 * @date 2021/3/21 <br>
 */
@Data
@NoArgsConstructor
public class CustomerChurnPoint {
    private String name;
    private String address;
    private int customerId;
    private Date createTime;
    private int isEnable;
    private int isDel;
    private GeoJsonPoint locBd;
}
