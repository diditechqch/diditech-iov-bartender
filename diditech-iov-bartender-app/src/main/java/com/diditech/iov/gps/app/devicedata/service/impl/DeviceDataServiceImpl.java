package com.diditech.iov.gps.app.devicedata.service.impl;

import com.diditech.iov.gps.api.devicedata.domain.PackageData;
import com.diditech.iov.gps.app.device.util.DecodeUtil;
import com.diditech.iov.gps.app.devicedata.service.DeviceDataService;
import dd.monitor.dm.device.IDecodeFilter;
import dd.monitor.dm.device.IDevice;
import dd.monitor.dm.device.RodimusServiceUtil;
import dd.monitor.dm.model.DeviceData;
import dd.monitor.dm.model.KEY;
import dd.monitor.dm.util.Const;
import dd.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.jboss.netty.buffer.ChannelBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 设备报文解析服务实例类
 * @author zhjd
 * @date 2019/11/12
 */
@Slf4j
@Service
public class DeviceDataServiceImpl implements DeviceDataService {

    @Autowired
    @Qualifier("filters")
    private Map<String, IDevice> filters;

    @Autowired
    @Qualifier("allKeys")
    private List<String[]> allKeys;

    @Autowired
    @Qualifier("allKeysPlat")
    private Set<String> allKeysPlat;

    @Autowired
    @Qualifier("redundantKeys")
    private Set<String> redundantKeys;

    @Autowired
    @Qualifier("transcodeDevices")
    private List<String> transcodeDevices;

    @Autowired
    @Qualifier("transcodeRule")
    private Map<String, String> transcodeRule;

    @Override
    public PackageData decodeDeviceData(String protocolType, String rawMessage) {
        if (rawMessage.startsWith("TIME AT")) {
            return new PackageData();
        }
        try {
            if (!filters.keySet().contains(protocolType)) {
                log.error("报文{}无法使用{}协议解析", rawMessage, protocolType);
                return new PackageData();
            }
            DeviceData devicedata = doFilter(protocolType, rawMessage);
            if (devicedata.getOriginalMap().size() < 1) {
                log.error("解析失败：{}-{}", protocolType, rawMessage);
                return new PackageData();
            }

            if (Util.isEmpty(devicedata.getSubDeviceData())) {
                return buildPackageData(devicedata);
            }

            return buildMultiPackageData(devicedata);

        } catch (Exception excep) {
            log.error("", excep);
            return new PackageData();
        }
    }

    /**
     * 轮询包类型解析报文
     */
    private DeviceData doFilter(String protocolType, String rawMessage) {
        DeviceData devicedata = new DeviceData();
        IDecodeFilter[] decodeFilterList = filters.get(protocolType).getDecodeFilters();
        try {
            devicedata.setDecoded(false);
            devicedata.add(KEY.DEVICE_TYPE, protocolType);
            ChannelBuffer bytes = RodimusServiceUtil.transcode(rawMessage, protocolType, decodeFilterList, transcodeDevices, transcodeRule);
            for (IDecodeFilter f : decodeFilterList) {
                f.doFilter(devicedata, bytes);
                if (devicedata.isDecoded()) {
                    break;
                }
            }
        } catch (Exception ex) {
            log.error("解析错误:" + rawMessage, ex);
            return new DeviceData();
        }
        Object reportCode = devicedata.get(KEY.REPORT_TYPE);
        if (reportCode != null) {
            devicedata.add(KEY.REPORT_TYPE, DecodeUtil.getReportTypeDesc(reportCode));
        }
        String btsInfo = getBtsInfo(devicedata);
        if (!Util.isEmpty(btsInfo)) {
            devicedata.add("基站信息", Const.SEP_FILE.concat(getBtsInfo(devicedata)));
        }
        getSignalDesc(devicedata);
        return devicedata;
    }

