package com.yongchun.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.yongchun.library.utils.luban.Luban;
import com.yongchun.library.utils.luban.OnCompressListener;
import com.yongchun.library.utils.luban.OnPhotoCompressListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class FileUtils {
    public static final String APP_NAME = "ImageSelector";
    public static final String CAMERA_PATH = "/ImageSelector/CameraImage/";
    public static final String CROP_PATH = "/ImageSelector/CropImage/";
    public static final String POSTFIX = ".JPEG";
    public static OnPhotoCompressListener mylistener;

    public static File createCameraFile(Context context) {
        return createMediaFile(context, CAMERA_PATH);
    }

    public static File createCropFile(Context context) {
        return createMediaFile(context, CROP_PATH);
    }

    private static File createMediaFile(Context context, String str) {
        File externalStorageDirectory = Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory() : context.getCacheDir();
        File file = new File(externalStorageDirectory.getAbsolutePath() + str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, ("ImageSelector_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date()) + "") + ".JPEG");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0051 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String imgToBase64(android.content.Context r3, java.lang.String r4, android.graphics.Bitmap r5) {
        /*
            if (r4 == 0) goto L_0x000c
            int r0 = r4.length()
            if (r0 <= 0) goto L_0x000c
            android.graphics.Bitmap r5 = readBitmap(r4)
        L_0x000c:
            r4 = 0
            r0 = 0
            if (r5 != 0) goto L_0x001a
            java.lang.String r5 = "bitmap not found!!"
            android.widget.Toast r3 = android.widget.Toast.makeText(r3, r5, r4)
            r3.show()
            return r0
        L_0x001a:
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0050, all -> 0x0042 }
            r3.<init>()     // Catch:{ Exception -> 0x0050, all -> 0x0042 }
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x0051, all -> 0x0040 }
            r2 = 100
            r5.compress(r1, r2, r3)     // Catch:{ Exception -> 0x0051, all -> 0x0040 }
            r3.flush()     // Catch:{ Exception -> 0x0051, all -> 0x0040 }
            r3.close()     // Catch:{ Exception -> 0x0051, all -> 0x0040 }
            byte[] r5 = r3.toByteArray()     // Catch:{ Exception -> 0x0051, all -> 0x0040 }
            java.lang.String r4 = android.util.Base64.encodeToString(r5, r4)     // Catch:{ Exception -> 0x0051, all -> 0x0040 }
            r3.flush()     // Catch:{ IOException -> 0x003b }
            r3.close()     // Catch:{ IOException -> 0x003b }
            goto L_0x003f
        L_0x003b:
            r3 = move-exception
            r3.printStackTrace()
        L_0x003f:
            return r4
        L_0x0040:
            r4 = move-exception
            goto L_0x0044
        L_0x0042:
            r4 = move-exception
            r3 = r0
        L_0x0044:
            r3.flush()     // Catch:{ IOException -> 0x004b }
            r3.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x004f
        L_0x004b:
            r3 = move-exception
            r3.printStackTrace()
        L_0x004f:
            throw r4
        L_0x0050:
            r3 = r0
        L_0x0051:
            r3.flush()     // Catch:{ IOException -> 0x0058 }
            r3.close()     // Catch:{ IOException -> 0x0058 }
            goto L_0x005c
        L_0x0058:
            r3 = move-exception
            r3.printStackTrace()
        L_0x005c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yongchun.library.utils.FileUtils.imgToBase64(android.content.Context, java.lang.String, android.graphics.Bitmap):java.lang.String");
    }

    private static Bitmap readBitmap(String str) {
        try {
            return BitmapFactory.decodeFile(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void base64ToBitmap(String str, String str2) {
        FileOutputStream fileOutputStream;
        byte[] decode = Base64.decode(str, 0);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        try {
            fileOutputStream = new FileOutputStream(new File("/sdcard/", str2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileOutputStream = null;
        }
        if (decodeByteArray.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)) {
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } else {
            try {
                fileOutputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    public static void photoCompress(Context context, final ArrayList<String> arrayList, OnPhotoCompressListener onPhotoCompressListener) {
        mylistener = onPhotoCompressListener;
        final ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            File file = new File(arrayList.get(i));
            if (file.length() / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS <= 60) {
                Log.d("--", "@@FirstLength=" + (file.length() / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS) + "k, @@path= " + arrayList.get(i));
                arrayList2.add(arrayList.get(i));
                if (arrayList2.size() == arrayList.size()) {
                    mylistener.complete(arrayList2);
                }
            } else {
                Luban.get(context).load(file).putGear(1).setCompressListener(new OnCompressListener() {
                    public void onError(Throwable th) {
                    }

                    public void onStart() {
                    }

                    public void onSuccess(File file) {
                        Log.d("--", "@@SecondLength=" + (file.length() / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS) + "k, @@path= " + file.getAbsolutePath());
                        arrayList2.add(file.getAbsolutePath());
                        if (arrayList2.size() == arrayList.size()) {
                            FileUtils.mylistener.complete(arrayList2);
                        }
                    }
                }).launch();
            }
        }
    }

    public static void photoCompressToBase64(final Context context, final ArrayList<String> arrayList, OnPhotoCompressListener onPhotoCompressListener) {
        final ArrayList arrayList2 = new ArrayList();
        mylistener = onPhotoCompressListener;
        for (int i = 0; i < arrayList.size(); i++) {
            File file = new File(arrayList.get(i));
            if (file.length() / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS <= 60) {
                Log.d("--", "@@FirstLength=" + (file.length() / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS) + "k, @@path= " + arrayList.get(i));
                arrayList2.add(imgToBase64(context, (String) arrayList2.get(i), (Bitmap) null));
                if (arrayList2.size() == arrayList.size()) {
                    mylistener.complete(arrayList2);
                }
            } else {
                Luban.get(context).load(file).putGear(1).setCompressListener(new OnCompressListener() {
                    public void onError(Throwable th) {
                    }

                    public void onStart() {
                    }

                    public void onSuccess(File file) {
                        Log.d("--", "@@SecondLength=" + (file.length() / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS) + "k, @@path= " + file.getAbsolutePath());
                        arrayList2.add(FileUtils.imgToBase64(context, file.getAbsolutePath(), (Bitmap) null));
                        if (arrayList2.size() == arrayList.size()) {
                            FileUtils.mylistener.complete(arrayList2);
                        }
                    }
                }).launch();
            }
        }
    }
}
