package com.wx.assistants.bean;

public class MarketVerModel {
    private MarketVerInfo version = new MarketVerInfo();

    public class MarketVerInfo {
        private int id;
        private String parmKey;
        private String parmValue;

        public MarketVerInfo() {
        }

        public String getParmKey() {
            return this.parmKey;
        }

        public String getParmValue() {
            return this.parmValue;
        }

        public void setParmKey(String str) {
            this.parmKey = str;
        }

        public void setParmValue(String str) {
            this.parmValue = str;
        }
    }

    public MarketVerInfo getVersion() {
        return this.version;
    }

    public void setConfig(MarketVerInfo marketVerInfo) {
        this.version = marketVerInfo;
    }
}
