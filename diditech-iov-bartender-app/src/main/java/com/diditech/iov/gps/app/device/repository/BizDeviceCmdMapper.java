package com.diditech.iov.gps.app.device.repository;

import com.diditech.iov.gps.app.device.po.BizDeviceCmd;
import com.diditech.iov.gps.app.device.po.BizDeviceCmdExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BizDeviceCmdMapper {
    long countByExample(BizDeviceCmdExample example);

    int deleteByExample(BizDeviceCmdExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizDeviceCmd record);

    int insertSelective(BizDeviceCmd record);

    List<BizDeviceCmd> selectByExampleWithRowbounds(BizDeviceCmdExample example, RowBounds rowBounds);

    List<BizDeviceCmd> selectByExample(BizDeviceCmdExample example);

    BizDeviceCmd selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizDeviceCmd record, @Param("example") BizDeviceCmdExample example);

    int updateByExample(@Param("record") BizDeviceCmd record, @Param("example") BizDeviceCmdExample example);

    int updateByPrimaryKeySelective(BizDeviceCmd record);

    int updateByPrimaryKey(BizDeviceCmd record);
}