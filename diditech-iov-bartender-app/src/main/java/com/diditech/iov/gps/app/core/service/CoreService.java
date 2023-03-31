package com.diditech.iov.gps.app.core.service;

import com.diditech.iov.gps.api.core.Page;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 通用服务接口类
 * @author zhjd
 * @date 2019/3/12
 */
public interface CoreService {
    /**
     * 校验平台标志
     * @param platformFlag 平台来源，1：平台1；2：平台2。可为null，默认平台2
     * @return void
     * @author zhjd
     * @date 2019/3/12
     */
    void platformValidation(int platformFlag);

    /**
     * 校验日期
     * @param gpsTime 日期
     * @param format  格式
     * @return 若不通过校验，抛异常
     * @author zhjd
     * @date 2019/2/20
     */
    Date getValidDate(String gpsTime, String format);

    /**
     * 校验查询日期
     * @param startTime 日期范围-开始时间
     * @param endTime   日期范围-结束时间
     * @param format    格式
     * @param maxMillis 最大毫秒
     * @return 若不通过校验，抛异常
     * @author zhjd
     * @date 2019/2/20
     */
    void dateValidation(String startTime, String endTime, String format, Long maxMillis);

    void dateValidation(Date startDate, Date endDate);

    int getDefaultLbsFlag(String categoryNo, String clientId);

    int getDefaultTimeoutThreshold(String categoryNo, String clientId);

    <T> Page<T> getPaged(List<T> list,
                         Comparator<T> comparator,
                         Integer pageSize,
                         Integer pageNo);
}
