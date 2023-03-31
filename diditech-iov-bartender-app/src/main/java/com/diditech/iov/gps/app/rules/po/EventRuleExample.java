package com.diditech.iov.gps.app.rules.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EventRuleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        return new Criteria();
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRuleTypeIsNull() {
            addCriterion("RULE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRuleTypeIsNotNull() {
            addCriterion("RULE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRuleTypeEqualTo(Integer value) {
            addCriterion("RULE_TYPE =", value, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeNotEqualTo(Integer value) {
            addCriterion("RULE_TYPE <>", value, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeGreaterThan(Integer value) {
            addCriterion("RULE_TYPE >", value, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("RULE_TYPE >=", value, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeLessThan(Integer value) {
            addCriterion("RULE_TYPE <", value, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeLessThanOrEqualTo(Integer value) {
            addCriterion("RULE_TYPE <=", value, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeIn(List<Integer> values) {
            addCriterion("RULE_TYPE in", values, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeNotIn(List<Integer> values) {
            addCriterion("RULE_TYPE not in", values, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeBetween(Integer value1, Integer value2) {
            addCriterion("RULE_TYPE between", value1, value2, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("RULE_TYPE not between", value1, value2, "ruleType");
            return (Criteria) this;
        }

        public Criteria andRuleNameIsNull() {
            addCriterion("RULE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRuleNameIsNotNull() {
            addCriterion("RULE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRuleNameEqualTo(String value) {
            addCriterion("RULE_NAME =", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotEqualTo(String value) {
            addCriterion("RULE_NAME <>", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameGreaterThan(String value) {
            addCriterion("RULE_NAME >", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("RULE_NAME >=", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLessThan(String value) {
            addCriterion("RULE_NAME <", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLessThanOrEqualTo(String value) {
            addCriterion("RULE_NAME <=", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLike(String value) {
            addCriterion("RULE_NAME like", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotLike(String value) {
            addCriterion("RULE_NAME not like", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameIn(List<String> values) {
            addCriterion("RULE_NAME in", values, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotIn(List<String> values) {
            addCriterion("RULE_NAME not in", values, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameBetween(String value1, String value2) {
            addCriterion("RULE_NAME between", value1, value2, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotBetween(String value1, String value2) {
            addCriterion("RULE_NAME not between", value1, value2, "ruleName");
            return (Criteria) this;
        }

        public Criteria andAreaEnableIsNull() {
            addCriterion("AREA_ENABLE is null");
            return (Criteria) this;
        }

        public Criteria andAreaEnableIsNotNull() {
            addCriterion("AREA_ENABLE is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEnableEqualTo(Byte value) {
            addCriterion("AREA_ENABLE =", value, "areaEnable");
            return (Criteria) this;
        }

        public Criteria andAreaEnableNotEqualTo(Byte value) {
            addCriterion("AREA_ENABLE <>", value, "areaEnable");
            return (Criteria) this;
        }

        public Criteria andAreaEnableGreaterThan(Byte value) {
            addCriterion("AREA_ENABLE >", value, "areaEnable");
            return (Criteria) this;
        }

        public Criteria andAreaEnableGreaterThanOrEqualTo(Byte value) {
            addCriterion("AREA_ENABLE >=", value, "areaEnable");
            return (Criteria) this;
        }

        public Criteria andAreaEnableLessThan(Byte value) {
            addCriterion("AREA_ENABLE <", value, "areaEnable");
            return (Criteria) this;
        }

        public Criteria andAreaEnableLessThanOrEqualTo(Byte value) {
            addCriterion("AREA_ENABLE <=", value, "areaEnable");
            return (Criteria) this;
        }

        public Criteria andAreaEnableIn(List<Byte> values) {
            addCriterion("AREA_ENABLE in", values, "areaEnable");
            return (Criteria) this;
        }

        public Criteria andAreaEnableNotIn(List<Byte> values) {
            addCriterion("AREA_ENABLE not in", values, "areaEnable");
            return (Criteria) this;
        }

        public Criteria andAreaEnableBetween(Byte value1, Byte value2) {
            addCriterion("AREA_ENABLE between", value1, value2, "areaEnable");
            return (Criteria) this;
        }

        public Criteria andAreaEnableNotBetween(Byte value1, Byte value2) {
            addCriterion("AREA_ENABLE not between", value1, value2, "areaEnable");
            return (Criteria) this;
        }

        public Criteria andIsdelIsNull() {
            addCriterion("ISDEL is null");
            return (Criteria) this;
        }

        public Criteria andIsdelIsNotNull() {
            addCriterion("ISDEL is not null");
            return (Criteria) this;
        }

        public Criteria andIsdelEqualTo(Byte value) {
            addCriterion("ISDEL =", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotEqualTo(Byte value) {
            addCriterion("ISDEL <>", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThan(Byte value) {
            addCriterion("ISDEL >", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelGreaterThanOrEqualTo(Byte value) {
            addCriterion("ISDEL >=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThan(Byte value) {
            addCriterion("ISDEL <", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelLessThanOrEqualTo(Byte value) {
            addCriterion("ISDEL <=", value, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelIn(List<Byte> values) {
            addCriterion("ISDEL in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotIn(List<Byte> values) {
            addCriterion("ISDEL not in", values, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelBetween(Byte value1, Byte value2) {
            addCriterion("ISDEL between", value1, value2, "isdel");
            return (Criteria) this;
        }

        public Criteria andIsdelNotBetween(Byte value1, Byte value2) {
            addCriterion("ISDEL not between", value1, value2, "isdel");
            return (Criteria) this;
        }

        public Criteria andEnableTimeFromIsNull() {
            addCriterion("ENABLE_TIME_FROM is null");
            return (Criteria) this;
        }

        public Criteria andEnableTimeFromIsNotNull() {
            addCriterion("ENABLE_TIME_FROM is not null");
            return (Criteria) this;
        }

        public Criteria andEnableTimeFromEqualTo(Date value) {
            addCriterion("ENABLE_TIME_FROM =", value, "enableTimeFrom");
            return (Criteria) this;
        }

        public Criteria andEnableTimeFromNotEqualTo(Date value) {
            addCriterion("ENABLE_TIME_FROM <>", value, "enableTimeFrom");
            return (Criteria) this;
        }

        public Criteria andEnableTimeFromGreaterThan(Date value) {
            addCriterion("ENABLE_TIME_FROM >", value, "enableTimeFrom");
            return (Criteria) this;
        }

        public Criteria andEnableTimeFromGreaterThanOrEqualTo(Date value) {
            addCriterion("ENABLE_TIME_FROM >=", value, "enableTimeFrom");
            return (Criteria) this;
        }

        public Criteria andEnableTimeFromLessThan(Date value) {
            addCriterion("ENABLE_TIME_FROM <", value, "enableTimeFrom");
            return (Criteria) this;
        }

        public Criteria andEnableTimeFromLessThanOrEqualTo(Date value) {
            addCriterion("ENABLE_TIME_FROM <=", value, "enableTimeFrom");
            return (Criteria) this;
        }

        public Criteria andEnableTimeFromIn(List<Date> values) {
            addCriterion("ENABLE_TIME_FROM in", values, "enableTimeFrom");
            return (Criteria) this;
        }

        public Criteria andEnableTimeFromNotIn(List<Date> values) {
            addCriterion("ENABLE_TIME_FROM not in", values, "enableTimeFrom");
            return (Criteria) this;
        }

        public Criteria andEnableTimeFromBetween(Date value1, Date value2) {
            addCriterion("ENABLE_TIME_FROM between", value1, value2, "enableTimeFrom");
            return (Criteria) this;
        }

        public Criteria andEnableTimeFromNotBetween(Date value1, Date value2) {
            addCriterion("ENABLE_TIME_FROM not between", value1, value2, "enableTimeFrom");
            return (Criteria) this;
        }

        public Criteria andEnableTimeToIsNull() {
            addCriterion("ENABLE_TIME_TO is null");
            return (Criteria) this;
        }

        public Criteria andEnableTimeToIsNotNull() {
            addCriterion("ENABLE_TIME_TO is not null");
            return (Criteria) this;
        }

        public Criteria andEnableTimeToEqualTo(Date value) {
            addCriterion("ENABLE_TIME_TO =", value, "enableTimeTo");
            return (Criteria) this;
        }

        public Criteria andEnableTimeToNotEqualTo(Date value) {
            addCriterion("ENABLE_TIME_TO <>", value, "enableTimeTo");
            return (Criteria) this;
        }

        public Criteria andEnableTimeToGreaterThan(Date value) {
            addCriterion("ENABLE_TIME_TO >", value, "enableTimeTo");
            return (Criteria) this;
        }

        public Criteria andEnableTimeToGreaterThanOrEqualTo(Date value) {
            addCriterion("ENABLE_TIME_TO >=", value, "enableTimeTo");
            return (Criteria) this;
        }

        public Criteria andEnableTimeToLessThan(Date value) {
            addCriterion("ENABLE_TIME_TO <", value, "enableTimeTo");
            return (Criteria) this;
        }

        public Criteria andEnableTimeToLessThanOrEqualTo(Date value) {
            addCriterion("ENABLE_TIME_TO <=", value, "enableTimeTo");
            return (Criteria) this;
        }

        public Criteria andEnableTimeToIn(List<Date> values) {
            addCriterion("ENABLE_TIME_TO in", values, "enableTimeTo");
            return (Criteria) this;
        }

        public Criteria andEnableTimeToNotIn(List<Date> values) {
            addCriterion("ENABLE_TIME_TO not in", values, "enableTimeTo");
            return (Criteria) this;
        }

        public Criteria andEnableTimeToBetween(Date value1, Date value2) {
            addCriterion("ENABLE_TIME_TO between", value1, value2, "enableTimeTo");
            return (Criteria) this;
        }

        public Criteria andEnableTimeToNotBetween(Date value1, Date value2) {
            addCriterion("ENABLE_TIME_TO not between", value1, value2, "enableTimeTo");
            return (Criteria) this;
        }

        public Criteria andTimeEnableIsNull() {
            addCriterion("TIME_ENABLE is null");
            return (Criteria) this;
        }

        public Criteria andTimeEnableIsNotNull() {
            addCriterion("TIME_ENABLE is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEnableEqualTo(Byte value) {
            addCriterion("TIME_ENABLE =", value, "timeEnable");
            return (Criteria) this;
        }

        public Criteria andTimeEnableNotEqualTo(Byte value) {
            addCriterion("TIME_ENABLE <>", value, "timeEnable");
            return (Criteria) this;
        }

        public Criteria andTimeEnableGreaterThan(Byte value) {
            addCriterion("TIME_ENABLE >", value, "timeEnable");
            return (Criteria) this;
        }

        public Criteria andTimeEnableGreaterThanOrEqualTo(Byte value) {
            addCriterion("TIME_ENABLE >=", value, "timeEnable");
            return (Criteria) this;
        }

        public Criteria andTimeEnableLessThan(Byte value) {
            addCriterion("TIME_ENABLE <", value, "timeEnable");
            return (Criteria) this;
        }

        public Criteria andTimeEnableLessThanOrEqualTo(Byte value) {
            addCriterion("TIME_ENABLE <=", value, "timeEnable");
            return (Criteria) this;
        }

        public Criteria andTimeEnableIn(List<Byte> values) {
            addCriterion("TIME_ENABLE in", values, "timeEnable");
            return (Criteria) this;
        }

        public Criteria andTimeEnableNotIn(List<Byte> values) {
            addCriterion("TIME_ENABLE not in", values, "timeEnable");
            return (Criteria) this;
        }

        public Criteria andTimeEnableBetween(Byte value1, Byte value2) {
            addCriterion("TIME_ENABLE between", value1, value2, "timeEnable");
            return (Criteria) this;
        }

        public Criteria andTimeEnableNotBetween(Byte value1, Byte value2) {
            addCriterion("TIME_ENABLE not between", value1, value2, "timeEnable");
            return (Criteria) this;
        }

        public Criteria andClientIdIsNull() {
            addCriterion("CLIENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andClientIdIsNotNull() {
            addCriterion("CLIENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andClientIdEqualTo(String value) {
            addCriterion("CLIENT_ID =", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotEqualTo(String value) {
            addCriterion("CLIENT_ID <>", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThan(String value) {
            addCriterion("CLIENT_ID >", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThanOrEqualTo(String value) {
            addCriterion("CLIENT_ID >=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThan(String value) {
            addCriterion("CLIENT_ID <", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThanOrEqualTo(String value) {
            addCriterion("CLIENT_ID <=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLike(String value) {
            addCriterion("CLIENT_ID like", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotLike(String value) {
            addCriterion("CLIENT_ID not like", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdIn(List<String> values) {
            addCriterion("CLIENT_ID in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotIn(List<String> values) {
            addCriterion("CLIENT_ID not in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdBetween(String value1, String value2) {
            addCriterion("CLIENT_ID between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotBetween(String value1, String value2) {
            addCriterion("CLIENT_ID not between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNull() {
            addCriterion("TENANT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("TENANT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(String value) {
            addCriterion("TENANT_ID =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(String value) {
            addCriterion("TENANT_ID <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(String value) {
            addCriterion("TENANT_ID >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(String value) {
            addCriterion("TENANT_ID >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(String value) {
            addCriterion("TENANT_ID <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(String value) {
            addCriterion("TENANT_ID <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLike(String value) {
            addCriterion("TENANT_ID like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotLike(String value) {
            addCriterion("TENANT_ID not like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<String> values) {
            addCriterion("TENANT_ID in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<String> values) {
            addCriterion("TENANT_ID not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(String value1, String value2) {
            addCriterion("TENANT_ID between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(String value1, String value2) {
            addCriterion("TENANT_ID not between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andThreshold1IsNull() {
            addCriterion("THRESHOLD1 is null");
            return (Criteria) this;
        }

        public Criteria andThreshold1IsNotNull() {
            addCriterion("THRESHOLD1 is not null");
            return (Criteria) this;
        }

        public Criteria andThreshold1EqualTo(String value) {
            addCriterion("THRESHOLD1 =", value, "threshold1");
            return (Criteria) this;
        }

        public Criteria andThreshold1NotEqualTo(String value) {
            addCriterion("THRESHOLD1 <>", value, "threshold1");
            return (Criteria) this;
        }

        public Criteria andThreshold1GreaterThan(String value) {
            addCriterion("THRESHOLD1 >", value, "threshold1");
            return (Criteria) this;
        }

        public Criteria andThreshold1GreaterThanOrEqualTo(String value) {
            addCriterion("THRESHOLD1 >=", value, "threshold1");
            return (Criteria) this;
        }

        public Criteria andThreshold1LessThan(String value) {
            addCriterion("THRESHOLD1 <", value, "threshold1");
            return (Criteria) this;
        }

        public Criteria andThreshold1LessThanOrEqualTo(String value) {
            addCriterion("THRESHOLD1 <=", value, "threshold1");
            return (Criteria) this;
        }

        public Criteria andThreshold1Like(String value) {
            addCriterion("THRESHOLD1 like", value, "threshold1");
            return (Criteria) this;
        }

        public Criteria andThreshold1NotLike(String value) {
            addCriterion("THRESHOLD1 not like", value, "threshold1");
            return (Criteria) this;
        }

        public Criteria andThreshold1In(List<String> values) {
            addCriterion("THRESHOLD1 in", values, "threshold1");
            return (Criteria) this;
        }

        public Criteria andThreshold1NotIn(List<String> values) {
            addCriterion("THRESHOLD1 not in", values, "threshold1");
            return (Criteria) this;
        }

        public Criteria andThreshold1Between(String value1, String value2) {
            addCriterion("THRESHOLD1 between", value1, value2, "threshold1");
            return (Criteria) this;
        }

        public Criteria andThreshold1NotBetween(String value1, String value2) {
            addCriterion("THRESHOLD1 not between", value1, value2, "threshold1");
            return (Criteria) this;
        }

        public Criteria andThreshold2IsNull() {
            addCriterion("THRESHOLD2 is null");
            return (Criteria) this;
        }

        public Criteria andThreshold2IsNotNull() {
            addCriterion("THRESHOLD2 is not null");
            return (Criteria) this;
        }

        public Criteria andThreshold2EqualTo(String value) {
            addCriterion("THRESHOLD2 =", value, "threshold2");
            return (Criteria) this;
        }

        public Criteria andThreshold2NotEqualTo(String value) {
            addCriterion("THRESHOLD2 <>", value, "threshold2");
            return (Criteria) this;
        }

        public Criteria andThreshold2GreaterThan(String value) {
            addCriterion("THRESHOLD2 >", value, "threshold2");
            return (Criteria) this;
        }

        public Criteria andThreshold2GreaterThanOrEqualTo(String value) {
            addCriterion("THRESHOLD2 >=", value, "threshold2");
            return (Criteria) this;
        }

        public Criteria andThreshold2LessThan(String value) {
            addCriterion("THRESHOLD2 <", value, "threshold2");
            return (Criteria) this;
        }

        public Criteria andThreshold2LessThanOrEqualTo(String value) {
            addCriterion("THRESHOLD2 <=", value, "threshold2");
            return (Criteria) this;
        }

        public Criteria andThreshold2Like(String value) {
            addCriterion("THRESHOLD2 like", value, "threshold2");
            return (Criteria) this;
        }

        public Criteria andThreshold2NotLike(String value) {
            addCriterion("THRESHOLD2 not like", value, "threshold2");
            return (Criteria) this;
        }

        public Criteria andThreshold2In(List<String> values) {
            addCriterion("THRESHOLD2 in", values, "threshold2");
            return (Criteria) this;
        }

        public Criteria andThreshold2NotIn(List<String> values) {
            addCriterion("THRESHOLD2 not in", values, "threshold2");
            return (Criteria) this;
        }

        public Criteria andThreshold2Between(String value1, String value2) {
            addCriterion("THRESHOLD2 between", value1, value2, "threshold2");
            return (Criteria) this;
        }

        public Criteria andThreshold2NotBetween(String value1, String value2) {
            addCriterion("THRESHOLD2 not between", value1, value2, "threshold2");
            return (Criteria) this;
        }

        public Criteria andThreshold3IsNull() {
            addCriterion("THRESHOLD3 is null");
            return (Criteria) this;
        }

        public Criteria andThreshold3IsNotNull() {
            addCriterion("THRESHOLD3 is not null");
            return (Criteria) this;
        }

        public Criteria andThreshold3EqualTo(String value) {
            addCriterion("THRESHOLD3 =", value, "threshold3");
            return (Criteria) this;
        }

        public Criteria andThreshold3NotEqualTo(String value) {
            addCriterion("THRESHOLD3 <>", value, "threshold3");
            return (Criteria) this;
        }

        public Criteria andThreshold3GreaterThan(String value) {
            addCriterion("THRESHOLD3 >", value, "threshold3");
            return (Criteria) this;
        }

        public Criteria andThreshold3GreaterThanOrEqualTo(String value) {
            addCriterion("THRESHOLD3 >=", value, "threshold3");
            return (Criteria) this;
        }

        public Criteria andThreshold3LessThan(String value) {
            addCriterion("THRESHOLD3 <", value, "threshold3");
            return (Criteria) this;
        }

        public Criteria andThreshold3LessThanOrEqualTo(String value) {
            addCriterion("THRESHOLD3 <=", value, "threshold3");
            return (Criteria) this;
        }

        public Criteria andThreshold3Like(String value) {
            addCriterion("THRESHOLD3 like", value, "threshold3");
            return (Criteria) this;
        }

        public Criteria andThreshold3NotLike(String value) {
            addCriterion("THRESHOLD3 not like", value, "threshold3");
            return (Criteria) this;
        }

        public Criteria andThreshold3In(List<String> values) {
            addCriterion("THRESHOLD3 in", values, "threshold3");
            return (Criteria) this;
        }

        public Criteria andThreshold3NotIn(List<String> values) {
            addCriterion("THRESHOLD3 not in", values, "threshold3");
            return (Criteria) this;
        }

        public Criteria andThreshold3Between(String value1, String value2) {
            addCriterion("THRESHOLD3 between", value1, value2, "threshold3");
            return (Criteria) this;
        }

        public Criteria andThreshold3NotBetween(String value1, String value2) {
            addCriterion("THRESHOLD3 not between", value1, value2, "threshold3");
            return (Criteria) this;
        }

        public Criteria andThreshold4IsNull() {
            addCriterion("THRESHOLD4 is null");
            return (Criteria) this;
        }

        public Criteria andThreshold4IsNotNull() {
            addCriterion("THRESHOLD4 is not null");
            return (Criteria) this;
        }

        public Criteria andThreshold4EqualTo(String value) {
            addCriterion("THRESHOLD4 =", value, "threshold4");
            return (Criteria) this;
        }

        public Criteria andThreshold4NotEqualTo(String value) {
            addCriterion("THRESHOLD4 <>", value, "threshold4");
            return (Criteria) this;
        }

        public Criteria andThreshold4GreaterThan(String value) {
            addCriterion("THRESHOLD4 >", value, "threshold4");
            return (Criteria) this;
        }

        public Criteria andThreshold4GreaterThanOrEqualTo(String value) {
            addCriterion("THRESHOLD4 >=", value, "threshold4");
            return (Criteria) this;
        }

        public Criteria andThreshold4LessThan(String value) {
            addCriterion("THRESHOLD4 <", value, "threshold4");
            return (Criteria) this;
        }

        public Criteria andThreshold4LessThanOrEqualTo(String value) {
            addCriterion("THRESHOLD4 <=", value, "threshold4");
            return (Criteria) this;
        }

        public Criteria andThreshold4Like(String value) {
            addCriterion("THRESHOLD4 like", value, "threshold4");
            return (Criteria) this;
        }

        public Criteria andThreshold4NotLike(String value) {
            addCriterion("THRESHOLD4 not like", value, "threshold4");
            return (Criteria) this;
        }

        public Criteria andThreshold4In(List<String> values) {
            addCriterion("THRESHOLD4 in", values, "threshold4");
            return (Criteria) this;
        }

        public Criteria andThreshold4NotIn(List<String> values) {
            addCriterion("THRESHOLD4 not in", values, "threshold4");
            return (Criteria) this;
        }

        public Criteria andThreshold4Between(String value1, String value2) {
            addCriterion("THRESHOLD4 between", value1, value2, "threshold4");
            return (Criteria) this;
        }

        public Criteria andThreshold4NotBetween(String value1, String value2) {
            addCriterion("THRESHOLD4 not between", value1, value2, "threshold4");
            return (Criteria) this;
        }

        public Criteria andThreshold5IsNull() {
            addCriterion("THRESHOLD5 is null");
            return (Criteria) this;
        }

        public Criteria andThreshold5IsNotNull() {
            addCriterion("THRESHOLD5 is not null");
            return (Criteria) this;
        }

        public Criteria andThreshold5EqualTo(String value) {
            addCriterion("THRESHOLD5 =", value, "threshold5");
            return (Criteria) this;
        }

        public Criteria andThreshold5NotEqualTo(String value) {
            addCriterion("THRESHOLD5 <>", value, "threshold5");
            return (Criteria) this;
        }

        public Criteria andThreshold5GreaterThan(String value) {
            addCriterion("THRESHOLD5 >", value, "threshold5");
            return (Criteria) this;
        }

        public Criteria andThreshold5GreaterThanOrEqualTo(String value) {
            addCriterion("THRESHOLD5 >=", value, "threshold5");
            return (Criteria) this;
        }

        public Criteria andThreshold5LessThan(String value) {
            addCriterion("THRESHOLD5 <", value, "threshold5");
            return (Criteria) this;
        }

        public Criteria andThreshold5LessThanOrEqualTo(String value) {
            addCriterion("THRESHOLD5 <=", value, "threshold5");
            return (Criteria) this;
        }

        public Criteria andThreshold5Like(String value) {
            addCriterion("THRESHOLD5 like", value, "threshold5");
            return (Criteria) this;
        }

        public Criteria andThreshold5NotLike(String value) {
            addCriterion("THRESHOLD5 not like", value, "threshold5");
            return (Criteria) this;
        }

        public Criteria andThreshold5In(List<String> values) {
            addCriterion("THRESHOLD5 in", values, "threshold5");
            return (Criteria) this;
        }

        public Criteria andThreshold5NotIn(List<String> values) {
            addCriterion("THRESHOLD5 not in", values, "threshold5");
            return (Criteria) this;
        }

        public Criteria andThreshold5Between(String value1, String value2) {
            addCriterion("THRESHOLD5 between", value1, value2, "threshold5");
            return (Criteria) this;
        }

        public Criteria andThreshold5NotBetween(String value1, String value2) {
            addCriterion("THRESHOLD5 not between", value1, value2, "threshold5");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}