package com.wx.assistants.utils;

import com.wx.assistants.application.MyApplication;

public class LogUtils {
    public static void log(String str) {
        try {
            if (MyApplication.isOpenLog) {
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
