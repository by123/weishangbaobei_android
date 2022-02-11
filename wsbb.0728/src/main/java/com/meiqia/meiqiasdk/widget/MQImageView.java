package com.meiqia.meiqiasdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.meiqia.meiqiasdk.R;
import com.yalantis.ucrop.view.CropImageView;

public class MQImageView extends ImageView {
    private int mBorderColor;
    private Paint mBorderPaint;
    private int mBorderWidth;
    private int mCornerRadius;
    private int mDefaultImageId;
    private boolean mIsCircle;
    private boolean mIsSquare;
    private OnDrawableChangedCallback mOnDrawableChangedCallback;
    private RectF mRect;

    public interface OnDrawableChangedCallback {
        void onDrawableChanged(Drawable drawable);
    }

    public MQImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MQImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MQImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCornerRadius = 0;
        this.mIsCircle = false;
        this.mIsSquare = false;
        this.mBorderWidth = 0;
        this.mBorderColor = -1;
        initCustomAttrs(context, attributeSet);
        initBorderPaint();
        setDefaultImage();
        this.mRect = new RectF();
    }

    public static RoundedBitmapDrawable getCircleDrawable(Context context, @DrawableRes int i) {
        return getCircleDrawable(context, BitmapFactory.decodeResource(context.getResources(), i));
    }

    public static RoundedBitmapDrawable getCircleDrawable(Context context, Bitmap bitmap) {
        Bitmap createBitmap = bitmap.getWidth() >= bitmap.getHeight() ? Bitmap.createBitmap(bitmap, (bitmap.getWidth() / 2) - (bitmap.getHeight() / 2), 0, bitmap.getHeight(), bitmap.getHeight()) : Bitmap.createBitmap(bitmap, 0, (bitmap.getHeight() / 2) - (bitmap.getWidth() / 2), bitmap.getWidth(), bitmap.getWidth());
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(context.getResources(), createBitmap);
        create.setAntiAlias(true);
        create.setCornerRadius(((float) Math.min(createBitmap.getWidth(), createBitmap.getHeight())) / 2.0f);
        return create;
    }

    public static RoundedBitmapDrawable getRoundedDrawable(Context context, @DrawableRes int i, float f) {
        return getRoundedDrawable(context, BitmapFactory.decodeResource(context.getResources(), i), f);
    }

    public static RoundedBitmapDrawable getRoundedDrawable(Context context, Bitmap bitmap, float f) {
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
        create.setAntiAlias(true);
        create.setCornerRadius(f);
        return create;
    }

    private void initBorderPaint() {
        this.mBorderPaint = new Paint();
        this.mBorderPaint.setAntiAlias(true);
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
        this.mBorderPaint.setColor(this.mBorderColor);
        this.mBorderPaint.setStrokeWidth((float) this.mBorderWidth);
    }

    private void initCustomAttr(int i, TypedArray typedArray) {
        if (i == R.styleable.MQImageView_android_src) {
            this.mDefaultImageId = typedArray.getResourceId(i, 0);
        } else if (i == R.styleable.MQImageView_mq_iv_isCircle) {
            this.mIsCircle = typedArray.getBoolean(i, this.mIsCircle);
        } else if (i == R.styleable.MQImageView_mq_iv_cornerRadius) {
            this.mCornerRadius = typedArray.getDimensionPixelSize(i, this.mCornerRadius);
        } else if (i == R.styleable.MQImageView_mq_iv_isSquare) {
            this.mIsSquare = typedArray.getBoolean(i, this.mIsSquare);
        } else if (i == R.styleable.MQImageView_mq_iv_borderWidth) {
            this.mBorderWidth = typedArray.getDimensionPixelSize(i, this.mBorderWidth);
        } else if (i == R.styleable.MQImageView_mq_iv_borderColor) {
            this.mBorderColor = typedArray.getColor(i, this.mBorderColor);
        }
    }

    private void initCustomAttrs(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MQImageView);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            initCustomAttr(obtainStyledAttributes.getIndex(i), obtainStyledAttributes);
        }
        obtainStyledAttributes.recycle();
    }

    private void notifyDrawableChanged(Drawable drawable) {
        if (this.mOnDrawableChangedCallback != null) {
            this.mOnDrawableChangedCallback.onDrawableChanged(drawable);
        }
    }

    private void setDefaultImage() {
        if (this.mDefaultImageId != 0) {
            setImageResource(this.mDefaultImageId);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
            if (this.mBorderWidth <= 0) {
                return;
            }
            if (this.mIsCircle) {
                canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) ((getWidth() / 2) - (this.mBorderWidth / 2)), this.mBorderPaint);
                return;
            }
            this.mRect.left = CropImageView.DEFAULT_ASPECT_RATIO;
            this.mRect.top = CropImageView.DEFAULT_ASPECT_RATIO;
            this.mRect.right = (float) getWidth();
            this.mRect.bottom = (float) getHeight();
            canvas.drawRoundRect(this.mRect, (float) this.mCornerRadius, (float) this.mCornerRadius, this.mBorderPaint);
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mIsCircle || this.mIsSquare) {
            setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
            i = makeMeasureSpec;
            i2 = makeMeasureSpec;
        }
        super.onMeasure(i, i2);
    }

    public void setDrawableChangedCallback(OnDrawableChangedCallback onDrawableChangedCallback) {
        this.mOnDrawableChangedCallback = onDrawableChangedCallback;
    }

    public void setImageDrawable(@Nullable Drawable drawable) {
        boolean z = drawable instanceof BitmapDrawable;
        if (z && this.mCornerRadius > 0) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null) {
                super.setImageDrawable(getRoundedDrawable(getContext(), bitmap, (float) this.mCornerRadius));
            } else {
                super.setImageDrawable(drawable);
            }
        } else if (!z || !this.mIsCircle) {
            super.setImageDrawable(drawable);
        } else {
            Bitmap bitmap2 = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap2 != null) {
                super.setImageDrawable(getCircleDrawable(getContext(), bitmap2));
            } else {
                super.setImageDrawable(drawable);
            }
        }
        notifyDrawableChanged(drawable);
    }

    public void setImageResource(@DrawableRes int i) {
        setImageDrawable(getResources().getDrawable(i));
    }
}
