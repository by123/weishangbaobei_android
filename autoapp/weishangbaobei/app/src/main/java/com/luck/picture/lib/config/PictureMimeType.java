package com.luck.picture.lib.config;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import com.luck.picture.lib.R;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.stub.StubApp;
import java.io.File;

public final class PictureMimeType {
    public static final String JPEG = ".JPEG";
    public static final String PNG = ".png";

    public static int ofAll() {
        return 0;
    }

    public static int ofAudio() {
        return 3;
    }

    public static int ofImage() {
        return 1;
    }

    public static int ofVideo() {
        return 2;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int isPictureType(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = 3
            r2 = 2
            r3 = 1
            switch(r0) {
                case -1930021710: goto L_0x016e;
                case -1664118616: goto L_0x0162;
                case -1662382439: goto L_0x0156;
                case -1662095187: goto L_0x014a;
                case -1488379748: goto L_0x0140;
                case -1488003120: goto L_0x0136;
                case -1487394660: goto L_0x012c;
                case -1487018032: goto L_0x0122;
                case -1079884372: goto L_0x0116;
                case -879299344: goto L_0x010b;
                case -879290539: goto L_0x0100;
                case -879272239: goto L_0x00f5;
                case -879267568: goto L_0x00ea;
                case -879258763: goto L_0x00df;
                case -586683234: goto L_0x00d3;
                case -107252314: goto L_0x00c6;
                case -48069494: goto L_0x00b9;
                case 5703450: goto L_0x00ac;
                case 187078282: goto L_0x00a0;
                case 187078669: goto L_0x0094;
                case 187090232: goto L_0x0088;
                case 187099443: goto L_0x007c;
                case 201674286: goto L_0x0070;
                case 1331792072: goto L_0x0063;
                case 1331836736: goto L_0x0056;
                case 1331848029: goto L_0x0049;
                case 1338492737: goto L_0x003d;
                case 1503095341: goto L_0x0031;
                case 1504787571: goto L_0x0025;
                case 1504831518: goto L_0x0019;
                case 2039520277: goto L_0x000c;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x0179
        L_0x000c:
            java.lang.String r0 = "video/x-matroska"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 17
            goto L_0x017a
        L_0x0019:
            java.lang.String r0 = "audio/mpeg"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 21
            goto L_0x017a
        L_0x0025:
            java.lang.String r0 = "audio/lamr"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 29
            goto L_0x017a
        L_0x0031:
            java.lang.String r0 = "audio/3gpp"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 30
            goto L_0x017a
        L_0x003d:
            java.lang.String r0 = "audio/quicktime"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 28
            goto L_0x017a
        L_0x0049:
            java.lang.String r0 = "video/mp4"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 14
            goto L_0x017a
        L_0x0056:
            java.lang.String r0 = "video/avi"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 13
            goto L_0x017a
        L_0x0063:
            java.lang.String r0 = "video/3gp"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 10
            goto L_0x017a
        L_0x0070:
            java.lang.String r0 = "imagex-ms-bmp"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 9
            goto L_0x017a
        L_0x007c:
            java.lang.String r0 = "audio/wav"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 25
            goto L_0x017a
        L_0x0088:
            java.lang.String r0 = "audio/mp4"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 27
            goto L_0x017a
        L_0x0094:
            java.lang.String r0 = "audio/amr"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 24
            goto L_0x017a
        L_0x00a0:
            java.lang.String r0 = "audio/aac"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 26
            goto L_0x017a
        L_0x00ac:
            java.lang.String r0 = "video/mp2ts"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 20
            goto L_0x017a
        L_0x00b9:
            java.lang.String r0 = "video/3gpp2"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 12
            goto L_0x017a
        L_0x00c6:
            java.lang.String r0 = "video/quicktime"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 15
            goto L_0x017a
        L_0x00d3:
            java.lang.String r0 = "audio/x-wav"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 23
            goto L_0x017a
        L_0x00df:
            java.lang.String r0 = "image/png"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 0
            goto L_0x017a
        L_0x00ea:
            java.lang.String r0 = "image/gif"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 6
            goto L_0x017a
        L_0x00f5:
            java.lang.String r0 = "image/bmp"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 7
            goto L_0x017a
        L_0x0100:
            java.lang.String r0 = "image/PNG"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 1
            goto L_0x017a
        L_0x010b:
            java.lang.String r0 = "image/GIF"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 8
            goto L_0x017a
        L_0x0116:
            java.lang.String r0 = "video/x-msvideo"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 16
            goto L_0x017a
        L_0x0122:
            java.lang.String r0 = "image/webp"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 4
            goto L_0x017a
        L_0x012c:
            java.lang.String r0 = "image/jpeg"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 2
            goto L_0x017a
        L_0x0136:
            java.lang.String r0 = "image/WEBP"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 5
            goto L_0x017a
        L_0x0140:
            java.lang.String r0 = "image/JPEG"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 3
            goto L_0x017a
        L_0x014a:
            java.lang.String r0 = "video/webm"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 19
            goto L_0x017a
        L_0x0156:
            java.lang.String r0 = "video/mpeg"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 18
            goto L_0x017a
        L_0x0162:
            java.lang.String r0 = "video/3gpp"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 11
            goto L_0x017a
        L_0x016e:
            java.lang.String r0 = "audio/x-ms-wma"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0179
            r4 = 22
            goto L_0x017a
        L_0x0179:
            r4 = -1
        L_0x017a:
            switch(r4) {
                case 0: goto L_0x0180;
                case 1: goto L_0x0180;
                case 2: goto L_0x0180;
                case 3: goto L_0x0180;
                case 4: goto L_0x0180;
                case 5: goto L_0x0180;
                case 6: goto L_0x0180;
                case 7: goto L_0x0180;
                case 8: goto L_0x0180;
                case 9: goto L_0x0180;
                case 10: goto L_0x017f;
                case 11: goto L_0x017f;
                case 12: goto L_0x017f;
                case 13: goto L_0x017f;
                case 14: goto L_0x017f;
                case 15: goto L_0x017f;
                case 16: goto L_0x017f;
                case 17: goto L_0x017f;
                case 18: goto L_0x017f;
                case 19: goto L_0x017f;
                case 20: goto L_0x017f;
                case 21: goto L_0x017e;
                case 22: goto L_0x017e;
                case 23: goto L_0x017e;
                case 24: goto L_0x017e;
                case 25: goto L_0x017e;
                case 26: goto L_0x017e;
                case 27: goto L_0x017e;
                case 28: goto L_0x017e;
                case 29: goto L_0x017e;
                case 30: goto L_0x017e;
                default: goto L_0x017d;
            }
        L_0x017d:
            return r3
        L_0x017e:
            return r1
        L_0x017f:
            return r2
        L_0x0180:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luck.picture.lib.config.PictureMimeType.isPictureType(java.lang.String):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0029 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002a A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isGif(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = -879299344(0xffffffffcb96f4f0, float:-1.9786208E7)
            r2 = 1
            r3 = 0
            if (r0 == r1) goto L_0x001b
            r1 = -879267568(0xffffffffcb977110, float:-1.984976E7)
            if (r0 == r1) goto L_0x0011
            goto L_0x0025
        L_0x0011:
            java.lang.String r0 = "image/gif"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0025
            r4 = 0
            goto L_0x0026
        L_0x001b:
            java.lang.String r0 = "image/GIF"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0025
            r4 = 1
            goto L_0x0026
        L_0x0025:
            r4 = -1
        L_0x0026:
            switch(r4) {
                case 0: goto L_0x002a;
                case 1: goto L_0x002a;
                default: goto L_0x0029;
            }
        L_0x0029:
            return r3
        L_0x002a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luck.picture.lib.config.PictureMimeType.isGif(java.lang.String):boolean");
    }

    public static boolean isImageGif(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String substring = str.substring(str.lastIndexOf("."), str.length());
        if (substring.startsWith(".gif") || substring.startsWith(".GIF")) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isVideo(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            r1 = 1
            r2 = 0
            switch(r0) {
                case -1664118616: goto L_0x007d;
                case -1662382439: goto L_0x0071;
                case -1662095187: goto L_0x0065;
                case -1079884372: goto L_0x005a;
                case -107252314: goto L_0x004f;
                case -48069494: goto L_0x0044;
                case 5703450: goto L_0x0038;
                case 1331792072: goto L_0x002d;
                case 1331836736: goto L_0x0022;
                case 1331848029: goto L_0x0017;
                case 2039520277: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0088
        L_0x000b:
            java.lang.String r0 = "video/x-matroska"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0088
            r3 = 7
            goto L_0x0089
        L_0x0017:
            java.lang.String r0 = "video/mp4"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0088
            r3 = 4
            goto L_0x0089
        L_0x0022:
            java.lang.String r0 = "video/avi"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0088
            r3 = 3
            goto L_0x0089
        L_0x002d:
            java.lang.String r0 = "video/3gp"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0088
            r3 = 0
            goto L_0x0089
        L_0x0038:
            java.lang.String r0 = "video/mp2ts"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0088
            r3 = 10
            goto L_0x0089
        L_0x0044:
            java.lang.String r0 = "video/3gpp2"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0088
            r3 = 2
            goto L_0x0089
        L_0x004f:
            java.lang.String r0 = "video/quicktime"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0088
            r3 = 5
            goto L_0x0089
        L_0x005a:
            java.lang.String r0 = "video/x-msvideo"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0088
            r3 = 6
            goto L_0x0089
        L_0x0065:
            java.lang.String r0 = "video/webm"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0088
            r3 = 9
            goto L_0x0089
        L_0x0071:
            java.lang.String r0 = "video/mpeg"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0088
            r3 = 8
            goto L_0x0089
        L_0x007d:
            java.lang.String r0 = "video/3gpp"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0088
            r3 = 1
            goto L_0x0089
        L_0x0088:
            r3 = -1
        L_0x0089:
            switch(r3) {
                case 0: goto L_0x008d;
                case 1: goto L_0x008d;
                case 2: goto L_0x008d;
                case 3: goto L_0x008d;
                case 4: goto L_0x008d;
                case 5: goto L_0x008d;
                case 6: goto L_0x008d;
                case 7: goto L_0x008d;
                case 8: goto L_0x008d;
                case 9: goto L_0x008d;
                case 10: goto L_0x008d;
                default: goto L_0x008c;
            }
        L_0x008c:
            return r2
        L_0x008d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.luck.picture.lib.config.PictureMimeType.isVideo(java.lang.String):boolean");
    }

    public static boolean isHttp(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.startsWith("http") || str.startsWith("https");
        }
        return false;
    }

    public static String fileToType(File file) {
        if (file == null) {
            return "image/jpeg";
        }
        String name = file.getName();
        if (name.endsWith(PictureFileUtils.POST_VIDEO) || name.endsWith(".avi") || name.endsWith(".3gpp") || name.endsWith(".3gp") || name.startsWith(".mov")) {
            return "video/mp4";
        }
        if (name.endsWith(".PNG") || name.endsWith(PNG) || name.endsWith(".jpeg") || name.endsWith(".gif") || name.endsWith(".GIF") || name.endsWith(".jpg") || name.endsWith(".webp") || name.endsWith(".WEBP") || name.endsWith(".JPEG") || name.endsWith(".bmp")) {
            return "image/jpeg";
        }
        return (name.endsWith(".mp3") || name.endsWith(".amr") || name.endsWith(".aac") || name.endsWith(".war") || name.endsWith(".flac") || name.endsWith(".lamr")) ? "audio/mpeg" : "image/jpeg";
    }

    public static boolean mimeToEqual(String str, String str2) {
        return isPictureType(str) == isPictureType(str2);
    }

    public static String createImageType(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "image/jpeg";
            }
            String name = new File(str).getName();
            String substring = name.substring(name.lastIndexOf(".") + 1, name.length());
            return "image/" + substring;
        } catch (Exception e) {
            e.printStackTrace();
            return "image/jpeg";
        }
    }

    public static String createVideoType(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "video/mp4";
            }
            String name = new File(str).getName();
            String substring = name.substring(name.lastIndexOf(".") + 1, name.length());
            return "video/" + substring;
        } catch (Exception e) {
            e.printStackTrace();
            return "video/mp4";
        }
    }

