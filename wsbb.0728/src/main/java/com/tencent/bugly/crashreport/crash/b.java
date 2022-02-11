package com.tencent.bugly.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.ak;
import com.tencent.bugly.proguard.am;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ao;
import com.tencent.bugly.proguard.ap;
import com.tencent.bugly.proguard.k;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.r;
import com.tencent.bugly.proguard.t;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public final class b {
    private static int a;
    private Context b;
    private u c;
    private p d;
    private a e;
    private o f;
    private BuglyStrategy.a g;

    public b(int i, Context context, u uVar, p pVar, a aVar, BuglyStrategy.a aVar2, o oVar) {
        a = i;
        this.b = context;
        this.c = uVar;
        this.d = pVar;
        this.e = aVar;
        this.g = aVar2;
        this.f = oVar;
    }

    private static CrashDetailBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            CrashDetailBean crashDetailBean = (CrashDetailBean) z.a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean == null) {
                return crashDetailBean;
            }
            crashDetailBean.a = j;
            return crashDetailBean;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private CrashDetailBean a(List<a> list, CrashDetailBean crashDetailBean) {
        CrashDetailBean crashDetailBean2;
        CrashDetailBean crashDetailBean3;
        List<CrashDetailBean> b2;
        String[] split;
        CrashDetailBean crashDetailBean4 = null;
        if (list == null || list.size() == 0) {
            return crashDetailBean;
        }
        ArrayList arrayList = new ArrayList(10);
        for (a next : list) {
            if (next.e) {
                arrayList.add(next);
            }
        }
        if (arrayList.size() <= 0 || (b2 = b((List<a>) arrayList)) == null || b2.size() <= 0) {
            crashDetailBean2 = null;
        } else {
            Collections.sort(b2);
            int i = 0;
            while (i < b2.size()) {
                CrashDetailBean crashDetailBean5 = b2.get(i);
                if (i != 0) {
                    if (!(crashDetailBean5.s == null || (split = crashDetailBean5.s.split("\n")) == null)) {
                        for (String str : split) {
                            if (!crashDetailBean4.s.contains(str)) {
                                crashDetailBean4.t++;
                                crashDetailBean4.s += str + "\n";
                            }
                        }
                    }
                    crashDetailBean5 = crashDetailBean4;
                }
                i++;
                crashDetailBean4 = crashDetailBean5;
            }
            crashDetailBean2 = crashDetailBean4;
        }
        if (crashDetailBean2 == null) {
            crashDetailBean.j = true;
            crashDetailBean.t = 0;
            crashDetailBean.s = "";
            crashDetailBean3 = crashDetailBean;
        } else {
            crashDetailBean3 = crashDetailBean2;
        }
        for (a next2 : list) {
            if (!next2.e && !next2.d) {
                String str2 = crashDetailBean3.s;
                StringBuilder sb = new StringBuilder();
                sb.append(next2.b);
                if (!str2.contains(sb.toString())) {
                    crashDetailBean3.t++;
                    crashDetailBean3.s += next2.b + "\n";
                }
            }
        }
        if (crashDetailBean3.r != crashDetailBean.r) {
            String str3 = crashDetailBean3.s;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.r);
            if (!str3.contains(sb2.toString())) {
                crashDetailBean3.t++;
                crashDetailBean3.s += crashDetailBean.r + "\n";
            }
        }
        return crashDetailBean3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0059 A[Catch:{ all -> 0x00bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005e A[SYNTHETIC, Splitter:B:22:0x005e] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c3 A[SYNTHETIC, Splitter:B:48:0x00c3] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    private static am a(String str, Context context, String str2) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        if (str2 == null || context == null) {
            x.d("rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}", new Object[0]);
            return null;
        }
        x.c("zip %s", str2);
        File file = new File(str2);
        File file2 = new File(context.getCacheDir(), str);
        if (!z.a(file, file2, 5000)) {
            x.d("zip fail!", new Object[0]);
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            fileInputStream = new FileInputStream(file2);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                    byteArrayOutputStream.flush();
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                x.c("read bytes :%d", Integer.valueOf(byteArray.length));
                am amVar = new am((byte) 2, file2.getName(), byteArray);
                try {
                    fileInputStream.close();
                } catch (IOException e2) {
                    if (!x.a(e2)) {
                        e2.printStackTrace();
                    }
                }
                if (file2.exists()) {
                    x.c("del tmp", new Object[0]);
                    file2.delete();
                }
                return amVar;
            } catch (Throwable th) {
                th = th;
                try {
                    if (!x.a(th)) {
                    }
                    if (fileInputStream != null) {
                    }
                    if (file2.exists()) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                    }
                    if (file2.exists()) {
                    }
                    throw th;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e3) {
                    if (!x.a(e3)) {
                        e3.printStackTrace();
                    }
                }
            }
            if (file2.exists()) {
                x.c("del tmp", new Object[0]);
                file2.delete();
            }
            throw th;
        }
    }

    private static an a(Context context, CrashDetailBean crashDetailBean, com.tencent.bugly.crashreport.common.info.a aVar) {
        am a2;
        am a3;
        am amVar;
        if (context == null || crashDetailBean == null || aVar == null) {
            x.d("enExp args == null", new Object[0]);
            return null;
        }
        an anVar = new an();
        switch (crashDetailBean.b) {
            case 0:
                anVar.a = crashDetailBean.j ? "200" : "100";
                break;
            case 1:
                anVar.a = crashDetailBean.j ? "201" : "101";
                break;
            case 2:
                anVar.a = crashDetailBean.j ? "202" : "102";
                break;
            case 3:
                anVar.a = crashDetailBean.j ? "203" : "103";
                break;
            case 4:
                anVar.a = crashDetailBean.j ? "204" : "104";
                break;
            case 5:
                anVar.a = crashDetailBean.j ? "207" : "107";
                break;
            case 6:
                anVar.a = crashDetailBean.j ? "206" : "106";
                break;
            case 7:
                anVar.a = crashDetailBean.j ? "208" : "108";
                break;
            default:
                x.e("crash type error! %d", Integer.valueOf(crashDetailBean.b));
                break;
        }
        anVar.b = crashDetailBean.r;
        anVar.c = crashDetailBean.n;
        anVar.d = crashDetailBean.o;
        anVar.e = crashDetailBean.p;
        anVar.g = crashDetailBean.q;
        anVar.h = crashDetailBean.z;
        anVar.i = crashDetailBean.c;
        anVar.j = null;
        anVar.l = crashDetailBean.m;
        anVar.m = crashDetailBean.e;
        anVar.f = crashDetailBean.B;
        anVar.t = com.tencent.bugly.crashreport.common.info.a.b().i();
        anVar.n = null;
        if (crashDetailBean.i != null && crashDetailBean.i.size() > 0) {
            anVar.o = new ArrayList<>();
            for (Map.Entry next : crashDetailBean.i.entrySet()) {
                ak akVar = new ak();
                akVar.a = ((PlugInBean) next.getValue()).a;
                akVar.c = ((PlugInBean) next.getValue()).c;
                akVar.d = ((PlugInBean) next.getValue()).b;
                akVar.b = aVar.r();
                anVar.o.add(akVar);
            }
        }
        if (crashDetailBean.h != null && crashDetailBean.h.size() > 0) {
            anVar.p = new ArrayList<>();
            for (Map.Entry next2 : crashDetailBean.h.entrySet()) {
                ak akVar2 = new ak();
                akVar2.a = ((PlugInBean) next2.getValue()).a;
                akVar2.c = ((PlugInBean) next2.getValue()).c;
                akVar2.d = ((PlugInBean) next2.getValue()).b;
                anVar.p.add(akVar2);
            }
        }
        if (crashDetailBean.j) {
            anVar.k = crashDetailBean.t;
            if (crashDetailBean.s != null && crashDetailBean.s.length() > 0) {
                if (anVar.q == null) {
                    anVar.q = new ArrayList<>();
                }
                try {
                    anVar.q.add(new am((byte) 1, "alltimes.txt", crashDetailBean.s.getBytes("utf-8")));
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                    anVar.q = null;
                }
            }
            x.c("crashcount:%d sz:%d", Integer.valueOf(anVar.k), Integer.valueOf(anVar.q != null ? anVar.q.size() : 0));
        }
        if (crashDetailBean.w != null) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            try {
                anVar.q.add(new am((byte) 1, "log.txt", crashDetailBean.w.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
                anVar.q = null;
            }
        }
        if (crashDetailBean.x != null) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            try {
                anVar.q.add(new am((byte) 1, "jniLog.txt", crashDetailBean.x.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e4) {
                e4.printStackTrace();
                anVar.q = null;
            }
        }
        if (!z.a(crashDetailBean.V)) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            try {
                amVar = new am((byte) 1, "crashInfos.txt", crashDetailBean.V.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e5) {
                e5.printStackTrace();
                amVar = null;
            }
            if (amVar != null) {
                x.c("attach crash infos", new Object[0]);
                anVar.q.add(amVar);
            }
        }
        if (crashDetailBean.W != null) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            am a4 = a("backupRecord.zip", context, crashDetailBean.W);
            if (a4 != null) {
                x.c("attach backup record", new Object[0]);
                anVar.q.add(a4);
            }
        }
        if (crashDetailBean.y != null && crashDetailBean.y.length > 0) {
            am amVar2 = new am((byte) 2, "buglylog.zip", crashDetailBean.y);
            x.c("attach user log", new Object[0]);
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            anVar.q.add(amVar2);
        }
        if (crashDetailBean.b == 3) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            x.c("crashBean.anrMessages:%s", crashDetailBean.P);
            if (crashDetailBean.P != null && crashDetailBean.P.containsKey("BUGLY_CR_01")) {
                try {
                    if (!TextUtils.isEmpty(crashDetailBean.P.get("BUGLY_CR_01"))) {
                        anVar.q.add(new am((byte) 1, "anrMessage.txt", crashDetailBean.P.get("BUGLY_CR_01").getBytes("utf-8")));
                        x.c("attach anr message", new Object[0]);
                    }
                } catch (UnsupportedEncodingException e6) {
                    e6.printStackTrace();
                    anVar.q = null;
                }
                crashDetailBean.P.remove("BUGLY_CR_01");
            }
            if (!(crashDetailBean.v == null || (a3 = a("trace.zip", context, crashDetailBean.v)) == null)) {
                x.c("attach traces", new Object[0]);
                anVar.q.add(a3);
            }
        }
        if (crashDetailBean.b == 1) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            if (!(crashDetailBean.v == null || (a2 = a("tomb.zip", context, crashDetailBean.v)) == null)) {
                x.c("attach tombs", new Object[0]);
                anVar.q.add(a2);
            }
        }
        if (aVar.E != null && !aVar.E.isEmpty()) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            StringBuilder sb = new StringBuilder();
            for (String append : aVar.E) {
                sb.append(append);
            }
            try {
                anVar.q.add(new am((byte) 1, "martianlog.txt", sb.toString().getBytes("utf-8")));
                x.c("attach pageTracingList", new Object[0]);
            } catch (UnsupportedEncodingException e7) {
                e7.printStackTrace();
            }
        }
        if (crashDetailBean.U != null && crashDetailBean.U.length > 0) {
            if (anVar.q == null) {
                anVar.q = new ArrayList<>();
            }
            anVar.q.add(new am((byte) 1, "userExtraByteData", crashDetailBean.U));
            x.c("attach extraData", new Object[0]);
        }
        anVar.r = new HashMap();
        Map<String, String> map = anVar.r;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(crashDetailBean.C);
        map.put("A9", sb2.toString());
        Map<String, String> map2 = anVar.r;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(crashDetailBean.D);
        map2.put("A11", sb3.toString());
        Map<String, String> map3 = anVar.r;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(crashDetailBean.E);
        map3.put("A10", sb4.toString());
        Map<String, String> map4 = anVar.r;
        map4.put("A23", crashDetailBean.f);
        Map<String, String> map5 = anVar.r;
        map5.put("A7", aVar.g);
        Map<String, String> map6 = anVar.r;
        map6.put("A6", aVar.s());
        Map<String, String> map7 = anVar.r;
        map7.put("A5", aVar.r());
        Map<String, String> map8 = anVar.r;
        map8.put("A22", aVar.h());
        Map<String, String> map9 = anVar.r;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(crashDetailBean.G);
        map9.put("A2", sb5.toString());
        Map<String, String> map10 = anVar.r;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(crashDetailBean.F);
        map10.put("A1", sb6.toString());
        Map<String, String> map11 = anVar.r;
        map11.put("A24", aVar.i);
        Map<String, String> map12 = anVar.r;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(crashDetailBean.H);
        map12.put("A17", sb7.toString());
        Map<String, String> map13 = anVar.r;
        map13.put("A3", aVar.k());
        Map<String, String> map14 = anVar.r;
        map14.put("A16", aVar.m());
        Map<String, String> map15 = anVar.r;
        map15.put("A25", aVar.n());
        Map<String, String> map16 = anVar.r;
        map16.put("A14", aVar.l());
        Map<String, String> map17 = anVar.r;
        map17.put("A15", aVar.w());
        Map<String, String> map18 = anVar.r;
        StringBuilder sb8 = new StringBuilder();
        sb8.append(aVar.x());
        map18.put("A13", sb8.toString());
        Map<String, String> map19 = anVar.r;
        map19.put("A34", crashDetailBean.A);
        if (aVar.y != null) {
            Map<String, String> map20 = anVar.r;
            map20.put("productIdentify", aVar.y);
        }
        try {
            Map<String, String> map21 = anVar.r;
            map21.put("A26", URLEncoder.encode(crashDetailBean.I, "utf-8"));
        } catch (UnsupportedEncodingException e8) {
            e8.printStackTrace();
        }
        if (crashDetailBean.b == 1) {
            Map<String, String> map22 = anVar.r;
            map22.put("A27", crashDetailBean.K);
            Map<String, String> map23 = anVar.r;
            map23.put("A28", crashDetailBean.J);
            Map<String, String> map24 = anVar.r;
            StringBuilder sb9 = new StringBuilder();
            sb9.append(crashDetailBean.k);
            map24.put("A29", sb9.toString());
        }
        Map<String, String> map25 = anVar.r;
        map25.put("A30", crashDetailBean.L);
        Map<String, String> map26 = anVar.r;
        StringBuilder sb10 = new StringBuilder();
        sb10.append(crashDetailBean.M);
        map26.put("A18", sb10.toString());
        Map<String, String> map27 = anVar.r;
        StringBuilder sb11 = new StringBuilder();
        sb11.append(!crashDetailBean.N);
        map27.put("A36", sb11.toString());
        Map<String, String> map28 = anVar.r;
        StringBuilder sb12 = new StringBuilder();
        sb12.append(aVar.r);
        map28.put("F02", sb12.toString());
        Map<String, String> map29 = anVar.r;
        StringBuilder sb13 = new StringBuilder();
        sb13.append(aVar.s);
        map29.put("F03", sb13.toString());
        Map<String, String> map30 = anVar.r;
        map30.put("F04", aVar.e());
        Map<String, String> map31 = anVar.r;
        StringBuilder sb14 = new StringBuilder();
        sb14.append(aVar.t);
        map31.put("F05", sb14.toString());
        Map<String, String> map32 = anVar.r;
        map32.put("F06", aVar.q);
        Map<String, String> map33 = anVar.r;
        map33.put("F08", aVar.w);
        Map<String, String> map34 = anVar.r;
        map34.put("F09", aVar.x);
        Map<String, String> map35 = anVar.r;
        StringBuilder sb15 = new StringBuilder();
        sb15.append(aVar.u);
        map35.put("F10", sb15.toString());
        if (crashDetailBean.Q >= 0) {
            Map<String, String> map36 = anVar.r;
            StringBuilder sb16 = new StringBuilder();
            sb16.append(crashDetailBean.Q);
            map36.put("C01", sb16.toString());
        }
        if (crashDetailBean.R >= 0) {
            Map<String, String> map37 = anVar.r;
            StringBuilder sb17 = new StringBuilder();
            sb17.append(crashDetailBean.R);
            map37.put("C02", sb17.toString());
        }
        if (crashDetailBean.S != null && crashDetailBean.S.size() > 0) {
            for (Map.Entry next3 : crashDetailBean.S.entrySet()) {
                Map<String, String> map38 = anVar.r;
                map38.put("C03_" + ((String) next3.getKey()), next3.getValue());
            }
        }
        if (crashDetailBean.T != null && crashDetailBean.T.size() > 0) {
            for (Map.Entry next4 : crashDetailBean.T.entrySet()) {
                Map<String, String> map39 = anVar.r;
                map39.put("C04_" + ((String) next4.getKey()), next4.getValue());
            }
        }
        anVar.s = null;
        if (crashDetailBean.O != null && crashDetailBean.O.size() > 0) {
            anVar.s = crashDetailBean.O;
            x.a("setted message size %d", Integer.valueOf(anVar.s.size()));
        }
        x.c("%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d", crashDetailBean.n, crashDetailBean.c, aVar.e(), Long.valueOf((crashDetailBean.r - crashDetailBean.M) / 1000), Boolean.valueOf(crashDetailBean.k), Boolean.valueOf(crashDetailBean.N), Boolean.valueOf(crashDetailBean.j), Boolean.valueOf(crashDetailBean.b == 1), Integer.valueOf(crashDetailBean.t), crashDetailBean.s, Boolean.valueOf(crashDetailBean.d), Integer.valueOf(anVar.r.size()));
        return anVar;
    }

    private static List<a> a(List<a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (a next : list) {
            if (next.d && next.b <= currentTimeMillis - 86400000) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public static void a(String str, String str2, String str3, String str4, String str5, CrashDetailBean crashDetailBean) {
        String str6;
        com.tencent.bugly.crashreport.common.info.a b2 = com.tencent.bugly.crashreport.common.info.a.b();
        if (b2 != null) {
            x.e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
            x.e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
            x.e("# PKG NAME: %s", b2.c);
            x.e("# APP VER: %s", b2.k);
            x.e("# SDK VER: %s", b2.f);
            x.e("# LAUNCH TIME: %s", z.a(new Date(com.tencent.bugly.crashreport.common.info.a.b().a)));
            x.e("# CRASH TYPE: %s", str);
            x.e("# CRASH TIME: %s", str2);
            x.e("# CRASH PROCESS: %s", str3);
            x.e("# CRASH THREAD: %s", str4);
            if (crashDetailBean != null) {
                x.e("# REPORT ID: %s", crashDetailBean.c);
                x.e("# CRASH DEVICE: %s %s", b2.h, b2.x().booleanValue() ? "ROOTED" : "UNROOT");
                x.e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.C), Long.valueOf(crashDetailBean.D), Long.valueOf(crashDetailBean.E));
                x.e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.F), Long.valueOf(crashDetailBean.G), Long.valueOf(crashDetailBean.H));
                if (!z.a(crashDetailBean.K)) {
                    x.e("# EXCEPTION FIRED BY %s %s", crashDetailBean.K, crashDetailBean.J);
                } else if (crashDetailBean.b == 3) {
                    if (crashDetailBean.P == null) {
                        str6 = "null";
                    } else {
                        str6 = crashDetailBean.P.get("BUGLY_CR_01");
                    }
                    x.e("# EXCEPTION ANR MESSAGE:\n %s", str6);
                }
            }
            if (!z.a(str5)) {
                x.e("# CRASH STACK: ", new Object[0]);
                x.e(str5, new Object[0]);
            }
            x.e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
        }
    }

    public static void a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            x.c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean next : list) {
                x.c("pre uid:%s uc:%d re:%b me:%b", next.c, Integer.valueOf(next.l), Boolean.valueOf(next.d), Boolean.valueOf(next.j));
                next.l++;
                next.d = z;
                x.c("set uid:%s uc:%d re:%b me:%b", next.c, Integer.valueOf(next.l), Boolean.valueOf(next.d), Boolean.valueOf(next.j));
            }
            for (CrashDetailBean a2 : list) {
                c.a().a(a2);
            }
            x.c("update state size %d", Integer.valueOf(list.size()));
        }
        if (!z) {
            x.b("[crash] upload fail.", new Object[0]);
        }
    }

    private static a b(Cursor cursor) {
        boolean z = true;
        if (cursor == null) {
            return null;
        }
        try {
            a aVar = new a();
            aVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            aVar.b = cursor.getLong(cursor.getColumnIndex("_tm"));
            aVar.c = cursor.getString(cursor.getColumnIndex("_s1"));
            aVar.d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            if (cursor.getInt(cursor.getColumnIndex("_me")) != 1) {
                z = false;
            }
            aVar.e = z;
            aVar.f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return aVar;
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006a, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006b, code lost:
        r1 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009b, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009b A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x009e  */
    private List<a> b() {
        Cursor cursor;
        Cursor cursor2;
        Cursor cursor3 = null;
        ArrayList arrayList = new ArrayList();
        try {
            cursor2 = p.a().a("t_cr", new String[]{"_id", "_tm", "_s1", "_up", "_me", "_uc"}, (String) null, (String[]) null, (o) null, true);
            if (cursor2 != null) {
                try {
                    if (cursor2.getCount() > 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("_id in ");
                        sb.append("(");
                        int i = 0;
                        while (cursor2.moveToNext()) {
                            a b2 = b(cursor2);
                            if (b2 != null) {
                                arrayList.add(b2);
                            } else {
                                sb.append(cursor2.getLong(cursor2.getColumnIndex("_id")));
                                sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
                                i++;
                            }
                        }
                        StringBuilder sb2 = sb.toString().contains(ListUtils.DEFAULT_JOIN_SEPARATOR) ? new StringBuilder(sb.substring(0, sb.lastIndexOf(ListUtils.DEFAULT_JOIN_SEPARATOR))) : sb;
                        sb2.append(")");
                        String sb3 = sb2.toString();
                        sb2.setLength(0);
                        if (i > 0) {
                            x.d("deleted %s illegal data %d", "t_cr", Integer.valueOf(p.a().a("t_cr", sb3, (String[]) null, (o) null, true)));
                        }
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        return arrayList;
                    } else if (cursor2 == null) {
                        return null;
                    } else {
                        cursor2.close();
                        return null;
                    }
                } catch (Throwable th) {
                }
            } else if (cursor2 == null) {
                return null;
            } else {
                cursor2.close();
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor2 = cursor3;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
        try {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            cursor3 = cursor;
            cursor2 = cursor3;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0093, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0094, code lost:
        r1 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c4, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c4 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:17:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c8  */
    private List<CrashDetailBean> b(List<a> list) {
        Cursor cursor;
        Cursor cursor2;
        Cursor cursor3 = null;
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("_id in ");
        sb.append("(");
        for (a aVar : list) {
            sb.append(aVar.a);
            sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        }
        StringBuilder sb2 = sb.toString().contains(ListUtils.DEFAULT_JOIN_SEPARATOR) ? new StringBuilder(sb.substring(0, sb.lastIndexOf(ListUtils.DEFAULT_JOIN_SEPARATOR))) : sb;
        sb2.append(")");
        String sb3 = sb2.toString();
        sb2.setLength(0);
        try {
            cursor2 = p.a().a("t_cr", (String[]) null, sb3, (String[]) null, (o) null, true);
            if (cursor2 != null) {
                try {
                    ArrayList arrayList = new ArrayList();
                    sb2.append("_id in ");
                    sb2.append("(");
                    int i = 0;
                    while (cursor2.moveToNext()) {
                        CrashDetailBean a2 = a(cursor2);
                        if (a2 != null) {
                            arrayList.add(a2);
                        } else {
                            sb2.append(cursor2.getLong(cursor2.getColumnIndex("_id")));
                            sb2.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
                            i++;
                        }
                    }
                    if (sb2.toString().contains(ListUtils.DEFAULT_JOIN_SEPARATOR)) {
                        sb2 = new StringBuilder(sb2.substring(0, sb2.lastIndexOf(ListUtils.DEFAULT_JOIN_SEPARATOR)));
                    }
                    sb2.append(")");
                    String sb4 = sb2.toString();
                    if (i > 0) {
                        x.d("deleted %s illegal data %d", "t_cr", Integer.valueOf(p.a().a("t_cr", sb4, (String[]) null, (o) null, true)));
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    return arrayList;
                } catch (Throwable th) {
                }
            } else if (cursor2 == null) {
                return null;
            } else {
                cursor2.close();
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            if (cursor3 != null) {
            }
            throw th;
        }
        try {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            if (cursor == null) {
                return null;
            }
            cursor.close();
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor2 = cursor;
            cursor3 = cursor2;
            if (cursor3 != null) {
                cursor3.close();
            }
            throw th;
        }
    }

    private static void c(List<a> list) {
        if (list != null && list.size() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("_id in ");
            sb.append("(");
            for (a aVar : list) {
                sb.append(aVar.a);
                sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
            }
            StringBuilder sb2 = new StringBuilder(sb.substring(0, sb.lastIndexOf(ListUtils.DEFAULT_JOIN_SEPARATOR)));
            sb2.append(")");
            String sb3 = sb2.toString();
            sb2.setLength(0);
            try {
                x.c("deleted %s data %d", "t_cr", Integer.valueOf(p.a().a("t_cr", sb3, (String[]) null, (o) null, true)));
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static void d(List<CrashDetailBean> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (CrashDetailBean crashDetailBean : list) {
                        sb.append(" or _id");
                        sb.append(" = ");
                        sb.append(crashDetailBean.a);
                    }
                    String sb2 = sb.toString();
                    if (sb2.length() > 0) {
                        sb2 = sb2.substring(4);
                    }
                    sb.setLength(0);
                    x.c("deleted %s data %d", "t_cr", Integer.valueOf(p.a().a("t_cr", sb2, (String[]) null, (o) null, true)));
                }
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    private static ContentValues f(CrashDetailBean crashDetailBean) {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }

    public final List<CrashDetailBean> a() {
        StrategyBean c2 = a.a().c();
        if (c2 == null) {
            x.d("have not synced remote!", new Object[0]);
            return null;
        } else if (!c2.g) {
            x.d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            x.b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            long b2 = z.b();
            List<a> b3 = b();
            x.c("Size of crash list loaded from DB: %s", Integer.valueOf(b3.size()));
            if (b3 == null || b3.size() <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(a(b3));
            b3.removeAll(arrayList);
            Iterator<a> it = b3.iterator();
            while (it.hasNext()) {
                a next = it.next();
                if (next.b < b2 - c.g) {
                    it.remove();
                    arrayList.add(next);
                } else if (next.d) {
                    if (next.b >= currentTimeMillis - 86400000) {
                        it.remove();
                    } else if (!next.e) {
                        it.remove();
                        arrayList.add(next);
                    }
                } else if (((long) next.f) >= 3 && next.b < currentTimeMillis - 86400000) {
                    it.remove();
                    arrayList.add(next);
                }
            }
            if (arrayList.size() > 0) {
                c((List<a>) arrayList);
            }
            ArrayList arrayList2 = new ArrayList();
            List<CrashDetailBean> b4 = b(b3);
            if (b4 != null && b4.size() > 0) {
                String str = com.tencent.bugly.crashreport.common.info.a.b().k;
                Iterator<CrashDetailBean> it2 = b4.iterator();
                while (it2.hasNext()) {
                    CrashDetailBean next2 = it2.next();
                    if (!str.equals(next2.f)) {
                        it2.remove();
                        arrayList2.add(next2);
                    }
                }
            }
            if (arrayList2.size() > 0) {
                d((List<CrashDetailBean>) arrayList2);
            }
            return b4;
        }
    }

    public final void a(CrashDetailBean crashDetailBean, long j, boolean z) {
        boolean z2 = false;
        if (c.l) {
            x.a("try to upload right now", new Object[0]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(crashDetailBean);
            if (crashDetailBean.b == 7) {
                z2 = true;
            }
            a(arrayList, 3000, z, z2, z);
        }
    }

    public final void a(final List<CrashDetailBean> list, long j, boolean z, boolean z2, boolean z3) {
        ao aoVar;
        if (!com.tencent.bugly.crashreport.common.info.a.a(this.b).e || this.c == null) {
            return;
        }
        if (z3 || this.c.b(c.a)) {
            StrategyBean c2 = this.e.c();
            if (!c2.g) {
                x.d("remote report is disable!", new Object[0]);
                x.b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
            } else if (list != null && list.size() != 0) {
                try {
                    String str = this.c.a ? c2.s : c2.t;
                    String str2 = this.c.a ? StrategyBean.c : StrategyBean.a;
                    int i = this.c.a ? 830 : 630;
                    Context context = this.b;
                    com.tencent.bugly.crashreport.common.info.a b2 = com.tencent.bugly.crashreport.common.info.a.b();
                    if (context == null || list == null || list.size() == 0 || b2 == null) {
                        x.d("enEXPPkg args == null!", new Object[0]);
                        aoVar = null;
                    } else {
                        ao aoVar2 = new ao();
                        aoVar2.a = new ArrayList<>();
                        for (CrashDetailBean a2 : list) {
                            aoVar2.a.add(a(context, a2, b2));
                        }
                        aoVar = aoVar2;
                    }
                    if (aoVar == null) {
                        x.d("create eupPkg fail!", new Object[0]);
                        return;
                    }
                    byte[] a3 = com.tencent.bugly.proguard.a.a((k) aoVar);
                    if (a3 == null) {
                        x.d("send encode fail!", new Object[0]);
                        return;
                    }
                    ap a4 = com.tencent.bugly.proguard.a.a(this.b, i, a3);
                    if (a4 == null) {
                        x.d("request package is null.", new Object[0]);
                        return;
                    }
                    AnonymousClass1 r5 = new t() {
                        public final void a(boolean z) {
                            b bVar = b.this;
                            b.a(z, (List<CrashDetailBean>) list);
                        }
                    };
                    if (z) {
                        this.c.a(a, a4, str, str2, r5, j, z2);
                    } else {
                        this.c.a(a, a4, str, str2, r5, false);
                    }
                } catch (Throwable th) {
                    x.e("req cr error %s", th.toString());
                    if (!x.b(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    public final boolean a(CrashDetailBean crashDetailBean) {
        return b(crashDetailBean);
    }

    public final boolean b(CrashDetailBean crashDetailBean) {
        ArrayList arrayList = null;
        if (crashDetailBean == null) {
            return true;
        }
        if (c.n != null && !c.n.isEmpty()) {
            x.c("Crash filter for crash stack is: %s", c.n);
            if (crashDetailBean.q.contains(c.n)) {
                x.d("This crash contains the filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        if (c.o != null && !c.o.isEmpty()) {
            x.c("Crash regular filter for crash stack is: %s", c.o);
            if (Pattern.compile(c.o).matcher(crashDetailBean.q).find()) {
                x.d("This crash matches the regular filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        if (crashDetailBean.b != 2) {
            r rVar = new r();
            rVar.b = 1;
            rVar.c = crashDetailBean.A;
            rVar.d = crashDetailBean.B;
            rVar.e = crashDetailBean.r;
            this.d.b(1);
            this.d.a(rVar);
            x.b("[crash] a crash occur, handling...", new Object[0]);
        } else {
            x.b("[crash] a caught exception occur, handling...", new Object[0]);
        }
        List<a> b2 = b();
        if (b2 != null && b2.size() > 0) {
            ArrayList arrayList2 = new ArrayList(10);
            ArrayList<a> arrayList3 = new ArrayList<>(10);
            arrayList2.addAll(a(b2));
            b2.removeAll(arrayList2);
            if (((long) b2.size()) > 20) {
                StringBuilder sb = new StringBuilder();
                sb.append("_id in ");
                sb.append("(");
                sb.append("SELECT _id");
                sb.append(" FROM t_cr");
                sb.append(" order by _id");
                sb.append(" limit 5");
                sb.append(")");
                String sb2 = sb.toString();
                sb.setLength(0);
                try {
                    x.c("deleted first record %s data %d", "t_cr", Integer.valueOf(p.a().a("t_cr", sb2, (String[]) null, (o) null, true)));
                } catch (Throwable th) {
                    if (!x.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
            if (!com.tencent.bugly.b.c && c.d) {
                boolean z = false;
                for (a next : b2) {
                    if (crashDetailBean.u.equals(next.c)) {
                        if (next.e) {
                            z = true;
                        }
                        arrayList3.add(next);
                    }
                }
                if (z || arrayList3.size() >= c.c) {
                    x.a("same crash occur too much do merged!", new Object[0]);
                    CrashDetailBean a2 = a((List<a>) arrayList3, crashDetailBean);
                    for (a aVar : arrayList3) {
                        if (aVar.a != a2.a) {
                            arrayList2.add(aVar);
                        }
                    }
                    e(a2);
                    c((List<a>) arrayList2);
                    x.b("[crash] save crash success. For this device crash many times, it will not upload crashes immediately", new Object[0]);
                    return true;
                }
            }
            arrayList = arrayList2;
        }
        e(crashDetailBean);
        if (arrayList != null && !arrayList.isEmpty()) {
            c((List<a>) arrayList);
        }
        x.b("[crash] save crash success", new Object[0]);
        return false;
    }

    public final void c(CrashDetailBean crashDetailBean) {
        int i = crashDetailBean.b;
        if (i != 3) {
            switch (i) {
                case 0:
                    if (!c.a().o()) {
                        return;
                    }
                    break;
                case 1:
                    if (!c.a().o()) {
                        return;
                    }
                    break;
            }
        } else if (!c.a().p()) {
            return;
        }
        if (this.f != null) {
            x.c("Calling 'onCrashHandleEnd' of RQD crash listener.", new Object[0]);
            o oVar = this.f;
            int i2 = crashDetailBean.b;
        }
    }

    public final void d(CrashDetailBean crashDetailBean) {
        int i;
        int i2;
        Map map;
        byte[] bArr;
        String str;
        if (crashDetailBean != null) {
            if (this.g != null || this.f != null) {
                try {
                    switch (crashDetailBean.b) {
                        case 0:
                            if (c.a().o()) {
                                i = 0;
                                break;
                            } else {
                                return;
                            }
                        case 1:
                            if (c.a().o()) {
                                i = 2;
                                break;
                            } else {
                                return;
                            }
                        case 2:
                            i = 1;
                            break;
                        case 3:
                            i2 = 4;
                            if (!c.a().p()) {
                                return;
                            }
                            break;
                        case 4:
                            i2 = 3;
                            if (!c.a().q()) {
                                return;
                            }
                            break;
                        case 5:
                            i2 = 5;
                            if (!c.a().r()) {
                                return;
                            }
                            break;
                        case 6:
                            i2 = 6;
                            if (!c.a().s()) {
                                return;
                            }
                            break;
                        case 7:
                            i = 7;
                            break;
                        default:
                            return;
                    }
                    i = i2;
                    int i3 = crashDetailBean.b;
                    String str2 = crashDetailBean.n;
                    String str3 = crashDetailBean.p;
                    String str4 = crashDetailBean.q;
                    long j = crashDetailBean.r;
                    if (this.f != null) {
                        x.c("Calling 'onCrashHandleStart' of RQD crash listener.", new Object[0]);
                        o oVar = this.f;
                        x.c("Calling 'getCrashExtraMessage' of RQD crash listener.", new Object[0]);
                        String b2 = this.f.b();
                        if (b2 != null) {
                            map = new HashMap(1);
                            map.put("userData", b2);
                        } else {
                            map = null;
                        }
                    } else if (this.g != null) {
                        x.c("Calling 'onCrashHandleStart' of Bugly crash listener.", new Object[0]);
                        map = this.g.onCrashHandleStart(i, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q);
                    } else {
                        map = null;
                    }
                    if (map != null && map.size() > 0) {
                        crashDetailBean.O = new LinkedHashMap(map.size());
                        for (Map.Entry entry : map.entrySet()) {
                            if (!z.a((String) entry.getKey())) {
                                String str5 = (String) entry.getKey();
                                if (str5.length() > 100) {
                                    str5 = str5.substring(0, 100);
                                    x.d("setted key length is over limit %d substring to %s", 100, str5);
                                }
                                String str6 = str5;
                                if (z.a((String) entry.getValue()) || ((String) entry.getValue()).length() <= 30000) {
                                    str = ((String) entry.getValue());
                                } else {
                                    str = ((String) entry.getValue()).substring(((String) entry.getValue()).length() - 30000);
                                    x.d("setted %s value length is over limit %d substring", str6, Integer.valueOf(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH));
                                }
                                crashDetailBean.O.put(str6, str);
                                x.a("add setted key %s value size:%d", str6, Integer.valueOf(str.length()));
                            }
                        }
                    }
                    x.a("[crash callback] start user's callback:onCrashHandleStart2GetExtraDatas()", new Object[0]);
                    if (this.f != null) {
                        x.c("Calling 'getCrashExtraData' of RQD crash listener.", new Object[0]);
                        bArr = this.f.a();
                    } else if (this.g != null) {
                        x.c("Calling 'onCrashHandleStart2GetExtraDatas' of Bugly crash listener.", new Object[0]);
                        bArr = this.g.onCrashHandleStart2GetExtraDatas(i, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q);
                    } else {
                        bArr = null;
                    }
                    crashDetailBean.U = bArr;
                    if (bArr != null) {
                        if (bArr.length > 30000) {
                            x.d("extra bytes size %d is over limit %d will drop over part", Integer.valueOf(bArr.length), Integer.valueOf(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH));
                            crashDetailBean.U = Arrays.copyOf(bArr, BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
                        }
                        x.a("add extra bytes %d ", Integer.valueOf(bArr.length));
                    }
                    if (this.f != null) {
                        x.c("Calling 'onCrashSaving' of RQD crash listener.", new Object[0]);
                        o oVar2 = this.f;
                        String str7 = crashDetailBean.o;
                        String str8 = crashDetailBean.m;
                        String str9 = crashDetailBean.e;
                        String str10 = crashDetailBean.c;
                        String str11 = crashDetailBean.A;
                        String str12 = crashDetailBean.B;
                        if (!oVar2.c()) {
                            x.d("Crash listener 'onCrashSaving' return 'false' thus will not handle this crash.", new Object[0]);
                        }
                    }
                } catch (Throwable th) {
                    x.d("crash handle callback something wrong! %s", th.getClass().getName());
                    if (!x.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    public final void e(CrashDetailBean crashDetailBean) {
        ContentValues f2;
        if (crashDetailBean != null && (f2 = f(crashDetailBean)) != null) {
            long a2 = p.a().a("t_cr", f2, (o) null, true);
            if (a2 >= 0) {
                x.c("insert %s success!", "t_cr");
                crashDetailBean.a = a2;
            }
        }
    }
}
