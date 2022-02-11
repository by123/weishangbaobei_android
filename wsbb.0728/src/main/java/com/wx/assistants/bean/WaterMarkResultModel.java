package com.wx.assistants.bean;

public class WaterMarkResultModel {
    private WaterMarkResultBean data;
    private int retCode;
    private String retDesc;
    private boolean succ;

    public class WaterMarkResultBean {
        private String cover;
        private String text;
        private String video;

        public WaterMarkResultBean() {
        }

        public String getCover() {
            return this.cover;
        }

        public String getText() {
            return this.text;
        }

        public String getVideo() {
            return this.video;
        }

        public void setCover(String str) {
            this.cover = str;
        }

        public void setText(String str) {
            this.text = str;
        }

        public void setVideo(String str) {
            this.video = str;
        }
    }

    public WaterMarkResultBean getData() {
        return this.data;
    }

    public int getRetCode() {
        return this.retCode;
    }

    public String getRetDesc() {
        return this.retDesc;
    }

    public boolean isSucc() {
        return this.succ;
    }

    public void setData(WaterMarkResultBean waterMarkResultBean) {
        this.data = waterMarkResultBean;
    }

    public void setRetCode(int i) {
        this.retCode = i;
    }

    public void setRetDesc(String str) {
        this.retDesc = str;
    }

    public void setSucc(boolean z) {
        this.succ = z;
    }
}
