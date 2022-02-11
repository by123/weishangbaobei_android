package com.wx.assistants.bean;

import java.util.ArrayList;

public class ConmdList2Bean {
    private int code;
    private DataBean data;
    private String message;

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "ConmdListBean{data=" + this.data + ", code=" + this.code + ", message='" + this.message + '\'' + '}';
    }

    public class DataBean {
        private ArrayList<ResultBean> list;
        private PagesBean pages;

        public DataBean() {
        }

        public ArrayList<ResultBean> getList() {
            return this.list;
        }

        public void setList(ArrayList<ResultBean> arrayList) {
            this.list = arrayList;
        }

        public PagesBean getPages() {
            return this.pages;
        }

        public void setPages(PagesBean pagesBean) {
            this.pages = pagesBean;
        }
    }

    public class PagesBean {
        private int pageNum;
        private int pageSize;
        private int size;
        private int total;

        public PagesBean() {
        }

        public int getTotal() {
            return this.total;
        }

        public void setTotal(int i) {
            this.total = i;
        }

        public int getPageNum() {
            return this.pageNum;
        }

        public void setPageNum(int i) {
            this.pageNum = i;
        }

        public int getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(int i) {
            this.pageSize = i;
        }

        public int getSize() {
            return this.size;
        }

        public void setSize(int i) {
            this.size = i;
        }
    }

    public static class ResultBean {
        private String createTime;
        private String phone;
        private String result;

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String str) {
            this.phone = str;
        }

        public String getResult() {
            return this.result;
        }

        public void setResult(String str) {
            this.result = str;
        }

        public String getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(String str) {
            this.createTime = str;
        }
    }
}
