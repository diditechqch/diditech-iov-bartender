package com.diditech.iov.gps.app.rules.repository;

import com.diditech.iov.gps.app.rules.po.ClientEventType;
import com.diditech.iov.gps.app.rules.po.ClientEventTypeExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ClientEventTypeMapper {
    long countByExample(ClientEventTypeExample example);

    int deleteByExample(ClientEventTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClientEventType record);

    int insertSelective(ClientEventType record);

    List<ClientEventType> selectByExampleWithRowbounds(ClientEventTypeExample example, RowBounds rowBounds);

    List<ClientEventType> selectByExample(ClientEventTypeExample example);

    ClientEventType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClientEventType record, @Param("example") ClientEventTypeExample example);

    int updateByExample(@Param("record") ClientEventType record, @Param("example") ClientEventTypeExample example);

    int updateByPrimaryKeySelective(ClientEventType record);

    int updateByPrimaryKey(ClientEventType record);
}