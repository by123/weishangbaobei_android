package com.umeng.social.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import com.yalantis.ucrop.view.CropImageView;

class UMWaterMark {
    static final int RELATIVE_POSITION_HORIZONTAL_LEFT = 4;
    static final int RELATIVE_POSITION_HORIZONTAL_RIGHT = 3;
    static final int RELATIVE_POSITION_VERTICAL_BOTTOM = 1;
    static final int RELATIVE_POSITION_VERTICAL_TOP = 2;
    private static final String TAG = "UMWaterMark";
    private float mAlpha = -1.0f;
    private Rect mAnchorMarkRect = new Rect();
    private int mBottomMargin;
    private Context mContext;
    private int mDegree = -1;
    private int mGravity = 51;
    private int mHorizontalRelativePosition = -1;
    private boolean mIsBringToFront = false;
    private boolean mIsTransparent = false;
    private int mLeftMargin;
    private Rect mMeasureRect = new Rect();
    private int mRightMargin;
    private float mScale = 0.3f;
    private int mTopMargin;
    private int mVerticalRelativePosition = -1;

    /* access modifiers changed from: package-private */
    public Bitmap getMarkBitmap() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public void releaseResource() {
    }

    UMWaterMark() {
    }

    public void setMargins(int i, int i2, int i3, int i4) {
        this.mLeftMargin = i;
        this.mTopMargin = i2;
        this.mRightMargin = i3;
        this.mBottomMargin = i4;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setGravity(int i) {
        if (i > 0 && this.mGravity != i) {
            this.mGravity = i;
        }
    }

    public void setScale(float f) {
        if (f >= CropImageView.DEFAULT_ASPECT_RATIO && f <= 1.0f) {
            this.mScale = f;
        }
    }

    public void setRotate(int i) {
        if (i >= 0 && i <= 360) {
            this.mDegree = i;
        }
    }

    public void bringToFront() {
        this.mIsBringToFront = true;
    }

    public void setAlpha(float f) {
        if (f >= CropImageView.DEFAULT_ASPECT_RATIO && f <= 1.0f) {
            this.mAlpha = f;
        }
    }

    public void setTransparent() {
        this.mIsTransparent = true;
    }

    public Bitmap compound(Bitmap bitmap) {
        Canvas canvas;
        Bitmap bitmap2;
        if (bitmap == null) {
            try {
                Log.e(TAG, "scr bitmap is null");
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Bitmap markBitmap = getMarkBitmap();
            if (markBitmap == null) {
                Log.e(TAG, "mark bitmap is null");
                return bitmap;
            }
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > 0) {
                if (height > 0) {
                    int markWidth = getMarkWidth();
                    int markHeight = getMarkHeight();
                    if (markWidth > 0) {
                        if (markHeight > 0) {
                            if (this.mIsTransparent) {
                                bitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                                canvas = new Canvas(bitmap2);
                                canvas.drawColor(0);
                            } else {
                                bitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                                canvas = new Canvas(bitmap2);
                            }
                            canvas.drawBitmap(bitmap, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (Paint) null);
                            Matrix matrix = new Matrix();
                            float min = (this.mScale * ((float) Math.min(bitmap.getWidth(), bitmap.getHeight()))) / ((float) Math.max(markWidth, markHeight));
                            matrix.postScale(min, min, getScaleAnchorX(markWidth), getScaleAnchorY(markHeight));
                            if (this.mDegree != -1) {
                                matrix.postRotate((float) this.mDegree, (float) (markWidth / 2), (float) (markHeight / 2));
                            }
                            matrix.postTranslate(isHorizontalRelativePosition() ? getRelativeDx(width) : getDx(width), isVerticalRelativePosition() ? getRelativeDy(height) : getDy(height));
                            if (this.mAlpha != -1.0f) {
                                Paint paint = new Paint();
                                paint.setAlpha((int) (this.mAlpha * 255.0f));
                                canvas.drawBitmap(markBitmap, matrix, paint);
                            } else {
                                canvas.drawBitmap(markBitmap, matrix, (Paint) null);
                            }
                            canvas.save(31);
                            canvas.restore();
                            safelyRecycleBitmap(bitmap);
                            safelyRecycleBitmap(markBitmap);
                            releaseResource();
                            return bitmap2;
                        }
                    }
                    String str = TAG;
                    Log.e(str, "mark bitmap is error, markWidth:" + markWidth + ", markHeight:" + markHeight);
                    return bitmap;
                }
            }
            String str2 = TAG;
            Log.e(str2, "mark bitmap is error, markWidth:" + width + ", markHeight:" + height);
            return bitmap;
        }
    }

    private float getScaleAnchorY(int i) {
        int i2 = this.mGravity & 112;
        if (i2 != 16) {
            return i2 != 80 ? CropImageView.DEFAULT_ASPECT_RATIO : (float) i;
        }
        return (float) (i / 2);
    }

    private float getScaleAnchorX(int i) {
        int i2 = this.mGravity & 7;
        if (i2 != 1) {
            return i2 != 5 ? CropImageView.DEFAULT_ASPECT_RATIO : (float) i;
        }
        return (float) (i / 2);
    }

    private void safelyRecycleBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private float getRelativeDy(int i) {
        float f = (float) this.mAnchorMarkRect.top;
        float f2 = (float) this.mAnchorMarkRect.bottom;
        switch (this.mVerticalRelativePosition) {
            case 1:
                return f2 + ((float) dip2px((float) this.mTopMargin));
            case 2:
                return (f - ((float) getMarkHeight())) + ((float) (-dip2px((float) this.mBottomMargin)));
            default:
                return getDy(i);
        }
    }

    private float getRelativeDx(int i) {
        float f = (float) this.mAnchorMarkRect.left;
        float f2 = (float) this.mAnchorMarkRect.right;
        switch (this.mHorizontalRelativePosition) {
            case 3:
                return f2 + ((float) dip2px((float) this.mLeftMargin));
            case 4:
                return (f - ((float) getMarkWidth())) + ((float) (-dip2px((float) this.mRightMargin)));
            default:
                return getDx(i);
        }
    }

    private float getDy(int i) {
        int i2 = -dip2px((float) this.mBottomMargin);
        int dip2px = dip2px((float) this.mTopMargin);
        int i3 = this.mGravity & 112;
        if (i3 == 16) {
            if (dip2px != 0) {
                i2 = dip2px;
            }
            return ((((float) (i - getMarkHeight())) * 1.0f) / 2.0f) + ((float) i2);
        } else if (i3 != 80) {
            return (float) dip2px;
        } else {
            return (float) ((i - getMarkHeight()) + i2);
        }
    }

    private float getDx(int i) {
        int dip2px = dip2px((float) this.mLeftMargin);
        int i2 = -dip2px((float) this.mRightMargin);
        int i3 = this.mGravity & 7;
        if (i3 == 1) {
            if (dip2px == 0) {
                dip2px = i2;
            }
            return ((((float) (i - getMarkWidth())) * 1.0f) / 2.0f) + ((float) dip2px);
        } else if (i3 != 5) {
            return (float) dip2px;
        } else {
            return (float) ((i - getMarkWidth()) + i2);
        }
    }

    private int getMarkWidth() {
        if (getMarkBitmap() == null) {
            return -1;
        }
        return getMarkBitmap().getWidth();
    }

    private int getMarkHeight() {
        if (getMarkBitmap() == null) {
            return -1;
        }
        return getMarkBitmap().getHeight();
    }

    /* access modifiers changed from: package-private */
    public void setAnchorMarkHorizontalRect(Rect rect) {
        this.mAnchorMarkRect.set(rect.left, this.mAnchorMarkRect.top, rect.right, this.mAnchorMarkRect.bottom);
    }

    /* access modifiers changed from: package-private */
    public void setAnchorMarkVerticalRect(Rect rect) {
        this.mAnchorMarkRect = rect;
        this.mAnchorMarkRect.set(this.mAnchorMarkRect.left, rect.top, this.mAnchorMarkRect.right, rect.bottom);
    }

    /* access modifiers changed from: package-private */
    public Rect onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        if (!isHorizontalRelativePosition()) {
            i3 = (int) getDx(i);
            i4 = getMarkWidth() + i3;
        } else {
            i3 = 0;
            i4 = 0;
        }
        if (!isVerticalRelativePosition()) {
            i6 = (int) getDy(i2);
            i5 = getMarkHeight() + i6;
        } else {
            i5 = 0;
        }
        this.mMeasureRect.set(i3, i6, i4, i5);
        return this.mMeasureRect;
    }

    /* access modifiers changed from: package-private */
    public void setHorizontalRelativePosition(int i) {
        this.mHorizontalRelativePosition = i;
    }

    /* access modifiers changed from: package-private */
    public void setVerticalRelativePosition(int i) {
        this.mVerticalRelativePosition = i;
    }

    /* access modifiers changed from: package-private */
    public void clearRelativePosition() {
        this.mHorizontalRelativePosition = -1;
        this.mVerticalRelativePosition = -1;
    }

    /* access modifiers changed from: package-private */
    public boolean isVerticalRelativePosition() {
        return this.mVerticalRelativePosition != -1;
    }

    /* access modifiers changed from: package-private */
    public boolean isHorizontalRelativePosition() {
        return this.mHorizontalRelativePosition != -1;
    }

    /* access modifiers changed from: package-private */
    public boolean isBringToFront() {
        return this.mIsBringToFront;
    }

    /* access modifiers changed from: package-private */
    public int dip2px(float f) {
        return (int) ((f * this.mContext.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
