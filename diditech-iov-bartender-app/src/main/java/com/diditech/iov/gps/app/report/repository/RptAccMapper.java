package com.diditech.iov.gps.app.report.repository;

import com.diditech.iov.gps.app.report.po.RptAcc;
import com.diditech.iov.gps.app.report.po.RptAccExample;
import com.diditech.iov.gps.app.report.po.RptAccKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface RptAccMapper {
    long countByExample(RptAccExample example);

    int deleteByExample(RptAccExample example);

    int deleteByPrimaryKey(RptAccKey key);

    int insert(RptAcc record);

    int insertSelective(RptAcc record);

    List<RptAcc> selectByExampleWithRowbounds(RptAccExample example, RowBounds rowBounds);

    List<RptAcc> selectByExample(RptAccExample example);

    RptAcc selectByPrimaryKey(RptAccKey key);

    int updateByExampleSelective(@Param("record") RptAcc record, @Param("example") RptAccExample example);

    int updateByExample(@Param("record") RptAcc record, @Param("example") RptAccExample example);

    int updateByPrimaryKeySelective(RptAcc record);

    int updateByPrimaryKey(RptAcc record);
}