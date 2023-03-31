package com.diditech.iov.gps.app.report.repository;

import com.diditech.iov.gps.app.report.po.RptTrips;
import com.diditech.iov.gps.app.report.po.RptTripsExample;
import com.diditech.iov.gps.app.report.po.RptTripsKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface RptTripsMapper {
    long countByExample(RptTripsExample example);

    int deleteByExample(RptTripsExample example);

    int deleteByPrimaryKey(RptTripsKey key);

    int insert(RptTrips record);

    int insertSelective(RptTrips record);

    List<RptTrips> selectByExampleWithRowbounds(RptTripsExample example, RowBounds rowBounds);

    List<RptTrips> selectByExample(RptTripsExample example);

    RptTrips selectByPrimaryKey(RptTripsKey key);

    int updateByExampleSelective(@Param("record") RptTrips record, @Param("example") RptTripsExample example);

    int updateByExample(@Param("record") RptTrips record, @Param("example") RptTripsExample example);

    int updateByPrimaryKeySelective(RptTrips record);

    int updateByPrimaryKey(RptTrips record);
}