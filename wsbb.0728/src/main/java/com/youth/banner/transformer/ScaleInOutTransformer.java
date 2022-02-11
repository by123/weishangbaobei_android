package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class ScaleInOutTransformer extends ABaseTransformer {
    /* access modifiers changed from: protected */
    public void onTransform(View view, float f) {
        view.setPivotX(f < CropImageView.DEFAULT_ASPECT_RATIO ? 0.0f : (float) view.getWidth());
        view.setPivotY(((float) view.getHeight()) / 2.0f);
        float f2 = f < CropImageView.DEFAULT_ASPECT_RATIO ? f + 1.0f : 1.0f - f;
        view.setScaleX(f2);
        view.setScaleY(f2);
    }
}
