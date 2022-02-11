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
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: IdJournal */
public class a implements j<a, e>, Serializable, Cloneable {
    public static final Map<e, v> e;
    private static final long f = 9132678615281394583L;
    /* access modifiers changed from: private */
    public static final an g = new an("IdJournal");
    /* access modifiers changed from: private */
    public static final ad h = new ad("domain", (byte) 11, 1);
    /* access modifiers changed from: private */
    public static final ad i = new ad("old_id", (byte) 11, 2);
    /* access modifiers changed from: private */
    public static final ad j = new ad("new_id", (byte) 11, 3);
    /* access modifiers changed from: private */
    public static final ad k = new ad("ts", (byte) 10, 4);
    private static final Map<Class<? extends aq>, ar> l = new HashMap();
    private static final int m = 0;
    public String a;
    public String b;
    public String c;
    public long d;
    private byte n;
    private e[] o;

    static {
        l.put(as.class, new b());
        l.put(at.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.DOMAIN, new v("domain", (byte) 1, new w((byte) 11)));
        enumMap.put(e.OLD_ID, new v("old_id", (byte) 2, new w((byte) 11)));
        enumMap.put(e.NEW_ID, new v("new_id", (byte) 1, new w((byte) 11)));
        enumMap.put(e.TS, new v("ts", (byte) 1, new w((byte) 10)));
        e = Collections.unmodifiableMap(enumMap);
        v.a(a.class, e);
    }

    /* compiled from: IdJournal */
    public enum e implements q {
        DOMAIN(1, "domain"),
        OLD_ID(2, "old_id"),
        NEW_ID(3, "new_id"),
        TS(4, "ts");
        
        private static final Map<String, e> e = null;
        private final short f;
        private final String g;

        static {
            e = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                e.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return DOMAIN;
                case 2:
                    return OLD_ID;
                case 3:
                    return NEW_ID;
                case 4:
                    return TS;
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
            return e.get(str);
        }

        private e(short s, String str) {
            this.f = s;
            this.g = str;
        }

        public short a() {
            return this.f;
        }

        public String b() {
            return this.g;
        }
    }

    public a() {
        this.n = 0;
        this.o = new e[]{e.OLD_ID};
    }

    public a(String str, String str2, long j2) {
        this();
        this.a = str;
        this.c = str2;
        this.d = j2;
        d(true);
    }

    public a(a aVar) {
        this.n = 0;
        this.o = new e[]{e.OLD_ID};
        this.n = aVar.n;
        if (aVar.d()) {
            this.a = aVar.a;
        }
        if (aVar.g()) {
            this.b = aVar.b;
        }
        if (aVar.j()) {
            this.c = aVar.c;
        }
        this.d = aVar.d;
    }

