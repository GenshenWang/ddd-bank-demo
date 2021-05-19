package com.wgs.ddd.exception;

public class InvalidParamException extends RuntimeException {
    public InvalidParamException() {
    }

    public InvalidParamException(String message) {
        super(message);
    }
}
