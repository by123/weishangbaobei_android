package com.meiqia.meiqiasdk.util;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.meiqia.meiqiasdk.third.photoview.PhotoViewAttacher;
import com.yalantis.ucrop.view.CropImageView;

public class MQBrowserPhotoViewAttacher extends PhotoViewAttacher {
    private boolean isSetTopCrop = false;

    public MQBrowserPhotoViewAttacher(ImageView imageView) {
        super(imageView);
    }

    private void setTopCrop(Drawable drawable) {
        ImageView imageView = getImageView();
        if (imageView != null && drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            Matrix matrix = new Matrix();
            float max = Math.max(((float) getImageViewWidth(imageView)) / ((float) intrinsicWidth), ((float) getImageViewHeight(imageView)) / ((float) intrinsicHeight));
            matrix.postScale(max, max);
            matrix.postTranslate(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
            updateBaseMatrix(matrix);
        }
    }

    public void setIsSetTopCrop(boolean z) {
        this.isSetTopCrop = z;
    }

    public void setUpdateBaseMatrix() {
        ImageView imageView = getImageView();
        if (imageView != null) {
            updateBaseMatrix(imageView.getDrawable());
        }
    }

    /* access modifiers changed from: protected */
    public void updateBaseMatrix(Drawable drawable) {
        if (this.isSetTopCrop) {
            setTopCrop(drawable);
        } else {
            super.updateBaseMatrix(drawable);
        }
    }
}
