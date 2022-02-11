package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.s;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback;
import com.umeng.commonsdk.statistics.internal.UMImprintPreProcessCallback;
import com.umeng.commonsdk.statistics.internal.d;
import com.umeng.commonsdk.statistics.proto.e;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class ImprintHandler implements FileLockCallback {
    private static final String a = "ImprintHandler";
    private static Object b = new Object();
    private static final String c = ".imprint";
    private static final byte[] d = "pbl0".getBytes();
    private static Map<String, ArrayList<UMImprintChangeCallback>> f = new HashMap();
    private static Object g = new Object();
    private static ImprintHandler j = null;
    private static Context k = null;
    private static FileLockUtil l = null;
    private static final int m = 0;
    private static final int n = 1;
    private static Map<String, UMImprintPreProcessCallback> o = new HashMap();
    private static Object p = new Object();
    private d e;
    private a h = new a();
    private com.umeng.commonsdk.statistics.proto.d i = null;

    public boolean onFileLock(String str) {
        return false;
    }

    public boolean onFileLock(String str, Object obj) {
        return false;
    }

    public boolean onFileLock(File file, int i2) {
        if (i2 == 0) {
            j.e();
        } else if (i2 == 1) {
            j.a(file);
        }
        return true;
    }

    private ImprintHandler(Context context) {
        k = StubApp.getOrigApplicationContext(context.getApplicationContext());
    }

    public static synchronized ImprintHandler getImprintService(Context context) {
        ImprintHandler imprintHandler;
        synchronized (ImprintHandler.class) {
            if (j == null) {
                j = new ImprintHandler(context);
                l = new FileLockUtil();
                if (l != null) {
                    l.doFileOperateion(new File(k.getFilesDir(), c), (FileLockCallback) j, 0);
                }
            }
            imprintHandler = j;
        }
        return imprintHandler;
    }

    private static void a(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        synchronized (g) {
            try {
                int i2 = 0;
                if (f.containsKey(str)) {
                    ArrayList arrayList = f.get(str);
                    int size = arrayList.size();
                    ULog.i("--->>> addCallback: before add: callbacks size is: " + size);
                    while (i2 < size) {
                        if (uMImprintChangeCallback == arrayList.get(i2)) {
                            ULog.i("--->>> addCallback: callback has exist, just exit");
                            return;
                        }
                        i2++;
                    }
                    arrayList.add(uMImprintChangeCallback);
                    ULog.i("--->>> addCallback: after add: callbacks size is: " + arrayList.size());
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    int size2 = arrayList2.size();
                    ULog.i("--->>> addCallback: before add: callbacks size is: " + size2);
                    while (i2 < size2) {
                        if (uMImprintChangeCallback == arrayList2.get(i2)) {
                            ULog.i("--->>> addCallback: callback has exist, just exit");
                            return;
                        }
                        i2++;
                    }
                    arrayList2.add(uMImprintChangeCallback);
                    ULog.i("--->>> addCallback: after add: callbacks size is: " + arrayList2.size());
                    f.put(str, arrayList2);
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(k, th);
            }
        }
    }

    private static void b(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintChangeCallback != null) {
            synchronized (g) {
                try {
                    if (f.containsKey(str)) {
                        ArrayList arrayList = f.get(str);
                        if (uMImprintChangeCallback != null && arrayList.size() > 0) {
                            int size = arrayList.size();
                            ULog.i("--->>> removeCallback: before remove: callbacks size is: " + size);
                            int i2 = 0;
                            while (true) {
                                if (i2 >= size) {
                                    break;
                                } else if (uMImprintChangeCallback == arrayList.get(i2)) {
                                    ULog.i("--->>> removeCallback: remove index " + i2);
                                    arrayList.remove(i2);
                                    break;
                                } else {
                                    i2++;
                                }
                            }
                            ULog.i("--->>> removeCallback: after remove: callbacks size is: " + arrayList.size());
                            if (arrayList.size() == 0) {
                                ULog.i("--->>> removeCallback: remove key from map: key = " + str);
                                f.remove(str);
                            }
                        }
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(k, th);
                }
            }
        }
    }

    public void registImprintCallback(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintChangeCallback != null) {
            a(str, uMImprintChangeCallback);
        }
    }

    public void unregistImprintCallback(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintChangeCallback != null) {
            b(str, uMImprintChangeCallback);
        }
    }

    public void registPreProcessCallback(String str, UMImprintPreProcessCallback uMImprintPreProcessCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintPreProcessCallback != null) {
            synchronized (p) {
                try {
                    if (!o.containsKey(str)) {
                        o.put(str, uMImprintPreProcessCallback);
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> registPreProcessCallback: key : " + str + " regist success.");
                    } else {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> key : " + str + " PreProcesser has registed!");
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(k, th);
                }
            }
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (p) {
                try {
                    if (o.containsKey(str)) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> unregistPreProcessCallback: unregist [" + str + "] success.");
                        f.remove(str);
                    } else {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> unregistPreProcessCallback: can't find [" + str + "], pls regist first.");
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(k, th);
                }
            }
        }
    }

    public void a(d dVar) {
        this.e = dVar;
    }

    public String a(com.umeng.commonsdk.statistics.proto.d dVar) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : new TreeMap(dVar.c()).entrySet()) {
            sb.append((String) entry.getKey());
            if (((e) entry.getValue()).d()) {
                sb.append(((e) entry.getValue()).b());
            }
            sb.append(((e) entry.getValue()).e());
            sb.append(((e) entry.getValue()).h());
        }
        sb.append(dVar.b);
        return HelperUtils.MD5(sb.toString()).toLowerCase(Locale.US);
    }

    private boolean c(com.umeng.commonsdk.statistics.proto.d dVar) {
        if (!dVar.i().equals(a(dVar))) {
            return false;
        }
        for (e next : dVar.c().values()) {
            byte[] reverseHexString = DataHelper.reverseHexString(next.h());
            byte[] a2 = a(next);
            int i2 = 0;
            while (true) {
                if (i2 < 4) {
                    if (reverseHexString[i2] != a2[i2]) {
                        return false;
                    }
                    i2++;
                }
            }
        }
        return true;
    }

    public byte[] a(e eVar) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order((ByteOrder) null);
        allocate.putLong(eVar.e());
        byte[] array = allocate.array();
        byte[] bArr = d;
        byte[] bArr2 = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr2[i2] = (byte) (array[i2] ^ bArr[i2]);
        }
        return bArr2;
    }

    public byte[] a() {
        try {
            synchronized (this) {
                if (this.i == null) {
                    return null;
                }
                byte[] a2 = new s().a(this.i);
                return a2;
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(k, th);
            return null;
        }
    }

    public void b(com.umeng.commonsdk.statistics.proto.d dVar) {
        String str;
        com.umeng.commonsdk.statistics.proto.d dVar2;
        boolean z;
        if (dVar == null) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.d("Imprint is null");
            }
        } else if (c(dVar)) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.d("Imprint is ok");
            }
            HashMap hashMap = new HashMap();
            synchronized (this) {
                com.umeng.commonsdk.statistics.proto.d dVar3 = this.i;
                String str2 = null;
                if (dVar3 == null) {
                    str = null;
                } else {
                    str = dVar3.i();
                }
                if (dVar3 == null) {
                    dVar2 = d(dVar);
                } else {
                    dVar2 = a(dVar3, dVar, hashMap);
                }
                this.i = dVar2;
                if (dVar2 != null) {
                    str2 = dVar2.i();
                }
                z = !a(str, str2);
            }
            if (this.i != null) {
                boolean z2 = AnalyticsConstants.UM_DEBUG;
                if (z) {
                    this.h.a(this.i);
                    if (this.e != null) {
                        this.e.onImprintChanged(this.h);
                    }
                }
            }
            if (hashMap.size() > 0) {
                synchronized (g) {
                    for (Map.Entry entry : hashMap.entrySet()) {
                        String str3 = (String) entry.getKey();
                        String str4 = (String) entry.getValue();
                        if (!TextUtils.isEmpty(str3) && f.containsKey(str3)) {
                            ULog.i("--->>> target imprint key is: " + str3 + "; value is: " + str4);
                            ArrayList arrayList = f.get(str3);
                            if (arrayList != null) {
                                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                    ((UMImprintChangeCallback) arrayList.get(i2)).onImprintValueChanged(str3, str4);
                                }
                            }
                        }
                    }
                }
            }
        } else if (AnalyticsConstants.UM_DEBUG) {
            MLog.e("Imprint is not valid");
        }
    }

    private boolean a(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    private com.umeng.commonsdk.statistics.proto.d a(com.umeng.commonsdk.statistics.proto.d dVar, com.umeng.commonsdk.statistics.proto.d dVar2, Map<String, String> map) {
        ArrayList arrayList;
        UMImprintPreProcessCallback uMImprintPreProcessCallback;
        if (dVar2 == null) {
            return dVar;
        }
        Map<String, e> c2 = dVar.c();
        for (Map.Entry next : dVar2.c().entrySet()) {
            int i2 = 0;
            if (((e) next.getValue()).d()) {
                String str = (String) next.getKey();
                String str2 = ((e) next.getValue()).a;
                synchronized (p) {
                    if (!TextUtils.isEmpty(str) && o.containsKey(str) && (uMImprintPreProcessCallback = o.get(str)) != null && uMImprintPreProcessCallback.onPreProcessImprintKey(str, str2)) {
                        i2 = 1;
                    }
                }
                if (i2 == 0) {
                    c2.put(next.getKey(), next.getValue());
                    synchronized (g) {
                        if (!TextUtils.isEmpty(str) && f.containsKey(str) && f.get(str) != null) {
                            map.put(str, str2);
                        }
                    }
                } else {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> merge: [" + str + "] should be ignored.");
                }
            } else {
                String str3 = (String) next.getKey();
                synchronized (g) {
                    if (!TextUtils.isEmpty(str3) && f.containsKey(str3) && (arrayList = f.get(str3)) != null) {
                        while (i2 < arrayList.size()) {
                            ((UMImprintChangeCallback) arrayList.get(i2)).onImprintValueChanged(str3, (String) null);
                            i2++;
                        }
                    }
                }
                c2.remove(str3);
                this.h.a(str3);
            }
        }
        dVar.a(dVar2.f());
        dVar.a(a(dVar));
        return dVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0057, code lost:
        r7 = o.get(r5);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.umeng.commonsdk.statistics.proto.d d(com.umeng.commonsdk.statistics.proto.d r10) {
        /*
            r9 = this;
            java.util.Map r0 = r10.c()
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.size()
            int r2 = r2 / 2
            r1.<init>(r2)
            java.util.Set r2 = r0.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0017:
            boolean r3 = r2.hasNext()
            r4 = 0
            if (r3 == 0) goto L_0x00a6
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r5 = r3.getValue()
            com.umeng.commonsdk.statistics.proto.e r5 = (com.umeng.commonsdk.statistics.proto.e) r5
            boolean r5 = r5.d()
            if (r5 != 0) goto L_0x0038
            java.lang.Object r3 = r3.getKey()
            r1.add(r3)
            goto L_0x0017
        L_0x0038:
            java.lang.Object r5 = r3.getKey()
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r3 = r3.getValue()
            com.umeng.commonsdk.statistics.proto.e r3 = (com.umeng.commonsdk.statistics.proto.e) r3
            java.lang.String r3 = r3.a
            java.lang.Object r6 = p
            monitor-enter(r6)
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x00a3 }
            if (r7 != 0) goto L_0x0069
            java.util.Map<java.lang.String, com.umeng.commonsdk.statistics.internal.UMImprintPreProcessCallback> r7 = o     // Catch:{ all -> 0x00a3 }
            boolean r7 = r7.containsKey(r5)     // Catch:{ all -> 0x00a3 }
            if (r7 == 0) goto L_0x0069
            java.util.Map<java.lang.String, com.umeng.commonsdk.statistics.internal.UMImprintPreProcessCallback> r7 = o     // Catch:{ all -> 0x00a3 }
            java.lang.Object r7 = r7.get(r5)     // Catch:{ all -> 0x00a3 }
            com.umeng.commonsdk.statistics.internal.UMImprintPreProcessCallback r7 = (com.umeng.commonsdk.statistics.internal.UMImprintPreProcessCallback) r7     // Catch:{ all -> 0x00a3 }
            if (r7 == 0) goto L_0x0069
            boolean r7 = r7.onPreProcessImprintKey(r5, r3)     // Catch:{ all -> 0x00a3 }
            if (r7 == 0) goto L_0x0069
            r7 = 1
            goto L_0x006a
        L_0x0069:
            r7 = 0
        L_0x006a:
            monitor-exit(r6)     // Catch:{ all -> 0x00a3 }
            if (r7 == 0) goto L_0x0070
            r1.add(r5)
        L_0x0070:
            java.lang.Object r7 = g
            monitor-enter(r7)
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x00a0 }
            if (r6 != 0) goto L_0x009d
            java.util.Map<java.lang.String, java.util.ArrayList<com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback>> r6 = f     // Catch:{ all -> 0x00a0 }
            boolean r6 = r6.containsKey(r5)     // Catch:{ all -> 0x00a0 }
            if (r6 == 0) goto L_0x009d
            java.util.Map<java.lang.String, java.util.ArrayList<com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback>> r6 = f     // Catch:{ all -> 0x00a0 }
            java.lang.Object r6 = r6.get(r5)     // Catch:{ all -> 0x00a0 }
            java.util.ArrayList r6 = (java.util.ArrayList) r6     // Catch:{ all -> 0x00a0 }
            if (r6 == 0) goto L_0x009d
        L_0x008b:
            int r8 = r6.size()     // Catch:{ all -> 0x00a0 }
            if (r4 >= r8) goto L_0x009d
            java.lang.Object r8 = r6.get(r4)     // Catch:{ all -> 0x00a0 }
            com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback r8 = (com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback) r8     // Catch:{ all -> 0x00a0 }
            r8.onImprintValueChanged(r5, r3)     // Catch:{ all -> 0x00a0 }
            int r4 = r4 + 1
            goto L_0x008b
        L_0x009d:
            monitor-exit(r7)     // Catch:{ all -> 0x00a0 }
            goto L_0x0017
        L_0x00a0:
            r10 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x00a0 }
            throw r10
        L_0x00a3:
            r10 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x00a3 }
            throw r10
        L_0x00a6:
            java.util.Iterator r1 = r1.iterator()
        L_0x00aa:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00ed
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = g
            monitor-enter(r3)
            boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00ea }
            if (r5 != 0) goto L_0x00e5
            java.util.Map<java.lang.String, java.util.ArrayList<com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback>> r5 = f     // Catch:{ all -> 0x00ea }
            boolean r5 = r5.containsKey(r2)     // Catch:{ all -> 0x00ea }
            if (r5 == 0) goto L_0x00e5
            java.util.Map<java.lang.String, java.util.ArrayList<com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback>> r5 = f     // Catch:{ all -> 0x00ea }
            java.lang.Object r5 = r5.get(r2)     // Catch:{ all -> 0x00ea }
            java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch:{ all -> 0x00ea }
            if (r5 == 0) goto L_0x00e5
            r6 = 0
        L_0x00d2:
            int r7 = r5.size()     // Catch:{ all -> 0x00ea }
            if (r6 >= r7) goto L_0x00e5
            java.lang.Object r7 = r5.get(r6)     // Catch:{ all -> 0x00ea }
            com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback r7 = (com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback) r7     // Catch:{ all -> 0x00ea }
            r8 = 0
            r7.onImprintValueChanged(r2, r8)     // Catch:{ all -> 0x00ea }
            int r6 = r6 + 1
            goto L_0x00d2
        L_0x00e5:
            monitor-exit(r3)     // Catch:{ all -> 0x00ea }
            r0.remove(r2)
            goto L_0x00aa
        L_0x00ea:
            r10 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x00ea }
            throw r10
        L_0x00ed:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.ImprintHandler.d(com.umeng.commonsdk.statistics.proto.d):com.umeng.commonsdk.statistics.proto.d");
    }

    public synchronized com.umeng.commonsdk.statistics.proto.d b() {
        return this.i;
    }

    public a c() {
        return this.h;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: byte[]} */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0038 A[SYNTHETIC, Splitter:B:24:0x0038] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void e() {
        /*
            r5 = this;
            java.io.File r0 = new java.io.File
            android.content.Context r1 = k
            java.io.File r1 = r1.getFilesDir()
            java.lang.String r2 = ".imprint"
            r0.<init>(r1, r2)
            java.lang.Object r1 = b
            monitor-enter(r1)
            boolean r0 = r0.exists()     // Catch:{ all -> 0x005b }
            if (r0 != 0) goto L_0x0018
            monitor-exit(r1)     // Catch:{ all -> 0x005b }
            return
        L_0x0018:
            r0 = 0
            android.content.Context r2 = k     // Catch:{ Exception -> 0x002e, all -> 0x002c }
            java.lang.String r3 = ".imprint"
            java.io.FileInputStream r2 = r2.openFileInput(r3)     // Catch:{ Exception -> 0x002e, all -> 0x002c }
            byte[] r3 = com.umeng.commonsdk.statistics.common.HelperUtils.readStreamToByteArray(r2)     // Catch:{ Exception -> 0x002a }
            com.umeng.commonsdk.statistics.common.HelperUtils.safeClose((java.io.InputStream) r2)     // Catch:{ all -> 0x005b }
            r0 = r3
            goto L_0x0036
        L_0x002a:
            r3 = move-exception
            goto L_0x0030
        L_0x002c:
            r2 = move-exception
            goto L_0x0057
        L_0x002e:
            r3 = move-exception
            r2 = r0
        L_0x0030:
            r3.printStackTrace()     // Catch:{ all -> 0x0053 }
            com.umeng.commonsdk.statistics.common.HelperUtils.safeClose((java.io.InputStream) r2)     // Catch:{ all -> 0x005b }
        L_0x0036:
            if (r0 == 0) goto L_0x0051
            com.umeng.commonsdk.statistics.proto.d r2 = new com.umeng.commonsdk.statistics.proto.d     // Catch:{ Exception -> 0x004d }
            r2.<init>()     // Catch:{ Exception -> 0x004d }
            com.umeng.commonsdk.proguard.m r3 = new com.umeng.commonsdk.proguard.m     // Catch:{ Exception -> 0x004d }
            r3.<init>()     // Catch:{ Exception -> 0x004d }
            r3.a((com.umeng.commonsdk.proguard.j) r2, (byte[]) r0)     // Catch:{ Exception -> 0x004d }
            r5.i = r2     // Catch:{ Exception -> 0x004d }
            com.umeng.commonsdk.statistics.idtracking.ImprintHandler$a r0 = r5.h     // Catch:{ Exception -> 0x004d }
            r0.a((com.umeng.commonsdk.statistics.proto.d) r2)     // Catch:{ Exception -> 0x004d }
            goto L_0x0051
        L_0x004d:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x005b }
        L_0x0051:
            monitor-exit(r1)     // Catch:{ all -> 0x005b }
            return
        L_0x0053:
            r0 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
        L_0x0057:
            com.umeng.commonsdk.statistics.common.HelperUtils.safeClose((java.io.InputStream) r0)     // Catch:{ all -> 0x005b }
            throw r2     // Catch:{ all -> 0x005b }
        L_0x005b:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x005b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.ImprintHandler.e():void");
    }

    private void a(File file) {
        if (this.i != null) {
            try {
                synchronized (b) {
                    byte[] a2 = new s().a(this.i);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(a2);
                        fileOutputStream.flush();
                    } finally {
                        HelperUtils.safeClose((OutputStream) fileOutputStream);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:7|8|9|10) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(k, r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0020 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d() {
        /*
            r4 = this;
            com.umeng.commonsdk.statistics.proto.d r0 = r4.i
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            com.umeng.commonsdk.utils.FileLockUtil r0 = l
            if (r0 == 0) goto L_0x0032
            java.io.File r0 = new java.io.File
            android.content.Context r1 = k
            java.io.File r1 = r1.getFilesDir()
            java.lang.String r2 = ".imprint"
            r0.<init>(r1, r2)
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x002a
            r0.createNewFile()     // Catch:{ IOException -> 0x0020 }
            goto L_0x002a
        L_0x0020:
            r0.createNewFile()     // Catch:{ IOException -> 0x0024 }
            goto L_0x002a
        L_0x0024:
            r1 = move-exception
            android.content.Context r2 = k
            com.umeng.commonsdk.internal.crash.UMCrashManager.reportCrash(r2, r1)
        L_0x002a:
            com.umeng.commonsdk.utils.FileLockUtil r1 = l
            com.umeng.commonsdk.statistics.idtracking.ImprintHandler r2 = j
            r3 = 1
            r1.doFileOperateion((java.io.File) r0, (com.umeng.commonsdk.utils.FileLockCallback) r2, (int) r3)
        L_0x0032:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.ImprintHandler.d():void");
    }

    public static class a {
        private Map<String, String> a = new HashMap();

        a() {
        }

        public synchronized void a(String str) {
            if (this.a != null && this.a.size() > 0 && !TextUtils.isEmpty(str) && this.a.containsKey(str)) {
                this.a.remove(str);
            }
        }

        a(com.umeng.commonsdk.statistics.proto.d dVar) {
            a(dVar);
        }

        public void a(com.umeng.commonsdk.statistics.proto.d dVar) {
            if (dVar != null) {
                b(dVar);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0068, code lost:
            return;
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private synchronized void b(com.umeng.commonsdk.statistics.proto.d r7) {
            /*
                r6 = this;
                monitor-enter(r6)
                if (r7 == 0) goto L_0x0067
                boolean r0 = r7.e()     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                if (r0 != 0) goto L_0x000a
                goto L_0x0067
            L_0x000a:
                java.util.Map r7 = r7.c()     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                java.util.Set r0 = r7.keySet()     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                java.util.Iterator r0 = r0.iterator()     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
            L_0x0016:
                boolean r1 = r0.hasNext()     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                if (r1 == 0) goto L_0x0065
                java.lang.Object r1 = r0.next()     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                java.lang.String r1 = (java.lang.String) r1     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                if (r2 != 0) goto L_0x0016
                java.lang.Object r2 = r7.get(r1)     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                com.umeng.commonsdk.statistics.proto.e r2 = (com.umeng.commonsdk.statistics.proto.e) r2     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                if (r2 == 0) goto L_0x0016
                java.lang.String r2 = r2.b()     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                if (r3 != 0) goto L_0x0016
                java.util.Map<java.lang.String, java.lang.String> r3 = r6.a     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                r3.put(r1, r2)     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                boolean r3 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                if (r3 == 0) goto L_0x0016
                java.lang.String r3 = "ImprintHandler"
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                r4.<init>()     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                java.lang.String r5 = "imKey is "
                r4.append(r5)     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                r4.append(r1)     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                java.lang.String r1 = ", imValue is "
                r4.append(r1)     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                r4.append(r2)     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                java.lang.String r1 = r4.toString()     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                android.util.Log.i(r3, r1)     // Catch:{ Throwable -> 0x0065, all -> 0x0062 }
                goto L_0x0016
            L_0x0062:
                r7 = move-exception
                monitor-exit(r6)
                throw r7
            L_0x0065:
                monitor-exit(r6)
                return
            L_0x0067:
                monitor-exit(r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.ImprintHandler.a.b(com.umeng.commonsdk.statistics.proto.d):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
            return r3;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized java.lang.String a(java.lang.String r2, java.lang.String r3) {
            /*
                r1 = this;
                monitor-enter(r1)
                boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0024 }
                if (r0 != 0) goto L_0x0022
                java.util.Map<java.lang.String, java.lang.String> r0 = r1.a     // Catch:{ all -> 0x0024 }
                int r0 = r0.size()     // Catch:{ all -> 0x0024 }
                if (r0 > 0) goto L_0x0010
                goto L_0x0022
            L_0x0010:
                java.util.Map<java.lang.String, java.lang.String> r0 = r1.a     // Catch:{ all -> 0x0024 }
                java.lang.Object r2 = r0.get(r2)     // Catch:{ all -> 0x0024 }
                java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0024 }
                boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0024 }
                if (r0 != 0) goto L_0x0020
                monitor-exit(r1)
                return r2
            L_0x0020:
                monitor-exit(r1)
                return r3
            L_0x0022:
                monitor-exit(r1)
                return r3
            L_0x0024:
                r2 = move-exception
                monitor-exit(r1)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.idtracking.ImprintHandler.a.a(java.lang.String, java.lang.String):java.lang.String");
        }
    }
}
