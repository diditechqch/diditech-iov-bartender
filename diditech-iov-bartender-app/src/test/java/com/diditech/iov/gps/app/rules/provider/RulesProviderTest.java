package com.diditech.iov.gps.app.rules.provider;

import cn.hutool.core.convert.Convert;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.rules.domain.EventRuleDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhjd <br>
 * @date 2020/8/18 <br>
 */
@Slf4j
@SpringBootTest
public class RulesProviderTest {

    private RestTemplate restTemplate = new RestTemplate();

    private String host = "http://localhost:9000/api";

    private String token;

    @Before
    public void setUp() {
        String response = HttpUtil.post("http://localhost:8080/oauth/token?grant_type=client_credentials&client_id=saomayongche&client_secret=password", "");
        token = "Bearer " + JSON.parseObject(response).get("access_token").toString();
        log.debug("{}", token);
    }

    @Test
    public void saveRule() {
        String deviceNum = "123456789002";
        String url = String.format("%s/rules?devices=%s", host, deviceNum);

        EventRuleDTO ruleDTO = new EventRuleDTO();
        ruleDTO.setShapeType(1);
        ruleDTO.setAreaPoints("[[120.356116,36.206523],[120.46765,36.213512],[120.4665,36.186018],[120.357841,36.182756]]");
        ruleDTO.setCircles("112.998800,23.623920,0.5");
        ruleDTO.setAreaEnable(1);
        ruleDTO.setAreaId(973);
        ruleDTO.setRuleType(1);
        ruleDTO.setRuleName("测试进区域事件");
        ruleDTO.setTimeEnable(1);
        ruleDTO.setEnableTimeFrom(Convert.toDate("2020-07-06 00:00:00"));
        ruleDTO.setEnableTimeTo(Convert.toDate("2020-07-20 00:00:00"));
        ruleDTO.setTenantId("324dsaf98");

        System.out.println(JSON.toJSONString(ruleDTO));

        HttpResponse response = HttpUtil.createRequest(Method.POST, url)
                .header("Authorization", token, true)
                .body(JSON.toJSONString(ruleDTO), ContentType.JSON.toString())
                .execute();
        log.debug("{}", response);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        ResponseMessage rm = JSON.parseObject(response.body(), ResponseMessage.class);
        Assert.assertNotNull(rm);
        EventRuleDTO obj = JSON.parseObject(JSON.toJSONString(rm.getData()), EventRuleDTO.class);
        Assert.assertSame(obj.getRuleType(), ruleDTO.getRuleType());
    }

    @Test
    public void testRuleCreateCutWire() {
        String url = String.format("%s/rules?devices=%s", host, "868120248724939");

        EventRuleDTO ruleDTO = new EventRuleDTO();
        ruleDTO.setRuleType(301);
        ruleDTO.setRuleName("测试EV40断电拆除");
        ruleDTO.setTenantId("ding8cd86619c098fc1824f2f5cc6abecb85");

        HttpResponse response = HttpUtil.createRequest(Method.POST, url)
                .header("Authorization", token, true)
                .body(JSON.toJSONString(ruleDTO), ContentType.JSON.toString())
                .execute();

        log.debug("{}", response);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        ResponseMessage rm = JSON.parseObject(response.body(), ResponseMessage.class);

    }

    @Test
    public void enableRulesForAllInClient() {
        String url = String.format("%s/rules/client?ruleTypes=23,24,15", host);
        HttpResponse response = HttpUtil.createRequest(Method.POST, url)
                .header("Authorization", token, true)
                .execute();
        log.debug("{}", response);
        Assert.assertNotNull(response);
        Assert.assertEquals(500, response.getStatus());
    }

    @Test
    public void enableRulesForAllInClient2() {
        String url = String.format("%s/rules/client?ruleTypes=33", host);
        HttpResponse response = HttpUtil.createRequest(Method.POST, url)
                .header("Authorization", token, true)
                .execute();
        log.debug("{}", response);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void updateRule() {
        String url = String.format("%s/rules?devices=%s", host, "15493500206");

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
        ruleDTO.setRuleId(529);

        HttpResponse response = HttpUtil.createRequest(Method.PUT, url)
                .header("Authorization", token, true)
                .body(JSON.toJSONString(ruleDTO), ContentType.JSON.toString())
                .execute();

        log.debug("{}", response);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void deleteRuleByDevices() {
        String url = String.format("%s/rules/devices?devices=%s", host, "123456789001");
        HttpResponse response = HttpUtil.createRequest(Method.DELETE, url)
                .header("Authorization", token, true)
                .execute();
        log.debug("{}", response);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void deleteRuleByIds() {
    }

    @Test
    public void disableRulesForAllInClient() {
    }

    @Test
    public void getAreaPoints() {
    }
}