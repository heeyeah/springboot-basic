package com.hee.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.reflect.Method;


public class CustomUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(CustomUncaughtExceptionHandler.class);

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {

        log.error("{}", throwable.getMessage());
        log.error("{}", method.getDeclaringClass());

        for (Object object : objects) {
            log.error("{}", object);
        }
        if (throwable instanceof BasicRuntimeException) {
            BasicRuntimeException basicRuntimeException = (BasicRuntimeException) throwable;
            log.error(" async thread에서 에러가 났는데, BasicRuntimeException 타입일 경우 : {}", basicRuntimeException.toString());
        } else if (throwable instanceof BasicException) {
            BasicException basicException = (BasicException) throwable;
            log.error(" async thread에서 에러가 났는데, BasicException 타입일 경우 : {}", basicException.toString());
        } else if (throwable instanceof HttpClientErrorException) {
            HttpClientErrorException httpClientErrorException = (HttpClientErrorException) throwable;
            log.error(" async thread에서 에러가 났는데, HttpClientErrorException 타입일 경우 : {}", httpClientErrorException.toString());
        }

        throwable.printStackTrace();
    }
}
