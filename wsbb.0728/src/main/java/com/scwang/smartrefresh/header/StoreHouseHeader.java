package com.scwang.smartrefresh.header;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.scwang.smartrefresh.header.storehouse.StoreHouseBarItem;
import com.scwang.smartrefresh.header.storehouse.StoreHousePath;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

public class StoreHouseHeader extends InternalAbstract implements RefreshHeader {
    protected static final float mBarDarkAlpha = 0.4f;
    protected static final float mFromAlpha = 1.0f;
    protected static final float mInternalAnimationFactor = 0.7f;
    protected static final int mLoadingAniItemDuration = 400;
    protected static final float mToAlpha = 0.4f;
    protected AniController mAniController;
    protected int mBackgroundColor;
    protected int mDrawZoneHeight;
    protected int mDrawZoneWidth;
    protected int mDropHeight;
    protected boolean mEnableFadeAnimation;
    protected int mHorizontalRandomness;
    protected boolean mIsInLoading;
    public List<StoreHouseBarItem> mItemList;
    protected int mLineWidth;
    protected int mLoadingAniDuration;
    protected int mLoadingAniSegDuration;
    protected Matrix mMatrix;
    protected int mOffsetX;
    protected int mOffsetY;
    protected float mProgress;
    protected RefreshKernel mRefreshKernel;
    protected float mScale;
    protected int mTextColor;
    protected Transformation mTransformation;

    private class AniController implements Runnable {
        int mCountPerSeg;
        int mInterval;
        boolean mRunning;
        int mSegCount;
        int mTick;

        private AniController() {
            this.mTick = 0;
            this.mCountPerSeg = 0;
            this.mSegCount = 0;
            this.mInterval = 0;
            this.mRunning = true;
        }

        /* access modifiers changed from: private */
        public void start() {
            this.mRunning = true;
            this.mTick = 0;
            this.mInterval = StoreHouseHeader.this.mLoadingAniDuration / StoreHouseHeader.this.mItemList.size();
            this.mCountPerSeg = StoreHouseHeader.this.mLoadingAniSegDuration / this.mInterval;
            this.mSegCount = (StoreHouseHeader.this.mItemList.size() / this.mCountPerSeg) + 1;
            run();
        }

        /* access modifiers changed from: private */
        public void stop() {
            this.mRunning = false;
            StoreHouseHeader.this.removeCallbacks(this);
        }

        public void run() {
            int i = this.mTick;
            int i2 = this.mCountPerSeg;
            for (int i3 = 0; i3 < this.mSegCount; i3++) {
                int i4 = (this.mCountPerSeg * i3) + (i % i2);
                if (i4 <= this.mTick) {
                    StoreHouseBarItem storeHouseBarItem = StoreHouseHeader.this.mItemList.get(i4 % StoreHouseHeader.this.mItemList.size());
                    storeHouseBarItem.setFillAfter(false);
                    storeHouseBarItem.setFillEnabled(true);
                    storeHouseBarItem.setFillBefore(false);
                    storeHouseBarItem.setDuration(400);
                    storeHouseBarItem.start(1.0f, 0.4f);
                }
            }
            this.mTick++;
            if (this.mRunning && StoreHouseHeader.this.mRefreshKernel != null) {
                StoreHouseHeader.this.mRefreshKernel.getRefreshLayout().getLayout().postDelayed(this, (long) this.mInterval);
            }
        }
    }

    public StoreHouseHeader(Context context) {
        this(context, (AttributeSet) null);
    }

