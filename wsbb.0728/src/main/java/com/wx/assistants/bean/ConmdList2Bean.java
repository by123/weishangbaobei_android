package com.wx.assistants.bean;

import java.util.ArrayList;

public class ConmdList2Bean {
    private int code;
    private DataBean data;
    private String message;

    public class DataBean {
        private ArrayList<ResultBean> list;
        private PagesBean pages;

        public DataBean() {
        }

        public ArrayList<ResultBean> getList() {
            return this.list;
        }

        public PagesBean getPages() {
            return this.pages;
        }

        public void setList(ArrayList<ResultBean> arrayList) {
            this.list = arrayList;
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

        public int getPageNum() {
            return this.pageNum;
        }

        public int getPageSize() {
            return this.pageSize;
        }

        public int getSize() {
            return this.size;
        }

        public int getTotal() {
            return this.total;
        }

        public void setPageNum(int i) {
            this.pageNum = i;
        }

        public void setPageSize(int i) {
            this.pageSize = i;
        }

        public void setSize(int i) {
            this.size = i;
        }

        public void setTotal(int i) {
            this.total = i;
        }
    }

    public static class ResultBean {
        private String createTime;
        private String phone;
        private String result;

        public String getCreateTime() {
            return this.createTime;
        }

        public String getPhone() {
            return this.phone;
        }

        public String getResult() {
            return this.result;
        }

        public void setCreateTime(String str) {
            this.createTime = str;
        }

        public void setPhone(String str) {
            this.phone = str;
        }

        public void setResult(String str) {
            this.result = str;
        }
    }

    public int getCode() {
        return this.code;
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "ConmdListBean{data=" + this.data + ", code=" + this.code + ", message='" + this.message + '\'' + '}';
    }
}
