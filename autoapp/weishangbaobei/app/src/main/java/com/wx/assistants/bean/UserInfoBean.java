package com.wx.assistants.bean;

public class UserInfoBean extends ConmdBean {
    private int code;
    private UserBean data = new UserBean();
    private String message;

    public UserBean getData() {
        return this.data;
    }

    public void setData(UserBean userBean) {
        this.data = userBean;
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

    public class UserBean {
        private String accountName;
        private String accountNo;
        private String activationCode;
        private String buyCodeTime;
        private String codeEndTime;
        private String govtIdNo;
        private String govtIdType;
        private int inviteNum;
        private int invitePaymentNum;
        private int inviteTwoNum;
        private int inviteTwoPaymentNum;
        private String level;
        private String levelName;
        private String memberStatus;
        private String nickName;
        private String phone_number;
        private String picUrl;
        private String region;
        private String state;
        private String userId;
        private double withdrawBalance;
        private double withdrawSum;
        private String withdrawTotal;
        private double withdrawTwoSum;
        private String yesterdayProfits;

        public UserBean() {
        }

        public String getWithdrawTotal() {
            return this.withdrawTotal;
        }

        public void setWithdrawTotal(String str) {
            this.withdrawTotal = str;
        }

        public String getYesterdayProfits() {
            return this.yesterdayProfits;
        }

        public void setYesterdayProfits(String str) {
            this.yesterdayProfits = str;
        }

        public int getInviteTwoNum() {
            return this.inviteTwoNum;
        }

        public void setInviteTwoNum(int i) {
            this.inviteTwoNum = i;
        }

        public int getInviteTwoPaymentNum() {
            return this.inviteTwoPaymentNum;
        }

        public void setInviteTwoPaymentNum(int i) {
            this.inviteTwoPaymentNum = i;
        }

        public double getWithdrawTwoSum() {
            return this.withdrawTwoSum;
        }

        public void setWithdrawTwoSum(double d) {
            this.withdrawTwoSum = d;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public String getCodeEndTime() {
            return this.codeEndTime;
        }

        public void setCodeEndTime(String str) {
            this.codeEndTime = str;
        }

        public String getBuyCodeTime() {
            return this.buyCodeTime;
        }

        public void setBuyCodeTime(String str) {
            this.buyCodeTime = str;
        }

        public String getState() {
            return this.state;
        }

        public void setState(String str) {
            this.state = str;
        }

        public String getAccountName() {
            return this.accountName;
        }

        public void setAccountName(String str) {
            this.accountName = str;
        }

        public int getInviteNum() {
            return this.inviteNum;
        }

        public void setInviteNum(int i) {
            this.inviteNum = i;
        }

        public int getInvitePaymentNum() {
            return this.invitePaymentNum;
        }

        public void setInvitePaymentNum(int i) {
            this.invitePaymentNum = i;
        }

        public double getWithdrawSum() {
            return this.withdrawSum;
        }

        public void setWithdrawSum(double d) {
            this.withdrawSum = d;
        }

        public double getWithdrawBalance() {
            return this.withdrawBalance;
        }

        public void setWithdrawBalance(double d) {
            this.withdrawBalance = d;
        }

        public String getPicUrl() {
            return this.picUrl;
        }

        public void setPicUrl(String str) {
            this.picUrl = str;
        }

        public String getLevel() {
            return this.level;
        }

        public void setLevel(String str) {
            this.level = str;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String str) {
            this.nickName = str;
        }

        public String getAccountNo() {
            return this.accountNo;
        }

        public void setAccountNo(String str) {
            this.accountNo = str;
        }

        public String getMemberStatus() {
            return this.memberStatus;
        }

        public void setMemberStatus(String str) {
            this.memberStatus = str;
        }

        public String getPhone_number() {
            return this.phone_number;
        }

        public void setPhone_number(String str) {
            this.phone_number = str;
        }

        public String getLevelName() {
            return this.levelName;
        }

        public void setLevelName(String str) {
            this.levelName = str;
        }

        public String getGovtIdType() {
            return this.govtIdType;
        }

        public void setGovtIdType(String str) {
            this.govtIdType = str;
        }

        public String getActivationCode() {
            return this.activationCode;
        }

        public void setActivationCode(String str) {
            this.activationCode = str;
        }

        public String getRegion() {
            return this.region;
        }

        public void setRegion(String str) {
            this.region = str;
        }

        public String getGovtIdNo() {
            return this.govtIdNo;
        }

        public void setGovtIdNo(String str) {
            this.govtIdNo = str;
        }
    }
}
