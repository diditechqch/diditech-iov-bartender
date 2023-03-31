package com.diditech.iov.gps.app.rules.repository;

import com.diditech.iov.gps.app.rules.po.EventRule;
import com.diditech.iov.gps.app.rules.po.EventRuleExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface EventRuleMapper {
    long countByExample(EventRuleExample example);

    int deleteByExample(EventRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EventRule record);

    int insertSelective(EventRule record);

    List<EventRule> selectByExampleWithRowbounds(EventRuleExample example, RowBounds rowBounds);

    List<EventRule> selectByExample(EventRuleExample example);

    EventRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EventRule record, @Param("example") EventRuleExample example);

    int updateByExample(@Param("record") EventRule record, @Param("example") EventRuleExample example);

    int updateByPrimaryKeySelective(EventRule record);

    int updateByPrimaryKey(EventRule record);
}