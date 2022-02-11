package com.wang.avi.indicators;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.wang.avi.Indicator;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

public class BallGridBeatIndicator extends Indicator {
    public static final int ALPHA = 255;
    int[] alphas = {255, 255, 255, 255, 255, 255, 255, 255, 255};

    public void draw(Canvas canvas, Paint paint) {
        float width = (((float) getWidth()) - 16.0f) / 6.0f;
        float width2 = (float) (getWidth() / 2);
        float f = 2.0f * width;
        float f2 = f + 4.0f;
        float width3 = (float) (getWidth() / 2);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < 3) {
                for (int i3 = 0; i3 < 3; i3++) {
                    canvas.save();
                    float f3 = (float) i3;
                    float f4 = (float) i2;
                    canvas.translate((f3 * 4.0f) + (f * f3) + (width2 - f2), (f4 * 4.0f) + (f * f4) + (width3 - f2));
                    paint.setAlpha(this.alphas[(i2 * 3) + i3]);
                    canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, width, paint);
                    canvas.restore();
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> arrayList = new ArrayList<>();
        for (final int i = 0; i < 9; i++) {
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{255, 168, 255});
            ofInt.setDuration((long) new int[]{960, 930, 1190, 1130, 1340, 940, 1200, 820, 1190}[i]);
            ofInt.setRepeatCount(-1);
            ofInt.setStartDelay((long) new int[]{360, 400, 680, 410, 710, -150, -120, 10, 320}[i]);
            addUpdateListener(ofInt, new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BallGridBeatIndicator.this.alphas[i] = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    BallGridBeatIndicator.this.postInvalidate();
                }
            });
            arrayList.add(ofInt);
        }
        return arrayList;
    }
}
