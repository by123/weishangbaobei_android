package com.wx.assistants.service_utils;

public enum Gender {
    ALL("ALL"),
    MAN("MAN"),
    WOMEN("WOMEN");
    
    private final String value;

    private Gender(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
