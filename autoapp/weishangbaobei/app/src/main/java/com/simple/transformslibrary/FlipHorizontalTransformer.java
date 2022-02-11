package com.simple.transformslibrary;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class FlipHorizontalTransformer extends TransformAdapter {
    public void onTransform(View view, float f) {
        float f2 = 180.0f * f;
        view.setTranslationX(((float) view.getWidth()) * (-f));
        view.setAlpha((f2 > 90.0f || f2 < -90.0f) ? CropImageView.DEFAULT_ASPECT_RATIO : 1.0f);
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(((float) view.getHeight()) * 0.5f);
        view.setRotationY(f2);
    }
}
