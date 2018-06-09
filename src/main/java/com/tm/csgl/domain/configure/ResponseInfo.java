package com.tm.csgl.domain.configure;

public class ResponseInfo<T> {
    private T data;

    private String msg="";

    public ResponseInfo() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
