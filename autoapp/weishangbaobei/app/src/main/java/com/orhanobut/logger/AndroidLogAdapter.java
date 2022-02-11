package com.orhanobut.logger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class AndroidLogAdapter implements LogAdapter {
    @NonNull
    private final FormatStrategy formatStrategy;

    public boolean isLoggable(int i, @Nullable String str) {
        return true;
    }

    public AndroidLogAdapter() {
        this.formatStrategy = PrettyFormatStrategy.newBuilder().build();
    }

    public AndroidLogAdapter(@NonNull FormatStrategy formatStrategy2) {
        this.formatStrategy = (FormatStrategy) Utils.checkNotNull(formatStrategy2);
    }

    public void log(int i, @Nullable String str, @NonNull String str2) {
        this.formatStrategy.log(i, str, str2);
    }
}
