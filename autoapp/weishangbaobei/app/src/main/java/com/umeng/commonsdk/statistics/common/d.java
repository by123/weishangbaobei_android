package com.umeng.commonsdk.statistics.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;

/* compiled from: StoreHelper */
public final class d {
    private static d a = null;
    private static Context b = null;
    private static String c = null;
    private static final String e = "mobclick_agent_user_";
    private static final String f = "mobclick_agent_header_";
    private static final String g = "mobclick_agent_cached_";
    private a d;

    /* compiled from: StoreHelper */
    public interface b {
        void a(File file);

        boolean b(File file);

        void c(File file);
    }

    public d(Context context) {
        this.d = new a(context);
    }

    public static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            b = StubApp.getOrigApplicationContext(context.getApplicationContext());
            c = context.getPackageName();
            if (a == null) {
                a = new d(context);
            }
            dVar = a;
        }
        return dVar;
    }

    public void a(int i) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt("vt", i).commit();
        }
    }

    public int a() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(b);
        if (sharedPreferences != null) {
            return sharedPreferences.getInt("vt", 0);
        }
        return 0;
    }

    public String b() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(b);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("st", (String) null);
        }
        return null;
    }

    public void a(String str) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("st", str).commit();
        }
    }

    public boolean c() {
        return UMFrUtils.envelopeFileNumber(b) > 0;
    }

    /* compiled from: StoreHelper */
    public static class a {
        private final int a;
        private File b;
        private FilenameFilter c;

        public a(Context context) {
            this(context, ".um");
        }

        public a(Context context, String str) {
            this.a = 10;
            this.c = new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return str.startsWith("um");
                }
            };
            this.b = new File(context.getFilesDir(), str);
            if (!this.b.exists() || !this.b.isDirectory()) {
                this.b.mkdir();
            }
        }

        public boolean a() {
            File[] listFiles = this.b.listFiles();
            return listFiles != null && listFiles.length > 0;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
            r3 = r0[r1];
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(com.umeng.commonsdk.statistics.common.d.b r6) {
            /*
                r5 = this;
                java.io.File r0 = r5.b
                java.io.FilenameFilter r1 = r5.c
                java.io.File[] r0 = r0.listFiles(r1)
                r1 = 0
                if (r0 == 0) goto L_0x0020
                int r2 = r0.length
                r3 = 10
                if (r2 < r3) goto L_0x0020
                java.util.Arrays.sort(r0)
                int r2 = r0.length
                int r2 = r2 - r3
                r3 = 0
            L_0x0016:
                if (r3 >= r2) goto L_0x0020
                r4 = r0[r3]
                r4.delete()
                int r3 = r3 + 1
                goto L_0x0016
            L_0x0020:
                if (r0 == 0) goto L_0x0047
                int r2 = r0.length
                if (r2 <= 0) goto L_0x0047
                java.io.File r2 = r5.b
                r6.a(r2)
                int r2 = r0.length
            L_0x002b:
                if (r1 >= r2) goto L_0x0042
                r3 = r0[r1]     // Catch:{ Throwable -> 0x003a, all -> 0x0038 }
                boolean r3 = r6.b(r3)     // Catch:{ Throwable -> 0x003a, all -> 0x0038 }
                if (r3 == 0) goto L_0x003f
                r3 = r0[r1]
                goto L_0x003c
            L_0x0038:
                r6 = move-exception
                throw r6
            L_0x003a:
                r3 = r0[r1]
            L_0x003c:
                r3.delete()
            L_0x003f:
                int r1 = r1 + 1
                goto L_0x002b
            L_0x0042:
                java.io.File r0 = r5.b
                r6.c(r0)
            L_0x0047:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.common.d.a.a(com.umeng.commonsdk.statistics.common.d$b):void");
        }

        public void a(byte[] bArr) {
            if (bArr != null && bArr.length != 0) {
                try {
                    HelperUtils.writeFile(new File(this.b, String.format(Locale.US, "um_cache_%d.env", new Object[]{Long.valueOf(System.currentTimeMillis())})), bArr);
                } catch (Exception unused) {
                }
            }
        }

        public void b() {
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles != null && listFiles.length > 0) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }

        public int c() {
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles == null || listFiles.length <= 0) {
                return 0;
            }
            return listFiles.length;
        }
    }

    private SharedPreferences f() {
        Context context = b;
        return context.getSharedPreferences(e + c, 0);
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            SharedPreferences.Editor edit = f().edit();
            edit.putString("au_p", str);
            edit.putString("au_u", str2);
            edit.commit();
        }
    }

    public String[] d() {
        try {
            SharedPreferences f2 = f();
            String string = f2.getString("au_p", (String) null);
            String string2 = f2.getString("au_u", (String) null);
            if (!(string == null || string2 == null)) {
                return new String[]{string, string2};
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public void e() {
        f().edit().remove("au_p").remove("au_u").commit();
    }
}
