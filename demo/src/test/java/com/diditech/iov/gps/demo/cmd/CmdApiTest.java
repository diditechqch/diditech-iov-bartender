package com.diditech.iov.gps.demo.cmd;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.api.cmd.domain.ClientCmdDTO;
import com.diditech.iov.gps.demo.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.diditech.iov.gps.demo.Const.GATEWAY_REQUEST;

/**
 * @author zhjd <br>
 * @date 2023/5/9 <br>
 */
@Slf4j
public class CmdApiTest extends BaseTest {

    private String requestPath = GATEWAY_REQUEST + "/devices/cmd";

    @Test
    public void saveCmd() {
        ClientCmdDTO dto = new ClientCmdDTO();
        dto.setCmdTime(new Date());
        dto.setCmd("SERVER#");
        dto.setDeviceNum("123456789002");
        dto.setRealTime(true); // 设置true下发实时指令
        dto.setRequireCache(false); // 设置false当实时指令下发失败时缓存等待下次上线发送
        List<ClientCmdDTO> clientCmdList = new ArrayList<>();
        clientCmdList.add(dto);

        HttpResponse response = HttpUtil.createRequest(Method.POST, requestPath)
                .header("Authorization", token, true)
                .body(JSON.toJSONString(clientCmdList), ContentType.JSON.toString())
                .execute();
        log.debug("{}", response);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void getCmd() {
        String url = requestPath + "?cmdIds=1";
        HttpResponse response = HttpUtil.createRequest(Method.GET, url)
                .header("Authorization", token, true)
                .execute();
        log.debug("{}", response);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }
}