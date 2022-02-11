package com.wx.assistants.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Locale;

public class Util {
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d1  */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFilePath(android.content.Context r12, android.net.Uri r13) throws java.net.URISyntaxException {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 19
            if (r0 < r2) goto L_0x009d
            android.content.Context r0 = r12.getApplicationContext()
            android.content.Context r0 = com.stub.StubApp.getOrigApplicationContext(r0)
            boolean r0 = android.provider.DocumentsContract.isDocumentUri(r0, r13)
            if (r0 == 0) goto L_0x009d
            boolean r0 = isExternalStorageDocument(r13)
            r2 = 1
            if (r0 == 0) goto L_0x0041
            java.lang.String r12 = android.provider.DocumentsContract.getDocumentId(r13)
            java.lang.String r13 = ":"
            java.lang.String[] r12 = r12.split(r13)
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()
            r13.append(r0)
            java.lang.String r0 = "/"
            r13.append(r0)
            r12 = r12[r2]
            r13.append(r12)
            java.lang.String r12 = r13.toString()
            return r12
        L_0x0041:
            boolean r0 = isDownloadsDocument(r13)
            if (r0 == 0) goto L_0x005e
            java.lang.String r13 = android.provider.DocumentsContract.getDocumentId(r13)
            java.lang.String r0 = "content://downloads/public_downloads"
            android.net.Uri r0 = android.net.Uri.parse(r0)
            java.lang.Long r13 = java.lang.Long.valueOf(r13)
            long r2 = r13.longValue()
            android.net.Uri r13 = android.content.ContentUris.withAppendedId(r0, r2)
            goto L_0x009d
        L_0x005e:
            boolean r0 = isMediaDocument(r13)
            if (r0 == 0) goto L_0x009d
            java.lang.String r0 = android.provider.DocumentsContract.getDocumentId(r13)
            java.lang.String r3 = ":"
            java.lang.String[] r0 = r0.split(r3)
            r3 = 0
            r4 = r0[r3]
            java.lang.String r5 = "image"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x007c
            android.net.Uri r13 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            goto L_0x0091
        L_0x007c:
            java.lang.String r5 = "video"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x0087
            android.net.Uri r13 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            goto L_0x0091
        L_0x0087:
            java.lang.String r5 = "audio"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0091
            android.net.Uri r13 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        L_0x0091:
            java.lang.String r4 = "_id=?"
            java.lang.String[] r5 = new java.lang.String[r2]
            r0 = r0[r2]
            r5[r3] = r0
            r7 = r13
            r9 = r4
            r10 = r5
            goto L_0x00a0
        L_0x009d:
            r7 = r13
            r9 = r1
            r10 = r9
        L_0x00a0:
            java.lang.String r13 = "content"
            java.lang.String r0 = r7.getScheme()
            boolean r13 = r13.equalsIgnoreCase(r0)
            if (r13 == 0) goto L_0x00d1
            java.lang.String r13 = "_data"
            java.lang.String[] r8 = new java.lang.String[]{r13}
            android.content.ContentResolver r6 = r12.getContentResolver()     // Catch:{ Exception -> 0x00cc }
            r11 = 0
            android.database.Cursor r12 = r6.query(r7, r8, r9, r10, r11)     // Catch:{ Exception -> 0x00cc }
            java.lang.String r13 = "_data"
            int r13 = r12.getColumnIndexOrThrow(r13)     // Catch:{ Exception -> 0x00cc }
            boolean r0 = r12.moveToFirst()     // Catch:{ Exception -> 0x00cc }
            if (r0 == 0) goto L_0x00e2
            java.lang.String r12 = r12.getString(r13)     // Catch:{ Exception -> 0x00cc }
            return r12
        L_0x00cc:
            r12 = move-exception
            r12.printStackTrace()
            goto L_0x00e2
        L_0x00d1:
            java.lang.String r12 = "file"
            java.lang.String r13 = r7.getScheme()
            boolean r12 = r12.equalsIgnoreCase(r13)
            if (r12 == 0) goto L_0x00e2
            java.lang.String r12 = r7.getPath()
            return r12
        L_0x00e2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.Util.getFilePath(android.content.Context, android.net.Uri):java.lang.String");
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static void writeFile(Context context) {
        try {
            OutputStream logStream = getLogStream(context);
            logStream.write(getInformation(context).getBytes("utf-8"));
            logStream.flush();
            logStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(Context context, String str) {
        try {
            OutputStream logStream = getLogStream(context);
            logStream.write(str.getBytes("utf-8"));
            logStream.flush();
            logStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static OutputStream getLogStream(Context context) throws IOException {
        String replace = Build.MODEL.replace(" ", "_");
        File file = new File(Environment.getExternalStorageDirectory(), String.format("compress_" + replace + ".log", new Object[]{context.getPackageName()}));
        if (!file.exists()) {
            file.createNewFile();
        }
        return new FileOutputStream(file, true);
    }

    public static String getInformation(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        return 10 + "BOARD: " + Build.BOARD + 10 + "BOOTLOADER: " + Build.BOOTLOADER + 10 + "BRAND: " + Build.BRAND + 10 + "CPU_ABI: " + Build.CPU_ABI + 10 + "CPU_ABI2: " + Build.CPU_ABI2 + 10 + "DEVICE: " + Build.DEVICE + 10 + "DISPLAY: " + Build.DISPLAY + 10 + "FINGERPRINT: " + Build.FINGERPRINT + 10 + "HARDWARE: " + Build.HARDWARE + 10 + "HOST: " + Build.HOST + 10 + "ID: " + Build.ID + 10 + "MANUFACTURER: " + Build.MANUFACTURER + 10 + "MODEL: " + Build.MODEL + 10 + "PRODUCT: " + Build.PRODUCT + 10 + "SERIAL: " + Build.SERIAL + 10 + "TAGS: " + Build.TAGS + 10 + "TIME: " + Build.TIME + ' ' + toDateString(Build.TIME) + 10 + "TYPE: " + Build.TYPE + 10 + "USER: " + Build.USER + 10 + "VERSION.CODENAME: " + Build.VERSION.CODENAME + 10 + "VERSION.INCREMENTAL: " + Build.VERSION.INCREMENTAL + 10 + "VERSION.RELEASE: " + Build.VERSION.RELEASE + 10 + "VERSION.SDK_INT: " + Build.VERSION.SDK_INT + 10 + "LANG: " + context.getResources().getConfiguration().locale.getLanguage() + 10 + "APP.VERSION.NAME: " + getVersionName(context) + 10 + "APP.VERSION.CODE: " + getVersionCode(context) + 10 + "CURRENT: " + currentTimeMillis + ' ' + toDateString(currentTimeMillis) + 10;
    }

    public static String toDateString(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return String.format(Locale.CHINESE, "%04d.%02d.%02d %02d:%02d:%02d:%03d", new Object[]{Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(11)), Integer.valueOf(instance.get(12)), Integer.valueOf(instance.get(13)), Integer.valueOf(instance.get(14))});
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
