package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class ZoomInTransformer extends ABaseTransformer {
    /* access modifiers changed from: protected */
    public void onTransform(View view, float f) {
        float abs = f < CropImageView.DEFAULT_ASPECT_RATIO ? f + 1.0f : Math.abs(1.0f - f);
        view.setScaleX(abs);
        view.setScaleY(abs);
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(((float) view.getHeight()) * 0.5f);
        view.setAlpha(f >= -1.0f ? f > 1.0f ? 0.0f : 1.0f - (abs - 1.0f) : 0.0f);
    }
}
