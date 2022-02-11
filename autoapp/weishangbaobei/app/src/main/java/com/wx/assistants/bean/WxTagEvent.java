package com.wx.assistants.bean;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class WxTagEvent {
    private LinkedHashSet<String> selectedPeopleList = new LinkedHashSet<>();
    private String selectedPeoples = new String();
    private ArrayList<String> selectedTagList = new ArrayList<>();

    public void setSelectedTagList(ArrayList<String> arrayList) {
        this.selectedTagList = arrayList;
    }

    public LinkedHashSet<String> getSelectedPeopleList() {
        return this.selectedPeopleList;
    }

    public void setSelectedPeopleList(LinkedHashSet<String> linkedHashSet) {
        this.selectedPeopleList = linkedHashSet;
    }

    public ArrayList<String> getSelectedTagList() {
        return this.selectedTagList;
    }

    public String getSelectedPeoples() {
        return this.selectedPeoples;
    }

    public void setSelectedPeoples(String str) {
        this.selectedPeoples = str;
    }
}
