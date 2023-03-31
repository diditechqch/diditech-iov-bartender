package com.diditech.iov.gps.app.subscribe;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.api.cmd.domain.ClientCommand;
import com.diditech.iov.gps.api.cmd.domain.R;
import com.diditech.iov.gps.api.cmd.domain.RealTimeCmdR;
import com.google.common.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 订阅跨平台业务消息
 * @author zhjd
 * @date 2021/10/13
 */
@Slf4j
@Service
public class SubscribeService {

    @Autowired
    private Cache<String, List<R>> TcpSubscriberEnvelope;

    public boolean isRtCmdIssued(ClientCommand cc) {

        String key = cc.getTokenId().split(StrUtil.DASHED)[1];
        if (TcpSubscriberEnvelope.getIfPresent(key) == null) {
            return false;
        }

        List<R> cmdRList = TcpSubscriberEnvelope.getIfPresent(key);
        int index = -1;
        R r;
        RealTimeCmdR result;
        for (int i = 0; i < cmdRList.size(); i++) {
            r = cmdRList.get(i);
            try {
                result = JSON.parseObject(r.getData().toString(), RealTimeCmdR.class);
            } catch (Exception ex) {
                continue;
            }
            if (cc.getDevice_num().equals(result.getDeviceNum())) {
                index = i;
                break;
            }
        }

        if (index > -1 && cmdRList.size() > index) {
            // 此处有并发问题，但由于批量时一个成功即成功，所以不需要加锁
            log.debug("remove index:{}, from {}", index, JSON.toJSONString(cmdRList));
            cmdRList.remove(index);
            return true;
        }
        return false;
    }

    public void markSubRealTimeCmd(ClientCommand item) {
        String key = item.getTokenId().split(StrUtil.DASHED)[1];
        TcpSubscriberEnvelope.put(key, new ArrayList<>());
    }
}
