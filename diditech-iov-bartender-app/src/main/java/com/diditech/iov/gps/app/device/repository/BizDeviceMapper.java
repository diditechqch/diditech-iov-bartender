package com.diditech.iov.gps.app.device.repository;

import com.diditech.iov.gps.app.device.po.BizDevice;
import com.diditech.iov.gps.app.device.po.BizDeviceExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BizDeviceMapper {
    long countByExample(BizDeviceExample example);

    int deleteByExample(BizDeviceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizDevice record);

    int insertSelective(BizDevice record);

    List<BizDevice> selectByExampleWithRowbounds(BizDeviceExample example, RowBounds rowBounds);

    List<BizDevice> selectByExample(BizDeviceExample example);

    BizDevice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizDevice record, @Param("example") BizDeviceExample example);

    int updateByExample(@Param("record") BizDevice record, @Param("example") BizDeviceExample example);

    int updateByPrimaryKeySelective(BizDevice record);

    int updateByPrimaryKey(BizDevice record);
}