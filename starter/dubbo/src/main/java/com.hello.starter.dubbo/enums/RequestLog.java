package com.hello.starter.dubbo.enums;

import lombok.AllArgsConstructor;

/**
 * 请求日志泛型
 *
 * @author hellor
 */
@AllArgsConstructor
public enum RequestLog {

    /**
     * info 基础信息
     */
    INFO,

    /**
     * param 参数信息
     */
    PARAM,

    /**
     * full 全部
     */
    FULL;

}

