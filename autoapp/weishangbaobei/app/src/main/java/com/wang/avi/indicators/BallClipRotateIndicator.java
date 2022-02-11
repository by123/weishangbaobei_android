package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.wang.avi.Indicator;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

public class BallClipRotateIndicator extends Indicator {
    float degrees;
    float scaleFloat = 1.0f;

    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3.0f);
        float width = (float) (getWidth() / 2);
        float height = (float) (getHeight() / 2);
        canvas.translate(width, height);
        canvas.scale(this.scaleFloat, this.scaleFloat);
        canvas.rotate(this.degrees);
        canvas.drawArc(new RectF((-width) + 12.0f, (-height) + 12.0f, (width + CropImageView.DEFAULT_ASPECT_RATIO) - 12.0f, (height + CropImageView.DEFAULT_ASPECT_RATIO) - 12.0f), -45.0f, 270.0f, false, paint);
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> arrayList = new ArrayList<>();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.6f, 0.5f, 1.0f});
        ofFloat.setDuration(750);
        ofFloat.setRepeatCount(-1);
        addUpdateListener(ofFloat, new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BallClipRotateIndicator.this.scaleFloat = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                BallClipRotateIndicator.this.postInvalidate();
            }
        });
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 180.0f, 360.0f});
        ofFloat2.setDuration(750);
        ofFloat2.setRepeatCount(-1);
        addUpdateListener(ofFloat2, new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BallClipRotateIndicator.this.degrees = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                BallClipRotateIndicator.this.postInvalidate();
            }
        });
        arrayList.add(ofFloat);
        arrayList.add(ofFloat2);
        return arrayList;
    }
}
