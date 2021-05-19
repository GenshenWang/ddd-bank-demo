package com.wgs.ddd.exception;

public class DailyLimitException extends RuntimeException {
    public DailyLimitException(String message) {
        super(message);
    }

    public DailyLimitException() {
    }
}
