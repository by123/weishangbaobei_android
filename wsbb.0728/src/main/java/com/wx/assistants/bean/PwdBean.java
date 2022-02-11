package com.wx.assistants.bean;

public class PwdBean<T> {
    private int code;
    private DataBean data;
    private String message;

    public static class DataBean {
        private String accesstoken;
        private int expiresin;

        public String getAccesstoken() {
            return this.accesstoken;
        }

        public int getExpiresin() {
            return this.expiresin;
        }

        public void setAccesstoken(String str) {
            this.accesstoken = str;
        }

        public void setExpiresin(int i) {
            this.expiresin = i;
        }
    }

    public int getCode() {
        return this.code;
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
