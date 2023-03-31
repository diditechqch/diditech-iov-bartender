package com.diditech.iov.gps.app.report.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RptTripsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RptTripsExample() {
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

        public Criteria andDistanceIsNull() {
            addCriterion("DISTANCE is null");
            return (Criteria) this;
        }

        public Criteria andDistanceIsNotNull() {
            addCriterion("DISTANCE is not null");
            return (Criteria) this;
        }

        public Criteria andDistanceEqualTo(BigDecimal value) {
            addCriterion("DISTANCE =", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotEqualTo(BigDecimal value) {
            addCriterion("DISTANCE <>", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThan(BigDecimal value) {
            addCriterion("DISTANCE >", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DISTANCE >=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThan(BigDecimal value) {
            addCriterion("DISTANCE <", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DISTANCE <=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceIn(List<BigDecimal> values) {
            addCriterion("DISTANCE in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotIn(List<BigDecimal> values) {
            addCriterion("DISTANCE not in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DISTANCE between", value1, value2, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DISTANCE not between", value1, value2, "distance");
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

        public Criteria andSpeedMaxIsNull() {
            addCriterion("SPEED_MAX is null");
            return (Criteria) this;
        }

        public Criteria andSpeedMaxIsNotNull() {
            addCriterion("SPEED_MAX is not null");
            return (Criteria) this;
        }

        public Criteria andSpeedMaxEqualTo(Short value) {
            addCriterion("SPEED_MAX =", value, "speedMax");
            return (Criteria) this;
        }

        public Criteria andSpeedMaxNotEqualTo(Short value) {
            addCriterion("SPEED_MAX <>", value, "speedMax");
            return (Criteria) this;
        }

        public Criteria andSpeedMaxGreaterThan(Short value) {
            addCriterion("SPEED_MAX >", value, "speedMax");
            return (Criteria) this;
        }

        public Criteria andSpeedMaxGreaterThanOrEqualTo(Short value) {
            addCriterion("SPEED_MAX >=", value, "speedMax");
            return (Criteria) this;
        }

        public Criteria andSpeedMaxLessThan(Short value) {
            addCriterion("SPEED_MAX <", value, "speedMax");
            return (Criteria) this;
        }

        public Criteria andSpeedMaxLessThanOrEqualTo(Short value) {
            addCriterion("SPEED_MAX <=", value, "speedMax");
            return (Criteria) this;
        }

        public Criteria andSpeedMaxIn(List<Short> values) {
            addCriterion("SPEED_MAX in", values, "speedMax");
            return (Criteria) this;
        }

        public Criteria andSpeedMaxNotIn(List<Short> values) {
            addCriterion("SPEED_MAX not in", values, "speedMax");
            return (Criteria) this;
        }

        public Criteria andSpeedMaxBetween(Short value1, Short value2) {
            addCriterion("SPEED_MAX between", value1, value2, "speedMax");
            return (Criteria) this;
        }

        public Criteria andSpeedMaxNotBetween(Short value1, Short value2) {
            addCriterion("SPEED_MAX not between", value1, value2, "speedMax");
            return (Criteria) this;
        }

        public Criteria andSpeedAveIsNull() {
            addCriterion("SPEED_AVE is null");
            return (Criteria) this;
        }

        public Criteria andSpeedAveIsNotNull() {
            addCriterion("SPEED_AVE is not null");
            return (Criteria) this;
        }

        public Criteria andSpeedAveEqualTo(Short value) {
            addCriterion("SPEED_AVE =", value, "speedAve");
            return (Criteria) this;
        }

        public Criteria andSpeedAveNotEqualTo(Short value) {
            addCriterion("SPEED_AVE <>", value, "speedAve");
            return (Criteria) this;
        }

        public Criteria andSpeedAveGreaterThan(Short value) {
            addCriterion("SPEED_AVE >", value, "speedAve");
            return (Criteria) this;
        }

        public Criteria andSpeedAveGreaterThanOrEqualTo(Short value) {
            addCriterion("SPEED_AVE >=", value, "speedAve");
            return (Criteria) this;
        }

        public Criteria andSpeedAveLessThan(Short value) {
            addCriterion("SPEED_AVE <", value, "speedAve");
            return (Criteria) this;
        }

        public Criteria andSpeedAveLessThanOrEqualTo(Short value) {
            addCriterion("SPEED_AVE <=", value, "speedAve");
            return (Criteria) this;
        }

        public Criteria andSpeedAveIn(List<Short> values) {
            addCriterion("SPEED_AVE in", values, "speedAve");
            return (Criteria) this;
        }

        public Criteria andSpeedAveNotIn(List<Short> values) {
            addCriterion("SPEED_AVE not in", values, "speedAve");
            return (Criteria) this;
        }

        public Criteria andSpeedAveBetween(Short value1, Short value2) {
            addCriterion("SPEED_AVE between", value1, value2, "speedAve");
            return (Criteria) this;
        }

        public Criteria andSpeedAveNotBetween(Short value1, Short value2) {
            addCriterion("SPEED_AVE not between", value1, value2, "speedAve");
            return (Criteria) this;
        }

        public Criteria andFuelConsumptionIsNull() {
            addCriterion("FUEL_CONSUMPTION is null");
            return (Criteria) this;
        }

        public Criteria andFuelConsumptionIsNotNull() {
            addCriterion("FUEL_CONSUMPTION is not null");
            return (Criteria) this;
        }

        public Criteria andFuelConsumptionEqualTo(Integer value) {
            addCriterion("FUEL_CONSUMPTION =", value, "fuelConsumption");
            return (Criteria) this;
        }

        public Criteria andFuelConsumptionNotEqualTo(Integer value) {
            addCriterion("FUEL_CONSUMPTION <>", value, "fuelConsumption");
            return (Criteria) this;
        }

        public Criteria andFuelConsumptionGreaterThan(Integer value) {
            addCriterion("FUEL_CONSUMPTION >", value, "fuelConsumption");
            return (Criteria) this;
        }

        public Criteria andFuelConsumptionGreaterThanOrEqualTo(Integer value) {
            addCriterion("FUEL_CONSUMPTION >=", value, "fuelConsumption");
            return (Criteria) this;
        }

        public Criteria andFuelConsumptionLessThan(Integer value) {
            addCriterion("FUEL_CONSUMPTION <", value, "fuelConsumption");
            return (Criteria) this;
        }

        public Criteria andFuelConsumptionLessThanOrEqualTo(Integer value) {
            addCriterion("FUEL_CONSUMPTION <=", value, "fuelConsumption");
            return (Criteria) this;
        }

        public Criteria andFuelConsumptionIn(List<Integer> values) {
            addCriterion("FUEL_CONSUMPTION in", values, "fuelConsumption");
            return (Criteria) this;
        }

        public Criteria andFuelConsumptionNotIn(List<Integer> values) {
            addCriterion("FUEL_CONSUMPTION not in", values, "fuelConsumption");
            return (Criteria) this;
        }

        public Criteria andFuelConsumptionBetween(Integer value1, Integer value2) {
            addCriterion("FUEL_CONSUMPTION between", value1, value2, "fuelConsumption");
            return (Criteria) this;
        }

        public Criteria andFuelConsumptionNotBetween(Integer value1, Integer value2) {
            addCriterion("FUEL_CONSUMPTION not between", value1, value2, "fuelConsumption");
            return (Criteria) this;
        }

        public Criteria andStartLngBdIsNull() {
            addCriterion("START_LNG_BD is null");
            return (Criteria) this;
        }

        public Criteria andStartLngBdIsNotNull() {
            addCriterion("START_LNG_BD is not null");
            return (Criteria) this;
        }

        public Criteria andStartLngBdEqualTo(BigDecimal value) {
            addCriterion("START_LNG_BD =", value, "startLngBd");
            return (Criteria) this;
        }

        public Criteria andStartLngBdNotEqualTo(BigDecimal value) {
            addCriterion("START_LNG_BD <>", value, "startLngBd");
            return (Criteria) this;
        }

        public Criteria andStartLngBdGreaterThan(BigDecimal value) {
            addCriterion("START_LNG_BD >", value, "startLngBd");
            return (Criteria) this;
        }

        public Criteria andStartLngBdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("START_LNG_BD >=", value, "startLngBd");
            return (Criteria) this;
        }

        public Criteria andStartLngBdLessThan(BigDecimal value) {
            addCriterion("START_LNG_BD <", value, "startLngBd");
            return (Criteria) this;
        }

        public Criteria andStartLngBdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("START_LNG_BD <=", value, "startLngBd");
            return (Criteria) this;
        }

        public Criteria andStartLngBdIn(List<BigDecimal> values) {
            addCriterion("START_LNG_BD in", values, "startLngBd");
            return (Criteria) this;
        }

        public Criteria andStartLngBdNotIn(List<BigDecimal> values) {
            addCriterion("START_LNG_BD not in", values, "startLngBd");
            return (Criteria) this;
        }

        public Criteria andStartLngBdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_LNG_BD between", value1, value2, "startLngBd");
            return (Criteria) this;
        }

        public Criteria andStartLngBdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_LNG_BD not between", value1, value2, "startLngBd");
            return (Criteria) this;
        }

        public Criteria andStartLatBdIsNull() {
            addCriterion("START_LAT_BD is null");
            return (Criteria) this;
        }

        public Criteria andStartLatBdIsNotNull() {
            addCriterion("START_LAT_BD is not null");
            return (Criteria) this;
        }

        public Criteria andStartLatBdEqualTo(BigDecimal value) {
            addCriterion("START_LAT_BD =", value, "startLatBd");
            return (Criteria) this;
        }

        public Criteria andStartLatBdNotEqualTo(BigDecimal value) {
            addCriterion("START_LAT_BD <>", value, "startLatBd");
            return (Criteria) this;
        }

        public Criteria andStartLatBdGreaterThan(BigDecimal value) {
            addCriterion("START_LAT_BD >", value, "startLatBd");
            return (Criteria) this;
        }

        public Criteria andStartLatBdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("START_LAT_BD >=", value, "startLatBd");
            return (Criteria) this;
        }

        public Criteria andStartLatBdLessThan(BigDecimal value) {
            addCriterion("START_LAT_BD <", value, "startLatBd");
            return (Criteria) this;
        }

        public Criteria andStartLatBdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("START_LAT_BD <=", value, "startLatBd");
            return (Criteria) this;
        }

        public Criteria andStartLatBdIn(List<BigDecimal> values) {
            addCriterion("START_LAT_BD in", values, "startLatBd");
            return (Criteria) this;
        }

        public Criteria andStartLatBdNotIn(List<BigDecimal> values) {
            addCriterion("START_LAT_BD not in", values, "startLatBd");
            return (Criteria) this;
        }

        public Criteria andStartLatBdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_LAT_BD between", value1, value2, "startLatBd");
            return (Criteria) this;
        }

        public Criteria andStartLatBdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_LAT_BD not between", value1, value2, "startLatBd");
            return (Criteria) this;
        }

        public Criteria andEndLngBdIsNull() {
            addCriterion("END_LNG_BD is null");
            return (Criteria) this;
        }

        public Criteria andEndLngBdIsNotNull() {
            addCriterion("END_LNG_BD is not null");
            return (Criteria) this;
        }

        public Criteria andEndLngBdEqualTo(BigDecimal value) {
            addCriterion("END_LNG_BD =", value, "endLngBd");
            return (Criteria) this;
        }

        public Criteria andEndLngBdNotEqualTo(BigDecimal value) {
            addCriterion("END_LNG_BD <>", value, "endLngBd");
            return (Criteria) this;
        }

        public Criteria andEndLngBdGreaterThan(BigDecimal value) {
            addCriterion("END_LNG_BD >", value, "endLngBd");
            return (Criteria) this;
        }

        public Criteria andEndLngBdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("END_LNG_BD >=", value, "endLngBd");
            return (Criteria) this;
        }

        public Criteria andEndLngBdLessThan(BigDecimal value) {
            addCriterion("END_LNG_BD <", value, "endLngBd");
            return (Criteria) this;
        }

        public Criteria andEndLngBdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("END_LNG_BD <=", value, "endLngBd");
            return (Criteria) this;
        }

        public Criteria andEndLngBdIn(List<BigDecimal> values) {
            addCriterion("END_LNG_BD in", values, "endLngBd");
            return (Criteria) this;
        }

        public Criteria andEndLngBdNotIn(List<BigDecimal> values) {
            addCriterion("END_LNG_BD not in", values, "endLngBd");
            return (Criteria) this;
        }

        public Criteria andEndLngBdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("END_LNG_BD between", value1, value2, "endLngBd");
            return (Criteria) this;
        }

        public Criteria andEndLngBdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("END_LNG_BD not between", value1, value2, "endLngBd");
            return (Criteria) this;
        }

        public Criteria andEndLatBdIsNull() {
            addCriterion("END_LAT_BD is null");
            return (Criteria) this;
        }

        public Criteria andEndLatBdIsNotNull() {
            addCriterion("END_LAT_BD is not null");
            return (Criteria) this;
        }

        public Criteria andEndLatBdEqualTo(BigDecimal value) {
            addCriterion("END_LAT_BD =", value, "endLatBd");
            return (Criteria) this;
        }

        public Criteria andEndLatBdNotEqualTo(BigDecimal value) {
            addCriterion("END_LAT_BD <>", value, "endLatBd");
            return (Criteria) this;
        }

        public Criteria andEndLatBdGreaterThan(BigDecimal value) {
            addCriterion("END_LAT_BD >", value, "endLatBd");
            return (Criteria) this;
        }

        public Criteria andEndLatBdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("END_LAT_BD >=", value, "endLatBd");
            return (Criteria) this;
        }

        public Criteria andEndLatBdLessThan(BigDecimal value) {
            addCriterion("END_LAT_BD <", value, "endLatBd");
            return (Criteria) this;
        }

        public Criteria andEndLatBdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("END_LAT_BD <=", value, "endLatBd");
            return (Criteria) this;
        }

        public Criteria andEndLatBdIn(List<BigDecimal> values) {
            addCriterion("END_LAT_BD in", values, "endLatBd");
            return (Criteria) this;
        }

        public Criteria andEndLatBdNotIn(List<BigDecimal> values) {
            addCriterion("END_LAT_BD not in", values, "endLatBd");
            return (Criteria) this;
        }

        public Criteria andEndLatBdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("END_LAT_BD between", value1, value2, "endLatBd");
            return (Criteria) this;
        }

        public Criteria andEndLatBdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("END_LAT_BD not between", value1, value2, "endLatBd");
            return (Criteria) this;
        }

        public Criteria andStartLngGcIsNull() {
            addCriterion("START_LNG_GC is null");
            return (Criteria) this;
        }

        public Criteria andStartLngGcIsNotNull() {
            addCriterion("START_LNG_GC is not null");
            return (Criteria) this;
        }

        public Criteria andStartLngGcEqualTo(BigDecimal value) {
            addCriterion("START_LNG_GC =", value, "startLngGc");
            return (Criteria) this;
        }

        public Criteria andStartLngGcNotEqualTo(BigDecimal value) {
            addCriterion("START_LNG_GC <>", value, "startLngGc");
            return (Criteria) this;
        }

        public Criteria andStartLngGcGreaterThan(BigDecimal value) {
            addCriterion("START_LNG_GC >", value, "startLngGc");
            return (Criteria) this;
        }

        public Criteria andStartLngGcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("START_LNG_GC >=", value, "startLngGc");
            return (Criteria) this;
        }

        public Criteria andStartLngGcLessThan(BigDecimal value) {
            addCriterion("START_LNG_GC <", value, "startLngGc");
            return (Criteria) this;
        }

        public Criteria andStartLngGcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("START_LNG_GC <=", value, "startLngGc");
            return (Criteria) this;
        }

        public Criteria andStartLngGcIn(List<BigDecimal> values) {
            addCriterion("START_LNG_GC in", values, "startLngGc");
            return (Criteria) this;
        }

        public Criteria andStartLngGcNotIn(List<BigDecimal> values) {
            addCriterion("START_LNG_GC not in", values, "startLngGc");
            return (Criteria) this;
        }

        public Criteria andStartLngGcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_LNG_GC between", value1, value2, "startLngGc");
            return (Criteria) this;
        }

        public Criteria andStartLngGcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_LNG_GC not between", value1, value2, "startLngGc");
            return (Criteria) this;
        }

        public Criteria andStartLatGcIsNull() {
            addCriterion("START_LAT_GC is null");
            return (Criteria) this;
        }

        public Criteria andStartLatGcIsNotNull() {
            addCriterion("START_LAT_GC is not null");
            return (Criteria) this;
        }

        public Criteria andStartLatGcEqualTo(BigDecimal value) {
            addCriterion("START_LAT_GC =", value, "startLatGc");
            return (Criteria) this;
        }

        public Criteria andStartLatGcNotEqualTo(BigDecimal value) {
            addCriterion("START_LAT_GC <>", value, "startLatGc");
            return (Criteria) this;
        }

        public Criteria andStartLatGcGreaterThan(BigDecimal value) {
            addCriterion("START_LAT_GC >", value, "startLatGc");
            return (Criteria) this;
        }

        public Criteria andStartLatGcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("START_LAT_GC >=", value, "startLatGc");
            return (Criteria) this;
        }

        public Criteria andStartLatGcLessThan(BigDecimal value) {
            addCriterion("START_LAT_GC <", value, "startLatGc");
            return (Criteria) this;
        }

        public Criteria andStartLatGcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("START_LAT_GC <=", value, "startLatGc");
            return (Criteria) this;
        }

        public Criteria andStartLatGcIn(List<BigDecimal> values) {
            addCriterion("START_LAT_GC in", values, "startLatGc");
            return (Criteria) this;
        }

        public Criteria andStartLatGcNotIn(List<BigDecimal> values) {
            addCriterion("START_LAT_GC not in", values, "startLatGc");
            return (Criteria) this;
        }

        public Criteria andStartLatGcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_LAT_GC between", value1, value2, "startLatGc");
            return (Criteria) this;
        }

        public Criteria andStartLatGcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_LAT_GC not between", value1, value2, "startLatGc");
            return (Criteria) this;
        }

        public Criteria andEndLngGcIsNull() {
            addCriterion("END_LNG_GC is null");
            return (Criteria) this;
        }

        public Criteria andEndLngGcIsNotNull() {
            addCriterion("END_LNG_GC is not null");
            return (Criteria) this;
        }

        public Criteria andEndLngGcEqualTo(BigDecimal value) {
            addCriterion("END_LNG_GC =", value, "endLngGc");
            return (Criteria) this;
        }

        public Criteria andEndLngGcNotEqualTo(BigDecimal value) {
            addCriterion("END_LNG_GC <>", value, "endLngGc");
            return (Criteria) this;
        }

        public Criteria andEndLngGcGreaterThan(BigDecimal value) {
            addCriterion("END_LNG_GC >", value, "endLngGc");
            return (Criteria) this;
        }

        public Criteria andEndLngGcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("END_LNG_GC >=", value, "endLngGc");
            return (Criteria) this;
        }

        public Criteria andEndLngGcLessThan(BigDecimal value) {
            addCriterion("END_LNG_GC <", value, "endLngGc");
            return (Criteria) this;
        }

        public Criteria andEndLngGcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("END_LNG_GC <=", value, "endLngGc");
            return (Criteria) this;
        }

        public Criteria andEndLngGcIn(List<BigDecimal> values) {
            addCriterion("END_LNG_GC in", values, "endLngGc");
            return (Criteria) this;
        }

        public Criteria andEndLngGcNotIn(List<BigDecimal> values) {
            addCriterion("END_LNG_GC not in", values, "endLngGc");
            return (Criteria) this;
        }

        public Criteria andEndLngGcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("END_LNG_GC between", value1, value2, "endLngGc");
            return (Criteria) this;
        }

        public Criteria andEndLngGcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("END_LNG_GC not between", value1, value2, "endLngGc");
            return (Criteria) this;
        }

        public Criteria andEndLatGcIsNull() {
            addCriterion("END_LAT_GC is null");
            return (Criteria) this;
        }

        public Criteria andEndLatGcIsNotNull() {
            addCriterion("END_LAT_GC is not null");
            return (Criteria) this;
        }

        public Criteria andEndLatGcEqualTo(BigDecimal value) {
            addCriterion("END_LAT_GC =", value, "endLatGc");
            return (Criteria) this;
        }

        public Criteria andEndLatGcNotEqualTo(BigDecimal value) {
            addCriterion("END_LAT_GC <>", value, "endLatGc");
            return (Criteria) this;
        }

        public Criteria andEndLatGcGreaterThan(BigDecimal value) {
            addCriterion("END_LAT_GC >", value, "endLatGc");
            return (Criteria) this;
        }

        public Criteria andEndLatGcGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("END_LAT_GC >=", value, "endLatGc");
            return (Criteria) this;
        }

        public Criteria andEndLatGcLessThan(BigDecimal value) {
            addCriterion("END_LAT_GC <", value, "endLatGc");
            return (Criteria) this;
        }

        public Criteria andEndLatGcLessThanOrEqualTo(BigDecimal value) {
            addCriterion("END_LAT_GC <=", value, "endLatGc");
            return (Criteria) this;
        }

        public Criteria andEndLatGcIn(List<BigDecimal> values) {
            addCriterion("END_LAT_GC in", values, "endLatGc");
            return (Criteria) this;
        }

        public Criteria andEndLatGcNotIn(List<BigDecimal> values) {
            addCriterion("END_LAT_GC not in", values, "endLatGc");
            return (Criteria) this;
        }

        public Criteria andEndLatGcBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("END_LAT_GC between", value1, value2, "endLatGc");
            return (Criteria) this;
        }

        public Criteria andEndLatGcNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("END_LAT_GC not between", value1, value2, "endLatGc");
            return (Criteria) this;
        }

        public Criteria andStartLngIsNull() {
            addCriterion("START_LNG is null");
            return (Criteria) this;
        }

        public Criteria andStartLngIsNotNull() {
            addCriterion("START_LNG is not null");
            return (Criteria) this;
        }

        public Criteria andStartLngEqualTo(BigDecimal value) {
            addCriterion("START_LNG =", value, "startLng");
            return (Criteria) this;
        }

        public Criteria andStartLngNotEqualTo(BigDecimal value) {
            addCriterion("START_LNG <>", value, "startLng");
            return (Criteria) this;
        }

        public Criteria andStartLngGreaterThan(BigDecimal value) {
            addCriterion("START_LNG >", value, "startLng");
            return (Criteria) this;
        }

        public Criteria andStartLngGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("START_LNG >=", value, "startLng");
            return (Criteria) this;
        }

        public Criteria andStartLngLessThan(BigDecimal value) {
            addCriterion("START_LNG <", value, "startLng");
            return (Criteria) this;
        }

        public Criteria andStartLngLessThanOrEqualTo(BigDecimal value) {
            addCriterion("START_LNG <=", value, "startLng");
            return (Criteria) this;
        }

        public Criteria andStartLngIn(List<BigDecimal> values) {
            addCriterion("START_LNG in", values, "startLng");
            return (Criteria) this;
        }

        public Criteria andStartLngNotIn(List<BigDecimal> values) {
            addCriterion("START_LNG not in", values, "startLng");
            return (Criteria) this;
        }

        public Criteria andStartLngBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_LNG between", value1, value2, "startLng");
            return (Criteria) this;
        }

        public Criteria andStartLngNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_LNG not between", value1, value2, "startLng");
            return (Criteria) this;
        }

        public Criteria andStartLatIsNull() {
            addCriterion("START_LAT is null");
            return (Criteria) this;
        }

        public Criteria andStartLatIsNotNull() {
            addCriterion("START_LAT is not null");
            return (Criteria) this;
        }

        public Criteria andStartLatEqualTo(BigDecimal value) {
            addCriterion("START_LAT =", value, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatNotEqualTo(BigDecimal value) {
            addCriterion("START_LAT <>", value, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatGreaterThan(BigDecimal value) {
            addCriterion("START_LAT >", value, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("START_LAT >=", value, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatLessThan(BigDecimal value) {
            addCriterion("START_LAT <", value, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("START_LAT <=", value, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatIn(List<BigDecimal> values) {
            addCriterion("START_LAT in", values, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatNotIn(List<BigDecimal> values) {
            addCriterion("START_LAT not in", values, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_LAT between", value1, value2, "startLat");
            return (Criteria) this;
        }

        public Criteria andStartLatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_LAT not between", value1, value2, "startLat");
            return (Criteria) this;
        }

        public Criteria andEndLngIsNull() {
            addCriterion("END_LNG is null");
            return (Criteria) this;
        }

        public Criteria andEndLngIsNotNull() {
            addCriterion("END_LNG is not null");
            return (Criteria) this;
        }

        public Criteria andEndLngEqualTo(BigDecimal value) {
            addCriterion("END_LNG =", value, "endLng");
            return (Criteria) this;
        }

        public Criteria andEndLngNotEqualTo(BigDecimal value) {
            addCriterion("END_LNG <>", value, "endLng");
            return (Criteria) this;
        }

        public Criteria andEndLngGreaterThan(BigDecimal value) {
            addCriterion("END_LNG >", value, "endLng");
            return (Criteria) this;
        }

        public Criteria andEndLngGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("END_LNG >=", value, "endLng");
            return (Criteria) this;
        }

        public Criteria andEndLngLessThan(BigDecimal value) {
            addCriterion("END_LNG <", value, "endLng");
            return (Criteria) this;
        }

        public Criteria andEndLngLessThanOrEqualTo(BigDecimal value) {
            addCriterion("END_LNG <=", value, "endLng");
            return (Criteria) this;
        }

        public Criteria andEndLngIn(List<BigDecimal> values) {
            addCriterion("END_LNG in", values, "endLng");
            return (Criteria) this;
        }

        public Criteria andEndLngNotIn(List<BigDecimal> values) {
            addCriterion("END_LNG not in", values, "endLng");
            return (Criteria) this;
        }

        public Criteria andEndLngBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("END_LNG between", value1, value2, "endLng");
            return (Criteria) this;
        }

        public Criteria andEndLngNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("END_LNG not between", value1, value2, "endLng");
            return (Criteria) this;
        }

        public Criteria andEndLatIsNull() {
            addCriterion("END_LAT is null");
            return (Criteria) this;
        }

        public Criteria andEndLatIsNotNull() {
            addCriterion("END_LAT is not null");
            return (Criteria) this;
        }

        public Criteria andEndLatEqualTo(BigDecimal value) {
            addCriterion("END_LAT =", value, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatNotEqualTo(BigDecimal value) {
            addCriterion("END_LAT <>", value, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatGreaterThan(BigDecimal value) {
            addCriterion("END_LAT >", value, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("END_LAT >=", value, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatLessThan(BigDecimal value) {
            addCriterion("END_LAT <", value, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatLessThanOrEqualTo(BigDecimal value) {
            addCriterion("END_LAT <=", value, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatIn(List<BigDecimal> values) {
            addCriterion("END_LAT in", values, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatNotIn(List<BigDecimal> values) {
            addCriterion("END_LAT not in", values, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("END_LAT between", value1, value2, "endLat");
            return (Criteria) this;
        }

        public Criteria andEndLatNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("END_LAT not between", value1, value2, "endLat");
            return (Criteria) this;
        }

        public Criteria andStartAddressIsNull() {
            addCriterion("START_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andStartAddressIsNotNull() {
            addCriterion("START_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andStartAddressEqualTo(String value) {
            addCriterion("START_ADDRESS =", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressNotEqualTo(String value) {
            addCriterion("START_ADDRESS <>", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressGreaterThan(String value) {
            addCriterion("START_ADDRESS >", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressGreaterThanOrEqualTo(String value) {
            addCriterion("START_ADDRESS >=", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressLessThan(String value) {
            addCriterion("START_ADDRESS <", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressLessThanOrEqualTo(String value) {
            addCriterion("START_ADDRESS <=", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressLike(String value) {
            addCriterion("START_ADDRESS like", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressNotLike(String value) {
            addCriterion("START_ADDRESS not like", value, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressIn(List<String> values) {
            addCriterion("START_ADDRESS in", values, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressNotIn(List<String> values) {
            addCriterion("START_ADDRESS not in", values, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressBetween(String value1, String value2) {
            addCriterion("START_ADDRESS between", value1, value2, "startAddress");
            return (Criteria) this;
        }

        public Criteria andStartAddressNotBetween(String value1, String value2) {
            addCriterion("START_ADDRESS not between", value1, value2, "startAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressIsNull() {
            addCriterion("END_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andEndAddressIsNotNull() {
            addCriterion("END_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andEndAddressEqualTo(String value) {
            addCriterion("END_ADDRESS =", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressNotEqualTo(String value) {
            addCriterion("END_ADDRESS <>", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressGreaterThan(String value) {
            addCriterion("END_ADDRESS >", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressGreaterThanOrEqualTo(String value) {
            addCriterion("END_ADDRESS >=", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressLessThan(String value) {
            addCriterion("END_ADDRESS <", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressLessThanOrEqualTo(String value) {
            addCriterion("END_ADDRESS <=", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressLike(String value) {
            addCriterion("END_ADDRESS like", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressNotLike(String value) {
            addCriterion("END_ADDRESS not like", value, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressIn(List<String> values) {
            addCriterion("END_ADDRESS in", values, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressNotIn(List<String> values) {
            addCriterion("END_ADDRESS not in", values, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressBetween(String value1, String value2) {
            addCriterion("END_ADDRESS between", value1, value2, "endAddress");
            return (Criteria) this;
        }

        public Criteria andEndAddressNotBetween(String value1, String value2) {
            addCriterion("END_ADDRESS not between", value1, value2, "endAddress");
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