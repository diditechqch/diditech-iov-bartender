package com.diditech.iov.gps.app.rules.service;


import java.util.List;
import java.util.Set;

import com.diditech.iov.gps.api.rules.domain.EventRuleDTO;
import com.diditech.common.domain.EventRuleThreshold;
import com.diditech.iov.gps.app.rules.po.EventRule;
import com.diditech.iov.gps.app.rules.po.EventRuleDevice;

/**
 * 规则服务
 *
 * @author zhjd
 * @date 2020/7/1
 */
public interface RulesService {

    /**
     * 存储事件规则:
     * 1. 存储规则
     * 2. 存储设备规则关系
     * （若有区域逻辑：
     * 3. 存储区域；
     * 4. 存储区域履历；
     * 5. 存储规则区域关系；
     * 6. 存储规则区域关系履历）
     *
     * @param devices 设备号数组
     * @param rules   {@link EventRuleDTO}
     * @date 2020/7/1
     * @author zhjd
     */
    void saveRule(String[] devices, EventRuleDTO rules);

    void updateRule(String[] devices, EventRuleDTO rules);

    EventRule getRuleById(Integer ruleId);

    List<EventRuleDevice> getRuleDevice(String[] devices);

    int deleteRule(String[] devices, List<Integer> ruleIdList);

    String getAreaPoints(Integer ruleAreaHistoryId, String clientId);

    /**
     * 根据规则id批量逻辑删除
     *
     * @param ruleIds 规则id
     * @return int 返回error code，0为正常，非0为异常
     * @date 2020/7/9
     * @author zhjd
     */
    int deleteRule(List<Integer> ruleIds);

    /**
     * 存储终端级规则
     *
     * @date 2020/8/17
     * @author zhjd
     */
    void enableRulesForAllInClient(Set<Integer> ruleTypes, String clientId);

    /**
     * 逻辑删除终端级规则
     *
     * @date 2020/8/17
     * @author zhjd
     */
    void disableRulesForAllInClient(Set<Integer> ruleTypes, String clientId);

    /**
     * 校验规则ID，返回第一个规则ID，校验通过时返回null
     *
     * @date 2020/8/18
     * @author zhjd
     */
    String validateRules(String clientId, String... ruleIds);

    /**
     * 校验客户端是否允许配置该规则类型
     *
     * @date 2020/8/21
     * @author zhjd
     */
    Set<Integer> validateRuleTypesForClient(String clientId, String... ruleTypes);

    /**
     * 获取触发阈值
     *
     * @return
     */
    EventRuleThreshold getThreshold(String device, Integer ruleType);

    /**
     * 设置触发阈值
     *
     * @return
     */
    void setThreshold(String device, Integer ruleType,
                         EventRuleThreshold threshold);

    /**
     * 清空触发阈值
     *
     * @return
     */
    void deleteThreshold(String devices, Integer ruleType);

}

