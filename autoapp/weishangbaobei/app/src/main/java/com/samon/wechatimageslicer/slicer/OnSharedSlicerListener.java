package com.samon.wechatimageslicer.slicer;

import android.net.Uri;
import java.io.File;
import java.util.ArrayList;

public interface OnSharedSlicerListener {
    void shared(ArrayList<File> arrayList, ArrayList<Uri> arrayList2);
}
