package com.diditech.iov.gps.app.geo.address.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.geo.address.baidu.vo.*;
import com.diditech.iov.gps.app.geo.address.config.BaiduMapConfig;
import com.diditech.iov.gps.app.geo.address.service.BaiduServiceI;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dd.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 百度反地理编码服务实体类
 * @author zhjd <br>
 * @date 2023/3/7 <br>
 */
@Slf4j
@Service
public class BaiduService implements BaiduServiceI {
    private static final ObjectMapper om = new ObjectMapper();
    private static final Random random = new Random();
    private static final List<String> PROVINCE_LEVEL_MUNICIPALITY = new ArrayList<>();

    static {
        PROVINCE_LEVEL_MUNICIPALITY.add("北京市");
        PROVINCE_LEVEL_MUNICIPALITY.add("上海市");
        PROVINCE_LEVEL_MUNICIPALITY.add("天津市");
        PROVINCE_LEVEL_MUNICIPALITY.add("重庆市");
    }


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BaiduMapConfig baiduMapConfig;

    @Override
    public String loadAddress(double lat, double lng) {
        String currentKey = baiduMapConfig.getAvailableKey();
        AddressResponseWrap responseWrap;
        int status = 0;

        try {
            responseWrap = getRequestForObject(baiduMapConfig, currentKey, lat, lng);
            status = responseWrap.getStatus();
            if (status != BAIDU_STATUS_OK || responseWrap.getResult() == null) {
                throw new RuntimeException();
            }
        } catch (Throwable e) {
            baiduMapConfig.markKeyNA(currentKey);
            throw new RuntimeException("Key (" + currentKey + ") Not Available, response status: " + status);
        }

        if (responseWrap == null) {
            return Const.EMPTY;
        }
        String addr = concatAddress(responseWrap.getResult());
        log.info("GET ADDRESS SUCCESSFUL={}, KEY={}", addr, currentKey);
        return addr;
    }


    /**
     * 百度API秘钥状态测试
     * @author zhjd
     */
    @Override
    public int checkBaiduKeyStatus(String key) {
        AddressResponseWrap responseWrap;
        int status;
        double lat = 24.0 + getRandomDouble(6);
        double lng = 118.0 + getRandomDouble(6);
        try {
            responseWrap = getRequestForObject(baiduMapConfig, key, lat, lng);
            status = responseWrap.getStatus();
        } catch (Throwable e) {
            status = 404;
        }
        return status;
    }

    private double getRandomDouble(int digitNum) {
        double d = random.nextDouble();
        return BigDecimal.valueOf(d).setScale(digitNum, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private AddressResponseWrap getRequestForObject(String url) {
        try {
            String jsonStr = restTemplate.getForObject(url, String.class);
            if (StrUtil.isEmpty(jsonStr)) {
                return null;
            }
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return om.readValue(jsonStr, AddressResponseWrap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private AddressResponseWrap getRequestForObject(BaiduMapConfig baiduConfig, String key, double lat, double lng) {
        return getRequestForObject(String.format(baiduConfig.getUrl(), key, lat, lng, baiduConfig.isUsePoi() ? 1 : 0, baiduConfig.getCoordtype()));
    }

    protected String concatAddress(ResponseResult result) {
        AddressComponent component = result.getAddressComponent();
        // add by zhjd 20190709 start 存在没有市级地方，使用行政区
        String city = StrUtil.isNotBlank(component.getCity()) ?
                component.getCity() : StrUtil.isNotBlank(component.getDistrict()) ?
                component.getDistrict() : Const.EMPTY;
        component.setCity(city);
        // add by zhjd 20190709 end
        StringBuilderWrapper addressBuilder = new StringBuilderWrapper(Const.SPACE, false);

        addressBuilder.append(component.getProvince());
        if (!component.getProvince().equals(component.getCity())) {
            addressBuilder.appendWithSeperator(component.getCity());
        }
        addressBuilder
                .appendWithSeperator(component.getDistrict())
                .appendWithSeperator(component.getStreet())
                .appendWithSeperator(component.getStreet_number());

        if (!baiduMapConfig.isUsePoi()) {
            addressBuilder.append(component.getCity(), Const.SEP_LINE);
            return addressBuilder.generatorString().trim();
        }

        if (CollUtil.isEmpty(result.getPois())) {
            addressBuilder.append(component.getCity(), Const.SEP_LINE);
            return addressBuilder.generatorString().trim();
        }

        Poi poi = result.getPois().get(0);
        addressBuilder
                .appendWithSeperator(poi.getName())
                .appendWithSeperator(poi.getDirection());
        if (poi.getDistance() <= 0) {
            addressBuilder.append(component.getCity(), Const.SEP_LINE);
            return addressBuilder.generatorString().trim();
        }
        addressBuilder
                .appendWithSeperator(Util.asInt(poi.getDistance()))
                .append(baiduMapConfig.getDistanceUnit())
                .append(component.getCity(), Const.SEP_LINE);
        return addressBuilder.generatorString().trim();
    }

    @Override
    public String crash(List<GeoHash> addressList, DoCrash doCrash) {
        if (CollUtil.isEmpty(addressList)) {
            return Const.EMPTY;
        }

        if (addressList.size() > 1) {
            doCrash.process();
            return Const.EMPTY;
        }

        String address = addressList.get(0).getAddress();
        if (StrUtil.isEmpty(address)) {
            return Const.EMPTY;
        }

        if (address.startsWith(Const.SPACE)) {
            doCrash.process();
            return Const.EMPTY;
        }

        Date createTime = addressList.get(0).getCreateTime();
        if (DateUtil.between(createTime, DateUtil.date().toJdkDate(), DateUnit.DAY) > 365) {
            doCrash.process();
            return address;
        }

        int fieldSeperatorIdx = address.indexOf(Const.SEP_LINE);
        if (fieldSeperatorIdx > -1) {
            if (address.charAt(fieldSeperatorIdx - 1) == ' ') {
                doCrash.process();
                return Const.EMPTY;
            }
            return address;
        }

        try {
            String[] addressUnits = address.split(Const.SPACE);
            boolean isMunicipality = PROVINCE_LEVEL_MUNICIPALITY.stream()
                    .filter(item -> Arrays.asList(addressUnits).contains(item))
                    .findAny()
                    .orElse(null) != null;
            if (isMunicipality) {
                return address + Const.SEP_LINE + addressUnits[0];
            }
            return address + Const.SEP_LINE + addressUnits[1];
        } catch (Throwable e) {
            doCrash.process();
            return Const.EMPTY;
        }
    }

    public interface DoCrash {
        void process();
    }
}
