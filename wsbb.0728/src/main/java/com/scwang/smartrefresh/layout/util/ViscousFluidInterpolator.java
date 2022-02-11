package com.scwang.smartrefresh.layout.util;

import android.view.animation.Interpolator;
import com.yalantis.ucrop.view.CropImageView;

public class ViscousFluidInterpolator implements Interpolator {
    private static final float VISCOUS_FLUID_NORMALIZE = (1.0f / viscousFluid(1.0f));
    private static final float VISCOUS_FLUID_OFFSET = (1.0f - (VISCOUS_FLUID_NORMALIZE * viscousFluid(1.0f)));
    private static final float VISCOUS_FLUID_SCALE = 8.0f;

    private static float viscousFluid(float f) {
        float f2 = VISCOUS_FLUID_SCALE * f;
        return f2 < 1.0f ? f2 - (1.0f - ((float) Math.exp((double) (-f2)))) : ((1.0f - ((float) Math.exp((double) (1.0f - f2)))) * 0.63212055f) + 0.36787945f;
    }

    public float getInterpolation(float f) {
        float viscousFluid = VISCOUS_FLUID_NORMALIZE * viscousFluid(f);
        return viscousFluid > CropImageView.DEFAULT_ASPECT_RATIO ? viscousFluid + VISCOUS_FLUID_OFFSET : viscousFluid;
    }
}
