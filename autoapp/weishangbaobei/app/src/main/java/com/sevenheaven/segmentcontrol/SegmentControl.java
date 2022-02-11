package com.sevenheaven.segmentcontrol;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.yalantis.ucrop.view.CropImageView;

public class SegmentControl extends View {
    private int DEFAULT_NORMAL_COLOR;
    private int DEFAULT_SELECTED_COLOR;
    private boolean inTapRegion;
    private ColorStateList mBackgroundColors;
    private RadiusDrawable mBackgroundDrawable;
    private int mBoundWidth;
    private Rect[] mCacheBounds;
    private Paint.FontMetrics mCachedFM;
    private int mCornerRadius;
    private int mCurrentIndex;
    private float mCurrentX;
    private float mCurrentY;
    private Direction mDirection;
    private int mHorizonGap;
    private OnSegmentControlClickListener mOnSegmentControlClickListener;
    private Paint mPaint;
    private RadiusDrawable mSelectedDrawable;
    private int mSeparatorWidth;
    private int mSingleChildHeight;
    private int mSingleChildWidth;
    private float mStartX;
    private float mStartY;
    private Rect[] mTextBounds;
    private ColorStateList mTextColors;
    private int mTextSize;
    private String[] mTexts;
    private int mTouchSlop;
    private int mVerticalGap;

    public interface OnSegmentControlClickListener {
        void onSegmentControlClick(int i);
    }

    public enum Direction {
        HORIZONTAL(0),
        VERTICAL(1);
        
        int value;

        private Direction(int i) {
            this.value = i;
        }
    }

    public SegmentControl(Context context) {
        this(context, (AttributeSet) null);
    }

