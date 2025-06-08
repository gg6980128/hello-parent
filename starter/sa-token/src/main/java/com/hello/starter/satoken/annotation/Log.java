package com.hello.starter.satoken.annotation;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 *
 * @author hellor
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseData() default true;


    /**
     * 排除指定的请求参数
     */
    String[] excludeParamNames() default {};

    /**
     * 业务操作类型
     */
    enum BusinessType {
        /**
         * 其它
         */
        OTHER,
        /**
         * 新增
         */
        INSERT,
        /**
         * 修改
         */
        UPDATE,
        /**
         * 删除
         */
        DELETE,
        /**
         * 授权
         */
        GRANT,
        /**
         * 导出
         */
        EXPORT,
        /**
         * 导入
         */
        IMPORT,
        /**
         * 强退
         */
        FORCE,
        /**
         * 生成代码
         */
        GENCODE,
        /**
         * 清空数据
         */
        CLEAN,
    }

    /**
     * 操作人类别
     */
    enum OperatorType {
        /**
         * 其它
         */
        OTHER,
        /**
         * 后台用户
         */
        MANAGE,
        /**
         * 手机端用户
         */
        MOBILE
    }
}

