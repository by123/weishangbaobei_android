package com.simple.transformslibrary;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public abstract class TransformAdapter implements ViewPager.PageTransformer {
    private boolean debug = true;

    public void onCenterIdle(View view) {
    }

    public void onLeftScorlling(View view, float f) {
    }

    public void onRightScorlling(View view, float f) {
    }

    public void onTransform(View view, float f) {
    }

    public void transformPage(View view, float f) {
        if (f > CropImageView.DEFAULT_ASPECT_RATIO && f <= 1.0f) {
            onRightScorlling(view, f);
        } else if (f < CropImageView.DEFAULT_ASPECT_RATIO && f >= -1.0f) {
            onLeftScorlling(view, f);
        } else if (f == CropImageView.DEFAULT_ASPECT_RATIO) {
            onCenterIdle(view);
        }
        onTransform(view, f);
    }

    public void log(Class<? extends TransformAdapter> cls, String str) {
        if (this.debug) {
            Log.d(cls.getSimpleName(), str);
        }
    }
}
