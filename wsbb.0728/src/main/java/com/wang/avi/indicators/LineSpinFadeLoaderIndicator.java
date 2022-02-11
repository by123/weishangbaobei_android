package com.wang.avi.indicators;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.wang.avi.indicators.BallSpinFadeLoaderIndicator;

public class LineSpinFadeLoaderIndicator extends BallSpinFadeLoaderIndicator {
    public void draw(Canvas canvas, Paint paint) {
        float width = (float) (getWidth() / 10);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < 8) {
                canvas.save();
                int width2 = getWidth();
                int height = getHeight();
                double d = (double) i2;
                Double.isNaN(d);
                BallSpinFadeLoaderIndicator.Point circleAt = circleAt(width2, height, (((float) getWidth()) / 2.5f) - width, d * 0.7853981633974483d);
                canvas.translate(circleAt.x, circleAt.y);
                canvas.scale(this.scaleFloats[i2], this.scaleFloats[i2]);
                canvas.rotate((float) (i2 * 45));
                paint.setAlpha(this.alphas[i2]);
                float f = -width;
                canvas.drawRoundRect(new RectF(f, f / 1.5f, width * 1.5f, width / 1.5f), 5.0f, 5.0f, paint);
                canvas.restore();
                i = i2 + 1;
            } else {
                return;
            }
        }
    }
}
