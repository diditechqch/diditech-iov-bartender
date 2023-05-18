package com.diditech.iov.gps.app.trace.srv;

import com.diditech.iov.gps.api.trace.entity.CoordinateType;
import com.diditech.iov.gps.api.trace.entity.TripAcc;
import com.diditech.iov.gps.api.trace.entity.TripGps;
import com.diditech.iov.gps.app.trace.po.GpsEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 轨迹分段查询条件构建器
 * @author zhjd <br>
 * @date 2023/3/24 <br>
 */
@Getter
@Component
public class TraceRequest {
    private String deviceNum;
    private Date beginTime;
    private Date endTime;
    private String coorType;
    private int minNoDataDuration;
    private double minTripDistance;
    private int includeAddress;
    private int order;

    private static TraceService trace;

    @Autowired
    public void setTrace(TraceService trace) {
        TraceRequest.trace = trace;
    }

    private TraceRequest() {
    }

    public static class Builder {
        private String deviceNum;
        private Date beginTime;
        private Date endTime;
        private String coorType = CoordinateType.BD09.name();
        private int minNoDataDuration = 900;
        private double minTripDistance = 0.01;
        private int includeAddress = 1;
        private int order = 0;

        private Builder() {
        }

        public static Builder create(String deviceNum, Date beginTime, Date endTime) {
            Builder builder = new Builder();
            builder.deviceNum = deviceNum;
            builder.beginTime = beginTime;
            builder.endTime = endTime;
            return builder;
        }

        public static Builder create(String deviceNum, Date beginTime) {
            Builder builder = new Builder();
            builder.deviceNum = deviceNum;
            builder.beginTime = beginTime;
            return builder;
        }

        public Builder coorType(String coorType) {
            this.coorType = coorType;
            return this;
        }

        public Builder minNoDataDuration(int minNoDataDuration) {
            this.minNoDataDuration = minNoDataDuration;
            return this;
        }

        public Builder minTripDistance(double minTripDistance) {
            this.minTripDistance = minTripDistance;
            return this;
        }

        public Builder includeAddress(boolean includeAddress) {
            this.includeAddress = includeAddress ? 1 : 0;
            return this;
        }

        public Builder order(int order) {
            this.order = order;
            return this;
        }

        public List<TripGps> getTrips() {
            TraceRequest request = new TraceRequest();
            request.deviceNum = this.deviceNum;
            request.beginTime = this.beginTime;
            request.endTime = this.endTime;
            request.coorType = this.coorType;
            request.minNoDataDuration = this.minNoDataDuration;
            request.minTripDistance = this.minTripDistance;
            request.includeAddress = this.includeAddress;
            request.order = this.order;
            return trace.getTrip(request);
        }

        public GpsEntity getGps() {
            TraceRequest request = new TraceRequest();
            request.deviceNum = this.deviceNum;
            request.beginTime = this.beginTime;
            return trace.getGpsEntity(request);
        }

        public List<TripAcc> getAcc() {
            TraceRequest request = new TraceRequest();
            request.deviceNum = this.deviceNum;
            request.beginTime = this.beginTime;
            request.endTime = this.endTime;
            request.coorType = this.coorType;
            request.minNoDataDuration = this.minNoDataDuration;
            request.minTripDistance = this.minTripDistance;
            request.includeAddress = this.includeAddress;
            request.order = this.order;
            return trace.getAcc(request);
        }
    }
}
