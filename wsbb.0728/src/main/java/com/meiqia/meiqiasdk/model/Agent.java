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

    public String getAvatar() {
        return this.avatar;
    }

    public String getCellphone() {
        return this.cellphone;
    }

    public String getEmail() {
        return this.email;
    }

    public int getEnterprise_id() {
        return this.enterprise_id;
    }

    public String getId() {
        return this.id;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getPrivilege() {
        return this.privilege;
    }

    public String getPublic_cellphone() {
        return this.public_cellphone;
    }

    public String getPublic_email() {
        return this.public_email;
    }

    public String getQq() {
        return this.qq;
    }

    public String getRealname() {
        return this.realname;
    }

    public String getSignature() {
        return this.signature;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public String getToken() {
        return this.token;
    }

    public String getWeixin() {
        return this.weixin;
    }

    public boolean isOffDuty() {
        return "off_duty".equals(this.status);
    }

    public boolean isOnline() {
        return this.isOnline;
    }

    public boolean isRobot() {
        return TextUtils.equals(MQMessage.TYPE_FROM_ROBOT, this.privilege);
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public void setCellphone(String str) {
        this.cellphone = str;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setEnterprise_id(int i) {
        this.enterprise_id = i;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setIsOnline(boolean z) {
        this.isOnline = z;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public void setPrivilege(String str) {
        this.privilege = str;
    }

    public void setPublic_cellphone(String str) {
        this.public_cellphone = str;
    }

    public void setPublic_email(String str) {
        this.public_email = str;
    }

    public void setQq(String str) {
        this.qq = str;
    }

    public void setRealname(String str) {
        this.realname = str;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setTelephone(String str) {
        this.telephone = str;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setWeixin(String str) {
        this.weixin = str;
    }
}
