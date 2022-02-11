package com.wx.assistants.bean;

public class ConmdListBean<T> {
    private int code;
    private ConmdListBean<T>.LimitPageBean<T> data;
    private String message;

    public ConmdListBean<T>.LimitPageBean<T> getData() {
        return this.data;
    }

    public void setData(ConmdListBean<T>.LimitPageBean<T> limitPageBean) {
        this.data = limitPageBean;
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

    public class LimitPageBean<T> {
        private T list;
        private int pageNum;
        private int pageSize;
        private int size;
        private int total;

        public LimitPageBean() {
        }

        public int getSize() {
            return this.size;
        }

        public void setSize(int i) {
            this.size = i;
        }

        public int getTotal() {
            return this.total;
        }

        public void setTotal(int i) {
            this.total = i;
        }

        public int getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(int i) {
            this.pageSize = i;
        }

        public int getPageNum() {
            return this.pageNum;
        }

        public void setPageNum(int i) {
            this.pageNum = i;
        }

        public T getList() {
            return this.list;
        }

        public void setList(T t) {
            this.list = t;
        }

        public String toString() {
            return "LimitPageBean{total=" + this.total + ", pageSize=" + this.pageSize + ", size=" + this.size + ", pageNum=" + this.pageNum + ", list=" + this.list + '}';
        }
    }
}
