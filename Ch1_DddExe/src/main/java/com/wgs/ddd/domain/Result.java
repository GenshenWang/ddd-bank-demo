package com.wgs.ddd.domain;

public class Result<T> {
    private int code;
    private boolean success;
    private String msg;
    private T data;

    public Result(int code, boolean success, String msg, T data) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public static Result fail(String msg) {
        return new Result(400, false, msg, null);
    }

    public static Result<Boolean> success() {
        return new Result<>(200, true, "success", true);
    }

    public static Result<Object> success(Object data) {
        return new Result<>(200, true, "success", data);
    }
}
