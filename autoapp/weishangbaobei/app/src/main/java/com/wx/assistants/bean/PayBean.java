package com.wx.assistants.bean;

public class PayBean<T> {
    private String aliPrice;
    private int code;
    private T data;
    private String message;
    private String noncestr;
    private String oid;
    private String phoneNumber;
    private String prepayid;
    private String sign;
    private String statu;
    private String timestamp;
    private String wxPrice;

    public String getWxPrice() {
        return this.wxPrice;
    }

    public void setWxPrice(String str) {
        this.wxPrice = str;
    }

    public String getAliPrice() {
        return this.aliPrice;
    }

    public void setAliPrice(String str) {
        this.aliPrice = str;
    }

    public String getNoncestr() {
        return this.noncestr;
    }

    public void setNoncestr(String str) {
        this.noncestr = str;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public String getPrepayid() {
        return this.prepayid;
    }

    public void setPrepayid(String str) {
        this.prepayid = str;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public String getOid() {
        return this.oid;
    }

    public void setOid(String str) {
        this.oid = str;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public String getStatu() {
        return this.statu;
    }

    public void setStatu(String str) {
        this.statu = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }
}
