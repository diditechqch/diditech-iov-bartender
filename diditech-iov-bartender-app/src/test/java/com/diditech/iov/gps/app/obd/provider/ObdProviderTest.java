package com.diditech.iov.gps.app.obd.provider;

import cn.hutool.core.net.URLEncoder;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.diditech.iov.gps.api.core.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhjd <br>
 * @date 2020/8/18 <br>
 */
@Slf4j
@SpringBootTest
public class ObdProviderTest {

    private String host = "http://localhost:9000/api";

    private String token;

    @Before
    public void setUp() {
        String response = HttpUtil.post("http://localhost:8080/oauth/token?grant_type=client_credentials&client_id=yqyc&client_secret=password", "");
        token = "Bearer " + JSON.parseObject(response).get("access_token").toString();
        log.debug("{}", token);
    }


    @Test
    public void getDeviceLocation() {
    }

    @Test
    public void getDeviceMileage() {
        String deviceNum = "15493500206";
        String url = String.format("%s/devices/mileage?deviceNum=%s&mileageType=1", host, deviceNum);
        HttpResponse response = HttpUtil.createRequest(Method.GET, url)
                .header("Authorization", token, true)
                .execute();
        log.debug("{}", response);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        ResponseMessage rm = JSON.parseObject(response.body(), ResponseMessage.class);
        Assert.assertNotNull(rm);
        JSONObject obj = JSON.parseObject(JSON.toJSONString(rm.getData()));
        Assert.assertTrue(StrUtil.equals(obj.get("deviceNum").toString(), deviceNum));
    }

    @Test
    public void updateDeviceMileage() {
        String deviceNum = "15493500206";
        String url = String.format("%s/devices/mileage/correction?deviceNum=%s&correctMileage=10000", host, deviceNum);
        HttpResponse response = HttpUtil.createRequest(Method.GET, url)
                .header("Authorization", token, true)
                .execute();
        log.debug("{}", response);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        ResponseMessage rm = JSON.parseObject(response.body(), ResponseMessage.class);
        Assert.assertNotNull(rm);
    }

    @Test
    public void getObdDrivingInfo() {
        String deviceNum = "14142921071";
        String url = String.format("%s/devices/obd?deviceNum=%s", host, deviceNum);
        HttpResponse response = HttpUtil.createRequest(Method.GET, url)
                .header("Authorization", token, true)
                .execute();
        log.debug("{}", response);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        ResponseMessage rm = JSON.parseObject(response.body(), ResponseMessage.class);
        Assert.assertNotNull(rm);
        JSONObject obj = JSON.parseObject(JSON.toJSONString(rm.getData()));
        Assert.assertTrue(obj.containsKey("deviceNum"));
        Assert.assertTrue(obj.containsKey("obdAirflow"));
        Assert.assertTrue(obj.containsKey("obdAit"));
        Assert.assertTrue(obj.containsKey("obdAlmTirePres"));
        Assert.assertTrue(obj.containsKey("obdAp"));
        Assert.assertTrue(obj.containsKey("obdApp"));
        Assert.assertTrue(obj.containsKey("obdAt"));
        Assert.assertTrue(obj.containsKey("obdAtp"));
        Assert.assertTrue(obj.containsKey("obdBbpwl"));
        Assert.assertTrue(obj.containsKey("obdBfls"));
        Assert.assertTrue(obj.containsKey("obdCl"));
        Assert.assertTrue(obj.containsKey("obdEl"));
        Assert.assertTrue(obj.containsKey("obdEol"));
        Assert.assertTrue(obj.containsKey("obdEst"));
        Assert.assertTrue(obj.containsKey("obdFaultNum"));
        Assert.assertTrue(obj.containsKey("obdFaultStatus"));
        Assert.assertTrue(obj.containsKey("obdFbpwl"));
        Assert.assertTrue(obj.containsKey("obdFciaa"));
        Assert.assertTrue(obj.containsKey("obdFm"));
        Assert.assertTrue(obj.containsKey("obdFp"));
        Assert.assertTrue(obj.containsKey("obdInp"));
        Assert.assertTrue(obj.containsKey("obdLtfa"));
        Assert.assertTrue(obj.containsKey("obdMileage"));
        Assert.assertTrue(obj.containsKey("obdOil"));
        Assert.assertTrue(obj.containsKey("obdOilUnit"));
        Assert.assertTrue(obj.containsKey("obdRotation"));
        Assert.assertTrue(obj.containsKey("obdSpeed"));
        Assert.assertTrue(obj.containsKey("obdWt"));
        Assert.assertTrue(obj.containsKey("updateTime"));
    }

    @Test
    public void getObdFaultInfo() {
        String deviceNum = "14142921071";
        String url = String.format("%s/devices/obd/fault?deviceNum=%s", host, deviceNum);
        HttpResponse response = HttpUtil.createRequest(Method.GET, url)
                .header("Authorization", token, true)
                .execute();
        log.debug("{}", response);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        ResponseMessage rm = JSON.parseObject(response.body(), ResponseMessage.class);
        Assert.assertNotNull(rm);
    }

    @Test
    public void insertDeviceCmd() {
        String deviceNum = "14142921071";
        String cmdName = URLEncoder.createDefault().encode("清除故障码", CharsetUtil.CHARSET_UTF_8);
        String cmd = "SETPARAM,8102,0003001";
        String cmdStr = URLEncoder.createDefault().encode("清除故障码", CharsetUtil.CHARSET_UTF_8);
        String url = String.format("%s/devices/cmd/insert?deviceNum=%s&cmdName=%s&cmd=%s&cmdStr=%s", host, deviceNum, cmdName, cmd, cmdStr);
        HttpResponse response = HttpUtil.createRequest(Method.GET, url)
                .header("Authorization", token, true)
                .execute();
        log.debug("{}", response);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        ResponseMessage rm = JSON.parseObject(response.body(), ResponseMessage.class);
        Assert.assertNotNull(rm);
    }

    @Test
    public void getDeviceCmd() {
    }

    @Test
    public void batchStartOrStop() {
        String url = String.format("%s/devices/batch?devices=%s,%s&isEnable=%s", host, "14142921071", "15493500206", 1);
        HttpResponse response = HttpUtil.createRequest(Method.GET, url)
                .header("Authorization", token, true)
                .execute();
        log.debug("{}", response);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        ResponseMessage rm = JSON.parseObject(response.body(), ResponseMessage.class);
        Assert.assertNotNull(rm);
    }
}