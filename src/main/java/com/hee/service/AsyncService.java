package com.hee.service;

import com.hee.exception.BasicException;
import com.hee.exception.BasicRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;


@Service
public class AsyncService {

    private final Logger log = LoggerFactory.getLogger(AsyncService.class);

    @Async
    public void uncaughtException() {

        String nullableText = null;
        nullableText.trim();
    }

    @Async
    public void asyncException(int exceptionType) throws Exception {

        log.info("hello, world :) {}", LocalDateTime.now().toString());

        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (exceptionType) {
            case 1:
                throw new BasicException("basic exception", "case 1");
            case 2:
                throw new BasicRuntimeException("basic runtime exception", "case 2");
            case 402:
                throw new HttpClientErrorException(HttpStatus.valueOf(402));
            case 500:
                throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
            default:
                break;
        }

        log.info("normal ending :) {}", LocalDateTime.now().toString());
    }
}
