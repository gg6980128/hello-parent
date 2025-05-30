package com.hello.common.core.util;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 多语言资源工具类
 *
 * @author hellor
 */
public final class MessageResourceUtils {
    private final static MessageSource messageSource = SpringUtil.getBean(MessageSource.class);

    /**
     * 获取多语言资源
     *
     * @param code 资源码
     * @param args 参数
     * @return 资源内容
     */
    public static String format(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
