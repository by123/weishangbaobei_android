package com.sevenheaven.segmentcontrol;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.yalantis.ucrop.view.CropImageView;

public class RadiusDrawable extends Drawable {
    private int bottom;
    private int bottomLeftRadius;
    private int bottomRightRadius;
    private int fillColor;
    private final boolean isStroke;
    private int left;
    private final Paint paint;
    private Path path;
    private int right;
    private int strokeColor;
    private int strokeWidth;

    /* renamed from: top  reason: collision with root package name */
    private int f3top;
    private int topLeftRadius;
    private int topRightRadius;

    public RadiusDrawable(int i, boolean z) {
        this.strokeWidth = 0;
        this.strokeColor = -16711681;
        setRadius(i);
        this.paint = new Paint(1);
        this.isStroke = z;
    }

    public RadiusDrawable(boolean z) {
        this(0, z);
    }

    public void draw(Canvas canvas) {
        if (this.fillColor != 0) {
            this.paint.setColor(this.fillColor);
            this.paint.setStyle(Paint.Style.FILL);
            canvas.drawPath(this.path, this.paint);
        }
        if (this.strokeWidth > 0) {
            this.paint.setColor(this.strokeColor);
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeJoin(Paint.Join.MITER);
            this.paint.setStrokeWidth((float) this.strokeWidth);
            canvas.drawPath(this.path, this.paint);
        }
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int i) {
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        this.left = i;
        this.f3top = i2;
        this.right = i3;
        this.bottom = i4;
        if (this.isStroke) {
            int i5 = this.strokeWidth / 2;
            i += i5;
            i2 += i5;
            i3 -= i5;
            i4 -= i5;
        }
        this.path = new Path();
        float f = (float) i2;
        this.path.moveTo((float) (this.topLeftRadius + i), f);
        this.path.lineTo((float) (i3 - this.topRightRadius), f);
        float f2 = (float) i3;
        this.path.arcTo(new RectF((float) (i3 - (this.topRightRadius * 2)), f, f2, (float) ((this.topRightRadius * 2) + i2)), -90.0f, 90.0f);
        this.path.lineTo(f2, (float) (i4 - this.bottomRightRadius));
        float f3 = (float) i4;
        this.path.arcTo(new RectF((float) (i3 - (this.bottomRightRadius * 2)), (float) (i4 - (this.bottomRightRadius * 2)), f2, f3), CropImageView.DEFAULT_ASPECT_RATIO, 90.0f);
        this.path.lineTo((float) (this.bottomLeftRadius + i), f3);
        float f4 = (float) i;
        this.path.arcTo(new RectF(f4, (float) (i4 - (this.bottomLeftRadius * 2)), (float) ((this.bottomLeftRadius * 2) + i), f3), 90.0f, 90.0f);
        this.path.lineTo(f4, (float) (this.topLeftRadius + i2));
        this.path.arcTo(new RectF(f4, f, (float) ((this.topLeftRadius * 2) + i), (float) ((this.topLeftRadius * 2) + i2)), 180.0f, 90.0f);
        this.path.close();
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setFillColor(int i) {
        this.fillColor = i;
    }

    public void setRadius(int i) {
        this.bottomRightRadius = i;
        this.bottomLeftRadius = i;
        this.topRightRadius = i;
        this.topLeftRadius = i;
    }

    public void setRadius(int i, int i2, int i3, int i4) {
        this.topLeftRadius = i;
        this.topRightRadius = i2;
        this.bottomLeftRadius = i3;
        this.bottomRightRadius = i4;
    }

    public void setStrokeColor(int i) {
        this.strokeColor = i;
    }

    public void setStrokeWidth(int i) {
        this.strokeWidth = i;
        setBounds(this.left, this.f3top, this.right, this.bottom);
    }
}
