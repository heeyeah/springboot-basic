package com.hee.exception;

import lombok.ToString;

@ToString
public class BasicRuntimeException extends RuntimeException {

    private String who;

    public BasicRuntimeException(String message) {
        super(message);
    }

    public BasicRuntimeException(String message, String who) {
        super(message);
        this.who = who;
    }
}
