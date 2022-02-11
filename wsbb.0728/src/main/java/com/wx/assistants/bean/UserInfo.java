package com.wx.assistants.bean;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1;
    private String headpic;
    private int id;
    private String identiycode;
    private String password;
    private String username;

    public UserInfo() {
    }

    public UserInfo(String str, String str2, String str3) {
        this.username = str;
        this.password = str2;
        this.identiycode = str3;
    }

    public String getHeadpic() {
        return this.headpic;
    }

    public int getId() {
        return this.id;
    }

    public String getIdentiycode() {
        return this.identiycode;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setHeadpic(String str) {
        this.headpic = str;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setIdentiycode(String str) {
        this.identiycode = str;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setUsername(String str) {
        this.username = str;
    }
}
