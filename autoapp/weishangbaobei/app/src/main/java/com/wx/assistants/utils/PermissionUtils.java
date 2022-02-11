package com.wx.assistants.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import java.util.ArrayList;
import permission.PermissionListener;
import permission.PermissionsOperationUtil;

public class PermissionUtils {
    public static final int READ_CONTACTS = 273;
    public static final int REQUEST_LOAD_IMAGE_PERMISSSION = 0;
    public static final int REQUEST_RECORD_PERMISSSION = 1;

    private PermissionUtils() {
    }

    public static boolean checkLoadImagePermission(Activity activity) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        int checkSelfPermission = activity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        int checkSelfPermission2 = activity.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE");
        ArrayList arrayList = new ArrayList();
        if (checkSelfPermission != 0) {
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        if (checkSelfPermission2 != 0) {
            arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), 0);
        return false;
    }

    public static boolean checkRecordPermission(Activity activity) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        int checkSelfPermission = activity.checkSelfPermission("android.permission.RECORD_AUDIO");
        int checkSelfPermission2 = activity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        ArrayList arrayList = new ArrayList();
        if (checkSelfPermission != 0) {
            arrayList.add("android.permission.RECORD_AUDIO");
        }
        if (checkSelfPermission2 != 0) {
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), 1);
        return false;
    }

    public static void getContactPermissionAndGetMobileContacts(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23 && activity.checkSelfPermission("android.permission.READ_CONTACTS") != 0) {
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.READ_CONTACTS"}, 273);
        }
    }

    public static boolean hasCameraPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.CAMERA") == 0;
    }

    public static boolean hasRecordAudioPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.RECORD_AUDIO") == 0;
    }

    public static boolean hasReadContactsPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.READ_CONTACTS") == 0;
    }

    public static boolean hasReadExternalStoragePermission(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") == 0;
    }

    public static boolean hasWriteExternalStoragePermission(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public static boolean hasExternalStoragePermission(Context context) {
        return hasWriteExternalStoragePermission(context) && hasReadExternalStoragePermission(context);
    }

    public static void checkRecord(Context context, PermissionListener permissionListener) {
        checkPermission(context, permissionListener, "android.permission.RECORD_AUDIO", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    public static void checkReadContact(Context context, PermissionListener permissionListener) {
        checkPermission(context, permissionListener, "android.permission.READ_CONTACTS");
    }

    public static void checkCameraAndExternalStorage(Context context, PermissionListener permissionListener) {
        checkPermission(context, permissionListener, "android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    public static void checkCamera(Context context, PermissionListener permissionListener) {
        checkPermission(context, permissionListener, "android.permission.CAMERA");
    }

    public static void checkReadExternalStorage(Context context, PermissionListener permissionListener) {
        checkPermission(context, permissionListener, "android.permission.READ_EXTERNAL_STORAGE");
    }

    public static void checkWriteExternalStorage(Context context, PermissionListener permissionListener) {
        checkPermission(context, permissionListener, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    public static void checkReadAndWriteExternalStorage(Context context, PermissionListener permissionListener) {
        checkPermission(context, permissionListener, "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    public static void checkCameraExternalStorage(Context context, PermissionListener permissionListener) {
        checkPermission(context, permissionListener, "android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    public static void checkFloatWindow(Context context, PermissionListener permissionListener) {
        checkPermission(context, permissionListener, "android.permission.SYSTEM_ALERT_WINDOW");
    }

    public static void checkAllPermission(Context context, PermissionListener permissionListener) {
        checkPermission(context, permissionListener, "android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.READ_CONTACTS", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    private static void checkPermission(Context context, final PermissionListener permissionListener, String... strArr) {
        PermissionsOperationUtil.requestPermission(context, new PermissionListener() {
            public void permissionGranted(@NonNull String[] strArr) {
                permissionListener.permissionGranted(strArr);
            }

            public void permissionDenied(@NonNull String[] strArr) {
                permissionListener.permissionDenied(strArr);
            }
        }, strArr);
    }
}
