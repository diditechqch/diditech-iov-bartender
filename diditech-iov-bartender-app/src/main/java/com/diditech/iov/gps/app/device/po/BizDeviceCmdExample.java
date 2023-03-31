package com.diditech.iov.gps.app.device.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BizDeviceCmdExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizDeviceCmdExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andDeviceNumIsNull() {
            addCriterion("DEVICE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNumIsNotNull() {
            addCriterion("DEVICE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNumEqualTo(String value) {
            addCriterion("DEVICE_NUM =", value, "deviceNum");
            return (Criteria) this;
        }

        public Criteria andDeviceNumNotEqualTo(String value) {
            addCriterion("DEVICE_NUM <>", value, "deviceNum");
            return (Criteria) this;
        }

        public Criteria andDeviceNumGreaterThan(String value) {
            addCriterion("DEVICE_NUM >", value, "deviceNum");
            return (Criteria) this;
        }

        public Criteria andDeviceNumGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_NUM >=", value, "deviceNum");
            return (Criteria) this;
        }

        public Criteria andDeviceNumLessThan(String value) {
            addCriterion("DEVICE_NUM <", value, "deviceNum");
            return (Criteria) this;
        }

        public Criteria andDeviceNumLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_NUM <=", value, "deviceNum");
            return (Criteria) this;
        }

        public Criteria andDeviceNumLike(String value) {
            addCriterion("DEVICE_NUM like", value, "deviceNum");
            return (Criteria) this;
        }

        public Criteria andDeviceNumNotLike(String value) {
            addCriterion("DEVICE_NUM not like", value, "deviceNum");
            return (Criteria) this;
        }

        public Criteria andDeviceNumIn(List<String> values) {
            addCriterion("DEVICE_NUM in", values, "deviceNum");
            return (Criteria) this;
        }

        public Criteria andDeviceNumNotIn(List<String> values) {
            addCriterion("DEVICE_NUM not in", values, "deviceNum");
            return (Criteria) this;
        }

        public Criteria andDeviceNumBetween(String value1, String value2) {
            addCriterion("DEVICE_NUM between", value1, value2, "deviceNum");
            return (Criteria) this;
        }

        public Criteria andDeviceNumNotBetween(String value1, String value2) {
            addCriterion("DEVICE_NUM not between", value1, value2, "deviceNum");
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

        public Criteria andCmdNameIsNull() {
            addCriterion("CMD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCmdNameIsNotNull() {
            addCriterion("CMD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCmdNameEqualTo(String value) {
            addCriterion("CMD_NAME =", value, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameNotEqualTo(String value) {
            addCriterion("CMD_NAME <>", value, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameGreaterThan(String value) {
            addCriterion("CMD_NAME >", value, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameGreaterThanOrEqualTo(String value) {
            addCriterion("CMD_NAME >=", value, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameLessThan(String value) {
            addCriterion("CMD_NAME <", value, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameLessThanOrEqualTo(String value) {
            addCriterion("CMD_NAME <=", value, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameLike(String value) {
            addCriterion("CMD_NAME like", value, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameNotLike(String value) {
            addCriterion("CMD_NAME not like", value, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameIn(List<String> values) {
            addCriterion("CMD_NAME in", values, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameNotIn(List<String> values) {
            addCriterion("CMD_NAME not in", values, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameBetween(String value1, String value2) {
            addCriterion("CMD_NAME between", value1, value2, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameNotBetween(String value1, String value2) {
            addCriterion("CMD_NAME not between", value1, value2, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdIsNull() {
            addCriterion("CMD is null");
            return (Criteria) this;
        }

        public Criteria andCmdIsNotNull() {
            addCriterion("CMD is not null");
            return (Criteria) this;
        }

        public Criteria andCmdEqualTo(String value) {
            addCriterion("CMD =", value, "cmd");
            return (Criteria) this;
        }

        public Criteria andCmdNotEqualTo(String value) {
            addCriterion("CMD <>", value, "cmd");
            return (Criteria) this;
        }

        public Criteria andCmdGreaterThan(String value) {
            addCriterion("CMD >", value, "cmd");
            return (Criteria) this;
        }

        public Criteria andCmdGreaterThanOrEqualTo(String value) {
            addCriterion("CMD >=", value, "cmd");
            return (Criteria) this;
        }

        public Criteria andCmdLessThan(String value) {
            addCriterion("CMD <", value, "cmd");
            return (Criteria) this;
        }

        public Criteria andCmdLessThanOrEqualTo(String value) {
            addCriterion("CMD <=", value, "cmd");
            return (Criteria) this;
        }

        public Criteria andCmdLike(String value) {
            addCriterion("CMD like", value, "cmd");
            return (Criteria) this;
        }

        public Criteria andCmdNotLike(String value) {
            addCriterion("CMD not like", value, "cmd");
            return (Criteria) this;
        }

        public Criteria andCmdIn(List<String> values) {
            addCriterion("CMD in", values, "cmd");
            return (Criteria) this;
        }

        public Criteria andCmdNotIn(List<String> values) {
            addCriterion("CMD not in", values, "cmd");
            return (Criteria) this;
        }

        public Criteria andCmdBetween(String value1, String value2) {
            addCriterion("CMD between", value1, value2, "cmd");
            return (Criteria) this;
        }

        public Criteria andCmdNotBetween(String value1, String value2) {
            addCriterion("CMD not between", value1, value2, "cmd");
            return (Criteria) this;
        }

        public Criteria andCmdStrIsNull() {
            addCriterion("CMD_STR is null");
            return (Criteria) this;
        }

        public Criteria andCmdStrIsNotNull() {
            addCriterion("CMD_STR is not null");
            return (Criteria) this;
        }

        public Criteria andCmdStrEqualTo(String value) {
            addCriterion("CMD_STR =", value, "cmdStr");
            return (Criteria) this;
        }

        public Criteria andCmdStrNotEqualTo(String value) {
            addCriterion("CMD_STR <>", value, "cmdStr");
            return (Criteria) this;
        }

        public Criteria andCmdStrGreaterThan(String value) {
            addCriterion("CMD_STR >", value, "cmdStr");
            return (Criteria) this;
        }

        public Criteria andCmdStrGreaterThanOrEqualTo(String value) {
            addCriterion("CMD_STR >=", value, "cmdStr");
            return (Criteria) this;
        }

        public Criteria andCmdStrLessThan(String value) {
            addCriterion("CMD_STR <", value, "cmdStr");
            return (Criteria) this;
        }

        public Criteria andCmdStrLessThanOrEqualTo(String value) {
            addCriterion("CMD_STR <=", value, "cmdStr");
            return (Criteria) this;
        }

        public Criteria andCmdStrLike(String value) {
            addCriterion("CMD_STR like", value, "cmdStr");
            return (Criteria) this;
        }

        public Criteria andCmdStrNotLike(String value) {
            addCriterion("CMD_STR not like", value, "cmdStr");
            return (Criteria) this;
        }

        public Criteria andCmdStrIn(List<String> values) {
            addCriterion("CMD_STR in", values, "cmdStr");
            return (Criteria) this;
        }

        public Criteria andCmdStrNotIn(List<String> values) {
            addCriterion("CMD_STR not in", values, "cmdStr");
            return (Criteria) this;
        }

        public Criteria andCmdStrBetween(String value1, String value2) {
            addCriterion("CMD_STR between", value1, value2, "cmdStr");
            return (Criteria) this;
        }

        public Criteria andCmdStrNotBetween(String value1, String value2) {
            addCriterion("CMD_STR not between", value1, value2, "cmdStr");
            return (Criteria) this;
        }

        public Criteria andCmdTimeIsNull() {
            addCriterion("CMD_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCmdTimeIsNotNull() {
            addCriterion("CMD_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCmdTimeEqualTo(Date value) {
            addCriterion("CMD_TIME =", value, "cmdTime");
            return (Criteria) this;
        }

        public Criteria andCmdTimeNotEqualTo(Date value) {
            addCriterion("CMD_TIME <>", value, "cmdTime");
            return (Criteria) this;
        }

        public Criteria andCmdTimeGreaterThan(Date value) {
            addCriterion("CMD_TIME >", value, "cmdTime");
            return (Criteria) this;
        }

        public Criteria andCmdTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CMD_TIME >=", value, "cmdTime");
            return (Criteria) this;
        }

        public Criteria andCmdTimeLessThan(Date value) {
            addCriterion("CMD_TIME <", value, "cmdTime");
            return (Criteria) this;
        }

        public Criteria andCmdTimeLessThanOrEqualTo(Date value) {
            addCriterion("CMD_TIME <=", value, "cmdTime");
            return (Criteria) this;
        }

        public Criteria andCmdTimeIn(List<Date> values) {
            addCriterion("CMD_TIME in", values, "cmdTime");
            return (Criteria) this;
        }

        public Criteria andCmdTimeNotIn(List<Date> values) {
            addCriterion("CMD_TIME not in", values, "cmdTime");
            return (Criteria) this;
        }

        public Criteria andCmdTimeBetween(Date value1, Date value2) {
            addCriterion("CMD_TIME between", value1, value2, "cmdTime");
            return (Criteria) this;
        }

        public Criteria andCmdTimeNotBetween(Date value1, Date value2) {
            addCriterion("CMD_TIME not between", value1, value2, "cmdTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("SEND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("SEND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("SEND_TIME =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("SEND_TIME <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("SEND_TIME >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SEND_TIME >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("SEND_TIME <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("SEND_TIME <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("SEND_TIME in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("SEND_TIME not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("SEND_TIME between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("SEND_TIME not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("RESULT is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(String value) {
            addCriterion("RESULT =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(String value) {
            addCriterion("RESULT <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(String value) {
            addCriterion("RESULT >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(String value) {
            addCriterion("RESULT >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(String value) {
            addCriterion("RESULT <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(String value) {
            addCriterion("RESULT <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLike(String value) {
            addCriterion("RESULT like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotLike(String value) {
            addCriterion("RESULT not like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<String> values) {
            addCriterion("RESULT in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<String> values) {
            addCriterion("RESULT not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(String value1, String value2) {
            addCriterion("RESULT between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(String value1, String value2) {
            addCriterion("RESULT not between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultTimeIsNull() {
            addCriterion("RESULT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andResultTimeIsNotNull() {
            addCriterion("RESULT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andResultTimeEqualTo(Date value) {
            addCriterion("RESULT_TIME =", value, "resultTime");
            return (Criteria) this;
        }

        public Criteria andResultTimeNotEqualTo(Date value) {
            addCriterion("RESULT_TIME <>", value, "resultTime");
            return (Criteria) this;
        }

        public Criteria andResultTimeGreaterThan(Date value) {
            addCriterion("RESULT_TIME >", value, "resultTime");
            return (Criteria) this;
        }

        public Criteria andResultTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("RESULT_TIME >=", value, "resultTime");
            return (Criteria) this;
        }

        public Criteria andResultTimeLessThan(Date value) {
            addCriterion("RESULT_TIME <", value, "resultTime");
            return (Criteria) this;
        }

        public Criteria andResultTimeLessThanOrEqualTo(Date value) {
            addCriterion("RESULT_TIME <=", value, "resultTime");
            return (Criteria) this;
        }

        public Criteria andResultTimeIn(List<Date> values) {
            addCriterion("RESULT_TIME in", values, "resultTime");
            return (Criteria) this;
        }

        public Criteria andResultTimeNotIn(List<Date> values) {
            addCriterion("RESULT_TIME not in", values, "resultTime");
            return (Criteria) this;
        }

        public Criteria andResultTimeBetween(Date value1, Date value2) {
            addCriterion("RESULT_TIME between", value1, value2, "resultTime");
            return (Criteria) this;
        }

        public Criteria andResultTimeNotBetween(Date value1, Date value2) {
            addCriterion("RESULT_TIME not between", value1, value2, "resultTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andExeCountIsNull() {
            addCriterion("EXE_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andExeCountIsNotNull() {
            addCriterion("EXE_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andExeCountEqualTo(Short value) {
            addCriterion("EXE_COUNT =", value, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountNotEqualTo(Short value) {
            addCriterion("EXE_COUNT <>", value, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountGreaterThan(Short value) {
            addCriterion("EXE_COUNT >", value, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountGreaterThanOrEqualTo(Short value) {
            addCriterion("EXE_COUNT >=", value, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountLessThan(Short value) {
            addCriterion("EXE_COUNT <", value, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountLessThanOrEqualTo(Short value) {
            addCriterion("EXE_COUNT <=", value, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountIn(List<Short> values) {
            addCriterion("EXE_COUNT in", values, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountNotIn(List<Short> values) {
            addCriterion("EXE_COUNT not in", values, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountBetween(Short value1, Short value2) {
            addCriterion("EXE_COUNT between", value1, value2, "exeCount");
            return (Criteria) this;
        }

        public Criteria andExeCountNotBetween(Short value1, Short value2) {
            addCriterion("EXE_COUNT not between", value1, value2, "exeCount");
            return (Criteria) this;
        }

        public Criteria andBatIdIsNull() {
            addCriterion("BAT_ID is null");
            return (Criteria) this;
        }

        public Criteria andBatIdIsNotNull() {
            addCriterion("BAT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBatIdEqualTo(Integer value) {
            addCriterion("BAT_ID =", value, "batId");
            return (Criteria) this;
        }

        public Criteria andBatIdNotEqualTo(Integer value) {
            addCriterion("BAT_ID <>", value, "batId");
            return (Criteria) this;
        }

        public Criteria andBatIdGreaterThan(Integer value) {
            addCriterion("BAT_ID >", value, "batId");
            return (Criteria) this;
        }

        public Criteria andBatIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("BAT_ID >=", value, "batId");
            return (Criteria) this;
        }

        public Criteria andBatIdLessThan(Integer value) {
            addCriterion("BAT_ID <", value, "batId");
            return (Criteria) this;
        }

        public Criteria andBatIdLessThanOrEqualTo(Integer value) {
            addCriterion("BAT_ID <=", value, "batId");
            return (Criteria) this;
        }

        public Criteria andBatIdIn(List<Integer> values) {
            addCriterion("BAT_ID in", values, "batId");
            return (Criteria) this;
        }

        public Criteria andBatIdNotIn(List<Integer> values) {
            addCriterion("BAT_ID not in", values, "batId");
            return (Criteria) this;
        }

        public Criteria andBatIdBetween(Integer value1, Integer value2) {
            addCriterion("BAT_ID between", value1, value2, "batId");
            return (Criteria) this;
        }

        public Criteria andBatIdNotBetween(Integer value1, Integer value2) {
            addCriterion("BAT_ID not between", value1, value2, "batId");
            return (Criteria) this;
        }

        public Criteria andTokenIdIsNull() {
            addCriterion("TOKEN_ID is null");
            return (Criteria) this;
        }

        public Criteria andTokenIdIsNotNull() {
            addCriterion("TOKEN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTokenIdEqualTo(String value) {
            addCriterion("TOKEN_ID =", value, "tokenId");
            return (Criteria) this;
        }

        public Criteria andTokenIdNotEqualTo(String value) {
            addCriterion("TOKEN_ID <>", value, "tokenId");
            return (Criteria) this;
        }

        public Criteria andTokenIdGreaterThan(String value) {
            addCriterion("TOKEN_ID >", value, "tokenId");
            return (Criteria) this;
        }

        public Criteria andTokenIdGreaterThanOrEqualTo(String value) {
            addCriterion("TOKEN_ID >=", value, "tokenId");
            return (Criteria) this;
        }

        public Criteria andTokenIdLessThan(String value) {
            addCriterion("TOKEN_ID <", value, "tokenId");
            return (Criteria) this;
        }

        public Criteria andTokenIdLessThanOrEqualTo(String value) {
            addCriterion("TOKEN_ID <=", value, "tokenId");
            return (Criteria) this;
        }

        public Criteria andTokenIdLike(String value) {
            addCriterion("TOKEN_ID like", value, "tokenId");
            return (Criteria) this;
        }

        public Criteria andTokenIdNotLike(String value) {
            addCriterion("TOKEN_ID not like", value, "tokenId");
            return (Criteria) this;
        }

        public Criteria andTokenIdIn(List<String> values) {
            addCriterion("TOKEN_ID in", values, "tokenId");
            return (Criteria) this;
        }

        public Criteria andTokenIdNotIn(List<String> values) {
            addCriterion("TOKEN_ID not in", values, "tokenId");
            return (Criteria) this;
        }

        public Criteria andTokenIdBetween(String value1, String value2) {
            addCriterion("TOKEN_ID between", value1, value2, "tokenId");
            return (Criteria) this;
        }

        public Criteria andTokenIdNotBetween(String value1, String value2) {
            addCriterion("TOKEN_ID not between", value1, value2, "tokenId");
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