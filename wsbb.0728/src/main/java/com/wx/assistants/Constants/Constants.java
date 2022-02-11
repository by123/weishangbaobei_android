package com.wx.assistants.Constants;

import android.content.Context;
import android.os.Handler;

public class Constants {
    public static final String CONTENT = "content";
    public static String LHTAG = "LHTAG";
    public static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    public static final int PHOTO_REQUEST_CAREMA = 1;
    public static final int PHOTO_REQUEST_CUT = 3;
    public static final int PHOTO_REQUEST_GALLERY = 2;
    public static final int STATE_LOAD = 2;
    public static final int STATE_LOAD_EMPTY = 4;
    public static final int STATE_LOAD_ERROR = 3;
    public static final int STATE_LOAD_SUCCESS = 5;
    public static final int STATE_UNLOAD = 1;
    public static final int STICKER_BTN_HALF_SIZE = 30;
    public static final String WECHAT_STORAGE = "WebChat_Auto";
    public static Context applicationContext;
    public static Handler handler;
    public static int mainId;
    public static Thread thread;
}
