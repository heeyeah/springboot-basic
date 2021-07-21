package com.hee.exception;

import lombok.ToString;

@ToString
public class BasicException extends Exception {

    private final String who;

    public BasicException(String message, String who) {
        super(message);
        this.who = who;
    }
}
