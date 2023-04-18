package com.diditech.iov.gps.app.device.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.diditech.iov.gps.api.device.domain.Device;
import com.diditech.iov.gps.api.device.domain.DeviceLocation;
import com.diditech.iov.gps.api.device.domain.DeviceMileage;
import com.diditech.iov.gps.api.report.domain.ReportPositionData;
import com.diditech.iov.gps.app.core.service.CoreService;
import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.device.po.BizDevice;
import com.diditech.iov.gps.app.device.po.BizDeviceCategory;
import com.diditech.iov.gps.app.device.po.BizDeviceCategoryExample;
import com.diditech.iov.gps.app.device.po.BizDeviceExample;
import com.diditech.iov.gps.app.device.repository.BizDeviceCategoryMapper;
import com.diditech.iov.gps.app.device.repository.BizDeviceMapper;
import com.diditech.iov.gps.app.device.repository.DeviceMapper;
import com.diditech.iov.gps.app.device.service.DeviceService;
import com.diditech.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhjd
 * @date 2020/6/18
 */
@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {

    @Value("${gps.redis.prefix.lastdevicedata}")
    private String redis_key_pre_lastdevicedata;

    @Autowired
    private DeviceMapper gpsMapper;

    @Autowired
    private BizDeviceMapper deviceMapper;

    @Autowired
    private RedisTemplate coreRedisTemplate;

    @Autowired
    private CoreService coreService;

    @Autowired
    private BizDeviceCategoryMapper categoryMapper;

    @Override
    public DeviceLocation getDeviceLocation(String deviceNum) {
        return gpsMapper.getDeviceLocation(deviceNum);
    }

    @Override
    public DeviceMileage getDeviceMileage(String deviceNum, int mileageType) {
        DeviceMileage deviceMileage = gpsMapper.getDeviceMileage(deviceNum);
        if (deviceMileage == null) {
            return null;
        }
        deviceMileage.setMileageType(mileageType);
        Double resultMileage;
        // modify by zhjd 20220314 start
        if (mileageType == 1 && deviceMileage.getHardMileage() != null) {
            resultMileage = deviceMileage.getHardMileage();
        } else if (mileageType == 2 && deviceMileage.getObdMileage() != null) {
            resultMileage = deviceMileage.getObdMileage();
        } else if (mileageType == 3 && deviceMileage.getTotalMileage() != null) {
            resultMileage = deviceMileage.getTotalMileage()
                    + (deviceMileage.getZqycDiff() == null ? 0D : deviceMileage.getZqycDiff());
        } else {
            return deviceMileage;
        }
        deviceMileage.setResultMileage(
                BigDecimal.valueOf(resultMileage)
                        .divide(BigDecimal.valueOf(1000), 2, BigDecimal.ROUND_HALF_UP)
                        .doubleValue());
        // modify by zhjd 20220314 end
        return deviceMileage;
    }

    @Override
    public int updateDeviceMileage(String deviceNum, double correctMileage) {
        int correctMileageInt = (int) (correctMileage * 1000);
        DeviceMileage deviceMileage = gpsMapper.getDeviceMileageDiffer(deviceNum, correctMileageInt);
        if (deviceMileage == null) {
            return -1;
        }
        return gpsMapper.updateDeviceMileage(deviceNum, deviceMileage.getZqycDiff().intValue());
    }

    @Override
    public String getInvalidDeviceNum(String clientId, String... deviceNums) {
        for (String num : deviceNums) {
            int count = gpsMapper.countByClientNDeviceNum(clientId, num);
            if (count != 1) {
                return num;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void batchStartOrStop(String[] devices, String clientId, Integer isEnable) {
        int result = gpsMapper.batchStartOrStop(devices, clientId, isEnable);
        if (result != devices.length) {
            log.error("批量启停更新数量不一致，共{}，成功{}，{}", devices.length, result, devices);
        }
        // 删除lastdevicedata缓存
        Arrays.stream(devices)
                .forEach(deviceNum -> coreRedisTemplate.delete(redis_key_pre_lastdevicedata.concat(deviceNum)));
    }

    @Override
    public int addDevice(Device device) {
        String categoryNo = device.getCategoryNo().trim();
        if (device.getLbsEnable() == null) {
            device.setLbsEnable(
                    coreService.getDefaultLbsFlag(categoryNo, device.getClientId()));
        }
        if (device.getTimeoutThreshold() == null) {
            device.setTimeoutThreshold(
                    coreService.getDefaultTimeoutThreshold(categoryNo, device.getClientId()));
        }
        BizDevice deviceDto = new BizDevice();
        BeanUtil.copyProperties(device, deviceDto);
        deviceDto.setIsdel((byte) 0);
        deviceDto.setUpdateTime(new Date());
        deviceDto.setCreateTime(new Date());
        return deviceMapper.insert(deviceDto);
    }

    @Override
    public int deleteDevice(String deviceNum) {
        BizDeviceExample deviceExample = new BizDeviceExample();
        deviceExample.createCriteria().andDeviceNumEqualTo(deviceNum);
        return deviceMapper.deleteByExample(deviceExample);
    }

    @Override
    public int updateDevice(Device device) {
        BizDevice deviceDto = new BizDevice();
        BeanUtil.copyProperties(device, deviceDto);
        BizDeviceExample deviceExample = new BizDeviceExample();
        deviceExample.createCriteria().andDeviceNumEqualTo(device.getDeviceNum());
        return deviceMapper.updateByExampleSelective(deviceDto, deviceExample);
    }

    @Override
    public boolean isExistDevice(String deviceNum) {
        return gpsMapper.isExistDevice(deviceNum) > 0;
    }

    @Override
    public Integer isValidCategoryNo(String categoryNo) {
        return gpsMapper.getCateIdByCateNo(categoryNo);
    }

    @Override
    public BizDeviceCategory getDeviceCategory(String categoryNo) {
        BizDeviceCategoryExample example = new BizDeviceCategoryExample();
        example.createCriteria().andCategoryNoEqualTo(categoryNo);
        List<BizDeviceCategory> list = categoryMapper.selectByExample(example);
        if (Util.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void addSimpleDevice(String[] deviceNums, BizDeviceCategory deviceCategory, Integer wifiFlag, String clientId) {

        List<String> addList = Arrays.stream(deviceNums)
                .filter(item -> !isExistDevice(item))
                .collect(Collectors.toList());
        if (Util.isEmpty(addList)) {
            return;
        }

        Device common = new Device();
        common.setCategoryNo(deviceCategory.getCategoryNo());
        common.setIsenable(1);
        common.setWifiFlag(wifiFlag == null ? deviceCategory.getWifiFlag().intValue() : wifiFlag);
        common.setClientId(clientId);
        common.setCategoryId(deviceCategory.getId());

        addList.stream()
                .map(item -> {
                    Device device = new Device();
                    BeanUtil.copyProperties(common, device);
                    device.setDeviceNum(item);
                    return device;
                })
                .forEach(this::addDevice);
    }

    @Override
    public List<ReportPositionData> getPositionReport(List<String> deviceNums, Date beginTime, Date endTime, String coorType) {
        List<ReportPositionData> list = gpsMapper.getPositionReport(deviceNums, beginTime, endTime, coorType);
        list.forEach(item -> buildStatus(item));
        return list;
    }

    private void buildStatus(ReportPositionData item) {
        String status;
        if (item.getIsOffline()) {
            status = "离线";
        } else {
            status = item.getIsMoving() ? "行驶" : "停车";
            status += Const.SPACE;
            status += item.getIsAccOn() ? "点火" : "熄火";
        }
        item.setStatus(status);
    }
}
