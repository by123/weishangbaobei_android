package com.yalantis.ucrop.util;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import com.luck.picture.lib.config.PictureConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Locale;

public class FileUtils {
    static final String TAG = "FileUtils";

    private FileUtils() {
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
}
