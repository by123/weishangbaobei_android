package com.luck.picture.lib.compress;

import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class Checker {
    private static final String BMP = "bmp";
    private static final String GIF = "gif";
    private static final String JPEG = "jpeg";
    private static final String JPG = "jpg";
    private static final String PNG = "png";
    private static final String WEBP = "webp";
    private static List<String> format = new ArrayList();

    static {
        format.add(JPG);
        format.add(JPEG);
        format.add(PNG);
        format.add(WEBP);
        format.add(GIF);
        format.add(BMP);
    }

    Checker() {
    }

    static String checkSuffix(String str) {
        return TextUtils.isEmpty(str) ? ".jpg" : str.substring(str.lastIndexOf("."), str.length());
    }

    static boolean isImage(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return format.contains(str.substring(str.lastIndexOf(".") + 1, str.length()).toLowerCase());
    }

    static boolean isJPG(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String lowerCase = str.substring(str.lastIndexOf("."), str.length()).toLowerCase();
        return lowerCase.contains(JPG) || lowerCase.contains(JPEG);
    }

    static boolean isNeedCompress(int i, String str) {
        if (i > 0) {
            File file = new File(str);
            return file.exists() && file.length() > ((long) (i << 10));
        }
    }
}
