package com.diditech.iov.gps.app.rules.provider;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.diditech.common.domain.EventRuleThreshold;
import com.diditech.iov.gps.api.core.ResponseMessage;
import com.diditech.iov.gps.api.rules.RulesApi;
import com.diditech.iov.gps.api.rules.domain.EventRuleDTO;
import com.diditech.iov.gps.app.core.aspect.RequestHelper;
import com.diditech.iov.gps.app.device.service.DeviceService;
import com.diditech.iov.gps.app.rules.po.EventRule;
import com.diditech.iov.gps.app.rules.po.EventRuleDevice;
import com.diditech.iov.gps.app.rules.service.RulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 规则接口
 * @author zhjd
 * @date 2020/7/1
 */
@RestController
public class RulesProvider implements RulesApi {

    @Autowired
    private RulesService rulesService;

    @Autowired
    private DeviceService deviceService;

    @Override
    public ResponseMessage saveRule(@RequestParam(value = "devices") String[] devices,
                                    @Valid @RequestBody EventRuleDTO rules) {
        rules.setClientId(RequestHelper.getClientId());
        ResponseMessage r = ruleValidation(devices, rules);
        if (r != null) {
            return r;
        }
        // TODO 校验客户端是否允许配置该规则类型
        try {
            rulesService.saveRule(devices, rules);
        } catch (Exception exception) {
            return ResponseMessage.error(exception.getMessage());
        }
        return ResponseMessage.ok(rules);
    }

    @Override
    public ResponseMessage enableRulesForAllInClient(@RequestParam(value = "ruleTypes") String[] ruleTypes) {
        String clientId = RequestHelper.getClientId();
        Set<Integer> types = rulesService.validateRuleTypesForClient(clientId, ruleTypes);
        // TODO 校验是否已存在
        try {
            rulesService.enableRulesForAllInClient(types, clientId);
        } catch (Exception exception) {
            return ResponseMessage.error(exception.getMessage());
        }
        return ResponseMessage.ok(types);
    }

    @Override
    public ResponseMessage updateRule(@RequestParam(value = "devices") String[] devices,
                                      @RequestBody EventRuleDTO rules) {
        String clientId = RequestHelper.getClientId();
        String invalidRuleId = rulesService.validateRules(clientId, Convert.toStr(rules.getRuleId()));
        if (StrUtil.isNotBlank(invalidRuleId)) {
            return ResponseMessage.error("无权访问规则id或已删除，invalidRuleId:" + invalidRuleId);
        }

        rules.setClientId(clientId);
        ResponseMessage r = ruleValidation(devices, rules);
        if (r != null) {
            return r;
        }
        if (rules.getRuleId() == null || rules.getRuleId() == 0) {
            return ResponseMessage.error("更新规则ruleId不可为空");
        }
        EventRule ruleEntity = rulesService.getRuleById(rules.getRuleId());
        if (ruleEntity == null || !StrUtil.equals(ruleEntity.getClientId(), clientId)) {
            return ResponseMessage.error("查找不到ruleId=" + rules.getRuleId());
        }
        try {
            rulesService.updateRule(devices, rules);
        } catch (Exception exception) {
            return ResponseMessage.error(exception.getMessage());
        }
        return ResponseMessage.ok(rules);
    }

    @Override
    public ResponseMessage deleteRuleByDevices(@RequestParam(value = "devices") String[] devices) {
        List<EventRuleDevice> ruleDevices = rulesService.getRuleDevice(devices);
        if (CollUtil.isEmpty(ruleDevices)) {
            return ResponseMessage.ok("设备未关联规则");
        }
        List<Integer> ruleIdList = ruleDevices.stream().map(EventRuleDevice::getRuleId).distinct().collect(Collectors.toList());
        int result = rulesService.deleteRule(devices, ruleIdList);
        if (result == 0) {
            return ResponseMessage.ok();
        }
        return ResponseMessage.ok("删除规则设备关系不存在或已删除，devices=" + devices);
    }

    @Override
    public ResponseMessage deleteRuleByIds(@RequestParam(value = "ruleIds") String[] ruleIds) {
        String clientId = RequestHelper.getClientId();

        String invalidRuleId = rulesService.validateRules(clientId, ruleIds);
        if (StrUtil.isNotBlank(invalidRuleId)) {
            return ResponseMessage.error("无权访问规则id或已删除，invalidRuleId:" + invalidRuleId);
        }
        List<Integer> ruleIdList = Arrays.stream(ruleIds).map(Convert::toInt).collect(Collectors.toList());
        int result = rulesService.deleteRule(ruleIdList);
        if (result == 0) {
            return ResponseMessage.ok();
        }
        return ResponseMessage.ok("规则id不存在或已删除，ruleIds=" + ruleIds);
    }

    @Override
    public ResponseMessage disableRulesForAllInClient(@RequestBody String[] ruleTypes) {
        String clientId = RequestHelper.getClientId();
        Set<Integer> types = Arrays.stream(ruleTypes).map(Convert::toInt).collect(Collectors.toSet());
        try {
            rulesService.disableRulesForAllInClient(types, clientId);
        } catch (Exception exception) {
            return ResponseMessage.error(exception.getMessage());
        }
        return ResponseMessage.ok(types);
    }

