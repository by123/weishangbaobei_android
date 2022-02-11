package com.wx.assistants.utils;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import com.luck.picture.lib.config.PictureConfig;
import com.stub.StubApp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Locale;

public class Util {
    /* JADX WARNING: Removed duplicated region for block: B:13:0x006d A[SYNTHETIC, Splitter:B:13:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00d1  */
    @SuppressLint({"NewApi"})
    public static String getFilePath(Context context, Uri uri) throws URISyntaxException {
        String[] strArr;
        String str;
        Uri uri2;
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(StubApp.getOrigApplicationContext(context.getApplicationContext()), uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                uri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue());
            } else if (isMediaDocument(uri)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String str2 = split2[0];
                if ("image".equals(str2)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if (PictureConfig.VIDEO.equals(str2)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str2)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                str = "_id=?";
                strArr = new String[]{split2[1]};
                uri2 = uri;
                if (!"content".equalsIgnoreCase(uri2.getScheme())) {
                    try {
                        Cursor query = context.getContentResolver().query(uri2, new String[]{"_data"}, str, strArr, (String) null);
                        int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
                        if (query.moveToFirst()) {
                            return query.getString(columnIndexOrThrow);
                        }
                        return null;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                } else if ("file".equalsIgnoreCase(uri2.getScheme())) {
                    return uri2.getPath();
                } else {
                    return null;
                }
            }
        }
        strArr = null;
        uri2 = uri;
        str = null;
        if (!"content".equalsIgnoreCase(uri2.getScheme())) {
        }
    }

    public static String getInformation(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        return 10 + "BOARD: " + Build.BOARD + 10 + "BOOTLOADER: " + Build.BOOTLOADER + 10 + "BRAND: " + Build.BRAND + 10 + "CPU_ABI: " + Build.CPU_ABI + 10 + "CPU_ABI2: " + Build.CPU_ABI2 + 10 + "DEVICE: " + Build.DEVICE + 10 + "DISPLAY: " + Build.DISPLAY + 10 + "FINGERPRINT: " + Build.FINGERPRINT + 10 + "HARDWARE: " + Build.HARDWARE + 10 + "HOST: " + Build.HOST + 10 + "ID: " + Build.ID + 10 + "MANUFACTURER: " + Build.MANUFACTURER + 10 + "MODEL: " + Build.MODEL + 10 + "PRODUCT: " + Build.PRODUCT + 10 + "SERIAL: " + Build.SERIAL + 10 + "TAGS: " + Build.TAGS + 10 + "TIME: " + Build.TIME + ' ' + toDateString(Build.TIME) + 10 + "TYPE: " + Build.TYPE + 10 + "USER: " + Build.USER + 10 + "VERSION.CODENAME: " + Build.VERSION.CODENAME + 10 + "VERSION.INCREMENTAL: " + Build.VERSION.INCREMENTAL + 10 + "VERSION.RELEASE: " + Build.VERSION.RELEASE + 10 + "VERSION.SDK_INT: " + Build.VERSION.SDK_INT + 10 + "LANG: " + context.getResources().getConfiguration().locale.getLanguage() + 10 + "APP.VERSION.NAME: " + getVersionName(context) + 10 + "APP.VERSION.CODE: " + getVersionCode(context) + 10 + "CURRENT: " + currentTimeMillis + ' ' + toDateString(currentTimeMillis) + 10;
    }

    public static OutputStream getLogStream(Context context) throws IOException {
        String replace = Build.MODEL.replace(" ", "_");
        File file = new File(Environment.getExternalStorageDirectory(), String.format("compress_" + replace + ".log", new Object[]{context.getPackageName()}));
        if (!file.exists()) {
            file.createNewFile();
        }
        return new FileOutputStream(file, true);
    }

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static String toDateString(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return String.format(Locale.CHINESE, "%04d.%02d.%02d %02d:%02d:%02d:%03d", new Object[]{Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(11)), Integer.valueOf(instance.get(12)), Integer.valueOf(instance.get(13)), Integer.valueOf(instance.get(14))});
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
}
