package com.diditech.iov.gps.app.obd.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.diditech.iov.gps.api.obd.domain.ObdData;
import com.diditech.iov.gps.api.obd.domain.ObdFault;
import com.diditech.iov.gps.api.obd.domain.ObdFaultDesc;
import com.diditech.iov.gps.app.core.config.CategoryIdConfig;
import com.diditech.iov.gps.app.core.po.TimeFrameQuery;
import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.core.util.IncrementalDataFilter;
import com.diditech.iov.gps.app.obd.repository.ObdMapper;
import com.diditech.iov.gps.app.obd.service.ObdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhaist
 * @date 2020/11/19
 */
@Slf4j
@Service
public class ObdServiceImpl implements ObdService {

    @Value("${obd.fuel.threshold}")
    private int threshold;

    @Autowired
    private CategoryIdConfig categoryIdConfig;

    @Autowired
    private ObdMapper obdMapper;

    @Override
    public List<ObdFault> getObdFaultList(String deviceNum, String vehicleBrand) {

        List<ObdFault> obdFaults = obdMapper.getObdFaultList(deviceNum);
        if (CollUtil.isEmpty(obdFaults)) {
            return null;
        }

        List<ObdFault> validObdFaults = obdFaults.stream()
                .filter(item -> item.getStatus() == 0)
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(validObdFaults)) {
            return null;
        }

        List<ObdFault> splitAsIndividuals = validObdFaults.stream()
                .map(this::mapToList)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        List<String> errorCodes = splitAsIndividuals.stream()
                .map(ObdFault::getErrorCode)
                .distinct()
                .collect(Collectors.toList());

        List<ObdFaultDesc> obdFaultDescList =
                obdMapper.getObdFaultDescList(errorCodes, StrUtil.isBlank(vehicleBrand) ? null : vehicleBrand);

        for (ObdFault obd : splitAsIndividuals) {
            for (ObdFaultDesc desc : obdFaultDescList) {
                if (StrUtil.equalsIgnoreCase(obd.getErrorCode(), desc.getCode())) {
                    BeanUtils.copyProperties(desc, obd);
                }
            }
        }

        return splitAsIndividuals;
    }

    @Override
    public ObdData getObdData(String deviceNum) {
        return obdMapper.getObdData(deviceNum);
    }

    @Override
    public Integer getObdFuel(String deviceNum, Date startTime, Date endTime) {
        Integer categoryId = obdMapper.getCategoryId(deviceNum);
        List<Integer> categoryIds = categoryIdConfig.getCategoryIds();
        if(!categoryIds.contains(categoryId)){
            return obdMapper.getObdFuel(deviceNum, startTime, endTime);
        }
        List<Integer> obdFuels = obdMapper.getAllFuel(deviceNum, startTime, endTime);
        if (CollectionUtil.isEmpty(obdFuels)) {
            return 0;
        }
        int result = IncrementalDataFilter.doDiff(obdFuels, threshold);
        if(result < 0){
            log.error("设备号：{} 开始时间：{} 结束时间：{} 区间油耗：{} 已更新为正值",deviceNum, startTime, endTime, result);
            result = Math.abs(result);
        }
        return result;
    }

    @Override
    public Integer[] getObdFuelMulti(String deviceNum, List<TimeFrameQuery> timeFramesItems) {
        return obdMapper.getObdFuelMulti(deviceNum, timeFramesItems);
    }

    private List<ObdFault> mapToList(ObdFault data) {
        return Arrays.stream(data.getErrorCode().split(Const.SEP_COMMA))
                .map(item -> {
                    ObdFault fault = new ObdFault();
                    BeanUtils.copyProperties(data, fault);
                    fault.setErrorCode(item);
                    return fault;
                })
                .collect(Collectors.toList());
    }
}
