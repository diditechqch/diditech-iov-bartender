package com.diditech.iov.gps.app.report.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RptStopsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RptStopsExample() {
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
        Criteria criteria = new Criteria();
        return criteria;
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

        public Criteria andStartTimeIsNull() {
            addCriterion("START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("START_TIME =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("START_TIME <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("START_TIME >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("START_TIME >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("START_TIME <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("START_TIME <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("START_TIME in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("START_TIME not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("START_TIME between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("START_TIME not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andDayTagIsNull() {
            addCriterion("DAY_TAG is null");
            return (Criteria) this;
        }

        public Criteria andDayTagIsNotNull() {
            addCriterion("DAY_TAG is not null");
            return (Criteria) this;
        }

        public Criteria andDayTagEqualTo(String value) {
            addCriterion("DAY_TAG =", value, "dayTag");
            return (Criteria) this;
        }

        public Criteria andDayTagNotEqualTo(String value) {
            addCriterion("DAY_TAG <>", value, "dayTag");
            return (Criteria) this;
        }

        public Criteria andDayTagGreaterThan(String value) {
            addCriterion("DAY_TAG >", value, "dayTag");
            return (Criteria) this;
        }

        public Criteria andDayTagGreaterThanOrEqualTo(String value) {
            addCriterion("DAY_TAG >=", value, "dayTag");
            return (Criteria) this;
        }

        public Criteria andDayTagLessThan(String value) {
            addCriterion("DAY_TAG <", value, "dayTag");
            return (Criteria) this;
        }

        public Criteria andDayTagLessThanOrEqualTo(String value) {
            addCriterion("DAY_TAG <=", value, "dayTag");
            return (Criteria) this;
        }

        public Criteria andDayTagLike(String value) {
            addCriterion("DAY_TAG like", value, "dayTag");
            return (Criteria) this;
        }

        public Criteria andDayTagNotLike(String value) {
            addCriterion("DAY_TAG not like", value, "dayTag");
            return (Criteria) this;
        }

        public Criteria andDayTagIn(List<String> values) {
            addCriterion("DAY_TAG in", values, "dayTag");
            return (Criteria) this;
        }

        public Criteria andDayTagNotIn(List<String> values) {
            addCriterion("DAY_TAG not in", values, "dayTag");
            return (Criteria) this;
        }

        public Criteria andDayTagBetween(String value1, String value2) {
            addCriterion("DAY_TAG between", value1, value2, "dayTag");
            return (Criteria) this;
        }

        public Criteria andDayTagNotBetween(String value1, String value2) {
            addCriterion("DAY_TAG not between", value1, value2, "dayTag");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("DURATION is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("DURATION is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(Integer value) {
            addCriterion("DURATION =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(Integer value) {
            addCriterion("DURATION <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(Integer value) {
            addCriterion("DURATION >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(Integer value) {
            addCriterion("DURATION >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(Integer value) {
            addCriterion("DURATION <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(Integer value) {
            addCriterion("DURATION <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<Integer> values) {
            addCriterion("DURATION in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<Integer> values) {
            addCriterion("DURATION not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(Integer value1, Integer value2) {
            addCriterion("DURATION between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(Integer value1, Integer value2) {
            addCriterion("DURATION not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andLngBdIsNull() {
            addCriterion("LNG_BD is null");
            return (Criteria) this;
        }

        public Criteria andLngBdIsNotNull() {
            addCriterion("LNG_BD is not null");
            return (Criteria) this;
        }

        public Criteria andLngBdEqualTo(BigDecimal value) {
            addCriterion("LNG_BD =", value, "lngBd");
            return (Criteria) this;
        }

        public Criteria andLngBdNotEqualTo(BigDecimal value) {
            addCriterion("LNG_BD <>", value, "lngBd");
            return (Criteria) this;
        }

        public Criteria andLngBdGreaterThan(BigDecimal value) {
            addCriterion("LNG_BD >", value, "lngBd");
            return (Criteria) this;
        }

        public Criteria andLngBdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LNG_BD >=", value, "lngBd");
            return (Criteria) this;
        }

        public Criteria andLngBdLessThan(BigDecimal value) {
            addCriterion("LNG_BD <", value, "lngBd");
            return (Criteria) this;
        }

        public Criteria andLngBdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LNG_BD <=", value, "lngBd");
            return (Criteria) this;
        }

        public Criteria andLngBdIn(List<BigDecimal> values) {
            addCriterion("LNG_BD in", values, "lngBd");
            return (Criteria) this;
        }

        public Criteria andLngBdNotIn(List<BigDecimal> values) {
            addCriterion("LNG_BD not in", values, "lngBd");
            return (Criteria) this;
        }

        public Criteria andLngBdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LNG_BD between", value1, value2, "lngBd");
            return (Criteria) this;
        }

        public Criteria andLngBdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LNG_BD not between", value1, value2, "lngBd");
            return (Criteria) this;
        }

        public Criteria andLatBdIsNull() {
            addCriterion("LAT_BD is null");
            return (Criteria) this;
        }

        public Criteria andLatBdIsNotNull() {
            addCriterion("LAT_BD is not null");
            return (Criteria) this;
        }

        public Criteria andLatBdEqualTo(BigDecimal value) {
            addCriterion("LAT_BD =", value, "latBd");
            return (Criteria) this;
        }

        public Criteria andLatBdNotEqualTo(BigDecimal value) {
            addCriterion("LAT_BD <>", value, "latBd");
            return (Criteria) this;
        }

        public Criteria andLatBdGreaterThan(BigDecimal value) {
            addCriterion("LAT_BD >", value, "latBd");
            return (Criteria) this;
        }

        public Criteria andLatBdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LAT_BD >=", value, "latBd");
            return (Criteria) this;
        }

        public Criteria andLatBdLessThan(BigDecimal value) {
            addCriterion("LAT_BD <", value, "latBd");
            return (Criteria) this;
        }

        public Criteria andLatBdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LAT_BD <=", value, "latBd");
            return (Criteria) this;
        }

        public Criteria andLatBdIn(List<BigDecimal> values) {
            addCriterion("LAT_BD in", values, "latBd");
            return (Criteria) this;
        }

        public Criteria andLatBdNotIn(List<BigDecimal> values) {
            addCriterion("LAT_BD not in", values, "latBd");
            return (Criteria) this;
        }

        public Criteria andLatBdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LAT_BD between", value1, value2, "latBd");
            return (Criteria) this;
        }

        public Criteria andLatBdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LAT_BD not between", value1, value2, "latBd");
            return (Criteria) this;
        }

        public Criteria andLngGcIsNull() {
            addCriterion("LNG_GC is null");
            return (Criteria) this;
        }

        public Criteria andLngGcIsNotNull() {
            addCriterion("LNG_GC is not null");
            return (Criteria) this;
        }

        public Criteria andLngGcEqualTo(BigDecimal value) {
            addCriterion("LNG_GC =", value, "lngGc");
            return (Criteria) this;
        }

        public Criteria andLngGcNotEqualTo(BigDecimal value) {
            addCriterion("LNG_GC <>", value, "lngGc");
            return (Criteria) this;
        }

        public Criteria andLngGcGreaterThan(BigDecimal value) {
            addCriterion("LNG_GC >", value, "lngGc");
            return (Criteria) this;
        }

        public Criteria andLngGcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LNG_GC >=", value, "lngGc");
            return (Criteria) this;
        }

        public Criteria andLngGcLessThan(BigDecimal value) {
            addCriterion("LNG_GC <", value, "lngGc");
            return (Criteria) this;
        }

        public Criteria andLngGcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LNG_GC <=", value, "lngGc");
            return (Criteria) this;
        }

        public Criteria andLngGcIn(List<BigDecimal> values) {
            addCriterion("LNG_GC in", values, "lngGc");
            return (Criteria) this;
        }

        public Criteria andLngGcNotIn(List<BigDecimal> values) {
            addCriterion("LNG_GC not in", values, "lngGc");
            return (Criteria) this;
        }

        public Criteria andLngGcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LNG_GC between", value1, value2, "lngGc");
            return (Criteria) this;
        }

        public Criteria andLngGcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LNG_GC not between", value1, value2, "lngGc");
            return (Criteria) this;
        }

        public Criteria andLatGcIsNull() {
            addCriterion("LAT_GC is null");
            return (Criteria) this;
        }

        public Criteria andLatGcIsNotNull() {
            addCriterion("LAT_GC is not null");
            return (Criteria) this;
        }

        public Criteria andLatGcEqualTo(BigDecimal value) {
            addCriterion("LAT_GC =", value, "latGc");
            return (Criteria) this;
        }

        public Criteria andLatGcNotEqualTo(BigDecimal value) {
            addCriterion("LAT_GC <>", value, "latGc");
            return (Criteria) this;
        }

        public Criteria andLatGcGreaterThan(BigDecimal value) {
            addCriterion("LAT_GC >", value, "latGc");
            return (Criteria) this;
        }

        public Criteria andLatGcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LAT_GC >=", value, "latGc");
            return (Criteria) this;
        }

        public Criteria andLatGcLessThan(BigDecimal value) {
            addCriterion("LAT_GC <", value, "latGc");
            return (Criteria) this;
        }

        public Criteria andLatGcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LAT_GC <=", value, "latGc");
            return (Criteria) this;
        }

        public Criteria andLatGcIn(List<BigDecimal> values) {
            addCriterion("LAT_GC in", values, "latGc");
            return (Criteria) this;
        }

        public Criteria andLatGcNotIn(List<BigDecimal> values) {
            addCriterion("LAT_GC not in", values, "latGc");
            return (Criteria) this;
        }

        public Criteria andLatGcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LAT_GC between", value1, value2, "latGc");
            return (Criteria) this;
        }

        public Criteria andLatGcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LAT_GC not between", value1, value2, "latGc");
            return (Criteria) this;
        }

        public Criteria andLngIsNull() {
            addCriterion("LNG is null");
            return (Criteria) this;
        }

        public Criteria andLngIsNotNull() {
            addCriterion("LNG is not null");
            return (Criteria) this;
        }

        public Criteria andLngEqualTo(BigDecimal value) {
            addCriterion("LNG =", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotEqualTo(BigDecimal value) {
            addCriterion("LNG <>", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThan(BigDecimal value) {
            addCriterion("LNG >", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LNG >=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThan(BigDecimal value) {
            addCriterion("LNG <", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LNG <=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngIn(List<BigDecimal> values) {
            addCriterion("LNG in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotIn(List<BigDecimal> values) {
            addCriterion("LNG not in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LNG between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LNG not between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLatIsNull() {
            addCriterion("LAT is null");
            return (Criteria) this;
        }

        public Criteria andLatIsNotNull() {
            addCriterion("LAT is not null");
            return (Criteria) this;
        }

        public Criteria andLatEqualTo(BigDecimal value) {
            addCriterion("LAT =", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotEqualTo(BigDecimal value) {
            addCriterion("LAT <>", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThan(BigDecimal value) {
            addCriterion("LAT >", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LAT >=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThan(BigDecimal value) {
            addCriterion("LAT <", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LAT <=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatIn(List<BigDecimal> values) {
            addCriterion("LAT in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotIn(List<BigDecimal> values) {
            addCriterion("LAT not in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LAT between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LAT not between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("ADDRESS =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("ADDRESS <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("ADDRESS >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("ADDRESS <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("ADDRESS like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("ADDRESS not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("ADDRESS in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("ADDRESS not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("ADDRESS between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("ADDRESS not between", value1, value2, "address");
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