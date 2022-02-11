package com.luck.picture.lib.photoview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;

public class PhotoView extends ImageView {
    private PhotoViewAttacher attacher;
    private ImageView.ScaleType pendingScaleType;

    public PhotoView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @TargetApi(21)
    public PhotoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        this.attacher = new PhotoViewAttacher(this);
        super.setScaleType(ImageView.ScaleType.MATRIX);
        if (this.pendingScaleType != null) {
            setScaleType(this.pendingScaleType);
            this.pendingScaleType = null;
        }
    }

    public PhotoViewAttacher getAttacher() {
        return this.attacher;
    }

    public ImageView.ScaleType getScaleType() {
        return this.attacher.getScaleType();
    }

    public Matrix getImageMatrix() {
        return this.attacher.getImageMatrix();
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.attacher.setOnLongClickListener(onLongClickListener);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.attacher.setOnClickListener(onClickListener);
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        if (this.attacher == null) {
            this.pendingScaleType = scaleType;
        } else {
            this.attacher.setScaleType(scaleType);
        }
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (this.attacher != null) {
            this.attacher.update();
        }
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        if (this.attacher != null) {
            this.attacher.update();
        }
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (this.attacher != null) {
            this.attacher.update();
        }
    }

    /* access modifiers changed from: protected */
    public boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        if (frame) {
            this.attacher.update();
        }
        return frame;
    }

    public void setRotationTo(float f) {
        this.attacher.setRotationTo(f);
    }

    public void setRotationBy(float f) {
        this.attacher.setRotationBy(f);
    }

    @Deprecated
    public boolean isZoomEnabled() {
        return this.attacher.isZoomEnabled();
    }

    public boolean isZoomable() {
        return this.attacher.isZoomable();
    }

    public void setZoomable(boolean z) {
        this.attacher.setZoomable(z);
    }

    public RectF getDisplayRect() {
        return this.attacher.getDisplayRect();
    }

    public void getDisplayMatrix(Matrix matrix) {
        this.attacher.getDisplayMatrix(matrix);
    }

    public boolean setDisplayMatrix(Matrix matrix) {
        return this.attacher.setDisplayMatrix(matrix);
    }

    public void getSuppMatrix(Matrix matrix) {
        this.attacher.getSuppMatrix(matrix);
    }

    public boolean setSuppMatrix(Matrix matrix) {
        return this.attacher.setDisplayMatrix(matrix);
    }

    public float getMinimumScale() {
        return this.attacher.getMinimumScale();
    }

    public float getMediumScale() {
        return this.attacher.getMediumScale();
    }

    public float getMaximumScale() {
        return this.attacher.getMaximumScale();
    }

    public float getScale() {
        return this.attacher.getScale();
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.attacher.setAllowParentInterceptOnEdge(z);
    }

    public void setMinimumScale(float f) {
        this.attacher.setMinimumScale(f);
    }

    public void setMediumScale(float f) {
        this.attacher.setMediumScale(f);
    }

    public void setMaximumScale(float f) {
        this.attacher.setMaximumScale(f);
    }

    public void setScaleLevels(float f, float f2, float f3) {
        this.attacher.setScaleLevels(f, f2, f3);
    }

    public void setOnMatrixChangeListener(OnMatrixChangedListener onMatrixChangedListener) {
        this.attacher.setOnMatrixChangeListener(onMatrixChangedListener);
    }

    public void setOnPhotoTapListener(OnPhotoTapListener onPhotoTapListener) {
        this.attacher.setOnPhotoTapListener(onPhotoTapListener);
    }

    public void setOnOutsidePhotoTapListener(OnOutsidePhotoTapListener onOutsidePhotoTapListener) {
        this.attacher.setOnOutsidePhotoTapListener(onOutsidePhotoTapListener);
    }

    public void setOnViewTapListener(OnViewTapListener onViewTapListener) {
        this.attacher.setOnViewTapListener(onViewTapListener);
    }

    public void setOnViewDragListener(OnViewDragListener onViewDragListener) {
        this.attacher.setOnViewDragListener(onViewDragListener);
    }

    public void setScale(float f) {
        this.attacher.setScale(f);
    }

    public void setScale(float f, boolean z) {
        this.attacher.setScale(f, z);
    }

    public void setScale(float f, float f2, float f3, boolean z) {
        this.attacher.setScale(f, f2, f3, z);
    }

    public void setZoomTransitionDuration(int i) {
        this.attacher.setZoomTransitionDuration(i);
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.attacher.setOnDoubleTapListener(onDoubleTapListener);
    }

    public void setOnScaleChangeListener(OnScaleChangedListener onScaleChangedListener) {
        this.attacher.setOnScaleChangeListener(onScaleChangedListener);
    }

    public void setOnSingleFlingListener(OnSingleFlingListener onSingleFlingListener) {
        this.attacher.setOnSingleFlingListener(onSingleFlingListener);
    }
}
