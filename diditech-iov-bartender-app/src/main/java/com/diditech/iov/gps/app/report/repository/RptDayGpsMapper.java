package com.diditech.iov.gps.app.report.repository;

import com.diditech.iov.gps.app.report.po.RptDayGps;
import com.diditech.iov.gps.app.report.po.RptDayGpsExample;
import com.diditech.iov.gps.app.report.po.RptDayGpsKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface RptDayGpsMapper {
    long countByExample(RptDayGpsExample example);

    int deleteByExample(RptDayGpsExample example);

    int deleteByPrimaryKey(RptDayGpsKey key);

    int insert(RptDayGps record);

    int insertSelective(RptDayGps record);

    List<RptDayGps> selectByExampleWithRowbounds(RptDayGpsExample example, RowBounds rowBounds);

    List<RptDayGps> selectByExample(RptDayGpsExample example);

    RptDayGps selectByPrimaryKey(RptDayGpsKey key);

    int updateByExampleSelective(@Param("record") RptDayGps record, @Param("example") RptDayGpsExample example);

    int updateByExample(@Param("record") RptDayGps record, @Param("example") RptDayGpsExample example);

    int updateByPrimaryKeySelective(RptDayGps record);

    int updateByPrimaryKey(RptDayGps record);
}