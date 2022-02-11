package com.umeng.commonsdk.proguard;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: UMEnvelope */
public class f implements j<f, e>, Serializable, Cloneable {
    private static final int A = 2;
    private static final int B = 3;
    public static final Map<e, v> k;
    private static final long l = 420342210744516016L;
    /* access modifiers changed from: private */
    public static final an m = new an("UMEnvelope");
    /* access modifiers changed from: private */
    public static final ad n = new ad("version", (byte) 11, 1);
    /* access modifiers changed from: private */
    public static final ad o = new ad("address", (byte) 11, 2);
    /* access modifiers changed from: private */
    public static final ad p = new ad("signature", (byte) 11, 3);
    /* access modifiers changed from: private */
    public static final ad q = new ad("serial_num", (byte) 8, 4);
    /* access modifiers changed from: private */
    public static final ad r = new ad("ts_secs", (byte) 8, 5);
    /* access modifiers changed from: private */
    public static final ad s = new ad("length", (byte) 8, 6);
    /* access modifiers changed from: private */
    public static final ad t = new ad("entity", (byte) 11, 7);
    /* access modifiers changed from: private */
    public static final ad u = new ad("guid", (byte) 11, 8);
    /* access modifiers changed from: private */
    public static final ad v = new ad("checksum", (byte) 11, 9);
    /* access modifiers changed from: private */
    public static final ad w = new ad("codex", (byte) 8, 10);
    private static final Map<Class<? extends aq>, ar> x = new HashMap();
    private static final int y = 0;
    private static final int z = 1;
    private byte C;
    private e[] D;
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;

    static {
        x.put(as.class, new b());
        x.put(at.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.VERSION, new v("version", (byte) 1, new w((byte) 11)));
        enumMap.put(e.ADDRESS, new v("address", (byte) 1, new w((byte) 11)));
        enumMap.put(e.SIGNATURE, new v("signature", (byte) 1, new w((byte) 11)));
        enumMap.put(e.SERIAL_NUM, new v("serial_num", (byte) 1, new w((byte) 8)));
        enumMap.put(e.TS_SECS, new v("ts_secs", (byte) 1, new w((byte) 8)));
        enumMap.put(e.LENGTH, new v("length", (byte) 1, new w((byte) 8)));
        enumMap.put(e.ENTITY, new v("entity", (byte) 1, new w((byte) 11, true)));
        enumMap.put(e.GUID, new v("guid", (byte) 1, new w((byte) 11)));
        enumMap.put(e.CHECKSUM, new v("checksum", (byte) 1, new w((byte) 11)));
        enumMap.put(e.CODEX, new v("codex", (byte) 2, new w((byte) 8)));
        k = Collections.unmodifiableMap(enumMap);
        v.a(f.class, k);
    }

    /* compiled from: UMEnvelope */
    public enum e implements q {
        VERSION(1, "version"),
        ADDRESS(2, "address"),
        SIGNATURE(3, "signature"),
        SERIAL_NUM(4, "serial_num"),
        TS_SECS(5, "ts_secs"),
        LENGTH(6, "length"),
        ENTITY(7, "entity"),
        GUID(8, "guid"),
        CHECKSUM(9, "checksum"),
        CODEX(10, "codex");
        
        private static final Map<String, e> k = null;
        private final short l;
        private final String m;

        static {
            k = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                k.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case 7:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
                    return CHECKSUM;
                case 10:
                    return CODEX;
                default:
                    return null;
            }
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return k.get(str);
        }

        private e(short s, String str) {
            this.l = s;
            this.m = str;
        }

        public short a() {
            return this.l;
        }

