package com.wx.assistants.bean;

public class ConmdListBean<T> {
    private int code;
    private ConmdListBean<T>.LimitPageBean<T> data;
    private String message;

    public class LimitPageBean<T> {
        private T list;
        private int pageNum;
        private int pageSize;
        private int size;
        private int total;

        public LimitPageBean() {
        }

        public T getList() {
            return this.list;
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

        public void setList(T t) {
            this.list = t;
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

        public String toString() {
            return "LimitPageBean{total=" + this.total + ", pageSize=" + this.pageSize + ", size=" + this.size + ", pageNum=" + this.pageNum + ", list=" + this.list + '}';
        }
    }

    public int getCode() {
        return this.code;
    }

    public ConmdListBean<T>.LimitPageBean<T> getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setData(ConmdListBean<T>.LimitPageBean<T> limitPageBean) {
        this.data = limitPageBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "ConmdListBean{data=" + this.data + ", code=" + this.code + ", message='" + this.message + '\'' + '}';
    }
}
