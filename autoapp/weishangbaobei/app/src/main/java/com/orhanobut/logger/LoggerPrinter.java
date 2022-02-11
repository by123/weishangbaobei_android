package com.orhanobut.logger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class LoggerPrinter implements Printer {
    private static final int JSON_INDENT = 2;
    private final ThreadLocal<String> localTag = new ThreadLocal<>();
    private final List<LogAdapter> logAdapters = new ArrayList();

    LoggerPrinter() {
    }

    public Printer t(String str) {
        if (str != null) {
            this.localTag.set(str);
        }
        return this;
    }

    public void d(@NonNull String str, @Nullable Object... objArr) {
        log(3, (Throwable) null, str, objArr);
    }

    public void d(@Nullable Object obj) {
        log(3, (Throwable) null, Utils.toString(obj), new Object[0]);
    }

    public void e(@NonNull String str, @Nullable Object... objArr) {
        e((Throwable) null, str, objArr);
    }

    public void e(@Nullable Throwable th, @NonNull String str, @Nullable Object... objArr) {
        log(6, th, str, objArr);
    }

    public void w(@NonNull String str, @Nullable Object... objArr) {
        log(5, (Throwable) null, str, objArr);
    }

    public void i(@NonNull String str, @Nullable Object... objArr) {
        log(4, (Throwable) null, str, objArr);
    }

    public void v(@NonNull String str, @Nullable Object... objArr) {
        log(2, (Throwable) null, str, objArr);
    }

    public void wtf(@NonNull String str, @Nullable Object... objArr) {
        log(7, (Throwable) null, str, objArr);
    }

    public void json(@Nullable String str) {
        if (Utils.isEmpty(str)) {
            d("Empty/Null json content");
            return;
        }
        try {
            String trim = str.trim();
            if (trim.startsWith("{")) {
                d(new JSONObject(trim).toString(2));
            } else if (trim.startsWith("[")) {
                d(new JSONArray(trim).toString(2));
            } else {
                e("Invalid Json", new Object[0]);
            }
        } catch (JSONException unused) {
            e("Invalid Json", new Object[0]);
        }
    }

    public void xml(@Nullable String str) {
        if (Utils.isEmpty(str)) {
            d("Empty/Null xml content");
            return;
        }
        try {
            StreamSource streamSource = new StreamSource(new StringReader(str));
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            newTransformer.transform(streamSource, streamResult);
            d(streamResult.getWriter().toString().replaceFirst(">", ">\n"));
        } catch (TransformerException unused) {
            e("Invalid xml", new Object[0]);
        }
    }

    public synchronized void log(int i, @Nullable String str, @Nullable String str2, @Nullable Throwable th) {
        if (!(th == null || str2 == null)) {
            try {
                str2 = str2 + " : " + Utils.getStackTraceString(th);
            } catch (Throwable th2) {
                throw th2;
            }
        }
        if (th != null && str2 == null) {
            str2 = Utils.getStackTraceString(th);
        }
        if (Utils.isEmpty(str2)) {
            str2 = "Empty/NULL log message";
        }
        for (LogAdapter next : this.logAdapters) {
            if (next.isLoggable(i, str)) {
                next.log(i, str, str2);
            }
        }
    }

    public void clearLogAdapters() {
        this.logAdapters.clear();
    }

    public void addAdapter(@NonNull LogAdapter logAdapter) {
        this.logAdapters.add(Utils.checkNotNull(logAdapter));
    }

    private synchronized void log(int i, @Nullable Throwable th, @NonNull String str, @Nullable Object... objArr) {
        Utils.checkNotNull(str);
        log(i, getTag(), createMessage(str, objArr), th);
    }

    @Nullable
    private String getTag() {
        String str = this.localTag.get();
        if (str == null) {
            return null;
        }
        this.localTag.remove();
        return str;
    }

    @NonNull
    private String createMessage(@NonNull String str, @Nullable Object... objArr) {
        return (objArr == null || objArr.length == 0) ? str : String.format(str, objArr);
    }
}
