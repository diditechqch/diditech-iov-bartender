package com.diditech.iov.gps.app.trace.srv;

import com.diditech.iov.gps.app.trace.srv.impl.TripAccServiceImpl;
import com.diditech.iov.gps.app.trace.srv.impl.TripGpsServiceImpl;

/**
 * 分段服务
 * @author zhjd <br>
 * @date 2023/4/13 <br>
 */
public enum TripType {

    GPS(TripGpsServiceImpl.getInstance()),
    ACC(TripAccServiceImpl.getInstance()),
    ;

    private TripService tripService;

    TripType(TripService tripService) {
        this.tripService = tripService;
    }

    public TripService getService() {
        return tripService;
    }
}