    /**
     * 构造基站解析结果
     * @author zhjd
     * @date 2019/11/20
     */
    private String getBtsInfo(DeviceData devicedata) {
        int count = 0;
        StringBuilder bts = new StringBuilder();
        if (devicedata.get(KEY.LAC) != null) {
            bts.append(KEY.LAC);
            bts.append(Const.SEP_EQUAL);
            bts.append(devicedata.get(KEY.LAC));
            bts.append(Const.SEP_COMMA);
        }
        if (devicedata.get(KEY.CELL_ID) != null) {
            bts.append(KEY.CELL_ID);
            bts.append(Const.SEP_EQUAL);
            bts.append(devicedata.get(KEY.CELL_ID));
            bts.append(Const.SEP_COMMA);
        }
        if (devicedata.get(KEY.CELL_DBM) != null) {
            bts.append(KEY.CELL_DBM);
            bts.append(Const.SEP_EQUAL);
            bts.append(devicedata.get(KEY.CELL_DBM));
        }
        if (bts.length() > 0) {
            bts.append(Const.SEP_FILE);
            count++;
        }

        StringBuilder nearbts = new StringBuilder();
        boolean hasNearBts;
        for (int i = 1; i <= 6; i++) {
            hasNearBts = false;
            if (devicedata.get(KEY.LAC + i) != null) {
                nearbts.append(KEY.LAC).append(i);
                nearbts.append(Const.SEP_EQUAL);
                nearbts.append(devicedata.get(KEY.LAC + i));
                nearbts.append(Const.SEP_COMMA);
                hasNearBts = true;
            }
            if (devicedata.get(KEY.CELL_ID + i) != null) {
                nearbts.append(KEY.CELL_ID).append(i);
                nearbts.append(Const.SEP_EQUAL);
                nearbts.append(devicedata.get(KEY.CELL_ID + i));
                nearbts.append(Const.SEP_COMMA);
                hasNearBts = true;
            }
            if (devicedata.get(KEY.CELL_DBM + i) != null) {
                nearbts.append(KEY.CELL_DBM).append(i);
                nearbts.append(Const.SEP_EQUAL);
                nearbts.append(!Util.isNull(devicedata.get(KEY.CELL_DBM + i)) ? devicedata.get(KEY.CELL_DBM + i) : "");
                hasNearBts = true;
            }
            if (hasNearBts && nearbts.length() > 0) {
                count++;
                if (i != 6) {
                    nearbts.append(Const.SEP_FILE);
                }
            }
        }
        if (count == 0) {
            return null;
        }
        if (count == 1) {
            return bts.toString().substring(0, bts.length() - 2);
        }
        return bts.toString().concat(nearbts.toString());
    }

    /**
     * 生成应用层使用对象
     */
    private PackageData buildPackageData(DeviceData dd) {
        List<String[]> allKeys = getAllKeys(dd);
        PackageData packageData = new PackageData();
        String[] details = allKeys.stream()
                .map(item -> getDetailsInfo(item, dd))
                .filter(item -> item.length() > 0)
                .toArray(String[]::new);
        packageData.setDetails(details);
        packageData.setPackageType(Util.asString(dd.get(KEY.DATA_TYPE)));
        return packageData;
    }

    /**
     * 含有分包的报文，如部标协议，生成应用层使用对象
     */
    private PackageData buildMultiPackageData(DeviceData devicedata) {
        List<DeviceData> subDeviceData = devicedata.getSubDeviceData();
        List<String[]> subPackageData = new ArrayList<>();
        subPackageData.add(buildPackageData(devicedata).getDetails());
        for (int i = 0; i < subDeviceData.size(); i++) {
            subPackageData.add(new String[]{"分包 " + (i + 1)});
            subPackageData.add(buildPackageData(subDeviceData.get(i)).getDetails());
        }
        String[] subData = subPackageData.stream().flatMap(Arrays::stream).toArray(String[]::new);
        PackageData packageData = new PackageData();
        packageData.setDetails(subData);
        packageData.setPackageType(Util.asString(devicedata.get(KEY.DATA_TYPE)));
        return packageData;
    }

    private List<String[]> getAllKeys(DeviceData dd) {
        Set<String> originalKeySet = dd.getOriginalMap().keySet();
        String[] otherKeys = originalKeySet.stream()
                .filter(item -> !allKeysPlat.contains(item) && !redundantKeys.contains(item))
                .toArray(String[]::new);
        List<String[]> list = new ArrayList<>(8);
        list.addAll(allKeys);
        if (otherKeys.length > 0) {
            list.add(otherKeys);
        }
        return list;
    }

    private String getDetailsInfo(String[] keys, DeviceData dd) {
        return Arrays.stream(keys)
                .filter(key -> !Util.isEmpty(dd.get(key)))
                .map(key -> key.concat(Const.SEP_EQUAL).concat(Util.asString(dd.get(key))))
                .collect(Collectors.joining(Const.SEP_COMMA));
    }

    public void getSignalDesc(DeviceData dd) {
        Object signalLvl = dd.get(KEY.SIGNAL_LEVEL);
        if (!Util.isEmpty(signalLvl)) {
            dd.add(KEY.SIGNAL_LEVEL, DecodeUtil.getSignalDesc(signalLvl));
        }
    }
}
