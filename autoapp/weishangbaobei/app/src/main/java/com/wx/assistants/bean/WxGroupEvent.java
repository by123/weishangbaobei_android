package com.wx.assistants.bean;

import java.util.LinkedHashSet;

public class WxGroupEvent {
    private LinkedHashSet<String> selectedGroupList = new LinkedHashSet<>();
    private LinkedHashSet<String> selectedPeopleList = new LinkedHashSet<>();
    private String selectedPeoples = new String();

    public void setSelectedGroupList(LinkedHashSet<String> linkedHashSet) {
        this.selectedGroupList = linkedHashSet;
    }

    public LinkedHashSet<String> getSelectedPeopleList() {
        return this.selectedPeopleList;
    }

    public void setSelectedPeopleList(LinkedHashSet<String> linkedHashSet) {
        this.selectedPeopleList = linkedHashSet;
    }

    public LinkedHashSet<String> getSelectedGroupList() {
        return this.selectedGroupList;
    }

    public String getSelectedPeoples() {
        return this.selectedPeoples;
    }

    public void setSelectedPeoples(String str) {
        this.selectedPeoples = str;
    }
}
