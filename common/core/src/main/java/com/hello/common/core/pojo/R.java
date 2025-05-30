package com.hello.common.core.pojo;

import com.hello.common.core.util.MessageResourceUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.Instant;
import java.util.Locale;

/**
 * 请求响应包装类
 *
 * @param code   状态码
 *               200: 成功
 *               400: 参数错误
 *               401: 未授权
 *               403: 拒绝访问
 *               404: 未找到
 *               500: 服务器错误
 * @param msg    提示信息
 * @param <T>    数据类型
 * @param data   数据
 * @param locale 当前语言
 * @param time   时间
 * @author yase
 */
public record R<T>(int code, String msg, T data, Instant time, Locale locale) {

    /**
     * 响应成功
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return 响应结果
     */
    public static <T> R<T> ok(T data) {
        String msg = MessageResourceUtils.format("R.msg.success");
        return new R<>(200, msg, data, Instant.now(), LocaleContextHolder.getLocale());
    }

    /**
     * 响应成功
     *
     * @return 响应结果
     */
    public static R<Void> ok() {
        return ok(null);
    }

    /**
     * 响应失败
     *
     * @param msg 提示信息
     * @return 响应结果
     */
    public static R<Void> fail(String msg) {
        return new R<>(500, msg, null, Instant.now(), LocaleContextHolder.getLocale());
    }

    /**
     * 响应失败
     *
     * @param code 状态码
     * @param msg  提示信息
     * @return 响应结果
     */
    public static R<Void> fail(int code, String msg) {
        return new R<>(code, msg, null, Instant.now(), LocaleContextHolder.getLocale());
    }
}
