package com.wx.assistants.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import com.wx.assistants.activity.LoginActivity;

public class ClipboardUtils {
    public static void setClipboardText(Activity activity, String str) {
        if (str == null || str.equals("")) {
            Intent intent = new Intent(activity, LoginActivity.class);
            intent.putExtra("isNeedBack", true);
            activity.startActivity(intent);
            return;
        }
        ((ClipboardManager) activity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence) null, str));
        ToastUtils.showToast(activity, "复制成功");
    }

    public static void setClipboardText(Activity activity, String str, boolean z) {
        if (str != null && !str.equals("")) {
            ((ClipboardManager) activity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence) null, str));
            ToastUtils.showToast(activity, "复制成功");
        } else if (z) {
            Intent intent = new Intent(activity, LoginActivity.class);
            intent.putExtra("isNeedBack", true);
            activity.startActivity(intent);
        }
    }

    public static void setClipboardTextNoToast(Activity activity, String str) {
        if (str == null || str.equals("")) {
            Intent intent = new Intent(activity, LoginActivity.class);
            intent.putExtra("isNeedBack", true);
            activity.startActivity(intent);
            return;
        }
        ((ClipboardManager) activity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence) null, str));
    }
}
