package com.wx.assistants.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyBroadUtils {
    public static void hide_keyboard_from(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
    }

    public static void show_keyboard_from(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(view, 1);
    }
}
