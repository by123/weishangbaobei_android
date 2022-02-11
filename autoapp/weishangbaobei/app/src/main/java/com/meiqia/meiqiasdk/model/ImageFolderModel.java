package com.meiqia.meiqiasdk.model;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import java.util.ArrayList;

public class ImageFolderModel {
    public String coverPath;
    private ArrayList<Uri> mImageUri = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private boolean mTakePhotoEnabled;
    public String name;

    public ImageFolderModel(boolean z) {
        this.mTakePhotoEnabled = z;
        if (z) {
            this.mImages.add("");
        }
    }

    public ImageFolderModel(String str, String str2) {
        this.name = str;
        this.coverPath = str2;
    }

    public boolean isTakePhotoEnabled() {
        return this.mTakePhotoEnabled;
    }

    public void addLastImage(String str) {
        this.mImages.add(str);
    }

    public void addLastImageUri(Uri uri) {
        this.mImageUri.add(uri);
    }

    public ArrayList<String> getImages() {
        return this.mImages;
    }

    @TargetApi(29)
    public ArrayList<Uri> getImageUri() {
        return this.mImageUri;
    }

    public int getCount() {
        if (Build.VERSION.SDK_INT >= 29) {
            return this.mImageUri.size();
        }
        return this.mImages.size();
    }
}
