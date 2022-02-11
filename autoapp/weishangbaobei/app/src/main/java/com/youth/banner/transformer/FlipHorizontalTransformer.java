package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class FlipHorizontalTransformer extends ABaseTransformer {
    /* access modifiers changed from: protected */
    public void onTransform(View view, float f) {
        float f2 = f * 180.0f;
        view.setAlpha((f2 > 90.0f || f2 < -90.0f) ? CropImageView.DEFAULT_ASPECT_RATIO : 1.0f);
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(((float) view.getHeight()) * 0.5f);
        view.setRotationY(f2);
    }

    /* access modifiers changed from: protected */
    public void onPostTransform(View view, float f) {
        super.onPostTransform(view, f);
        if (f <= -0.5f || f >= 0.5f) {
            view.setVisibility(4);
        } else {
            view.setVisibility(0);
        }
    }
}