    public StoreHouseHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StoreHouseHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mItemList = new ArrayList();
        this.mLineWidth = -1;
        this.mScale = 1.0f;
        this.mDropHeight = -1;
        this.mHorizontalRandomness = -1;
        this.mProgress = CropImageView.DEFAULT_ASPECT_RATIO;
        this.mDrawZoneWidth = 0;
        this.mDrawZoneHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mLoadingAniDuration = SocializeConstants.CANCLE_RESULTCODE;
        this.mLoadingAniSegDuration = SocializeConstants.CANCLE_RESULTCODE;
        this.mTextColor = -1;
        this.mBackgroundColor = 0;
        this.mIsInLoading = false;
        this.mEnableFadeAnimation = false;
        this.mMatrix = new Matrix();
        this.mAniController = new AniController();
        this.mTransformation = new Transformation();
        DensityUtil densityUtil = new DensityUtil();
        this.mLineWidth = densityUtil.dip2px(1.0f);
        this.mDropHeight = densityUtil.dip2px(40.0f);
        this.mHorizontalRandomness = Resources.getSystem().getDisplayMetrics().widthPixels / 2;
        this.mBackgroundColor = -13421773;
        setTextColor(-3355444);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.StoreHouseHeader);
        this.mLineWidth = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.StoreHouseHeader_shhLineWidth, this.mLineWidth);
        this.mDropHeight = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.StoreHouseHeader_shhDropHeight, this.mDropHeight);
        this.mEnableFadeAnimation = obtainStyledAttributes.getBoolean(R.styleable.StoreHouseHeader_shhEnableFadeAnimation, this.mEnableFadeAnimation);
        if (obtainStyledAttributes.hasValue(R.styleable.StoreHouseHeader_shhText)) {
            initWithString(obtainStyledAttributes.getString(R.styleable.StoreHouseHeader_shhText));
        } else {
            initWithString("StoreHouse");
        }
        obtainStyledAttributes.recycle();
        setMinimumHeight(this.mDrawZoneHeight + DensityUtil.dp2px(40.0f));
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        int save = canvas.save();
        int size = this.mItemList.size();
        float f = isInEditMode() ? 1.0f : this.mProgress;
        for (int i = 0; i < size; i++) {
            canvas.save();
            StoreHouseBarItem storeHouseBarItem = this.mItemList.get(i);
            float f2 = storeHouseBarItem.midPoint.x + ((float) this.mOffsetX);
            float f3 = storeHouseBarItem.midPoint.y + ((float) this.mOffsetY);
            if (this.mIsInLoading) {
                storeHouseBarItem.getTransformation(getDrawingTime(), this.mTransformation);
                canvas.translate(f2, f3);
            } else {
                float f4 = CropImageView.DEFAULT_ASPECT_RATIO;
                if (f == CropImageView.DEFAULT_ASPECT_RATIO) {
                    storeHouseBarItem.resetPosition(this.mHorizontalRandomness);
                } else {
                    float f5 = (((float) i) * 0.3f) / ((float) size);
                    if (f == 1.0f || f >= 1.0f - (0.3f - f5)) {
                        canvas.translate(f2, f3);
                        storeHouseBarItem.setAlpha(0.4f);
                    } else {
                        if (f > f5) {
                            f4 = Math.min(1.0f, (f - f5) / mInternalAnimationFactor);
                        }
                        float f6 = storeHouseBarItem.translationX;
                        float f7 = 1.0f - f4;
                        this.mMatrix.reset();
                        this.mMatrix.postRotate(360.0f * f4);
                        this.mMatrix.postScale(f4, f4);
                        this.mMatrix.postTranslate(f2 + (f6 * f7), f3 + (((float) (-this.mDropHeight)) * f7));
                        storeHouseBarItem.setAlpha(f4 * 0.4f);
                        canvas.concat(this.mMatrix);
                    }
                }
            }
            storeHouseBarItem.draw(canvas);
            canvas.restore();
        }
        if (this.mIsInLoading) {
            invalidate();
        }
        canvas.restoreToCount(save);
        super.dispatchDraw(canvas);
    }

    public StoreHouseHeader initWithPointList(List<float[]> list) {
        boolean z = this.mItemList.size() > 0;
        this.mItemList.clear();
        DensityUtil densityUtil = new DensityUtil();
        int i = 0;
        float f = 0.0f;
        float f2 = 0.0f;
        while (i < list.size()) {
            float[] fArr = list.get(i);
            PointF pointF = new PointF(((float) densityUtil.dip2px(fArr[0])) * this.mScale, ((float) densityUtil.dip2px(fArr[1])) * this.mScale);
            PointF pointF2 = new PointF(((float) densityUtil.dip2px(fArr[2])) * this.mScale, ((float) densityUtil.dip2px(fArr[3])) * this.mScale);
            float max = Math.max(Math.max(f, pointF.x), pointF2.x);
            float max2 = Math.max(Math.max(f2, pointF.y), pointF2.y);
            StoreHouseBarItem storeHouseBarItem = new StoreHouseBarItem(i, pointF, pointF2, this.mTextColor, this.mLineWidth);
            storeHouseBarItem.resetPosition(this.mHorizontalRandomness);
            this.mItemList.add(storeHouseBarItem);
            i++;
            f = max;
            f2 = max2;
        }
        this.mDrawZoneWidth = (int) Math.ceil((double) f);
        this.mDrawZoneHeight = (int) Math.ceil((double) f2);
        if (z) {
            requestLayout();
        }
        return this;
    }

    public StoreHouseHeader initWithString(String str) {
        initWithString(str, 25);
        return this;
    }

    public StoreHouseHeader initWithString(String str, int i) {
        initWithPointList(StoreHousePath.getPath(str, ((float) i) * 0.01f, 14));
        return this;
    }

    public StoreHouseHeader initWithStringArray(int i) {
        String[] stringArray = getResources().getStringArray(i);
        ArrayList arrayList = new ArrayList();
        for (String split : stringArray) {
            String[] split2 = split.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
            float[] fArr = new float[4];
            for (int i2 = 0; i2 < 4; i2++) {
                fArr[i2] = Float.parseFloat(split2[i2]);
            }
            arrayList.add(fArr);
        }
        initWithPointList(arrayList);
        return this;
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        this.mIsInLoading = false;
        this.mAniController.stop();
        if (!z || !this.mEnableFadeAnimation) {
            for (int i = 0; i < this.mItemList.size(); i++) {
                this.mItemList.get(i).resetPosition(this.mHorizontalRandomness);
            }
            return 0;
        }
        startAnimation(new Animation() {
            {
                super.setDuration(250);
                super.setInterpolator(new AccelerateInterpolator());
            }

            /* access modifiers changed from: protected */
            public void applyTransformation(float f, Transformation transformation) {
                StoreHouseHeader storeHouseHeader = StoreHouseHeader.this;
                StoreHouseHeader.this.mProgress = 1.0f - f;
                storeHouseHeader.invalidate();
                if (f == 1.0f) {
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 < StoreHouseHeader.this.mItemList.size()) {
                            StoreHouseHeader.this.mItemList.get(i2).resetPosition(StoreHouseHeader.this.mHorizontalRandomness);
                            i = i2 + 1;
                        } else {
                            return;
                        }
                    }
                }
            }
        });
        return 250;
    }

    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
        this.mRefreshKernel = refreshKernel;
        this.mRefreshKernel.requestDrawBackgroundFor(this, this.mBackgroundColor);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.setMeasuredDimension(View.resolveSize(super.getSuggestedMinimumWidth(), i), View.resolveSize(super.getSuggestedMinimumHeight(), i2));
        this.mOffsetX = (getMeasuredWidth() - this.mDrawZoneWidth) / 2;
        this.mOffsetY = (getMeasuredHeight() - this.mDrawZoneHeight) / 2;
        this.mDropHeight = getMeasuredHeight() / 2;
    }

    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        this.mProgress = 0.8f * f;
        invalidate();
    }

    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        this.mIsInLoading = true;
        this.mAniController.start();
        invalidate();
    }

    public StoreHouseHeader setDropHeight(int i) {
        this.mDropHeight = i;
        return this;
    }

    public StoreHouseHeader setLineWidth(int i) {
        this.mLineWidth = i;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.mItemList.size()) {
                return this;
            }
            this.mItemList.get(i3).setLineWidth(i);
            i2 = i3 + 1;
        }
    }

    public StoreHouseHeader setLoadingAniDuration(int i) {
        this.mLoadingAniDuration = i;
        this.mLoadingAniSegDuration = i;
        return this;
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (iArr.length > 0) {
            this.mBackgroundColor = iArr[0];
            if (this.mRefreshKernel != null) {
                this.mRefreshKernel.requestDrawBackgroundFor(this, this.mBackgroundColor);
            }
            if (iArr.length > 1) {
                setTextColor(iArr[1]);
            }
        }
    }

    public StoreHouseHeader setScale(float f) {
        this.mScale = f;
        return this;
    }

    public StoreHouseHeader setTextColor(@ColorInt int i) {
        this.mTextColor = i;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.mItemList.size()) {
                return this;
            }
            this.mItemList.get(i3).setColor(i);
            i2 = i3 + 1;
        }
    }
}
