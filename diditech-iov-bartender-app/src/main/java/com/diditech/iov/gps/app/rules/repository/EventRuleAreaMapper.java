package com.diditech.iov.gps.app.rules.repository;

import com.diditech.iov.gps.app.rules.po.EventRuleArea;
import com.diditech.iov.gps.app.rules.po.EventRuleAreaExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface EventRuleAreaMapper {
    long countByExample(EventRuleAreaExample example);

    int deleteByExample(EventRuleAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EventRuleArea record);

    int insertSelective(EventRuleArea record);

    List<EventRuleArea> selectByExampleWithBLOBsWithRowbounds(EventRuleAreaExample example, RowBounds rowBounds);

    List<EventRuleArea> selectByExampleWithBLOBs(EventRuleAreaExample example);

    List<EventRuleArea> selectByExampleWithRowbounds(EventRuleAreaExample example, RowBounds rowBounds);

    List<EventRuleArea> selectByExample(EventRuleAreaExample example);

    EventRuleArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EventRuleArea record, @Param("example") EventRuleAreaExample example);

    int updateByExampleWithBLOBs(@Param("record") EventRuleArea record, @Param("example") EventRuleAreaExample example);

    int updateByExample(@Param("record") EventRuleArea record, @Param("example") EventRuleAreaExample example);

    int updateByPrimaryKeySelective(EventRuleArea record);

    int updateByPrimaryKeyWithBLOBs(EventRuleArea record);

    int updateByPrimaryKey(EventRuleArea record);
}