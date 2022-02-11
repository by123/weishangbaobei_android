package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.tencent.bugly.CrashModule;
import com.tencent.bugly.crashreport.common.info.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class n {
    public static final long a = System.currentTimeMillis();
    private static n b;
    private Context c;
    /* access modifiers changed from: private */
    public String d = a.b().d;
    /* access modifiers changed from: private */
    public Map<Integer, Map<String, m>> e = new HashMap();
    /* access modifiers changed from: private */
    public SharedPreferences f;

    private n(Context context) {
        this.c = context;
        this.f = context.getSharedPreferences("crashrecord", 0);
    }

    public static n a() {
        n nVar;
        synchronized (n.class) {
            try {
                nVar = b;
            } catch (Throwable th) {
                Class<n> cls = n.class;
                throw th;
            }
        }
        return nVar;
    }

    public static n a(Context context) {
        n nVar;
        synchronized (n.class) {
            try {
                if (b == null) {
                    b = new n(context);
                }
                nVar = b;
            } catch (Throwable th) {
                Class<n> cls = n.class;
                throw th;
            }
        }
        return nVar;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0036 A[SYNTHETIC, Splitter:B:16:0x0036] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0056 A[SYNTHETIC, Splitter:B:31:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x005a A[DONT_GENERATE] */
    public <T extends List<?>> void a(int i, T t) {
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2 = null;
        synchronized (this) {
            if (t != null) {
                try {
                    File dir = this.c.getDir("crashrecord", 0);
                    StringBuilder sb = new StringBuilder();
                    sb.append(i);
                    try {
                        objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(dir, sb.toString())));
                        try {
                            objectOutputStream.writeObject(t);
                            objectOutputStream.close();
                        } catch (IOException e2) {
                            e = e2;
                            try {
                                e.printStackTrace();
                                x.a("open record file error", new Object[0]);
                                if (objectOutputStream == null) {
                                    objectOutputStream.close();
                                    return;
                                }
                                return;
                            } catch (Throwable th) {
                                th = th;
                                objectOutputStream2 = objectOutputStream;
                                objectOutputStream = objectOutputStream2;
                                if (objectOutputStream != null) {
                                    objectOutputStream.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (objectOutputStream != null) {
                            }
                            throw th;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        objectOutputStream = null;
                        e.printStackTrace();
                        x.a("open record file error", new Object[0]);
                        if (objectOutputStream == null) {
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        objectOutputStream = objectOutputStream2;
                        if (objectOutputStream != null) {
                        }
                        throw th;
                    }
                } catch (Exception e4) {
                    x.e("writeCrashRecord error", new Object[0]);
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0085, code lost:
        return true;
     */
    public boolean b(int i) {
        synchronized (this) {
            try {
                List<m> c2 = c(i);
                if (c2 == null) {
                    return false;
                }
                long currentTimeMillis = System.currentTimeMillis();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (m mVar : c2) {
                    if (mVar.b != null && mVar.b.equalsIgnoreCase(this.d) && mVar.d > 0) {
                        arrayList.add(mVar);
                    }
                    if (mVar.c + 86400000 < currentTimeMillis) {
                        arrayList2.add(mVar);
                    }
                }
                Collections.sort(arrayList);
                if (arrayList.size() < 2) {
                    c2.removeAll(arrayList2);
                    a(i, c2);
                    return false;
                } else if (arrayList.size() > 0 && ((m) arrayList.get(arrayList.size() - 1)).c + 86400000 < currentTimeMillis) {
                    c2.clear();
                    a(i, c2);
                    return false;
                }
            } catch (Exception e2) {
                x.e("isFrequentCrash failed", new Object[0]);
                return false;
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x006b, code lost:
        if (r0 != null) goto L_0x005a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003e A[SYNTHETIC, Splitter:B:17:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0059  */
    public <T extends List<?>> T c(int i) {
        ObjectInputStream objectInputStream;
        Throwable th;
        Throwable th2;
        ObjectInputStream objectInputStream2;
        ObjectInputStream objectInputStream3;
        synchronized (this) {
            try {
                File dir = this.c.getDir("crashrecord", 0);
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                File file = new File(dir, sb.toString());
                if (!file.exists()) {
                    return null;
                }
                try {
                    objectInputStream3 = new ObjectInputStream(new FileInputStream(file));
                    try {
                        T t = (List) objectInputStream3.readObject();
                        objectInputStream3.close();
                        return t;
                    } catch (IOException e2) {
                        objectInputStream2 = objectInputStream3;
                        try {
                            x.a("open record file error", new Object[0]);
                        } catch (Throwable th3) {
                            th = th3;
                            objectInputStream = objectInputStream2;
                            th2 = th;
                            if (objectInputStream != null) {
                            }
                            throw th2;
                        }
                    } catch (ClassNotFoundException e3) {
                        try {
                            x.a("get object error", new Object[0]);
                            if (objectInputStream3 != null) {
                                objectInputStream2 = objectInputStream3;
                                objectInputStream2.close();
                            }
                            return null;
                        } catch (Throwable th4) {
                            th = th4;
                            objectInputStream = objectInputStream3;
                            th2 = th;
                            if (objectInputStream != null) {
                            }
                            throw th2;
                        }
                    }
                } catch (IOException e4) {
                    objectInputStream2 = null;
                    x.a("open record file error", new Object[0]);
                } catch (ClassNotFoundException e5) {
                    objectInputStream3 = null;
                    x.a("get object error", new Object[0]);
                    if (objectInputStream3 != null) {
                    }
                    return null;
                } catch (Throwable th5) {
                    th2 = th5;
                    objectInputStream = null;
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    throw th2;
                }
            } catch (Exception e6) {
                x.e("readCrashRecord error", new Object[0]);
            }
        }
    }

    public final void a(int i, final int i2) {
        w.a().a(new Runnable(CrashModule.MODULE_ID) {
            public final void run() {
                m mVar;
                try {
                    if (!TextUtils.isEmpty(n.this.d)) {
                        List a2 = n.this.c(CrashModule.MODULE_ID);
                        List<m> arrayList = a2 == null ? new ArrayList<>() : a2;
                        if (n.this.e.get(Integer.valueOf(CrashModule.MODULE_ID)) == null) {
                            n.this.e.put(Integer.valueOf(CrashModule.MODULE_ID), new HashMap());
                        }
                        if (((Map) n.this.e.get(Integer.valueOf(CrashModule.MODULE_ID))).get(n.this.d) == null) {
                            m mVar2 = new m();
                            mVar2.a = (long) CrashModule.MODULE_ID;
                            mVar2.g = n.a;
                            mVar2.b = n.this.d;
                            mVar2.f = a.b().k;
                            mVar2.e = a.b().f;
                            mVar2.c = System.currentTimeMillis();
                            mVar2.d = i2;
                            ((Map) n.this.e.get(Integer.valueOf(CrashModule.MODULE_ID))).put(n.this.d, mVar2);
                            mVar = mVar2;
                        } else {
                            m mVar3 = (m) ((Map) n.this.e.get(Integer.valueOf(CrashModule.MODULE_ID))).get(n.this.d);
                            mVar3.d = i2;
                            mVar = mVar3;
                        }
                        ArrayList arrayList2 = new ArrayList();
                        boolean z = false;
                        for (m mVar4 : arrayList) {
                            if (mVar4.g == mVar.g && mVar4.b != null && mVar4.b.equalsIgnoreCase(mVar.b)) {
                                z = true;
                                mVar4.d = mVar.d;
                            }
                            if ((mVar4.e != null && !mVar4.e.equalsIgnoreCase(mVar.e)) || ((mVar4.f != null && !mVar4.f.equalsIgnoreCase(mVar.f)) || mVar4.d <= 0)) {
                                arrayList2.add(mVar4);
                            }
                        }
                        arrayList.removeAll(arrayList2);
                        if (!z) {
                            arrayList.add(mVar);
                        }
                        n.this.a(CrashModule.MODULE_ID, arrayList);
                    }
                } catch (Exception e) {
                    x.e("saveCrashRecord failed", new Object[0]);
                }
            }
        });
    }

    public final boolean a(final int i) {
        boolean z = true;
        synchronized (this) {
            try {
                SharedPreferences sharedPreferences = this.f;
                z = sharedPreferences.getBoolean(i + "_" + this.d, true);
                try {
                    w.a().a(new Runnable() {
                        public final void run() {
                            boolean b2 = n.this.b(i);
                            SharedPreferences.Editor edit = n.this.f.edit();
                            edit.putBoolean(i + "_" + n.this.d, !b2).commit();
                        }
                    });
                } catch (Exception e2) {
                    x.e("canInit error", new Object[0]);
                    return z;
                }
            } catch (Exception e3) {
            }
        }
        return z;
    }
}
