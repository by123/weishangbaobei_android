package com.umeng.qq.tencent;

public class n {
    private String a;
    private String b;
    private String c;
    private long d = -1;

    public n(String str) {
        this.a = str;
    }

    public void a(String str) {
        this.c = str;
    }

    public void a(String str, String str2) {
        this.b = str;
        this.d = 0;
        if (str2 != null) {
            this.d = System.currentTimeMillis() + (Long.parseLong(str2) * 1000);
        }
    }

    public boolean a() {
        return this.b != null && System.currentTimeMillis() < this.d;
    }

    public String b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }
}
