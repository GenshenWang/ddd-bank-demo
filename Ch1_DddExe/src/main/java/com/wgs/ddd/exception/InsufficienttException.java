package com.wgs.ddd.exception;

public class InsufficienttException extends RuntimeException {
    public InsufficienttException(String message) {
        super(message);
    }

    public InsufficienttException() {
    }
}
