package com.ilike.voicerecorder.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import java.util.List;

public class CommonUtils {
    private static final String TAG = "CommonUtils";

    public static boolean isSdcardExist() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    static String getString(Context context, int i) {
        return context.getResources().getString(i);
    }

    public static String getTopActivity(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        return runningTasks != null ? runningTasks.get(0).topActivity.getClassName() : "";
    }

    public static synchronized int getVoiceLineWight(Context context, int i) {
        synchronized (CommonUtils.class) {
            if (i <= 2) {
                int dip2px = dip2px(context, 90.0f);
                return dip2px;
            } else if (i <= 10) {
                int dip2px2 = dip2px(context, (float) ((i * 8) + 90));
                return dip2px2;
            } else {
                int dip2px3 = dip2px(context, (float) (((i / 10) * 10) + 170));
                return dip2px3;
            }
        }
    }

    public static synchronized int getVoiceLineWight2(Context context, int i) {
        synchronized (CommonUtils.class) {
            if (i <= 2) {
                int dip2px = dip2px(context, 60.0f);
                return dip2px;
            } else if (i <= 2 || i > 10) {
                int dip2px2 = dip2px(context, (float) (((i / 10) * 10) + 140));
                return dip2px2;
            } else {
                int dip2px3 = dip2px(context, (float) ((i * 8) + 60));
                return dip2px3;
            }
        }
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
