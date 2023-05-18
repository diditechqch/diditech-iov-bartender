package com.diditech.iov.gps.api.rules;

import com.diditech.common.domain.EventRuleThreshold;
import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.rules.domain.EventRuleDTO;
import com.diditech.iov.gps.api.rules.domain.EventType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    ResponseMessage saveRule(@RequestParam(value = "devices") String[] devices,
                             @Valid @RequestBody EventRuleDTO rules);

    /**
     * 批量新建规则 适配班车线路保存，每个站点维护进出记录
     * @param devices  设备集合
     * @param rules    事件规则集合 {@link EventRuleDTO}
     * @param clearOld 是否清除旧数据(根据第一个ruleDto中的threshold1作为线路ID清除)
     * @author hefan
     * @date 2022/7/21 0021 16:09
     */
    @PostMapping("batch")
    ResponseMessage batchSaveRule(@RequestParam(value = "devices") String[] devices,
                                  @Valid @RequestBody EventRuleDTO[] rules,
                                  @RequestParam(value = "clearOld", required = false, defaultValue = "0") boolean clearOld);

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
     * @param devices    设备集合
     * @param threshold1 使用threshold1作为条件过滤数据，班车中该条件为线路ID
     * @param areaId     使用areaId作为条件过滤数据，班车中该条件为线路站点ID
     * @date 2020/7/7
     * @author zhjd
     */
    @DeleteMapping(path = "/devices")
    ResponseMessage deleteRuleByDevices(@RequestParam(value = "devices") String[] devices,
                                        @RequestParam(value = "threshold1", required = false) String threshold1,
                                        @RequestParam(value = "areaId", required = false) String areaId);

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
