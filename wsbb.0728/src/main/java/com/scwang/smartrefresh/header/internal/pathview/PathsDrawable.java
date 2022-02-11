package com.scwang.smartrefresh.header.internal.pathview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.support.annotation.NonNull;
import com.scwang.smartrefresh.layout.internal.PaintDrawable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PathsDrawable extends PaintDrawable {
    protected static final Region MAX_CLIP = new Region(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected static final Region REGION = new Region();
    private boolean mCacheDirty;
    private Bitmap mCachedBitmap;
    protected List<Integer> mColors;
    protected int mHeight = 1;
    protected int mOriginHeight;
    protected int mOriginWidth;
    protected List<Path> mPaths;
    protected int mStartX = 0;
    protected int mStartY = 0;
    protected int mWidth = 1;
    protected List<Path> mltOriginPath;
    protected List<String> mltOriginSvg;

    private void createCachedBitmapIfNeeded(int i, int i2) {
        if (this.mCachedBitmap == null || i != this.mCachedBitmap.getWidth() || i2 != this.mCachedBitmap.getHeight()) {
            this.mCachedBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            this.mCacheDirty = true;
        }
    }

    private void drawCachedBitmap(Canvas canvas) {
        canvas.translate((float) (-this.mStartX), (float) (-this.mStartY));
        if (this.mPaths != null) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.mPaths.size()) {
                    if (this.mColors != null && i2 < this.mColors.size()) {
                        this.mPaint.setColor(this.mColors.get(i2).intValue());
                    }
                    canvas.drawPath(this.mPaths.get(i2), this.mPaint);
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    public void declareOriginal(int i, int i2, int i3, int i4) {
        this.mStartX = i;
        this.mStartY = i2;
        this.mWidth = i3;
        this.mOriginWidth = i3;
        this.mHeight = i4;
        this.mOriginHeight = i4;
        Rect bounds = getBounds();
        super.setBounds(bounds.left, bounds.top, bounds.left + i3, bounds.top + i4);
    }

    public void draw(@NonNull Canvas canvas) {
        int i = 0;
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        if (this.mPaint.getAlpha() == 255) {
            canvas.save();
            canvas.translate((float) (bounds.left - this.mStartX), (float) (bounds.top - this.mStartY));
            if (this.mPaths != null) {
                while (true) {
                    int i2 = i;
                    if (i2 >= this.mPaths.size()) {
                        break;
                    }
                    if (this.mColors != null && i2 < this.mColors.size()) {
                        this.mPaint.setColor(this.mColors.get(i2).intValue());
                    }
                    canvas.drawPath(this.mPaths.get(i2), this.mPaint);
                    i = i2 + 1;
                }
                this.mPaint.setAlpha(255);
            }
            canvas.restore();
            return;
        }
        createCachedBitmapIfNeeded(width, height);
        if (this.mCacheDirty) {
            this.mCachedBitmap.eraseColor(0);
            drawCachedBitmap(new Canvas(this.mCachedBitmap));
            this.mCacheDirty = false;
        }
        canvas.drawBitmap(this.mCachedBitmap, (float) bounds.left, (float) bounds.top, this.mPaint);
    }

    /* access modifiers changed from: protected */
    public boolean onMeasure() {
        Integer num;
        Integer num2;
        Integer num3;
        Integer num4;
        Integer num5 = null;
        if (this.mPaths != null) {
            Iterator<Path> it = this.mPaths.iterator();
            num4 = null;
            num3 = null;
            num2 = null;
            while (true) {
                num = num5;
                if (!it.hasNext()) {
                    break;
                }
                REGION.setPath(it.next(), MAX_CLIP);
                Rect bounds = REGION.getBounds();
                num4 = Integer.valueOf(Math.min(num4 == null ? bounds.top : num4.intValue(), bounds.top));
                num3 = Integer.valueOf(Math.min(num3 == null ? bounds.left : num3.intValue(), bounds.left));
                num2 = Integer.valueOf(Math.max(num2 == null ? bounds.right : num2.intValue(), bounds.right));
                num5 = Integer.valueOf(Math.max(num == null ? bounds.bottom : num.intValue(), bounds.bottom));
            }
        } else {
            num3 = null;
            num4 = null;
            num2 = null;
            num = null;
        }
        this.mStartX = num3 == null ? 0 : num3.intValue();
        this.mStartY = num4 == null ? 0 : num4.intValue();
        this.mWidth = num2 == null ? 0 : num2.intValue() - this.mStartX;
        this.mHeight = num == null ? 0 : num.intValue() - this.mStartY;
        if (this.mOriginWidth == 0) {
            this.mOriginWidth = this.mWidth;
        }
        if (this.mOriginHeight == 0) {
            this.mOriginHeight = this.mHeight;
        }
        Rect bounds2 = getBounds();
        if (this.mWidth == 0 || this.mHeight == 0) {
            if (this.mOriginWidth == 0) {
                this.mOriginWidth = 1;
            }
            if (this.mOriginHeight == 0) {
                this.mOriginHeight = 1;
            }
            this.mHeight = 1;
            this.mWidth = 1;
            return false;
        }
        super.setBounds(bounds2.left, bounds2.top, bounds2.left + this.mWidth, bounds2.top + this.mHeight);
        return true;
    }

    public void parserColors(int... iArr) {
        this.mColors = new ArrayList();
        for (int valueOf : iArr) {
            this.mColors.add(Integer.valueOf(valueOf));
        }
    }

    public boolean parserPaths(String... strArr) {
        this.mOriginHeight = 0;
        this.mOriginWidth = 0;
        this.mltOriginSvg = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.mltOriginPath = arrayList;
        this.mPaths = arrayList;
        for (String str : strArr) {
            this.mltOriginSvg.add(str);
            this.mltOriginPath.add(PathParser.createPathFromPathData(str));
        }
        return onMeasure();
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        if (this.mltOriginPath == null || this.mltOriginPath.size() <= 0 || (i5 == this.mWidth && i6 == this.mHeight)) {
            super.setBounds(i, i2, i3, i4);
            return;
        }
        int i7 = this.mStartX;
        int i8 = this.mStartY;
        float f = (float) i5;
        float f2 = (float) i6;
        this.mPaths = PathParser.transformScale((f * 1.0f) / ((float) this.mOriginWidth), (f2 * 1.0f) / ((float) this.mOriginHeight), this.mltOriginPath, this.mltOriginSvg);
        if (!onMeasure()) {
            this.mWidth = i5;
            this.mHeight = i6;
            this.mStartX = (int) (((((float) i7) * 1.0f) * f) / ((float) this.mOriginWidth));
            this.mStartY = (int) (((((float) i8) * 1.0f) * f2) / ((float) this.mOriginHeight));
            super.setBounds(i, i2, i3, i4);
        }
    }

    public void setBounds(@NonNull Rect rect) {
        setBounds(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void setGeometricHeight(int i) {
        Rect bounds = getBounds();
        float height = (((float) i) * 1.0f) / ((float) bounds.height());
        setBounds((int) (((float) bounds.left) * height), (int) (((float) bounds.top) * height), (int) (((float) bounds.right) * height), (int) (((float) bounds.bottom) * height));
    }

    public void setGeometricWidth(int i) {
        Rect bounds = getBounds();
        float width = (((float) i) * 1.0f) / ((float) bounds.width());
        setBounds((int) (((float) bounds.left) * width), (int) (((float) bounds.top) * width), (int) (((float) bounds.right) * width), (int) (((float) bounds.bottom) * width));
    }
}
