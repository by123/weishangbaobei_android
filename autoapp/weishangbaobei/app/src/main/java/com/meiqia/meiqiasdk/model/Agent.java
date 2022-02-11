package com.meiqia.meiqiasdk.model;

import android.text.TextUtils;
import com.meiqia.core.bean.MQMessage;

public class Agent {
    private String avatar;
    private String cellphone;
    private String email;
    private int enterprise_id;
    private String id;
    private boolean isOnline;
    private String nickname;
    private String privilege;
    private String public_cellphone;
    private String public_email;
    private String qq;
    private String realname;
    private String signature;
    private String status;
    private String telephone;
    private String token;
    private String weixin;

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setCellphone(String str) {
        this.cellphone = str;
    }

    public String getCellphone() {
        return this.cellphone;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEnterprise_id(int i) {
        this.enterprise_id = i;
    }

    public int getEnterprise_id() {
        return this.enterprise_id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setPublic_cellphone(String str) {
        this.public_cellphone = str;
    }

    public String getPublic_cellphone() {
        return this.public_cellphone;
    }

    public void setPublic_email(String str) {
        this.public_email = str;
    }

    public String getPublic_email() {
        return this.public_email;
    }

    public void setQq(String str) {
        this.qq = str;
    }

    public String getQq() {
        return this.qq;
    }

    public void setRealname(String str) {
        this.realname = str;
    }

    public String getRealname() {
        return this.realname;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setTelephone(String str) {
        this.telephone = str;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setWeixin(String str) {
        this.weixin = str;
    }

    public String getWeixin() {
        return this.weixin;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public boolean isOffDuty() {
        return "off_duty".equals(this.status);
    }

    public void setIsOnline(boolean z) {
        this.isOnline = z;
    }

    public boolean isOnline() {
        return this.isOnline;
    }

    public String getPrivilege() {
        return this.privilege;
    }

    public void setPrivilege(String str) {
        this.privilege = str;
    }

    public boolean isRobot() {
        return TextUtils.equals(MQMessage.TYPE_FROM_ROBOT, this.privilege);
    }
}
