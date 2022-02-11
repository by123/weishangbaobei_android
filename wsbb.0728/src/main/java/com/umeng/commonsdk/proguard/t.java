package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.q;
import com.umeng.commonsdk.proguard.t;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class t<T extends t<?, ?>, F extends q> implements j<T, F> {
    private static final Map<Class<? extends aq>, ar> c = new HashMap();
    protected Object a;
    protected F b;

    private static class a extends as<t> {
        private a() {
        }

        /* renamed from: a */
        public void b(ai aiVar, t tVar) throws p {
            tVar.b = null;
            tVar.a = null;
            aiVar.j();
            ad l = aiVar.l();
            tVar.a = tVar.a(aiVar, l);
            if (tVar.a != null) {
                tVar.b = tVar.a(l.c);
            }
            aiVar.m();
            aiVar.l();
            aiVar.k();
        }

        /* renamed from: b */
        public void a(ai aiVar, t tVar) throws p {
            if (tVar.a() == null || tVar.b() == null) {
                throw new aj("Cannot write a TUnion with no set value!");
            }
            aiVar.a(tVar.d());
            aiVar.a(tVar.c(tVar.b));
            tVar.a(aiVar);
            aiVar.c();
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

    private static class c extends at<t> {
        private c() {
        }

        /* renamed from: a */
        public void b(ai aiVar, t tVar) throws p {
            tVar.b = null;
            tVar.a = null;
            short v = aiVar.v();
            tVar.a = tVar.a(aiVar, v);
            if (tVar.a != null) {
                tVar.b = tVar.a(v);
            }
        }

        /* renamed from: b */
        public void a(ai aiVar, t tVar) throws p {
            if (tVar.a() == null || tVar.b() == null) {
                throw new aj("Cannot write a TUnion with no set value!");
            }
            aiVar.a(tVar.b.a());
            tVar.b(aiVar);
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

    static {
        c.put(as.class, new b());
        c.put(at.class, new d());
    }

    protected t() {
        this.b = null;
        this.a = null;
    }

    protected t(F f, Object obj) {
        a(f, obj);
    }

    protected t(t<T, F> tVar) {
        if (tVar.getClass().equals(getClass())) {
            this.b = tVar.b;
            this.a = a(tVar.a);
            return;
        }
        throw new ClassCastException();
    }

    private static Object a(Object obj) {
        return obj instanceof j ? ((j) obj).deepCopy() : obj instanceof ByteBuffer ? k.d((ByteBuffer) obj) : obj instanceof List ? a((List) obj) : obj instanceof Set ? a((Set) obj) : obj instanceof Map ? a((Map<Object, Object>) (Map) obj) : obj;
    }

    private static List a(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Object a2 : list) {
            arrayList.add(a(a2));
        }
        return arrayList;
    }

    private static Map a(Map<Object, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            hashMap.put(a(next.getKey()), a(next.getValue()));
        }
        return hashMap;
    }

    private static Set a(Set set) {
        HashSet hashSet = new HashSet();
        for (Object a2 : set) {
            hashSet.add(a(a2));
        }
        return hashSet;
    }

    public F a() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public abstract F a(short s);

    public Object a(int i) {
        return a(a((short) i));
    }

    /* access modifiers changed from: protected */
    public abstract Object a(ai aiVar, ad adVar) throws p;

    /* access modifiers changed from: protected */
    public abstract Object a(ai aiVar, short s) throws p;

    public Object a(F f) {
        if (f == this.b) {
            return b();
        }
        throw new IllegalArgumentException("Cannot get the value of field " + f + " because union's set field is " + this.b);
    }

    public void a(int i, Object obj) {
        a(a((short) i), obj);
    }

    /* access modifiers changed from: protected */
    public abstract void a(ai aiVar) throws p;

    public void a(F f, Object obj) {
        b(f, obj);
        this.b = f;
        this.a = obj;
    }

    public Object b() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public abstract void b(ai aiVar) throws p;

    /* access modifiers changed from: protected */
    public abstract void b(F f, Object obj) throws ClassCastException;

    public boolean b(int i) {
        return b(a((short) i));
    }

    public boolean b(F f) {
        return this.b == f;
    }

    /* access modifiers changed from: protected */
    public abstract ad c(F f);

    public boolean c() {
        return this.b != null;
    }

    public final void clear() {
        this.b = null;
        this.a = null;
    }

    /* access modifiers changed from: protected */
    public abstract an d();

    public void read(ai aiVar) throws p {
        c.get(aiVar.D()).b().b(aiVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getClass().getSimpleName());
        sb.append(" ");
        if (a() != null) {
            Object b2 = b();
            sb.append(c(a()).a);
            sb.append(":");
            if (b2 instanceof ByteBuffer) {
                k.a((ByteBuffer) b2, sb);
            } else {
                sb.append(b2.toString());
            }
        }
        sb.append(">");
        return sb.toString();
    }

    public void write(ai aiVar) throws p {
        c.get(aiVar.D()).b().a(aiVar, this);
    }
}
