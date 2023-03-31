package com.diditech.iov.gps.app.core.aspect;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.api.core.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 日志切面
 * @author zhjd <br>
 * @date 2020/11/30 <br>
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
    }

    @Before("controller()")
    public void logBefore(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        log.info("-> {}: {}.{}(), params={}",
                null != RequestHelper.getRequest() ? RequestHelper.getRequest().getServletPath() : StrUtil.EMPTY,
                className, methodName, JSON.toJSONString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "controller()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        String returnValue = this.getValue(result);
        ResponseMessage rm;
        try {
            rm = JSON.parseObject(returnValue, ResponseMessage.class);
        } catch (Exception ex) {
            rm = null;
        }
        String returnString;
        if (rm == null) {
            returnString = returnValue;
        } else {
            returnString = (rm.getData() instanceof List) && !((List) rm.getData()).isEmpty() ?
                    ((List) rm.getData()).size() + "(size)" : returnValue;
        }
        log.info("<- {}: {}.{}(), result={}",
                null != RequestHelper.getRequest() ? RequestHelper.getRequest().getServletPath() : StrUtil.EMPTY,
                className, methodName, returnString);
    }

    @AfterThrowing(pointcut = "controller() ", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
        log.error("Cause : {}", exception.getMessage());
    }

    private String getValue(Object result) {
        return null == result ? StrUtil.EMPTY : JSON.toJSONString(result);
    }

}