    /* renamed from: a */
    public a deepCopy() {
        return new a(this);
    }

    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0;
    }

    public String b() {
        return this.a;
    }

    public a a(String str) {
        this.a = str;
        return this;
    }

    public void c() {
        this.a = null;
    }

    public boolean d() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public String e() {
        return this.b;
    }

    public a b(String str) {
        this.b = str;
        return this;
    }

    public void f() {
        this.b = null;
    }

    public boolean g() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public String h() {
        return this.c;
    }

    public a c(String str) {
        this.c = str;
        return this;
    }

    public void i() {
        this.c = null;
    }

    public boolean j() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    public long k() {
        return this.d;
    }

    public a a(long j2) {
        this.d = j2;
        d(true);
        return this;
    }

    public void l() {
        this.n = g.b(this.n, 0);
    }

    public boolean m() {
        return g.a(this.n, 0);
    }

    public void d(boolean z) {
        this.n = g.a(this.n, 0, z);
    }

    /* renamed from: a */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    public void read(ai aiVar) throws p {
        l.get(aiVar.D()).b().b(aiVar, this);
    }

    public void write(ai aiVar) throws p {
        l.get(aiVar.D()).b().a(aiVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdJournal(");
        sb.append("domain:");
        if (this.a == null) {
            sb.append("null");
        } else {
            sb.append(this.a);
        }
        if (g()) {
            sb.append(", ");
            sb.append("old_id:");
            if (this.b == null) {
                sb.append("null");
            } else {
                sb.append(this.b);
            }
        }
        sb.append(", ");
        sb.append("new_id:");
        if (this.c == null) {
            sb.append("null");
        } else {
            sb.append(this.c);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.d);
        sb.append(")");
        return sb.toString();
    }

    public void n() throws p {
        if (this.a == null) {
            throw new aj("Required field 'domain' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new aj("Required field 'new_id' was not present! Struct: " + toString());
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
            this.n = 0;
            read(new ac(new au((InputStream) objectInputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdJournal */
    private static class b implements ar {
        private b() {
        }

        /* renamed from: a */
        public C0011a b() {
            return new C0011a();
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.a$a  reason: collision with other inner class name */
    /* compiled from: IdJournal */
    private static class C0011a extends as<a> {
        private C0011a() {
        }

        /* renamed from: a */
        public void b(ai aiVar, a aVar) throws p {
            aiVar.j();
            while (true) {
                ad l = aiVar.l();
                if (l.b == 0) {
                    aiVar.k();
                    if (aVar.m()) {
                        aVar.n();
                        return;
                    }
                    throw new aj("Required field 'ts' was not found in serialized data! Struct: " + toString());
                }
                switch (l.c) {
                    case 1:
                        if (l.b != 11) {
                            al.a(aiVar, l.b);
                            break;
                        } else {
                            aVar.a = aiVar.z();
                            aVar.a(true);
                            break;
                        }
                    case 2:
                        if (l.b != 11) {
                            al.a(aiVar, l.b);
                            break;
                        } else {
                            aVar.b = aiVar.z();
                            aVar.b(true);
                            break;
                        }
                    case 3:
                        if (l.b != 11) {
                            al.a(aiVar, l.b);
                            break;
                        } else {
                            aVar.c = aiVar.z();
                            aVar.c(true);
                            break;
                        }
                    case 4:
                        if (l.b != 10) {
                            al.a(aiVar, l.b);
                            break;
                        } else {
                            aVar.d = aiVar.x();
                            aVar.d(true);
                            break;
                        }
                    default:
                        al.a(aiVar, l.b);
                        break;
                }
                aiVar.m();
            }
        }

        /* renamed from: b */
        public void a(ai aiVar, a aVar) throws p {
            aVar.n();
            aiVar.a(a.g);
            if (aVar.a != null) {
                aiVar.a(a.h);
                aiVar.a(aVar.a);
                aiVar.c();
            }
            if (aVar.b != null && aVar.g()) {
                aiVar.a(a.i);
                aiVar.a(aVar.b);
                aiVar.c();
            }
            if (aVar.c != null) {
                aiVar.a(a.j);
                aiVar.a(aVar.c);
                aiVar.c();
            }
            aiVar.a(a.k);
            aiVar.a(aVar.d);
            aiVar.c();
            aiVar.d();
            aiVar.b();
        }
    }

    /* compiled from: IdJournal */
    private static class d implements ar {
        private d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* compiled from: IdJournal */
    private static class c extends at<a> {
        private c() {
        }

        public void a(ai aiVar, a aVar) throws p {
            ao aoVar = (ao) aiVar;
            aoVar.a(aVar.a);
            aoVar.a(aVar.c);
            aoVar.a(aVar.d);
            BitSet bitSet = new BitSet();
            if (aVar.g()) {
                bitSet.set(0);
            }
            aoVar.a(bitSet, 1);
            if (aVar.g()) {
                aoVar.a(aVar.b);
            }
        }

        public void b(ai aiVar, a aVar) throws p {
            ao aoVar = (ao) aiVar;
            aVar.a = aoVar.z();
            aVar.a(true);
            aVar.c = aoVar.z();
            aVar.c(true);
            aVar.d = aoVar.x();
            aVar.d(true);
            if (aoVar.b(1).get(0)) {
                aVar.b = aoVar.z();
                aVar.b(true);
            }
        }
    }
}
