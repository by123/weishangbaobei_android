package com.meiqia.core.a;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.meiqia.core.MQManager;
import com.meiqia.core.bean.MQClient;
import com.umeng.commonsdk.proguard.e;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class k {
    public static Uri a(Context context, String str) {
        Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{str}, (String) null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    int i = query.getInt(query.getColumnIndex("_id"));
                    Uri parse = Uri.parse("content://media/external/images/media");
                    return Uri.withAppendedPath(parse, "" + i);
                }
            } finally {
                if (query != null) {
                    query.close();
                }
            }
        }
        if (new File(str).exists()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_data", str);
            Uri insert = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            if (query != null) {
                query.close();
            }
            return insert;
        }
        if (query != null) {
            query.close();
        }
        return null;
    }

    @Deprecated
    public static MQClient a(String str, i iVar) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String i = iVar.i();
        iVar.c(str);
        String c = iVar.c();
        String g = iVar.g();
        String e = iVar.e();
        String f = iVar.f();
        String d = iVar.d();
        String h = iVar.h();
        if (!(!TextUtils.isEmpty(c) && !TextUtils.isEmpty(g) && !TextUtils.isEmpty(e) && !TextUtils.isEmpty(f) && !TextUtils.isEmpty(d) && !TextUtils.isEmpty(h))) {
            return null;
        }
        MQClient mQClient = new MQClient();
        mQClient.setVisitPageId(e);
        mQClient.setVisitId(d);
        mQClient.setTrackId(c);
        mQClient.setEnterpriseId(g);
        mQClient.setAESKey(h);
        mQClient.setBrowserId(f);
        mQClient.setBindUserId(str);
        iVar.c(i);
        return mQClient;
    }

    public static String a(Context context) {
        File externalFilesDir = context.getExternalFilesDir((String) null);
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdir();
        }
        File file = new File(externalFilesDir.getAbsolutePath() + "/mq");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getAbsolutePath();
    }

    public static void a(Context context, Intent intent) {
        intent.setPackage(context.getPackageName());
        intent.putExtra("packageName", context.getPackageName());
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0075 A[SYNTHETIC, Splitter:B:40:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x007d A[Catch:{ IOException -> 0x0079 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0088 A[SYNTHETIC, Splitter:B:49:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0090 A[Catch:{ IOException -> 0x008c }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    @android.annotation.TargetApi(29)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r2, java.lang.String r3, java.lang.String r4) {
        /*
            android.content.ContentValues r0 = new android.content.ContentValues
            r0.<init>()
            java.lang.String r1 = "_display_name"
            r0.put(r1, r4)
            java.lang.String r4 = "mime_type"
            java.lang.String r1 = "*/*"
            r0.put(r4, r1)
            java.lang.String r4 = "relative_path"
            java.lang.String r1 = android.os.Environment.DIRECTORY_DOWNLOADS
            r0.put(r4, r1)
            android.net.Uri r4 = android.provider.MediaStore.Downloads.EXTERNAL_CONTENT_URI
            android.content.ContentResolver r2 = r2.getContentResolver()
            android.net.Uri r4 = r2.insert(r4, r0)
            if (r4 != 0) goto L_0x0025
            return
        L_0x0025:
            r0 = 0
            java.io.OutputStream r2 = r2.openOutputStream(r4)     // Catch:{ Exception -> 0x006e, all -> 0x006b }
            if (r2 != 0) goto L_0x0037
            if (r2 == 0) goto L_0x0036
            r2.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x0036
        L_0x0032:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0036:
            return
        L_0x0037:
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x0069 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0069 }
            boolean r3 = r4.exists()     // Catch:{ Exception -> 0x0069 }
            if (r3 == 0) goto L_0x005d
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0069 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0069 }
            r4 = 4096(0x1000, float:5.74E-42)
            byte[] r4 = new byte[r4]     // Catch:{ Exception -> 0x005a, all -> 0x0057 }
        L_0x004b:
            int r0 = r3.read(r4)     // Catch:{ Exception -> 0x005a, all -> 0x0057 }
            r1 = -1
            if (r0 == r1) goto L_0x005e
            r1 = 0
            r2.write(r4, r1, r0)     // Catch:{ Exception -> 0x005a, all -> 0x0057 }
            goto L_0x004b
        L_0x0057:
            r4 = move-exception
            r0 = r3
            goto L_0x0086
        L_0x005a:
            r4 = move-exception
            r0 = r3
            goto L_0x0070
        L_0x005d:
            r3 = r0
        L_0x005e:
            if (r3 == 0) goto L_0x0063
            r3.close()     // Catch:{ IOException -> 0x0079 }
        L_0x0063:
            if (r2 == 0) goto L_0x0084
            r2.close()     // Catch:{ IOException -> 0x0079 }
            goto L_0x0084
        L_0x0069:
            r4 = move-exception
            goto L_0x0070
        L_0x006b:
            r4 = move-exception
            r2 = r0
            goto L_0x0086
        L_0x006e:
            r4 = move-exception
            r2 = r0
        L_0x0070:
            r4.printStackTrace()     // Catch:{ all -> 0x0085 }
            if (r0 == 0) goto L_0x007b
            r0.close()     // Catch:{ IOException -> 0x0079 }
            goto L_0x007b
        L_0x0079:
            r2 = move-exception
            goto L_0x0081
        L_0x007b:
            if (r2 == 0) goto L_0x0084
            r2.close()     // Catch:{ IOException -> 0x0079 }
            goto L_0x0084
        L_0x0081:
            r2.printStackTrace()
        L_0x0084:
            return
        L_0x0085:
            r4 = move-exception
        L_0x0086:
            if (r0 == 0) goto L_0x008e
            r0.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x008e
        L_0x008c:
            r2 = move-exception
            goto L_0x0094
        L_0x008e:
            if (r2 == 0) goto L_0x0097
            r2.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x0097
        L_0x0094:
            r2.printStackTrace()
        L_0x0097:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.core.a.k.a(android.content.Context, java.lang.String, java.lang.String):void");
    }

    public static boolean a() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static boolean a(FileDescriptor fileDescriptor, String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileDescriptor);
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            byte[] bArr = new byte[1444];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String c(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return "";
        }
        int type = activeNetworkInfo.getType();
        return type == 0 ? g.a(activeNetworkInfo.getSubtype()) : type == 1 ? "WIFI" : "unknown";
    }

    public static String d(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
        } catch (Exception unused) {
            return "";
        }
    }

    public static Map<String, Object> e(Context context) {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put(e.E, Build.BRAND);
            hashMap.put("device_model", Build.MODEL + " " + Build.DEVICE);
            hashMap.put("os_family", "Android");
            hashMap.put("os_version", Build.VERSION.RELEASE);
            hashMap.put(e.y, g(context));
            hashMap.put("net_type", c(context));
            hashMap.put("app_version", b(context));
            hashMap.put("sdk_version", MQManager.getMeiqiaSDKVersion());
            hashMap.put("os_language", Locale.getDefault().getLanguage());
            hashMap.put("os_timezone", TimeZone.getDefault().getID());
            hashMap.put("app_name", d(context));
        } catch (Exception unused) {
        }
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0014, code lost:
        r2 = r2.getActiveNetworkInfo();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean f(android.content.Context r2) {
        /*
            android.content.Context r2 = r2.getApplicationContext()
            android.content.Context r2 = com.stub.StubApp.getOrigApplicationContext(r2)
            java.lang.String r0 = "connectivity"
            java.lang.Object r2 = r2.getSystemService(r0)
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2
            r0 = 0
            if (r2 != 0) goto L_0x0014
            return r0
        L_0x0014:
            android.net.NetworkInfo r2 = r2.getActiveNetworkInfo()
            if (r2 == 0) goto L_0x0027
            boolean r1 = r2.isAvailable()
            if (r1 == 0) goto L_0x0027
            boolean r2 = r2.isConnected()
            if (r2 == 0) goto L_0x0027
            r0 = 1
        L_0x0027:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.core.a.k.f(android.content.Context):boolean");
    }

    private static String g(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels + "x" + displayMetrics.widthPixels;
    }
}
