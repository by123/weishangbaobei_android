package com.ilike.voicerecorder.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import java.util.List;

public class CommonUtils {
    private static final String TAG = "CommonUtils";

    public static int dip2px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    static String getString(Context context, int i) {
        return context.getResources().getString(i);
    }

    public static String getTopActivity(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        return runningTasks != null ? runningTasks.get(0).topActivity.getClassName() : "";
    }

    public static int getVoiceLineWight(Context context, int i) {
        int dip2px;
        synchronized (CommonUtils.class) {
            if (i <= 2) {
                try {
                    dip2px = dip2px(context, 90.0f);
                } catch (Throwable th) {
                    Class<CommonUtils> cls = CommonUtils.class;
                    throw th;
                }
            } else {
                dip2px = i <= 10 ? dip2px(context, (float) ((i * 8) + 90)) : dip2px(context, (float) (((i / 10) * 10) + 170));
            }
        }
        return dip2px;
    }

    public static int getVoiceLineWight2(Context context, int i) {
        int dip2px;
        synchronized (CommonUtils.class) {
            if (i <= 2) {
                try {
                    dip2px = dip2px(context, 60.0f);
                } catch (Throwable th) {
                    Class<CommonUtils> cls = CommonUtils.class;
                    throw th;
                }
            } else {
                dip2px = (i <= 2 || i > 10) ? dip2px(context, (float) (((i / 10) * 10) + 140)) : dip2px(context, (float) ((i * 8) + 60));
            }
        }
        return dip2px;
    }

    public static boolean isSdcardExist() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
