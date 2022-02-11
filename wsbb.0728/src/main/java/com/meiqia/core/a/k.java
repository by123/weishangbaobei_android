package com.meiqia.core.a;

import android.annotation.TargetApi;
import android.content.ContentResolver;
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
import com.stub.StubApp;
import com.umeng.commonsdk.proguard.e;
import gdut.bsx.share2.ShareContentType;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class k {
    public static Uri a(Context context, String str) {
        Uri uri = null;
        Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{str}, (String) null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    int i = query.getInt(query.getColumnIndex("_id"));
                    Uri parse = Uri.parse("content://media/external/images/media");
                    uri = Uri.withAppendedPath(parse, "" + i);
                    return uri;
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
            uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            if (query != null) {
                query.close();
            }
        } else if (query != null) {
            query.close();
        }
        return uri;
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

    /* JADX WARNING: Removed duplicated region for block: B:24:0x005d A[SYNTHETIC, Splitter:B:24:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0062 A[Catch:{ IOException -> 0x0066 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006f A[SYNTHETIC, Splitter:B:33:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0074 A[Catch:{ IOException -> 0x0094 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    @TargetApi(29)
    public static void a(Context context, String str, String str2) {
        OutputStream outputStream;
        FileInputStream fileInputStream;
        Throwable th;
        FileInputStream fileInputStream2 = null;
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str2);
        contentValues.put("mime_type", ShareContentType.FILE);
        contentValues.put("relative_path", Environment.DIRECTORY_DOWNLOADS);
        Uri uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = context.getContentResolver();
        Uri insert = contentResolver.insert(uri, contentValues);
        if (insert != null) {
            try {
                outputStream = contentResolver.openOutputStream(insert);
                if (outputStream != null) {
                    try {
                        File file = new File(str);
                        if (file.exists()) {
                            fileInputStream = new FileInputStream(file);
                            try {
                                byte[] bArr = new byte[4096];
                                while (true) {
                                    int read = fileInputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    outputStream.write(bArr, 0, read);
                                }
                            } catch (Exception e) {
                                e = e;
                                try {
                                    e.printStackTrace();
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e2) {
                                            e2.printStackTrace();
                                            return;
                                        }
                                    }
                                    if (outputStream == null) {
                                        outputStream.close();
                                        return;
                                    }
                                    return;
                                } catch (Throwable th2) {
                                    th = th2;
                                    fileInputStream2 = fileInputStream;
                                    fileInputStream = fileInputStream2;
                                    th = th;
                                    if (fileInputStream != null) {
                                    }
                                    if (outputStream != null) {
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                        throw th;
                                    }
                                }
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                                throw th;
                            }
                        } else {
                            fileInputStream = null;
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (Exception e4) {
                        e = e4;
                        fileInputStream = null;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                        }
                        if (outputStream == null) {
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        fileInputStream = fileInputStream2;
                        th = th;
                        if (fileInputStream != null) {
                        }
                        if (outputStream != null) {
                        }
                        throw th;
                    }
                } else if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
            } catch (Exception e6) {
                e = e6;
                fileInputStream = null;
                outputStream = null;
            } catch (Throwable th5) {
                fileInputStream = null;
                th = th5;
                outputStream = null;
                if (fileInputStream != null) {
                }
                if (outputStream != null) {
                }
                throw th;
            }
        }
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
        } catch (Exception e) {
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
        } catch (Exception e) {
        }
        return hashMap;
    }

    public static boolean f(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
    }

    private static String g(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels + "x" + displayMetrics.widthPixels;
    }
}