    public static int pictureToVideo(String str) {
        if (TextUtils.isEmpty(str)) {
            return 1;
        }
        if (str.startsWith(PictureConfig.VIDEO)) {
            return 2;
        }
        return str.startsWith("audio") ? 3 : 1;
    }

    public static int getLocalVideoDuration(String str) {
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            return Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean isLongImg(LocalMedia localMedia) {
        if (localMedia == null) {
            return false;
        }
        if (localMedia.getHeight() > localMedia.getWidth() * 3) {
            return true;
        }
        return false;
    }

    public static String getLastImgType(String str) {
        try {
            int lastIndexOf = str.lastIndexOf(".");
            if (lastIndexOf <= 0) {
                return PNG;
            }
            String substring = str.substring(lastIndexOf, str.length());
            char c = 65535;
            switch (substring.hashCode()) {
                case 1436279:
                    if (substring.equals(".BMP")) {
                        c = 7;
                        break;
                    }
                    break;
                case 1449755:
                    if (substring.equals(".PNG")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1468055:
                    if (substring.equals(".bmp")) {
                        c = 6;
                        break;
                    }
                    break;
                case 1475827:
                    if (substring.equals(".jpg")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1481531:
                    if (substring.equals(PNG)) {
                        c = 0;
                        break;
                    }
                    break;
                case 44765590:
                    if (substring.equals(".JPEG")) {
                        c = 4;
                        break;
                    }
                    break;
                case 45142218:
                    if (substring.equals(".WEBP")) {
                        c = 5;
                        break;
                    }
                    break;
                case 45750678:
                    if (substring.equals(".jpeg")) {
                        c = 3;
                        break;
                    }
                    break;
                case 46127306:
                    if (substring.equals(".webp")) {
                        c = 8;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    return substring;
                default:
                    return PNG;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return PNG;
        }
    }

    public static String s(Context context, int i) {
        Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        switch (i) {
            case 1:
                return origApplicationContext.getString(R.string.picture_error);
            case 2:
                return origApplicationContext.getString(R.string.picture_video_error);
            case 3:
                return origApplicationContext.getString(R.string.picture_audio_error);
            default:
                return origApplicationContext.getString(R.string.picture_error);
        }
    }
}
