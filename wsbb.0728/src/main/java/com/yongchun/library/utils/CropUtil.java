package com.yongchun.library.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
            } catch (Throwable th) {
            }
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
        } catch (IOException e) {
            return false;
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
            if (attributeInt == 8) {
                return SubsamplingScaleImageView.ORIENTATION_270;
            }
            return 0;
        } catch (IOException e) {
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    @Nullable
    public static File getFromMediaUri(Context context, ContentResolver contentResolver, Uri uri) {
        Cursor cursor;
        Cursor cursor2 = null;
        if (uri == null) {
            return null;
        }
        if ("file".equals(uri.getScheme())) {
            return new File(uri.getPath());
        }
        if (!"content".equals(uri.getScheme())) {
            return null;
        }
        try {
            cursor = contentResolver.query(uri, new String[]{"_data", "_display_name"}, (String) null, (String[]) null, (String) null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        int columnIndex = uri.toString().startsWith("content://com.google.android.gallery3d") ? cursor.getColumnIndex("_display_name") : cursor.getColumnIndex("_data");
                        if (columnIndex != -1) {
                            String string = cursor.getString(columnIndex);
                            if (!TextUtils.isEmpty(string)) {
                                File file = new File(string);
                                if (cursor != null) {
                                    cursor.close();
                                }
                                return file;
                            }
                        }
                    }
                } catch (IllegalArgumentException e) {
                } catch (SecurityException e2) {
                    if (cursor == null) {
                    }
                    cursor.close();
                    return null;
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                    }
                    throw th;
                }
            }
            if (cursor == null) {
                return null;
            }
        } catch (IllegalArgumentException e3) {
            cursor = null;
        } catch (SecurityException e4) {
            cursor = null;
            if (cursor == null) {
                return null;
            }
            cursor.close();
            return null;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursor2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        cursor.close();
        return null;
        try {
            File fromMediaUriPfd = getFromMediaUriPfd(context, contentResolver, uri);
            if (cursor == null) {
                return fromMediaUriPfd;
            }
            cursor.close();
            return fromMediaUriPfd;
        } catch (Throwable th3) {
            th = th3;
            cursor2 = cursor;
        }
    }

    @Nullable
    private static File getFromMediaUriPfd(Context context, ContentResolver contentResolver, Uri uri) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        String tempFilename;
        if (uri == null) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(contentResolver.openFileDescriptor(uri, "r").getFileDescriptor());
            try {
                tempFilename = getTempFilename(context);
                fileOutputStream = new FileOutputStream(tempFilename);
            } catch (IOException e) {
                fileOutputStream = null;
                closeSilently(fileInputStream);
                closeSilently(fileOutputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
                closeSilently(fileInputStream);
                closeSilently(fileOutputStream);
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
            } catch (IOException e2) {
                closeSilently(fileInputStream);
                closeSilently(fileOutputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                closeSilently(fileInputStream);
                closeSilently(fileOutputStream);
                throw th;
            }
        } catch (IOException e3) {
            fileInputStream = null;
            fileOutputStream = null;
            closeSilently(fileInputStream);
            closeSilently(fileOutputStream);
            return null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            fileInputStream = null;
            closeSilently(fileInputStream);
            closeSilently(fileOutputStream);
            throw th;
        }
    }

    private static String getTempFilename(Context context) throws IOException {
        return File.createTempFile("image", "tmp", context.getCacheDir()).getAbsolutePath();
    }
}
