package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import com.meiqia.core.bean.MQInquireForm;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.common.info.b;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class v implements Runnable {
    private int a;
    private int b;
    private final Context c;
    private final int d;
    private final byte[] e;
    private final a f;
    private final com.tencent.bugly.crashreport.common.strategy.a g;
    private final s h;
    private final u i;
    private final int j;
    private final t k;
    private final t l;
    private String m;
    private final String n;
    private final Map<String, String> o;
    private int p;
    private long q;
    private long r;
    private boolean s;
    private boolean t;

    public v(Context context, int i2, int i3, byte[] bArr, String str, String str2, t tVar, boolean z, int i4, int i5, boolean z2, Map<String, String> map) {
        this.a = 2;
        this.b = BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH;
        this.m = null;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = true;
        this.t = false;
        this.c = context;
        this.f = a.a(context);
        this.e = bArr;
        this.g = com.tencent.bugly.crashreport.common.strategy.a.a();
        this.h = s.a(context);
        this.i = u.a();
        this.j = i2;
        this.m = str;
        this.n = str2;
        this.k = tVar;
        u uVar = this.i;
        this.l = null;
        this.s = z;
        this.d = i3;
        if (i4 > 0) {
            this.a = i4;
        }
        if (i5 > 0) {
            this.b = i5;
        }
        this.t = z2;
        this.o = map;
    }

    public v(Context context, int i2, int i3, byte[] bArr, String str, String str2, t tVar, boolean z, boolean z2) {
        this(context, i2, i3, bArr, str, str2, tVar, z, 2, BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH, z2, (Map<String, String>) null);
    }

    private static String a(String str) {
        if (z.a(str)) {
            return str;
        }
        try {
            return String.format("%s?aid=%s", new Object[]{str, UUID.randomUUID().toString()});
        } catch (Throwable th) {
            x.a(th);
            return str;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001c  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    private void a(aq aqVar, boolean z, int i2, String str, int i3) {
        String str2;
        int i4 = this.d;
        if (i4 != 630) {
            if (i4 != 640) {
                if (i4 != 830) {
                    if (i4 != 840) {
                        str2 = String.valueOf(this.d);
                        if (z) {
                            x.a("[Upload] Success: %s", str2);
                        } else {
                            x.e("[Upload] Failed to upload(%d) %s: %s", Integer.valueOf(i2), str2, str);
                            if (this.s) {
                                this.i.a(i3, (aq) null);
                            }
                        }
                        if (this.q + this.r > 0) {
                            long a2 = this.i.a(this.t);
                            long j2 = this.q;
                            this.i.a(a2 + j2 + this.r, this.t);
                        }
                        if (this.k != null) {
                            t tVar = this.k;
                            int i5 = this.d;
                            long j3 = this.q;
                            long j4 = this.r;
                            tVar.a(z);
                        }
                        if (this.l != null) {
                            t tVar2 = this.l;
                            int i6 = this.d;
                            long j5 = this.q;
                            long j6 = this.r;
                            tVar2.a(z);
                            return;
                        }
                        return;
                    }
                }
            }
            str2 = "userinfo";
            if (z) {
            }
            if (this.q + this.r > 0) {
            }
            if (this.k != null) {
            }
            if (this.l != null) {
            }
        }
        str2 = "crash";
        if (z) {
        }
        if (this.q + this.r > 0) {
        }
        if (this.k != null) {
        }
        if (this.l != null) {
        }
    }

    private static boolean a(aq aqVar, a aVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        if (aqVar == null) {
            x.d("resp == null!", new Object[0]);
            return false;
        } else if (aqVar.a != 0) {
            x.e("resp result error %d", Byte.valueOf(aqVar.a));
            return false;
        } else {
            try {
                if (!z.a(aqVar.d) && !a.b().i().equals(aqVar.d)) {
                    p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "gateway", aqVar.d.getBytes("UTF-8"), (o) null, true);
                    aVar.d(aqVar.d);
                }
                if (!z.a(aqVar.f) && !a.b().j().equals(aqVar.f)) {
                    p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "device", aqVar.f.getBytes("UTF-8"), (o) null, true);
                    aVar.e(aqVar.f);
                }
            } catch (Throwable th) {
                x.a(th);
            }
            aVar.j = aqVar.e;
            if (aqVar.b == 510) {
                if (aqVar.c == null) {
                    x.e("[Upload] Strategy data is null. Response cmd: %d", Integer.valueOf(aqVar.b));
                    return false;
                }
                as asVar = (as) a.a(aqVar.c, as.class);
                if (asVar == null) {
                    x.e("[Upload] Failed to decode strategy from server. Response cmd: %d", Integer.valueOf(aqVar.b));
                    return false;
                }
                aVar2.a(asVar);
            }
            return true;
        }
    }

    public final void a(long j2) {
        this.p++;
        this.q += j2;
    }

    public final void b(long j2) {
        this.r += j2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x02d6, code lost:
        if (r5 == 0) goto L_0x037a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x02d9, code lost:
        if (r5 != 2) goto L_0x0339;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x02e4, code lost:
        if ((r12.q + r12.r) <= 0) goto L_0x02fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x02e6, code lost:
        r12.i.a((r12.i.a(r12.t) + r12.q) + r12.r, r12.t);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x02fb, code lost:
        r12.i.a(r5, (com.tencent.bugly.proguard.aq) null);
        com.tencent.bugly.proguard.x.a("[Upload] Session ID is invalid, will try again immediately (pid=%d | tid=%d).", java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
        r12.i.a(r12.j, r12.d, r12.e, r12.m, r12.n, r12.k, r12.a, r12.b, true, r12.o);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0339, code lost:
        a((com.tencent.bugly.proguard.aq) null, false, 1, "status of server is " + r5, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:?, code lost:
        return;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01e1 A[Catch:{ Throwable -> 0x0350, Throwable -> 0x0031 }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x02a5  */
    public final void run() {
        byte[] a2;
        Map<String, String> map;
        int i2;
        byte[] bArr;
        boolean z;
        int i3;
        Object[] objArr;
        try {
            this.p = 0;
            this.q = 0;
            this.r = 0;
            byte[] bArr2 = this.e;
            if (b.c(this.c) == null) {
                a((aq) null, false, 0, "network is not available", 0);
            } else if (bArr2 == null || bArr2.length == 0) {
                a((aq) null, false, 0, "request package is empty!", 0);
            } else {
                x.c("[Upload] Run upload task with cmd: %d", Integer.valueOf(this.d));
                if (this.c == null || this.f == null || this.g == null || this.h == null) {
                    a((aq) null, false, 0, "illegal access error", 0);
                    return;
                }
                StrategyBean c2 = this.g.c();
                if (c2 == null) {
                    a((aq) null, false, 0, "illegal local strategy", 0);
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("prodId", this.f.f());
                hashMap.put("bundleId", this.f.c);
                hashMap.put("appVer", this.f.k);
                if (this.o != null) {
                    hashMap.putAll(this.o);
                }
                if (this.s) {
                    hashMap.put("cmd", Integer.toString(this.d));
                    hashMap.put(ConstantsAPI.Token.WX_TOKEN_PLATFORMID_KEY, Byte.toString((byte) 1));
                    hashMap.put("sdkVer", this.f.f);
                    hashMap.put("strategylastUpdateTime", Long.toString(c2.p));
                    if (!this.i.a((Map<String, String>) hashMap)) {
                        a((aq) null, false, 0, "failed to add security info to HTTP headers", 0);
                        return;
                    }
                    byte[] a3 = z.a(bArr2, 2);
                    if (a3 == null) {
                        a((aq) null, false, 0, "failed to zip request body", 0);
                        return;
                    }
                    bArr2 = this.i.a(a3);
                    if (bArr2 == null) {
                        a((aq) null, false, 0, "failed to encrypt request body", 0);
                        return;
                    }
                }
                byte[] bArr3 = bArr2;
                this.i.a(this.j, System.currentTimeMillis());
                if (this.k != null) {
                    t tVar = this.k;
                    int i4 = this.d;
                }
                if (this.l != null) {
                    t tVar2 = this.l;
                    int i5 = this.d;
                }
                int i6 = 0;
                int i7 = 0;
                int i8 = -1;
                String str = this.m;
                while (true) {
                    int i9 = i6 + 1;
                    if (i6 < this.a) {
                        if (i9 > 1) {
                            x.d("[Upload] Failed to upload last time, wait and try(%d) again.", Integer.valueOf(i9));
                            z.b((long) this.b);
                            if (i9 == this.a) {
                                x.d("[Upload] Use the back-up url at the last time: %s", this.n);
                                str = this.n;
                            }
                        }
                        x.c("[Upload] Send %d bytes", Integer.valueOf(bArr3.length));
                        if (this.s) {
                            str = a(str);
                        }
                        x.c("[Upload] Upload to %s with cmd %d (pid=%d | tid=%d).", str, Integer.valueOf(this.d), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                        a2 = this.h.a(str, bArr3, this, (Map<String, String>) hashMap);
                        if (a2 != null) {
                            map = this.h.a;
                            if (!this.s) {
                                i2 = i8;
                                break;
                            }
                            if (map == null || map.size() == 0) {
                                x.d("[Upload] Headers is empty.", new Object[0]);
                            } else {
                                if (!map.containsKey(MQInquireForm.KEY_STATUS)) {
                                    objArr = new Object[1];
                                    objArr[0] = MQInquireForm.KEY_STATUS;
                                } else if (!map.containsKey("Bugly-Version")) {
                                    objArr = new Object[1];
                                    objArr[0] = "Bugly-Version";
                                } else {
                                    String str2 = map.get("Bugly-Version");
                                    if (!str2.contains("bugly")) {
                                        x.d("[Upload] Bugly version is not valid: %s", str2);
                                    } else {
                                        x.c("[Upload] Bugly version from headers is: %s", str2);
                                        z = true;
                                        if (z) {
                                            x.c("[Upload] Headers from server is not valid, just try again (pid=%d | tid=%d).", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                                            x.e("[Upload] Failed to upload(%d): %s", 1, "[Upload] Failed to upload for no status header.");
                                            if (map != null) {
                                                for (Map.Entry next : map.entrySet()) {
                                                    x.c(String.format("[key]: %s, [value]: %s", new Object[]{next.getKey(), next.getValue()}), new Object[0]);
                                                }
                                            }
                                            x.c("[Upload] Failed to upload for no status header.", new Object[0]);
                                            i3 = i8;
                                        } else {
                                            try {
                                                i2 = Integer.parseInt(map.get(MQInquireForm.KEY_STATUS));
                                                x.c("[Upload] Status from server is %d (pid=%d | tid=%d).", Integer.valueOf(i2), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                                                break;
                                            } catch (Throwable th) {
                                                i3 = i8;
                                                x.e("[Upload] Failed to upload(%d): %s", 1, "[Upload] Failed to upload for format of status header is invalid: " + Integer.toString(i3));
                                                i7 = 1;
                                                i6 = i9;
                                                i8 = i3;
                                            }
                                        }
                                    }
                                }
                                x.d("[Upload] Headers does not contain %s", objArr);
                            }
                            z = false;
                            if (z) {
                            }
                        } else {
                            x.e("[Upload] Failed to upload(%d): %s", 1, "Failed to upload for no response!");
                            i3 = i8;
                        }
                        i7 = 1;
                        i6 = i9;
                        i8 = i3;
                    } else {
                        a((aq) null, false, i7, "failed after many attempts", 0);
                        return;
                    }
                }
                x.c("[Upload] Received %d bytes", Integer.valueOf(a2.length));
                if (!this.s) {
                    bArr = a2;
                } else if (a2.length == 0) {
                    for (Map.Entry next2 : map.entrySet()) {
                        x.c("[Upload] HTTP headers from server: key = %s, value = %s", next2.getKey(), next2.getValue());
                    }
                    a((aq) null, false, 1, "response data from server is empty", 0);
                    return;
                } else {
                    byte[] b2 = this.i.b(a2);
                    if (b2 == null) {
                        a((aq) null, false, 1, "failed to decrypt response from server", 0);
                        return;
                    }
                    bArr = z.b(b2, 2);
                    if (bArr == null) {
                        a((aq) null, false, 1, "failed unzip(Gzip) response from server", 0);
                        return;
                    }
                }
                aq a4 = a.a(bArr, this.s);
                if (a4 == null) {
                    a((aq) null, false, 1, "failed to decode response package", 0);
                    return;
                }
                if (this.s) {
                    this.i.a(i2, a4);
                }
                x.c("[Upload] Response cmd is: %d, length of sBuffer is: %d", Integer.valueOf(a4.b), Integer.valueOf(a4.c == null ? 0 : a4.c.length));
                if (!a(a4, this.f, this.g)) {
                    a(a4, false, 2, "failed to process response package", 0);
                } else {
                    a(a4, true, 2, "successfully uploaded", 0);
                }
            }
        } catch (Throwable th2) {
            if (!x.a(th2)) {
                th2.printStackTrace();
            }
        }
    }
}
