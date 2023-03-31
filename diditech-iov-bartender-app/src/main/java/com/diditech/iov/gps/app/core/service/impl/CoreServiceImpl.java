package com.diditech.iov.gps.app.core.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.diditech.iov.gps.api.core.BusinessException;
import com.diditech.iov.gps.api.core.Page;
import com.diditech.iov.gps.api.device.domain.ClientConfig;
import com.diditech.iov.gps.app.core.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * OBD数据服务实现类
 * @author zhjd
 * @date 2019/3/12
 */
@Service
public class CoreServiceImpl implements CoreService {

    @Value("${client.default.lbs-enable}")
    private int lbsEnableConfig;

    @Value("${client.default.timeout-threshold}")
    private int timeoutThresholdConfig;

    @Autowired
    @Qualifier("gpsGlobalData")
    private List<ClientConfig> gpsGlobalData;

    @Override
    public void platformValidation(int platformFlag) {
        switch (platformFlag) {
            case (1):
            case (2):
                return;
            default:
                throw new BusinessException("platformFlag参数错误。1：平台1；2：平台2。可为null，默认平台2");
        }
    }

    @Override
    public Date getValidDate(String dateStr, String format) {
        try {
            return DateUtil.parse(dateStr, format);
        } catch (Exception ex) {
            throw new BusinessException("时间格式错误，请使用" + format);
        }
    }

    @Override
    public void dateValidation(String startTime, String endTime, String format, Long maxMillis) {
        Date startDate = getValidDate(startTime, format);
        Date endDate = getValidDate(endTime, format);
        if (endDate.before(startDate)) {
            throw new BusinessException("结束时间需晚于开始时间");
        }
        if (maxMillis != null && (endDate.getTime() - startDate.getTime()) > maxMillis) {
            throw new BusinessException("暂不支持时间范围超过milli: " + maxMillis);
        }
    }

    @Override
    public void dateValidation(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new BusinessException("无效时间");
        }
        if (endDate.before(startDate)) {
            throw new BusinessException("结束时间需晚于开始时间");
        }
    }

    @Override
    public int getDefaultLbsFlag(String categoryNo, String clientId) {
        Optional<ClientConfig> configs = gpsGlobalData
                .stream()
                .filter(item -> categoryNo.equalsIgnoreCase(item.getCategoryNo())
                        && clientId.equalsIgnoreCase(item.getClientId()))
                .findAny();
        if (configs.isPresent()) {
            return configs.get().getLbsEnable();
        }
        return lbsEnableConfig;
    }

    @Override
    public int getDefaultTimeoutThreshold(String categoryNo, String clientId) {
        Optional<ClientConfig> configs = gpsGlobalData.stream()
                .filter(item -> categoryNo.equalsIgnoreCase(item.getCategoryNo())
                        && clientId.equalsIgnoreCase(item.getClientId()))
                .findAny();
        if (configs.isPresent()) {
            return configs.get().getTimeoutThreshold();
        }
        return timeoutThresholdConfig;
    }

    @Override
    public <T> Page<T> getPaged(List<T> list,
                                Comparator<T> comparator,
                                Integer pageSize,
                                Integer pageNo) {
        Page<T> page = new Page<>(
                CollUtil.sortPageAll(pageNo == null ? 1 : pageNo,
                        pageSize == null ? list.size() : pageSize,
                        comparator, list));
        page.setCurrentPage(pageNo == null ? 1 : pageNo);
        page.setPageSize(pageSize == null ? page.getPageSize() : pageSize);
        page.setTotalRecord(list.size());
        return page;
    }

}
