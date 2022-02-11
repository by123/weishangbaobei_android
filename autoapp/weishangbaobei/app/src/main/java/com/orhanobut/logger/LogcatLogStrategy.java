package com.orhanobut.logger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class LogcatLogStrategy implements LogStrategy {
    static final String DEFAULT_TAG = "NO_TAG";

    public void log(int i, @Nullable String str, @NonNull String str2) {
        Utils.checkNotNull(str2);
        if (str == null) {
            str = DEFAULT_TAG;
        }
        Log.println(i, str, str2);
    }
}
