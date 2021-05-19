package com.wgs.ddd.exception;

public class DailyLimitException extends RuntimeException {
    public DailyLimitException() {
    }

    public DailyLimitException(String message) {
        super(message);
    }
}
