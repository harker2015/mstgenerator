package com.example.mstgenerator.common;

public class ResultJson<T> {
    private Integer code;

    private String message;

    private T data;

    private Integer count;

    public static ResultJson success() {
        ResultJson r = new ResultJson();
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static ResultJson success(Object data, int count) {
        ResultJson r = new ResultJson();
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        r.setData(data);
        r.setCount(count);
        return r;
    }

    public static ResultJson error() {
        return error("失败");
    }

    public static ResultJson error(String errMsg) {
        ResultJson r = new ResultJson();
        r.setCode(ResultCode.ERROR);
        r.setMessage(errMsg);
        return r;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
