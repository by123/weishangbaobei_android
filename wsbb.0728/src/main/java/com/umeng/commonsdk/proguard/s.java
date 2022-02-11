package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.ac;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class s {
    private final ByteArrayOutputStream a;
    private final au b;
    private ai c;

    public s() {
        this(new ac.a());
    }

    public s(ak akVar) {
        this.a = new ByteArrayOutputStream();
        this.b = new au((OutputStream) this.a);
        this.c = akVar.a(this.b);
    }

    public String a(j jVar, String str) throws p {
        try {
            return new String(a(jVar), str);
        } catch (UnsupportedEncodingException e) {
            throw new p("JVM DOES NOT SUPPORT ENCODING: " + str);
        }
    }

    public byte[] a(j jVar) throws p {
        this.a.reset();
        jVar.write(this.c);
        return this.a.toByteArray();
    }

    public String b(j jVar) throws p {
        return new String(a(jVar));
    }
}
