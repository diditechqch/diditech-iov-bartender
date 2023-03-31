package com.diditech.iov.gps.app.rules.repository;

import com.diditech.iov.gps.app.rules.po.EventRuleDevice;
import com.diditech.iov.gps.app.rules.po.EventRuleDeviceExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface EventRuleDeviceMapper {
    long countByExample(EventRuleDeviceExample example);

    int deleteByExample(EventRuleDeviceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EventRuleDevice record);

    int insertSelective(EventRuleDevice record);

    List<EventRuleDevice> selectByExampleWithRowbounds(EventRuleDeviceExample example, RowBounds rowBounds);

    List<EventRuleDevice> selectByExample(EventRuleDeviceExample example);

    EventRuleDevice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EventRuleDevice record, @Param("example") EventRuleDeviceExample example);

    int updateByExample(@Param("record") EventRuleDevice record, @Param("example") EventRuleDeviceExample example);

    int updateByPrimaryKeySelective(EventRuleDevice record);

    int updateByPrimaryKey(EventRuleDevice record);
}