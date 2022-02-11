package com.wx.assistants.utils.fileutil;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.stub.StubApp;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.utils.Constant;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

public class FileUtils {
    private static void deleteFile(File file) {
        if (file != null) {
            file.delete();
            updateGallery(file.getAbsolutePath());
        }
    }

    public static void deleteFile(String str) {
        if (!StringUtils.isEmpty(str)) {
            deleteFile(new File(str));
        }
    }

    public static void deleteForward(int i, int i2) {
        if (i2 > 0 && i >= 0 && i <= 1) {
            if (i == 0) {
                try {
                    String str = (String) SPUtils.get(MyApplication.getConText(), "forward_img", "");
                    if (str != null && !"".equals(str)) {
                        if (str.contains("@ws_baby@")) {
                            String[] split = str.split("@ws_baby@");
                            for (String deleteFile : split) {
                                deleteFile(deleteFile);
                            }
                        } else {
                            deleteFile(str);
                        }
                        SPUtils.put(MyApplication.getConText(), "delete_img", "");
                        SPUtils.put(MyApplication.getConText(), "forward_img", "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String str2 = (String) SPUtils.get(MyApplication.getConText(), "forward_img", "");
                if (str2 != null && !"".equals(str2)) {
                    deleteFile(str2);
                    SPUtils.put(MyApplication.getConText(), "delete_img", "");
                    SPUtils.put(MyApplication.getConText(), "forward_img", "");
                }
            }
        }
    }

    public static File getAppDir(Context context) {
        String format;
        boolean equals = "mounted".equals(Environment.getExternalStorageState());
        boolean exists = Environment.getExternalStorageDirectory().exists();
        if (!equals || !exists) {
            format = String.format("%s/%s/", new Object[]{StubApp.getOrigApplicationContext(context.getApplicationContext()).getFilesDir().getAbsolutePath(), Constant.FilePath.ROOT_PATH});
        } else {
            format = String.format("%s/%s/", new Object[]{Environment.getExternalStorageDirectory().getAbsolutePath(), Constant.FilePath.ROOT_PATH});
        }
        File file = new File(format);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getAppRecordDir(Context context) {
        File file = new File(getAppDir(context).getAbsolutePath(), Constant.FilePath.RECORD_DIR);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    public static ArrayList<FileBean> getCopyFiles() {
        File[] listFiles;
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        ArrayList<FileBean> arrayList = new ArrayList<>();
        File file = new File(absolutePath);
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                String name = file2.getName();
                System.out.println("WS_BABY_FILE_NAME." + name);
                if (name.startsWith("WsBaby#_#")) {
                    arrayList.add(new FileBean(name, file2.getAbsolutePath(), getFileSizes(file2)));
                }
            }
        }
        if (arrayList.size() > 0) {
            Collections.sort(arrayList, new Comparator<FileBean>() {
                public int compare(FileBean fileBean, FileBean fileBean2) {
                    return fileBean.getFileName().compareTo(fileBean2.getFileName());
                }
            });
        }
        return arrayList;
    }

    public static String getDataFolderPath(Context context) {
        return Environment.getDataDirectory() + "/data/" + context.getPackageName() + "/files";
    }

    public static File getDiskCacheDir(Context context) {
        return new File(("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) ? context.getExternalCacheDir().getPath() : context.getCacheDir().getPath());
    }

    public static File getDiskCacheDir(Context context, String str) {
        String path = ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) ? context.getExternalCacheDir().getPath() : context.getCacheDir().getPath();
        return new File(path + File.separator + str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0029 A[SYNTHETIC, Splitter:B:18:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0034 A[SYNTHETIC, Splitter:B:26:0x0034] */
    public static long getFileSize(File file) {
        long j;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        long j2 = 0;
        try {
            if (file.exists() && file.isFile()) {
                fileInputStream = new FileInputStream(file);
                try {
                    j2 = (long) fileInputStream.available();
                    fileInputStream2 = fileInputStream;
                } catch (IOException e) {
                    e = e;
                    try {
                        e.printStackTrace();
                        if (fileInputStream != null) {
                        }
                        j = j2;
                        return j;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream2 = fileInputStream;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                    }
                    throw th;
                }
            }
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                    return j2;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return j2;
                }
            }
        } catch (IOException e3) {
            e = e3;
            fileInputStream = null;
            e.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
                j = 0;
                return j;
            }
            j = j2;
            return j;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = fileInputStream2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
        j = j2;
        return j;
    }

    public static long getFileSizes(File file) {
        File[] listFiles;
        long j = 0;
        try {
            if (file.exists() && (listFiles = file.listFiles()) != null) {
                for (File file2 : listFiles) {
                    j += file2.isDirectory() ? getFileSizes(file2) : getFileSize(file2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    public static ArrayList<FileInfoBean> getImageFiles(String str) {
        ArrayList<FileInfoBean> arrayList = new ArrayList<>();
        File file = new File(str);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (!file.isDirectory() || listFiles == null) {
                String name = file.getName();
                String absolutePath = file.getAbsolutePath();
                long fileSizes = getFileSizes(file);
                if (name.startsWith("mmexport") && name.endsWith(".jpg")) {
                    arrayList.add(new FileInfoBean(name, absolutePath, fileSizes));
                }
            } else {
                for (File file2 : listFiles) {
                    String name2 = file2.getName();
                    String absolutePath2 = file2.getAbsolutePath();
                    long fileSizes2 = getFileSizes(file2);
                    if (name2.startsWith("mmexport") && name2.endsWith(".jpg")) {
                        arrayList.add(new FileInfoBean(name2, absolutePath2, fileSizes2));
                    }
                }
            }
        }
        if (arrayList.size() > 0) {
            Collections.sort(arrayList, new Comparator<FileInfoBean>() {
                public int compare(FileInfoBean fileInfoBean, FileInfoBean fileInfoBean2) {
                    return fileInfoBean.getFileName().compareTo(fileInfoBean2.getFileName());
                }
            });
        }
        return arrayList;
    }

    public static ArrayList<FileInfoBean> getMp4Files(String str) {
        ArrayList<FileInfoBean> arrayList = new ArrayList<>();
        File file = new File(str);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (!file.isDirectory() || listFiles == null) {
                String name = file.getName();
                String absolutePath = file.getAbsolutePath();
                long fileSizes = getFileSizes(file);
                if (isStartWithNumber(name) && name.endsWith(PictureFileUtils.POST_VIDEO)) {
                    arrayList.add(new FileInfoBean(name, absolutePath, fileSizes));
                }
            } else {
                for (File file2 : listFiles) {
                    String name2 = file2.getName();
                    String absolutePath2 = file2.getAbsolutePath();
                    long fileSizes2 = getFileSizes(file2);
                    if (isStartWithNumber(name2) && name2.endsWith(PictureFileUtils.POST_VIDEO)) {
                        arrayList.add(new FileInfoBean(name2, absolutePath2, fileSizes2));
                    }
                }
            }
        }
        if (arrayList.size() > 0) {
            Collections.sort(arrayList, new Comparator<FileInfoBean>() {
                public int compare(FileInfoBean fileInfoBean, FileInfoBean fileInfoBean2) {
                    return fileInfoBean.getFileName().compareTo(fileInfoBean2.getFileName());
                }
            });
        }
        return arrayList;
    }

    public static String getMyCacheDir(Context context) {
        return context.getCacheDir().toString();
    }

    public static String getMyFileDir(Context context) {
        return context.getFilesDir().toString();
    }

    public static String getSDPath() {
        return Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory().toString() : Environment.getDataDirectory().toString();
    }

    public static boolean hasSDCardMounted() {
        String externalStorageState = Environment.getExternalStorageState();
        return externalStorageState != null && externalStorageState.equals("mounted");
    }

    public static boolean isStartWithNumber(String str) {
        Pattern compile = Pattern.compile("[0-9]*");
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        sb.append("");
        return compile.matcher(sb.toString()).matches();
    }

    public static String read(Context context, String str) {
        try {
            FileInputStream openFileInput = context.openFileInput(str);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[WXMediaMessage.DESCRIPTION_LENGTH_LIMIT];
            while (true) {
                int read = openFileInput.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    openFileInput.close();
                    byteArrayOutputStream.close();
                    return new String(byteArray);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readRootFile(String str) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(str));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[WXMediaMessage.DESCRIPTION_LENGTH_LIMIT];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                fileInputStream.close();
                byteArrayOutputStream.close();
                return new String(byteArray);
            }
        }
    }

    public static String readSDCard(String str) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(Environment.getExternalStorageDirectory(), str));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[WXMediaMessage.DESCRIPTION_LENGTH_LIMIT];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                fileInputStream.close();
                byteArrayOutputStream.close();
                return new String(byteArray);
            }
        }
    }

    public static void save(Context context, String str, String str2, int i) {
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, i);
            openFileOutput.write(str2.getBytes());
            openFileOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveForward(int i, int i2) {
        if (i2 > 0 && i >= 0 && i <= 1) {
            try {
                String str = getSDPath() + "/tencent/MicroMsg/WeiXin/";
                if (i == 0) {
                    ArrayList<FileInfoBean> imageFiles = getImageFiles(str);
                    StringBuffer stringBuffer = new StringBuffer();
                    if (imageFiles != null && imageFiles.size() > 0) {
                        for (int size = imageFiles.size() - i2; size < imageFiles.size(); size++) {
                            String filePath = imageFiles.get(size).getFilePath();
                            LogUtils.log("WS_BABY_DELETE#" + filePath);
                            if (size != imageFiles.size() - 1) {
                                stringBuffer.append(filePath + "@ws_baby@");
                            } else {
                                stringBuffer.append(filePath);
                            }
                        }
                        SPUtils.put(MyApplication.getConText(), "forward_img", stringBuffer.toString());
                        return;
                    }
                    return;
                }
                ArrayList<FileInfoBean> mp4Files = getMp4Files(str);
                if (mp4Files != null && mp4Files.size() > 0) {
                    String filePath2 = mp4Files.get(mp4Files.size() - i2).getFilePath();
                    LogUtils.log("WS_BABY_DELETE#" + filePath2);
                    SPUtils.put(MyApplication.getConText(), "forward_img", filePath2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveToSDCard(Context context, String str, String str2) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), str));
        fileOutputStream.write(str2.getBytes());
        fileOutputStream.close();
    }

    private static void updateGallery(String str) {
        String[] strArr = {str};
        MediaScannerConnection.scanFile(MyApplication.getConText(), strArr, (String[]) null, new MediaScannerConnection.OnScanCompletedListener() {
            public void onScanCompleted(String str, Uri uri) {
                Log.i("ExternalStorage", "Scanned " + str + ":");
                StringBuilder sb = new StringBuilder();
                sb.append("-> uri=");
                sb.append(uri);
                Log.i("ExternalStorage", sb.toString());
            }
        });
    }
}
