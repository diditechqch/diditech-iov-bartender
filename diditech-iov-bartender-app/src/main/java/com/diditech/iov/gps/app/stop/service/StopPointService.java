package com.diditech.iov.gps.app.stop.service;

import com.diditech.iov.gps.api.stop.domain.StopPointInfo;

import java.util.Date;
import java.util.List;

/**
 * 停留点服务提供类
 *
 * @auth ZhangQuanBo
 * @date 2019/6/24 9:21
 */
public interface StopPointService {

    List<StopPointInfo> getStopPointList(String deviceNum, Date startTime, Date endTime, int minStaySeconds);

}
