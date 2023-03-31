package com.diditech.iov.gps.api.gpslog;

import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.gpslog.domain.GpsLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * 报文查询
 * @author zhjd <br>
 * @date 2022/3/11 <br>
 */
@RequestMapping("/gps-log")
public interface GpsLogApi {

    /**
     * 查询报文
     * @param beginTime 日期，兼容多种格式
     * @param endTime   日期，兼容多种格式
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值类型为 List&lt;{@link GpsLog}&gt; 按服务器时间倒序
     * @date 2022/3/11
     * @author zhjd
     */
    @GetMapping
    ResponseMessage getGpsLog(
            @RequestParam("deviceNum") String deviceNum,
            @RequestParam("beginTime") Date beginTime,
            @RequestParam("endTime") Date endTime);
}
