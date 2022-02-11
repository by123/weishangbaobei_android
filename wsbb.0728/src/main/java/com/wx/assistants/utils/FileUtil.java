package com.wx.assistants.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import com.bumptech.glide.Glide;
import com.fb.jjyyzjy.watermark.utils.AlbumUtil;
import com.luck.picture.lib.config.PictureMimeType;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.weicheng.amrconvert.AmrConvertUtils;
import com.wx.assistants.application.MyApplication;
import io.microshow.rxffmpeg.RxFFmpegInvoke;
import io.microshow.rxffmpeg.RxFFmpegSubscriber;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileUtil {
    public static final String FOLDER_NAME = "xinlanedit";
    /* access modifiers changed from: private */
    public MyHandler handler = new MyHandler();
    /* access modifiers changed from: private */
    public OnSaveCompletedListener listener;
    private OnConvertMp3Listener onConvertMp3Listener;
    /* access modifiers changed from: private */
    public int savedCount = 0;
    /* access modifiers changed from: private */
    public int totalCount = 0;

    class MyHandler extends Handler {
        MyHandler() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (FileUtil.this.savedCount == FileUtil.this.totalCount && FileUtil.this.listener != null) {
                LogUtils.log("ws_baby_update_5");
                FileUtil.this.listener.saveCompleted();
            }
        }
    }

    public interface OnConvertMp3Listener {
        void convertFail();

        void convertSuccess(String str);
    }

    public interface OnConvertMp3Listener2 {
        void convertFail();

        void convertProgress(int i);

        void convertSuccess(String str);
    }

    public interface OnConvertMp4Listener {
        void convertFail();

        void convertProgress(int i);

        void convertSuccess(String str);
    }

    public interface OnSaveCompletedListener {
        void saveCompleted();
    }

    public static void amrConvertMp3(Context context, String str, OnConvertMp3Listener onConvertMp3Listener2) {
        try {
            File file = new File(str);
            String substring = file.getName().substring(0, file.getName().indexOf(".amr"));
            String str2 = Environment.getExternalStorageDirectory() + "/ws_baby_mp3/";
            File file2 = new File(str2);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            String str3 = str2 + substring + ".mp3";
            File file3 = new File(str3);
            if (file3.exists()) {
                file3.delete();
            }
            if (AmrConvertUtils.amr2Mp3(context, str, str3)) {
                if (onConvertMp3Listener2 != null) {
                    onConvertMp3Listener2.convertSuccess(str3);
                }
            } else if (onConvertMp3Listener2 != null) {
                onConvertMp3Listener2.convertFail();
            }
        } catch (Exception e) {
            if (onConvertMp3Listener2 != null) {
                onConvertMp3Listener2.convertFail();
            }
        }
    }

    public static void amrConvertShelfMp3(Context context, String str, final OnConvertMp3Listener onConvertMp3Listener2) {
        try {
            File file = new File(str);
            String substring = file.getName().substring(0, file.getName().indexOf(".amr"));
            String str2 = Environment.getExternalStorageDirectory() + "/ws_baby_mp3/";
            File file2 = new File(str2);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            String str3 = str2 + substring + ".mp3";
            File file3 = new File(str3);
            if (file3.exists()) {
                file3.delete();
            }
            amrToMp3(str, str3, new OnConvertMp3Listener2() {
                public void convertFail() {
                    if (onConvertMp3Listener2 != null) {
                        onConvertMp3Listener2.convertFail();
                    }
                }

                public void convertProgress(int i) {
                }

                public void convertSuccess(String str) {
                    if (onConvertMp3Listener2 != null) {
                        onConvertMp3Listener2.convertSuccess(str);
                    }
                }
            });
        } catch (Exception e) {
            if (onConvertMp3Listener2 != null) {
                onConvertMp3Listener2.convertFail();
            }
        }
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [io.reactivex.FlowableSubscriber, com.wx.assistants.utils.FileUtil$2] */
    public static void amrToMp3(String str, final String str2, final OnConvertMp3Listener2 onConvertMp3Listener2) {
        RxFFmpegInvoke.getInstance().runCommandRxJava(("ffmpeg -y -i " + str + " -vf boxblur=25:5 -preset superfast " + str2).split(" ")).subscribe(new RxFFmpegSubscriber() {
            public void onCancel() {
                LogUtils.log("WS_BABY_onCancel");
            }

            public void onError(String str) {
                LogUtils.log("WS_BABY_onError");
            }

            public void onFinish() {
                if (onConvertMp3Listener2 != null) {
                    onConvertMp3Listener2.convertSuccess(str2);
                }
                LogUtils.log("WS_BABY_onFinish");
            }

            public void onProgress(int i, long j) {
                if (onConvertMp3Listener2 != null) {
                    onConvertMp3Listener2.convertProgress(i);
                }
                LogUtils.log("WS_BABY_onProgress");
            }
        });
    }

    public static File createFolders() {
        File externalStorageDirectory = Build.VERSION.SDK_INT < 8 ? Environment.getExternalStorageDirectory() : Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (externalStorageDirectory == null) {
            return Environment.getExternalStorageDirectory();
        }
        File file = new File(externalStorageDirectory, FOLDER_NAME);
        if (file.exists()) {
            return file;
        }
        if (file.isFile()) {
            file.delete();
        }
        return !file.mkdirs() ? Environment.getExternalStorageDirectory() : file;
    }

    public static boolean deleteFileNoThrow(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return file.delete();
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static File genEditFile() {
        return getEmptyFile("tietu" + System.currentTimeMillis() + PictureMimeType.PNG);
    }

    public static File getEmptyFile(String str) {
        File createFolders = createFolders();
        if (createFolders == null || !createFolders.exists()) {
            return null;
        }
        return new File(createFolders, str);
    }

    public static long getFolderSize(File file) throws Exception {
        long j = 0;
        try {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                j += listFiles[i].isDirectory() ? getFolderSize(listFiles[i]) : listFiles[i].length();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    public static String getFormatSize(double d) {
        return ((int) ((d / 1024.0d) / 1024.0d)) + "MB";
    }

    public static File getSaveFile(Context context) {
        return new File(context.getFilesDir(), "pic.jpg");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r0 = r0.getActiveNetworkInfo();
     */
    public static boolean isConnect(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            return connectivityManager != null && activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
        } catch (Exception e) {
        }
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [io.reactivex.FlowableSubscriber, com.wx.assistants.utils.FileUtil$3] */
    public static void mp4ToMp4(String str, final String str2, final OnConvertMp4Listener onConvertMp4Listener) {
        RxFFmpegInvoke.getInstance().runCommandRxJava(("ffmpeg -y -i " + str + " -vcodec copy -f mp4 " + str2).split(" ")).subscribe(new RxFFmpegSubscriber() {
            public void onCancel() {
                LogUtils.log("WS_BABY_onCancel");
            }

            public void onError(String str) {
                LogUtils.log("WS_BABY_onError");
            }

            public void onFinish() {
                if (onConvertMp4Listener != null) {
                    onConvertMp4Listener.convertSuccess(str2);
                }
                LogUtils.log("WS_BABY_onFinish");
            }

            public void onProgress(int i, long j) {
                if (onConvertMp4Listener != null) {
                    onConvertMp4Listener.convertProgress(i);
                }
                LogUtils.log("WS_BABY_onProgress");
            }
        });
    }

    public static void renameFile(String str, String str2) {
        new File(str).renameTo(new File(str2));
    }

    public static Bitmap returnBitMap(String str) {
        URL url;
        Bitmap bitmap = null;
        try {
            url = new URL(str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            try {
                inputStream.close();
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                return bitmap;
            }
        } catch (IOException e3) {
            e = e3;
        }
        return bitmap;
    }

    public static String saveBitmap(String str, Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        File file = new File(createFolders().getAbsolutePath(), str);
        try {
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
            fileOutputStream = null;
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        try {
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    /* access modifiers changed from: private */
    public void updateToSystemAlbum(Context context, File file) {
        if (file != null) {
            LogUtils.log("ws_baby_update_0");
            String absolutePath = file.getAbsolutePath();
            try {
                MediaStore.Images.Media.insertImage(context.getContentResolver(), absolutePath, file.getName(), (String) null);
                LogUtils.log("ws_baby_update_1");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                LogUtils.log("ws_baby_update_2");
            }
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + absolutePath)));
            LogUtils.log("ws_baby_update_3");
            this.savedCount = this.savedCount + 1;
            this.handler.sendEmptyMessage(0);
            LogUtils.log("ws_baby_update_4");
        }
    }

    public byte[] readInputStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[WXMediaMessage.DESCRIPTION_LENGTH_LIMIT];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public void saveImg(Context context, String str, String str2) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(5000);
        byte[] readInputStream = readInputStream(httpURLConnection.getInputStream());
        FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
        fileOutputStream.write(readInputStream);
        fileOutputStream.close();
        AlbumUtil.insertImageToMediaStore(context, str2);
        this.savedCount++;
        this.handler.sendEmptyMessage(0);
    }

    public void saveSyn(final Context context, final ArrayList<String> arrayList, OnSaveCompletedListener onSaveCompletedListener) {
        if (onSaveCompletedListener != null) {
            this.listener = onSaveCompletedListener;
        }
        if (arrayList != null && arrayList.size() > 0) {
            this.savedCount = 0;
            this.totalCount = arrayList.size();
            ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
            newCachedThreadPool.execute(new Runnable() {
                public void run() {
                    for (int i = 0; i < arrayList.size(); i++) {
                        try {
                            FileUtil.this.saveImg(context, (String) arrayList.get(i), com.fb.jjyyzjy.watermark.utils.FileUtil.genEditFile().getAbsolutePath());
                        } catch (Exception e) {
                            e.printStackTrace();
                            FileUtil.this.handler.sendEmptyMessage(0);
                            LogUtils.log("ws_baby_error_1");
                        }
                    }
                }
            });
            newCachedThreadPool.shutdown();
        }
    }

    public void saveSyn(final Context context, final byte[] bArr, OnSaveCompletedListener onSaveCompletedListener) {
        if (onSaveCompletedListener != null) {
            this.listener = onSaveCompletedListener;
        }
        this.savedCount = 0;
        this.totalCount = 1;
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        newCachedThreadPool.execute(new Runnable() {
            public void run() {
                try {
                    File file = (File) Glide.with(MyApplication.getConText()).load(bArr).downloadOnly(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
                    LogUtils.log("ws_baby_size" + file.getName());
                    LogUtils.log("ws_baby_update_000000000");
                    FileUtil.this.updateToSystemAlbum(context, file);
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.log("ws_baby_update_11111111111");
                }
            }
        });
        newCachedThreadPool.shutdown();
    }
}
