package com.iov.gps.gateway.api;

/**
 * 封装API的错误码
 * @author hefan
 * @date 2020/8/18 10:23
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
