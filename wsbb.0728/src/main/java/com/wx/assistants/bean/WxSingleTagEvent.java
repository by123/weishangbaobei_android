package com.wx.assistants.bean;

import java.util.LinkedHashSet;

public class WxSingleTagEvent {
    private LabelBean selectedLabelBean;
    private LinkedHashSet<String> selectedPeopleList = new LinkedHashSet<>();
    private String selectedPeoples = new String();

    public LabelBean getSelectedLabelBean() {
        return this.selectedLabelBean;
    }

    public LinkedHashSet<String> getSelectedPeopleList() {
        return this.selectedPeopleList;
    }

    public String getSelectedPeoples() {
        return this.selectedPeoples;
    }

    public void setSelectedLabelBean(LabelBean labelBean) {
        this.selectedLabelBean = labelBean;
    }

    public void setSelectedPeopleList(LinkedHashSet<String> linkedHashSet) {
        this.selectedPeopleList = linkedHashSet;
    }

    public void setSelectedPeoples(String str) {
        this.selectedPeoples = str;
    }

    public void setSelectedTag(LabelBean labelBean) {
        this.selectedLabelBean = labelBean;
    }
}
