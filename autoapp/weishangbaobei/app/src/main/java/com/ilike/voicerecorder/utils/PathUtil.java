package com.ilike.voicerecorder.utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;

public class PathUtil {
    public static final String filePathName = "/file/";
    public static final String historyPathName = "/chat/";
    public static final String imagePathName = "/image/";
    private static PathUtil instance = null;
    public static final String meetingPathName = "/meeting/";
    public static final String netdiskDownloadPathName = "/netdisk/";
    public static String pathPrefix = "";
    private static File storageDir = null;
    public static final String videoPathName = "/video/";
    public static final String voicePathName = "/voice/";
    private File filePath;
    private File historyPath = null;
    private File imagePath = null;
    private File videoPath = null;
    private File voicePath = null;

    private PathUtil() {
    }

    public static PathUtil getInstance() {
        if (instance == null) {
            instance = new PathUtil();
        }
        return instance;
    }

    public void initDirs(String str, String str2, Context context) {
        String packageName = context.getPackageName();
        pathPrefix = "/Android/data/" + packageName + "/";
        this.voicePath = generateVoicePath(str, str2, context);
        if (!this.voicePath.exists()) {
            this.voicePath.mkdirs();
        }
        this.imagePath = generateImagePath(str, str2, context);
        if (!this.imagePath.exists()) {
            this.imagePath.mkdirs();
        }
        this.historyPath = generateHistoryPath(str, str2, context);
        if (!this.historyPath.exists()) {
            this.historyPath.mkdirs();
        }
        this.videoPath = generateVideoPath(str, str2, context);
        if (!this.videoPath.exists()) {
            this.videoPath.mkdirs();
        }
        this.filePath = generateFiePath(str, str2, context);
        if (!this.filePath.exists()) {
            this.filePath.mkdirs();
        }
    }

    public void createDirs(String str, String str2, Context context) {
        String packageName = context.getPackageName();
        pathPrefix = "/Android/data/" + packageName + "/";
        this.voicePath = generateVoicePath(str, str2, context);
        if (!this.voicePath.exists()) {
            this.voicePath.mkdirs();
        }
    }

    public void initDirs(String str, Context context) {
        String packageName = context.getPackageName();
        pathPrefix = "/Android/data/" + packageName + "/";
        this.voicePath = generateVoicePathForCustom(str, context);
        if (!this.voicePath.exists()) {
            this.voicePath.mkdirs();
        }
    }

    public File getImagePath() {
        return this.imagePath;
    }

    public File getVoicePath() {
        return this.voicePath;
    }

    public File getFilePath() {
        return this.filePath;
    }

    public File getVideoPath() {
        return this.videoPath;
    }

    public File getHistoryPath() {
        return this.historyPath;
    }

    private static File getStorageDir(Context context) {
        if (storageDir == null) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory.exists()) {
                return externalStorageDirectory;
            }
            storageDir = context.getFilesDir();
        }
        return storageDir;
    }

    private static File generateImagePath(String str, String str2, Context context) {
        String str3;
        if (str == null) {
            str3 = pathPrefix + str2 + imagePathName;
        } else {
            str3 = pathPrefix + str + "/" + str2 + imagePathName;
        }
        return new File(getStorageDir(context), str3);
    }

    private static File generateVoicePath(String str, String str2, Context context) {
        String str3;
        if (str == null) {
            str3 = pathPrefix + str2 + voicePathName;
        } else {
            str3 = pathPrefix + str + "/" + str2 + voicePathName;
        }
        return new File(getStorageDir(context), str3);
    }

    private static File generateVoicePathForCustom(String str, Context context) {
        String str2;
        if (str == null) {
            str2 = pathPrefix + voicePathName;
        } else {
            str2 = pathPrefix + str;
        }
        return new File(getStorageDir(context), str2);
    }

    private static File generateFiePath(String str, String str2, Context context) {
        String str3;
        if (str == null) {
            str3 = pathPrefix + str2 + filePathName;
        } else {
            str3 = pathPrefix + str + "/" + str2 + filePathName;
        }
        return new File(getStorageDir(context), str3);
    }

    private static File generateVideoPath(String str, String str2, Context context) {
        String str3;
        if (str == null) {
            str3 = pathPrefix + str2 + videoPathName;
        } else {
            str3 = pathPrefix + str + "/" + str2 + videoPathName;
        }
        return new File(getStorageDir(context), str3);
    }

    private static File generateHistoryPath(String str, String str2, Context context) {
        String str3;
        if (str == null) {
            str3 = pathPrefix + str2 + historyPathName;
        } else {
            str3 = pathPrefix + str + "/" + str2 + historyPathName;
        }
        return new File(getStorageDir(context), str3);
    }

    public static File getTempPath(File file) {
        return new File(file.getAbsoluteFile() + ".tmp");
    }
}
