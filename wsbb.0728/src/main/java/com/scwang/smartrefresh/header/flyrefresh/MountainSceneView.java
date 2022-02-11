package com.scwang.smartrefresh.header.flyrefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import com.scwang.smartrefresh.header.R;
import com.yalantis.ucrop.view.CropImageView;

public class MountainSceneView extends View {
    protected static final int HEIGHT = 180;
    protected static final int TREE_HEIGHT = 200;
    protected static final int TREE_WIDTH = 100;
    protected static final int WIDTH = 240;
    protected int COLOR_BACKGROUND;
    protected int COLOR_MOUNTAIN_1;
    protected int COLOR_MOUNTAIN_2;
    protected int COLOR_MOUNTAIN_3;
    protected int COLOR_TREE_1_BRANCH;
    protected int COLOR_TREE_1_BRINK;
    protected int COLOR_TREE_2_BRANCH;
    protected int COLOR_TREE_2_BRINK;
    protected int COLOR_TREE_3_BRANCH;
    protected int COLOR_TREE_3_BRINK;
    protected Paint mBoarderPaint;
    protected float mBounceMax;
    protected Path mBranch;
    protected Paint mBranchPaint;
    protected Path mMount1;
    protected Path mMount2;
    protected Path mMount3;
    protected Paint mMountPaint;
    protected float mMoveFactor;
    protected float mScaleX;
    protected float mScaleY;
    protected Matrix mTransMatrix;
    protected float mTreeBendFactor;
    protected Path mTrunk;
    protected Paint mTrunkPaint;
    protected int mViewportHeight;

