package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.animation.LinearInterpolator;
import com.wang.avi.Indicator;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

public class PacmanIndicator extends Indicator {
    /* access modifiers changed from: private */
    public int alpha;
    /* access modifiers changed from: private */
    public float degrees1;
    /* access modifiers changed from: private */
    public float degrees2;
    /* access modifiers changed from: private */
    public float translateX;

    public void draw(Canvas canvas, Paint paint) {
        drawPacman(canvas, paint);
        drawCircle(canvas, paint);
    }

    private void drawPacman(Canvas canvas, Paint paint) {
        float width = (float) (getWidth() / 2);
        float height = (float) (getHeight() / 2);
        canvas.save();
        canvas.translate(width, height);
        canvas.rotate(this.degrees1);
        paint.setAlpha(255);
        float f = (-width) / 1.7f;
        float f2 = (-height) / 1.7f;
        float f3 = width / 1.7f;
        float f4 = height / 1.7f;
        Canvas canvas2 = canvas;
        Paint paint2 = paint;
        canvas2.drawArc(new RectF(f, f2, f3, f4), CropImageView.DEFAULT_ASPECT_RATIO, 270.0f, true, paint2);
        canvas.restore();
        canvas.save();
        canvas.translate(width, height);
        canvas.rotate(this.degrees2);
        paint.setAlpha(255);
        canvas2.drawArc(new RectF(f, f2, f3, f4), 90.0f, 270.0f, true, paint2);
        canvas.restore();
    }

    private void drawCircle(Canvas canvas, Paint paint) {
        paint.setAlpha(this.alpha);
        canvas.drawCircle(this.translateX, (float) (getHeight() / 2), (float) (getWidth() / 11), paint);
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> arrayList = new ArrayList<>();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{((float) getWidth()) - ((float) (getWidth() / 11)), (float) (getWidth() / 2)});
        ofFloat.setDuration(650);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.setRepeatCount(-1);
        addUpdateListener(ofFloat, new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = PacmanIndicator.this.translateX = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                PacmanIndicator.this.postInvalidate();
            }
        });
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{255, 122});
        ofInt.setDuration(650);
        ofInt.setRepeatCount(-1);
        addUpdateListener(ofInt, new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int unused = PacmanIndicator.this.alpha = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                PacmanIndicator.this.postInvalidate();
            }
        });
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 45.0f, CropImageView.DEFAULT_ASPECT_RATIO});
        ofFloat2.setDuration(650);
        ofFloat2.setRepeatCount(-1);
        addUpdateListener(ofFloat2, new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = PacmanIndicator.this.degrees1 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                PacmanIndicator.this.postInvalidate();
            }
        });
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, -45.0f, CropImageView.DEFAULT_ASPECT_RATIO});
        ofFloat3.setDuration(650);
        ofFloat3.setRepeatCount(-1);
        addUpdateListener(ofFloat3, new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = PacmanIndicator.this.degrees2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                PacmanIndicator.this.postInvalidate();
            }
        });
        arrayList.add(ofFloat);
        arrayList.add(ofInt);
        arrayList.add(ofFloat2);
        arrayList.add(ofFloat3);
        return arrayList;
    }
}
