package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class ForegroundToBackgroundTransformer extends ABaseTransformer {
    /* access modifiers changed from: protected */
    public void onTransform(View view, float f) {
        float height = (float) view.getHeight();
        float width = (float) view.getWidth();
        float f2 = 1.0f;
        if (f <= CropImageView.DEFAULT_ASPECT_RATIO) {
            f2 = Math.abs(1.0f + f);
        }
        float min = min(f2, 0.5f);
        view.setScaleX(min);
        view.setScaleY(min);
        view.setPivotX(width * 0.5f);
        view.setPivotY(height * 0.5f);
        view.setTranslationX(f > CropImageView.DEFAULT_ASPECT_RATIO ? width * f : (-width) * f * 0.25f);
    }
}
