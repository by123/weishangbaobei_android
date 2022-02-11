package com.wx.assistants.bean;

import java.util.ArrayList;

public class WxTagCompanyEvent {
    private ArrayList<String> selectedTagList = new ArrayList<>();

    public ArrayList<String> getSelectedTagList() {
        return this.selectedTagList;
    }

    public void setSelectedTagList(ArrayList<String> arrayList) {
        this.selectedTagList = arrayList;
    }
}
