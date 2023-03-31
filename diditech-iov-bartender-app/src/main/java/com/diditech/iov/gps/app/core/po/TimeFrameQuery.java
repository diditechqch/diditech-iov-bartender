package com.diditech.iov.gps.app.core.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 批量查询时间范围-数据库条件对象
 * @author zhjd <br>
 * @date 2022/1/12 <br>
 */
@Data
@NoArgsConstructor
public class TimeFrameQuery {
    private Date startTime;
    private Date endTime;
}
