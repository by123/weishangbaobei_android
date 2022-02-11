package com.meiqia.core.a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.yalantis.ucrop.view.CropImageView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public class b {
    private static long a(File file) {
        long j;
        File[] listFiles;
        long j2 = 0;
        if (file != null) {
            if (file.exists()) {
                if (file.isDirectory()) {
                    LinkedList linkedList = new LinkedList();
                    linkedList.add(file);
                    loop0:
                    while (true) {
                        j = j2;
                        while (!linkedList.isEmpty()) {
                            File file2 = (File) linkedList.remove(0);
                            if (file2.exists() && (listFiles = file2.listFiles()) != null && listFiles.length != 0) {
                                j2 = j;
                                for (File file3 : listFiles) {
                                    j2 += file3.length();
                                    if (file3.isDirectory()) {
                                        linkedList.add(file3);
                                    }
                                }
                            }
                        }
                        break loop0;
                    }
                } else {
                    return file.length();
                }
            } else {
                return 0;
            }
        } else {
            j = 0;
        }
        return j;
    }

    public static Bitmap a(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        while (true) {
            float width = (float) bitmap2.getWidth();
            float height = (float) bitmap2.getHeight();
            float f = 1024.0f / (width > height ? width : height);
            if (width <= 1024.0f && height <= 1024.0f) {
                break;
            }
            Matrix matrix = new Matrix();
            matrix.postScale(f, f);
            bitmap2 = Bitmap.createBitmap(bitmap2, 0, 0, (int) width, (int) height, matrix, true);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        int i = 80;
        while (byteArrayOutputStream.toByteArray().length / WXMediaMessage.DESCRIPTION_LENGTH_LIMIT > 150) {
            byteArrayOutputStream.reset();
            bitmap2.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            i -= 20;
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
    }

    private static Bitmap a(String str) {
        long j;
        ExifInterface exifInterface = null;
        try {
            j = a(new File(str));
        } catch (Exception e) {
            j = 0;
        }
        if (j == 0) {
            return null;
        }
        long j2 = j / 1000;
        int i = 4;
        if ((j2 <= 250 && j2 >= 150) || (j2 > 251 && j2 < 1500)) {
            i = 2;
        } else if ((j2 < 1500 || j2 >= 3000) && (j2 < 3000 || j2 > 4500)) {
            i = j2 >= 4500 ? 8 : 1;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = i;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        try {
            exifInterface = new ExifInterface(str);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        int attributeInt = exifInterface != null ? exifInterface.getAttributeInt("Orientation", 1) : 0;
        Matrix matrix = new Matrix();
        matrix.postRotate(attributeInt == 3 ? 180.0f : attributeInt == 6 ? 90.0f : attributeInt == 8 ? 270.0f : CropImageView.DEFAULT_ASPECT_RATIO);
        return Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, true);
    }

    private static void a(Bitmap bitmap, File file) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
        int i = 80;
        while (true) {
            bitmap.compress(compressFormat, i, byteArrayOutputStream);
            if (byteArrayOutputStream.toByteArray().length / WXMediaMessage.DESCRIPTION_LENGTH_LIMIT > 100) {
                byteArrayOutputStream.reset();
                i -= 10;
                compressFormat = Bitmap.CompressFormat.JPEG;
            } else {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    public static void a(File file, File file2) {
        try {
            a(a(a(file.getAbsolutePath())), file2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
