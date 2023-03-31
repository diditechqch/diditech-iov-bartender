package com.diditech.iov.gps.app.rules.repository;

import com.diditech.iov.gps.app.rules.po.EventAreaHistory;
import com.diditech.iov.gps.app.rules.po.EventAreaHistoryExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface EventAreaHistoryMapper {
    long countByExample(EventAreaHistoryExample example);

    int deleteByExample(EventAreaHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EventAreaHistory record);

    int insertSelective(EventAreaHistory record);

    List<EventAreaHistory> selectByExampleWithBLOBsWithRowbounds(EventAreaHistoryExample example, RowBounds rowBounds);

    List<EventAreaHistory> selectByExampleWithBLOBs(EventAreaHistoryExample example);

    List<EventAreaHistory> selectByExampleWithRowbounds(EventAreaHistoryExample example, RowBounds rowBounds);

    List<EventAreaHistory> selectByExample(EventAreaHistoryExample example);

    EventAreaHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EventAreaHistory record, @Param("example") EventAreaHistoryExample example);

    int updateByExampleWithBLOBs(@Param("record") EventAreaHistory record, @Param("example") EventAreaHistoryExample example);

    int updateByExample(@Param("record") EventAreaHistory record, @Param("example") EventAreaHistoryExample example);

    int updateByPrimaryKeySelective(EventAreaHistory record);

    int updateByPrimaryKeyWithBLOBs(EventAreaHistory record);

    int updateByPrimaryKey(EventAreaHistory record);
}