package com.diditech.iov.gps.api.cmd.domain;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 响应信息主体
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;

    private long timestamp;

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, 1, msg);
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, 0, msg);
    }

    public static <T> R<T> failed(T data, String msg) {
        return restResult(data, 0, msg);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}