package com.diditech.iov.gps.app.rules.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.diditech.common.domain.EventRuleThreshold;
import com.diditech.iov.gps.api.core.BusinessException;
import com.diditech.iov.gps.api.rules.domain.EventRuleDTO;
import com.diditech.iov.gps.api.rules.domain.SimpleEventType;
import com.diditech.iov.gps.app.core.util.GisUtils;
import com.diditech.iov.gps.app.rules.po.*;
import com.diditech.iov.gps.app.rules.repository.*;
import com.diditech.iov.gps.app.rules.service.RulesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RulesServiceImpl implements RulesService {

    /**
     * 进区域规则类型
     */
    private static final int inRuleType = 11;

    /**
     * 出区域规则类型
     */
    private static final int outRuleType = 21;

    /**
     * 班车站点规则类型，进出绑定同时创建
     */
    private static final List<Integer> bcRuleTypeList = new ArrayList<Integer>(2) {{
        add(inRuleType);
        add(outRuleType);
    }};

    @Value("${gps.redis.prefix.rule}")
    private String redis_key_pre_rule;

    @Value("${gps.redis.prefix.rule-device}")
    private String redis_key_pre_rule_device;

    @Value("${gps.redis.prefix.rule-client}")
    private String redis_key_pre_rule_client;

    @Value("${gps.redis.prefix.event-rule}")
    private String redis_key_pre_event_rule;

    @Value("${gps.redis.prefix.event-rule-threshold}")
    private String redis_key_pre_event_rule_threshold;

    @Autowired
    private EventRuleMapper ruleMapper;

    @Autowired
    private BizEventRuleMapper bizRuleMapper;

    @Autowired
    private EventRuleDeviceMapper ruleDeviceMapper;

    @Autowired
    private EventAreaMapper areaMapper;

    @Autowired
    private EventAreaHistoryMapper areaHistoryMapper;

    @Autowired
    private EventRuleAreaMapper ruleAreaMapper;

    @Autowired
    private EventRuleAreaHistoryMapper ruleAreaHistoryMapper;

    @Autowired
    private ClientEventRuleMapper clientRuleMapper;

    @Autowired
    private RedisTemplate coreRedisTemplate;

    @Autowired
    @Qualifier("clientEventTypes")
    private Map<String, Set<Integer>> clientEventTypes;

    @Override
    @Transactional(transactionManager = "defaultSqlTransactionManager")
    public void saveRule(String[] devices, EventRuleDTO rules) {
        // 1. 存储规则
        EventRule ruleRecord = insertRule(rules);

        // 2. 存储规则设备关系表
        saveRuleDeviceData(devices, ruleRecord);

        boolean hasArea = Convert.toInt(rules.getAreaEnable(), 0) == 1;
        EventArea area = null;
        if (hasArea) {
            // 3. 存储区域、区域履历，存储规则区域关系、关系履历
            area = saveRuleArea(rules);
        }

        // 4. 缓存规则、区域数据
        saveCacheRules(rules, area, new HashSet<>(Arrays.asList(devices)));
    }

    @Override
    @Transactional(transactionManager = "defaultSqlTransactionManager")
    public void batchSaveRule(String[] devices, List<EventRuleDTO> rules, boolean clearOld) {
        String routeId = rules.get(0).getThreshold1();
        if (clearOld && StrUtil.isNotBlank(routeId)) {
            List<EventRuleDevice> ruleDevices = getRuleDevice(devices);
            if (!CollUtil.isEmpty(ruleDevices)) {
                List<Integer> ruleIdList = ruleDevices.stream()
                        .map(EventRuleDevice::getRuleId).distinct().collect(Collectors.toList());
                int result = deleteRule(devices, ruleIdList);
                if (result == 0) {
                    log.info("线路{}原站点数据清除成功", routeId);
                }
            }
        }
        for (EventRuleDTO rule : rules) {
            for (Integer ruleType : bcRuleTypeList) {
                rule.setRuleType(ruleType);
                saveRule(devices, rule);
            }
        }
    }

    @Override
    @Transactional
    public void updateRule(String[] devices, EventRuleDTO rules) {
        EventRule ruleRecord = new EventRule();
        BeanUtil.copyProperties(rules, ruleRecord, CopyOptions.create().ignoreError());
        ruleRecord.setId(rules.getRuleId());
        ruleRecord.setUpdateTime(new Date());

        // 1. 更新规则
        int result = ruleMapper.updateByPrimaryKeySelective(ruleRecord);
        if (result != 1) {
            throw new BusinessException("更新规则失败" + devices + ", " + rules + ", result=" + result);
        }
        BeanUtil.copyProperties(ruleRecord, rules, CopyOptions.create().ignoreError().ignoreNullValue());

        // 2. 删除规则原设备关系数据
        EventRuleDeviceExample ruleDeviceExample = new EventRuleDeviceExample();
        ruleDeviceExample.createCriteria().andRuleIdEqualTo(ruleRecord.getId());
        List<EventRuleDevice> deviceList = ruleDeviceMapper.selectByExample(ruleDeviceExample);
        if (CollUtil.isEmpty(deviceList)) {
            throw new BusinessException("规则未关联设备，更新规则失败" + devices + ", " + rules + ", result=" + result);
        }

        List<String> originDevices = deviceList.stream().map(EventRuleDevice::getDeviceNum).collect(Collectors.toList());
        // 3. 存储规则设备关系表
        int deleteResult = ruleDeviceMapper.deleteByExample(ruleDeviceExample);
        if (deleteResult == 0) {
            log.error("更新规则：删除规则关联设备失败，原规则为与设备关联，ruleId={}", ruleRecord.getId());
        }
        saveRuleDeviceData(devices, ruleRecord);

        // 开始判断区域
        boolean hasArea = Convert.toInt(rules.getAreaEnable(), 0) == 1;
        EventArea area = null;
        if (hasArea) {
            EventRuleAreaExample ruleAreaExample = new EventRuleAreaExample();
            ruleAreaExample.createCriteria().andRuleIdEqualTo(ruleRecord.getId());
            List<EventRuleArea> originAreaList = ruleAreaMapper.selectByExampleWithBLOBs(ruleAreaExample);
            // 4. 删除规则原区域及其关系
            if (CollUtil.isNotEmpty(originAreaList)) {
                int deleteRuleAreaCount = ruleAreaMapper.deleteByExample(ruleAreaExample);
                if (deleteRuleAreaCount != originAreaList.size()) {
                    throw new BusinessException("删除规则原区域关系失败: ruleId=" + rules + ", areaId=" + originAreaList.stream().map(item -> Convert.toStr(item.getAreaId())).collect(Collectors.joining(",")));
                }
            }
            // 5. 存储区域、区域履历，存储规则区域关系、关系履历
            area = saveRuleArea(rules);
        }
        // else 进出区域已校验区域必填

        // 6. 缓存规则、区域数据
        Set<String> updateDevices = new HashSet<>(Arrays.asList(devices));
        updateCacheRules(rules, area, updateDevices, new HashSet<>(originDevices));
    }

    @Override
    public EventRule getRuleById(Integer ruleId) {
        return ruleMapper.selectByPrimaryKey(ruleId);
    }

    @Override
    public List<EventRuleDevice> getRuleDevice(String[] devices) {
        EventRuleDeviceExample ruleDeviceExample = new EventRuleDeviceExample();
        ruleDeviceExample.createCriteria().andDeviceNumIn(Arrays.asList(devices));
        return ruleDeviceMapper.selectByExample(ruleDeviceExample);
    }

    @Override
    public List<EventRuleDevice> getRuleDevice(List<String> devices, String threshold1, String areaId) {
        return ruleDeviceMapper.selectByFilter(devices, threshold1, areaId);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public int deleteRule(String[] devices, List<Integer> ruleIdList) {
        int error = 0;

        // 删除规则设备关系
        EventRuleDeviceExample ruleDeviceExample = new EventRuleDeviceExample();
        ruleDeviceExample.createCriteria()
                .andDeviceNumIn(Arrays.asList(devices))
                .andRuleIdIn(ruleIdList);
        int deleteRuleDevice = ruleDeviceMapper.deleteByExample(ruleDeviceExample);
        if (deleteRuleDevice < 1) {
            log.error("删除规则设备关系失败：devices={}, ruleIdList:{}", devices, ruleIdList);
            error = 1;
        }
        final Object[] ruleIdArr = ruleIdList.toArray();
        Arrays.stream(devices).forEach(deviceNum -> {
            // 删除所有规则关联设备
            coreRedisTemplate.opsForSet().remove(redis_key_pre_rule_device.concat(deviceNum), ruleIdArr);
            // coreRedisTemplate.delete(redis_key_pre_rule_device.concat(deviceNum));
            // 删除设备历史事件状态缓存
            Set<String> keys = coreRedisTemplate.keys(redis_key_pre_event_rule.concat("*").concat(deviceNum));
            if (!CollUtil.isEmpty(keys)) {
                for (String key : keys) {
                    String[] keyParams = key.split("-");
                    if (keyParams.length == 4 && ruleIdList.contains(Integer.valueOf(keyParams[2]))) {
                        coreRedisTemplate.delete(keys);
                    }
                }
            }
        });

        // add by zhjd 20220125 start
        // 删除规则之前判断是否还有其他设备使用该规则
        List<Integer> rulesToBeDeleted = ruleIdList;
        ruleDeviceExample = new EventRuleDeviceExample();
        ruleDeviceExample.createCriteria()
                .andRuleIdIn(ruleIdList)
                .andDeviceNumNotIn(Arrays.asList(devices));
        List<EventRuleDevice> eventRuleDevices = ruleDeviceMapper.selectByExample(ruleDeviceExample);

        if (CollUtil.isNotEmpty(eventRuleDevices)) {
            List<Integer> rulesBeingUsing = eventRuleDevices.stream()
                    .map(EventRuleDevice::getRuleId)
                    .distinct()
                    .collect(Collectors.toList());
            rulesToBeDeleted = ruleIdList.stream()
                    .filter(item -> !rulesBeingUsing.contains(item))
                    .collect(Collectors.toList());
        }

        if (CollUtil.isEmpty(rulesToBeDeleted)) {
            return error;
        }
        // add by zhjd 20220125 end

        // 逻辑删除规则
        EventRule ruleEntity = new EventRule();
        ruleEntity.setIsdel((byte) 1);
        EventRuleExample ruleExample = new EventRuleExample();
        ruleExample.createCriteria().andIdIn(rulesToBeDeleted);
        int ruleResult = ruleMapper.updateByExampleSelective(ruleEntity, ruleExample);
        if (ruleResult != rulesToBeDeleted.size()) {
            log.error("逻辑删除规则失败: devices={}, rulesToBeDeleted={}", devices, rulesToBeDeleted);
            error = 2;
        }

        // 删除所有规则ID缓存
        rulesToBeDeleted.forEach(ruleId -> coreRedisTemplate.delete(redis_key_pre_rule + ruleId));

        // 获取规则区域关系
        EventRuleAreaExample ruleAreaExample = new EventRuleAreaExample();
        ruleAreaExample.createCriteria().andRuleIdIn(rulesToBeDeleted);
        List<EventRuleArea> ruleAreaList = ruleAreaMapper.selectByExample(ruleAreaExample);
        if (CollUtil.isEmpty(ruleAreaList)) {
            return error;
        }
        // 删除区域规则关系
        int deleteRuleArea = ruleAreaMapper.deleteByExample(ruleAreaExample);
        if (deleteRuleArea < 1) {
            log.error("删除区域规则关系失败：devices={}, rulesToBeDeleted={}", devices, rulesToBeDeleted);
            error = 3;
        }

        // 逻辑删除区域
        List<Integer> areaIdList = ruleAreaList.stream()
                .map(EventRuleArea::getAreaId)
                .distinct()
                .collect(Collectors.toList());
        // add by zhjd 20220125 start
        // 删除区域之前判断是否还有其他规则使用该区域
        List<Integer> areasToBeDeleted = areaIdList;

        ruleAreaExample = new EventRuleAreaExample();
        ruleAreaExample.createCriteria()
                .andAreaIdIn(areaIdList)
                .andRuleIdNotIn(rulesToBeDeleted);
        List<EventRuleArea> eventRuleAreas = ruleAreaMapper.selectByExample(ruleAreaExample);
        if (CollUtil.isNotEmpty(eventRuleAreas)) {
            List<Integer> areasBeingUsing = eventRuleAreas.stream()
                    .map(EventRuleArea::getAreaId)
                    .distinct()
                    .collect(Collectors.toList());
            areasToBeDeleted = areaIdList.stream()
                    .filter(item -> !areasBeingUsing.contains(item))
                    .collect(Collectors.toList());
        }
        if (CollUtil.isEmpty(areasToBeDeleted)) {
            return error;
        }
        // add by zhjd 20220125 end

        EventArea areaEntity = new EventArea();
        areaEntity.setIsdel((byte) 1);
        EventAreaExample areaExample = new EventAreaExample();
        areaExample.createCriteria().andIdIn(areasToBeDeleted);
        int areaResult = areaMapper.updateByExampleSelective(areaEntity, areaExample);
        if (areaResult != areasToBeDeleted.size()) {
            log.error("逻辑删除区域失败：devices={}, areasToBeDeleted={}", devices, areasToBeDeleted);
            error = 4;
        }

        return error;
    }

    @Override
    public String getAreaPoints(Integer ruleAreaHistoryId, String clientId) {
        EventRuleAreaHistoryExample example = new EventRuleAreaHistoryExample();
        example.createCriteria().andIdEqualTo(ruleAreaHistoryId).andClientIdEqualTo(clientId);
        List<EventRuleAreaHistory> list = ruleAreaHistoryMapper.selectByExampleWithBLOBs(example);
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        return list.get(0).getPoints();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public int deleteRule(List<Integer> ruleIdList) {
        int error = 0;
        EventRuleExample example = new EventRuleExample();
        example.createCriteria().andIdIn(ruleIdList).andIsdelEqualTo((byte) 0);
        long count = ruleMapper.countByExample(example);
        if (count != ruleIdList.size()) {
            error = 1;
            log.error("规则id不存在或已删除，ruleIds={}", ruleIdList);
        }
        // 1. 删除规则和设备的关联关系
        EventRuleDeviceExample ruleDeviceExample = new EventRuleDeviceExample();
        ruleDeviceExample.createCriteria().andRuleIdIn(ruleIdList);
        List<EventRuleDevice> ruleDevices = ruleDeviceMapper.selectByExample(ruleDeviceExample);
        int deleteRuleDevice = ruleDeviceMapper.deleteByExample(ruleDeviceExample);
        if (deleteRuleDevice != ruleDevices.size()) {
            log.error("删除规则设备关系失败：ruleIds={}, ruleDevices={}", ruleIdList, ruleDevices);
            error = 2;
        }

        // 2. 删除规则和区域的关联关系
        EventRuleAreaExample ruleAreaExample = new EventRuleAreaExample();
        ruleAreaExample.createCriteria().andRuleIdIn(ruleIdList);
        int deleteRuleArea = ruleAreaMapper.deleteByExample(ruleAreaExample);
        if (deleteRuleArea < 1) {
            log.error("删除ruleIds={}，无关联区域", ruleIdList);
        }

        // 3. 逻辑删除规则
        example = new EventRuleExample();
        example.createCriteria().andIdIn(ruleIdList);
        EventRule rule = new EventRule();
        rule.setIsdel((byte) 1);
        int updateResult = ruleMapper.updateByExampleSelective(rule, example);
        if (updateResult != ruleIdList.size()) {
            log.error("逻辑删除规则失败：ruleIds={}", ruleIdList);
            error = 3;
        }

        Set<String> deviceList = ruleDevices.stream().map(EventRuleDevice::getDeviceNum).collect(Collectors.toSet());

        ruleIdList.forEach(ruleId -> {
            // 删除所有规则ID缓存
            coreRedisTemplate.delete(redis_key_pre_rule + ruleId);
            // 删除原关联设备 originDevices
            removeRedisRuleDevice(Convert.toStr(ruleId), deviceList);
            // 删除设备历史事件状态缓存
            Set<String> keys = coreRedisTemplate.keys(redis_key_pre_event_rule.concat("*-").concat(Convert.toStr(ruleId)).concat("-*"));
            if (CollUtil.isEmpty(keys)) {
                coreRedisTemplate.delete(keys);
            }
        });
        return error;
    }

    @Override
    @Transactional
    public void enableRulesForAllInClient(Set<Integer> ruleTypes, String clientId) {
        // 1. 创建规则
        List<EventRuleDTO> rules = ruleTypes.stream()
                .map(item -> {
                    EventRuleDTO ruleDTO = new EventRuleDTO();
                    ruleDTO.setRuleType(item);
                    ruleDTO.setRuleName(clientId + "通用规则");
                    ruleDTO.setClientId(clientId);
                    return ruleDTO;
                })
                .peek(this::insertRule) // 存规则表
                .collect(Collectors.toList());
        // 2. 存储客户端级规则
        List<ClientEventRule> records = rules.stream()
                .map(item -> {
                    ClientEventRule obj = new ClientEventRule();
                    obj.setClientId(clientId);
                    obj.setRuleId(item.getRuleId());
                    obj.setRuleType(item.getRuleType());
                    obj.setCreateTime(item.getCreateTime());
                    return obj;
                })
                .filter(item -> clientRuleMapper.insertSelective(item) == 1)
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(records) || records.size() != ruleTypes.size()) {
            throw new BusinessException("存储终端级规则失败");
        }

        // 3. 缓存规则
        rules.forEach(item -> setRedisRule(item, null));

        // 3. 缓存终端规则关系
        coreRedisTemplate.executePipelined((RedisCallback<List<Object>>) connection -> {
            for (ClientEventRule item : records) {
                connection.sAdd(redis_key_pre_rule_client.concat(item.getClientId()).getBytes(), Convert.toStr(item.getRuleId()).getBytes());
            }
            return null;
        });
    }

    @Override
    @Transactional
    public void disableRulesForAllInClient(Set<Integer> ruleTypes, String clientId) {
        ClientEventRuleExample example = new ClientEventRuleExample();
        example.createCriteria().andClientIdEqualTo(clientId).andRuleTypeIn(new ArrayList<>(ruleTypes));
        List<ClientEventRule> list = clientRuleMapper.selectByExample(example);

        // 1. 删除规则和客户端关系
        list.forEach(item -> clientRuleMapper.deleteByPrimaryKey(item.getId()));

        // 2. 逻辑删除规则
        list.stream()
                .map(item -> {
                    EventRule updateInfo = new EventRule();
                    updateInfo.setIsdel((byte) 1);
                    updateInfo.setId(item.getRuleId());
                    return updateInfo;
                })
                .forEach(item -> ruleMapper.updateByPrimaryKeySelective(item));


        // 3. 删除客户端规则关系缓存
        coreRedisTemplate.executePipelined((RedisCallback<List<Object>>) connection -> {
            for (Integer item : ruleTypes) {
                connection.sRem(redis_key_pre_rule_client.concat(clientId).getBytes(), Convert.toStr(item).getBytes());
            }
            return null;
        });

        // 4. 删除所有规则ID缓存
        list.forEach(item -> coreRedisTemplate.delete(redis_key_pre_rule + item.getRuleId()));
    }

    @Override
    public String validateRules(String clientId, String... ruleIds) {
        for (String id : ruleIds) {
            int count = bizRuleMapper.countByRuleId(clientId, id);
            if (count != 1) {
                return id;
            }
        }
        return null;
    }

    @Override
    public Set<Integer> validateRuleTypesForClient(String clientId, String... ruleTypes) {
        Set<Integer> types = Arrays.stream(ruleTypes)
                .map(item -> Convert.toInt(item, 0))
                .filter(item -> SimpleEventType.has(getRootType(item)))
                .collect(Collectors.toSet());
        if (CollUtil.isEmpty(types) || ruleTypes.length != types.size()) {
            throw new BusinessException("仅支持简单事件规则");
        }
        boolean isValid = getAvailableRuleTypes(clientId).containsAll(types);
        if (!isValid) {
            throw new BusinessException("请确认客户端支持事件规则");
        }
        return types;
    }

    private Integer getRootType(Integer ruleType) {
        if (ruleType == null) {
            return null;
        }
        String str = Convert.toStr(ruleType);
        if (str.length() == 1) {
            return ruleType;
        }
        return Convert.toInt(str.substring(0, 1));
    }

    /**
     * 存储规则设备关系
     * @date 2020/7/2
     * @author zhjd
     */
    @Transactional
    public void saveRuleDeviceData(String[] devices, EventRule ruleRecord) {
        long ruleDeviceResult = Arrays.stream(devices)
                .map(item -> {
                    EventRuleDevice ruleDeviceRecord = new EventRuleDevice();
                    BeanUtil.copyProperties(ruleRecord, ruleDeviceRecord);
                    ruleDeviceRecord.setDeviceNum(item);
                    ruleDeviceRecord.setCreateTime(new Date());
                    ruleDeviceRecord.setRuleId(ruleRecord.getId());
                    // 删除规则设备表更新时间字段
                    return ruleDeviceRecord;
                })
                .map(item -> ruleDeviceMapper.insert(item))
                .count();
        if (ruleDeviceResult != devices.length) {
            throw new BusinessException("存储规则设备关系失败" + devices + ", " + ruleRecord + ", result=" + ruleDeviceResult);
        }
    }

    /**
     * 存储区域及其履历，存储规则区域关系及其履历
     * @date 2020/7/2
     * @author zhjd
     */
    @Transactional
    public EventArea saveRuleArea(EventRuleDTO rules) {
        EventArea area = generateRuleArea(rules);
        int resultError;
        if (rules.getAreaId() != null) {
            // 若EventRuleDTO.areaId有赋值，则视为对已存在区域创建规则
            EventArea originArea = areaMapper.selectByPrimaryKey(rules.getAreaId());
            if (originArea == null) {
                throw new BusinessException("对已存在区域创建规则失败：areaId=" + rules.getAreaId() + "不存在");
            }
            // 带区域ID时，忽略传输区域信息，不校验区域
            area = originArea;
        } else {
            // add by zhjd 20220118 start 使用既有区域建规则，跳过区域存储
            // 3. 存储区域以及区域履历
            resultError = saveAreaAndHistory(area);
            if (resultError != 0) {
                throw new BusinessException("存储区域失败1，存储履历失败2，更新履历id失败3：" + resultError + ", rules" + rules);
            }
            // add by zhjd 20220118 end
        }
        resultError = saveRuleAreaAndHistory(area, rules);
        if (resultError != 0) {
            throw new BusinessException("存储规则区域关系失败1，存储履历失败2，更新履历id失败3：" + resultError + ", rules" + rules);
        }

        // 用于返回
        rules.setAreaPoints(area.getPoints());
        rules.setAreaId(area.getId());
        return area;
    }

    /**
     * 存储区域以及区域履历
     * @param item {@link EventArea}
     * @return 0表示成功，1区域存储失败，2区域履历存储失败，3更新履历id失败
     */
    @Transactional(transactionManager = "defaultSqlTransactionManager")
    public int saveAreaAndHistory(EventArea item) {
        areaMapper.insert(item);
        if (Convert.toInt(item.getId(), 0) == 0) {
            return 1;
        }
        EventAreaHistory eah = new EventAreaHistory();
        BeanUtil.copyProperties(item, eah);
        eah.setAreaId(item.getId());
        areaHistoryMapper.insert(eah);
        if (Convert.toInt(eah.getId(), 0) == 0) {
            return 2;
        }
        EventArea update = new EventArea();
        update.setAreaHistoryId(eah.getId());
        update.setId(item.getId());
        int updateResult = areaMapper.updateByPrimaryKeySelective(update);
        if (updateResult == 1) {
            return 0;
        }
        return 3;
    }

    /**
     * 存储规则区域关系及其履历
     * @param item {@link EventArea}
     * @param rule 规则
     * @return 0表示成功，1规则区域关系失败，2履历失败，3更新履历id失败
     */
    @Transactional(transactionManager = "defaultSqlTransactionManager")
    public int saveRuleAreaAndHistory(EventArea item, EventRuleDTO rule) {
        EventRuleArea era = new EventRuleArea();
        BeanUtil.copyProperties(item, era);
        era.setRuleId(rule.getRuleId());
        era.setAreaId(item.getId());
        ruleAreaMapper.insert(era);
        if (Convert.toInt(era.getId(), 0) == 0) {
            return 1;
        }
        EventRuleAreaHistory erah = new EventRuleAreaHistory();
        BeanUtil.copyProperties(era, erah);
        erah.setRuleAreaId(era.getId());
        ruleAreaHistoryMapper.insert(erah);
        if (Convert.toInt(erah.getId(), 0) == 0) {
            return 2;
        }

        EventRuleArea update = new EventRuleArea();
        update.setRuleAreaHistoryId(erah.getId());
        update.setId(era.getId());
        int updateResult = ruleAreaMapper.updateByPrimaryKeySelective(update);
        if (updateResult == 1) {
            rule.setRuleAreaHistoryId(update.getRuleAreaHistoryId());
            return 0;
        }
        return 3;
    }

    private EventRule insertRule(EventRuleDTO item) {
        EventRule ruleRecord = new EventRule();
        BeanUtil.copyProperties(item, ruleRecord, CopyOptions.create().ignoreError());
        ruleRecord.setCreateTime(new Date());
        ruleRecord.setUpdateTime(new Date());
        BeanUtil.copyProperties(ruleRecord, item, CopyOptions.create().ignoreError().ignoreNullValue());
        // 1. 存储规则
        int result = ruleMapper.insert(ruleRecord);
        if (result != 1 || ruleRecord.getId() == null) {
            throw new BusinessException("存储规则失败" + ruleRecord + ", " + item + ", result=" + result);
        }
        // 将规则ID赋到dto
        item.setRuleId(ruleRecord.getId());
        return ruleRecord;
    }

    /**
     * 根据入参对象，生成区域对象
     * @date 2020/7/2
     * @author zhjd
     */
    private EventArea generateRuleArea(EventRuleDTO rules) {
        EventArea entity = new EventArea();
        BeanUtil.copyProperties(rules, entity);
        entity.setAreaType((byte) 1);
        entity.setIsdel((byte) 0);
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        if (Convert.toInt(rules.getShapeType(), 0) == 2) {
            String[] circleData = rules.getCircles().split(StrUtil.COMMA);
            List<double[]> pointList = GisUtils.getInstance()
                    .getCircularAreaPoints(Convert.toDouble(circleData[0]), Convert.toDouble(circleData[1]), Convert.toDouble(circleData[2]));
            entity.setPoints(JSON.toJSONString(pointList));
        } else {
            entity.setPoints(rules.getAreaPoints());
        }
        return entity;
    }

    /**
     * 新建缓存规则、及其关联设备、区域数据
     * @date 2020/7/2
     * @author zhjd
     */
    public void saveCacheRules(EventRuleDTO ruleRecord, EventArea area, HashSet<String> deviceNumSet) {
        setRedisRule(ruleRecord, area);
        setRedisRuleDevice(Convert.toStr(ruleRecord.getRuleId()), deviceNumSet);
    }

    /**
     * 更新缓存规则、及其关联设备、区域数据
     * @date 2020/7/2
     * @author zhjd
     */
    public void updateCacheRules(EventRuleDTO ruleRecord, EventArea area, Set<String> updateDevices, Set<String> originDevices) {
        updateRedisRule(ruleRecord, area);
        String ruleIdStr = Convert.toStr(ruleRecord.getRuleId());
        // 删除原关联设备 originDevices
        removeRedisRuleDevice(ruleIdStr, originDevices);
        // 添加新关联设备 updateDevices
        setRedisRuleDevice(ruleIdStr, updateDevices);
    }

    /**
     * redis set-事件规则详情
     * @return void
     * @date 2020/7/3
     * @author zhjd
     */
    private void setRedisRule(EventRuleDTO ruleRecord, EventArea area) {
        com.diditech.iov.gps.common.domain.EventRule redisEventRule = new com.diditech.iov.gps.common.domain.EventRule();
        // 1.区域数据
        if (area != null) {
            BeanUtil.copyProperties(area, redisEventRule);
            redisEventRule.setAreaId(area.getId());
        }
        // 2.规则数据
        try {
            BeanUtil.copyProperties(ruleRecord, redisEventRule);
        } catch (UtilException ex) {
            log.error("", ex);
        }
        redisEventRule.setTimeEnable(Convert.toInt(ruleRecord.getTimeEnable(), 0));
        coreRedisTemplate.opsForValue().set(redis_key_pre_rule + ruleRecord.getRuleId(), redisEventRule);
        log.debug("setRedisRule:{},{}", ruleRecord, area);
    }

    private void updateRedisRule(EventRuleDTO ruleRecord, EventArea area) {
        String key = redis_key_pre_rule + ruleRecord.getRuleId();
        com.diditech.iov.gps.common.domain.EventRule redisEventRule = (com.diditech.iov.gps.common.domain.EventRule) coreRedisTemplate.opsForValue().get(key);
        if (redisEventRule == null) {
            return;
        }
        BeanUtil.copyProperties(ruleRecord, redisEventRule, CopyOptions.create().ignoreNullValue());
        if (area != null) {
            BeanUtil.copyProperties(area, redisEventRule, CopyOptions.create().ignoreNullValue());
        }
        coreRedisTemplate.opsForValue().set(key, redisEventRule);
        log.debug("updateRedisRule:{},{}", ruleRecord, area);
    }

    /**
     * redis remove-删除规则原关联设备
     * @date 2020/7/3
     * @author zhjd
     */
    private void removeRedisRuleDevice(String ruleIdStr, Set<String> devices) {
        coreRedisTemplate.executePipelined((RedisCallback<List<Object>>) connection -> {
            for (String num : devices) {
                connection.sRem(redis_key_pre_rule_device.concat(num).getBytes(), ruleIdStr.getBytes());
            }
            return null;
        });
        log.debug("removeRedisRuleDevice:{},{}", ruleIdStr, devices);
    }

    /**
     * redis set-添加规则原关联设备
     * @date 2020/7/3
     * @author zhjd
     */
    private void setRedisRuleDevice(String ruleIdStr, Set<String> devices) {
        coreRedisTemplate.executePipelined((RedisCallback<List<Object>>) connection -> {
            for (String num : devices) {
                connection.sAdd(redis_key_pre_rule_device.concat(num).getBytes(), ruleIdStr.getBytes());
            }
            return null;
        });
        log.debug("setRedisRuleDevice:{},{}", ruleIdStr, devices);
    }

    public Set<Integer> getAvailableRuleTypes(String clientId) {
        if (StrUtil.isBlank(clientId) || !clientEventTypes.containsKey(clientId)) {
            return null;
        }
        return clientEventTypes.get(clientId);
    }

    // add by hf 20211123 start
    @Override
    public EventRuleThreshold getThreshold(String device, Integer ruleType) {
        String key = joinThresholdRedisKey(device, ruleType);
        Object obj = coreRedisTemplate.opsForValue().get(key);
        if (null == obj) {
            return null;
        }
        return (EventRuleThreshold) obj;
    }

    @Override
    public void setThreshold(String device, Integer ruleType, EventRuleThreshold threshold) {
        String key = joinThresholdRedisKey(device, ruleType);
        coreRedisTemplate.opsForValue().set(key, threshold);
    }

    @Override
    public void deleteThreshold(String device, Integer ruleType) {
        String key = joinThresholdRedisKey(device, ruleType);
        coreRedisTemplate.delete(key);
    }

    /**
     * event-rule:threshold:${deviceNum}:${ruleType}
     */
    private String joinThresholdRedisKey(String device, Integer ruleType) {
        return String.format(redis_key_pre_event_rule_threshold, device, ruleType);
    }
    // add by hf 20211123 end

}
