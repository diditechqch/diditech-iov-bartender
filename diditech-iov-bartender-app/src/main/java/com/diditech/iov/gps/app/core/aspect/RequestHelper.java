package com.diditech.iov.gps.app.core.aspect;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * request工具类
 * @author zhjd
 * @date 2021/2/23
 */
public class RequestHelper {

    public static final String CLIENT_INFO = "client";
    public static final String INNER_REQUEST_FLAG = "inner";

    private RequestHelper() {
    }

    public static HttpServletRequest getRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        if (null == ra) {
            return null;
        }
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        return sra.getRequest();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getAttribute(String name) {
        HttpServletRequest request = getRequest();
        if (null == request) {
            return null;
        }
        return (T) getRequest().getAttribute(name);
    }

    public static String getClientId() {
        if (getRequest() == null) {
            return null;
        }
        String clientInfo = getRequest().getHeader(CLIENT_INFO);
        JSONObject jsonObject = null;
        if (StrUtil.isNotBlank(clientInfo)) {
            jsonObject = JSON.parseObject(getRequest().getHeader(CLIENT_INFO));
        }
        if (jsonObject != null) {
            return Convert.toStr(jsonObject.get("client_id"));
        }
        return null;
    }

    /**
     * 属于白名单IP的，gateway在HEADER添加标识，此处判断标识
     * @date 2021/7/16
     * @author zhjd
     */
    public static boolean isInner() {
        if (getRequest() == null) {
            return false;
        }
        return StrUtil.equals(getRequest().getHeader(INNER_REQUEST_FLAG), StrUtil.BACKSLASH);
    }
}
