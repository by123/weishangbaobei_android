package com.yongchun.library.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.media.ExifInterface;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CropUtil {
    private static final String SCHEME_CONTENT = "content";
    private static final String SCHEME_FILE = "file";

    public static void closeSilently(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static int getExifRotation(File file) {
        if (file == null) {
            return 0;
        }
        try {
            int attributeInt = new ExifInterface(file.getAbsolutePath()).getAttributeInt("Orientation", 0);
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
        } catch (IOException unused) {
            return 0;
        }
    }

    public static boolean copyExifRotation(File file, File file2) {
        if (file == null || file2 == null) {
            return false;
        }
        try {
            ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
            ExifInterface exifInterface2 = new ExifInterface(file2.getAbsolutePath());
            exifInterface2.setAttribute("Orientation", exifInterface.getAttribute("Orientation"));
            exifInterface2.saveAttributes();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0076, code lost:
        if (r1 != null) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007d, code lost:
        if (r1 != null) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x007f, code lost:
        r1.close();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x0083 */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x008f  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File getFromMediaUri(android.content.Context r9, android.content.ContentResolver r10, android.net.Uri r11) {
        /*
            r0 = 0
            if (r11 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "file"
            java.lang.String r2 = r11.getScheme()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x001a
            java.io.File r9 = new java.io.File
            java.lang.String r10 = r11.getPath()
            r9.<init>(r10)
            return r9
        L_0x001a:
            java.lang.String r1 = "content"
            java.lang.String r2 = r11.getScheme()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0093
            java.lang.String r1 = "_data"
            java.lang.String r2 = "_display_name"
            java.lang.String[] r5 = new java.lang.String[]{r1, r2}
            r6 = 0
            r7 = 0
            r8 = 0
            r3 = r10
            r4 = r11
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ IllegalArgumentException -> 0x0083, SecurityException -> 0x007c }
            if (r1 == 0) goto L_0x0076
            boolean r2 = r1.moveToFirst()     // Catch:{ IllegalArgumentException -> 0x0074, SecurityException -> 0x0072, all -> 0x0070 }
            if (r2 == 0) goto L_0x0076
            java.lang.String r2 = r11.toString()     // Catch:{ IllegalArgumentException -> 0x0074, SecurityException -> 0x0072, all -> 0x0070 }
            java.lang.String r3 = "content://com.google.android.gallery3d"
            boolean r2 = r2.startsWith(r3)     // Catch:{ IllegalArgumentException -> 0x0074, SecurityException -> 0x0072, all -> 0x0070 }
            if (r2 == 0) goto L_0x0052
            java.lang.String r2 = "_display_name"
            int r2 = r1.getColumnIndex(r2)     // Catch:{ IllegalArgumentException -> 0x0074, SecurityException -> 0x0072, all -> 0x0070 }
            goto L_0x0058
        L_0x0052:
            java.lang.String r2 = "_data"
            int r2 = r1.getColumnIndex(r2)     // Catch:{ IllegalArgumentException -> 0x0074, SecurityException -> 0x0072, all -> 0x0070 }
        L_0x0058:
            r3 = -1
            if (r2 == r3) goto L_0x0076
            java.lang.String r2 = r1.getString(r2)     // Catch:{ IllegalArgumentException -> 0x0074, SecurityException -> 0x0072, all -> 0x0070 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ IllegalArgumentException -> 0x0074, SecurityException -> 0x0072, all -> 0x0070 }
            if (r3 != 0) goto L_0x0076
            java.io.File r3 = new java.io.File     // Catch:{ IllegalArgumentException -> 0x0074, SecurityException -> 0x0072, all -> 0x0070 }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x0074, SecurityException -> 0x0072, all -> 0x0070 }
            if (r1 == 0) goto L_0x006f
            r1.close()
        L_0x006f:
            return r3
        L_0x0070:
            r9 = move-exception
            goto L_0x008d
        L_0x0072:
            goto L_0x007d
        L_0x0074:
            r0 = r1
            goto L_0x0083
        L_0x0076:
            if (r1 == 0) goto L_0x0093
            goto L_0x007f
        L_0x0079:
            r9 = move-exception
            r1 = r0
            goto L_0x008d
        L_0x007c:
            r1 = r0
        L_0x007d:
            if (r1 == 0) goto L_0x0093
        L_0x007f:
            r1.close()
            goto L_0x0093
        L_0x0083:
            java.io.File r9 = getFromMediaUriPfd(r9, r10, r11)     // Catch:{ all -> 0x0079 }
            if (r0 == 0) goto L_0x008c
            r0.close()
        L_0x008c:
            return r9
        L_0x008d:
            if (r1 == 0) goto L_0x0092
            r1.close()
        L_0x0092:
            throw r9
        L_0x0093:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yongchun.library.utils.CropUtil.getFromMediaUri(android.content.Context, android.content.ContentResolver, android.net.Uri):java.io.File");
    }

    private static String getTempFilename(Context context) throws IOException {
        return File.createTempFile("image", "tmp", context.getCacheDir()).getAbsolutePath();
    }

    @Nullable
    private static File getFromMediaUriPfd(Context context, ContentResolver contentResolver, Uri uri) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        String tempFilename;
        FileOutputStream fileOutputStream2 = null;
        if (uri == null) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(contentResolver.openFileDescriptor(uri, "r").getFileDescriptor());
            try {
                tempFilename = getTempFilename(context);
                fileOutputStream = new FileOutputStream(tempFilename);
            } catch (IOException unused) {
                fileOutputStream = null;
                closeSilently(fileInputStream);
                closeSilently(fileOutputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                closeSilently(fileInputStream);
                closeSilently(fileOutputStream2);
                throw th;
            }
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        File file = new File(tempFilename);
                        closeSilently(fileInputStream);
                        closeSilently(fileOutputStream);
                        return file;
                    }
                }
            } catch (IOException unused2) {
                closeSilently(fileInputStream);
                closeSilently(fileOutputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                closeSilently(fileInputStream);
                closeSilently(fileOutputStream2);
                throw th;
            }
        } catch (IOException unused3) {
            fileOutputStream = null;
            fileInputStream = null;
            closeSilently(fileInputStream);
            closeSilently(fileOutputStream);
            return null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            closeSilently(fileInputStream);
            closeSilently(fileOutputStream2);
            throw th;
        }
    }
}
