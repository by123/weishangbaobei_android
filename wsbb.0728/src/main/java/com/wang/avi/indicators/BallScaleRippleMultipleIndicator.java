package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

public class BallScaleRippleMultipleIndicator extends BallScaleMultipleIndicator {
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3.0f);
        super.draw(canvas, paint);
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> arrayList = new ArrayList<>();
        long[] jArr = {0, 200, 400};
        for (final int i = 0; i < 3; i++) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 1.0f});
            ofFloat.setInterpolator(new LinearInterpolator());
            ofFloat.setDuration(1000);
            ofFloat.setRepeatCount(-1);
            addUpdateListener(ofFloat, new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BallScaleRippleMultipleIndicator.this.scaleFloats[i] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    BallScaleRippleMultipleIndicator.this.postInvalidate();
                }
            });
            ofFloat.setStartDelay(jArr[i]);
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, 255});
            ofFloat.setInterpolator(new LinearInterpolator());
            ofInt.setDuration(1000);
            ofInt.setRepeatCount(-1);
            addUpdateListener(ofInt, new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BallScaleRippleMultipleIndicator.this.alphaInts[i] = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    BallScaleRippleMultipleIndicator.this.postInvalidate();
                }
            });
            ofFloat.setStartDelay(jArr[i]);
            arrayList.add(ofFloat);
            arrayList.add(ofInt);
        }
        return arrayList;
    }
}
