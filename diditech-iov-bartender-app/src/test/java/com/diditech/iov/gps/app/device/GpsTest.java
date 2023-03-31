package com.diditech.iov.gps.app.device;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.device.domain.Device;
import com.diditech.iov.gps.api.device.domain.DeviceLocation;
import com.diditech.iov.gps.api.device.domain.DeviceMileage;
import com.diditech.iov.gps.app.core.aspect.RequestHelper;
import com.diditech.iov.gps.app.device.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class GpsTest {

    @Autowired
    private DeviceService deviceService;

    private RestTemplate restTemplate = new RestTemplate();

    @org.junit.Test
    public void testGetDeviceLocation() {
        String deviceNum = "14142921071";
        log.info("Request come: deviceNum={}", deviceNum);
        DeviceLocation obj = deviceService.getDeviceLocation(deviceNum);
        if (obj != null) {
            log.info("-------- getDeviceLocation() ok -----------");
        } else {
            log.info("-------- getDeviceLocation() null -----------");
        }
    }

    @org.junit.Test
    public void testGetDeviceMileage() {
        String deviceNum = "14142921071";
        Integer mileageType = 3;
        log.info("Request come: deviceNum={} mileageType={}", deviceNum, mileageType);
        DeviceMileage obj = deviceService.getDeviceMileage(deviceNum, mileageType);
        if (obj != null) {
            log.info("-------- getDeviceMileage() ok -----------");
        } else {
            log.info("-------- getDeviceMileage() null -----------");
        }
    }

    @org.junit.Test
    public void testUpdateDeviceMileage() {
        String deviceNum = "14142921071";
        Integer correctMileage = 1000000;
        log.info("Request come: deviceNum={} correctMileage={}", deviceNum, correctMileage);
        int count = deviceService.updateDeviceMileage(deviceNum, correctMileage);
        if (count > 0) {
            log.info("-------- updateDeviceMileage() update ok -----------");
        } else if (count == -1) {
            log.info("-------- updateDeviceMileage() invalid deviceNum: " + deviceNum + "-----------");
        } else {
            log.info("-------- updateDeviceMileage() update fail -----------");
        }
    }

    @org.junit.Test
    public void testUpdate() {
        String[] devices = {"14142921071", "14142921072", "14142921073", "14142921074"};
        int isEnable = 0;
        log.info("Request come: devices={} isEnable={} ", devices, isEnable);
        deviceService.batchStartOrStop(devices, RequestHelper.getClientId(), isEnable);
    }


    @org.junit.Test
    public void testAddDevice() {
        String url = "http://localhost:8082/devices/device";
        Device device = new Device();
        device.setDeviceNum("");
        device.setCategoryNo("");
        device.setWifiFlag(1);
        device.setLbsEnable(null);
        device.setIsenable(0);
        device.setClientId("yqyc");
        device.setTenantId(null);
        HttpResponse response = HttpUtil.createRequest(Method.POST, url)
                .body(JSON.toJSONString(device), ContentType.JSON.toString())
                .execute();
        System.out.println(HttpUtil.post(url, JSON.toJSONString(device)));
        System.out.println("response: " + response);

    }

    @org.junit.Test
    public void testDeleteDevice() {
        String url = "http://localhost:8082/devices/device?deviceNum=9000004";
        ResponseEntity<ResponseMessage> response = restTemplate.exchange(url, HttpMethod.DELETE, null, ResponseMessage.class);
        System.out.println(response.getBody());
    }

    @org.junit.Test
    public void testUpdateDevice() {
        String url = "http://localhost:8082/devices/device";
        Device device = new Device();
        device.setDeviceNum("9000004");
        device.setCategoryNo("JT808");
        device.setWifiFlag(2);
        device.setLbsEnable(3);
        device.setTimeoutThreshold(8888);
        device.setClientId("yqyc123");
        device.setTenantId("9527");
        HttpEntity<Device> entity = new HttpEntity<>(device);
        ResponseEntity<ResponseMessage> response = restTemplate.exchange(url, HttpMethod.PUT, entity, ResponseMessage.class);
        System.out.println(response.getBody());
    }
}
