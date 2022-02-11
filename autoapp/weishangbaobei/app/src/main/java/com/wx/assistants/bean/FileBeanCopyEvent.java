package com.wx.assistants.bean;

import com.wx.assistants.utils.fileutil.FileBean;

public class FileBeanCopyEvent {
    private FileBean selectedBean = new FileBean();

    public FileBean getSelectedBean() {
        return this.selectedBean;
    }

    public void setSelectedBean(FileBean fileBean) {
        this.selectedBean = fileBean;
    }
}
