package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.stub.StubApp;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.j;
import com.umeng.commonsdk.proguard.m;
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

    public static class a {
        private Map<String, String> a = new HashMap();

        a() {
        }

        a(com.umeng.commonsdk.statistics.proto.d dVar) {
            a(dVar);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
            return;
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        private void b(com.umeng.commonsdk.statistics.proto.d dVar) {
            e eVar;
            synchronized (this) {
                if (dVar != null) {
                    if (dVar.e()) {
                        Map<String, e> c = dVar.c();
                        for (String next : c.keySet()) {
                            if (!TextUtils.isEmpty(next) && (eVar = c.get(next)) != null) {
                                String b = eVar.b();
                                if (!TextUtils.isEmpty(b)) {
                                    this.a.put(next, b);
                                    if (AnalyticsConstants.UM_DEBUG) {
                                        Log.i(ImprintHandler.a, "imKey is " + next + ", imValue is " + b);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            return r4;
         */
        public String a(String str, String str2) {
            synchronized (this) {
                if (!TextUtils.isEmpty(str) && this.a.size() > 0) {
                    String str3 = this.a.get(str);
                    return !TextUtils.isEmpty(str3) ? str3 : str2;
                }
            }
        }

        public void a(com.umeng.commonsdk.statistics.proto.d dVar) {
            if (dVar != null) {
                b(dVar);
            }
        }

        public void a(String str) {
            synchronized (this) {
                if (this.a != null && this.a.size() > 0 && !TextUtils.isEmpty(str) && this.a.containsKey(str)) {
                    this.a.remove(str);
                }
            }
        }
    }

    private ImprintHandler(Context context) {
        k = StubApp.getOrigApplicationContext(context.getApplicationContext());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004b, code lost:
        r2 = o.get(r1);
     */
    private com.umeng.commonsdk.statistics.proto.d a(com.umeng.commonsdk.statistics.proto.d dVar, com.umeng.commonsdk.statistics.proto.d dVar2, Map<String, String> map) {
        ArrayList arrayList;
        boolean z;
        UMImprintPreProcessCallback uMImprintPreProcessCallback;
        if (dVar2 != null) {
            Map<String, e> c2 = dVar.c();
            for (Map.Entry next : dVar2.c().entrySet()) {
                if (((e) next.getValue()).d()) {
                    String str = (String) next.getKey();
                    String str2 = ((e) next.getValue()).a;
                    synchronized (p) {
                        z = !TextUtils.isEmpty(str) && o.containsKey(str) && uMImprintPreProcessCallback != null && uMImprintPreProcessCallback.onPreProcessImprintKey(str, str2);
                    }
                    if (!z) {
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
                            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                ((UMImprintChangeCallback) arrayList.get(i2)).onImprintValueChanged(str3, (String) null);
                            }
                        }
                    }
                    c2.remove(str3);
                    this.h.a(str3);
                }
            }
            dVar.a(dVar2.f());
            dVar.a(a(dVar));
        }
        return dVar;
    }

    private void a(File file) {
        FileOutputStream fileOutputStream;
        if (this.i != null) {
            try {
                synchronized (b) {
                    byte[] a2 = new s().a(this.i);
                    fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(a2);
                    fileOutputStream.flush();
                    HelperUtils.safeClose((OutputStream) fileOutputStream);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            } catch (Throwable th) {
                HelperUtils.safeClose((OutputStream) fileOutputStream);
                throw th;
            }
        }
    }

    private static void a(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        synchronized (g) {
            try {
                if (f.containsKey(str)) {
                    ArrayList arrayList = f.get(str);
                    int size = arrayList.size();
                    ULog.i("--->>> addCallback: before add: callbacks size is: " + size);
                    for (int i2 = 0; i2 < size; i2++) {
                        if (uMImprintChangeCallback == arrayList.get(i2)) {
                            ULog.i("--->>> addCallback: callback has exist, just exit");
                            return;
                        }
                    }
                    arrayList.add(uMImprintChangeCallback);
                    ULog.i("--->>> addCallback: after add: callbacks size is: " + arrayList.size());
                    return;
                }
                ArrayList arrayList2 = new ArrayList();
                int size2 = arrayList2.size();
                ULog.i("--->>> addCallback: before add: callbacks size is: " + size2);
                for (int i3 = 0; i3 < size2; i3++) {
                    if (uMImprintChangeCallback == arrayList2.get(i3)) {
                        ULog.i("--->>> addCallback: callback has exist, just exit");
                        return;
                    }
                }
                arrayList2.add(uMImprintChangeCallback);
                ULog.i("--->>> addCallback: after add: callbacks size is: " + arrayList2.size());
                f.put(str, arrayList2);
                return;
            } catch (Throwable th) {
                UMCrashManager.reportCrash(k, th);
            }
        }
    }

    private boolean a(String str, String str2) {
        return str == null ? str2 == null : str.equals(str2);
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
            return;
        }
        return;
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

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0057, code lost:
        r1 = o.get(r2);
     */
    private com.umeng.commonsdk.statistics.proto.d d(com.umeng.commonsdk.statistics.proto.d dVar) {
        ArrayList arrayList;
        boolean z;
        ArrayList arrayList2;
        UMImprintPreProcessCallback uMImprintPreProcessCallback;
        Map<String, e> c2 = dVar.c();
        ArrayList<String> arrayList3 = new ArrayList<>(c2.size() / 2);
        for (Map.Entry next : c2.entrySet()) {
            if (!((e) next.getValue()).d()) {
                arrayList3.add(next.getKey());
            } else {
                String str = (String) next.getKey();
                String str2 = ((e) next.getValue()).a;
                synchronized (p) {
                    z = !TextUtils.isEmpty(str) && o.containsKey(str) && uMImprintPreProcessCallback != null && uMImprintPreProcessCallback.onPreProcessImprintKey(str, str2);
                }
                if (z) {
                    arrayList3.add(str);
                }
                synchronized (g) {
                    if (!TextUtils.isEmpty(str) && f.containsKey(str) && (arrayList2 = f.get(str)) != null) {
                        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                            ((UMImprintChangeCallback) arrayList2.get(i2)).onImprintValueChanged(str, str2);
                        }
                    }
                }
            }
        }
        for (String str3 : arrayList3) {
            synchronized (g) {
                if (!TextUtils.isEmpty(str3) && f.containsKey(str3) && (arrayList = f.get(str3)) != null) {
                    for (int i3 = 0; i3 < arrayList.size(); i3++) {
                        ((UMImprintChangeCallback) arrayList.get(i3)).onImprintValueChanged(str3, (String) null);
                    }
                }
            }
            c2.remove(str3);
        }
        return dVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: byte[]} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0051, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0052, code lost:
        r2 = r1;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002a A[SYNTHETIC, Splitter:B:13:0x002a] */
    private void e() {
        FileInputStream fileInputStream;
        ? r2 = 0;
        File file = new File(k.getFilesDir(), c);
        synchronized (b) {
            if (file.exists()) {
                try {
                    fileInputStream = k.openFileInput(c);
                    try {
                        byte[] readStreamToByteArray = HelperUtils.readStreamToByteArray(fileInputStream);
                        HelperUtils.safeClose((InputStream) fileInputStream);
                        r2 = readStreamToByteArray;
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    HelperUtils.safeClose((InputStream) r2);
                    throw th;
                }
                if (r2 != 0) {
                    try {
                        com.umeng.commonsdk.statistics.proto.d dVar = new com.umeng.commonsdk.statistics.proto.d();
                        new m().a((j) dVar, r2);
                        this.i = dVar;
                        this.h.a(dVar);
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
            }
            return;
        }
        e.printStackTrace();
        HelperUtils.safeClose((InputStream) fileInputStream);
        if (r2 != 0) {
        }
    }

    public static ImprintHandler getImprintService(Context context) {
        ImprintHandler imprintHandler;
        synchronized (ImprintHandler.class) {
            try {
                if (j == null) {
                    j = new ImprintHandler(context);
                    l = new FileLockUtil();
                    if (l != null) {
                        l.doFileOperateion(new File(k.getFilesDir(), c), (FileLockCallback) j, 0);
                    }
                }
                imprintHandler = j;
            } catch (Throwable th) {
                Class<ImprintHandler> cls = ImprintHandler.class;
                throw th;
            }
        }
        return imprintHandler;
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

    public void a(d dVar) {
        this.e = dVar;
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

    public com.umeng.commonsdk.statistics.proto.d b() {
        com.umeng.commonsdk.statistics.proto.d dVar;
        synchronized (this) {
            dVar = this.i;
        }
        return dVar;
    }

    public void b(com.umeng.commonsdk.statistics.proto.d dVar) {
        boolean a2;
        String str = null;
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
                com.umeng.commonsdk.statistics.proto.d dVar2 = this.i;
                String i2 = dVar2 == null ? null : dVar2.i();
                com.umeng.commonsdk.statistics.proto.d d2 = dVar2 == null ? d(dVar) : a(dVar2, dVar, hashMap);
                this.i = d2;
                if (d2 != null) {
                    str = d2.i();
                }
                a2 = a(i2, str);
            }
            if (this.i != null) {
                boolean z = AnalyticsConstants.UM_DEBUG;
                if (!a2) {
                    this.h.a(this.i);
                    if (this.e != null) {
                        this.e.onImprintChanged(this.h);
                    }
                }
            }
            if (hashMap.size() > 0) {
                synchronized (g) {
                    for (Map.Entry entry : hashMap.entrySet()) {
                        String str2 = (String) entry.getKey();
                        String str3 = (String) entry.getValue();
                        if (!TextUtils.isEmpty(str2) && f.containsKey(str2)) {
                            ULog.i("--->>> target imprint key is: " + str2 + "; value is: " + str3);
                            ArrayList arrayList = f.get(str2);
                            if (arrayList != null) {
                                int i3 = 0;
                                while (true) {
                                    int i4 = i3;
                                    if (i4 >= arrayList.size()) {
                                        break;
                                    }
                                    ((UMImprintChangeCallback) arrayList.get(i4)).onImprintValueChanged(str2, str3);
                                    i3 = i4 + 1;
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

    public a c() {
        return this.h;
    }

    public void d() {
        if (this.i != null && l != null) {
            File file = new File(k.getFilesDir(), c);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e2) {
                    try {
                        file.createNewFile();
                    } catch (IOException e3) {
                        UMCrashManager.reportCrash(k, e3);
                    }
                }
            }
            l.doFileOperateion(file, (FileLockCallback) j, 1);
        }
    }

    public boolean onFileLock(File file, int i2) {
        if (i2 == 0) {
            j.e();
        } else if (i2 == 1) {
            j.a(file);
        }
        return true;
    }

    public boolean onFileLock(String str) {
        return false;
    }

    public boolean onFileLock(String str, Object obj) {
        return false;
    }

    public void registImprintCallback(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintChangeCallback != null) {
            a(str, uMImprintChangeCallback);
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

    public void unregistImprintCallback(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (!TextUtils.isEmpty(str) && uMImprintChangeCallback != null) {
            b(str, uMImprintChangeCallback);
        }
    }
}
