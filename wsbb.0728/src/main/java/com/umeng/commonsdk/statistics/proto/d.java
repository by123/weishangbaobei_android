package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.aa;
import com.umeng.commonsdk.proguard.ac;
import com.umeng.commonsdk.proguard.ad;
import com.umeng.commonsdk.proguard.af;
import com.umeng.commonsdk.proguard.ai;
import com.umeng.commonsdk.proguard.aj;
import com.umeng.commonsdk.proguard.al;
import com.umeng.commonsdk.proguard.an;
import com.umeng.commonsdk.proguard.ao;
import com.umeng.commonsdk.proguard.ap;
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
import com.umeng.commonsdk.proguard.y;
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

public class d implements j<d, e>, Serializable, Cloneable {
    public static final Map<e, v> d;
    private static final long e = 2846460275012375038L;
    /* access modifiers changed from: private */
    public static final an f = new an("Imprint");
    /* access modifiers changed from: private */
    public static final ad g = new ad("property", ap.k, 1);
    /* access modifiers changed from: private */
    public static final ad h = new ad("version", (byte) 8, 2);
    /* access modifiers changed from: private */
    public static final ad i = new ad("checksum", (byte) 11, 3);
    private static final Map<Class<? extends aq>, ar> j = new HashMap();
    private static final int k = 0;
    public Map<String, e> a;
    public int b;
    public String c;
    private byte l;

    private static class a extends as<d> {
        private a() {
        }

        /* renamed from: a */
        public void b(ai aiVar, d dVar) throws p {
            aiVar.j();
            while (true) {
                ad l = aiVar.l();
                if (l.b == 0) {
                    aiVar.k();
                    if (dVar.h()) {
                        dVar.l();
                        return;
                    }
                    throw new aj("Required field 'version' was not found in serialized data! Struct: " + toString());
                }
                switch (l.c) {
                    case 1:
                        if (l.b != 13) {
                            al.a(aiVar, l.b);
                            break;
                        } else {
                            af n = aiVar.n();
                            dVar.a = new HashMap(n.c * 2);
                            for (int i = 0; i < n.c; i++) {
                                String z = aiVar.z();
                                e eVar = new e();
                                eVar.read(aiVar);
                                dVar.a.put(z, eVar);
                            }
                            aiVar.o();
                            dVar.a(true);
                            break;
                        }
                    case 2:
                        if (l.b != 8) {
                            al.a(aiVar, l.b);
                            break;
                        } else {
                            dVar.b = aiVar.w();
                            dVar.b(true);
                            break;
                        }
                    case 3:
                        if (l.b != 11) {
                            al.a(aiVar, l.b);
                            break;
                        } else {
                            dVar.c = aiVar.z();
                            dVar.c(true);
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
        public void a(ai aiVar, d dVar) throws p {
            dVar.l();
            aiVar.a(d.f);
            if (dVar.a != null) {
                aiVar.a(d.g);
                aiVar.a(new af((byte) 11, (byte) 12, dVar.a.size()));
                for (Map.Entry next : dVar.a.entrySet()) {
                    aiVar.a((String) next.getKey());
                    ((e) next.getValue()).write(aiVar);
                }
                aiVar.e();
                aiVar.c();
            }
            aiVar.a(d.h);
            aiVar.a(dVar.b);
            aiVar.c();
            if (dVar.c != null) {
                aiVar.a(d.i);
                aiVar.a(dVar.c);
                aiVar.c();
            }
            aiVar.d();
            aiVar.b();
        }
    }

    private static class b implements ar {
        private b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    private static class c extends at<d> {
        private c() {
        }

        public void a(ai aiVar, d dVar) throws p {
            ao aoVar = (ao) aiVar;
            aoVar.a(dVar.a.size());
            for (Map.Entry next : dVar.a.entrySet()) {
                aoVar.a((String) next.getKey());
                ((e) next.getValue()).write(aoVar);
            }
            aoVar.a(dVar.b);
            aoVar.a(dVar.c);
        }

        public void b(ai aiVar, d dVar) throws p {
            ao aoVar = (ao) aiVar;
            af afVar = new af((byte) 11, (byte) 12, aoVar.w());
            dVar.a = new HashMap(afVar.c * 2);
            for (int i = 0; i < afVar.c; i++) {
                String z = aoVar.z();
                e eVar = new e();
                eVar.read(aoVar);
                dVar.a.put(z, eVar);
            }
            dVar.a(true);
            dVar.b = aoVar.w();
            dVar.b(true);
            dVar.c = aoVar.z();
            dVar.c(true);
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.d$d  reason: collision with other inner class name */
    private static class C0014d implements ar {
        private C0014d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    public enum e implements q {
        PROPERTY(1, "property"),
        VERSION(2, "version"),
        CHECKSUM(3, "checksum");
        
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
                    return PROPERTY;
                case 2:
                    return VERSION;
                case 3:
                    return CHECKSUM;
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
        j.put(as.class, new b());
        j.put(at.class, new C0014d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.PROPERTY, new v("property", (byte) 1, new y(ap.k, new w((byte) 11), new aa((byte) 12, e.class))));
        enumMap.put(e.VERSION, new v("version", (byte) 1, new w((byte) 8)));
        enumMap.put(e.CHECKSUM, new v("checksum", (byte) 1, new w((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        v.a(d.class, d);
    }

    public d() {
        this.l = 0;
    }

    public d(d dVar) {
        this.l = 0;
        this.l = dVar.l;
        if (dVar.e()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry next : dVar.a.entrySet()) {
                hashMap.put((String) next.getKey(), new e((e) next.getValue()));
            }
            this.a = hashMap;
        }
        this.b = dVar.b;
        if (dVar.k()) {
            this.c = dVar.c;
        }
    }

    public d(Map<String, e> map, int i2, String str) {
        this();
        this.a = map;
        this.b = i2;
        b(true);
        this.c = str;
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.l = 0;
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
    public d deepCopy() {
        return new d(this);
    }

    public d a(int i2) {
        this.b = i2;
        b(true);
        return this;
    }

    public d a(String str) {
        this.c = str;
        return this;
    }

    public d a(Map<String, e> map) {
        this.a = map;
        return this;
    }

    public void a(String str, e eVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, eVar);
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public int b() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    /* renamed from: b */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    public void b(boolean z) {
        this.l = g.a(this.l, 0, z);
    }

    public Map<String, e> c() {
        return this.a;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    public void clear() {
        this.a = null;
        b(false);
        this.b = 0;
        this.c = null;
    }

    public void d() {
        this.a = null;
    }

    public boolean e() {
        return this.a != null;
    }

    public int f() {
        return this.b;
    }

    public void g() {
        this.l = g.b(this.l, 0);
    }

    public boolean h() {
        return g.a(this.l, 0);
    }

    public String i() {
        return this.c;
    }

    public void j() {
        this.c = null;
    }

    public boolean k() {
        return this.c != null;
    }

    public void l() throws p {
        if (this.a == null) {
            throw new aj("Required field 'property' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new aj("Required field 'checksum' was not present! Struct: " + toString());
        }
    }

    public void read(ai aiVar) throws p {
        j.get(aiVar.D()).b().b(aiVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Imprint(");
        sb.append("property:");
        if (this.a == null) {
            sb.append("null");
        } else {
            sb.append(this.a);
        }
        sb.append(", ");
        sb.append("version:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("checksum:");
        if (this.c == null) {
            sb.append("null");
        } else {
            sb.append(this.c);
        }
        sb.append(")");
        return sb.toString();
    }

    public void write(ai aiVar) throws p {
        j.get(aiVar.D()).b().a(aiVar, this);
    }
}
