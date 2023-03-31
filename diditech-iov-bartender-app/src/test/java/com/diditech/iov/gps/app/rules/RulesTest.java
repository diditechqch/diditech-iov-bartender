package com.diditech.iov.gps.app.rules;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.rules.domain.EventRuleDTO;
import com.diditech.iov.gps.app.core.util.GisUtils;
import com.diditech.iov.gps.app.rules.service.impl.RulesServiceImpl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import lombok.extern.slf4j.Slf4j;


@SuppressWarnings("unchecked")
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RulesTest {

    private RestTemplate restTemplate = new RestTemplate();


    @Autowired
    private RedisTemplate coreRedisTemplate;

    @Autowired
    private RulesServiceImpl rulesService;

    @Test
    public void test() {
//        Set<String> keys = coreRedisTemplate.keys("event-rule:event:*868120241765178");
//        System.out.println(keys);

        Set<String> keys = coreRedisTemplate.keys("event-rule:event:*-556-*");
        System.out.println(keys);
    }

    @Test
    public void testFastjson() {
        String areaPoints = "[[120.356116,36.206523][120.46765,36.213512][120.4665,36.186018][120.357841,36.182756]]";
        String[] split = areaPoints.split("\\]\\,\\[");
        System.out.println(split);

        List<double[]> points = JSON.parseArray(areaPoints, double[].class);
        System.out.println(points);
        System.out.println(JSON.toJSONString(points));
    }

    @Test
    public void testPoints() {
        System.out.println(JSON.toJSONString(GisUtils.getInstance().getCircularAreaPoints(120.489629, 36.152641, 10)));
        System.out.println(JSON.toJSONString(GisUtils.getInstance().getCircularAreaMapPoints(120.489629, 36.152641, 10)));
    }

//    @Test
//    public void testInArea() {
//        List<double[]> points1 = GisUtils.getInstance().getCircularAreaPoints(120.489629, 36.152641, 2);
//        List<double[]> points2 = GisUtils.getInstance().getCircularAreaPoints(120.413869, 36.16625, 2);
//
//        points1.addAll(points2);
//
//        Assert.assertTrue(GPSUtil.isPointInArea(120.489629, 36.152641, points1));
//        Assert.assertTrue(GPSUtil.isPointInArea(120.413869, 36.16625, points1));
//        Assert.assertFalse(GPSUtil.isPointInArea(120.325044, 36.304304, points1));
//
//        List<double[]> points3 = GisUtils.getInstance().getCircularAreaPoints(120.489629, 36.152641, 1);
//        List<double[]> points4 = GisUtils.getInstance().getCircularAreaPoints(120.489629, 36.152641, 50);
//
//        points3.addAll(points4);
//
//        Assert.assertTrue(GPSUtil.isPointInArea(120.438462, 36.152641, points3)); // 同心圆验证得，算法是去重叠区域的
//        Assert.assertTrue(GPSUtil.isPointInArea(120.680814, 36.353274, points3));
//        Assert.assertFalse(GPSUtil.isPointInArea(119.756468, 36.394262, points3));
//    }

    @Test
    public void testFastJson() {
        String[] areas = {"[[120.600752,36.152641],[120.599384,36.138541],[120.595314,36.124787],[120.588641,36.111716],[120.57953,36.099651],[120.568205,36.088889],[120.554946,36.079697],[120.540078,36.072301],[120.523968,36.066883],[120.507012,36.063578],[120.489628,36.062467],[120.472245,36.063578],[120.455289,36.066883],[120.439179,36.072301],[120.424311,36.079697],[120.411052,36.088889],[120.399727,36.099651],[120.390616,36.111716],[120.383943,36.124787],[120.379873,36.138541],[120.378505,36.152641],[120.379873,36.166737],[120.383943,36.180484],[120.390616,36.193544],[120.399727,36.205594],[120.411052,36.21634],[120.424311,36.225516],[120.439179,36.232897],[120.455289,36.238303],[120.472245,36.241601],[120.489628,36.242709],[120.507012,36.241601],[120.523968,36.238303],[120.540078,36.232897],[120.554946,36.225516],[120.568205,36.21634],[120.57953,36.205594],[120.588641,36.193544],[120.595314,36.180484],[120.599384,36.166737]]",
                "[[120.600752,36.152641],[120.599384,36.138541],[120.595314,36.124787],[120.588641,36.111716],[120.57953,36.099651],[120.568205,36.088889],[120.554946,36.079697],[120.540078,36.072301],[120.523968,36.066883],[120.507012,36.063578],[120.489628,36.062467],[120.472245,36.063578],[120.455289,36.066883],[120.439179,36.072301],[120.424311,36.079697],[120.411052,36.088889],[120.399727,36.099651],[120.390616,36.111716],[120.383943,36.124787],[120.379873,36.138541],[120.378505,36.152641],[120.379873,36.166737],[120.383943,36.180484],[120.390616,36.193544],[120.399727,36.205594],[120.411052,36.21634],[120.424311,36.225516],[120.439179,36.232897],[120.455289,36.238303],[120.472245,36.241601],[120.489628,36.242709],[120.507012,36.241601],[120.523968,36.238303],[120.540078,36.232897],[120.554946,36.225516],[120.568205,36.21634],[120.57953,36.205594],[120.588641,36.193544],[120.595314,36.180484],[120.599384,36.166737]]"};
        for (String area : areas) {
            List<double[]> points = JSON.parseArray(area, double[].class);
            System.out.println(points);
        }
    }

    @Test
    public void testRuleCreate() {
        String url = "http://localhost:9000/api/rules?devices=15493500206";
        EventRuleDTO ruleDTO = new EventRuleDTO();
        ruleDTO.setShapeType(1);
        ruleDTO.setAreaPoints("[[120.356116,36.206523],[120.46765,36.213512],[120.4665,36.186018],[120.357841,36.182756]]");
        ruleDTO.setCircles("112.998800,23.623920,0.5");
        ruleDTO.setAreaEnable(1);
        ruleDTO.setRuleType(2);
        ruleDTO.setRuleName("测试进区域事件");
        ruleDTO.setTimeEnable(1);
        ruleDTO.setEnableTimeFrom(Convert.toDate("2020-07-06 00:00:00"));
        ruleDTO.setEnableTimeTo(Convert.toDate("2020-07-20 00:00:00"));
        ruleDTO.setTenantId("324dsaf98");

        HttpResponse response = HttpUtil.createRequest(Method.POST, url)
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNTk3NzIzNzE1LCJqdGkiOiJhNmQ0NjI0ZC0xMjA5LTQ0NWEtYWEzMy0yZjBkNjIyYzE4YjgiLCJjbGllbnRfaWQiOiJjbGllbnQxIn0.O7tlUsNeITDpflV4vQcxaxRs9O-1HowXvO1xPLRucTyCZzgNyB_1cz5A9vMqUvUY4y4ukDG1QWElv0cW3x9GXN5fjN1IM0Utp46c3iJZlkSDYeOoEyKuY_JyYXIWNnxQRXvsPG9_YpnHXupKTBFrQH-oaMPvvTMTPHzrfO6XgGkSf44ZZ2M5shf6EJbFpk4aSR87kAADF6vMXlIba6QlTsi-oc_2sDPpL2RC_9slPYuEcUMJBXE-jaHSGZObx8J6vJURX-mYDVmtNuNgtFjvp4h_qzSjW6LNEEbBRq2QrgQ42rL9sRcwae7X7ZIlaOEaLM9v_AACYzCKPUoJJDWnaw")
                .body(JSON.toJSONString(ruleDTO), ContentType.JSON.toString())
                .execute();

        System.out.println("old: " + HttpUtil.post(url, JSON.toJSONString(ruleDTO)));
        System.out.println("with gateway: " + response);
    }

    @Test
    public void testRuleCreateCutWire() {
        String url = "http://monica.didigps.com:8012/rules?devices=868120241765178";
        EventRuleDTO ruleDTO = new EventRuleDTO();
        ruleDTO.setRuleType(25);
        ruleDTO.setRuleName("测试DO80断电拆除");
        ruleDTO.setTenantId("ding8cd86619c098fc1824f2f5cc6abecb85");
        System.out.println(HttpUtil.post(url, JSON.toJSONString(ruleDTO)));
    }

    @Test
    public void testRuleNoTaskDriving() {
        String url = "http://localhost:8082/rules?devices=15493500815";
        EventRuleDTO ruleDTO = new EventRuleDTO();
        ruleDTO.setRuleType(41);
        ruleDTO.setRuleName("测试无任务预警");
        ruleDTO.setTenantId("ding8cd86619c098fc1824f2f5cc6abecb85");
        System.out.println(HttpUtil.post(url, JSON.toJSONString(ruleDTO)));
    }

    @Test
    public void testRuleUpdate() {
        String url = "http://localhost:8082/rules?devices=1234567890";
        String[] devices = {};
        EventRuleDTO ruleDTO = new EventRuleDTO();

        ruleDTO.setShapeType(2);
        ruleDTO.setCircles("112.989500,23.587140,50");

        ruleDTO.setAreaEnable((byte) 1);
        ruleDTO.setRuleType(2);
        ruleDTO.setRuleName("测试出区域事件");
        ruleDTO.setTimeEnable((byte) 1);
        ruleDTO.setEnableTimeFrom(Convert.toDate("2020-07-06 00:00:00"));
        ruleDTO.setEnableTimeTo(Convert.toDate("2020-07-20 00:00:00"));
        ruleDTO.setTenantId("324dsaf98");
        ruleDTO.setRuleId(183);

        HttpEntity<EventRuleDTO> entity = new HttpEntity<>(ruleDTO);

        ResponseEntity<ResponseMessage> response = restTemplate.exchange(url, HttpMethod.PUT, entity, ResponseMessage.class);
        System.out.println(response.getBody());
        System.out.println(JSON.parseObject(JSON.toJSONString(response.getBody().getData()), EventRuleDTO.class));
    }

    @Test
    public void testRuleDelete() {
        String url = "http://115.28.254.166:8012/rules/devices?devices=15493500206";
        ResponseEntity<ResponseMessage> response = restTemplate.exchange(url, HttpMethod.DELETE, null, ResponseMessage.class);
        System.out.println(response.getBody());
    }

    @Test
    public void testRuleDelete2() {
        String url = "http://localhost:8082/rules/ids?ruleIds=556";
        ResponseEntity<ResponseMessage> response = restTemplate.exchange(url, HttpMethod.DELETE, null, ResponseMessage.class);
        System.out.println(response.getBody());
    }

    @Test
    public void testGetAreaPoints() {
        String url = "http://localhost:8082/rules/areas?ruleAreaHistoryId=42";
//        String url = "http://localhost:8082/rules/areas?ruleAreaHistoryId=42";
        ResponseEntity<ResponseMessage> response = restTemplate.exchange(url, HttpMethod.GET, null, ResponseMessage.class);
        System.out.println(response.getBody());
    }

    @Test
    public void date() {
        Date today = new Date();
        long dayDiff = DateUtil.betweenDay(today, DateUtil.endOfYear(today), true);
        double perc = BigDecimal.valueOf(dayDiff).divide(BigDecimal.valueOf(365), 3, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).doubleValue();
        System.out.println(perc + "%");
    }

}
