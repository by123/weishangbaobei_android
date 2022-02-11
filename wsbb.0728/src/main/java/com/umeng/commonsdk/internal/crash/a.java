package com.umeng.commonsdk.internal.crash;

import java.io.PrintWriter;
import java.io.StringWriter;

public class a {
    public static String a(Throwable th) {
        String str = null;
        if (th != null) {
            try {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                th.printStackTrace(printWriter);
                for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                    cause.printStackTrace(printWriter);
                }
                str = stringWriter.toString();
                try {
                    printWriter.close();
                    stringWriter.close();
                } catch (Exception e) {
                }
            } catch (Exception e2) {
            }
        }
        return str;
    }
}
