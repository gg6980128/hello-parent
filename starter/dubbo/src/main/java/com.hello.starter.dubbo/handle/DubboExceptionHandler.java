package com.hello.starter.dubbo.handle;

import com.hello.common.core.pojo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Dubbo异常处理器
 *
 * @author hellor
 */
@Slf4j
@RestControllerAdvice
public class DubboExceptionHandler {

    /**
     * 主键或UNIQUE索引，数据重复异常
     */
    @ExceptionHandler(RpcException.class)
    public R<Void> handleDubboException(RpcException e) {
        log.error("RPC异常: {}", e.getMessage());
        return R.fail("RPC异常，请联系管理员确认");
    }

}

