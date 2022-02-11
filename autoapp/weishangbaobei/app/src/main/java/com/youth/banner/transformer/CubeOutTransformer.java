package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class CubeOutTransformer extends ABaseTransformer {
    public boolean isPagingEnabled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onTransform(View view, float f) {
        float f2 = CropImageView.DEFAULT_ASPECT_RATIO;
        if (f < CropImageView.DEFAULT_ASPECT_RATIO) {
            f2 = (float) view.getWidth();
        }
        view.setPivotX(f2);
        view.setPivotY(((float) view.getHeight()) * 0.5f);
        view.setRotationY(f * 90.0f);
    }
}
