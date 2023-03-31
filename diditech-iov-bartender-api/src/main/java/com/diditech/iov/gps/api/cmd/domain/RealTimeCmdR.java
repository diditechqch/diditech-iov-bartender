package com.diditech.iov.gps.api.cmd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 实时指令下发结果
 * @author zhjd <br>
 * @date 2021/10/13 <br>
 */
@Data
@AllArgsConstructor
public class RealTimeCmdR {
    private String deviceNum;
}