    public SegmentControl(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SegmentControl(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2;
        this.mBoundWidth = 4;
        this.mSeparatorWidth = this.mBoundWidth / 2;
        this.DEFAULT_SELECTED_COLOR = -13455873;
        this.DEFAULT_NORMAL_COLOR = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SegmentControl);
        String string = obtainStyledAttributes.getString(R.styleable.SegmentControl_texts);
        if (string != null) {
            this.mTexts = string.split("\\|");
        }
        this.mTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SegmentControl_android_textSize, (int) TypedValue.applyDimension(2, 14.0f, context.getResources().getDisplayMetrics()));
        this.mCornerRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SegmentControl_cornerRadius, (int) TypedValue.applyDimension(1, 5.0f, context.getResources().getDisplayMetrics()));
        this.mDirection = Direction.values()[obtainStyledAttributes.getInt(R.styleable.SegmentControl_android_orientation, 0)];
        this.mHorizonGap = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SegmentControl_horizonGap, 0);
        this.mVerticalGap = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SegmentControl_verticalGap, 0);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SegmentControl_gaps, (int) TypedValue.applyDimension(1, 2.0f, context.getResources().getDisplayMetrics()));
        if (this.mHorizonGap == 0) {
            this.mHorizonGap = dimensionPixelSize;
        }
        if (this.mVerticalGap == 0) {
            this.mVerticalGap = dimensionPixelSize;
        }
        this.mBackgroundDrawable = new RadiusDrawable(this.mCornerRadius, true);
        this.mBackgroundDrawable.setStrokeWidth(2);
        this.DEFAULT_NORMAL_COLOR = obtainStyledAttributes.getColor(R.styleable.SegmentControl_normalColor, this.DEFAULT_NORMAL_COLOR);
        this.DEFAULT_SELECTED_COLOR = obtainStyledAttributes.getColor(R.styleable.SegmentControl_selectedColor, this.DEFAULT_SELECTED_COLOR);
        this.mBackgroundColors = obtainStyledAttributes.getColorStateList(R.styleable.SegmentControl_backgroundColors);
        this.mTextColors = obtainStyledAttributes.getColorStateList(R.styleable.SegmentControl_textColors);
        if (this.mBackgroundColors == null) {
            this.mBackgroundColors = new ColorStateList(new int[][]{new int[]{16842913}, new int[]{-16842913}}, new int[]{this.DEFAULT_SELECTED_COLOR, this.DEFAULT_NORMAL_COLOR});
        }
        if (this.mTextColors == null) {
            this.mTextColors = new ColorStateList(new int[][]{new int[]{16842913}, new int[]{-16842913}}, new int[]{this.DEFAULT_NORMAL_COLOR, this.DEFAULT_SELECTED_COLOR});
        }
        this.mBoundWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SegmentControl_boundWidth, this.mBoundWidth);
        this.mSeparatorWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SegmentControl_separatorWidth, this.mSeparatorWidth);
        obtainStyledAttributes.recycle();
        this.mBackgroundDrawable = new RadiusDrawable(this.mCornerRadius, true);
        this.mBackgroundDrawable.setStrokeWidth(this.mBoundWidth);
        this.mBackgroundDrawable.setStrokeColor(getSelectedBGColor());
        this.mBackgroundDrawable.setFillColor(getNormalBGColor());
        if (Build.VERSION.SDK_INT < 16) {
            setBackgroundDrawable(this.mBackgroundDrawable);
        } else {
            setBackground(this.mBackgroundDrawable);
        }
        this.mSelectedDrawable = new RadiusDrawable(false);
        this.mSelectedDrawable.setFillColor(getSelectedBGColor());
        this.mPaint = new Paint(1);
        this.mPaint.setTextSize((float) this.mTextSize);
        this.mCachedFM = this.mPaint.getFontMetrics();
        if (context == null) {
            i2 = ViewConfiguration.getTouchSlop();
        } else {
            i2 = ViewConfiguration.get(context).getScaledTouchSlop();
        }
        this.mTouchSlop = i2 * i2;
        this.inTapRegion = false;
    }

    public void setText(String... strArr) {
        this.mTexts = strArr;
        if (this.mTexts != null) {
            requestLayout();
        }
    }

    public void setSelectedTextColors(ColorStateList colorStateList) {
        this.mTextColors = colorStateList;
        invalidate();
    }

    public void setColors(ColorStateList colorStateList) {
        this.mBackgroundColors = colorStateList;
        if (this.mBackgroundDrawable != null) {
            this.mBackgroundDrawable.setStrokeColor(getSelectedBGColor());
            this.mBackgroundDrawable.setFillColor(getNormalBGColor());
        }
        if (this.mSelectedDrawable != null) {
            this.mSelectedDrawable.setFillColor(getSelectedBGColor());
        }
        invalidate();
    }

    public void setCornerRadius(int i) {
        this.mCornerRadius = i;
        if (this.mBackgroundDrawable != null) {
            this.mBackgroundDrawable.setRadius(i);
        }
        invalidate();
    }

    public void setDirection(Direction direction) {
        Direction direction2 = this.mDirection;
        this.mDirection = direction;
        if (direction2 != direction) {
            requestLayout();
            invalidate();
        }
    }

    public void setTextSize(int i) {
        setTextSize(2, i);
    }

    public void setTextSize(int i, int i2) {
        this.mPaint.setTextSize((float) ((int) TypedValue.applyDimension(i, (float) i2, getContext().getResources().getDisplayMetrics())));
        if (i2 != this.mTextSize) {
            this.mTextSize = i2;
            this.mCachedFM = this.mPaint.getFontMetrics();
            requestLayout();
        }
    }

    public void setSelectedIndex(int i) {
        this.mCurrentIndex = i;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (this.mTexts == null || this.mTexts.length <= 0) {
            if (mode == 0) {
                size = 0;
            }
            if (mode2 == 0) {
                size2 = 0;
            }
        } else {
            this.mSingleChildHeight = 0;
            this.mSingleChildWidth = 0;
            if (this.mCacheBounds == null || this.mCacheBounds.length != this.mTexts.length) {
                this.mCacheBounds = new Rect[this.mTexts.length];
            }
            if (this.mTextBounds == null || this.mTextBounds.length != this.mTexts.length) {
                this.mTextBounds = new Rect[this.mTexts.length];
            }
            for (int i3 = 0; i3 < this.mTexts.length; i3++) {
                String str = this.mTexts[i3];
                if (str != null) {
                    if (this.mTextBounds[i3] == null) {
                        this.mTextBounds[i3] = new Rect();
                    }
                    this.mPaint.getTextBounds(str, 0, str.length(), this.mTextBounds[i3]);
                    if (this.mSingleChildWidth < this.mTextBounds[i3].width() + (this.mHorizonGap * 2)) {
                        this.mSingleChildWidth = this.mTextBounds[i3].width() + (this.mHorizonGap * 2);
                    }
                    if (this.mSingleChildHeight < this.mTextBounds[i3].height() + (this.mVerticalGap * 2)) {
                        this.mSingleChildHeight = this.mTextBounds[i3].height() + (this.mVerticalGap * 2);
                    }
                }
            }
            if (mode != Integer.MIN_VALUE) {
                if (mode != 1073741824) {
                    size = this.mDirection == Direction.HORIZONTAL ? this.mSingleChildWidth * this.mTexts.length : this.mSingleChildWidth;
                }
            } else if (this.mDirection == Direction.HORIZONTAL) {
                if (size <= this.mSingleChildWidth * this.mTexts.length) {
                    this.mSingleChildWidth = size / this.mTexts.length;
                } else {
                    size = this.mSingleChildWidth * this.mTexts.length;
                }
            } else if (size > this.mSingleChildWidth) {
                size = this.mSingleChildWidth;
            }
            if (mode2 != Integer.MIN_VALUE) {
                if (mode2 != 1073741824) {
                    size2 = this.mDirection == Direction.VERTICAL ? this.mSingleChildHeight * this.mTexts.length : this.mSingleChildHeight;
                }
            } else if (this.mDirection == Direction.VERTICAL) {
                if (size2 <= this.mSingleChildHeight * this.mTexts.length) {
                    this.mSingleChildHeight = size2 / this.mTexts.length;
                } else {
                    size2 = this.mSingleChildHeight * this.mTexts.length;
                }
            } else if (size2 > this.mSingleChildHeight) {
                size2 = this.mSingleChildHeight;
            }
            switch (this.mDirection) {
                case HORIZONTAL:
                    if (this.mSingleChildWidth != size / this.mTexts.length) {
                        this.mSingleChildWidth = size / this.mTexts.length;
                    }
                    this.mSingleChildHeight = size2;
                    break;
                case VERTICAL:
                    if (this.mSingleChildHeight != size2 / this.mTexts.length) {
                        this.mSingleChildHeight = size2 / this.mTexts.length;
                    }
                    this.mSingleChildWidth = size;
                    break;
            }
            for (int i4 = 0; i4 < this.mTexts.length; i4++) {
                if (this.mCacheBounds[i4] == null) {
                    this.mCacheBounds[i4] = new Rect();
                }
                if (this.mDirection == Direction.HORIZONTAL) {
                    this.mCacheBounds[i4].left = this.mSingleChildWidth * i4;
                    this.mCacheBounds[i4].top = 0;
                } else {
                    this.mCacheBounds[i4].left = 0;
                    this.mCacheBounds[i4].top = this.mSingleChildHeight * i4;
                }
                this.mCacheBounds[i4].right = this.mCacheBounds[i4].left + this.mSingleChildWidth;
                this.mCacheBounds[i4].bottom = this.mCacheBounds[i4].top + this.mSingleChildHeight;
            }
        }
        setMeasuredDimension(size, size2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.inTapRegion = true;
                this.mStartX = motionEvent.getX();
                this.mStartY = motionEvent.getY();
                break;
            case 1:
                if (this.inTapRegion) {
                    if (this.mDirection == Direction.HORIZONTAL) {
                        i = (int) (this.mStartX / ((float) this.mSingleChildWidth));
                    } else {
                        i = (int) (this.mStartY / ((float) this.mSingleChildHeight));
                    }
                    if (this.mOnSegmentControlClickListener != null) {
                        this.mOnSegmentControlClickListener.onSegmentControlClick(i);
                    }
                    this.mCurrentIndex = i;
                    invalidate();
                    break;
                }
                break;
            case 2:
                this.mCurrentX = motionEvent.getX();
                this.mCurrentY = motionEvent.getY();
                int i2 = (int) (this.mCurrentX - this.mStartX);
                int i3 = (int) (this.mCurrentY - this.mStartY);
                if ((i2 * i2) + (i3 * i3) > this.mTouchSlop) {
                    this.inTapRegion = false;
                    break;
                }
                break;
        }
        return true;
    }

    private int getSelectedTextColor() {
        return this.mTextColors.getColorForState(new int[]{16842913}, this.DEFAULT_NORMAL_COLOR);
    }

    private int getNormalTextColor() {
        return this.mTextColors.getColorForState(new int[]{-16842913}, this.DEFAULT_SELECTED_COLOR);
    }

    private int getSelectedBGColor() {
        return this.mBackgroundColors.getColorForState(new int[]{16842913}, this.DEFAULT_SELECTED_COLOR);
    }

    private int getNormalBGColor() {
        return this.mBackgroundColors.getColorForState(new int[]{-16842913}, this.DEFAULT_NORMAL_COLOR);
    }

    public void onDraw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int i4;
        super.onDraw(canvas);
        if (this.mTexts != null && this.mTexts.length > 0) {
            for (int i5 = 0; i5 < this.mTexts.length; i5++) {
                if (i5 < this.mTexts.length - 1) {
                    this.mPaint.setColor(getSelectedBGColor());
                    this.mPaint.setStrokeWidth((float) this.mSeparatorWidth);
                    if (this.mDirection == Direction.HORIZONTAL) {
                        canvas.drawLine((float) this.mCacheBounds[i5].right, CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mCacheBounds[i5].right, (float) getHeight(), this.mPaint);
                    } else {
                        int i6 = i5 + 1;
                        canvas.drawLine((float) this.mCacheBounds[i5].left, (float) (this.mSingleChildHeight * i6), (float) this.mCacheBounds[i5].right, (float) (this.mSingleChildHeight * i6), this.mPaint);
                    }
                }
                if (i5 != this.mCurrentIndex || this.mSelectedDrawable == null) {
                    this.mPaint.setColor(getNormalTextColor());
                } else {
                    if (this.mTexts.length == 1) {
                        i4 = this.mCornerRadius;
                        int i7 = this.mCornerRadius;
                        int i8 = this.mCornerRadius;
                        i = this.mCornerRadius;
                        int i9 = i8;
                        i2 = i7;
                        i3 = i9;
                    } else {
                        if (this.mDirection == Direction.HORIZONTAL) {
                            if (i5 == 0) {
                                i4 = this.mCornerRadius;
                                i2 = this.mCornerRadius;
                                i3 = 0;
                            } else {
                                if (i5 == this.mTexts.length - 1) {
                                    int i10 = this.mCornerRadius;
                                    i = this.mCornerRadius;
                                    i2 = 0;
                                    i3 = i10;
                                    i4 = 0;
                                }
                                i4 = 0;
                                i3 = 0;
                                i2 = 0;
                            }
                        } else if (i5 == 0) {
                            i4 = this.mCornerRadius;
                            i3 = this.mCornerRadius;
                            i2 = 0;
                        } else {
                            if (i5 == this.mTexts.length - 1) {
                                i2 = this.mCornerRadius;
                                i = this.mCornerRadius;
                                i4 = 0;
                                i3 = 0;
                            }
                            i4 = 0;
                            i3 = 0;
                            i2 = 0;
                        }
                        i = 0;
                    }
                    this.mSelectedDrawable.setRadius(i4, i3, i2, i);
                    this.mSelectedDrawable.setBounds(this.mCacheBounds[i5]);
                    this.mSelectedDrawable.draw(canvas);
                    this.mPaint.setColor(getSelectedTextColor());
                }
                canvas.drawText(this.mTexts[i5], (float) (this.mCacheBounds[i5].left + ((this.mSingleChildWidth - this.mTextBounds[i5].width()) / 2)), (((float) this.mCacheBounds[i5].top) + (((((float) this.mSingleChildHeight) - this.mCachedFM.ascent) + this.mCachedFM.descent) / 2.0f)) - this.mCachedFM.descent, this.mPaint);
            }
        }
    }

    public void setOnSegmentControlClickListener(OnSegmentControlClickListener onSegmentControlClickListener) {
        this.mOnSegmentControlClickListener = onSegmentControlClickListener;
    }

    public OnSegmentControlClickListener getOnSegmentControlClicklistener() {
        return this.mOnSegmentControlClickListener;
    }
}
