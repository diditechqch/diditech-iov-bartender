package com.diditech.iov.gps.app.cmd.service;

import com.diditech.iov.gps.api.cmd.domain.ClientCmdDTO;
import com.diditech.iov.gps.api.device.domain.DeviceCmd;

import java.util.List;

/**
 * 指令服务接口类
 * @author zhjd <br>
 * @date 2021/8/12 <br>
 */
public interface CmdService {

    /**
     * 校验指令
     * @date 2022/3/22
     * @author zhjd
     */
    void validateCmdList(ClientCmdDTO[] clientCmdList);

    /**
     * 获取指令详情
     * @date 2021/2/23
     * @author zhjd
     */
    List<DeviceCmd> getCmds(String[] cmdIds, String clientId);

    /**
     * 下发并存储实时指令，若标记requireCache=false则不存储
     * @date 2022/3/22
     * @author zhjd
     */
    void issueAndSaveRealTimeCmd(List<ClientCmdDTO> realTimeCmdList, String clientId);

    /**
     * 存储指令
     * @date 2022/3/22
     * @author zhjd
     */
    void saveCmd(List<ClientCmdDTO> cacheCmdList, String clientId);

}
