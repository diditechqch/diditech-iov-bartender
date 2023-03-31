package com.diditech.iov.gps.app.device.repository;

import com.diditech.iov.gps.app.device.po.BizDeviceCategory;
import com.diditech.iov.gps.app.device.po.BizDeviceCategoryExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BizDeviceCategoryMapper {
    long countByExample(BizDeviceCategoryExample example);

    int deleteByExample(BizDeviceCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizDeviceCategory record);

    int insertSelective(BizDeviceCategory record);

    List<BizDeviceCategory> selectByExampleWithRowbounds(BizDeviceCategoryExample example, RowBounds rowBounds);

    List<BizDeviceCategory> selectByExample(BizDeviceCategoryExample example);

    BizDeviceCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizDeviceCategory record, @Param("example") BizDeviceCategoryExample example);

    int updateByExample(@Param("record") BizDeviceCategory record, @Param("example") BizDeviceCategoryExample example);

    int updateByPrimaryKeySelective(BizDeviceCategory record);

    int updateByPrimaryKey(BizDeviceCategory record);
}