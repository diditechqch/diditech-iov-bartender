package com.diditech.iov.gps.app.report.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RptDayGpsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RptDayGpsExample() {
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

        public Criteria andGpsTimeIsNull() {
            addCriterion("GPS_TIME is null");
            return (Criteria) this;
        }

        public Criteria andGpsTimeIsNotNull() {
            addCriterion("GPS_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andGpsTimeEqualTo(Date value) {
            addCriterion("GPS_TIME =", value, "gpsTime");
            return (Criteria) this;
        }

        public Criteria andGpsTimeNotEqualTo(Date value) {
            addCriterion("GPS_TIME <>", value, "gpsTime");
            return (Criteria) this;
        }

        public Criteria andGpsTimeGreaterThan(Date value) {
            addCriterion("GPS_TIME >", value, "gpsTime");
            return (Criteria) this;
        }

        public Criteria andGpsTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("GPS_TIME >=", value, "gpsTime");
            return (Criteria) this;
        }

        public Criteria andGpsTimeLessThan(Date value) {
            addCriterion("GPS_TIME <", value, "gpsTime");
            return (Criteria) this;
        }

        public Criteria andGpsTimeLessThanOrEqualTo(Date value) {
            addCriterion("GPS_TIME <=", value, "gpsTime");
            return (Criteria) this;
        }

        public Criteria andGpsTimeIn(List<Date> values) {
            addCriterion("GPS_TIME in", values, "gpsTime");
            return (Criteria) this;
        }

        public Criteria andGpsTimeNotIn(List<Date> values) {
            addCriterion("GPS_TIME not in", values, "gpsTime");
            return (Criteria) this;
        }

        public Criteria andGpsTimeBetween(Date value1, Date value2) {
            addCriterion("GPS_TIME between", value1, value2, "gpsTime");
            return (Criteria) this;
        }

        public Criteria andGpsTimeNotBetween(Date value1, Date value2) {
            addCriterion("GPS_TIME not between", value1, value2, "gpsTime");
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

        public Criteria andDayMileageIsNull() {
            addCriterion("DAY_MILEAGE is null");
            return (Criteria) this;
        }

        public Criteria andDayMileageIsNotNull() {
            addCriterion("DAY_MILEAGE is not null");
            return (Criteria) this;
        }

        public Criteria andDayMileageEqualTo(BigDecimal value) {
            addCriterion("DAY_MILEAGE =", value, "dayMileage");
            return (Criteria) this;
        }

        public Criteria andDayMileageNotEqualTo(BigDecimal value) {
            addCriterion("DAY_MILEAGE <>", value, "dayMileage");
            return (Criteria) this;
        }

        public Criteria andDayMileageGreaterThan(BigDecimal value) {
            addCriterion("DAY_MILEAGE >", value, "dayMileage");
            return (Criteria) this;
        }

        public Criteria andDayMileageGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DAY_MILEAGE >=", value, "dayMileage");
            return (Criteria) this;
        }

        public Criteria andDayMileageLessThan(BigDecimal value) {
            addCriterion("DAY_MILEAGE <", value, "dayMileage");
            return (Criteria) this;
        }

        public Criteria andDayMileageLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DAY_MILEAGE <=", value, "dayMileage");
            return (Criteria) this;
        }

        public Criteria andDayMileageIn(List<BigDecimal> values) {
            addCriterion("DAY_MILEAGE in", values, "dayMileage");
            return (Criteria) this;
        }

        public Criteria andDayMileageNotIn(List<BigDecimal> values) {
            addCriterion("DAY_MILEAGE not in", values, "dayMileage");
            return (Criteria) this;
        }

        public Criteria andDayMileageBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DAY_MILEAGE between", value1, value2, "dayMileage");
            return (Criteria) this;
        }

        public Criteria andDayMileageNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DAY_MILEAGE not between", value1, value2, "dayMileage");
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

        public Criteria andIsmovingIsNull() {
            addCriterion("ISMOVING is null");
            return (Criteria) this;
        }

        public Criteria andIsmovingIsNotNull() {
            addCriterion("ISMOVING is not null");
            return (Criteria) this;
        }

        public Criteria andIsmovingEqualTo(Byte value) {
            addCriterion("ISMOVING =", value, "ismoving");
            return (Criteria) this;
        }

        public Criteria andIsmovingNotEqualTo(Byte value) {
            addCriterion("ISMOVING <>", value, "ismoving");
            return (Criteria) this;
        }

        public Criteria andIsmovingGreaterThan(Byte value) {
            addCriterion("ISMOVING >", value, "ismoving");
            return (Criteria) this;
        }

        public Criteria andIsmovingGreaterThanOrEqualTo(Byte value) {
            addCriterion("ISMOVING >=", value, "ismoving");
            return (Criteria) this;
        }

        public Criteria andIsmovingLessThan(Byte value) {
            addCriterion("ISMOVING <", value, "ismoving");
            return (Criteria) this;
        }

        public Criteria andIsmovingLessThanOrEqualTo(Byte value) {
            addCriterion("ISMOVING <=", value, "ismoving");
            return (Criteria) this;
        }

        public Criteria andIsmovingIn(List<Byte> values) {
            addCriterion("ISMOVING in", values, "ismoving");
            return (Criteria) this;
        }

        public Criteria andIsmovingNotIn(List<Byte> values) {
            addCriterion("ISMOVING not in", values, "ismoving");
            return (Criteria) this;
        }

        public Criteria andIsmovingBetween(Byte value1, Byte value2) {
            addCriterion("ISMOVING between", value1, value2, "ismoving");
            return (Criteria) this;
        }

        public Criteria andIsmovingNotBetween(Byte value1, Byte value2) {
            addCriterion("ISMOVING not between", value1, value2, "ismoving");
            return (Criteria) this;
        }

        public Criteria andAlmAcconIsNull() {
            addCriterion("ALM_ACCON is null");
            return (Criteria) this;
        }

        public Criteria andAlmAcconIsNotNull() {
            addCriterion("ALM_ACCON is not null");
            return (Criteria) this;
        }

        public Criteria andAlmAcconEqualTo(Byte value) {
            addCriterion("ALM_ACCON =", value, "almAccon");
            return (Criteria) this;
        }

        public Criteria andAlmAcconNotEqualTo(Byte value) {
            addCriterion("ALM_ACCON <>", value, "almAccon");
            return (Criteria) this;
        }

        public Criteria andAlmAcconGreaterThan(Byte value) {
            addCriterion("ALM_ACCON >", value, "almAccon");
            return (Criteria) this;
        }

        public Criteria andAlmAcconGreaterThanOrEqualTo(Byte value) {
            addCriterion("ALM_ACCON >=", value, "almAccon");
            return (Criteria) this;
        }

        public Criteria andAlmAcconLessThan(Byte value) {
            addCriterion("ALM_ACCON <", value, "almAccon");
            return (Criteria) this;
        }

        public Criteria andAlmAcconLessThanOrEqualTo(Byte value) {
            addCriterion("ALM_ACCON <=", value, "almAccon");
            return (Criteria) this;
        }

        public Criteria andAlmAcconIn(List<Byte> values) {
            addCriterion("ALM_ACCON in", values, "almAccon");
            return (Criteria) this;
        }

        public Criteria andAlmAcconNotIn(List<Byte> values) {
            addCriterion("ALM_ACCON not in", values, "almAccon");
            return (Criteria) this;
        }

        public Criteria andAlmAcconBetween(Byte value1, Byte value2) {
            addCriterion("ALM_ACCON between", value1, value2, "almAccon");
            return (Criteria) this;
        }

        public Criteria andAlmAcconNotBetween(Byte value1, Byte value2) {
            addCriterion("ALM_ACCON not between", value1, value2, "almAccon");
            return (Criteria) this;
        }

        public Criteria andLocModeIsNull() {
            addCriterion("LOC_MODE is null");
            return (Criteria) this;
        }

        public Criteria andLocModeIsNotNull() {
            addCriterion("LOC_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andLocModeEqualTo(Byte value) {
            addCriterion("LOC_MODE =", value, "locMode");
            return (Criteria) this;
        }

        public Criteria andLocModeNotEqualTo(Byte value) {
            addCriterion("LOC_MODE <>", value, "locMode");
            return (Criteria) this;
        }

        public Criteria andLocModeGreaterThan(Byte value) {
            addCriterion("LOC_MODE >", value, "locMode");
            return (Criteria) this;
        }

        public Criteria andLocModeGreaterThanOrEqualTo(Byte value) {
            addCriterion("LOC_MODE >=", value, "locMode");
            return (Criteria) this;
        }

        public Criteria andLocModeLessThan(Byte value) {
            addCriterion("LOC_MODE <", value, "locMode");
            return (Criteria) this;
        }

        public Criteria andLocModeLessThanOrEqualTo(Byte value) {
            addCriterion("LOC_MODE <=", value, "locMode");
            return (Criteria) this;
        }

        public Criteria andLocModeIn(List<Byte> values) {
            addCriterion("LOC_MODE in", values, "locMode");
            return (Criteria) this;
        }

        public Criteria andLocModeNotIn(List<Byte> values) {
            addCriterion("LOC_MODE not in", values, "locMode");
            return (Criteria) this;
        }

        public Criteria andLocModeBetween(Byte value1, Byte value2) {
            addCriterion("LOC_MODE between", value1, value2, "locMode");
            return (Criteria) this;
        }

        public Criteria andLocModeNotBetween(Byte value1, Byte value2) {
            addCriterion("LOC_MODE not between", value1, value2, "locMode");
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

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDirectionNameIsNull() {
            addCriterion("DIRECTION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDirectionNameIsNotNull() {
            addCriterion("DIRECTION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDirectionNameEqualTo(String value) {
            addCriterion("DIRECTION_NAME =", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameNotEqualTo(String value) {
            addCriterion("DIRECTION_NAME <>", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameGreaterThan(String value) {
            addCriterion("DIRECTION_NAME >", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameGreaterThanOrEqualTo(String value) {
            addCriterion("DIRECTION_NAME >=", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameLessThan(String value) {
            addCriterion("DIRECTION_NAME <", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameLessThanOrEqualTo(String value) {
            addCriterion("DIRECTION_NAME <=", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameLike(String value) {
            addCriterion("DIRECTION_NAME like", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameNotLike(String value) {
            addCriterion("DIRECTION_NAME not like", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameIn(List<String> values) {
            addCriterion("DIRECTION_NAME in", values, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameNotIn(List<String> values) {
            addCriterion("DIRECTION_NAME not in", values, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameBetween(String value1, String value2) {
            addCriterion("DIRECTION_NAME between", value1, value2, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameNotBetween(String value1, String value2) {
            addCriterion("DIRECTION_NAME not between", value1, value2, "directionName");
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