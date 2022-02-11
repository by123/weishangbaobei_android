package com.umeng.commonsdk.proguard;

public class ad {
    public final String a;
    public final byte b;
    public final short c;

    public ad() {
        this("", (byte) 0, 0);
    }

    public ad(String str, byte b2, short s) {
        this.a = str;
        this.b = b2;
        this.c = s;
    }

    public boolean a(ad adVar) {
        return this.b == adVar.b && this.c == adVar.c;
    }

    public String toString() {
        return "<TField name:'" + this.a + "' type:" + this.b + " field-id:" + this.c + ">";
    }
}
