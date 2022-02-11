package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;
import com.wang.avi.Indicator;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

public class BallScaleMultipleIndicator extends Indicator {
    int[] alphaInts = {255, 255, 255};
    float[] scaleFloats = {1.0f, 1.0f, 1.0f};

    public void draw(Canvas canvas, Paint paint) {
        for (int i = 0; i < 3; i++) {
            paint.setAlpha(this.alphaInts[i]);
            canvas.scale(this.scaleFloats[i], this.scaleFloats[i], (float) (getWidth() / 2), (float) (getHeight() / 2));
            canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), ((float) (getWidth() / 2)) - 4.0f, paint);
        }
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
                    BallScaleMultipleIndicator.this.scaleFloats[i] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    BallScaleMultipleIndicator.this.postInvalidate();
                }
            });
            ofFloat.setStartDelay(jArr[i]);
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{255, 0});
            ofInt.setInterpolator(new LinearInterpolator());
            ofInt.setDuration(1000);
            ofInt.setRepeatCount(-1);
            addUpdateListener(ofInt, new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BallScaleMultipleIndicator.this.alphaInts[i] = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    BallScaleMultipleIndicator.this.postInvalidate();
                }
            });
            ofFloat.setStartDelay(jArr[i]);
            arrayList.add(ofFloat);
            arrayList.add(ofInt);
        }
        return arrayList;
    }
}
