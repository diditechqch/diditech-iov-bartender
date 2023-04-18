package com.diditech.iov.gps.app.trip;

import com.diditech.iov.gps.app.trip.srv.TripService;
import com.diditech.iov.gps.app.trip.srv.impl.AccServiceImpl;
import com.diditech.iov.gps.app.trip.srv.impl.TripServiceImpl;

/**
 * 分段服务
 * @author zhjd <br>
 * @date 2023/4/13 <br>
 */
public enum TripType {

    GPS(TripServiceImpl.getInstance()),
    ACC(AccServiceImpl.getInstance()),
    ;

    private TripService tripService;

    TripType(TripService tripService) {
        this.tripService = tripService;
    }

    public TripService getService() {
        return tripService;
    }
}
