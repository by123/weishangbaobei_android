package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class StackTransformer extends ABaseTransformer {
    /* access modifiers changed from: protected */
    public void onTransform(View view, float f) {
        float f2 = CropImageView.DEFAULT_ASPECT_RATIO;
        if (f >= CropImageView.DEFAULT_ASPECT_RATIO) {
            f2 = ((float) (-view.getWidth())) * f;
        }
        view.setTranslationX(f2);
    }
}
