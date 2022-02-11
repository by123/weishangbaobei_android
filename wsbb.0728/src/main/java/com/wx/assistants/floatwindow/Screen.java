package com.wx.assistants.floatwindow;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Screen {
    public static final int height = 1;
    public static final int width = 0;

    @Retention(RetentionPolicy.SOURCE)
    @interface screenType {
    }
}
