package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import java.util.ArrayList;

public class LineScalePulseOutRapidIndicator extends LineScaleIndicator {
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> arrayList = new ArrayList<>();
        for (final int i = 0; i < 5; i++) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.4f, 1.0f});
            ofFloat.setDuration(1000);
            ofFloat.setRepeatCount(-1);
            ofFloat.setStartDelay(new long[]{400, 200, 0, 200, 400}[i]);
            addUpdateListener(ofFloat, new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    LineScalePulseOutRapidIndicator.this.scaleYFloats[i] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    LineScalePulseOutRapidIndicator.this.postInvalidate();
                }
            });
            arrayList.add(ofFloat);
        }
        return arrayList;
    }
}
