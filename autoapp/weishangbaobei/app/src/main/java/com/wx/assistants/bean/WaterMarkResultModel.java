package com.wx.assistants.bean;

public class WaterMarkResultModel {
    private WaterMarkResultBean data;
    private int retCode;
    private String retDesc;
    private boolean succ;

    public int getRetCode() {
        return this.retCode;
    }

    public void setRetCode(int i) {
        this.retCode = i;
    }

    public String getRetDesc() {
        return this.retDesc;
    }

    public void setRetDesc(String str) {
        this.retDesc = str;
    }

    public WaterMarkResultBean getData() {
        return this.data;
    }

    public void setData(WaterMarkResultBean waterMarkResultBean) {
        this.data = waterMarkResultBean;
    }

    public boolean isSucc() {
        return this.succ;
    }

    public void setSucc(boolean z) {
        this.succ = z;
    }

    public class WaterMarkResultBean {
        private String cover;
        private String text;
        private String video;

        public WaterMarkResultBean() {
        }

        public String getCover() {
            return this.cover;
        }

        public void setCover(String str) {
            this.cover = str;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String str) {
            this.text = str;
        }

        public String getVideo() {
            return this.video;
        }

        public void setVideo(String str) {
            this.video = str;
        }
    }
}
