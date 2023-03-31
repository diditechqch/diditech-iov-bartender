package com.diditech.iov.gps.app.rules.repository;

import com.diditech.iov.gps.app.rules.po.EventArea;
import com.diditech.iov.gps.app.rules.po.EventAreaExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface EventAreaMapper {
    long countByExample(EventAreaExample example);

    int deleteByExample(EventAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EventArea record);

    int insertSelective(EventArea record);

    List<EventArea> selectByExampleWithBLOBsWithRowbounds(EventAreaExample example, RowBounds rowBounds);

    List<EventArea> selectByExampleWithBLOBs(EventAreaExample example);

    List<EventArea> selectByExampleWithRowbounds(EventAreaExample example, RowBounds rowBounds);

    List<EventArea> selectByExample(EventAreaExample example);

    EventArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EventArea record, @Param("example") EventAreaExample example);

    int updateByExampleWithBLOBs(@Param("record") EventArea record, @Param("example") EventAreaExample example);

    int updateByExample(@Param("record") EventArea record, @Param("example") EventAreaExample example);

    int updateByPrimaryKeySelective(EventArea record);

    int updateByPrimaryKeyWithBLOBs(EventArea record);

    int updateByPrimaryKey(EventArea record);
}