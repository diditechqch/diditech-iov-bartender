package com.diditech.iov.gps.common.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author zhjd <br>
 * @date 2021/3/5 <br>
 */
public class ResponseMessage implements Serializable {
    private static final long serialVersionUID = 8992436576262574064L;
    private boolean success;
    private Object data;
    private String message;
    private int code;
    private transient Map<Class<?>, Set<String>> includes = new HashMap();
    private transient Map<Class<?>, Set<String>> excludes;
    private transient boolean onlyData;
    private transient String callback;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap();
        map.put("success", this.success);
        if (this.data != null) {
            map.put("data", this.getData());
        }

        if (this.message != null) {
            map.put("message", this.getMessage());
        }

        map.put("code", this.getCode());
        return map;
    }

    protected ResponseMessage() {
    }

    protected ResponseMessage(String message) {
        this.code = 500;
        this.message = message;
        this.success = false;
    }

    protected ResponseMessage(boolean success, Object data) {
        this.code = success ? 200 : 500;
        this.data = data;
        this.success = success;
    }

    protected ResponseMessage(boolean success, Object data, Object message) {
        this.code = success ? 200 : 500;
        this.data = data;
        this.success = success;
        this.message = (String) message;
    }

    protected ResponseMessage(boolean success, Object data, int code) {
        this(success, data);
        this.code = code;
    }

    public ResponseMessage include(Class<?> type, String... fields) {
        return this.include(type, Arrays.asList(fields));
    }

    public ResponseMessage include(Class<?> type, Collection<String> fields) {
        if (this.includes == null) {
            this.includes = new HashMap();
        }

        if (fields != null && !fields.isEmpty()) {
            fields.forEach((field) -> {
                if (field.contains(".")) {
                    String[] tmp = field.split("[.]", 2);

                    try {
                        Field field1 = type.getDeclaredField(tmp[0]);
                        if (field1 != null) {
                            this.include(field1.getType(), tmp[1]);
                        }
                    } catch (Throwable var5) {
                    }
                } else {
                    this.getStringListFormMap(this.includes, type).add(field);
                }

            });
            return this;
        } else {
            return this;
        }
    }

    public ResponseMessage exclude(Class<?> type, Collection<String> fields) {
        if (this.excludes == null) {
            this.excludes = new HashMap();
        }

        if (fields != null && !fields.isEmpty()) {
            fields.forEach((field) -> {
                if (field.contains(".")) {
                    String[] tmp = field.split("[.]", 2);

                    try {
                        Field field1 = type.getDeclaredField(tmp[0]);
                        if (field1 != null) {
                            this.exclude(field1.getType(), tmp[1]);
                        }
                    } catch (Throwable var5) {
                    }
                } else {
                    this.getStringListFormMap(this.excludes, type).add(field);
                }

            });
            return this;
        } else {
            return this;
        }
    }

    public ResponseMessage exclude(Collection<String> fields) {
        if (this.excludes == null) {
            this.excludes = new HashMap();
        }

        if (fields != null && !fields.isEmpty()) {
            if (this.data != null) {
                Class<?> type = this.data.getClass();
                this.exclude(type, fields);
                return this;
            } else {
                return this;
            }
        } else {
            return this;
        }
    }

    public ResponseMessage include(Collection<String> fields) {
        if (this.includes == null) {
            this.includes = new HashMap();
        }

        if (fields != null && !fields.isEmpty()) {
            if (this.data != null) {
                Class<?> type = this.data.getClass();
                this.include(type, fields);
                return this;
            } else {
                return this;
            }
        } else {
            return this;
        }
    }

    public ResponseMessage exclude(Class<?> type, String... fields) {
        return this.exclude(type, Arrays.asList(fields));
    }

    public ResponseMessage exclude(String... fields) {
        return this.exclude(Arrays.asList(fields));
    }

    public ResponseMessage include(String... fields) {
        return this.include(Arrays.asList(fields));
    }

    protected Set<String> getStringListFormMap(Map<Class<?>, Set<String>> map, Class<?> type) {
        Set<String> list = map.computeIfAbsent(type, k -> new HashSet());

        return list;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return this.data;
    }

    public ResponseMessage setData(Object data) {
        this.data = data;
        return this;
    }

    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[0]);
    }

    public int getCode() {
        return this.code;
    }

    public ResponseMessage setCode(int code) {
        this.code = code;
        return this;
    }

    public static ResponseMessage fromJson(String json) {
        return (ResponseMessage) JSON.parseObject(json, ResponseMessage.class);
    }

    public Map<Class<?>, Set<String>> getExcludes() {
        return this.excludes;
    }

    public Map<Class<?>, Set<String>> getIncludes() {
        return this.includes;
    }

    public ResponseMessage onlyData() {
        this.setOnlyData(true);
        return this;
    }

    public void setOnlyData(boolean onlyData) {
        this.onlyData = onlyData;
    }

    public boolean isOnlyData() {
        return this.onlyData;
    }

    public ResponseMessage callback(String callback) {
        this.callback = callback;
        return this;
    }

    public String getCallback() {
        return this.callback;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResponseMessage ok() {
        return ok((Object) null);
    }

    public static ResponseMessage created(Object data) {
        return new ResponseMessage(true, data, 201);
    }

    public static ResponseMessage ok(Object data) {
        return new ResponseMessage(true, data);
    }

    public static ResponseMessage error(String message) {
        return new ResponseMessage(message);
    }

    public static ResponseMessage error(String message, int code) {
        return (new ResponseMessage(message)).setCode(code);
    }
}

