package com.diditech.iov.gps.api.core;

/**
 *
 * @ClassName: BusinessException
 * @Description: 业务异常，用于抛出给前端提示错误信息
 * @author Robin
 * @date 2017年3月8日 下午1:37:06
 *
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -695542791928644578L;
    private int status;

    public BusinessException(String message) {
        this(message, 400);
    }

    public BusinessException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BusinessException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}