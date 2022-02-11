package com.tencent.bugly.crashreport.biz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcelable;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.ap;
import com.tencent.bugly.proguard.au;
import com.tencent.bugly.proguard.k;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.t;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class a {
    private Context a;
    /* access modifiers changed from: private */
    public long b;
    private int c;
    private boolean d = true;

    /* renamed from: com.tencent.bugly.crashreport.biz.a$a  reason: collision with other inner class name */
    final class C0001a implements Runnable {
        private boolean a;
        private UserInfoBean b;

        public C0001a(UserInfoBean userInfoBean, boolean z) {
            this.b = userInfoBean;
            this.a = z;
        }

        public final void run() {
            com.tencent.bugly.crashreport.common.info.a b2;
            try {
                if (this.b != null) {
                    UserInfoBean userInfoBean = this.b;
                    if (!(userInfoBean == null || (b2 = com.tencent.bugly.crashreport.common.info.a.b()) == null)) {
                        userInfoBean.j = b2.e();
                    }
                    x.c("[UserInfo] Record user info.", new Object[0]);
                    a.a(a.this, this.b, false);
                }
                if (this.a) {
                    a aVar = a.this;
                    w a2 = w.a();
                    if (a2 != null) {
                        a2.a(new Runnable() {
                            public final void run() {
                                try {
                                    a.this.c();
                                } catch (Throwable th) {
                                    x.a(th);
                                }
                            }
                        });
                    }
                }
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    final class b implements Runnable {
        b() {
        }

        public final void run() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < a.this.b) {
                w.a().a(new b(), (a.this.b - currentTimeMillis) + 5000);
                return;
            }
            a.this.a(3, false, 0);
            a.this.a();
        }
    }

    final class c implements Runnable {
        private long a = 21600000;

        public c(long j) {
            this.a = j;
        }

        public final void run() {
            a aVar = a.this;
            w a2 = w.a();
            if (a2 != null) {
                a2.a(new Runnable() {
                    public final void run() {
                        try {
                            a.this.c();
                        } catch (Throwable th) {
                            x.a(th);
                        }
                    }
                });
            }
            a aVar2 = a.this;
            long j = this.a;
            w.a().a(new c(j), j);
        }
    }

    public a(Context context, boolean z) {
        this.a = context;
        this.d = z;
    }

    private static ContentValues a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.a > 0) {
                contentValues.put("_id", Long.valueOf(userInfoBean.a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.b));
            contentValues.put("_pc", userInfoBean.c);
            contentValues.put("_dt", z.a((Parcelable) userInfoBean));
            return contentValues;
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    private static UserInfoBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            UserInfoBean userInfoBean = (UserInfoBean) z.a(blob, UserInfoBean.CREATOR);
            if (userInfoBean == null) {
                return userInfoBean;
            }
            userInfoBean.a = j;
            return userInfoBean;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    static /* synthetic */ void a(a aVar, UserInfoBean userInfoBean, boolean z) {
        List<UserInfoBean> a2;
        if (userInfoBean == null) {
            return;
        }
        if (z || userInfoBean.b == 1 || (a2 = aVar.a(com.tencent.bugly.crashreport.common.info.a.a(aVar.a).d)) == null || a2.size() < 20) {
            long a3 = p.a().a("t_ui", a(userInfoBean), (o) null, true);
            if (a3 >= 0) {
                x.c("[Database] insert %s success with ID: %d", "t_ui", Long.valueOf(a3));
                userInfoBean.a = a3;
                return;
            }
            return;
        }
        x.a("[UserInfo] There are too many user info in local: %d", Integer.valueOf(a2.size()));
    }

    private static void a(List<UserInfoBean> list) {
        if (list != null && list.size() != 0) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < list.size() && i < 50) {
                sb.append(" or _id");
                sb.append(" = ");
                sb.append(list.get(i).a);
                i++;
            }
            String sb2 = sb.toString();
            if (sb2.length() > 0) {
                sb2 = sb2.substring(4);
            }
            sb.setLength(0);
            try {
                x.c("[Database] deleted %s data %d", "t_ui", Integer.valueOf(p.a().a("t_ui", sb2, (String[]) null, (o) null, true)));
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        List<UserInfoBean> list;
        boolean z;
        synchronized (this) {
            if (this.d) {
                u a2 = u.a();
                if (a2 != null) {
                    com.tencent.bugly.crashreport.common.strategy.a a3 = com.tencent.bugly.crashreport.common.strategy.a.a();
                    if (a3 != null) {
                        if (!a3.b() || a2.b(1001)) {
                            String str = com.tencent.bugly.crashreport.common.info.a.a(this.a).d;
                            ArrayList arrayList = new ArrayList();
                            final List<UserInfoBean> a4 = a(str);
                            if (a4 != null) {
                                int size = a4.size() - 20;
                                if (size > 0) {
                                    int i = 0;
                                    while (i < a4.size() - 1) {
                                        int i2 = i + 1;
                                        for (int i3 = i2; i3 < a4.size(); i3++) {
                                            if (a4.get(i).e > a4.get(i3).e) {
                                                a4.set(i, a4.get(i3));
                                                a4.set(i3, a4.get(i));
                                            }
                                        }
                                        i = i2;
                                    }
                                    for (int i4 = 0; i4 < size; i4++) {
                                        arrayList.add(a4.get(i4));
                                    }
                                }
                                Iterator<UserInfoBean> it = a4.iterator();
                                int i5 = 0;
                                while (it.hasNext()) {
                                    UserInfoBean next = it.next();
                                    if (next.f != -1) {
                                        it.remove();
                                        if (next.e < z.b()) {
                                            arrayList.add(next);
                                        }
                                    }
                                    if (next.e > System.currentTimeMillis() - 600000 && (next.b == 1 || next.b == 4 || next.b == 3)) {
                                        i5++;
                                    }
                                }
                                if (i5 > 15) {
                                    x.d("[UserInfo] Upload user info too many times in 10 min: %d", Integer.valueOf(i5));
                                    z = false;
                                } else {
                                    list = a4;
                                    z = true;
                                    a4 = list;
                                }
                            } else {
                                list = new ArrayList<>();
                                z = true;
                                a4 = list;
                            }
                            if (arrayList.size() > 0) {
                                a((List<UserInfoBean>) arrayList);
                            }
                            if (!z || a4.size() == 0) {
                                x.c("[UserInfo] There is no user info in local database.", new Object[0]);
                                return;
                            }
                            x.c("[UserInfo] Upload user info(size: %d)", Integer.valueOf(a4.size()));
                            au a5 = com.tencent.bugly.proguard.a.a(a4, this.c == 1 ? 1 : 2);
                            if (a5 == null) {
                                x.d("[UserInfo] Failed to create UserInfoPackage.", new Object[0]);
                                return;
                            }
                            byte[] a6 = com.tencent.bugly.proguard.a.a((k) a5);
                            if (a6 == null) {
                                x.d("[UserInfo] Failed to encode data.", new Object[0]);
                                return;
                            }
                            ap a7 = com.tencent.bugly.proguard.a.a(this.a, a2.a ? 840 : 640, a6);
                            if (a7 == null) {
                                x.d("[UserInfo] Request package is null.", new Object[0]);
                                return;
                            }
                            AnonymousClass1 r5 = new t() {
                                public final void a(boolean z) {
                                    if (z) {
                                        x.c("[UserInfo] Successfully uploaded user info.", new Object[0]);
                                        long currentTimeMillis = System.currentTimeMillis();
                                        for (UserInfoBean userInfoBean : a4) {
                                            userInfoBean.f = currentTimeMillis;
                                            a.a(a.this, userInfoBean, true);
                                        }
                                    }
                                }
                            };
                            StrategyBean c2 = com.tencent.bugly.crashreport.common.strategy.a.a().c();
                            u.a().a(1001, a7, a2.a ? c2.r : c2.t, a2.a ? StrategyBean.b : StrategyBean.a, r5, this.c == 1);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004d, code lost:
        r1 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007f A[ExcHandler: all (th java.lang.Throwable), Splitter:B:11:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0083  */
    public final List<UserInfoBean> a(String str) {
        Cursor cursor;
        Cursor cursor2;
        String str2;
        Cursor cursor3 = null;
        try {
            if (z.a(str)) {
                str2 = null;
            } else {
                str2 = "_pc = '" + str + "'";
            }
            cursor2 = p.a().a("t_ui", (String[]) null, str2, (String[]) null, (o) null, true);
            if (cursor2 != null) {
                try {
                    StringBuilder sb = new StringBuilder();
                    ArrayList arrayList = new ArrayList();
                    while (cursor2.moveToNext()) {
                        UserInfoBean a2 = a(cursor2);
                        if (a2 != null) {
                            arrayList.add(a2);
                        } else {
                            long j = cursor2.getLong(cursor2.getColumnIndex("_id"));
                            sb.append(" or _id");
                            sb.append(" = ");
                            sb.append(j);
                        }
                    }
                    String sb2 = sb.toString();
                    if (sb2.length() > 0) {
                        x.d("[Database] deleted %s error data %d", "t_ui", Integer.valueOf(p.a().a("t_ui", sb2.substring(4), (String[]) null, (o) null, true)));
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

    public final void a() {
        this.b = z.b() + 86400000;
        w.a().a(new b(), (this.b - System.currentTimeMillis()) + 5000);
    }

    public final void a(int i, boolean z, long j) {
        int i2 = 1;
        com.tencent.bugly.crashreport.common.strategy.a a2 = com.tencent.bugly.crashreport.common.strategy.a.a();
        if (a2 == null || a2.c().h || i == 1 || i == 3) {
            if (i == 1 || i == 3) {
                this.c++;
            }
            com.tencent.bugly.crashreport.common.info.a a3 = com.tencent.bugly.crashreport.common.info.a.a(this.a);
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.b = i;
            userInfoBean.c = a3.d;
            userInfoBean.d = a3.g();
            userInfoBean.e = System.currentTimeMillis();
            userInfoBean.f = -1;
            userInfoBean.n = a3.k;
            if (i != 1) {
                i2 = 0;
            }
            userInfoBean.o = i2;
            userInfoBean.l = a3.a();
            userInfoBean.m = a3.q;
            userInfoBean.g = a3.r;
            userInfoBean.h = a3.s;
            userInfoBean.i = a3.t;
            userInfoBean.k = a3.u;
            userInfoBean.r = a3.B();
            userInfoBean.s = a3.G();
            userInfoBean.p = a3.H();
            userInfoBean.q = a3.I();
            w.a().a(new C0001a(userInfoBean, z), 0);
            return;
        }
        x.e("UserInfo is disable", new Object[0]);
    }

    public final void b() {
        w a2 = w.a();
        if (a2 != null) {
            a2.a(new Runnable() {
                public final void run() {
                    try {
                        a.this.c();
                    } catch (Throwable th) {
                        x.a(th);
                    }
                }
            });
        }
    }
}
