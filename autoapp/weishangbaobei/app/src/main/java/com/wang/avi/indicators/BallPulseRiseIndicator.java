package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;
import com.wang.avi.Indicator;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

public class BallPulseRiseIndicator extends Indicator {
    /* access modifiers changed from: private */
    public float degress;
    private Camera mCamera = new Camera();
    private Matrix mMatrix = new Matrix();

    public void draw(Canvas canvas, Paint paint) {
        this.mMatrix.reset();
        this.mCamera.save();
        this.mCamera.rotateX(this.degress);
        this.mCamera.getMatrix(this.mMatrix);
        this.mCamera.restore();
        this.mMatrix.preTranslate((float) (-centerX()), (float) (-centerY()));
        this.mMatrix.postTranslate((float) centerX(), (float) centerY());
        canvas.concat(this.mMatrix);
        float width = (float) (getWidth() / 10);
        float f = 2.0f * width;
        canvas.drawCircle((float) (getWidth() / 4), f, width, paint);
        canvas.drawCircle((float) ((getWidth() * 3) / 4), f, width, paint);
        canvas.drawCircle(width, ((float) getHeight()) - f, width, paint);
        canvas.drawCircle((float) (getWidth() / 2), ((float) getHeight()) - f, width, paint);
        canvas.drawCircle(((float) getWidth()) - width, ((float) getHeight()) - f, width, paint);
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> arrayList = new ArrayList<>();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 360.0f});
        addUpdateListener(ofFloat, new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = BallPulseRiseIndicator.this.degress = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                BallPulseRiseIndicator.this.postInvalidate();
            }
        });
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.setRepeatCount(-1);
        ofFloat.setDuration(1500);
        arrayList.add(ofFloat);
        return arrayList;
    }
}
