package com.tencent.bugly.proguard;

import android.content.Context;
import android.text.TextUtils;
import com.mp4parser.iso14496.part15.SyncSampleEntry;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.info.b;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class a {
    private static Proxy e;
    protected HashMap<String, HashMap<String, byte[]>> a = new HashMap<>();
    protected String b;
    i c;
    private HashMap<String, Object> d;

    a() {
        new HashMap();
        this.d = new HashMap<>();
        this.b = "GBK";
        this.c = new i();
    }

    public static aj a(int i) {
        if (i == 1) {
            return new ai();
        }
        if (i == 3) {
            return new ah();
        }
        return null;
    }

    public static ap a(Context context, int i, byte[] bArr) {
        com.tencent.bugly.crashreport.common.info.a b2 = com.tencent.bugly.crashreport.common.info.a.b();
        StrategyBean c2 = com.tencent.bugly.crashreport.common.strategy.a.a().c();
        if (b2 == null || c2 == null) {
            x.e("Can not create request pkg for parameters is invalid.", new Object[0]);
            return null;
        }
        try {
            ap apVar = new ap();
            synchronized (b2) {
                apVar.a = 1;
                apVar.b = b2.f();
                apVar.c = b2.c;
                apVar.d = b2.k;
                apVar.e = b2.m;
                apVar.f = b2.f;
                apVar.g = i;
                apVar.h = bArr == null ? "".getBytes() : bArr;
                apVar.i = b2.h;
                apVar.j = b2.i;
                apVar.k = new HashMap();
                apVar.l = b2.e();
                apVar.m = c2.p;
                apVar.o = b2.h();
                apVar.p = b.c(context);
                apVar.q = System.currentTimeMillis();
                apVar.r = b2.k();
                apVar.s = b2.j();
                apVar.t = b2.m();
                apVar.u = b2.l();
                apVar.v = b2.n();
                apVar.w = apVar.p;
                b2.getClass();
                apVar.n = "com.tencent.bugly";
                Map<String, String> map = apVar.k;
                map.put("A26", b2.y());
                Map<String, String> map2 = apVar.k;
                map2.put("A60", b2.z());
                Map<String, String> map3 = apVar.k;
                map3.put("A61", b2.A());
                Map<String, String> map4 = apVar.k;
                StringBuilder sb = new StringBuilder();
                sb.append(b2.R());
                map4.put("A62", sb.toString());
                Map<String, String> map5 = apVar.k;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(b2.S());
                map5.put("A63", sb2.toString());
                Map<String, String> map6 = apVar.k;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(b2.B);
                map6.put("F11", sb3.toString());
                Map<String, String> map7 = apVar.k;
                StringBuilder sb4 = new StringBuilder();
                sb4.append(b2.A);
                map7.put("F12", sb4.toString());
                Map<String, String> map8 = apVar.k;
                map8.put("G1", b2.u());
                Map<String, String> map9 = apVar.k;
                map9.put("A64", b2.T());
                if (b2.D) {
                    Map<String, String> map10 = apVar.k;
                    map10.put("G2", b2.L());
                    Map<String, String> map11 = apVar.k;
                    map11.put("G3", b2.M());
                    Map<String, String> map12 = apVar.k;
                    map12.put("G4", b2.N());
                    Map<String, String> map13 = apVar.k;
                    map13.put("G5", b2.O());
                    Map<String, String> map14 = apVar.k;
                    map14.put("G6", b2.P());
                    Map<String, String> map15 = apVar.k;
                    map15.put("G7", Long.toString(b2.Q()));
                }
                Map<String, String> map16 = apVar.k;
                map16.put("D3", b2.l);
                if (com.tencent.bugly.b.b != null) {
                    for (com.tencent.bugly.a next : com.tencent.bugly.b.b) {
                        if (!(next.versionKey == null || next.version == null)) {
                            apVar.k.put(next.versionKey, next.version);
                        }
                    }
                }
                apVar.k.put("G15", z.b("G15", ""));
                apVar.k.put("D4", z.b("D4", "0"));
            }
            u a2 = u.a();
            if (!(a2 == null || a2.a || bArr == null)) {
                apVar.h = z.a(apVar.h, 2, 1, c2.u);
                if (apVar.h == null) {
                    x.e("reqPkg sbuffer error!", new Object[0]);
                    return null;
                }
            }
            Map<String, String> F = b2.F();
            if (F != null) {
                for (Map.Entry next2 : F.entrySet()) {
                    apVar.k.put(next2.getKey(), next2.getValue());
                }
            }
            return apVar;
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static aq a(byte[] bArr, boolean z) {
        if (bArr != null) {
            try {
                d dVar = new d();
                dVar.c();
                dVar.a("utf-8");
                dVar.a(bArr);
                Object b2 = dVar.b("detail", new aq());
                aq cast = aq.class.isInstance(b2) ? aq.class.cast(b2) : null;
                if (z || cast == null || cast.c == null || cast.c.length <= 0) {
                    return cast;
                }
                x.c("resp buf %d", Integer.valueOf(cast.c.length));
                cast.c = z.b(cast.c, 2, 1, StrategyBean.d);
                if (cast.c != null) {
                    return cast;
                }
                x.e("resp sbuffer error!", new Object[0]);
                return null;
            } catch (Throwable th) {
                if (!x.b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    public static at a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        at atVar = new at();
        atVar.a = userInfoBean.e;
        atVar.e = userInfoBean.j;
        atVar.d = userInfoBean.c;
        atVar.c = userInfoBean.d;
        atVar.g = com.tencent.bugly.crashreport.common.info.a.b().i();
        atVar.h = userInfoBean.o == 1;
        switch (userInfoBean.b) {
            case 1:
                atVar.b = 1;
                break;
            case 2:
                atVar.b = 4;
                break;
            case 3:
                atVar.b = 2;
                break;
            case 4:
                atVar.b = 3;
                break;
            default:
                if (userInfoBean.b >= 10 && userInfoBean.b < 20) {
                    atVar.b = (byte) userInfoBean.b;
                    break;
                } else {
                    x.e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.b));
                    return null;
                }
                break;
        }
        atVar.f = new HashMap();
        if (userInfoBean.p >= 0) {
            Map<String, String> map = atVar.f;
            StringBuilder sb = new StringBuilder();
            sb.append(userInfoBean.p);
            map.put("C01", sb.toString());
        }
        if (userInfoBean.q >= 0) {
            Map<String, String> map2 = atVar.f;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(userInfoBean.q);
            map2.put("C02", sb2.toString());
        }
        if (userInfoBean.r != null && userInfoBean.r.size() > 0) {
            for (Map.Entry next : userInfoBean.r.entrySet()) {
                Map<String, String> map3 = atVar.f;
                map3.put("C03_" + ((String) next.getKey()), next.getValue());
            }
        }
        if (userInfoBean.s != null && userInfoBean.s.size() > 0) {
            for (Map.Entry next2 : userInfoBean.s.entrySet()) {
                Map<String, String> map4 = atVar.f;
                map4.put("C04_" + ((String) next2.getKey()), next2.getValue());
            }
        }
        Map<String, String> map5 = atVar.f;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(!userInfoBean.l);
        map5.put("A36", sb3.toString());
        Map<String, String> map6 = atVar.f;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(userInfoBean.g);
        map6.put("F02", sb4.toString());
        Map<String, String> map7 = atVar.f;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(userInfoBean.h);
        map7.put("F03", sb5.toString());
        Map<String, String> map8 = atVar.f;
        map8.put("F04", userInfoBean.j);
        Map<String, String> map9 = atVar.f;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(userInfoBean.i);
        map9.put("F05", sb6.toString());
        Map<String, String> map10 = atVar.f;
        map10.put("F06", userInfoBean.m);
        Map<String, String> map11 = atVar.f;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(userInfoBean.k);
        map11.put("F10", sb7.toString());
        x.c("summary type %d vm:%d", Byte.valueOf(atVar.b), Integer.valueOf(atVar.f.size()));
        return atVar;
    }

    public static au a(List<UserInfoBean> list, int i) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }
        com.tencent.bugly.crashreport.common.info.a b2 = com.tencent.bugly.crashreport.common.info.a.b();
        if (b2 == null) {
            return null;
        }
        b2.t();
        au auVar = new au();
        auVar.b = b2.d;
        auVar.c = b2.h();
        ArrayList<at> arrayList = new ArrayList<>();
        for (UserInfoBean a2 : list) {
            at a3 = a(a2);
            if (a3 != null) {
                arrayList.add(a3);
            }
        }
        auVar.d = arrayList;
        auVar.e = new HashMap();
        Map<String, String> map = auVar.e;
        map.put("A7", b2.g);
        Map<String, String> map2 = auVar.e;
        map2.put("A6", b2.s());
        Map<String, String> map3 = auVar.e;
        map3.put("A5", b2.r());
        Map<String, String> map4 = auVar.e;
        StringBuilder sb = new StringBuilder();
        sb.append(b2.p());
        map4.put("A2", sb.toString());
        Map<String, String> map5 = auVar.e;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(b2.p());
        map5.put("A1", sb2.toString());
        Map<String, String> map6 = auVar.e;
        map6.put("A24", b2.i);
        Map<String, String> map7 = auVar.e;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(b2.q());
        map7.put("A17", sb3.toString());
        Map<String, String> map8 = auVar.e;
        map8.put("A15", b2.w());
        Map<String, String> map9 = auVar.e;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(b2.x());
        map9.put("A13", sb4.toString());
        Map<String, String> map10 = auVar.e;
        map10.put("F08", b2.w);
        Map<String, String> map11 = auVar.e;
        map11.put("F09", b2.x);
        Map<String, String> G = b2.G();
        if (G != null && G.size() > 0) {
            for (Map.Entry next : G.entrySet()) {
                Map<String, String> map12 = auVar.e;
                map12.put("C04_" + ((String) next.getKey()), next.getValue());
            }
        }
        switch (i) {
            case 1:
                auVar.a = 1;
                return auVar;
            case 2:
                auVar.a = 2;
                return auVar;
            default:
                x.e("unknown up type %d ", Integer.valueOf(i));
                return null;
        }
    }

    public static <T extends k> T a(byte[] bArr, Class<T> cls) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length <= 0) {
            return null;
        }
        try {
            T t = (k) cls.newInstance();
            i iVar = new i(bArr);
            iVar.a("utf-8");
            t.a(iVar);
            return t;
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static String a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayList.size(); i++) {
            String str = arrayList.get(i);
            if (str.equals("java.lang.Integer") || str.equals("int")) {
                str = "int32";
            } else if (str.equals("java.lang.Boolean") || str.equals("boolean")) {
                str = "bool";
            } else if (str.equals("java.lang.Byte") || str.equals("byte")) {
                str = "char";
            } else if (str.equals("java.lang.Double") || str.equals("double")) {
                str = "double";
            } else if (str.equals("java.lang.Float") || str.equals("float")) {
                str = "float";
            } else if (str.equals("java.lang.Long") || str.equals("long")) {
                str = "int64";
            } else if (str.equals("java.lang.Short") || str.equals("short")) {
                str = "short";
            } else if (str.equals("java.lang.Character")) {
                throw new IllegalArgumentException("can not support java.lang.Character");
            } else if (str.equals("java.lang.String")) {
                str = "string";
            } else if (str.equals("java.util.List")) {
                str = "list";
            } else if (str.equals("java.util.Map")) {
                str = "map";
            }
            arrayList.set(i, str);
        }
        Collections.reverse(arrayList);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String str2 = arrayList.get(i2);
            if (str2.equals("list")) {
                int i3 = i2 - 1;
                arrayList.set(i3, "<" + arrayList.get(i3));
                arrayList.set(0, arrayList.get(0) + ">");
            } else if (str2.equals("map")) {
                int i4 = i2 - 1;
                arrayList.set(i4, "<" + arrayList.get(i4) + ListUtils.DEFAULT_JOIN_SEPARATOR);
                arrayList.set(0, arrayList.get(0) + ">");
            } else if (str2.equals("Array")) {
                int i5 = i2 - 1;
                arrayList.set(i5, "<" + arrayList.get(i5));
                arrayList.set(0, arrayList.get(0) + ">");
            }
        }
        Collections.reverse(arrayList);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
        }
        return stringBuffer.toString();
    }

    public static void a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            e = null;
        } else {
            e = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str, i));
        }
    }

    public static void a(InetAddress inetAddress, int i) {
        if (inetAddress == null) {
            e = null;
        } else {
            e = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(inetAddress, i));
        }
    }

    private void a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            } else if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                a(arrayList, Array.get(obj, 0));
            } else {
                arrayList.add("Array");
                arrayList.add("?");
            }
        } else if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        } else if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                a(arrayList, list.get(0));
            } else {
                arrayList.add("?");
            }
        } else if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                a(arrayList, obj2);
                return;
            }
            arrayList.add("?");
            arrayList.add("?");
        } else {
            arrayList.add(obj.getClass().getName());
        }
    }

    public static byte[] a(k kVar) {
        try {
            j jVar = new j();
            jVar.a("utf-8");
            kVar.a(jVar);
            return jVar.b();
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] a(Object obj) {
        try {
            d dVar = new d();
            dVar.c();
            dVar.a("utf-8");
            dVar.b(1);
            dVar.b("RqdServer");
            dVar.c(SyncSampleEntry.TYPE);
            dVar.a("detail", obj);
            return dVar.a();
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static Proxy b() {
        return e;
    }

    public void a(String str) {
        this.b = str;
    }

    public <T> void a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (!(t instanceof Set)) {
            j jVar = new j();
            jVar.a(this.b);
            jVar.a((Object) t, 0);
            byte[] a2 = l.a(jVar.a());
            HashMap hashMap = new HashMap(1);
            ArrayList arrayList = new ArrayList(1);
            a((ArrayList<String>) arrayList, (Object) t);
            hashMap.put(a((ArrayList<String>) arrayList), a2);
            this.d.remove(str);
            this.a.put(str, hashMap);
        } else {
            throw new IllegalArgumentException("can not support Set");
        }
    }

    public void a(byte[] bArr) {
        this.c.a(bArr);
        this.c.a(this.b);
        HashMap hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("", new byte[0]);
        hashMap.put("", hashMap2);
        this.a = this.c.a(hashMap, 0, false);
    }

    public byte[] a() {
        j jVar = new j(0);
        jVar.a(this.b);
        jVar.a(this.a, 0);
        return l.a(jVar.a());
    }
}
