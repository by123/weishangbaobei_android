package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class p {
    private static p a;
    private static q b;
    private static boolean c;

    final class a extends Thread {
        private int a;
        private o b;
        private String c;
        private ContentValues d;
        private boolean e;
        private String[] f;
        private String g;
        private String[] h;
        private String i;
        private String j;
        private String k;
        private String l;
        private String m;
        private String[] n;
        private int o;
        private String p;
        private byte[] q;

        public a(int i2, o oVar) {
            this.a = i2;
            this.b = oVar;
        }

        public final void a(int i2, String str, byte[] bArr) {
            this.o = i2;
            this.p = str;
            this.q = bArr;
        }

        public final void a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
            this.e = z;
            this.c = str;
            this.f = strArr;
            this.g = str2;
            this.h = strArr2;
            this.i = str3;
            this.j = str4;
            this.k = str5;
            this.l = str6;
        }

        public final void run() {
            switch (this.a) {
                case 1:
                    long unused = p.this.a(this.c, this.d, this.b);
                    return;
                case 2:
                    int unused2 = p.this.a(this.c, this.m, this.n, this.b);
                    return;
                case 3:
                    Cursor a2 = p.this.a(this.e, this.c, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.b);
                    if (a2 != null) {
                        a2.close();
                        return;
                    }
                    return;
                case 4:
                    boolean unused3 = p.this.a(this.o, this.p, this.q, this.b);
                    return;
                case 5:
                    Map unused4 = p.this.a(this.o, this.b);
                    return;
                case 6:
                    boolean unused5 = p.this.a(this.o, this.p, this.b);
                    return;
                default:
                    return;
            }
        }
    }

    private p(Context context, List<com.tencent.bugly.a> list) {
        b = new q(context, list);
    }

    /* access modifiers changed from: private */
    public int a(String str, String str2, String[] strArr, o oVar) {
        int i = 0;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase != null) {
                    i = writableDatabase.delete(str, str2, strArr);
                }
                if (oVar != null) {
                }
            } catch (Throwable th) {
                if (oVar != null) {
                }
                throw th;
            }
        }
        return i;
    }

    /* access modifiers changed from: private */
    public long a(String str, ContentValues contentValues, o oVar) {
        long j = 0;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (!(writableDatabase == null || contentValues == null)) {
                    long replace = writableDatabase.replace(str, "_id", contentValues);
                    if (replace >= 0) {
                        x.c("[Database] insert %s success.", str);
                        j = replace;
                    } else {
                        x.d("[Database] replace %s error.", str);
                        j = replace;
                    }
                }
                if (oVar != null) {
                }
            } catch (Throwable th) {
                if (oVar != null) {
                }
                throw th;
            }
        }
        return j;
    }

    /* access modifiers changed from: private */
    public Cursor a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, o oVar) {
        Cursor cursor;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase != null) {
                    cursor = writableDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
                }
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                    cursor = null;
                }
            }
            cursor = null;
        }
        return cursor;
    }

    public static p a() {
        p pVar;
        synchronized (p.class) {
            try {
                pVar = a;
            } catch (Throwable th) {
                Class<p> cls = p.class;
                throw th;
            }
        }
        return pVar;
    }

    public static p a(Context context, List<com.tencent.bugly.a> list) {
        p pVar;
        synchronized (p.class) {
            try {
                if (a == null) {
                    a = new p(context, list);
                }
                pVar = a;
            } catch (Throwable th) {
                Class<p> cls = p.class;
                throw th;
            }
        }
        return pVar;
    }

    private static r a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            r rVar = new r();
            rVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            rVar.b = cursor.getInt(cursor.getColumnIndex("_tp"));
            rVar.c = cursor.getString(cursor.getColumnIndex("_pc"));
            rVar.d = cursor.getString(cursor.getColumnIndex("_th"));
            rVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            rVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return rVar;
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    public Map<String, byte[]> a(int i, o oVar) {
        HashMap hashMap;
        try {
            List<r> c2 = c(i);
            if (c2 == null) {
                return null;
            }
            hashMap = new HashMap();
            try {
                for (r next : c2) {
                    byte[] bArr = next.g;
                    if (bArr != null) {
                        hashMap.put(next.f, bArr);
                    }
                }
                return hashMap;
            } catch (Throwable th) {
                th = th;
                if (!x.a(th)) {
                }
            }
        } catch (Throwable th2) {
            th = th2;
            hashMap = null;
            if (!x.a(th)) {
                return hashMap;
            }
            th.printStackTrace();
            return hashMap;
        }
    }

    /* access modifiers changed from: private */
    public boolean a(int i, String str, o oVar) {
        String str2;
        boolean z = false;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase != null) {
                    if (z.a(str)) {
                        str2 = "_id = " + i;
                    } else {
                        str2 = "_id = " + i + " and _tp" + " = \"" + str + "\"";
                    }
                    int delete = writableDatabase.delete("t_pf", str2, (String[]) null);
                    x.c("[Database] deleted %s data %d", "t_pf", Integer.valueOf(delete));
                    if (delete > 0) {
                        z = true;
                    }
                }
                if (oVar != null) {
                }
            } catch (Throwable th) {
                if (oVar != null) {
                }
                throw th;
            }
        }
        return z;
    }

    /* access modifiers changed from: private */
    public boolean a(int i, String str, byte[] bArr, o oVar) {
        try {
            r rVar = new r();
            rVar.a = (long) i;
            rVar.f = str;
            rVar.e = System.currentTimeMillis();
            rVar.g = bArr;
            boolean b2 = b(rVar);
            if (oVar != null) {
            }
            return b2;
        } catch (Throwable th) {
            if (oVar != null) {
            }
            throw th;
        }
    }

    private static r b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            r rVar = new r();
            rVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            rVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            rVar.f = cursor.getString(cursor.getColumnIndex("_tp"));
            rVar.g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return rVar;
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return false;
     */
    private boolean b(r rVar) {
        ContentValues d;
        synchronized (this) {
            if (rVar == null) {
                return false;
            }
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase != null && (d = d(rVar)) != null) {
                    long replace = writableDatabase.replace("t_pf", "_id", d);
                    if (replace < 0) {
                        return false;
                    }
                    x.c("[Database] insert %s success.", "t_pf");
                    rVar.a = replace;
                    return true;
                }
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
    }

    private static ContentValues c(r rVar) {
        if (rVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (rVar.a > 0) {
                contentValues.put("_id", Long.valueOf(rVar.a));
            }
            contentValues.put("_tp", Integer.valueOf(rVar.b));
            contentValues.put("_pc", rVar.c);
            contentValues.put("_th", rVar.d);
            contentValues.put("_tm", Long.valueOf(rVar.e));
            if (rVar.g != null) {
                contentValues.put("_dt", rVar.g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        return r2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x007d A[SYNTHETIC, Splitter:B:37:0x007d] */
    private List<r> c(int i) {
        Cursor cursor;
        Cursor cursor2 = null;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase != null) {
                    String str = "_id = " + i;
                    cursor = writableDatabase.query("t_pf", (String[]) null, str, (String[]) null, (String) null, (String) null, (String) null);
                    if (cursor != null) {
                        try {
                            StringBuilder sb = new StringBuilder();
                            ArrayList arrayList = new ArrayList();
                            while (cursor.moveToNext()) {
                                r b2 = b(cursor);
                                if (b2 != null) {
                                    arrayList.add(b2);
                                } else {
                                    String string = cursor.getString(cursor.getColumnIndex("_tp"));
                                    sb.append(" or _tp");
                                    sb.append(" = ");
                                    sb.append(string);
                                }
                            }
                            if (sb.length() > 0) {
                                sb.append(" and _id");
                                sb.append(" = ");
                                sb.append(i);
                                x.d("[Database] deleted %s illegal data %d.", "t_pf", Integer.valueOf(writableDatabase.delete("t_pf", str.substring(4), (String[]) null)));
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                        }
                    } else if (cursor != null) {
                        cursor.close();
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (cursor2 != null) {
                }
                throw th;
            }
        }
    }

    private static ContentValues d(r rVar) {
        if (rVar == null || z.a(rVar.f)) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (rVar.a > 0) {
                contentValues.put("_id", Long.valueOf(rVar.a));
            }
            contentValues.put("_tp", rVar.f);
            contentValues.put("_tm", Long.valueOf(rVar.e));
            if (rVar.g != null) {
                contentValues.put("_dt", rVar.g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public final int a(String str, String str2, String[] strArr, o oVar, boolean z) {
        return a(str, str2, (String[]) null, (o) null);
    }

    public final long a(String str, ContentValues contentValues, o oVar, boolean z) {
        return a(str, contentValues, (o) null);
    }

    public final Cursor a(String str, String[] strArr, String str2, String[] strArr2, o oVar, boolean z) {
        return a(false, str, strArr, str2, (String[]) null, (String) null, (String) null, (String) null, (String) null, (o) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        return r2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007f A[SYNTHETIC, Splitter:B:40:0x007f] */
    public final List<r> a(int i) {
        String str;
        Cursor cursor;
        Cursor cursor2 = null;
        synchronized (this) {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase != null) {
                if (i >= 0) {
                    try {
                        str = "_tp = " + i;
                    } catch (Throwable th) {
                        th = th;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                } else {
                    str = null;
                }
                cursor = writableDatabase.query("t_lr", (String[]) null, str, (String[]) null, (String) null, (String) null, (String) null);
                if (cursor != null) {
                    try {
                        StringBuilder sb = new StringBuilder();
                        ArrayList arrayList = new ArrayList();
                        while (cursor.moveToNext()) {
                            r a2 = a(cursor);
                            if (a2 != null) {
                                arrayList.add(a2);
                            } else {
                                long j = cursor.getLong(cursor.getColumnIndex("_id"));
                                sb.append(" or _id");
                                sb.append(" = ");
                                sb.append(j);
                            }
                        }
                        String sb2 = sb.toString();
                        if (sb2.length() > 0) {
                            x.d("[Database] deleted %s illegal data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", sb2.substring(4), (String[]) null)));
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } else if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }

    public final Map<String, byte[]> a(int i, o oVar, boolean z) {
        return a(i, (o) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    public final void a(List<r> list) {
        synchronized (this) {
            if (list != null) {
                if (list.size() != 0) {
                    SQLiteDatabase writableDatabase = b.getWritableDatabase();
                    if (writableDatabase != null) {
                        StringBuilder sb = new StringBuilder();
                        for (r rVar : list) {
                            sb.append(" or _id");
                            sb.append(" = ");
                            sb.append(rVar.a);
                        }
                        String sb2 = sb.toString();
                        if (sb2.length() > 0) {
                            sb2 = sb2.substring(4);
                        }
                        sb.setLength(0);
                        try {
                            x.c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", sb2, (String[]) null)));
                        } catch (Throwable th) {
                            if (!x.a(th)) {
                                th.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public final boolean a(int i, String str, o oVar, boolean z) {
        return a(555, str, (o) null);
    }

    public final boolean a(int i, String str, byte[] bArr, o oVar, boolean z) {
        if (z) {
            return a(i, str, bArr, (o) null);
        }
        a aVar = new a(4, (o) null);
        aVar.a(i, str, bArr);
        w.a().a(aVar);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return false;
     */
    public final boolean a(r rVar) {
        ContentValues c2;
        synchronized (this) {
            if (rVar == null) {
                return false;
            }
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase != null && (c2 = c(rVar)) != null) {
                    long replace = writableDatabase.replace("t_lr", "_id", c2);
                    if (replace < 0) {
                        return false;
                    }
                    x.c("[Database] insert %s success.", "t_lr");
                    rVar.a = replace;
                    return true;
                }
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
    }

    public final void b(int i) {
        String str = null;
        synchronized (this) {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase != null) {
                if (i >= 0) {
                    try {
                        str = "_tp = " + i;
                    } catch (Throwable th) {
                        if (!x.a(th)) {
                            th.printStackTrace();
                        }
                        return;
                    }
                }
                x.c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", str, (String[]) null)));
            }
        }
    }
}
