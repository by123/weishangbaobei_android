package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import com.wang.avi.Indicator;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

public class BallRotateIndicator extends Indicator {
    float degress;
    private Matrix mMatrix = new Matrix();
    float scaleFloat = 0.5f;

    public void draw(Canvas canvas, Paint paint) {
        float width = (float) (getWidth() / 10);
        float width2 = (float) (getWidth() / 2);
        float height = (float) (getHeight() / 2);
        canvas.rotate(this.degress, (float) centerX(), (float) centerY());
        canvas.save();
        float f = 2.0f * width;
        canvas.translate((width2 - f) - width, height);
        canvas.scale(this.scaleFloat, this.scaleFloat);
        canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, width, paint);
        canvas.restore();
        canvas.save();
        canvas.translate(width2, height);
        canvas.scale(this.scaleFloat, this.scaleFloat);
        canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, width, paint);
        canvas.restore();
        canvas.save();
        canvas.translate(width2 + f + width, height);
        canvas.scale(this.scaleFloat, this.scaleFloat);
        canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, width, paint);
        canvas.restore();
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> arrayList = new ArrayList<>();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.5f, 1.0f, 0.5f});
        ofFloat.setDuration(1000);
        ofFloat.setRepeatCount(-1);
        addUpdateListener(ofFloat, new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BallRotateIndicator.this.scaleFloat = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                BallRotateIndicator.this.postInvalidate();
            }
        });
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 180.0f, 360.0f});
        addUpdateListener(ofFloat2, new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BallRotateIndicator.this.degress = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                BallRotateIndicator.this.postInvalidate();
            }
        });
        ofFloat2.setDuration(1000);
        ofFloat2.setRepeatCount(-1);
        arrayList.add(ofFloat);
        arrayList.add(ofFloat2);
        return arrayList;
    }
}
