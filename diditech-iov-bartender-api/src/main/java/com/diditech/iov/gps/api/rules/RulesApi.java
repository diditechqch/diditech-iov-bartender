package com.diditech.iov.gps.api.rules;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.diditech.common.domain.EventRuleThreshold;
import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.rules.domain.EventRuleDTO;
import com.diditech.iov.gps.api.rules.domain.EventType;

@RequestMapping("/rules")
public interface RulesApi {

    /**
     * 新建规则
     * @param devices 设备集合
     * @param rules   事件规则 {@link EventRuleDTO}
     * @date 2020/7/7
     * @author zhjd
     */
    @PostMapping
    ResponseMessage saveRule(@RequestParam(value = "devices") String[] devices, @Valid @RequestBody EventRuleDTO rules);

    /**
     * 新建终端级规则，配置后影响全终端应用范围设备
     * @param ruleTypes 事件规则类型类型 {@link EventType}
     * @date 2020/8/17
     * @author zhjd
     */
    @PostMapping("/client")
    ResponseMessage enableRulesForAllInClient(@RequestParam(value = "ruleTypes") String[] ruleTypes);

    /**
     * 更新规则
     * @param devices 设备集合
     * @param rules   事件规则 {@link EventRuleDTO}
     * @date 2020/7/7
     * @author zhjd
     */
    @PutMapping
    ResponseMessage updateRule(@RequestParam(value = "devices") String[] devices, @RequestBody EventRuleDTO rules);

    /**
     * 删除设备关联所有规则
     * @param devices 设备集合
     * @date 2020/7/7
     * @author zhjd
     */
    @DeleteMapping(path = "/devices")
    ResponseMessage deleteRuleByDevices(@RequestParam(value = "devices") String[] devices);

    /**
     * 批量删除规则
     * @param ruleIds 规则ID
     * @date 2020/7/9
     * @author zhjd
     */
    @DeleteMapping(path = "/ids")
    ResponseMessage deleteRuleByIds(@RequestParam(value = "ruleIds") String[] ruleIds);

    /**
     * 新建终端级规则，配置后影响全终端应用范围设备
     * @param ruleTypes 事件规则类型类型 {@link EventType}
     * @date 2020/8/17
     * @author zhjd
     */
    @DeleteMapping("/client")
    ResponseMessage disableRulesForAllInClient(@RequestParam(value = "ruleTypes") String[] ruleTypes);

    /**
     * 获取历史区域点集
     * @param ruleAreaHistoryId 规则区域关联履历ID
     */
    @GetMapping(path = "/areas")
    ResponseMessage getAreaPoints(@RequestParam(value = "ruleAreaHistoryId") Integer ruleAreaHistoryId);

    /**
     * 获取触发阈值
     * @author hefan
     * @date 2021/11/22 21:21
     */
    @GetMapping("/threshold")
    ResponseMessage getThreshold(@RequestParam(value = "device") String device,
                                 @RequestParam(value = "ruleType") Integer ruleType);

    /**
     * 设置触发阈值
     * @author hefan
     * @date 2021/11/22 21:21
     */
    @PostMapping("/threshold")
    ResponseMessage setThreshold(@RequestParam(value = "device") String device,
                                 @RequestParam(value = "ruleType") Integer ruleType,
                                 @RequestBody EventRuleThreshold threshold);

    /**
     * 清空触发阈值
     * @author hefan
     * @date 2021/11/22 21:21
     */
    @DeleteMapping(path = "/threshold")
    ResponseMessage deleteThreshold(@RequestParam(value = "device") String device,
                                    @RequestParam(value = "ruleType") Integer ruleType);

}
