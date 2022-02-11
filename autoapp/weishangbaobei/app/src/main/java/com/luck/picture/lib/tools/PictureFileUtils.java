package com.luck.picture.lib.tools;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.yalantis.ucrop.view.CropImageView;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PictureFileUtils {
    public static final String APP_NAME = "PictureSelector";
    public static final String CAMERA_PATH = "/PictureSelector/CameraImage/";
    public static final String CROP_PATH = "/PictureSelector/CropImage/";
    private static String DEFAULT_CACHE_DIR = "picture_cache";
    public static final String POSTFIX = ".JPEG";
    public static final String POST_VIDEO = ".mp4";
    static final String TAG = "PictureFileUtils";

    public static File createCameraFile(Context context, int i, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            str = CAMERA_PATH;
        }
        return createMediaFile(context, str, i, str2);
    }

    public static File createCropFile(Context context, int i, String str) {
        return createMediaFile(context, CROP_PATH, i, str);
    }

    private static File createMediaFile(Context context, String str, int i, String str2) {
        File externalStorageDirectory = Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory() : context.getCacheDir();
        File file = new File(externalStorageDirectory.getAbsolutePath() + str);
        if (!file.exists()) {
            file.mkdirs();
        }
        String str3 = "PictureSelector_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date()) + "";
        switch (i) {
            case 1:
                if (TextUtils.isEmpty(str2)) {
                    str2 = ".JPEG";
                }
                return new File(file, str3 + str2);
            case 2:
                return new File(file, str3 + POST_VIDEO);
            default:
                return null;
        }
    }

    private PictureFileUtils() {
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

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        if (r7 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0051, code lost:
        if (r7 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0054, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0058  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getDataColumn(android.content.Context r7, android.net.Uri r8, java.lang.String r9, java.lang.String[] r10) {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r0 = 0
            android.content.ContentResolver r1 = r7.getContentResolver()     // Catch:{ IllegalArgumentException -> 0x0036, all -> 0x0033 }
            r6 = 0
            r2 = r8
            r4 = r9
            r5 = r10
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ IllegalArgumentException -> 0x0036, all -> 0x0033 }
            if (r7 == 0) goto L_0x002d
            boolean r8 = r7.moveToFirst()     // Catch:{ IllegalArgumentException -> 0x002b }
            if (r8 == 0) goto L_0x002d
            java.lang.String r8 = "_data"
            int r8 = r7.getColumnIndexOrThrow(r8)     // Catch:{ IllegalArgumentException -> 0x002b }
            java.lang.String r8 = r7.getString(r8)     // Catch:{ IllegalArgumentException -> 0x002b }
            if (r7 == 0) goto L_0x002a
            r7.close()
        L_0x002a:
            return r8
        L_0x002b:
            r8 = move-exception
            goto L_0x0038
        L_0x002d:
            if (r7 == 0) goto L_0x0054
        L_0x002f:
            r7.close()
            goto L_0x0054
        L_0x0033:
            r8 = move-exception
            r7 = r0
            goto L_0x0056
        L_0x0036:
            r8 = move-exception
            r7 = r0
        L_0x0038:
            java.lang.String r9 = "PictureFileUtils"
            java.util.Locale r10 = java.util.Locale.getDefault()     // Catch:{ all -> 0x0055 }
            java.lang.String r1 = "getDataColumn: _data - [%s]"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0055 }
            r3 = 0
            java.lang.String r8 = r8.getMessage()     // Catch:{ all -> 0x0055 }
            r2[r3] = r8     // Catch:{ all -> 0x0055 }
            java.lang.String r8 = java.lang.String.format(r10, r1, r2)     // Catch:{ all -> 0x0055 }
            android.util.Log.i(r9, r8)     // Catch:{ all -> 0x0055 }
            if (r7 == 0) goto L_0x0054
            goto L_0x002f
        L_0x0054:
            return r0
        L_0x0055:
            r8 = move-exception
        L_0x0056:
            if (r7 == 0) goto L_0x005b
            r7.close()
        L_0x005b:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luck.picture.lib.tools.PictureFileUtils.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static File getPhotoCacheDir(Context context, File file) {
        String str;
        File cacheDir = context.getCacheDir();
        String name = file.getName();
        if (cacheDir != null) {
            File file2 = new File(cacheDir, DEFAULT_CACHE_DIR);
            if (!file2.mkdirs() && (!file2.exists() || !file2.isDirectory())) {
                return file;
            }
            if (name.endsWith(".webp")) {
                str = System.currentTimeMillis() + ".webp";
            } else {
                str = System.currentTimeMillis() + PictureMimeType.PNG;
            }
            return new File(file2, str);
        }
        return file;
    }

    @SuppressLint({"NewApi"})
    public static String getPath(Context context, Uri uri) {
        Uri uri2 = null;
        if (!(Build.VERSION.SDK_INT >= 19) || !DocumentsContract.isDocumentUri(context, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(context, uri, (String) null, (String[]) null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        } else if (isExternalStorageDocument(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            }
        } else if (isDownloadsDocument(uri)) {
            return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), (String) null, (String[]) null);
        } else if (isMediaDocument(uri)) {
            String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
            String str = split2[0];
            if ("image".equals(str)) {
                uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if (PictureConfig.VIDEO.equals(str)) {
                uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(str)) {
                uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyFile(@androidx.annotation.NonNull java.lang.String r10, @androidx.annotation.NonNull java.lang.String r11) throws java.io.IOException {
        /*
            boolean r0 = r10.equalsIgnoreCase(r11)
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0048 }
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x0048 }
            r2.<init>(r10)     // Catch:{ all -> 0x0048 }
            r1.<init>(r2)     // Catch:{ all -> 0x0048 }
            java.nio.channels.FileChannel r10 = r1.getChannel()     // Catch:{ all -> 0x0048 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0042 }
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x0042 }
            r2.<init>(r11)     // Catch:{ all -> 0x0042 }
            r1.<init>(r2)     // Catch:{ all -> 0x0042 }
            java.nio.channels.FileChannel r11 = r1.getChannel()     // Catch:{ all -> 0x0042 }
            r4 = 0
            long r6 = r10.size()     // Catch:{ all -> 0x003d }
            r3 = r10
            r8 = r11
            r3.transferTo(r4, r6, r8)     // Catch:{ all -> 0x003d }
            r10.close()     // Catch:{ all -> 0x003d }
            if (r10 == 0) goto L_0x0037
            r10.close()
        L_0x0037:
            if (r11 == 0) goto L_0x003c
            r11.close()
        L_0x003c:
            return
        L_0x003d:
            r0 = move-exception
            r9 = r0
            r0 = r10
            r10 = r9
            goto L_0x004a
        L_0x0042:
            r11 = move-exception
            r9 = r0
            r0 = r10
            r10 = r11
            r11 = r9
            goto L_0x004a
        L_0x0048:
            r10 = move-exception
            r11 = r0
        L_0x004a:
            if (r0 == 0) goto L_0x004f
            r0.close()
        L_0x004f:
            if (r11 == 0) goto L_0x0054
            r11.close()
        L_0x0054:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luck.picture.lib.tools.PictureFileUtils.copyFile(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean copyAudioFile(@androidx.annotation.NonNull java.lang.String r9, @androidx.annotation.NonNull java.lang.String r10) throws java.io.IOException {
        /*
            boolean r0 = r9.equalsIgnoreCase(r10)
            if (r0 == 0) goto L_0x0008
            r9 = 0
            return r9
        L_0x0008:
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0046 }
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x0046 }
            r2.<init>(r9)     // Catch:{ all -> 0x0046 }
            r1.<init>(r2)     // Catch:{ all -> 0x0046 }
            java.nio.channels.FileChannel r1 = r1.getChannel()     // Catch:{ all -> 0x0046 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x0044 }
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x0044 }
            r3.<init>(r10)     // Catch:{ all -> 0x0044 }
            r2.<init>(r3)     // Catch:{ all -> 0x0044 }
            java.nio.channels.FileChannel r10 = r2.getChannel()     // Catch:{ all -> 0x0044 }
            r4 = 0
            long r6 = r1.size()     // Catch:{ all -> 0x0042 }
            r3 = r1
            r8 = r10
            r3.transferTo(r4, r6, r8)     // Catch:{ all -> 0x0042 }
            r1.close()     // Catch:{ all -> 0x0042 }
            if (r1 == 0) goto L_0x0038
            r1.close()
        L_0x0038:
            if (r10 == 0) goto L_0x003d
            r10.close()
        L_0x003d:
            boolean r9 = deleteFile(r9)
            return r9
        L_0x0042:
            r0 = r10
            goto L_0x0047
        L_0x0044:
            goto L_0x0047
        L_0x0046:
            r1 = r0
        L_0x0047:
            if (r1 == 0) goto L_0x004c
            r1.close()
        L_0x004c:
            if (r0 == 0) goto L_0x0051
            r0.close()
        L_0x0051:
            boolean r9 = deleteFile(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luck.picture.lib.tools.PictureFileUtils.copyAudioFile(java.lang.String, java.lang.String):boolean");
    }

    public static int readPictureDegree(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            if (attributeInt == 3) {
                return SubsamplingScaleImageView.ORIENTATION_180;
            }
            if (attributeInt == 6) {
                return 90;
            }
            if (attributeInt != 8) {
                return 0;
            }
            return SubsamplingScaleImageView.ORIENTATION_270;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Bitmap rotaingImageView(int i, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        PrintStream printStream = System.out;
        printStream.println("angle2=" + i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static void saveBitmapFile(Bitmap bitmap, File file) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        float f;
        float f2;
        float f3;
        float f4;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            f4 = (float) (width / 2);
            f3 = (float) width;
            f = f3;
            f2 = CropImageView.DEFAULT_ASPECT_RATIO;
        } else {
            f2 = (float) ((width - height) / 2);
            f = (float) height;
            f3 = ((float) width) - f2;
            width = height;
            f4 = (float) (height / 2);
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect((int) f2, (int) CropImageView.DEFAULT_ASPECT_RATIO, (int) f3, (int) f);
        Rect rect2 = new Rect((int) CropImageView.DEFAULT_ASPECT_RATIO, (int) CropImageView.DEFAULT_ASPECT_RATIO, (int) f, (int) f);
        RectF rectF = new RectF(rect2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, f4, f4, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return createBitmap;
    }

    public static String createDir(Context context, String str, String str2) {
        File file;
        File externalStorageDirectory = Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory() : context.getCacheDir();
        if (!TextUtils.isEmpty(str2)) {
            file = new File(externalStorageDirectory.getAbsolutePath() + str2);
        } else {
            file = new File(externalStorageDirectory.getAbsolutePath() + "/PictureSelector");
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file + "/" + str;
    }

    public static int isDamage(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return (options.mCancel || options.outWidth == -1 || options.outHeight == -1) ? -1 : 0;
    }

    public static List<String> getDirFiles(String str) {
        File file = new File(str);
        ArrayList arrayList = new ArrayList();
        if (file.isDirectory()) {
            for (File absolutePath : file.listFiles()) {
                String absolutePath2 = absolutePath.getAbsolutePath();
                if (absolutePath2.endsWith(".jpg") || absolutePath2.endsWith(".jpeg") || absolutePath2.endsWith(PictureMimeType.PNG) || absolutePath2.endsWith(".gif") || absolutePath2.endsWith(".webp")) {
                    arrayList.add(absolutePath2);
                }
            }
        }
        return arrayList;
    }

    public static String getDCIMCameraPath() {
        try {
            return "%" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Camera";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void deleteCacheDirFile(Context context) {
        File cacheDir = context.getCacheDir();
        File file = new File(context.getCacheDir() + "/picture_cache");
        File file2 = new File(context.getCacheDir() + "/luban_disk_cache");
        if (cacheDir != null) {
            for (File file3 : cacheDir.listFiles()) {
                if (file3.isFile()) {
                    file3.delete();
                }
            }
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file4 : listFiles) {
                if (file4.isFile()) {
                    file4.delete();
                }
            }
        }
        File[] listFiles2 = file2.listFiles();
        if (listFiles2 != null) {
            for (File file5 : listFiles2) {
                if (file5.isFile()) {
                    file5.delete();
                }
            }
        }
    }

    public static void deleteExternalCacheDirFile(Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        File file = new File(context.getExternalCacheDir() + "/picture_cache");
        File file2 = new File(context.getExternalCacheDir() + "/luban_disk_cache");
        if (externalCacheDir != null) {
            for (File file3 : externalCacheDir.listFiles()) {
                if (file3.isFile()) {
                    file3.delete();
                }
            }
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file4 : listFiles) {
                if (file4.isFile()) {
                    file4.delete();
                }
            }
        }
        File[] listFiles2 = file2.listFiles();
        if (listFiles2 != null) {
            for (File file5 : listFiles2) {
                if (file5.isFile()) {
                    file5.delete();
                }
            }
        }
    }

    public static boolean deleteFile(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return new File(str).delete();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getDiskCacheDir(Context context) {
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            return context.getExternalCacheDir().getPath();
        }
        return context.getCacheDir().getPath();
    }
}
