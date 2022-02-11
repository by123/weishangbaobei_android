package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.aa;
import com.umeng.commonsdk.proguard.ac;
import com.umeng.commonsdk.proguard.ad;
import com.umeng.commonsdk.proguard.ae;
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
import com.umeng.commonsdk.proguard.j;
import com.umeng.commonsdk.proguard.p;
import com.umeng.commonsdk.proguard.q;
import com.umeng.commonsdk.proguard.v;
import com.umeng.commonsdk.proguard.w;
import com.umeng.commonsdk.proguard.x;
import com.umeng.commonsdk.proguard.y;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: IdTracking */
public class c implements j<c, e>, Serializable, Cloneable {
    public static final Map<e, v> d;
    private static final long e = -5764118265293965743L;
    /* access modifiers changed from: private */
    public static final an f = new an("IdTracking");
    /* access modifiers changed from: private */
    public static final ad g = new ad("snapshots", ap.k, 1);
    /* access modifiers changed from: private */
    public static final ad h = new ad("journals", ap.m, 2);
    /* access modifiers changed from: private */
    public static final ad i = new ad("checksum", (byte) 11, 3);
    private static final Map<Class<? extends aq>, ar> j = new HashMap();
    public Map<String, b> a;
    public List<a> b;
    public String c;
    private e[] k;

