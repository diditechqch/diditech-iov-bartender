package com.diditech.iov.gps.app.core.aspect;

import com.diditech.iov.gps.api.core.BusinessException;
import com.diditech.iov.gps.api.core.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * Controller层接口校验异常处理
 * @author zhjd <br>
 * @date 2020/12/9 <br>
 */
@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ValidationAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseMessage handleBindException(Exception exception) {
        String uri = RequestHelper.getRequest().getRequestURI();
        String errorMsg = exception.getMessage();

        if (exception instanceof MethodArgumentNotValidException) {
            errorMsg = ((MethodArgumentNotValidException) exception).getBindingResult()
                    .getFieldErrors().stream()
                    .map(item -> item.getField() + item.getDefaultMessage())
                    .collect(Collectors.joining(","));
            log.error("{} caught {}: {}", uri, exception.getClass(), errorMsg);
            return ResponseMessage.error(errorMsg);
        }

        if (exception instanceof BusinessException
                || exception instanceof ClientValidator.ClientException) {
            log.error("{} caught {}: {}", uri, exception.getClass(), errorMsg);
            return ResponseMessage.error(errorMsg);
        }

        if (exception instanceof HttpMessageNotReadableException
                && errorMsg.contains("Required request body is missing")) {
            String displayError = "Body不可为空";
            log.error("{} caught {}: {}", uri, exception.getClass(), errorMsg);
            return ResponseMessage.error(displayError);
        }

        log.error(uri, exception);
        return ResponseMessage.error(errorMsg);
    }

}