        public String b() {
            return this.m;
        }
    }

    public f() {
        this.C = 0;
        this.D = new e[]{e.CODEX};
    }

    public f(String str, String str2, String str3, int i2, int i3, int i4, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = i2;
        d(true);
        this.e = i3;
        e(true);
        this.f = i4;
        f(true);
        this.g = byteBuffer;
        this.h = str4;
        this.i = str5;
    }

    public f(f fVar) {
        this.C = 0;
        this.D = new e[]{e.CODEX};
        this.C = fVar.C;
        if (fVar.d()) {
            this.a = fVar.a;
        }
        if (fVar.g()) {
            this.b = fVar.b;
        }
        if (fVar.j()) {
            this.c = fVar.c;
        }
        this.d = fVar.d;
        this.e = fVar.e;
        this.f = fVar.f;
        if (fVar.w()) {
            this.g = k.d(fVar.g);
        }
        if (fVar.z()) {
            this.h = fVar.h;
        }
        if (fVar.C()) {
            this.i = fVar.i;
        }
        this.j = fVar.j;
    }

    /* renamed from: a */
    public f deepCopy() {
        return new f(this);
    }

    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0;
        e(false);
        this.e = 0;
        f(false);
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        j(false);
        this.j = 0;
    }

    public String b() {
        return this.a;
    }

    public f a(String str) {
        this.a = str;
        return this;
    }

    public void c() {
        this.a = null;
    }

    public boolean d() {
        return this.a != null;
    }

    public void a(boolean z2) {
        if (!z2) {
            this.a = null;
        }
    }

    public String e() {
        return this.b;
    }

    public f b(String str) {
        this.b = str;
        return this;
    }

    public void f() {
        this.b = null;
    }

    public boolean g() {
        return this.b != null;
    }

    public void b(boolean z2) {
        if (!z2) {
            this.b = null;
        }
    }

    public String h() {
        return this.c;
    }

    public f c(String str) {
        this.c = str;
        return this;
    }

    public void i() {
        this.c = null;
    }

    public boolean j() {
        return this.c != null;
    }

    public void c(boolean z2) {
        if (!z2) {
            this.c = null;
        }
    }

    public int k() {
        return this.d;
    }

    public f a(int i2) {
        this.d = i2;
        d(true);
        return this;
    }

    public void l() {
        this.C = g.b(this.C, 0);
    }

    public boolean m() {
        return g.a(this.C, 0);
    }

    public void d(boolean z2) {
        this.C = g.a(this.C, 0, z2);
    }

    public int n() {
        return this.e;
    }

    public f b(int i2) {
        this.e = i2;
        e(true);
        return this;
    }

    public void o() {
        this.C = g.b(this.C, 1);
    }

    public boolean p() {
        return g.a(this.C, 1);
    }

    public void e(boolean z2) {
        this.C = g.a(this.C, 1, z2);
    }

    public int q() {
        return this.f;
    }

    public f c(int i2) {
        this.f = i2;
        f(true);
        return this;
    }

    public void r() {
        this.C = g.b(this.C, 2);
    }

    public boolean s() {
        return g.a(this.C, 2);
    }

    public void f(boolean z2) {
        this.C = g.a(this.C, 2, z2);
    }

    public byte[] t() {
        a(k.c(this.g));
        if (this.g == null) {
            return null;
        }
        return this.g.array();
    }

    public ByteBuffer u() {
        return this.g;
    }

    public f a(byte[] bArr) {
        a(bArr == null ? null : ByteBuffer.wrap(bArr));
        return this;
    }

    public f a(ByteBuffer byteBuffer) {
        this.g = byteBuffer;
        return this;
    }

    public void v() {
        this.g = null;
    }

    public boolean w() {
        return this.g != null;
    }

    public void g(boolean z2) {
        if (!z2) {
            this.g = null;
        }
    }

    public String x() {
        return this.h;
    }

    public f d(String str) {
        this.h = str;
        return this;
    }

    public void y() {
        this.h = null;
    }

    public boolean z() {
        return this.h != null;
    }

    public void h(boolean z2) {
        if (!z2) {
            this.h = null;
        }
    }

    public String A() {
        return this.i;
    }

    public f e(String str) {
        this.i = str;
        return this;
    }

    public void B() {
        this.i = null;
    }

    public boolean C() {
        return this.i != null;
    }

    public void i(boolean z2) {
        if (!z2) {
            this.i = null;
        }
    }

    public int D() {
        return this.j;
    }

    public f d(int i2) {
        this.j = i2;
        j(true);
        return this;
    }

    public void E() {
        this.C = g.b(this.C, 3);
    }

    public boolean F() {
        return g.a(this.C, 3);
    }

    public void j(boolean z2) {
        this.C = g.a(this.C, 3, z2);
    }

    /* renamed from: e */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    public void read(ai aiVar) throws p {
        x.get(aiVar.D()).b().b(aiVar, this);
    }

    public void write(ai aiVar) throws p {
        x.get(aiVar.D()).b().a(aiVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UMEnvelope(");
        sb.append("version:");
        if (this.a == null) {
            sb.append("null");
        } else {
            sb.append(this.a);
        }
        sb.append(", ");
        sb.append("address:");
        if (this.b == null) {
            sb.append("null");
        } else {
            sb.append(this.b);
        }
        sb.append(", ");
        sb.append("signature:");
        if (this.c == null) {
            sb.append("null");
        } else {
            sb.append(this.c);
        }
        sb.append(", ");
        sb.append("serial_num:");
        sb.append(this.d);
        sb.append(", ");
        sb.append("ts_secs:");
        sb.append(this.e);
        sb.append(", ");
        sb.append("length:");
        sb.append(this.f);
        sb.append(", ");
        sb.append("entity:");
        if (this.g == null) {
            sb.append("null");
        } else {
            k.a(this.g, sb);
        }
        sb.append(", ");
        sb.append("guid:");
        if (this.h == null) {
            sb.append("null");
        } else {
            sb.append(this.h);
        }
        sb.append(", ");
        sb.append("checksum:");
        if (this.i == null) {
            sb.append("null");
        } else {
            sb.append(this.i);
        }
        if (F()) {
            sb.append(", ");
            sb.append("codex:");
            sb.append(this.j);
        }
        sb.append(")");
        return sb.toString();
    }

    public void G() throws p {
        if (this.a == null) {
            throw new aj("Required field 'version' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new aj("Required field 'address' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new aj("Required field 'signature' was not present! Struct: " + toString());
        } else if (this.g == null) {
            throw new aj("Required field 'entity' was not present! Struct: " + toString());
        } else if (this.h == null) {
            throw new aj("Required field 'guid' was not present! Struct: " + toString());
        } else if (this.i == null) {
            throw new aj("Required field 'checksum' was not present! Struct: " + toString());
        }
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new ac(new au((OutputStream) objectOutputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.C = 0;
            read(new ac(new au((InputStream) objectInputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: UMEnvelope */
    private static class b implements ar {
        private b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* compiled from: UMEnvelope */
    private static class a extends as<f> {
        private a() {
        }

        /* renamed from: a */
        public void b(ai aiVar, f fVar) throws p {
            aiVar.j();
            while (true) {
                ad l = aiVar.l();
                if (l.b == 0) {
                    aiVar.k();
                    if (!fVar.m()) {
                        throw new aj("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    } else if (!fVar.p()) {
                        throw new aj("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    } else if (fVar.s()) {
                        fVar.G();
                        return;
                    } else {
                        throw new aj("Required field 'length' was not found in serialized data! Struct: " + toString());
                    }
                } else {
                    switch (l.c) {
                        case 1:
                            if (l.b != 11) {
                                al.a(aiVar, l.b);
                                break;
                            } else {
                                fVar.a = aiVar.z();
                                fVar.a(true);
                                break;
                            }
                        case 2:
                            if (l.b != 11) {
                                al.a(aiVar, l.b);
                                break;
                            } else {
                                fVar.b = aiVar.z();
                                fVar.b(true);
                                break;
                            }
                        case 3:
                            if (l.b != 11) {
                                al.a(aiVar, l.b);
                                break;
                            } else {
                                fVar.c = aiVar.z();
                                fVar.c(true);
                                break;
                            }
                        case 4:
                            if (l.b != 8) {
                                al.a(aiVar, l.b);
                                break;
                            } else {
                                fVar.d = aiVar.w();
                                fVar.d(true);
                                break;
                            }
                        case 5:
                            if (l.b != 8) {
                                al.a(aiVar, l.b);
                                break;
                            } else {
                                fVar.e = aiVar.w();
                                fVar.e(true);
                                break;
                            }
                        case 6:
                            if (l.b != 8) {
                                al.a(aiVar, l.b);
                                break;
                            } else {
                                fVar.f = aiVar.w();
                                fVar.f(true);
                                break;
                            }
                        case 7:
                            if (l.b != 11) {
                                al.a(aiVar, l.b);
                                break;
                            } else {
                                fVar.g = aiVar.A();
                                fVar.g(true);
                                break;
                            }
                        case 8:
                            if (l.b != 11) {
                                al.a(aiVar, l.b);
                                break;
                            } else {
                                fVar.h = aiVar.z();
                                fVar.h(true);
                                break;
                            }
                        case 9:
                            if (l.b != 11) {
                                al.a(aiVar, l.b);
                                break;
                            } else {
                                fVar.i = aiVar.z();
                                fVar.i(true);
                                break;
                            }
                        case 10:
                            if (l.b != 8) {
                                al.a(aiVar, l.b);
                                break;
                            } else {
                                fVar.j = aiVar.w();
                                fVar.j(true);
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
        public void a(ai aiVar, f fVar) throws p {
            fVar.G();
            aiVar.a(f.m);
            if (fVar.a != null) {
                aiVar.a(f.n);
                aiVar.a(fVar.a);
                aiVar.c();
            }
            if (fVar.b != null) {
                aiVar.a(f.o);
                aiVar.a(fVar.b);
                aiVar.c();
            }
            if (fVar.c != null) {
                aiVar.a(f.p);
                aiVar.a(fVar.c);
                aiVar.c();
            }
            aiVar.a(f.q);
            aiVar.a(fVar.d);
            aiVar.c();
            aiVar.a(f.r);
            aiVar.a(fVar.e);
            aiVar.c();
            aiVar.a(f.s);
            aiVar.a(fVar.f);
            aiVar.c();
            if (fVar.g != null) {
                aiVar.a(f.t);
                aiVar.a(fVar.g);
                aiVar.c();
            }
            if (fVar.h != null) {
                aiVar.a(f.u);
                aiVar.a(fVar.h);
                aiVar.c();
            }
            if (fVar.i != null) {
                aiVar.a(f.v);
                aiVar.a(fVar.i);
                aiVar.c();
            }
            if (fVar.F()) {
                aiVar.a(f.w);
                aiVar.a(fVar.j);
                aiVar.c();
            }
            aiVar.d();
            aiVar.b();
        }
    }

    /* compiled from: UMEnvelope */
    private static class d implements ar {
        private d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* compiled from: UMEnvelope */
    private static class c extends at<f> {
        private c() {
        }

        public void a(ai aiVar, f fVar) throws p {
            ao aoVar = (ao) aiVar;
            aoVar.a(fVar.a);
            aoVar.a(fVar.b);
            aoVar.a(fVar.c);
            aoVar.a(fVar.d);
            aoVar.a(fVar.e);
            aoVar.a(fVar.f);
            aoVar.a(fVar.g);
            aoVar.a(fVar.h);
            aoVar.a(fVar.i);
            BitSet bitSet = new BitSet();
            if (fVar.F()) {
                bitSet.set(0);
            }
            aoVar.a(bitSet, 1);
            if (fVar.F()) {
                aoVar.a(fVar.j);
            }
        }

        public void b(ai aiVar, f fVar) throws p {
            ao aoVar = (ao) aiVar;
            fVar.a = aoVar.z();
            fVar.a(true);
            fVar.b = aoVar.z();
            fVar.b(true);
            fVar.c = aoVar.z();
            fVar.c(true);
            fVar.d = aoVar.w();
            fVar.d(true);
            fVar.e = aoVar.w();
            fVar.e(true);
            fVar.f = aoVar.w();
            fVar.f(true);
            fVar.g = aoVar.A();
            fVar.g(true);
            fVar.h = aoVar.z();
            fVar.h(true);
            fVar.i = aoVar.z();
            fVar.i(true);
            if (aoVar.b(1).get(0)) {
                fVar.j = aoVar.w();
                fVar.j(true);
            }
        }
    }
}
