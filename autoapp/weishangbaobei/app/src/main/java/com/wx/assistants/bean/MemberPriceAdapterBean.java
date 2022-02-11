package com.wx.assistants.bean;

import java.math.BigDecimal;
import java.util.Date;

public class MemberPriceAdapterBean {
    private BigDecimal aliAmount;
    private BigDecimal amount;
    private String cashBack;
    private Date createTime;
    private String describes;
    private String discount;
    private String effectiveTerm;
    private int id;
    private String isCumulative;
    private String typeName;
    private BigDecimal wechatAmount;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal bigDecimal) {
        this.amount = bigDecimal;
    }

    public BigDecimal getAliAmount() {
        return this.aliAmount;
    }

    public void setAliAmount(BigDecimal bigDecimal) {
        this.aliAmount = bigDecimal;
    }

    public BigDecimal getWechatAmount() {
        return this.wechatAmount;
    }

    public void setWechatAmount(BigDecimal bigDecimal) {
        this.wechatAmount = bigDecimal;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    public String getDiscount() {
        return this.discount;
    }

    public void setDiscount(String str) {
        this.discount = str;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String str) {
        this.typeName = str;
    }

    public String getDescribes() {
        return this.describes;
    }

    public void setDescribes(String str) {
        this.describes = str;
    }

    public String getIsCumulative() {
        return this.isCumulative;
    }

    public void setIsCumulative(String str) {
        this.isCumulative = str;
    }

    public String getEffectiveTerm() {
        return this.effectiveTerm;
    }

    public void setEffectiveTerm(String str) {
        this.effectiveTerm = str;
    }

    public String getCashBack() {
        return this.cashBack;
    }

    public void setCashBack(String str) {
        this.cashBack = str;
    }
}
