package com.wx.assistants.bean;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class WxTagEvent {
    private LinkedHashSet<String> selectedPeopleList = new LinkedHashSet<>();
    private String selectedPeoples = new String();
    private ArrayList<String> selectedTagList = new ArrayList<>();

    public LinkedHashSet<String> getSelectedPeopleList() {
        return this.selectedPeopleList;
    }

    public String getSelectedPeoples() {
        return this.selectedPeoples;
    }

    public ArrayList<String> getSelectedTagList() {
        return this.selectedTagList;
    }

    public void setSelectedPeopleList(LinkedHashSet<String> linkedHashSet) {
        this.selectedPeopleList = linkedHashSet;
    }

    public void setSelectedPeoples(String str) {
        this.selectedPeoples = str;
    }

    public void setSelectedTagList(ArrayList<String> arrayList) {
        this.selectedTagList = arrayList;
    }
}
