package com.hello.common.core.exception;

import com.hello.common.core.util.MessageResourceUtils;
import lombok.Getter;

/**
 * 业务异常
 *
 * @author hellor
 */
@Getter
public class BusinessException extends RuntimeException {

    private final int code;

    /**
     * 构造函数 - 本地构造i18n错误信息
     *
     * @param code     错误码
     * @param i18nCode i18n错误码
     * @param args     参数
     */
    public BusinessException(int code, String i18nCode, Object... args) {
        super(MessageResourceUtils.format(i18nCode, args));
        this.code = code;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
}
