package com.wx.assistants.floatwindow;

import android.content.Context;
import android.content.Intent;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.wx.assistants.utils.LogUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Rom {
    Rom() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0046 A[SYNTHETIC, Splitter:B:13:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053 A[SYNTHETIC, Splitter:B:22:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    static String getProp(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        Throwable th;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                try {
                    bufferedReader.close();
                    return readLine;
                } catch (IOException e) {
                    LogUtils.log("WS_BABY_Catch_23");
                    e.printStackTrace();
                    return readLine;
                }
            } catch (IOException e2) {
                try {
                    LogUtils.log("WS_BABY_Catch_22");
                    if (bufferedReader != null) {
                    }
                } catch (Throwable th2) {
                    bufferedReader2 = bufferedReader;
                    th = th2;
                    if (bufferedReader2 != null) {
                    }
                    throw th;
                }
            }
        } catch (IOException e3) {
            bufferedReader = null;
            LogUtils.log("WS_BABY_Catch_22");
            if (bufferedReader != null) {
                return null;
            }
            try {
                bufferedReader.close();
                return null;
            } catch (IOException e4) {
                LogUtils.log("WS_BABY_Catch_23");
                e4.printStackTrace();
                return null;
            }
        } catch (Throwable th3) {
            bufferedReader2 = null;
            th = th3;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e5) {
                    LogUtils.log("WS_BABY_Catch_23");
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    static boolean isIntentAvailable(Intent intent, Context context) {
        return intent != null && context.getPackageManager().queryIntentActivities(intent, WXMediaMessage.THUMB_LENGTH_LIMIT).size() > 0;
    }
}
