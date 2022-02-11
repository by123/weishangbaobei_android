package com.luck.picture.lib.tools;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
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
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.yalantis.ucrop.view.CropImageView;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
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

    private PictureFileUtils() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0049  */
    public static boolean copyAudioFile(@NonNull String str, @NonNull String str2) throws IOException {
        FileChannel fileChannel;
        FileChannel fileChannel2;
        if (str.equalsIgnoreCase(str2)) {
            return false;
        }
        try {
            fileChannel = new FileInputStream(new File(str)).getChannel();
            try {
                fileChannel2 = new FileOutputStream(new File(str2)).getChannel();
                try {
                    fileChannel.transferTo(0, fileChannel.size(), fileChannel2);
                    fileChannel.close();
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                    if (fileChannel2 != null) {
                        fileChannel2.close();
                    }
                    return deleteFile(str);
                } catch (Throwable th) {
                    if (fileChannel != null) {
                    }
                    if (fileChannel2 != null) {
                    }
                    return deleteFile(str);
                }
            } catch (Throwable th2) {
                fileChannel2 = null;
                if (fileChannel != null) {
                    fileChannel.close();
                }
                if (fileChannel2 != null) {
                    fileChannel2.close();
                }
                return deleteFile(str);
            }
        } catch (Throwable th3) {
            fileChannel = null;
            fileChannel2 = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044  */
    public static void copyFile(@NonNull String str, @NonNull String str2) throws IOException {
        FileChannel fileChannel;
        FileChannel fileChannel2;
        if (!str.equalsIgnoreCase(str2)) {
            try {
                fileChannel2 = new FileInputStream(new File(str)).getChannel();
                try {
                    fileChannel = new FileOutputStream(new File(str2)).getChannel();
                    try {
                        fileChannel2.transferTo(0, fileChannel2.size(), fileChannel);
                        fileChannel2.close();
                        if (fileChannel2 != null) {
                            fileChannel2.close();
                        }
                        if (fileChannel != null) {
                            fileChannel.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (fileChannel2 != null) {
                        }
                        if (fileChannel != null) {
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel = null;
                    if (fileChannel2 != null) {
                        fileChannel2.close();
                    }
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileChannel = null;
                fileChannel2 = null;
            }
        }
    }

    public static File createCameraFile(Context context, int i, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            str = CAMERA_PATH;
        }
        return createMediaFile(context, str, i, str2);
    }

    public static File createCropFile(Context context, int i, String str) {
        return createMediaFile(context, CROP_PATH, i, str);
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

    public static String getDCIMCameraPath() {
        try {
            return "%" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Camera";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r6v0 */
    /* JADX WARNING: type inference failed for: r6v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r6v2 */
    /* JADX WARNING: type inference failed for: r6v5 */
    /* JADX WARNING: type inference failed for: r6v6 */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        if (r1 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004e, code lost:
        if (r1 == null) goto L_0x002c;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0055  */
    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor;
        ? r6 = 0;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, (String) null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                        r6 = string;
                        if (cursor != null) {
                            cursor.close();
                            r6 = string;
                        }
                        return r6;
                    }
                } catch (IllegalArgumentException e) {
                    e = e;
                    try {
                        Log.i(TAG, String.format(Locale.getDefault(), "getDataColumn: _data - [%s]", new Object[]{e.getMessage()}));
                    } catch (Throwable th) {
                        th = th;
                        r6 = cursor;
                        if (r6 != 0) {
                            r6.close();
                        }
                        throw th;
                    }
                }
            }
        } catch (IllegalArgumentException e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (r6 != 0) {
            }
            throw th;
        }
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

    public static String getDiskCacheDir(Context context) {
        return ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) ? context.getExternalCacheDir().getPath() : context.getCacheDir().getPath();
    }

    @SuppressLint({"NewApi"})
    public static String getPath(Context context, Uri uri) {
        Uri uri2 = null;
        if (!(Build.VERSION.SDK_INT >= 19) || !DocumentsContract.isDocumentUri(context, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                return isGooglePhotosUri(uri) ? uri.getLastPathSegment() : getDataColumn(context, uri, (String) null, (String[]) null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
            return null;
        } else if (isExternalStorageDocument(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(":");
            if (!"primary".equalsIgnoreCase(split[0])) {
                return null;
            }
            return Environment.getExternalStorageDirectory() + "/" + split[1];
        } else if (isDownloadsDocument(uri)) {
            return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), (String) null, (String[]) null);
        } else if (!isMediaDocument(uri)) {
            return null;
        } else {
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
        } else if (!Log.isLoggable(TAG, 6)) {
            return file;
        } else {
            Log.e(TAG, "default disk cache dir is null");
            return file;
        }
    }

    public static int isDamage(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return (options.mCancel || options.outWidth == -1 || options.outHeight == -1) ? -1 : 0;
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
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
            f = (float) (width / 2);
            float f5 = (float) width;
            f3 = f5;
            f2 = 0.0f;
            f4 = f5;
        } else {
            f = (float) (height / 2);
            f2 = (float) ((width - height) / 2);
            f3 = (float) height;
            f4 = ((float) width) - f2;
            width = height;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect((int) f2, (int) CropImageView.DEFAULT_ASPECT_RATIO, (int) f4, (int) f3);
        Rect rect2 = new Rect((int) CropImageView.DEFAULT_ASPECT_RATIO, (int) CropImageView.DEFAULT_ASPECT_RATIO, (int) f3, (int) f3);
        RectF rectF = new RectF(rect2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return createBitmap;
    }
}
