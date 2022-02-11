package com.umeng.commonsdk.statistics.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Locale;

public final class d {
    private static d a = null;
    private static Context b = null;
    private static String c = null;
    private static final String e = "mobclick_agent_user_";
    private static final String f = "mobclick_agent_header_";
    private static final String g = "mobclick_agent_cached_";
    private a d;

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

        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0043, code lost:
            r0 = r2[r1];
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        public void a(b bVar) {
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles != null && listFiles.length >= 10) {
                Arrays.sort(listFiles);
                int length = listFiles.length;
                for (int i = 0; i < length - 10; i++) {
                    listFiles[i].delete();
                }
            }
            if (listFiles != null && listFiles.length > 0) {
                bVar.a(this.b);
                int length2 = listFiles.length;
                int i2 = 0;
                while (i2 < length2) {
                    if (bVar.b(listFiles[i2])) {
                        File file = listFiles[i2];
                        file.delete();
                        i2++;
                    } else {
                        i2++;
                    }
                }
                bVar.c(this.b);
            }
        }

        public void a(byte[] bArr) {
            if (bArr != null && bArr.length != 0) {
                try {
                    HelperUtils.writeFile(new File(this.b, String.format(Locale.US, "um_cache_%d.env", new Object[]{Long.valueOf(System.currentTimeMillis())})), bArr);
                } catch (Exception e) {
                }
            }
        }

        public boolean a() {
            File[] listFiles = this.b.listFiles();
            return listFiles != null && listFiles.length > 0;
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

    public interface b {
        void a(File file);

        boolean b(File file);

        void c(File file);
    }

    public d(Context context) {
        this.d = new a(context);
    }

    public static d a(Context context) {
        d dVar;
        synchronized (d.class) {
            try {
                b = StubApp.getOrigApplicationContext(context.getApplicationContext());
                c = context.getPackageName();
                if (a == null) {
                    a = new d(context);
                }
                dVar = a;
            } catch (Throwable th) {
                Class<d> cls = d.class;
                throw th;
            }
        }
        return dVar;
    }

    private SharedPreferences f() {
        Context context = b;
        return context.getSharedPreferences(e + c, 0);
    }

    public int a() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(b);
        if (sharedPreferences != null) {
            return sharedPreferences.getInt("vt", 0);
        }
        return 0;
    }

    public void a(int i) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt("vt", i).commit();
        }
    }

    public void a(String str) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("st", str).commit();
        }
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            SharedPreferences.Editor edit = f().edit();
            edit.putString("au_p", str);
            edit.putString("au_u", str2);
            edit.commit();
        }
    }

    public String b() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(b);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("st", (String) null);
        }
        return null;
    }

    public boolean c() {
        return UMFrUtils.envelopeFileNumber(b) > 0;
    }

    public String[] d() {
        try {
            SharedPreferences f2 = f();
            String string = f2.getString("au_p", (String) null);
            String string2 = f2.getString("au_u", (String) null);
            if (string == null || string2 == null) {
                return null;
            }
            return new String[]{string, string2};
        } catch (Exception e2) {
            return null;
        }
    }

    public void e() {
        f().edit().remove("au_p").remove("au_u").commit();
    }
}
