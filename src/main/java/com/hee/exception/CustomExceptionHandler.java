package com.hee.exception;

import com.hee.exception.dto.ErrorResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = BasicException.class)
    public ResponseEntity handleBasicException(HttpServletRequest httpServletRequest, BasicException e) {

        log.error("👻 BasicException :P");

        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(value = BasicRuntimeException.class)
    public ResponseEntity handleBasicRuntimeException(HttpServletRequest httpServletRequest, BasicRuntimeException e) {

        log.error("👻👻 BasicRuntimeException :P");

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(HttpServletRequest httpServletRequest, Exception e) {

        log.error("👻👻👻 default exception :!!!");
        return ResponseEntity.internalServerError().body(new ErrorResponseDto("etc", HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null));
    }
}
