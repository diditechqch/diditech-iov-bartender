package com.diditech.iov.gps.api.cmd;

import com.diditech.iov.gps.api.cmd.domain.ClientCmdDTO;
import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.device.domain.DeviceCmd;
import org.springframework.web.bind.annotation.*;

/**
 * 设备指令接口
 * @author zhjd
 * @date 2022/3/21
 */
@RequestMapping(value = "/devices")
public interface CmdApi {

    /**
     * 存储指令
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 List&lt;{@link ClientCmdDTO}&gt;存储成功的会返回指令主键ID
     **/
    @PostMapping("/cmd")
    ResponseMessage saveCmd(@RequestBody ClientCmdDTO[] clientCmdList);

    /**
     * 查询指令详情
     * @param cmdIds 指令ID
     * @return 返回对象 {@link ResponseMessage ResponseMessage.getData()}值为 List&lt;{@link DeviceCmd}&gt;
     **/
    @GetMapping("/cmd")
    ResponseMessage getCmd(@RequestParam("cmdIds") String[] cmdIds);
}
