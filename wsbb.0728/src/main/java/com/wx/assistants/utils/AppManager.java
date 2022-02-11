package com.wx.assistants.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Process;
import java.util.Iterator;
import java.util.Stack;

public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager instance;

    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ((ActivityManager) context.getSystemService("activity")).restartPackage(context.getPackageName());
            System.exit(0);
            Process.killProcess(Process.myPid());
        } catch (Exception e) {
        }
    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    public Activity currentActivity() {
        try {
            if (activityStack != null) {
                return (Activity) activityStack.lastElement();
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void finishActivity() {
        try {
            Activity activity = (Activity) activityStack.lastElement();
            if (activity != null) {
                activity.finish();
            }
        } catch (Exception e) {
        }
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            try {
                activityStack.remove(activity);
                activity.finish();
            } catch (Exception e) {
            }
        }
    }

    public void finishActivity(Class<?> cls) {
        try {
            Iterator it = activityStack.iterator();
            while (it.hasNext()) {
                Activity activity = (Activity) it.next();
                if (activity.getClass().equals(cls)) {
                    finishActivity(activity);
                }
            }
        } catch (Exception e) {
        }
    }

    public void finishAllActivity() {
        try {
            int size = activityStack.size();
            for (int i = 0; i < size; i++) {
                if (activityStack.get(i) != null) {
                    ((Activity) activityStack.get(i)).finish();
                }
            }
            activityStack.clear();
        } catch (Exception e) {
        }
    }

    public void printAllActivity() {
        try {
            int size = activityStack.size();
            for (int i = 0; i < size; i++) {
                activityStack.get(i);
            }
        } catch (Exception e) {
        }
    }

    public void reStartApp(Context context, String str) {
        try {
            ((AlarmManager) context.getSystemService("alarm")).set(1, System.currentTimeMillis() + 500, PendingIntent.getActivity(context, 0, context.getPackageManager().getLaunchIntentForPackage(str), 1073741824));
            System.exit(0);
        } catch (Exception e) {
        }
    }

    public void removeActivityFromStack(Activity activity) {
        if (activity != null) {
            try {
                activityStack.remove(activity);
            } catch (Exception e) {
            }
        }
    }
}
