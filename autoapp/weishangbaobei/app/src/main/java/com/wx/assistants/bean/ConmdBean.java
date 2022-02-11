package com.wx.assistants.bean;

public class ConmdBean<T> {
    private int code;
    private T data;
    private String message;

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "ConmdBean{data=" + this.data + ", code=" + this.code + ", message='" + this.message + '\'' + '}';
    }
}
