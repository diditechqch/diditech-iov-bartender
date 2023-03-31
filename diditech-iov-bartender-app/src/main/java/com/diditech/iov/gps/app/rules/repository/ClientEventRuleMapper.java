package com.diditech.iov.gps.app.rules.repository;

import com.diditech.iov.gps.app.rules.po.ClientEventRule;
import com.diditech.iov.gps.app.rules.po.ClientEventRuleExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ClientEventRuleMapper {
    long countByExample(ClientEventRuleExample example);

    int deleteByExample(ClientEventRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClientEventRule record);

    int insertSelective(ClientEventRule record);

    List<ClientEventRule> selectByExampleWithRowbounds(ClientEventRuleExample example, RowBounds rowBounds);

    List<ClientEventRule> selectByExample(ClientEventRuleExample example);

    ClientEventRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClientEventRule record, @Param("example") ClientEventRuleExample example);

    int updateByExample(@Param("record") ClientEventRule record, @Param("example") ClientEventRuleExample example);

    int updateByPrimaryKeySelective(ClientEventRule record);

    int updateByPrimaryKey(ClientEventRule record);
}