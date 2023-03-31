package com.diditech.iov.gps.demo.repository;

import com.diditech.iov.gps.common.domain.AccSiteInfo;
import com.diditech.iov.gps.common.domain.StopData;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhjd <br>
 * @date 2021/8/4 <br>
 */
public interface AccMapper {
    int insertStopData(@Param("item") StopData stopData);

    void deleteStopData(@Param("deviceNum")String deviceNum, @Param("time") String time);

    int insertSiteInfo(@Param("item") AccSiteInfo siteInfo);
}
