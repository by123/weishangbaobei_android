package com.youth.banner.loader;

import android.content.Context;
import android.widget.ImageView;

public abstract class ImageLoader implements ImageLoaderInterface<ImageView> {
    public ImageView createImageView(Context context) {
        return new ImageView(context);
    }
}
