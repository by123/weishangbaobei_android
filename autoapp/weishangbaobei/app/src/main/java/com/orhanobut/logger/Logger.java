package com.orhanobut.logger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class Logger {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    @NonNull
    private static Printer printer = new LoggerPrinter();

    private Logger() {
    }

    public static void printer(@NonNull Printer printer2) {
        printer = (Printer) Utils.checkNotNull(printer2);
    }

    public static void addLogAdapter(@NonNull LogAdapter logAdapter) {
        printer.addAdapter((LogAdapter) Utils.checkNotNull(logAdapter));
    }

    public static void clearLogAdapters() {
        printer.clearLogAdapters();
    }

    public static Printer t(@Nullable String str) {
        return printer.t(str);
    }

    public static void log(int i, @Nullable String str, @Nullable String str2, @Nullable Throwable th) {
        printer.log(i, str, str2, th);
    }

    public static void d(@NonNull String str, @Nullable Object... objArr) {
        printer.d(str, objArr);
    }

    public static void d(@Nullable Object obj) {
        printer.d(obj);
    }

    public static void e(@NonNull String str, @Nullable Object... objArr) {
        printer.e((Throwable) null, str, objArr);
    }

    public static void e(@Nullable Throwable th, @NonNull String str, @Nullable Object... objArr) {
        printer.e(th, str, objArr);
    }

    public static void i(@NonNull String str, @Nullable Object... objArr) {
        printer.i(str, objArr);
    }

    public static void v(@NonNull String str, @Nullable Object... objArr) {
        printer.v(str, objArr);
    }

    public static void w(@NonNull String str, @Nullable Object... objArr) {
        printer.w(str, objArr);
    }

    public static void wtf(@NonNull String str, @Nullable Object... objArr) {
        printer.wtf(str, objArr);
    }

    public static void json(@Nullable String str) {
        printer.json(str);
    }

    public static void xml(@Nullable String str) {
        printer.xml(str);
    }
}
