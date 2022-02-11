package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class CubeInTransformer extends ABaseTransformer {
    public boolean isPagingEnabled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onTransform(View view, float f) {
        view.setPivotX(f > CropImageView.DEFAULT_ASPECT_RATIO ? CropImageView.DEFAULT_ASPECT_RATIO : (float) view.getWidth());
        view.setPivotY(CropImageView.DEFAULT_ASPECT_RATIO);
        view.setRotationY(f * -90.0f);
    }
}
