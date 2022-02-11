package com.wx.assistants.utils;

import android.content.ContentValues;
import android.content.Context;
import android.provider.MediaStore;
import android.text.TextUtils;
import java.io.File;

public class ImageUtil {
    public static boolean checkFileExist(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return new File(str).exists();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r0 = r2.lastIndexOf(46);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getExtensionName(java.lang.String r2) {
        /*
            if (r2 == 0) goto L_0x0020
            int r0 = r2.length()
            if (r0 <= 0) goto L_0x0020
            r0 = 46
            int r0 = r2.lastIndexOf(r0)
            r1 = -1
            if (r0 <= r1) goto L_0x0020
            int r1 = r2.length()
            int r1 = r1 + -1
            if (r0 >= r1) goto L_0x0020
            int r0 = r0 + 1
            java.lang.String r2 = r2.substring(r0)
            return r2
        L_0x0020:
            java.lang.String r2 = ""
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.ImageUtil.getExtensionName(java.lang.String):java.lang.String");
    }

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
}
