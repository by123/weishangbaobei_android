package com.samon.wechatimageslicer.slicer;

import android.net.Uri;
import java.io.File;
import java.util.ArrayList;

public class SharedSlicerListenerManager {
    private static final SharedSlicerListenerManager manager = new SharedSlicerListenerManager();
    private OnSharedSlicerListener listener;

    private SharedSlicerListenerManager() {
    }

    public static SharedSlicerListenerManager getInstance() {
        return manager;
    }

    public void setOnSharedSlicerListener(OnSharedSlicerListener onSharedSlicerListener) {
        this.listener = onSharedSlicerListener;
    }

    public void shared(ArrayList<File> arrayList, ArrayList<Uri> arrayList2) {
        if (this.listener != null) {
            this.listener.shared(arrayList, arrayList2);
        }
    }
}
