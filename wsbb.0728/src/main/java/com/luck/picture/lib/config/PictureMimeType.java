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

    public static String fileToType(File file) {
        if (file != null) {
            String name = file.getName();
            if (name.endsWith(PictureFileUtils.POST_VIDEO) || name.endsWith(".avi") || name.endsWith(".3gpp") || name.endsWith(".3gp") || name.startsWith(".mov")) {
                return "video/mp4";
            }
            if (name.endsWith(".PNG") || name.endsWith(PNG) || name.endsWith(".jpeg") || name.endsWith(".gif") || name.endsWith(".GIF") || name.endsWith(".jpg") || name.endsWith(".webp") || name.endsWith(".WEBP") || name.endsWith(".JPEG") || name.endsWith(".bmp")) {
                return "image/jpeg";
            }
            if (name.endsWith(".mp3") || name.endsWith(".amr") || name.endsWith(".aac") || name.endsWith(".war") || name.endsWith(".flac") || name.endsWith(".lamr")) {
                return "audio/mpeg";
            }
        }
        return "image/jpeg";
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

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    public static boolean isGif(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -879299344) {
            if (str.equals("image/GIF")) {
                c = 1;
                switch (c) {
                    case 0:
                    case 1:
                        break;
                }
            }
        } else if (hashCode == -879267568 && str.equals("image/gif")) {
            c = 0;
            switch (c) {
                case 0:
                case 1:
                    return true;
                default:
                    return false;
            }
        }
        c = 65535;
        switch (c) {
            case 0:
            case 1:
                break;
        }
    }

    public static boolean isHttp(String str) {
        return !TextUtils.isEmpty(str) && (str.startsWith("http") || str.startsWith("https"));
    }

    public static boolean isImageGif(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String substring = str.substring(str.lastIndexOf("."), str.length());
        return substring.startsWith(".gif") || substring.startsWith(".GIF");
    }

    public static boolean isLongImg(LocalMedia localMedia) {
        if (localMedia == null) {
            return false;
        }
        return localMedia.getHeight() > localMedia.getWidth() * 3;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    public static int isPictureType(String str) {
        char c;
        switch (str.hashCode()) {
            case -1930021710:
                if (str.equals("audio/x-ms-wma")) {
                    c = 22;
                    break;
                }
            case -1664118616:
                if (str.equals("video/3gpp")) {
                    c = 11;
                    break;
                }
            case -1662382439:
                if (str.equals("video/mpeg")) {
                    c = 18;
                    break;
                }
            case -1662095187:
                if (str.equals("video/webm")) {
                    c = 19;
                    break;
                }
            case -1488379748:
                if (str.equals("image/JPEG")) {
                    c = 3;
                    break;
                }
            case -1488003120:
                if (str.equals("image/WEBP")) {
                    c = 5;
                    break;
                }
            case -1487394660:
                if (str.equals("image/jpeg")) {
                    c = 2;
                    break;
                }
            case -1487018032:
                if (str.equals("image/webp")) {
                    c = 4;
                    break;
                }
            case -1079884372:
                if (str.equals("video/x-msvideo")) {
                    c = 16;
                    break;
                }
            case -879299344:
                if (str.equals("image/GIF")) {
                    c = 8;
                    break;
                }
            case -879290539:
                if (str.equals("image/PNG")) {
                    c = 1;
                    break;
                }
            case -879272239:
                if (str.equals("image/bmp")) {
                    c = 7;
                    break;
                }
            case -879267568:
                if (str.equals("image/gif")) {
                    c = 6;
                    break;
                }
            case -879258763:
                if (str.equals("image/png")) {
                    c = 0;
                    break;
                }
            case -586683234:
                if (str.equals("audio/x-wav")) {
                    c = 23;
                    break;
                }
            case -107252314:
                if (str.equals("video/quicktime")) {
                    c = 15;
                    break;
                }
            case -48069494:
                if (str.equals("video/3gpp2")) {
                    c = 12;
                    break;
                }
            case 5703450:
                if (str.equals("video/mp2ts")) {
                    c = 20;
                    break;
                }
            case 187078282:
                if (str.equals("audio/aac")) {
                    c = 26;
                    break;
                }
            case 187078669:
                if (str.equals("audio/amr")) {
                    c = 24;
                    break;
                }
            case 187090232:
                if (str.equals("audio/mp4")) {
                    c = 27;
                    break;
                }
            case 187099443:
                if (str.equals("audio/wav")) {
                    c = 25;
                    break;
                }
            case 201674286:
                if (str.equals("imagex-ms-bmp")) {
                    c = 9;
                    break;
                }
            case 1331792072:
                if (str.equals("video/3gp")) {
                    c = 10;
                    break;
                }
            case 1331836736:
                if (str.equals("video/avi")) {
                    c = 13;
                    break;
                }
            case 1331848029:
                if (str.equals("video/mp4")) {
                    c = 14;
                    break;
                }
            case 1338492737:
                if (str.equals("audio/quicktime")) {
                    c = 28;
                    break;
                }
            case 1503095341:
                if (str.equals("audio/3gpp")) {
                    c = 30;
                    break;
                }
            case 1504787571:
                if (str.equals("audio/lamr")) {
                    c = 29;
                    break;
                }
            case 1504831518:
                if (str.equals("audio/mpeg")) {
                    c = 21;
                    break;
                }
            case 2039520277:
                if (str.equals("video/x-matroska")) {
                    c = 17;
                    break;
                }
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                return 2;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
                return 3;
            default:
                return 1;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    public static boolean isVideo(String str) {
        char c;
        switch (str.hashCode()) {
            case -1664118616:
                if (str.equals("video/3gpp")) {
                    c = 1;
                    break;
                }
            case -1662382439:
                if (str.equals("video/mpeg")) {
                    c = 8;
                    break;
                }
            case -1662095187:
                if (str.equals("video/webm")) {
                    c = 9;
                    break;
                }
            case -1079884372:
                if (str.equals("video/x-msvideo")) {
                    c = 6;
                    break;
                }
            case -107252314:
                if (str.equals("video/quicktime")) {
                    c = 5;
                    break;
                }
            case -48069494:
                if (str.equals("video/3gpp2")) {
                    c = 2;
                    break;
                }
            case 5703450:
                if (str.equals("video/mp2ts")) {
                    c = 10;
                    break;
                }
            case 1331792072:
                if (str.equals("video/3gp")) {
                    c = 0;
                    break;
                }
            case 1331836736:
                if (str.equals("video/avi")) {
                    c = 3;
                    break;
                }
            case 1331848029:
                if (str.equals("video/mp4")) {
                    c = 4;
                    break;
                }
            case 2039520277:
                if (str.equals("video/x-matroska")) {
                    c = 7;
                    break;
                }
            default:
                c = 65535;
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
            case 9:
            case 10:
                return true;
            default:
                return false;
        }
    }

    public static boolean mimeToEqual(String str, String str2) {
        return isPictureType(str) == isPictureType(str2);
    }

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

    public static int pictureToVideo(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.startsWith(PictureConfig.VIDEO)) {
                return 2;
            }
            if (str.startsWith("audio")) {
                return 3;
            }
        }
        return 1;
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
