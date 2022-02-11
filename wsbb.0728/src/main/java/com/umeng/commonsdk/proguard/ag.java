package com.umeng.commonsdk.proguard;

public final class ag {
    public final String a;
    public final byte b;
    public final int c;

    public ag() {
        this("", (byte) 0, 0);
    }

    public ag(String str, byte b2, int i) {
        this.a = str;
        this.b = b2;
        this.c = i;
    }

    public boolean a(ag agVar) {
        return this.a.equals(agVar.a) && this.b == agVar.b && this.c == agVar.c;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ag) {
            return a((ag) obj);
        }
        return false;
    }

    public String toString() {
        return "<TMessage name:'" + this.a + "' type: " + this.b + " seqid:" + this.c + ">";
    }
}
