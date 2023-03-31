package com.diditech.iov.gps.app.geo.wifi.util;

import com.alibaba.druid.util.StringUtils;
import com.diditech.iov.gps.app.geo.wifi.vo.GaodeApiResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class GaodeHttpUtils {

    @Value("${geo.wifi.gaode.url}")
    private String url;

    @Value("${geo.wifi.gaode.key}")
    private String key;

    @Autowired
    private RestTemplate restTemplate;

    private static final ObjectMapper om = new ObjectMapper();

    public GaodeApiResult getRequestForObject(String url) {
        try {
            String jsonStr = restTemplate.getForObject(url, String.class);
            if (StringUtils.isEmpty(jsonStr)) {
                return null;
            }
            return om.readValue(jsonStr, GaodeApiResult.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * for gaode wifi
     * @author hefan
     * @date 2021/10/22 14:47
     */
    public GaodeApiResult getRequestForObject(String imei, String macs, String[] lbsInfo) {
        return getRequestForObject(String.format(url, key, imei, macs, lbsInfo[0], lbsInfo[1]));
    }

    public GaodeApiResult getRequestForObject(String url, String... info) {
        return getRequestForObject(String.format(url, info));
    }
}
