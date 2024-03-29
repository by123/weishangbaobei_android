package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.wang.avi.Indicator;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

public class SemiCircleSpinIndicator extends Indicator {
    /* access modifiers changed from: private */
    public float degress;

    public void draw(Canvas canvas, Paint paint) {
        canvas.rotate(this.degress, (float) centerX(), (float) centerY());
        canvas.drawArc(new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) getWidth(), (float) getHeight()), -60.0f, 120.0f, false, paint);
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> arrayList = new ArrayList<>();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 180.0f, 360.0f});
        addUpdateListener(ofFloat, new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = SemiCircleSpinIndicator.this.degress = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                SemiCircleSpinIndicator.this.postInvalidate();
            }
        });
        ofFloat.setDuration(600);
        ofFloat.setRepeatCount(-1);
        arrayList.add(ofFloat);
        return arrayList;
    }
}
