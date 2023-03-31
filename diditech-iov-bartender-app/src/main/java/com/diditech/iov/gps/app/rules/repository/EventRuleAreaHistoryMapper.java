package com.diditech.iov.gps.app.rules.repository;

import com.diditech.iov.gps.app.rules.po.EventRuleAreaHistory;
import com.diditech.iov.gps.app.rules.po.EventRuleAreaHistoryExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface EventRuleAreaHistoryMapper {
    long countByExample(EventRuleAreaHistoryExample example);

    int deleteByExample(EventRuleAreaHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EventRuleAreaHistory record);

    int insertSelective(EventRuleAreaHistory record);

    List<EventRuleAreaHistory> selectByExampleWithBLOBsWithRowbounds(EventRuleAreaHistoryExample example, RowBounds rowBounds);

    List<EventRuleAreaHistory> selectByExampleWithBLOBs(EventRuleAreaHistoryExample example);

    List<EventRuleAreaHistory> selectByExampleWithRowbounds(EventRuleAreaHistoryExample example, RowBounds rowBounds);

    List<EventRuleAreaHistory> selectByExample(EventRuleAreaHistoryExample example);

    EventRuleAreaHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EventRuleAreaHistory record, @Param("example") EventRuleAreaHistoryExample example);

    int updateByExampleWithBLOBs(@Param("record") EventRuleAreaHistory record, @Param("example") EventRuleAreaHistoryExample example);

    int updateByExample(@Param("record") EventRuleAreaHistory record, @Param("example") EventRuleAreaHistoryExample example);

    int updateByPrimaryKeySelective(EventRuleAreaHistory record);

    int updateByPrimaryKeyWithBLOBs(EventRuleAreaHistory record);

    int updateByPrimaryKey(EventRuleAreaHistory record);
}