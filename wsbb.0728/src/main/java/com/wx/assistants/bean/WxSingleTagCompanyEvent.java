package com.wx.assistants.bean;

public class WxSingleTagCompanyEvent {
    private LabelBean selectedLabelBean;

    public LabelBean getSelectedLabelBean() {
        return this.selectedLabelBean;
    }

    public void setSelectedLabelBean(LabelBean labelBean) {
        this.selectedLabelBean = labelBean;
    }

    public void setSelectedTag(LabelBean labelBean) {
        this.selectedLabelBean = labelBean;
    }
}
