package com.wgs.ddd;

public class Result<T> {

    private Integer code;
    private String msg;
    private Boolean success;
    private T data;

    public Result(Integer code, String msg, Boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public Result(Integer code, String msg, Boolean success, T data) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.data = data;
    }

    public Result() {
    }

    public static Result success() {
        return new Result(200, "success", true);
    }

    public Result success(T data) {
        return new Result(200, "success", true, data);
    }
}
