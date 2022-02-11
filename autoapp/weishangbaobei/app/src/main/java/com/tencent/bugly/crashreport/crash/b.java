package com.tencent.bugly.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcelable;
import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.ak;
import com.tencent.bugly.proguard.am;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.r;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.wx.assistants.utils.fileutil.ListUtils;
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

/* compiled from: BUGLY */
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

    private CrashDetailBean a(List<a> list, CrashDetailBean crashDetailBean) {
        List<CrashDetailBean> b2;
        String[] split;
        if (list == null || list.size() == 0) {
            return crashDetailBean;
        }
        CrashDetailBean crashDetailBean2 = null;
        ArrayList arrayList = new ArrayList(10);
        for (a next : list) {
            if (next.e) {
                arrayList.add(next);
            }
        }
        if (arrayList.size() > 0 && (b2 = b((List<a>) arrayList)) != null && b2.size() > 0) {
            Collections.sort(b2);
            CrashDetailBean crashDetailBean3 = null;
            for (int i = 0; i < b2.size(); i++) {
                CrashDetailBean crashDetailBean4 = b2.get(i);
                if (i == 0) {
                    crashDetailBean3 = crashDetailBean4;
                } else if (!(crashDetailBean4.s == null || (split = crashDetailBean4.s.split("\n")) == null)) {
                    for (String str : split) {
                        if (!crashDetailBean3.s.contains(str)) {
                            crashDetailBean3.t++;
                            crashDetailBean3.s += str + "\n";
                        }
                    }
                }
            }
            crashDetailBean2 = crashDetailBean3;
        }
        if (crashDetailBean2 == null) {
            crashDetailBean.j = true;
            crashDetailBean.t = 0;
            crashDetailBean.s = "";
            crashDetailBean2 = crashDetailBean;
        }
        for (a next2 : list) {
            if (!next2.e && !next2.d) {
                String str2 = crashDetailBean2.s;
                StringBuilder sb = new StringBuilder();
                sb.append(next2.b);
                if (!str2.contains(sb.toString())) {
                    crashDetailBean2.t++;
                    crashDetailBean2.s += next2.b + "\n";
                }
            }
        }
        if (crashDetailBean2.r != crashDetailBean.r) {
            String str3 = crashDetailBean2.s;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.r);
            if (!str3.contains(sb2.toString())) {
                crashDetailBean2.t++;
                crashDetailBean2.s += crashDetailBean.r + "\n";
            }
        }
        return crashDetailBean2;
    }

    public final boolean a(CrashDetailBean crashDetailBean) {
        return b(crashDetailBean);
    }

    public final boolean b(CrashDetailBean crashDetailBean) {
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
        ArrayList arrayList = null;
        if (b2 != null && b2.size() > 0) {
            arrayList = new ArrayList(10);
            ArrayList<a> arrayList2 = new ArrayList<>(10);
            arrayList.addAll(a(b2));
            b2.removeAll(arrayList);
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
                        arrayList2.add(next);
                    }
                }
                if (z || arrayList2.size() >= c.c) {
                    x.a("same crash occur too much do merged!", new Object[0]);
                    CrashDetailBean a2 = a((List<a>) arrayList2, crashDetailBean);
                    for (a aVar : arrayList2) {
                        if (aVar.a != a2.a) {
                            arrayList.add(aVar);
                        }
                    }
                    e(a2);
                    c((List<a>) arrayList);
                    x.b("[crash] save crash success. For this device crash many times, it will not upload crashes immediately", new Object[0]);
                    return true;
                }
            }
        }
        e(crashDetailBean);
        if (arrayList != null && !arrayList.isEmpty()) {
            c((List<a>) arrayList);
        }
        x.b("[crash] save crash success", new Object[0]);
        return false;
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

    public final void a(CrashDetailBean crashDetailBean, long j, boolean z) {
        if (c.l) {
            x.a("try to upload right now", new Object[0]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(crashDetailBean);
            a(arrayList, 3000, z, crashDetailBean.b == 7, z);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a9 A[Catch:{ Throwable -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b1 A[Catch:{ Throwable -> 0x00eb }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(final java.util.List<com.tencent.bugly.crashreport.crash.CrashDetailBean> r15, long r16, boolean r18, boolean r19, boolean r20) {
        /*
            r14 = this;
            r1 = r14
            r0 = r15
            android.content.Context r2 = r1.b
            com.tencent.bugly.crashreport.common.info.a r2 = com.tencent.bugly.crashreport.common.info.a.a((android.content.Context) r2)
            boolean r2 = r2.e
            if (r2 != 0) goto L_0x000d
            return
        L_0x000d:
            com.tencent.bugly.proguard.u r2 = r1.c
            if (r2 != 0) goto L_0x0012
            return
        L_0x0012:
            if (r20 != 0) goto L_0x001f
            com.tencent.bugly.proguard.u r2 = r1.c
            int r3 = com.tencent.bugly.crashreport.crash.c.a
            boolean r2 = r2.b((int) r3)
            if (r2 != 0) goto L_0x001f
            return
        L_0x001f:
            com.tencent.bugly.crashreport.common.strategy.a r2 = r1.e
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r2 = r2.c()
            boolean r3 = r2.g
            r4 = 0
            if (r3 != 0) goto L_0x0039
            java.lang.String r0 = "remote report is disable!"
            java.lang.Object[] r2 = new java.lang.Object[r4]
            com.tencent.bugly.proguard.x.d(r0, r2)
            java.lang.String r0 = "[crash] server closed bugly in this app. please check your appid if is correct, and re-install it"
            java.lang.Object[] r2 = new java.lang.Object[r4]
            com.tencent.bugly.proguard.x.b(r0, r2)
            return
        L_0x0039:
            if (r0 == 0) goto L_0x0104
            int r3 = r15.size()
            if (r3 != 0) goto L_0x0043
            goto L_0x0104
        L_0x0043:
            com.tencent.bugly.proguard.u r3 = r1.c     // Catch:{ Throwable -> 0x00eb }
            boolean r3 = r3.a     // Catch:{ Throwable -> 0x00eb }
            if (r3 == 0) goto L_0x004c
            java.lang.String r2 = r2.s     // Catch:{ Throwable -> 0x00eb }
            goto L_0x004e
        L_0x004c:
            java.lang.String r2 = r2.t     // Catch:{ Throwable -> 0x00eb }
        L_0x004e:
            r8 = r2
            com.tencent.bugly.proguard.u r2 = r1.c     // Catch:{ Throwable -> 0x00eb }
            boolean r2 = r2.a     // Catch:{ Throwable -> 0x00eb }
            if (r2 == 0) goto L_0x0058
            java.lang.String r2 = com.tencent.bugly.crashreport.common.strategy.StrategyBean.c     // Catch:{ Throwable -> 0x00eb }
            goto L_0x005a
        L_0x0058:
            java.lang.String r2 = com.tencent.bugly.crashreport.common.strategy.StrategyBean.a     // Catch:{ Throwable -> 0x00eb }
        L_0x005a:
            r9 = r2
            com.tencent.bugly.proguard.u r2 = r1.c     // Catch:{ Throwable -> 0x00eb }
            boolean r2 = r2.a     // Catch:{ Throwable -> 0x00eb }
            if (r2 == 0) goto L_0x0064
            r2 = 830(0x33e, float:1.163E-42)
            goto L_0x0066
        L_0x0064:
            r2 = 630(0x276, float:8.83E-43)
        L_0x0066:
            android.content.Context r3 = r1.b     // Catch:{ Throwable -> 0x00eb }
            com.tencent.bugly.crashreport.common.info.a r5 = com.tencent.bugly.crashreport.common.info.a.b()     // Catch:{ Throwable -> 0x00eb }
            if (r3 == 0) goto L_0x009f
            if (r0 == 0) goto L_0x009f
            int r6 = r15.size()     // Catch:{ Throwable -> 0x00eb }
            if (r6 == 0) goto L_0x009f
            if (r5 != 0) goto L_0x0079
            goto L_0x009f
        L_0x0079:
            com.tencent.bugly.proguard.ao r6 = new com.tencent.bugly.proguard.ao     // Catch:{ Throwable -> 0x00eb }
            r6.<init>()     // Catch:{ Throwable -> 0x00eb }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00eb }
            r7.<init>()     // Catch:{ Throwable -> 0x00eb }
            r6.a = r7     // Catch:{ Throwable -> 0x00eb }
            java.util.Iterator r7 = r15.iterator()     // Catch:{ Throwable -> 0x00eb }
        L_0x0089:
            boolean r10 = r7.hasNext()     // Catch:{ Throwable -> 0x00eb }
            if (r10 == 0) goto L_0x00a7
            java.lang.Object r10 = r7.next()     // Catch:{ Throwable -> 0x00eb }
            com.tencent.bugly.crashreport.crash.CrashDetailBean r10 = (com.tencent.bugly.crashreport.crash.CrashDetailBean) r10     // Catch:{ Throwable -> 0x00eb }
            java.util.ArrayList<com.tencent.bugly.proguard.an> r11 = r6.a     // Catch:{ Throwable -> 0x00eb }
            com.tencent.bugly.proguard.an r10 = a((android.content.Context) r3, (com.tencent.bugly.crashreport.crash.CrashDetailBean) r10, (com.tencent.bugly.crashreport.common.info.a) r5)     // Catch:{ Throwable -> 0x00eb }
            r11.add(r10)     // Catch:{ Throwable -> 0x00eb }
            goto L_0x0089
        L_0x009f:
            java.lang.String r3 = "enEXPPkg args == null!"
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x00eb }
            com.tencent.bugly.proguard.x.d(r3, r5)     // Catch:{ Throwable -> 0x00eb }
            r6 = 0
        L_0x00a7:
            if (r6 != 0) goto L_0x00b1
            java.lang.String r0 = "create eupPkg fail!"
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x00eb }
            com.tencent.bugly.proguard.x.d(r0, r2)     // Catch:{ Throwable -> 0x00eb }
            return
        L_0x00b1:
            byte[] r3 = com.tencent.bugly.proguard.a.a((com.tencent.bugly.proguard.k) r6)     // Catch:{ Throwable -> 0x00eb }
            if (r3 != 0) goto L_0x00bf
            java.lang.String r0 = "send encode fail!"
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x00eb }
            com.tencent.bugly.proguard.x.d(r0, r2)     // Catch:{ Throwable -> 0x00eb }
            return
        L_0x00bf:
            android.content.Context r5 = r1.b     // Catch:{ Throwable -> 0x00eb }
            com.tencent.bugly.proguard.ap r7 = com.tencent.bugly.proguard.a.a(r5, r2, r3)     // Catch:{ Throwable -> 0x00eb }
            if (r7 != 0) goto L_0x00cf
            java.lang.String r0 = "request package is null."
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x00eb }
            com.tencent.bugly.proguard.x.d(r0, r2)     // Catch:{ Throwable -> 0x00eb }
            return
        L_0x00cf:
            com.tencent.bugly.crashreport.crash.b$1 r10 = new com.tencent.bugly.crashreport.crash.b$1     // Catch:{ Throwable -> 0x00eb }
            r10.<init>(r15)     // Catch:{ Throwable -> 0x00eb }
            if (r18 == 0) goto L_0x00e2
            com.tencent.bugly.proguard.u r5 = r1.c     // Catch:{ Throwable -> 0x00eb }
            int r6 = a     // Catch:{ Throwable -> 0x00eb }
            r11 = r16
            r13 = r19
            r5.a(r6, r7, r8, r9, r10, r11, r13)     // Catch:{ Throwable -> 0x00eb }
            goto L_0x0103
        L_0x00e2:
            com.tencent.bugly.proguard.u r5 = r1.c     // Catch:{ Throwable -> 0x00eb }
            int r6 = a     // Catch:{ Throwable -> 0x00eb }
            r11 = 0
            r5.a(r6, r7, r8, r9, r10, r11)     // Catch:{ Throwable -> 0x00eb }
            return
        L_0x00eb:
            r0 = move-exception
            java.lang.String r2 = "req cr error %s"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r5 = r0.toString()
            r3[r4] = r5
            com.tencent.bugly.proguard.x.e(r2, r3)
            boolean r2 = com.tencent.bugly.proguard.x.b(r0)
            if (r2 != 0) goto L_0x0103
            r0.printStackTrace()
        L_0x0103:
            return
        L_0x0104:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.a(java.util.List, long, boolean, boolean, boolean):void");
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

    public final void d(CrashDetailBean crashDetailBean) {
        int i;
        Map<String, String> map;
        String str;
        HashMap hashMap;
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
                            i = 4;
                            if (!c.a().p()) {
                                return;
                            }
                            break;
                        case 4:
                            i = 3;
                            if (!c.a().q()) {
                                return;
                            }
                            break;
                        case 5:
                            i = 5;
                            if (!c.a().r()) {
                                return;
                            }
                            break;
                        case 6:
                            i = 6;
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
                    int i2 = crashDetailBean.b;
                    String str2 = crashDetailBean.n;
                    String str3 = crashDetailBean.p;
                    String str4 = crashDetailBean.q;
                    long j = crashDetailBean.r;
                    byte[] bArr = null;
                    if (this.f != null) {
                        x.c("Calling 'onCrashHandleStart' of RQD crash listener.", new Object[0]);
                        o oVar = this.f;
                        x.c("Calling 'getCrashExtraMessage' of RQD crash listener.", new Object[0]);
                        String b2 = this.f.b();
                        if (b2 != null) {
                            hashMap = new HashMap(1);
                            hashMap.put("userData", b2);
                        } else {
                            hashMap = null;
                        }
                        map = hashMap;
                    } else if (this.g != null) {
                        x.c("Calling 'onCrashHandleStart' of Bugly crash listener.", new Object[0]);
                        map = this.g.onCrashHandleStart(i, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q);
                    } else {
                        map = null;
                    }
                    if (map != null && map.size() > 0) {
                        crashDetailBean.O = new LinkedHashMap(map.size());
                        for (Map.Entry next : map.entrySet()) {
                            if (!z.a((String) next.getKey())) {
                                String str5 = (String) next.getKey();
                                if (str5.length() > 100) {
                                    str5 = str5.substring(0, 100);
                                    x.d("setted key length is over limit %d substring to %s", 100, str5);
                                }
                                if (z.a((String) next.getValue()) || ((String) next.getValue()).length() <= 30000) {
                                    str = ((String) next.getValue());
                                } else {
                                    str = ((String) next.getValue()).substring(((String) next.getValue()).length() - BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
                                    x.d("setted %s value length is over limit %d substring", str5, Integer.valueOf(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH));
                                }
                                crashDetailBean.O.put(str5, str);
                                x.a("add setted key %s value size:%d", str5, Integer.valueOf(str.length()));
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
                        String str6 = crashDetailBean.o;
                        String str7 = crashDetailBean.m;
                        String str8 = crashDetailBean.e;
                        String str9 = crashDetailBean.c;
                        String str10 = crashDetailBean.A;
                        String str11 = crashDetailBean.B;
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

    private static ContentValues f(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.a > 0) {
                contentValues.put("_id", Long.valueOf(crashDetailBean.a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.r));
            contentValues.put("_s1", crashDetailBean.u);
            contentValues.put("_up", Integer.valueOf(crashDetailBean.d ? 1 : 0));
            contentValues.put("_me", Integer.valueOf(crashDetailBean.j ? 1 : 0));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.l));
            contentValues.put("_dt", z.a((Parcelable) crashDetailBean));
            return contentValues;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
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
            if (crashDetailBean != null) {
                crashDetailBean.a = j;
            }
            return crashDetailBean;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
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

    /* JADX WARNING: Removed duplicated region for block: B:51:0x010c A[Catch:{ all -> 0x0115 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0118  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.tencent.bugly.crashreport.crash.CrashDetailBean> b(java.util.List<com.tencent.bugly.crashreport.crash.a> r13) {
        /*
            r12 = this;
            r0 = 0
            if (r13 == 0) goto L_0x011c
            int r1 = r13.size()
            if (r1 != 0) goto L_0x000b
            goto L_0x011c
        L_0x000b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "_id in "
            r1.append(r2)
            java.lang.String r2 = "("
            r1.append(r2)
            java.util.Iterator r13 = r13.iterator()
        L_0x001e:
            boolean r2 = r13.hasNext()
            if (r2 == 0) goto L_0x0035
            java.lang.Object r2 = r13.next()
            com.tencent.bugly.crashreport.crash.a r2 = (com.tencent.bugly.crashreport.crash.a) r2
            long r2 = r2.a
            r1.append(r2)
            java.lang.String r2 = ","
            r1.append(r2)
            goto L_0x001e
        L_0x0035:
            java.lang.String r13 = r1.toString()
            java.lang.String r2 = ","
            boolean r13 = r13.contains(r2)
            r2 = 0
            if (r13 == 0) goto L_0x0052
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r3 = ","
            int r3 = r1.lastIndexOf(r3)
            java.lang.String r1 = r1.substring(r2, r3)
            r13.<init>(r1)
            goto L_0x0053
        L_0x0052:
            r13 = r1
        L_0x0053:
            java.lang.String r1 = ")"
            r13.append(r1)
            java.lang.String r6 = r13.toString()
            r13.setLength(r2)
            com.tencent.bugly.proguard.p r3 = com.tencent.bugly.proguard.p.a()     // Catch:{ Throwable -> 0x0104, all -> 0x0101 }
            java.lang.String r4 = "t_cr"
            r5 = 0
            r7 = 0
            r8 = 0
            r9 = 1
            android.database.Cursor r1 = r3.a(r4, r5, r6, r7, r8, r9)     // Catch:{ Throwable -> 0x0104, all -> 0x0101 }
            if (r1 != 0) goto L_0x0075
            if (r1 == 0) goto L_0x0074
            r1.close()
        L_0x0074:
            return r0
        L_0x0075:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Throwable -> 0x00ff }
            r3.<init>()     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r4 = "_id in "
            r13.append(r4)     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r4 = "("
            r13.append(r4)     // Catch:{ Throwable -> 0x00ff }
            r4 = 0
        L_0x0085:
            boolean r5 = r1.moveToNext()     // Catch:{ Throwable -> 0x00ff }
            if (r5 == 0) goto L_0x00b2
            com.tencent.bugly.crashreport.crash.CrashDetailBean r5 = a((android.database.Cursor) r1)     // Catch:{ Throwable -> 0x00ff }
            if (r5 == 0) goto L_0x0095
            r3.add(r5)     // Catch:{ Throwable -> 0x00ff }
            goto L_0x0085
        L_0x0095:
            java.lang.String r5 = "_id"
            int r5 = r1.getColumnIndex(r5)     // Catch:{ Throwable -> 0x00aa }
            long r5 = r1.getLong(r5)     // Catch:{ Throwable -> 0x00aa }
            r13.append(r5)     // Catch:{ Throwable -> 0x00aa }
            java.lang.String r5 = ","
            r13.append(r5)     // Catch:{ Throwable -> 0x00aa }
            int r4 = r4 + 1
            goto L_0x0085
        L_0x00aa:
            java.lang.String r5 = "unknown id!"
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00ff }
            com.tencent.bugly.proguard.x.d(r5, r6)     // Catch:{ Throwable -> 0x00ff }
            goto L_0x0085
        L_0x00b2:
            java.lang.String r5 = r13.toString()     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r6 = ","
            boolean r5 = r5.contains(r6)     // Catch:{ Throwable -> 0x00ff }
            if (r5 == 0) goto L_0x00ce
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r6 = ","
            int r6 = r13.lastIndexOf(r6)     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r13 = r13.substring(r2, r6)     // Catch:{ Throwable -> 0x00ff }
            r5.<init>(r13)     // Catch:{ Throwable -> 0x00ff }
            r13 = r5
        L_0x00ce:
            java.lang.String r5 = ")"
            r13.append(r5)     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r8 = r13.toString()     // Catch:{ Throwable -> 0x00ff }
            if (r4 <= 0) goto L_0x00f9
            com.tencent.bugly.proguard.p r6 = com.tencent.bugly.proguard.p.a()     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r7 = "t_cr"
            r9 = 0
            r10 = 0
            r11 = 1
            int r13 = r6.a((java.lang.String) r7, (java.lang.String) r8, (java.lang.String[]) r9, (com.tencent.bugly.proguard.o) r10, (boolean) r11)     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r4 = "deleted %s illegal data %d"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x00ff }
            java.lang.String r6 = "t_cr"
            r5[r2] = r6     // Catch:{ Throwable -> 0x00ff }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Throwable -> 0x00ff }
            r2 = 1
            r5[r2] = r13     // Catch:{ Throwable -> 0x00ff }
            com.tencent.bugly.proguard.x.d(r4, r5)     // Catch:{ Throwable -> 0x00ff }
        L_0x00f9:
            if (r1 == 0) goto L_0x00fe
            r1.close()
        L_0x00fe:
            return r3
        L_0x00ff:
            r13 = move-exception
            goto L_0x0106
        L_0x0101:
            r13 = move-exception
            r1 = r0
            goto L_0x0116
        L_0x0104:
            r13 = move-exception
            r1 = r0
        L_0x0106:
            boolean r2 = com.tencent.bugly.proguard.x.a(r13)     // Catch:{ all -> 0x0115 }
            if (r2 != 0) goto L_0x010f
            r13.printStackTrace()     // Catch:{ all -> 0x0115 }
        L_0x010f:
            if (r1 == 0) goto L_0x0114
            r1.close()
        L_0x0114:
            return r0
        L_0x0115:
            r13 = move-exception
        L_0x0116:
            if (r1 == 0) goto L_0x011b
            r1.close()
        L_0x011b:
            throw r13
        L_0x011c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.b(java.util.List):java.util.List");
    }

    private static a b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            a aVar = new a();
            aVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            aVar.b = cursor.getLong(cursor.getColumnIndex("_tm"));
            aVar.c = cursor.getString(cursor.getColumnIndex("_s1"));
            boolean z = false;
            aVar.d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            if (cursor.getInt(cursor.getColumnIndex("_me")) == 1) {
                z = true;
            }
            aVar.e = z;
            aVar.f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return aVar;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c6, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c8, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00c9, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d5, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00da, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e0, code lost:
        r3.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c6 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:7:0x002c] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00d5 A[Catch:{ all -> 0x00cb }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.tencent.bugly.crashreport.crash.a> b() {
        /*
            r16 = this;
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.lang.String r3 = "_id"
            java.lang.String r4 = "_tm"
            java.lang.String r5 = "_s1"
            java.lang.String r6 = "_up"
            java.lang.String r7 = "_me"
            java.lang.String r8 = "_uc"
            java.lang.String[] r11 = new java.lang.String[]{r3, r4, r5, r6, r7, r8}     // Catch:{ Throwable -> 0x00ce }
            com.tencent.bugly.proguard.p r9 = com.tencent.bugly.proguard.p.a()     // Catch:{ Throwable -> 0x00ce }
            java.lang.String r10 = "t_cr"
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 1
            android.database.Cursor r3 = r9.a(r10, r11, r12, r13, r14, r15)     // Catch:{ Throwable -> 0x00ce }
            if (r3 != 0) goto L_0x002c
            if (r3 == 0) goto L_0x002b
            r3.close()
        L_0x002b:
            return r2
        L_0x002c:
            int r0 = r3.getCount()     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            if (r0 > 0) goto L_0x0038
            if (r3 == 0) goto L_0x0037
            r3.close()
        L_0x0037:
            return r2
        L_0x0038:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            r0.<init>()     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            java.lang.String r2 = "_id in "
            r0.append(r2)     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            java.lang.String r2 = "("
            r0.append(r2)     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            r2 = 0
            r4 = 0
        L_0x0049:
            boolean r5 = r3.moveToNext()     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            if (r5 == 0) goto L_0x0076
            com.tencent.bugly.crashreport.crash.a r5 = b((android.database.Cursor) r3)     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            if (r5 == 0) goto L_0x0059
            r1.add(r5)     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            goto L_0x0049
        L_0x0059:
            java.lang.String r5 = "_id"
            int r5 = r3.getColumnIndex(r5)     // Catch:{ Throwable -> 0x006e, all -> 0x00c6 }
            long r5 = r3.getLong(r5)     // Catch:{ Throwable -> 0x006e, all -> 0x00c6 }
            r0.append(r5)     // Catch:{ Throwable -> 0x006e, all -> 0x00c6 }
            java.lang.String r5 = ","
            r0.append(r5)     // Catch:{ Throwable -> 0x006e, all -> 0x00c6 }
            int r4 = r4 + 1
            goto L_0x0049
        L_0x006e:
            java.lang.String r5 = "unknown id!"
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            com.tencent.bugly.proguard.x.d(r5, r6)     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            goto L_0x0049
        L_0x0076:
            java.lang.String r5 = r0.toString()     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            java.lang.String r6 = ","
            boolean r5 = r5.contains(r6)     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            if (r5 == 0) goto L_0x0092
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            java.lang.String r6 = ","
            int r6 = r0.lastIndexOf(r6)     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            java.lang.String r0 = r0.substring(r2, r6)     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            r5.<init>(r0)     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            r0 = r5
        L_0x0092:
            java.lang.String r5 = ")"
            r0.append(r5)     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            java.lang.String r8 = r0.toString()     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            r0.setLength(r2)     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            if (r4 <= 0) goto L_0x00c0
            com.tencent.bugly.proguard.p r6 = com.tencent.bugly.proguard.p.a()     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            java.lang.String r7 = "t_cr"
            r9 = 0
            r10 = 0
            r11 = 1
            int r0 = r6.a((java.lang.String) r7, (java.lang.String) r8, (java.lang.String[]) r9, (com.tencent.bugly.proguard.o) r10, (boolean) r11)     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            java.lang.String r4 = "deleted %s illegal data %d"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            java.lang.String r6 = "t_cr"
            r5[r2] = r6     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            r2 = 1
            r5[r2] = r0     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
            com.tencent.bugly.proguard.x.d(r4, r5)     // Catch:{ Throwable -> 0x00c8, all -> 0x00c6 }
        L_0x00c0:
            if (r3 == 0) goto L_0x00c5
            r3.close()
        L_0x00c5:
            return r1
        L_0x00c6:
            r0 = move-exception
            goto L_0x00de
        L_0x00c8:
            r0 = move-exception
            r2 = r3
            goto L_0x00cf
        L_0x00cb:
            r0 = move-exception
            r3 = r2
            goto L_0x00de
        L_0x00ce:
            r0 = move-exception
        L_0x00cf:
            boolean r3 = com.tencent.bugly.proguard.x.a(r0)     // Catch:{ all -> 0x00cb }
            if (r3 != 0) goto L_0x00d8
            r0.printStackTrace()     // Catch:{ all -> 0x00cb }
        L_0x00d8:
            if (r2 == 0) goto L_0x00dd
            r2.close()
        L_0x00dd:
            return r1
        L_0x00de:
            if (r3 == 0) goto L_0x00e3
            r3.close()
        L_0x00e3:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.b():java.util.List");
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

    private static an a(Context context, CrashDetailBean crashDetailBean, com.tencent.bugly.crashreport.common.info.a aVar) {
        am a2;
        am a3;
        am amVar;
        boolean z = false;
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
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(anVar.k);
            objArr[1] = Integer.valueOf(anVar.q != null ? anVar.q.size() : 0);
            x.c("crashcount:%d sz:%d", objArr);
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
        anVar.r.put("A23", crashDetailBean.f);
        anVar.r.put("A7", aVar.g);
        anVar.r.put("A6", aVar.s());
        anVar.r.put("A5", aVar.r());
        anVar.r.put("A22", aVar.h());
        Map<String, String> map4 = anVar.r;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(crashDetailBean.G);
        map4.put("A2", sb5.toString());
        Map<String, String> map5 = anVar.r;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(crashDetailBean.F);
        map5.put("A1", sb6.toString());
        anVar.r.put("A24", aVar.i);
        Map<String, String> map6 = anVar.r;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(crashDetailBean.H);
        map6.put("A17", sb7.toString());
        anVar.r.put("A3", aVar.k());
        anVar.r.put("A16", aVar.m());
        anVar.r.put("A25", aVar.n());
        anVar.r.put("A14", aVar.l());
        anVar.r.put("A15", aVar.w());
        Map<String, String> map7 = anVar.r;
        StringBuilder sb8 = new StringBuilder();
        sb8.append(aVar.x());
        map7.put("A13", sb8.toString());
        anVar.r.put("A34", crashDetailBean.A);
        if (aVar.y != null) {
            anVar.r.put("productIdentify", aVar.y);
        }
        try {
            anVar.r.put("A26", URLEncoder.encode(crashDetailBean.I, "utf-8"));
        } catch (UnsupportedEncodingException e8) {
            e8.printStackTrace();
        }
        if (crashDetailBean.b == 1) {
            anVar.r.put("A27", crashDetailBean.K);
            anVar.r.put("A28", crashDetailBean.J);
            Map<String, String> map8 = anVar.r;
            StringBuilder sb9 = new StringBuilder();
            sb9.append(crashDetailBean.k);
            map8.put("A29", sb9.toString());
        }
        anVar.r.put("A30", crashDetailBean.L);
        Map<String, String> map9 = anVar.r;
        StringBuilder sb10 = new StringBuilder();
        sb10.append(crashDetailBean.M);
        map9.put("A18", sb10.toString());
        Map<String, String> map10 = anVar.r;
        StringBuilder sb11 = new StringBuilder();
        sb11.append(!crashDetailBean.N);
        map10.put("A36", sb11.toString());
        Map<String, String> map11 = anVar.r;
        StringBuilder sb12 = new StringBuilder();
        sb12.append(aVar.r);
        map11.put("F02", sb12.toString());
        Map<String, String> map12 = anVar.r;
        StringBuilder sb13 = new StringBuilder();
        sb13.append(aVar.s);
        map12.put("F03", sb13.toString());
        anVar.r.put("F04", aVar.e());
        Map<String, String> map13 = anVar.r;
        StringBuilder sb14 = new StringBuilder();
        sb14.append(aVar.t);
        map13.put("F05", sb14.toString());
        anVar.r.put("F06", aVar.q);
        anVar.r.put("F08", aVar.w);
        anVar.r.put("F09", aVar.x);
        Map<String, String> map14 = anVar.r;
        StringBuilder sb15 = new StringBuilder();
        sb15.append(aVar.u);
        map14.put("F10", sb15.toString());
        if (crashDetailBean.Q >= 0) {
            Map<String, String> map15 = anVar.r;
            StringBuilder sb16 = new StringBuilder();
            sb16.append(crashDetailBean.Q);
            map15.put("C01", sb16.toString());
        }
        if (crashDetailBean.R >= 0) {
            Map<String, String> map16 = anVar.r;
            StringBuilder sb17 = new StringBuilder();
            sb17.append(crashDetailBean.R);
            map16.put("C02", sb17.toString());
        }
        if (crashDetailBean.S != null && crashDetailBean.S.size() > 0) {
            for (Map.Entry next3 : crashDetailBean.S.entrySet()) {
                anVar.r.put("C03_" + ((String) next3.getKey()), next3.getValue());
            }
        }
        if (crashDetailBean.T != null && crashDetailBean.T.size() > 0) {
            for (Map.Entry next4 : crashDetailBean.T.entrySet()) {
                anVar.r.put("C04_" + ((String) next4.getKey()), next4.getValue());
            }
        }
        anVar.s = null;
        if (crashDetailBean.O != null && crashDetailBean.O.size() > 0) {
            anVar.s = crashDetailBean.O;
            x.a("setted message size %d", Integer.valueOf(anVar.s.size()));
        }
        Object[] objArr2 = new Object[12];
        objArr2[0] = crashDetailBean.n;
        objArr2[1] = crashDetailBean.c;
        objArr2[2] = aVar.e();
        objArr2[3] = Long.valueOf((crashDetailBean.r - crashDetailBean.M) / 1000);
        objArr2[4] = Boolean.valueOf(crashDetailBean.k);
        objArr2[5] = Boolean.valueOf(crashDetailBean.N);
        objArr2[6] = Boolean.valueOf(crashDetailBean.j);
        if (crashDetailBean.b == 1) {
            z = true;
        }
        objArr2[7] = Boolean.valueOf(z);
        objArr2[8] = Integer.valueOf(crashDetailBean.t);
        objArr2[9] = crashDetailBean.s;
        objArr2[10] = Boolean.valueOf(crashDetailBean.d);
        objArr2[11] = Integer.valueOf(anVar.r.size());
        x.c("%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d", objArr2);
        return anVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0093 A[Catch:{ all -> 0x00b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0098 A[SYNTHETIC, Splitter:B:37:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ba A[SYNTHETIC, Splitter:B:49:0x00ba] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00ce  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.tencent.bugly.proguard.am a(java.lang.String r5, android.content.Context r6, java.lang.String r7) {
        /*
            r0 = 0
            r1 = 0
            if (r7 == 0) goto L_0x00d9
            if (r6 != 0) goto L_0x0008
            goto L_0x00d9
        L_0x0008:
            java.lang.String r2 = "zip %s"
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r4[r1] = r7
            com.tencent.bugly.proguard.x.c(r2, r4)
            java.io.File r2 = new java.io.File
            r2.<init>(r7)
            java.io.File r7 = new java.io.File
            java.io.File r6 = r6.getCacheDir()
            r7.<init>(r6, r5)
            r5 = 5000(0x1388, float:7.006E-42)
            boolean r5 = com.tencent.bugly.proguard.z.a((java.io.File) r2, (java.io.File) r7, (int) r5)
            if (r5 != 0) goto L_0x0030
            java.lang.String r5 = "zip fail!"
            java.lang.Object[] r6 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.d(r5, r6)
            return r0
        L_0x0030:
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream
            r5.<init>()
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x008b, all -> 0x0088 }
            r6.<init>(r7)     // Catch:{ Throwable -> 0x008b, all -> 0x0088 }
            r2 = 4096(0x1000, float:5.74E-42)
            byte[] r2 = new byte[r2]     // Catch:{ Throwable -> 0x0086 }
        L_0x003e:
            int r4 = r6.read(r2)     // Catch:{ Throwable -> 0x0086 }
            if (r4 <= 0) goto L_0x004b
            r5.write(r2, r1, r4)     // Catch:{ Throwable -> 0x0086 }
            r5.flush()     // Catch:{ Throwable -> 0x0086 }
            goto L_0x003e
        L_0x004b:
            byte[] r5 = r5.toByteArray()     // Catch:{ Throwable -> 0x0086 }
            java.lang.String r2 = "read bytes :%d"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0086 }
            int r4 = r5.length     // Catch:{ Throwable -> 0x0086 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x0086 }
            r3[r1] = r4     // Catch:{ Throwable -> 0x0086 }
            com.tencent.bugly.proguard.x.c(r2, r3)     // Catch:{ Throwable -> 0x0086 }
            com.tencent.bugly.proguard.am r2 = new com.tencent.bugly.proguard.am     // Catch:{ Throwable -> 0x0086 }
            r3 = 2
            java.lang.String r4 = r7.getName()     // Catch:{ Throwable -> 0x0086 }
            r2.<init>(r3, r4, r5)     // Catch:{ Throwable -> 0x0086 }
            r6.close()     // Catch:{ IOException -> 0x006b }
            goto L_0x0075
        L_0x006b:
            r5 = move-exception
            boolean r6 = com.tencent.bugly.proguard.x.a(r5)
            if (r6 != 0) goto L_0x0075
            r5.printStackTrace()
        L_0x0075:
            boolean r5 = r7.exists()
            if (r5 == 0) goto L_0x0085
            java.lang.String r5 = "del tmp"
            java.lang.Object[] r6 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.c(r5, r6)
            r7.delete()
        L_0x0085:
            return r2
        L_0x0086:
            r5 = move-exception
            goto L_0x008d
        L_0x0088:
            r5 = move-exception
            r6 = r0
            goto L_0x00b8
        L_0x008b:
            r5 = move-exception
            r6 = r0
        L_0x008d:
            boolean r2 = com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x00b7 }
            if (r2 != 0) goto L_0x0096
            r5.printStackTrace()     // Catch:{ all -> 0x00b7 }
        L_0x0096:
            if (r6 == 0) goto L_0x00a6
            r6.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x00a6
        L_0x009c:
            r5 = move-exception
            boolean r6 = com.tencent.bugly.proguard.x.a(r5)
            if (r6 != 0) goto L_0x00a6
            r5.printStackTrace()
        L_0x00a6:
            boolean r5 = r7.exists()
            if (r5 == 0) goto L_0x00b6
            java.lang.String r5 = "del tmp"
            java.lang.Object[] r6 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.c(r5, r6)
            r7.delete()
        L_0x00b6:
            return r0
        L_0x00b7:
            r5 = move-exception
        L_0x00b8:
            if (r6 == 0) goto L_0x00c8
            r6.close()     // Catch:{ IOException -> 0x00be }
            goto L_0x00c8
        L_0x00be:
            r6 = move-exception
            boolean r0 = com.tencent.bugly.proguard.x.a(r6)
            if (r0 != 0) goto L_0x00c8
            r6.printStackTrace()
        L_0x00c8:
            boolean r6 = r7.exists()
            if (r6 == 0) goto L_0x00d8
            java.lang.Object[] r6 = new java.lang.Object[r1]
            java.lang.String r0 = "del tmp"
            com.tencent.bugly.proguard.x.c(r0, r6)
            r7.delete()
        L_0x00d8:
            throw r5
        L_0x00d9:
            java.lang.String r5 = "rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}"
            java.lang.Object[] r6 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.d(r5, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.a(java.lang.String, android.content.Context, java.lang.String):com.tencent.bugly.proguard.am");
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
                Object[] objArr = new Object[2];
                objArr[0] = b2.h;
                objArr[1] = b2.x().booleanValue() ? "ROOTED" : "UNROOT";
                x.e("# CRASH DEVICE: %s %s", objArr);
                x.e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.C), Long.valueOf(crashDetailBean.D), Long.valueOf(crashDetailBean.E));
                x.e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.F), Long.valueOf(crashDetailBean.G), Long.valueOf(crashDetailBean.H));
                if (!z.a(crashDetailBean.K)) {
                    x.e("# EXCEPTION FIRED BY %s %s", crashDetailBean.K, crashDetailBean.J);
                } else if (crashDetailBean.b == 3) {
                    Object[] objArr2 = new Object[1];
                    if (crashDetailBean.P == null) {
                        str6 = "null";
                    } else {
                        str6 = crashDetailBean.P.get("BUGLY_CR_01");
                    }
                    objArr2[0] = str6;
                    x.e("# EXCEPTION ANR MESSAGE:\n %s", objArr2);
                }
            }
            if (!z.a(str5)) {
                x.e("# CRASH STACK: ", new Object[0]);
                x.e(str5, new Object[0]);
            }
            x.e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
        }
    }
}
