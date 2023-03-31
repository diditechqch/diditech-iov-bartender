package com.diditech.iov.gps.app.core.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.diditech.iov.gps.api.device.domain.ClientConfig;
import com.diditech.iov.gps.app.device.repository.DeviceMapper;
import com.diditech.iov.gps.app.rules.po.ClientEventType;
import com.diditech.iov.gps.app.rules.po.ClientEventTypeExample;
import com.diditech.iov.gps.app.rules.repository.ClientEventTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局数据配置
 * @author zhjd
 * @date 2023/3/6
 */
@Configuration
public class GlobalDataConfig {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private ClientEventTypeMapper clientEventTypeMapper;

    @Bean("clientEventTypes")
    public Map<String, Set<Integer>> clientEventTypes() {
        Map<String, Set<Integer>> clientEventTypes = new HashMap<>();
        ClientEventTypeExample example = new ClientEventTypeExample();
        example.createCriteria().andIdNotEqualTo(0);
        List<ClientEventType> list = clientEventTypeMapper.selectByExample(example);
        if (CollUtil.isEmpty(list)) {
            return clientEventTypes;
        }
        Set<String> clientSet = list.stream().map(ClientEventType::getClientId).collect(Collectors.toSet());
        Set<Integer> types;
        for (String clientId : clientSet) {
            types = list.stream()
                    .filter(item -> StrUtil.equalsAnyIgnoreCase(clientId, item.getClientId()))
                    .map(ClientEventType::getRuleType)
                    .collect(Collectors.toSet());
            clientEventTypes.put(clientId, types);
        }
        return clientEventTypes;
    }

    @Bean("gpsGlobalData")
    public List<ClientConfig> gpsGlobalData() {
        return deviceMapper.getDeviceConfigData();
    }
}
