package com.hello.starter.web.handler;

import com.hello.common.core.exception.BusinessException;
import com.hello.common.core.pojo.R;
import com.hello.common.core.util.MessageResourceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局一场处理器
 *
 * @author hellor
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     *
     * @param e 业务异常
     * @return 错误信息响应
     */
    @ExceptionHandler(BusinessException.class)
    public R<Void> handleException(BusinessException e) {
        log.error("业务异常: {}", e.getMessage(), e);
        return R.fail(e.getCode(), e.getMessage());
    }

    /**
     * 参数校验异常处理（@Valid 校验失败）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<Void> handleValidationException(MethodArgumentNotValidException ex) {
        FieldError firstError = ex.getBindingResult().getFieldErrors().stream().findFirst().orElse(null);
        String errorMessage = "未知参数错误";

        if (firstError != null) {
            errorMessage = MessageResourceUtils.format(firstError.getDefaultMessage(), firstError.getField());
        }

        log.warn("参数校验失败: {}", errorMessage, ex);

        return R.fail(HttpStatus.BAD_REQUEST.value(), errorMessage);
    }

    /**
     * 所有未捕获的异常兜底处理
     */
    @ExceptionHandler(Exception.class)
    public R<Void> handleUnexpectedException(Exception ex) {
        log.error("未知异常: ", ex);
        return R.fail(ex.getMessage());
    }
}
