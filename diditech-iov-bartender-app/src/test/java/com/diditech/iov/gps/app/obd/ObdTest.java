package com.diditech.iov.gps.app.obd;

import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.app.obd.service.ObdService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ObdTest {

    @Autowired
    private ObdService obdService;

    private RestTemplate restTemplate = new RestTemplate();

    @org.junit.Test
    public void testGetObdFaultList() {

        String url = "http://localhost:8082/obd/obd-fault?deviceNum=15493500206&vehicleBrand=宝马";
        ResponseEntity<ResponseMessage> response = restTemplate.exchange(url, HttpMethod.GET, null, ResponseMessage.class);
        System.out.println(response.getBody());
    }

    @org.junit.Test
    public void testGetObdData() {

        String url = "http://localhost:8082/obd/obd-data?deviceNum=15493500206";
        ResponseEntity<ResponseMessage> response = restTemplate.exchange(url, HttpMethod.GET, null, ResponseMessage.class);
        System.out.println(response.getBody());
    }

    @org.junit.Test
    public void testGetObdFuel() {

        String url = "http://localhost:8082/obd/obd-fuel?deviceNum=%s&startTime=%s&endTime=%s";
        ResponseMessage result = restTemplate.getForObject(String.format(url, "15493500206", "2020-11-12 11:31:49", "2020-11-24 15:50:46"), ResponseMessage.class);
        System.out.println(result);
    }

}
