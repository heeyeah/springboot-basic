package com.hee.web.rest;

import com.hee.exception.BasicException;
import com.hee.exception.BasicRuntimeException;
import com.hee.service.AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/async")
@RequiredArgsConstructor
public class AsyncController {


    private final AsyncService asyncService;


    @GetMapping("/uncaught")
    public ResponseEntity testAsyncUncaughtExceptionHandler() {
        asyncService.uncaughtException();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/exception/{exceptionType}")
    public ResponseEntity testAsyncExceptionHandling(@PathVariable int exceptionType) throws Exception {

        switch (exceptionType) {
            case 3:
                throw new BasicException("basic exception", "case 3");
            case 4:
                throw new BasicRuntimeException("basic runtime exception", "case 4");
            case 401:
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
            case 500:
                throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
            default:
                break;
        }

        asyncService.asyncException(exceptionType);

        return ResponseEntity.ok().build();
    }
}
