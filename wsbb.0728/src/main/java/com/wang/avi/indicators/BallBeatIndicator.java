package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.wang.avi.Indicator;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

public class BallBeatIndicator extends Indicator {
    public static final int ALPHA = 255;
    public static final float SCALE = 1.0f;
    int[] alphas = {255, 255, 255};
    /* access modifiers changed from: private */
    public float[] scaleFloats = {1.0f, 1.0f, 1.0f};

    public void draw(Canvas canvas, Paint paint) {
        float width = (((float) getWidth()) - 8.0f) / 6.0f;
        float width2 = (float) (getWidth() / 2);
        float f = 2.0f * width;
        float height = (float) (getHeight() / 2);
        for (int i = 0; i < 3; i++) {
            canvas.save();
            float f2 = (float) i;
            canvas.translate((f2 * 4.0f) + (f * f2) + (width2 - (f + 4.0f)), height);
            canvas.scale(this.scaleFloats[i], this.scaleFloats[i]);
            paint.setAlpha(this.alphas[i]);
            canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, width, paint);
            canvas.restore();
        }
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> arrayList = new ArrayList<>();
        int[] iArr = {350, 0, 350};
        for (final int i = 0; i < 3; i++) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.75f, 1.0f});
            ofFloat.setDuration(700);
            ofFloat.setRepeatCount(-1);
            ofFloat.setStartDelay((long) iArr[i]);
            addUpdateListener(ofFloat, new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BallBeatIndicator.this.scaleFloats[i] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    BallBeatIndicator.this.postInvalidate();
                }
            });
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{255, 51, 255});
            ofInt.setDuration(700);
            ofInt.setRepeatCount(-1);
            ofInt.setStartDelay((long) iArr[i]);
            addUpdateListener(ofInt, new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BallBeatIndicator.this.alphas[i] = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    BallBeatIndicator.this.postInvalidate();
                }
            });
            arrayList.add(ofFloat);
            arrayList.add(ofInt);
        }
        return arrayList;
    }
}
