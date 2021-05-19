package com.wgs.ddd.exception;

public class InvalidCurrencytException extends RuntimeException {
    public InvalidCurrencytException(String message) {
        super(message);
    }

    public InvalidCurrencytException() {
    }
}
