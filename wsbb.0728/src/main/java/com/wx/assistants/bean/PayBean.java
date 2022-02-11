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

    public String getAliPrice() {
        return this.aliPrice;
    }

    public int getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getNoncestr() {
        return this.noncestr;
    }

    public String getOid() {
        return this.oid;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPrepayid() {
        return this.prepayid;
    }

    public String getSign() {
        return this.sign;
    }

    public String getStatu() {
        return this.statu;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public String getWxPrice() {
        return this.wxPrice;
    }

    public void setAliPrice(String str) {
        this.aliPrice = str;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(T t) {
        this.data = t;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setNoncestr(String str) {
        this.noncestr = str;
    }

    public void setOid(String str) {
        this.oid = str;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public void setPrepayid(String str) {
        this.prepayid = str;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public void setStatu(String str) {
        this.statu = str;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public void setWxPrice(String str) {
        this.wxPrice = str;
    }
}