    static {
        j.put(as.class, new b());
        j.put(at.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.SNAPSHOTS, new v("snapshots", (byte) 1, new y(ap.k, new w((byte) 11), new aa((byte) 12, b.class))));
        enumMap.put(e.JOURNALS, new v("journals", (byte) 2, new x(ap.m, new aa((byte) 12, a.class))));
        enumMap.put(e.CHECKSUM, new v("checksum", (byte) 2, new w((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        v.a(c.class, d);
    }

    /* compiled from: IdTracking */
    public enum e implements q {
        SNAPSHOTS(1, "snapshots"),
        JOURNALS(2, "journals"),
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

        public static e a(int i) {
            switch (i) {
                case 1:
                    return SNAPSHOTS;
                case 2:
                    return JOURNALS;
                case 3:
                    return CHECKSUM;
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
            return d.get(str);
        }

        private e(short s, String str) {
            this.e = s;
            this.f = str;
        }

        public short a() {
            return this.e;
        }

        public String b() {
            return this.f;
        }
    }

    public c() {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
    }

    public c(Map<String, b> map) {
        this();
        this.a = map;
    }

    public c(c cVar) {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
        if (cVar.e()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry next : cVar.a.entrySet()) {
                hashMap.put((String) next.getKey(), new b((b) next.getValue()));
            }
            this.a = hashMap;
        }
        if (cVar.j()) {
            ArrayList arrayList = new ArrayList();
            for (a aVar : cVar.b) {
                arrayList.add(new a(aVar));
            }
            this.b = arrayList;
        }
        if (cVar.m()) {
            this.c = cVar.c;
        }
    }

    /* renamed from: a */
    public c deepCopy() {
        return new c(this);
    }

    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public int b() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public void a(String str, b bVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, bVar);
    }

    public Map<String, b> c() {
        return this.a;
    }

    public c a(Map<String, b> map) {
        this.a = map;
        return this;
    }

    public void d() {
        this.a = null;
    }

    public boolean e() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public int f() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }

    public Iterator<a> g() {
        if (this.b == null) {
            return null;
        }
        return this.b.iterator();
    }

    public void a(a aVar) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(aVar);
    }

    public List<a> h() {
        return this.b;
    }

    public c a(List<a> list) {
        this.b = list;
        return this;
    }

    public void i() {
        this.b = null;
    }

    public boolean j() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public String k() {
        return this.c;
    }

    public c a(String str) {
        this.c = str;
        return this;
    }

    public void l() {
        this.c = null;
    }

    public boolean m() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    /* renamed from: a */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    public void read(ai aiVar) throws p {
        j.get(aiVar.D()).b().b(aiVar, this);
    }

    public void write(ai aiVar) throws p {
        j.get(aiVar.D()).b().a(aiVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdTracking(");
        sb.append("snapshots:");
        if (this.a == null) {
            sb.append("null");
        } else {
            sb.append(this.a);
        }
        if (j()) {
            sb.append(", ");
            sb.append("journals:");
            if (this.b == null) {
                sb.append("null");
            } else {
                sb.append(this.b);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("checksum:");
            if (this.c == null) {
                sb.append("null");
            } else {
                sb.append(this.c);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void n() throws p {
        if (this.a == null) {
            throw new aj("Required field 'snapshots' was not present! Struct: " + toString());
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
            read(new ac(new au((InputStream) objectInputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdTracking */
    private static class b implements ar {
        private b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* compiled from: IdTracking */
    private static class a extends as<c> {
        private a() {
        }

        /* renamed from: a */
        public void b(ai aiVar, c cVar) throws p {
            aiVar.j();
            while (true) {
                ad l = aiVar.l();
                if (l.b == 0) {
                    aiVar.k();
                    cVar.n();
                    return;
                }
                int i = 0;
                switch (l.c) {
                    case 1:
                        if (l.b != 13) {
                            al.a(aiVar, l.b);
                            break;
                        } else {
                            af n = aiVar.n();
                            cVar.a = new HashMap(n.c * 2);
                            while (i < n.c) {
                                String z = aiVar.z();
                                b bVar = new b();
                                bVar.read(aiVar);
                                cVar.a.put(z, bVar);
                                i++;
                            }
                            aiVar.o();
                            cVar.a(true);
                            break;
                        }
                    case 2:
                        if (l.b != 15) {
                            al.a(aiVar, l.b);
                            break;
                        } else {
                            ae p = aiVar.p();
                            cVar.b = new ArrayList(p.b);
                            while (i < p.b) {
                                a aVar = new a();
                                aVar.read(aiVar);
                                cVar.b.add(aVar);
                                i++;
                            }
                            aiVar.q();
                            cVar.b(true);
                            break;
                        }
                    case 3:
                        if (l.b != 11) {
                            al.a(aiVar, l.b);
                            break;
                        } else {
                            cVar.c = aiVar.z();
                            cVar.c(true);
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
        public void a(ai aiVar, c cVar) throws p {
            cVar.n();
            aiVar.a(c.f);
            if (cVar.a != null) {
                aiVar.a(c.g);
                aiVar.a(new af((byte) 11, (byte) 12, cVar.a.size()));
                for (Map.Entry next : cVar.a.entrySet()) {
                    aiVar.a((String) next.getKey());
                    ((b) next.getValue()).write(aiVar);
                }
                aiVar.e();
                aiVar.c();
            }
            if (cVar.b != null && cVar.j()) {
                aiVar.a(c.h);
                aiVar.a(new ae((byte) 12, cVar.b.size()));
                for (a write : cVar.b) {
                    write.write(aiVar);
                }
                aiVar.f();
                aiVar.c();
            }
            if (cVar.c != null && cVar.m()) {
                aiVar.a(c.i);
                aiVar.a(cVar.c);
                aiVar.c();
            }
            aiVar.d();
            aiVar.b();
        }
    }

    /* compiled from: IdTracking */
    private static class d implements ar {
        private d() {
        }

        /* renamed from: a */
        public C0013c b() {
            return new C0013c();
        }
    }

    /* renamed from: com.umeng.commonsdk.statistics.proto.c$c  reason: collision with other inner class name */
    /* compiled from: IdTracking */
    private static class C0013c extends at<c> {
        private C0013c() {
        }

        public void a(ai aiVar, c cVar) throws p {
            ao aoVar = (ao) aiVar;
            aoVar.a(cVar.a.size());
            for (Map.Entry next : cVar.a.entrySet()) {
                aoVar.a((String) next.getKey());
                ((b) next.getValue()).write(aoVar);
            }
            BitSet bitSet = new BitSet();
            if (cVar.j()) {
                bitSet.set(0);
            }
            if (cVar.m()) {
                bitSet.set(1);
            }
            aoVar.a(bitSet, 2);
            if (cVar.j()) {
                aoVar.a(cVar.b.size());
                for (a write : cVar.b) {
                    write.write(aoVar);
                }
            }
            if (cVar.m()) {
                aoVar.a(cVar.c);
            }
        }

        public void b(ai aiVar, c cVar) throws p {
            ao aoVar = (ao) aiVar;
            af afVar = new af((byte) 11, (byte) 12, aoVar.w());
            cVar.a = new HashMap(afVar.c * 2);
            for (int i = 0; i < afVar.c; i++) {
                String z = aoVar.z();
                b bVar = new b();
                bVar.read(aoVar);
                cVar.a.put(z, bVar);
            }
            cVar.a(true);
            BitSet b = aoVar.b(2);
            if (b.get(0)) {
                ae aeVar = new ae((byte) 12, aoVar.w());
                cVar.b = new ArrayList(aeVar.b);
                for (int i2 = 0; i2 < aeVar.b; i2++) {
                    a aVar = new a();
                    aVar.read(aoVar);
                    cVar.b.add(aVar);
                }
                cVar.b(true);
            }
            if (b.get(1)) {
                cVar.c = aoVar.z();
                cVar.c(true);
            }
        }
    }
}
