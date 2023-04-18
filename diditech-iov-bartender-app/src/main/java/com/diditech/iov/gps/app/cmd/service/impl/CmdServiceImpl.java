package com.diditech.iov.gps.app.cmd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.api.cmd.domain.ClientCmdDTO;
import com.diditech.iov.gps.api.cmd.domain.ClientCommand;
import com.diditech.iov.gps.api.cmd.domain.CmdStatus;
import com.diditech.iov.gps.api.core.BusinessException;
import com.diditech.iov.gps.api.device.domain.DeviceCmd;
import com.diditech.iov.gps.api.device.domain.DeviceLocation;
import com.diditech.iov.gps.api.report.domain.ReportCmdData;
import com.diditech.iov.gps.app.cmd.service.CmdService;
import com.diditech.iov.gps.app.device.po.BizDeviceCmd;
import com.diditech.iov.gps.app.device.po.BizDeviceCmdExample;
import com.diditech.iov.gps.app.device.repository.BizDeviceCmdMapper;
import com.diditech.iov.gps.app.device.repository.DeviceMapper;
import com.diditech.iov.gps.app.publish.PublishService;
import com.diditech.iov.gps.app.subscribe.SubscribeService;
import dd.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 指令服务实体类
 * @author zhjd <br>
 * @date 2021/8/12 <br>
 */
@Slf4j
@Service
public class CmdServiceImpl implements CmdService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private BizDeviceCmdMapper cmdMapper;

    @Autowired
    private PublishService publishService;

    @Autowired
    private SubscribeService subscribeService;

    @Override
    public void validateCmdList(ClientCmdDTO[] clientCmdList) {
        if (Util.isEmpty(clientCmdList)) {
            throw new BusinessException("指令内容不可为空");
        }
        for (ClientCmdDTO item : clientCmdList) {
            if (StrUtil.isBlank(item.getDeviceNum())) {
                throw new BusinessException("deviceNum不可为空");
            }
            if (StrUtil.isBlank(item.getCmd())) {
                throw new BusinessException("cmd不可为空");
            }
        }
    }

    @Override
    public List<DeviceCmd> getCmds(String[] cmdIds, String clientId) {
        List<Integer> ids = Arrays.stream(cmdIds)
                .map(Convert::toInt)
                .collect(Collectors.toList());
        BizDeviceCmdExample example = new BizDeviceCmdExample();
        example.createCriteria().andClientIdEqualTo(clientId)
                .andIdIn(ids);
        List<BizDeviceCmd> list = cmdMapper.selectByExample(example);
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        return list.stream()
                .map(item -> {
                    DeviceCmd vo = new DeviceCmd();
                    BeanUtil.copyProperties(item, vo);
                    return vo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void issueAndSaveRealTimeCmd(List<ClientCmdDTO> realTimeCmdList, String clientId) {
        realTimeCmdList.forEach(this::getOnlineStatus);

        List<ClientCmdDTO> saveList = realTimeCmdList.stream()
                .filter(item -> StrUtil.isNotBlank(item.getHost()) || item.isRequireCache())
                .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(saveList)) {
            saveCmd(saveList, clientId);
        }

        List<ClientCmdDTO> onlineList = realTimeCmdList.stream()
                .filter(item -> StrUtil.isNotBlank(item.getHost()))
                .collect(Collectors.toList());

        List<ClientCommand> availableCmd = null;
        if (CollUtil.isNotEmpty(onlineList)) {
            availableCmd = onlineList.stream()
                    .map(this::buildClientCommand)
                    .collect(Collectors.toList());
        }

        if (CollUtil.isNotEmpty(availableCmd)) {
            availableCmd.forEach(item -> {
                publishService.publishTcpMsg(item.getHost(), item.getPort(), JSON.toJSONString(item));
                subscribeService.markSubRealTimeCmd(item);
            });
        }

        // check issue status after insert
        if (CollUtil.isNotEmpty(availableCmd)) {
            awaitForCmdIssued(availableCmd);
        }
    }

    private void getOnlineStatus(ClientCmdDTO item) {
        DeviceLocation location = deviceMapper.getDeviceLocation(item.getDeviceNum());
        if (location == null || location.getAlmOffline() == 1 || Util.isEmpty(location.getTag())) {
            return;
        }
        String[] tagInfo = location.getTag().split(StrUtil.SPACE);
        if (tagInfo.length < 2) {
            return;
        }
        item.setHost(tagInfo[0]);
        item.setPort(Convert.toInt(tagInfo[1], null));
        item.setStatus(CmdStatus.SEND.getCode());
    }

    @Override
    public void saveCmd(List<ClientCmdDTO> cmdList, String clientId) {
        List<BizDeviceCmd> entityList =
                cmdList.parallelStream()
                        .map(item -> {
                            BizDeviceCmd entity = new BizDeviceCmd();
                            item.setCmdTime(new Date());
                            BeanUtil.copyProperties(item, entity);
                            entity.setClientId(clientId);
                            entity.setExeCount((short) 0);
                            entity.setBatId(0);
                            return entity;
                        })
                        .collect(Collectors.toList());
        deviceMapper.insertCmdList(entityList);
        for (int i = 0; i < entityList.size(); i++) {
            cmdList.get(i).setId(entityList.get(i).getId());
        }
    }

    @Override
    public List<ReportCmdData> getCmdReport(List<String> deviceNums, Date beginTime, Date endTime) {
        BizDeviceCmdExample example = new BizDeviceCmdExample();
        example.createCriteria()
                .andDeviceNumIn(deviceNums)
                .andCmdTimeBetween(beginTime, endTime);
        List<BizDeviceCmd> list = cmdMapper.selectByExample(example);
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        return list.stream()
                .map(item -> {
                    ReportCmdData vo = new ReportCmdData();
                    BeanUtil.copyProperties(item, vo);
                    return vo;
                })
                .collect(Collectors.toList());
    }

    private void awaitForCmdIssued(List<ClientCommand> onlineCcList) {
        long start = System.currentTimeMillis();
        long end = start + 3000L;
        boolean success = false;
        while (!success && System.currentTimeMillis() < end) {
            // 批量时一个成功即为成功
            success = onlineCcList.stream()
                    .anyMatch(item -> subscribeService.isRtCmdIssued(item));
        }
    }

    private ClientCommand buildClientCommand(ClientCmdDTO dto) {
        ClientCommand cc = new ClientCommand();
        BeanUtil.copyProperties(dto, cc, true);
        cc.setDevice_num(dto.getDeviceNum());
        cc.setCmd_time(dto.getCmdTime());
        cc.setTokenId((dto.isRealTime() ? "gps-" + System.currentTimeMillis() : null));
        return cc;
    }
}
