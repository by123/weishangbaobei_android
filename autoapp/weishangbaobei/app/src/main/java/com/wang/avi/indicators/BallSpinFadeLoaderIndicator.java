package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.wang.avi.Indicator;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

public class BallSpinFadeLoaderIndicator extends Indicator {
    public static final int ALPHA = 255;
    public static final float SCALE = 1.0f;
    int[] alphas = {255, 255, 255, 255, 255, 255, 255, 255};
    float[] scaleFloats = {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};

    public void draw(Canvas canvas, Paint paint) {
        float width = (float) (getWidth() / 10);
        for (int i = 0; i < 8; i++) {
            canvas.save();
            int width2 = getWidth();
            int height = getHeight();
            float width3 = ((float) (getWidth() / 2)) - width;
            double d = (double) i;
            Double.isNaN(d);
            Point circleAt = circleAt(width2, height, width3, 0.7853981633974483d * d);
            canvas.translate(circleAt.x, circleAt.y);
            canvas.scale(this.scaleFloats[i], this.scaleFloats[i]);
            paint.setAlpha(this.alphas[i]);
            canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, width, paint);
            canvas.restore();
        }
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> arrayList = new ArrayList<>();
        int[] iArr = {0, 120, 240, 360, 480, 600, 720, 780, 840};
        for (final int i = 0; i < 8; i++) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.4f, 1.0f});
            ofFloat.setDuration(1000);
            ofFloat.setRepeatCount(-1);
            ofFloat.setStartDelay((long) iArr[i]);
            addUpdateListener(ofFloat, new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BallSpinFadeLoaderIndicator.this.scaleFloats[i] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    BallSpinFadeLoaderIndicator.this.postInvalidate();
                }
            });
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{255, 77, 255});
            ofInt.setDuration(1000);
            ofInt.setRepeatCount(-1);
            ofInt.setStartDelay((long) iArr[i]);
            addUpdateListener(ofInt, new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BallSpinFadeLoaderIndicator.this.alphas[i] = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    BallSpinFadeLoaderIndicator.this.postInvalidate();
                }
            });
            arrayList.add(ofFloat);
            arrayList.add(ofInt);
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public Point circleAt(int i, int i2, float f, double d) {
        double d2 = (double) (i / 2);
        double d3 = (double) f;
        double cos = Math.cos(d);
        Double.isNaN(d3);
        Double.isNaN(d2);
        double d4 = (double) (i2 / 2);
        double sin = Math.sin(d);
        Double.isNaN(d3);
        Double.isNaN(d4);
        return new Point((float) (d2 + (cos * d3)), (float) (d4 + (d3 * sin)));
    }

    final class Point {
        public float x;
        public float y;

        public Point(float f, float f2) {
            this.x = f;
            this.y = f2;
        }
    }
}
