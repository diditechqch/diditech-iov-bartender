package com.diditech.iov.gps.app.cmd.provider;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.diditech.iov.gps.api.cmd.CmdApi;
import com.diditech.iov.gps.api.cmd.domain.ClientCmdDTO;
import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.app.cmd.service.CmdService;
import com.diditech.iov.gps.app.core.aspect.RequestHelper;
import com.diditech.iov.gps.app.device.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 指令
 * @author zhjd <br>
 * @date 2021/8/12 <br>
 */
@RestController
public class CmdProvider implements CmdApi {

    @Autowired
    private CmdService cmdService;

    @Autowired
    private DeviceService deviceService;

    @Override
    public ResponseMessage saveCmd(@RequestBody ClientCmdDTO[] clientCmdList) {
        cmdService.validateCmdList(clientCmdList);

        String invalidNum = deviceService.getInvalidDeviceNum(RequestHelper.getClientId(),
                Arrays.stream(clientCmdList).map(ClientCmdDTO::getDeviceNum).toArray(String[]::new));
        if (StrUtil.isNotBlank(invalidNum)) {
            return ResponseMessage.error("设备号不存在或无权限：" + invalidNum);
        }

        List<ClientCmdDTO> realTimeCmdList = Arrays.stream(clientCmdList)
                .filter(ClientCmdDTO::isRealTime)
                .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(realTimeCmdList)) {
            cmdService.issueAndSaveRealTimeCmd(realTimeCmdList, RequestHelper.getClientId());
        }

        List<ClientCmdDTO> insertList = Arrays.stream(clientCmdList)
                .filter(item -> !realTimeCmdList.contains(item))
                .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(insertList)) {
            cmdService.saveCmd(insertList, RequestHelper.getClientId());
        }

        return ResponseMessage.ok(clientCmdList);
    }

    @Override
    public ResponseMessage getCmd(@RequestParam("cmdIds") String[] cmdIds) {
        String clientId = RequestHelper.getClientId();
        return ResponseMessage.ok(cmdService.getCmds(cmdIds, clientId));
    }
}
