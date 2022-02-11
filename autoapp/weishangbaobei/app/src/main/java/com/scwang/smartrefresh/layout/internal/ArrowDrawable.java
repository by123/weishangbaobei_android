package com.scwang.smartrefresh.layout.internal;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import com.yalantis.ucrop.view.CropImageView;

public class ArrowDrawable extends PaintDrawable {
    private int mHeight = 0;
    private Path mPath = new Path();
    private int mWidth = 0;

    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        if (!(this.mWidth == width && this.mHeight == height)) {
            int i = (width * 30) / 225;
            this.mPath.reset();
            double d = (double) i;
            double sin = Math.sin(0.7853981633974483d);
            Double.isNaN(d);
            float f = (float) (sin * d);
            double sin2 = Math.sin(0.7853981633974483d);
            Double.isNaN(d);
            int i2 = width / 2;
            float f2 = (float) height;
            this.mPath.moveTo((float) i2, f2);
            float f3 = (float) (height / 2);
            this.mPath.lineTo(CropImageView.DEFAULT_ASPECT_RATIO, f3);
            float f4 = f3 - f;
            this.mPath.lineTo(f, f4);
            int i3 = i / 2;
            float f5 = (float) (i2 - i3);
            float f6 = (f2 - ((float) (d / sin2))) - ((float) i3);
            this.mPath.lineTo(f5, f6);
            this.mPath.lineTo(f5, CropImageView.DEFAULT_ASPECT_RATIO);
            float f7 = (float) (i2 + i3);
            this.mPath.lineTo(f7, CropImageView.DEFAULT_ASPECT_RATIO);
            this.mPath.lineTo(f7, f6);
            float f8 = (float) width;
            this.mPath.lineTo(f8 - f, f4);
            this.mPath.lineTo(f8, f3);
            this.mPath.close();
            this.mWidth = width;
            this.mHeight = height;
        }
        canvas.drawPath(this.mPath, this.mPaint);
    }
}
