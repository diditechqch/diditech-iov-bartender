package com.diditech.iov.gps.demo;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.demo.service.HistoryStopService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author zhjd <br>
 * @date 2021/8/4 <br>
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HistoryStopDataGenerator {

    private static String[] deviceList = {
            "868120259197769",
            "868120259198007",
            "868120259198049",
            "868120259198080",
            "868120259198114",
            "868120259198155",
            "868120259198288",
            "868120259198460",
            "868120259198494",
            "868120259198858",
            "868120259198866",
            "868120259199203",
            "868120259199229",
            "868120259199245",
            "868120259199286",
            "868120259199310",
            "868120259199385",
            "868120259199864",
            "868120259200423",
            "868120259200514",
            "868120259200548",
            "868120259200688",
            "868120259200712",
            "868120259200720",
            "868120259200746",

    };


    @Autowired
    private HistoryStopService service;

    private String token;

    @Before
    public void setUp() {
        String response = HttpUtil.post("http://monica.didigps.com:8080/oauth/token?grant_type=client_credentials&client_id=yiqiyongche&client_secret=9xw5PGXKndxK", "");
        token = "Bearer " + JSON.parseObject(response).get("access_token").toString();
        log.debug("{}", token);
    }

    @Test
    public void generate() {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        Executor executor = Executors.newFixedThreadPool(20);
        List<CompletableFuture<Void>> tasks = new ArrayList<>();
        Arrays.stream(deviceList).forEach(num -> tasks.add(CompletableFuture.runAsync(service.process(num, token), executor)));
        tasks.stream().map(CompletableFuture::join).count();
    }
}
