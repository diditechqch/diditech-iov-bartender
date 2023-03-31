package com.diditech.iov.gps.demo.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.diditech.iov.gps.common.domain.ResponseMessage;
import com.diditech.iov.gps.common.domain.StopData;
import com.diditech.iov.gps.common.domain.TraceInfo;
import com.diditech.iov.gps.demo.repository.AccMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhjd <br>
 * @date 2021/8/4 <br>
 */
@Slf4j
@Service
public class HistoryStopService {

    private String host = "http://monica.didigps.com:9000/";

    String traceUrl = host + "rodimus/gps?deviceNum=%s&beginTime=%s&endTime=%s";

    private static final List<StopData> cacheList = new ArrayList<>();

    @Autowired
    private AccMapper mapper;

    public Runnable process(String deviceNum, String token) {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.setTime(DateUtil.parse("2020-11-01 00:00:00"));
        to.setTime(new Date());
        return () -> {
            mapper.deleteStopData(deviceNum, DateUtil.format(to.getTime(), "yyyy-MM-dd HH:mm:ss"));
            Calendar index;
            String request;
            while (from.before(to)) {
                index = (Calendar) from.clone();
                from.add(Calendar.DATE, 2);
                request = String.format(traceUrl, deviceNum,
                        DateUtil.format(index.getTime(), "yyyyMMddHHmmss"),
                        DateUtil.format(from.getTime(), "yyyyMMddHHmmss"));

                log.debug("query gps trace: {}", request);
                HttpResponse response = HttpUtil.createRequest(Method.GET, request)
                        .header("Authorization", token, true)
                        .execute();
                ResponseMessage rm = JSON.parseObject(response.body(), ResponseMessage.class);
                List<TraceInfo> result;
                try {
                    result = JSON.parseArray(JSON.toJSONString(rm.getData()), TraceInfo.class);
                } catch (JSONException ex) {
                    result = null;
                    log.error("", ex);
                }
                TraceInfo a;
                TraceInfo b;
                StopData stopData;
                for (int i = 0; i < result.size() - 1; i++) {
                    a = result.get(i);
                    b = result.get(i + 1);
                    if (isMoving(a) != isMoving(b)) {
                        // 停车或行驶开始
                        synchronized (cacheList) {
                            stopData = cacheList.stream()
                                    .filter(item -> Objects.equals(item.getDeviceNum(), deviceNum))
                                    .findFirst()
                                    .orElse(null);
                        }
                        if (isMoving(b) == 0 && stopData == null) {
                            stopData = new StopData();
                            stopData.setBeginTime(DateUtil.parse(b.getGpsTime(), "yyMMddHHmmss"));
                            stopData.setDeviceNum(deviceNum);
                            stopData.setLatBd(b.getLatBd());
                            stopData.setLngBd(b.getLngBd());
                            stopData.setLatGc(b.getLatGc());
                            stopData.setLngGc(b.getLngGc());
                            stopData.setLocMode(b.getLocMode().contains("GPS") ? 0 : b.getLocMode().contains("单") ? 1 : b.getLocMode().contains("多") ? 2 : 9);
                            synchronized (cacheList) {
                                cacheList.add(stopData);
                            }
                            continue;
                        }
                        if (isMoving(b) == 1 && stopData != null) {
                            stopData.setEndTime(DateUtil.parse(b.getGpsTime(), "yyMMddHHmmss"));
                            long diff = DateUtil.between(stopData.getBeginTime(), stopData.getEndTime(), DateUnit.SECOND);
                            if (diff >= 600) {
                                stopData.setTotalSecond(diff);
                                mapper.insertStopData(stopData);
                            }
                            synchronized (cacheList) {
                                cacheList.remove(stopData);
                            }
                        }
                    }
                }
            }
            log.info("done with deviceNum={}", deviceNum);
        };
    }

    private int isMoving(TraceInfo x) {
        return Convert.toInt(x.getSpeed(), 0) > 5 ? 1 : 0;
    }
}
