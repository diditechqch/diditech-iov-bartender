package com.diditech.iov.gps.app.core.aspect;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.diditech.iov.gps.api.trace.entity.CoordinateType;
import com.diditech.iov.gps.app.core.config.DateConverter;
import com.diditech.iov.gps.app.core.service.CoreService;
import com.diditech.iov.gps.app.core.util.Const;
import com.diditech.iov.gps.app.device.service.DeviceService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 共通客户端校验
 * @author zhjd <br>
 * @date 2021/2/23 <br>
 */
@Slf4j
@Aspect
@Component
public class ClientValidator {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private CoreService coreService;

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
    }

    /**
     * 当请求参数含有设备号时，校验该客户端是否有权限访问
     * @date 2021/2/23
     * @author zhjd
     */
    @SneakyThrows
    @Before("controller()")
    public void logBefore(JoinPoint joinPoint) {
        HttpServletRequest req = RequestHelper.getRequest();
        if (req == null || req.getRequestURI().contains("/opt")) {
            return;
        }
        String deviceNum = req.getParameter("deviceNum");
        if (deviceNum != null) {
            if (deviceNum.trim().length() == 0) {
                throw new ClientException("若传输设备号需使用有效设备号");
            }
            String clientId = RequestHelper.getClientId();
            if (StrUtil.isBlank(clientId)) {
                throw new ClientException("无权限:" + clientId);
            }
            String invalidNum = deviceService.getInvalidDeviceNum(clientId, deviceNum);
            if (StrUtil.isNotBlank(invalidNum)) {
                throw new ClientException("设备号不存在或无权限:" + invalidNum);
            }
        }

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        List<String> parameterNames = Arrays.asList(methodSignature.getParameterNames());
        if (parameterNames.contains("devices")) {
            List<Object> args = Arrays.asList(joinPoint.getArgs());
            Object object = args.get(parameterNames.indexOf("devices"));
            String devices;
            if (object instanceof String[]) {
                devices = String.join(Const.SEP_COMMA, (String[]) object);
            } else if (object instanceof String) {
                devices = object.toString();
            } else {
                return;
            }
            if (devices.trim().length() == 0) {
                throw new ClientException("若传输设备号需使用有效设备号");
            }
            List<String> deviceNumList = Arrays.asList(devices.split(Const.SEP_COMMA));
            if (CollUtil.isEmpty(deviceNumList)) {
                throw new ClientException("若传输设备号需使用有效设备号");
            }
            for (String device : deviceNumList) {
                String invalid = deviceService.getInvalidDeviceNum(RequestHelper.getClientId(), device);
                if (StrUtil.isNotBlank(invalid)) {
                    throw new ClientValidator.ClientException("设备号不存在或无权限:" + invalid);
                }
            }
        }

        Date beginTime = DateConverter.parseDate(req.getParameter("beginTime"));
        Date endTime = DateConverter.parseDate(req.getParameter("endTime"));
        if (beginTime != null && endTime != null) {
            coreService.dateValidation(beginTime, endTime);
        }

        String coorType = req.getParameter("coorType");
        if (coorType != null) {
            CoordinateType type = CoordinateType.get(coorType);
            req.setAttribute("coorType", type.name());
        }
    }

    public static class ClientException extends RuntimeException {
        public ClientException(String message) {
            super(message);
        }
    }
}
