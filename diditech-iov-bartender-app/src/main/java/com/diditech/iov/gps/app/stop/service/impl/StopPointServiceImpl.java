package com.diditech.iov.gps.app.stop.service.impl;


import com.diditech.iov.gps.api.stop.domain.StopPointInfo;
import com.diditech.iov.gps.app.stop.repository.StopPointMapper;
import com.diditech.iov.gps.app.stop.service.StopPointService;
import com.diditech.utils.Coordinate;
import com.diditech.utils.GISFixUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 停留点获取
 *
 * @author zhaist
 * @date 2021/07/02 9:48
 */
@Service
public class StopPointServiceImpl implements StopPointService {

    @Autowired
    private StopPointMapper stopPointMapper;

    @Override
    public List<StopPointInfo> getStopPointList(String deviceNum, Date startTime, Date endTime, int minStaySeconds) {
        List<StopPointInfo> stopPointInfos =  stopPointMapper.getStopPointList(deviceNum, startTime, endTime, minStaySeconds);
        stopPointInfos.forEach(item->{
            Coordinate c = GISFixUtil.convertBD09ToGCJ02(new Coordinate(item.getLatBd(), item.getLngBd()));
            item.setLngGc(c.lng);
            item.setLatGc(c.lat);
        });

        return stopPointInfos;
    }
}
