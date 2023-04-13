package com.diditech.iov.gps.api.trace.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhjd <br>
 * @date 2020/12/1 <br>
 */
@ToString
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TripAcc extends TripBase implements Serializable {

    private static final long serialVersionUID = -7161456410795706422L;

}
