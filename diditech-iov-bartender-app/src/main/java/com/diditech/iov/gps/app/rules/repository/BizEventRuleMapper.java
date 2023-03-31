package com.diditech.iov.gps.app.rules.repository;

import org.apache.ibatis.annotations.Param;

/**
 * @author zhjd <br>
 * @date 2020/8/18 <br>
 */
public interface BizEventRuleMapper {

    int countByRuleId(@Param("clientId") String clientId, @Param("ruleId") String id);

}