    public MountainSceneView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MountainSceneView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.COLOR_BACKGROUND = -8466743;
        this.COLOR_MOUNTAIN_1 = -7939369;
        this.COLOR_MOUNTAIN_2 = -12807524;
        this.COLOR_MOUNTAIN_3 = -12689549;
        this.COLOR_TREE_1_BRANCH = -14716553;
        this.COLOR_TREE_1_BRINK = -15974840;
        this.COLOR_TREE_2_BRANCH = -13334385;
        this.COLOR_TREE_2_BRINK = -14982807;
        this.COLOR_TREE_3_BRANCH = -11030098;
        this.COLOR_TREE_3_BRINK = -10312531;
        this.mMountPaint = new Paint();
        this.mTrunkPaint = new Paint();
        this.mBranchPaint = new Paint();
        this.mBoarderPaint = new Paint();
        this.mMount1 = new Path();
        this.mMount2 = new Path();
        this.mMount3 = new Path();
        this.mTrunk = new Path();
        this.mBranch = new Path();
        this.mTransMatrix = new Matrix();
        this.mScaleX = 5.0f;
        this.mScaleY = 5.0f;
        this.mMoveFactor = CropImageView.DEFAULT_ASPECT_RATIO;
        this.mBounceMax = 1.0f;
        this.mTreeBendFactor = Float.MAX_VALUE;
        this.mViewportHeight = 0;
        this.mMountPaint.setAntiAlias(true);
        this.mMountPaint.setStyle(Paint.Style.FILL);
        this.mTrunkPaint.setAntiAlias(true);
        this.mBranchPaint.setAntiAlias(true);
        this.mBoarderPaint.setAntiAlias(true);
        this.mBoarderPaint.setStyle(Paint.Style.STROKE);
        this.mBoarderPaint.setStrokeWidth(2.0f);
        this.mBoarderPaint.setStrokeJoin(Paint.Join.ROUND);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MountainSceneView);
        if (obtainStyledAttributes.hasValue(R.styleable.MountainSceneView_msvPrimaryColor)) {
            setPrimaryColor(obtainStyledAttributes.getColor(R.styleable.MountainSceneView_msvPrimaryColor, -16777216));
        }
        this.mViewportHeight = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.MountainSceneView_msvViewportHeight, 0);
        obtainStyledAttributes.recycle();
        updateMountainPath(this.mMoveFactor, 180);
        updateTreePath(this.mMoveFactor, true);
    }

    private void drawTree(Canvas canvas, float f, float f2, float f3, int i, int i2) {
        canvas.save();
        canvas.translate(f2 - ((100.0f * f) / 2.0f), f3 - (200.0f * f));
        canvas.scale(f, f);
        this.mBranchPaint.setColor(i2);
        canvas.drawPath(this.mBranch, this.mBranchPaint);
        this.mTrunkPaint.setColor(i);
        canvas.drawPath(this.mTrunk, this.mTrunkPaint);
        this.mBoarderPaint.setColor(i);
        canvas.drawPath(this.mBranch, this.mBoarderPaint);
        canvas.restore();
    }

    private void updateMountainPath(float f, int i) {
        this.mTransMatrix.reset();
        this.mTransMatrix.setScale(this.mScaleX, this.mScaleY);
        float f2 = 10.0f * f;
        this.mMount1.reset();
        this.mMount1.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, 95.0f + f2);
        this.mMount1.lineTo(55.0f, 74.0f + f2);
        this.mMount1.lineTo(146.0f, f2 + 104.0f);
        this.mMount1.lineTo(227.0f, 72.0f + f2);
        this.mMount1.lineTo(240.0f, f2 + 80.0f);
        this.mMount1.lineTo(240.0f, 180.0f);
        this.mMount1.lineTo(CropImageView.DEFAULT_ASPECT_RATIO, 180.0f);
        this.mMount1.close();
        this.mMount1.transform(this.mTransMatrix);
        float f3 = 20.0f * f;
        this.mMount2.reset();
        this.mMount2.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, 103.0f + f3);
        this.mMount2.lineTo(67.0f, 90.0f + f3);
        this.mMount2.lineTo(165.0f, 115.0f + f3);
        this.mMount2.lineTo(221.0f, 87.0f + f3);
        this.mMount2.lineTo(240.0f, f3 + 100.0f);
        this.mMount2.lineTo(240.0f, 180.0f);
        this.mMount2.lineTo(CropImageView.DEFAULT_ASPECT_RATIO, 180.0f);
        this.mMount2.close();
        this.mMount2.transform(this.mTransMatrix);
        float f4 = f * 30.0f;
        this.mMount3.reset();
        this.mMount3.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, 114.0f + f4);
        this.mMount3.cubicTo(30.0f, 106.0f + f4, 196.0f, 97.0f + f4, 240.0f, f4 + 104.0f);
        float f5 = (float) i;
        this.mMount3.lineTo(240.0f, f5 / this.mScaleY);
        this.mMount3.lineTo(CropImageView.DEFAULT_ASPECT_RATIO, f5 / this.mScaleY);
        this.mMount3.close();
        this.mMount3.transform(this.mTransMatrix);
    }

    private void updateTreePath(float f, boolean z) {
        int i;
        if (f != this.mTreeBendFactor || z) {
            Interpolator create = PathInterpolatorCompat.create(0.8f, -0.5f * f);
            float[] fArr = new float[26];
            float[] fArr2 = new float[26];
            int i2 = 0;
            float f2 = CropImageView.DEFAULT_ASPECT_RATIO;
            float f3 = 200.0f;
            while (true) {
                int i3 = i2;
                if (i3 > 25) {
                    break;
                }
                fArr[i3] = (create.getInterpolation(f2) * 30.000002f * f) + 50.0f;
                fArr2[i3] = f3;
                f3 -= 8.0f;
                f2 += 0.04f;
                i2 = i3 + 1;
            }
            this.mTrunk.reset();
            this.mTrunk.moveTo(45.0f, 200.0f);
            int i4 = (int) (((float) 17) * 0.5f);
            float f4 = (float) (17 - i4);
            for (int i5 = 0; i5 < 17; i5++) {
                if (i5 < i4) {
                    this.mTrunk.lineTo(fArr[i5] - 5.0f, fArr2[i5]);
                } else {
                    this.mTrunk.lineTo(fArr[i5] - ((((float) (17 - i5)) * 5.0f) / f4), fArr2[i5]);
                }
            }
            for (int i6 = 16; i6 >= 0; i6--) {
                if (i6 < i4) {
                    this.mTrunk.lineTo(fArr[i6] + 5.0f, fArr2[i6]);
                } else {
                    this.mTrunk.lineTo(fArr[i6] + ((((float) (17 - i6)) * 5.0f) / f4), fArr2[i6]);
                }
            }
            this.mTrunk.close();
            this.mBranch.reset();
            float f5 = (float) 15;
            this.mBranch.moveTo(fArr[10] - 20.0f, fArr2[10]);
            this.mBranch.addArc(new RectF(fArr[10] - 20.0f, fArr2[10] - 20.0f, fArr[10] + 20.0f, fArr2[10] + 20.0f), CropImageView.DEFAULT_ASPECT_RATIO, 180.0f);
            for (int i7 = 10; i7 <= 25; i7++) {
                float f6 = ((float) (i7 - 10)) / f5;
                this.mBranch.lineTo((f6 * f6 * 20.0f) + (fArr[i7] - 20.0f), fArr2[i7]);
            }
            for (i = 25; i >= 10; i--) {
                float f7 = ((float) (i - 10)) / f5;
                this.mBranch.lineTo((fArr[i] + 20.0f) - ((f7 * f7) * 20.0f), fArr2[i]);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(this.COLOR_BACKGROUND);
        this.mMountPaint.setColor(this.COLOR_MOUNTAIN_1);
        canvas.drawPath(this.mMount1, this.mMountPaint);
        canvas.save();
        canvas.scale(-1.0f, 1.0f, (float) (getWidth() / 2), CropImageView.DEFAULT_ASPECT_RATIO);
        drawTree(canvas, this.mScaleX * 0.12f, this.mScaleX * 180.0f, ((this.mMoveFactor * 20.0f) + 93.0f) * this.mScaleY, this.COLOR_TREE_3_BRINK, this.COLOR_TREE_3_BRANCH);
        drawTree(canvas, this.mScaleX * 0.1f, this.mScaleX * 200.0f, ((this.mMoveFactor * 20.0f) + 96.0f) * this.mScaleY, this.COLOR_TREE_3_BRINK, this.COLOR_TREE_3_BRANCH);
        canvas.restore();
        this.mMountPaint.setColor(this.COLOR_MOUNTAIN_2);
        canvas.drawPath(this.mMount2, this.mMountPaint);
        drawTree(canvas, this.mScaleX * 0.2f, this.mScaleX * 160.0f, ((this.mMoveFactor * 30.0f) + 105.0f) * this.mScaleY, this.COLOR_TREE_1_BRINK, this.COLOR_TREE_1_BRANCH);
        drawTree(canvas, this.mScaleX * 0.14f, this.mScaleX * 180.0f, ((this.mMoveFactor * 30.0f) + 105.0f) * this.mScaleY, this.COLOR_TREE_2_BRINK, this.COLOR_TREE_2_BRANCH);
        drawTree(canvas, this.mScaleX * 0.16f, this.mScaleX * 140.0f, ((this.mMoveFactor * 30.0f) + 105.0f) * this.mScaleY, this.COLOR_TREE_2_BRINK, this.COLOR_TREE_2_BRANCH);
        this.mMountPaint.setColor(this.COLOR_MOUNTAIN_3);
        canvas.drawPath(this.mMount3, this.mMountPaint);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        this.mScaleX = (((float) measuredWidth) * 1.0f) / 240.0f;
        this.mScaleY = (((float) (this.mViewportHeight > 0 ? this.mViewportHeight : measuredHeight)) * 1.0f) / 180.0f;
        updateMountainPath(this.mMoveFactor, measuredHeight);
        updateTreePath(this.mMoveFactor, true);
    }

    public void setPrimaryColor(@ColorInt int i) {
        this.COLOR_BACKGROUND = i;
        this.COLOR_MOUNTAIN_1 = ColorUtils.compositeColors(-1711276033, i);
        this.COLOR_MOUNTAIN_2 = ColorUtils.compositeColors(-1724083556, i);
        this.COLOR_MOUNTAIN_3 = ColorUtils.compositeColors(-868327565, i);
        this.COLOR_TREE_1_BRANCH = ColorUtils.compositeColors(1428124023, i);
        this.COLOR_TREE_1_BRINK = ColorUtils.compositeColors(-871612856, i);
        this.COLOR_TREE_2_BRANCH = ColorUtils.compositeColors(1429506191, i);
        this.COLOR_TREE_2_BRINK = ColorUtils.compositeColors(-870620823, i);
        this.COLOR_TREE_3_BRANCH = ColorUtils.compositeColors(1431810478, i);
        this.COLOR_TREE_3_BRINK = ColorUtils.compositeColors(-865950547, i);
    }

    public void updatePercent(float f) {
        this.mBounceMax = f;
        float max = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, f);
        this.mMoveFactor = Math.max(CropImageView.DEFAULT_ASPECT_RATIO, this.mBounceMax);
        int measuredHeight = getMeasuredHeight();
        float f2 = this.mMoveFactor;
        if (measuredHeight <= 0) {
            measuredHeight = 180;
        }
        updateMountainPath(f2, measuredHeight);
        updateTreePath(max, false);
    }
}
