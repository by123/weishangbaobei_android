package com.simple.transformslibrary;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class Flip3DTransform extends TransformAdapter {
    public void onRightScorlling(View view, float f) {
        view.setPivotX(CropImageView.DEFAULT_ASPECT_RATIO);
        view.setRotationY(f * 90.0f);
    }

    public void onLeftScorlling(View view, float f) {
        view.setPivotX((float) view.getWidth());
        view.setRotationY(f * 90.0f);
    }
}
