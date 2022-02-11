package com.wx.assistants.utils;

import android.content.ContentValues;
import android.content.Context;
import android.provider.MediaStore;
import android.text.TextUtils;
import java.io.File;

public class ImageUtil {
    public static void ablumUpdate(Context context, String str) {
        if (!TextUtils.isEmpty(str) && context != null) {
            File file = new File(str);
            if (file.exists() && file.length() != 0) {
                ContentValues contentValues = new ContentValues(2);
                String extensionName = getExtensionName(str);
                StringBuilder sb = new StringBuilder();
                sb.append("image/");
                if (TextUtils.isEmpty(extensionName)) {
                    extensionName = "jpeg";
                }
                sb.append(extensionName);
                contentValues.put("mime_type", sb.toString());
                contentValues.put("_data", str);
                context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            }
        }
    }

    public static boolean checkFileExist(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return new File(str).exists();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r0 = r2.lastIndexOf(46);
     */
    public static String getExtensionName(String str) {
        int lastIndexOf;
        return (str == null || str.length() <= 0 || lastIndexOf <= -1 || lastIndexOf >= str.length() + -1) ? "" : str.substring(lastIndexOf + 1);
    }
}
