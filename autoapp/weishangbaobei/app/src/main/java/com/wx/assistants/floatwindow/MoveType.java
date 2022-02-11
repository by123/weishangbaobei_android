package com.wx.assistants.floatwindow;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MoveType {
    public static final int active = 2;
    public static final int back = 4;
    public static final int fixed = 0;
    public static final int inactive = 1;
    public static final int slide = 3;

    @Retention(RetentionPolicy.SOURCE)
    @interface MOVE_TYPE {
    }
}
