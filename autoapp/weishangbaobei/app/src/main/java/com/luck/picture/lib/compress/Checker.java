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

    Checker() {
    }

    static {
        format.add(JPG);
        format.add(JPEG);
        format.add(PNG);
        format.add(WEBP);
        format.add(GIF);
        format.add(BMP);
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
        if (lowerCase.contains(JPG) || lowerCase.contains(JPEG)) {
            return true;
        }
        return false;
    }

    static String checkSuffix(String str) {
        if (TextUtils.isEmpty(str)) {
            return ".jpg";
        }
        return str.substring(str.lastIndexOf("."), str.length());
    }

    static boolean isNeedCompress(int i, String str) {
        if (i <= 0) {
            return true;
        }
        File file = new File(str);
        if (file.exists() && file.length() > ((long) (i << 10))) {
            return true;
        }
        return false;
    }
}
