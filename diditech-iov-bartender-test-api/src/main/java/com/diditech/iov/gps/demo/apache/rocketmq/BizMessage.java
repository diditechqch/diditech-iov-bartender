package com.diditech.iov.gps.demo.apache.rocketmq;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author zhjd <br>
 * @date 2022/4/21 <br>
 */
@Data
@AllArgsConstructor
public class BizMessage {
    private String uuid;
    private int delayFlag;
    private int delayTimeInSec;
    private int delayTimeInMin;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
