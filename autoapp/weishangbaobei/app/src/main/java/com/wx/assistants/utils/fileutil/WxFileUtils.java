package com.wx.assistants.utils.fileutil;

import android.app.Activity;
import android.provider.MediaStore;

public class WxFileUtils {
    private static final String[] IMAGE_PROJECTION = {"_data", "_display_name", "date_added", "_id"};
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_IMAGE_VIDEO = 3;
    public static final int TYPE_VIDEO = 2;
    private static final String[] VIDEO_PROJECTION = {"_data", "_display_name", "date_added", "_id", "duration"};
    private int type = 1;

    public void loadVideo(Activity activity) {
        activity.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, VIDEO_PROJECTION, (String) null, (String[]) null, (String) null);
    }
}
