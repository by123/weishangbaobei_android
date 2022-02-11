package com.luck.picture.lib.model;

import android.util.Log;
import com.luck.picture.lib.entity.LocalMedia;
import java.util.ArrayList;
import java.util.List;

public class LocalVideoLoader {
    private static final int type = 2;

    public interface LocalVideoLoaderListener {
        void videoLoaderComplete(List<LocalMedia> list);
    }

    public static void loadVideo(final List<LocalMedia> list, final LocalVideoLoaderListener localVideoLoaderListener) {
        if (list != null && list.size() != 0) {
            Log.e("wq", "localMediaList size == " + list.size());
            new Thread(new Runnable() {
                public void run() {
                    ArrayList arrayList = new ArrayList();
                    for (LocalMedia localMedia : list) {
                        Log.e("wq", "localMedia type == " + localMedia.getMimeType() + "..duration == " + localMedia.getDuration());
                        if (localMedia.getMimeType() == 2 || localMedia.getDuration() > 0) {
                            arrayList.add(localMedia);
                        }
                    }
                    Log.e("wq", "videoList size == " + arrayList.size());
                    localVideoLoaderListener.videoLoaderComplete(arrayList);
                }
            }).start();
        }
    }
}
