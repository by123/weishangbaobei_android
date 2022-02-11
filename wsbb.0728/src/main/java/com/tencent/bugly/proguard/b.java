package com.tencent.bugly.proguard;

public class b extends RuntimeException {
    public b(Exception exc) {
        super(exc);
    }

    public b(String str) {
        super(str);
    }
}
