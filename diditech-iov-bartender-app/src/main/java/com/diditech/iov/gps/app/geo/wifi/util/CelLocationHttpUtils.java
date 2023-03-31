package com.diditech.iov.gps.app.geo.wifi.util;

import com.alibaba.druid.util.StringUtils;
import com.diditech.iov.gps.app.geo.wifi.vo.CelLocationResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class CelLocationHttpUtils {

    @Value("${geo.wifi.cellocation.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    private static final ObjectMapper om = new ObjectMapper();

    public CelLocationResult getRequestForObject(String urlWithParam) {
        try {
            String jsonStr = restTemplate.getForObject(urlWithParam, String.class);
            if (StringUtils.isEmpty(jsonStr)) {
                return null;
            }
            return om.readValue(jsonStr, CelLocationResult.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CelLocationResult getRequestForObject(String cl, String wl) {
        return getRequestForObject(String.format(url, cl, wl));
    }

}
