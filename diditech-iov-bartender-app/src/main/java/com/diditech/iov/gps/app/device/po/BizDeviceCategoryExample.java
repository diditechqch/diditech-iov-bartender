package com.diditech.iov.gps.app.device.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BizDeviceCategoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizDeviceCategoryExample() {
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

        public Criteria andCategoryNoIsNull() {
            addCriterion("CATEGORY_NO is null");
            return (Criteria) this;
        }

        public Criteria andCategoryNoIsNotNull() {
            addCriterion("CATEGORY_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryNoEqualTo(String value) {
            addCriterion("CATEGORY_NO =", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoNotEqualTo(String value) {
            addCriterion("CATEGORY_NO <>", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoGreaterThan(String value) {
            addCriterion("CATEGORY_NO >", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoGreaterThanOrEqualTo(String value) {
            addCriterion("CATEGORY_NO >=", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoLessThan(String value) {
            addCriterion("CATEGORY_NO <", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoLessThanOrEqualTo(String value) {
            addCriterion("CATEGORY_NO <=", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoLike(String value) {
            addCriterion("CATEGORY_NO like", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoNotLike(String value) {
            addCriterion("CATEGORY_NO not like", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoIn(List<String> values) {
            addCriterion("CATEGORY_NO in", values, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoNotIn(List<String> values) {
            addCriterion("CATEGORY_NO not in", values, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoBetween(String value1, String value2) {
            addCriterion("CATEGORY_NO between", value1, value2, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoNotBetween(String value1, String value2) {
            addCriterion("CATEGORY_NO not between", value1, value2, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoShortIsNull() {
            addCriterion("CATEGORY_NO_SHORT is null");
            return (Criteria) this;
        }

        public Criteria andCategoryNoShortIsNotNull() {
            addCriterion("CATEGORY_NO_SHORT is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryNoShortEqualTo(String value) {
            addCriterion("CATEGORY_NO_SHORT =", value, "categoryNoShort");
            return (Criteria) this;
        }

        public Criteria andCategoryNoShortNotEqualTo(String value) {
            addCriterion("CATEGORY_NO_SHORT <>", value, "categoryNoShort");
            return (Criteria) this;
        }

        public Criteria andCategoryNoShortGreaterThan(String value) {
            addCriterion("CATEGORY_NO_SHORT >", value, "categoryNoShort");
            return (Criteria) this;
        }

        public Criteria andCategoryNoShortGreaterThanOrEqualTo(String value) {
            addCriterion("CATEGORY_NO_SHORT >=", value, "categoryNoShort");
            return (Criteria) this;
        }

        public Criteria andCategoryNoShortLessThan(String value) {
            addCriterion("CATEGORY_NO_SHORT <", value, "categoryNoShort");
            return (Criteria) this;
        }

        public Criteria andCategoryNoShortLessThanOrEqualTo(String value) {
            addCriterion("CATEGORY_NO_SHORT <=", value, "categoryNoShort");
            return (Criteria) this;
        }

        public Criteria andCategoryNoShortLike(String value) {
            addCriterion("CATEGORY_NO_SHORT like", value, "categoryNoShort");
            return (Criteria) this;
        }

        public Criteria andCategoryNoShortNotLike(String value) {
            addCriterion("CATEGORY_NO_SHORT not like", value, "categoryNoShort");
            return (Criteria) this;
        }

        public Criteria andCategoryNoShortIn(List<String> values) {
            addCriterion("CATEGORY_NO_SHORT in", values, "categoryNoShort");
            return (Criteria) this;
        }

        public Criteria andCategoryNoShortNotIn(List<String> values) {
            addCriterion("CATEGORY_NO_SHORT not in", values, "categoryNoShort");
            return (Criteria) this;
        }

        public Criteria andCategoryNoShortBetween(String value1, String value2) {
            addCriterion("CATEGORY_NO_SHORT between", value1, value2, "categoryNoShort");
            return (Criteria) this;
        }

        public Criteria andCategoryNoShortNotBetween(String value1, String value2) {
            addCriterion("CATEGORY_NO_SHORT not between", value1, value2, "categoryNoShort");
            return (Criteria) this;
        }

        public Criteria andMileageTypeIsNull() {
            addCriterion("MILEAGE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andMileageTypeIsNotNull() {
            addCriterion("MILEAGE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMileageTypeEqualTo(Byte value) {
            addCriterion("MILEAGE_TYPE =", value, "mileageType");
            return (Criteria) this;
        }

        public Criteria andMileageTypeNotEqualTo(Byte value) {
            addCriterion("MILEAGE_TYPE <>", value, "mileageType");
            return (Criteria) this;
        }

        public Criteria andMileageTypeGreaterThan(Byte value) {
            addCriterion("MILEAGE_TYPE >", value, "mileageType");
            return (Criteria) this;
        }

        public Criteria andMileageTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("MILEAGE_TYPE >=", value, "mileageType");
            return (Criteria) this;
        }

        public Criteria andMileageTypeLessThan(Byte value) {
            addCriterion("MILEAGE_TYPE <", value, "mileageType");
            return (Criteria) this;
        }

        public Criteria andMileageTypeLessThanOrEqualTo(Byte value) {
            addCriterion("MILEAGE_TYPE <=", value, "mileageType");
            return (Criteria) this;
        }

        public Criteria andMileageTypeIn(List<Byte> values) {
            addCriterion("MILEAGE_TYPE in", values, "mileageType");
            return (Criteria) this;
        }

        public Criteria andMileageTypeNotIn(List<Byte> values) {
            addCriterion("MILEAGE_TYPE not in", values, "mileageType");
            return (Criteria) this;
        }

        public Criteria andMileageTypeBetween(Byte value1, Byte value2) {
            addCriterion("MILEAGE_TYPE between", value1, value2, "mileageType");
            return (Criteria) this;
        }

        public Criteria andMileageTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("MILEAGE_TYPE not between", value1, value2, "mileageType");
            return (Criteria) this;
        }

        public Criteria andObdFlagIsNull() {
            addCriterion("OBD_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andObdFlagIsNotNull() {
            addCriterion("OBD_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andObdFlagEqualTo(Byte value) {
            addCriterion("OBD_FLAG =", value, "obdFlag");
            return (Criteria) this;
        }

        public Criteria andObdFlagNotEqualTo(Byte value) {
            addCriterion("OBD_FLAG <>", value, "obdFlag");
            return (Criteria) this;
        }

        public Criteria andObdFlagGreaterThan(Byte value) {
            addCriterion("OBD_FLAG >", value, "obdFlag");
            return (Criteria) this;
        }

        public Criteria andObdFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("OBD_FLAG >=", value, "obdFlag");
            return (Criteria) this;
        }

        public Criteria andObdFlagLessThan(Byte value) {
            addCriterion("OBD_FLAG <", value, "obdFlag");
            return (Criteria) this;
        }

        public Criteria andObdFlagLessThanOrEqualTo(Byte value) {
            addCriterion("OBD_FLAG <=", value, "obdFlag");
            return (Criteria) this;
        }

        public Criteria andObdFlagIn(List<Byte> values) {
            addCriterion("OBD_FLAG in", values, "obdFlag");
            return (Criteria) this;
        }

        public Criteria andObdFlagNotIn(List<Byte> values) {
            addCriterion("OBD_FLAG not in", values, "obdFlag");
            return (Criteria) this;
        }

        public Criteria andObdFlagBetween(Byte value1, Byte value2) {
            addCriterion("OBD_FLAG between", value1, value2, "obdFlag");
            return (Criteria) this;
        }

        public Criteria andObdFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("OBD_FLAG not between", value1, value2, "obdFlag");
            return (Criteria) this;
        }

        public Criteria andWifiFlagIsNull() {
            addCriterion("WIFI_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andWifiFlagIsNotNull() {
            addCriterion("WIFI_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andWifiFlagEqualTo(Byte value) {
            addCriterion("WIFI_FLAG =", value, "wifiFlag");
            return (Criteria) this;
        }

        public Criteria andWifiFlagNotEqualTo(Byte value) {
            addCriterion("WIFI_FLAG <>", value, "wifiFlag");
            return (Criteria) this;
        }

        public Criteria andWifiFlagGreaterThan(Byte value) {
            addCriterion("WIFI_FLAG >", value, "wifiFlag");
            return (Criteria) this;
        }

        public Criteria andWifiFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("WIFI_FLAG >=", value, "wifiFlag");
            return (Criteria) this;
        }

        public Criteria andWifiFlagLessThan(Byte value) {
            addCriterion("WIFI_FLAG <", value, "wifiFlag");
            return (Criteria) this;
        }

        public Criteria andWifiFlagLessThanOrEqualTo(Byte value) {
            addCriterion("WIFI_FLAG <=", value, "wifiFlag");
            return (Criteria) this;
        }

        public Criteria andWifiFlagIn(List<Byte> values) {
            addCriterion("WIFI_FLAG in", values, "wifiFlag");
            return (Criteria) this;
        }

        public Criteria andWifiFlagNotIn(List<Byte> values) {
            addCriterion("WIFI_FLAG not in", values, "wifiFlag");
            return (Criteria) this;
        }

        public Criteria andWifiFlagBetween(Byte value1, Byte value2) {
            addCriterion("WIFI_FLAG between", value1, value2, "wifiFlag");
            return (Criteria) this;
        }

        public Criteria andWifiFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("WIFI_FLAG not between", value1, value2, "wifiFlag");
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