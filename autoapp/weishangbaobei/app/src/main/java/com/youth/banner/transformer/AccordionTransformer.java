package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class AccordionTransformer extends ABaseTransformer {
    /* access modifiers changed from: protected */
    public void onTransform(View view, float f) {
        view.setPivotX(f < CropImageView.DEFAULT_ASPECT_RATIO ? CropImageView.DEFAULT_ASPECT_RATIO : (float) view.getWidth());
        view.setScaleX(f < CropImageView.DEFAULT_ASPECT_RATIO ? f + 1.0f : 1.0f - f);
    }
}