    @Override
    public ResponseMessage getAreaPoints(@RequestParam(value = "ruleAreaHistoryId") Integer ruleAreaHistoryId) {
        String clientId = RequestHelper.getClientId();
        String points = rulesService.getAreaPoints(ruleAreaHistoryId, clientId);
        if (StrUtil.isNotBlank(points)) {
            return ResponseMessage.ok(points);
        }
        return ResponseMessage.error("无效ruleAreaHistoryId");
    }

    @Override
    public ResponseMessage getThreshold(String device, Integer ruleType) {
        try {
            EventRuleThreshold threshold = rulesService.getThreshold(device, ruleType);
            if (ObjectUtil.isNull(threshold)) {
                return ResponseMessage.error("无规则阈值");
            }
            return ResponseMessage.ok(threshold);
        } catch (Exception e) {
            return ResponseMessage.error("获取规则阈值失败");
        }
    }

    @Override
    public ResponseMessage setThreshold(String device, Integer ruleType,
                                        EventRuleThreshold threshold) {
        try {
            rulesService.setThreshold(device, ruleType, threshold);
            return ResponseMessage.ok();
        } catch (Exception e) {
            return ResponseMessage.error("设置规则阈值失败");
        }
    }

    @Override
    public ResponseMessage deleteThreshold(String devices, Integer ruleType) {
        try {
            rulesService.deleteThreshold(devices, ruleType);
            return ResponseMessage.ok();
        } catch (Exception e) {
            return ResponseMessage.error("删除规则阈值失败");
        }
    }

    private ResponseMessage ruleValidation(String[] devices, EventRuleDTO rules) {
        String invalidNum = deviceService.getInvalidDeviceNum(rules.getClientId(), devices);
        if (StrUtil.isNotBlank(invalidNum)) {
            return ResponseMessage.error("设备号不存在或无权限：" + invalidNum);
        }
        if (rules.getAreaEnable() == 0 &&
                (StrUtil.toString(rules.getRuleType()).startsWith("1") || StrUtil.toString(rules.getRuleType()).startsWith("2"))) {
            return ResponseMessage.error("进出区域规则areaEnable必填1");
        }
        if (rules.getAreaEnable() == 1 && rules.getAreaId() == null) {
            if (rules.getShapeType() == null || rules.getShapeType() < (byte) 1 || rules.getShapeType() > (byte) 3) {
                return ResponseMessage.error("若启用区域，需使用有效shapeType");
            }
            if (rules.getShapeType() == 1) {
                String invalidPoints = validateAreaPoints(rules.getAreaPoints());
                if (StrUtil.isNotBlank(invalidPoints)) {
                    return ResponseMessage.error("无效区域：" + invalidPoints);
                }
            } else {
                if (StrUtil.isBlank(rules.getCircles())) {
                    return ResponseMessage.error("无效圆形区域，未传信息");
                }
                String invalidPoints = validateCircleData(rules.getCircles());
                if (StrUtil.isNotBlank(invalidPoints)) {
                    return ResponseMessage.error("无效圆形区域：" + invalidPoints);
                }
            }
        }
        if (rules.getTimeEnable() == 1) {
            if (rules.getEnableTimeFrom() == null || rules.getEnableTimeTo() == null) {
                return ResponseMessage.error("若启用时间判断，需使用有效时间区间");
            }
            if (DateUtil.compare(rules.getEnableTimeFrom(), rules.getEnableTimeTo()) >= 0) {
                return ResponseMessage.error("若启用时间判断，开始时间须小于结束时间");
            }
        }
        return null;
    }


    /**
     * 校验区域圆形点集，通过校验时返回null，校验失败时返回第一个有问题的点集字符串
     * @date 2020/7/1
     * @author zhjd
     */
    private String validateCircleData(String circles) {
        String[] items = circles.split(StrUtil.COMMA);
        if (items.length < 3) {
            return circles;
        }
        for (String i : items) {
            if (Convert.toDouble(i) == null || Convert.toDouble(i) == 0D) {
                return circles;
            }
        }
        return null;
    }

    /**
     * 校验区域不规则图形点集，通过校验时返回null，校验失败时返回第一个有问题的点集字符串
     * @date 2020/7/1
     * @author zhjd
     */
    private String validateAreaPoints(String areaPoints) {
        try {
            // fastjson [[120.356116,36.206523],[120.46765,36.213512]]和[[120.356116,36.206523][120.46765,36.213512]]都可成功反序列化
            String[] split = areaPoints.split("\\]\\,\\[");
            if (split.length < 3) {
                return areaPoints;
            }
            List<double[]> points = JSON.parseArray(areaPoints, double[].class);
            if (CollUtil.isEmpty(points)) {
                return areaPoints;
            }
        } catch (Exception ex) {
            return areaPoints;
        }
        return null;
    }
}
