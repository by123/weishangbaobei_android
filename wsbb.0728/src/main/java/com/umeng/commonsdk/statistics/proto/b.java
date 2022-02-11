package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.ac;
import com.umeng.commonsdk.proguard.ad;
import com.umeng.commonsdk.proguard.ai;
import com.umeng.commonsdk.proguard.aj;
import com.umeng.commonsdk.proguard.al;
import com.umeng.commonsdk.proguard.an;
import com.umeng.commonsdk.proguard.ao;
import com.umeng.commonsdk.proguard.aq;
import com.umeng.commonsdk.proguard.ar;
import com.umeng.commonsdk.proguard.as;
import com.umeng.commonsdk.proguard.at;
import com.umeng.commonsdk.proguard.au;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.proguard.j;
import com.umeng.commonsdk.proguard.p;
import com.umeng.commonsdk.proguard.q;
import com.umeng.commonsdk.proguard.v;
import com.umeng.commonsdk.proguard.w;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class b implements j<b, e>, Serializable, Cloneable {
    public static final Map<e, v> d;
    private static final long e = -6496538196005191531L;
    /* access modifiers changed from: private */
    public static final an f = new an("IdSnapshot");
    /* access modifiers changed from: private */
    public static final ad g = new ad("identity", (byte) 11, 1);
    /* access modifiers changed from: private */
    public static final ad h = new ad("ts", (byte) 10, 2);
    /* access modifiers changed from: private */
    public static final ad i = new ad("version", (byte) 8, 3);
    private static final Map<Class<? extends aq>, ar> j = new HashMap();
    private static final int k = 0;
    private static final int l = 1;
    public String a;
    public long b;
    public int c;
    private byte m;

    private static class a extends as<b> {
        private a() {
        }

        /* renamed from: a */
        public void b(ai aiVar, b bVar) throws p {
            aiVar.j();
            while (true) {
                ad l = aiVar.l();
                if (l.b == 0) {
                    aiVar.k();
                    if (!bVar.g()) {
                        throw new aj("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    } else if (bVar.j()) {
                        bVar.k();
                        return;
                    } else {
                        throw new aj("Required field 'version' was not found in serialized data! Struct: " + toString());
                    }
                } else {
                    switch (l.c) {
                        case 1:
                            if (l.b != 11) {
                                al.a(aiVar, l.b);
                                break;
                            } else {
                                bVar.a = aiVar.z();
                                bVar.a(true);
                                break;
                            }
                        case 2:
                            if (l.b != 10) {
                                al.a(aiVar, l.b);
                                break;
                            } else {
                                bVar.b = aiVar.x();
                                bVar.b(true);
                                break;
                            }
                        case 3:
                            if (l.b != 8) {
                                al.a(aiVar, l.b);
                                break;
                            } else {
                                bVar.c = aiVar.w();
                                bVar.c(true);
                                break;
                            }
                        default:
                            al.a(aiVar, l.b);
                            break;
                    }
                    aiVar.m();
                }
            }
        }

        /* renamed from: b */
        public void a(ai aiVar, b bVar) throws p {
            bVar.k();
            aiVar.a(b.f);
            if (bVar.a != null) {
                aiVar.a(b.g);
                aiVar.a(bVar.a);
                aiVar.c();
            }
            aiVar.a(b.h);
            aiVar.a(bVar.b);
            aiVar.c();
            aiVar.a(b.i);
            aiVar.a(bVar.c);
            aiVar.c();
            aiVar.d();
            aiVar.b();
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.b$b  reason: collision with other inner class name */
    private static class C0012b implements ar {
        private C0012b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    private static class c extends at<b> {
        private c() {
        }

        public void a(ai aiVar, b bVar) throws p {
            ao aoVar = (ao) aiVar;
            aoVar.a(bVar.a);
            aoVar.a(bVar.b);
            aoVar.a(bVar.c);
        }

        public void b(ai aiVar, b bVar) throws p {
            ao aoVar = (ao) aiVar;
            bVar.a = aoVar.z();
            bVar.a(true);
            bVar.b = aoVar.x();
            bVar.b(true);
            bVar.c = aoVar.w();
            bVar.c(true);
        }
    }

    private static class d implements ar {
        private d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    public enum e implements q {
        IDENTITY(1, "identity"),
        TS(2, "ts"),
        VERSION(3, "version");
        
        private static final Map<String, e> d = null;
        private final short e;
        private final String f;

        static {
            d = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        private e(short s, String str) {
            this.e = s;
            this.f = str;
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return IDENTITY;
                case 2:
                    return TS;
                case 3:
                    return VERSION;
                default:
                    return null;
            }
        }

        public static e a(String str) {
            return d.get(str);
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public short a() {
            return this.e;
        }

        public String b() {
            return this.f;
        }
    }

    static {
        j.put(as.class, new C0012b());
        j.put(at.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.IDENTITY, new v("identity", (byte) 1, new w((byte) 11)));
        enumMap.put(e.TS, new v("ts", (byte) 1, new w((byte) 10)));
        enumMap.put(e.VERSION, new v("version", (byte) 1, new w((byte) 8)));
        d = Collections.unmodifiableMap(enumMap);
        v.a(b.class, d);
    }

    public b() {
        this.m = 0;
    }

    public b(b bVar) {
        this.m = 0;
        this.m = bVar.m;
        if (bVar.d()) {
            this.a = bVar.a;
        }
        this.b = bVar.b;
        this.c = bVar.c;
    }

    public b(String str, long j2, int i2) {
        this();
        this.a = str;
        this.b = j2;
        b(true);
        this.c = i2;
        c(true);
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.m = 0;
            read(new ac(new au((InputStream) objectInputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new ac(new au((OutputStream) objectOutputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* renamed from: a */
    public b deepCopy() {
        return new b(this);
    }

    public b a(int i2) {
        this.c = i2;
        c(true);
        return this;
    }

    public b a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public b a(String str) {
        this.a = str;
        return this;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    /* renamed from: b */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    public String b() {
        return this.a;
    }

    public void b(boolean z) {
        this.m = g.a(this.m, 0, z);
    }

    public void c() {
        this.a = null;
    }

    public void c(boolean z) {
        this.m = g.a(this.m, 1, z);
    }

    public void clear() {
        this.a = null;
        b(false);
        this.b = 0;
        c(false);
        this.c = 0;
    }

    public boolean d() {
        return this.a != null;
    }

    public long e() {
        return this.b;
    }

    public void f() {
        this.m = g.b(this.m, 0);
    }

    public boolean g() {
        return g.a(this.m, 0);
    }

    public int h() {
        return this.c;
    }

    public void i() {
        this.m = g.b(this.m, 1);
    }

    public boolean j() {
        return g.a(this.m, 1);
    }

    public void k() throws p {
        if (this.a == null) {
            throw new aj("Required field 'identity' was not present! Struct: " + toString());
        }
    }

    public void read(ai aiVar) throws p {
        j.get(aiVar.D()).b().b(aiVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdSnapshot(");
        sb.append("identity:");
        if (this.a == null) {
            sb.append("null");
        } else {
            sb.append(this.a);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("version:");
        sb.append(this.c);
        sb.append(")");
        return sb.toString();
    }

    public void write(ai aiVar) throws p {
        j.get(aiVar.D()).b().a(aiVar, this);
    }
}
