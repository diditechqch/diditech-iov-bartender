package com.diditech.iov.gps.api.stop;

import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.stop.domain.StopPointInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * 停留点检索服务
 *
 * @auth ZhangQuanBo
 * @date 2019/6/24 9:26
 */
@RequestMapping("/stop")
public interface StopPointApi {

    /**
     * 获取车辆停留点列表
     *
     * @param deviceNum    设备号
     * @param startTime    停留事件检索起始时间 兼容多种格式
     * @param endTime      停留事件检索结束时间 兼容多种格式
     * @param minStaySeconds  最小时长，秒，默认600秒
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 {@link StopPointInfo StopPointInfo}
     */
    @Deprecated
    @GetMapping
    ResponseMessage getStopPointList(@RequestParam(value = "deviceNum") String deviceNum,
                                     @RequestParam(value = "startTime") Date startTime,
                                     @RequestParam(value = "endTime") Date endTime,
                                     @RequestParam(value = "minStaySeconds", required = false, defaultValue = "600") int minStaySeconds);
}
