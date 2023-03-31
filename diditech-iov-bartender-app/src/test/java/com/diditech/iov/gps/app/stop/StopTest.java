package com.diditech.iov.gps.app.stop;

import com.diditech.iov.gps.api.core.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StopTest {

    private RestTemplate restTemplate = new RestTemplate();

    @org.junit.Test
    public void testGetStopPointList() {

        String url = "http://localhost:8082/stop/stop-list?deviceNum=123456789000&startTime=2021-06-30%2018:12:17&endTime=2021-07-02%2018:12:17";
        ResponseEntity<ResponseMessage> response = restTemplate.exchange(url, HttpMethod.GET, null, ResponseMessage.class);
        System.out.println(response.getBody());
    }

}
