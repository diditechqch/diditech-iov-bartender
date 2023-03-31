package com.diditech.iov.gps.app.report.repository;

import com.diditech.iov.gps.app.report.po.RptStops;
import com.diditech.iov.gps.app.report.po.RptStopsExample;
import com.diditech.iov.gps.app.report.po.RptTripsKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface RptStopsMapper {
    long countByExample(RptStopsExample example);

    int deleteByExample(RptStopsExample example);

    int deleteByPrimaryKey(RptTripsKey key);

    int insert(RptStops record);

    int insertSelective(RptStops record);

    List<RptStops> selectByExampleWithRowbounds(RptStopsExample example, RowBounds rowBounds);

    List<RptStops> selectByExample(RptStopsExample example);

    RptStops selectByPrimaryKey(RptTripsKey key);

    int updateByExampleSelective(@Param("record") RptStops record, @Param("example") RptStopsExample example);

    int updateByExample(@Param("record") RptStops record, @Param("example") RptStopsExample example);

    int updateByPrimaryKeySelective(RptStops record);

    int updateByPrimaryKey(RptStops record);
}