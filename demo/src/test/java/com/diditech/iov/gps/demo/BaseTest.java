package com.diditech.iov.gps.demo;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;

import static com.diditech.iov.gps.demo.Const.*;

/**
 * @author zhjd <br>
 * @date 2023/5/9 <br>
 */
@Slf4j
public class BaseTest {
    public String token;

    @Before
    public void setUp() {
        String response = HttpUtil.post(String.format(AUTH_REQUEST, CLIENT, CLIENT_SECRET), "");
        token = "Bearer " + JSON.parseObject(response).get("access_token").toString();
        log.debug("token: {}", token);
    }
}
