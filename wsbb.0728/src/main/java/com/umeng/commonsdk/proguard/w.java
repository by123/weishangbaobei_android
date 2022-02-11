package com.umeng.commonsdk.proguard;

import java.io.Serializable;

public class w implements Serializable {
    private final boolean a;
    public final byte b;
    private final String c;
    private final boolean d;

    public w(byte b2) {
        this(b2, false);
    }

    public w(byte b2, String str) {
        this.b = b2;
        this.a = true;
        this.c = str;
        this.d = false;
    }

    public w(byte b2, boolean z) {
        this.b = b2;
        this.a = false;
        this.c = null;
        this.d = z;
    }

    public boolean a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        return this.b == 12;
    }

    public boolean d() {
        return this.b == 15 || this.b == 13 || this.b == 14;
    }

    public boolean e() {
        return this.d;
    }
}
