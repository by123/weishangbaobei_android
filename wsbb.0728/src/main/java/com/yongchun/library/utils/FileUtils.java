package com.yongchun.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.yongchun.library.utils.luban.Luban;
import com.yongchun.library.utils.luban.OnCompressListener;
import com.yongchun.library.utils.luban.OnPhotoCompressListener;
import java.io.ByteArrayOutputStream;
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

    public static String imgToBase64(Context context, String str, Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        Throwable th;
        String str2 = null;
        if (str != null && str.length() > 0) {
            bitmap = readBitmap(str);
        }
        if (bitmap == null) {
            Toast.makeText(context, "bitmap not found!!", 0).show();
        } else {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    str2 = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
                    try {
                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e2) {
                } catch (Throwable th2) {
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    th = th2;
                    try {
                        byteArrayOutputStream2.flush();
                        byteArrayOutputStream2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    throw th;
                }
            } catch (Exception e4) {
                byteArrayOutputStream = null;
                try {
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return str2;
            } catch (Throwable th3) {
                byteArrayOutputStream2 = null;
                th = th3;
                byteArrayOutputStream2.flush();
                byteArrayOutputStream2.close();
                throw th;
            }
        }
        return str2;
    }

    public static void photoCompress(Context context, final ArrayList<String> arrayList, OnPhotoCompressListener onPhotoCompressListener) {
        mylistener = onPhotoCompressListener;
        final ArrayList arrayList2 = new ArrayList();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < arrayList.size()) {
                File file = new File(arrayList.get(i2));
                if (file.length() / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS <= 60) {
                    Log.d("--", "@@FirstLength=" + (file.length() / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS) + "k, @@path= " + arrayList.get(i2));
                    arrayList2.add(arrayList.get(i2));
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
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public static void photoCompressToBase64(final Context context, final ArrayList<String> arrayList, OnPhotoCompressListener onPhotoCompressListener) {
        final ArrayList arrayList2 = new ArrayList();
        mylistener = onPhotoCompressListener;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < arrayList.size()) {
                File file = new File(arrayList.get(i2));
                if (file.length() / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS <= 60) {
                    Log.d("--", "@@FirstLength=" + (file.length() / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS) + "k, @@path= " + arrayList.get(i2));
                    arrayList2.add(imgToBase64(context, (String) arrayList2.get(i2), (Bitmap) null));
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
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    private static Bitmap readBitmap(String str) {
        try {
            return BitmapFactory.decodeFile(str);
        } catch (Exception e) {
            return null;
        }
    }
}
