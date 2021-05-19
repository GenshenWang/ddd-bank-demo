package com.wgs.ddd.exception;

public class InsufficientException extends RuntimeException {
    public InsufficientException() {
    }

    public InsufficientException(String message) {
        super(message);
    }
}
