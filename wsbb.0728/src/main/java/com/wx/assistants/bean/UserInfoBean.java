package com.wx.assistants.bean;

public class UserInfoBean extends ConmdBean {
    private int code;
    private UserBean data = new UserBean();
    private String message;

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

        public String getAccountName() {
            return this.accountName;
        }

        public String getAccountNo() {
            return this.accountNo;
        }

        public String getActivationCode() {
            return this.activationCode;
        }

        public String getBuyCodeTime() {
            return this.buyCodeTime;
        }

        public String getCodeEndTime() {
            return this.codeEndTime;
        }

        public String getGovtIdNo() {
            return this.govtIdNo;
        }

        public String getGovtIdType() {
            return this.govtIdType;
        }

        public int getInviteNum() {
            return this.inviteNum;
        }

        public int getInvitePaymentNum() {
            return this.invitePaymentNum;
        }

        public int getInviteTwoNum() {
            return this.inviteTwoNum;
        }

        public int getInviteTwoPaymentNum() {
            return this.inviteTwoPaymentNum;
        }

        public String getLevel() {
            return this.level;
        }

        public String getLevelName() {
            return this.levelName;
        }

        public String getMemberStatus() {
            return this.memberStatus;
        }

        public String getNickName() {
            return this.nickName;
        }

        public String getPhone_number() {
            return this.phone_number;
        }

        public String getPicUrl() {
            return this.picUrl;
        }

        public String getRegion() {
            return this.region;
        }

        public String getState() {
            return this.state;
        }

        public String getUserId() {
            return this.userId;
        }

        public double getWithdrawBalance() {
            return this.withdrawBalance;
        }

        public double getWithdrawSum() {
            return this.withdrawSum;
        }

        public String getWithdrawTotal() {
            return this.withdrawTotal;
        }

        public double getWithdrawTwoSum() {
            return this.withdrawTwoSum;
        }

        public String getYesterdayProfits() {
            return this.yesterdayProfits;
        }

        public void setAccountName(String str) {
            this.accountName = str;
        }

        public void setAccountNo(String str) {
            this.accountNo = str;
        }

        public void setActivationCode(String str) {
            this.activationCode = str;
        }

        public void setBuyCodeTime(String str) {
            this.buyCodeTime = str;
        }

        public void setCodeEndTime(String str) {
            this.codeEndTime = str;
        }

        public void setGovtIdNo(String str) {
            this.govtIdNo = str;
        }

        public void setGovtIdType(String str) {
            this.govtIdType = str;
        }

        public void setInviteNum(int i) {
            this.inviteNum = i;
        }

        public void setInvitePaymentNum(int i) {
            this.invitePaymentNum = i;
        }

        public void setInviteTwoNum(int i) {
            this.inviteTwoNum = i;
        }

        public void setInviteTwoPaymentNum(int i) {
            this.inviteTwoPaymentNum = i;
        }

        public void setLevel(String str) {
            this.level = str;
        }

        public void setLevelName(String str) {
            this.levelName = str;
        }

        public void setMemberStatus(String str) {
            this.memberStatus = str;
        }

        public void setNickName(String str) {
            this.nickName = str;
        }

        public void setPhone_number(String str) {
            this.phone_number = str;
        }

        public void setPicUrl(String str) {
            this.picUrl = str;
        }

        public void setRegion(String str) {
            this.region = str;
        }

        public void setState(String str) {
            this.state = str;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public void setWithdrawBalance(double d) {
            this.withdrawBalance = d;
        }

        public void setWithdrawSum(double d) {
            this.withdrawSum = d;
        }

        public void setWithdrawTotal(String str) {
            this.withdrawTotal = str;
        }

        public void setWithdrawTwoSum(double d) {
            this.withdrawTwoSum = d;
        }

        public void setYesterdayProfits(String str) {
            this.yesterdayProfits = str;
        }
    }

    public int getCode() {
        return this.code;
    }

    public UserBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(UserBean userBean) {
        this.data = userBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
