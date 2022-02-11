package com.yongchun.library.view.photoview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;

public class PhotoView extends AppCompatImageView {
    private PhotoViewAttacher2 attacher;
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

    /* JADX WARNING: type inference failed for: r1v0, types: [com.yongchun.library.view.photoview.PhotoView, android.support.v7.widget.AppCompatImageView, android.widget.ImageView] */
    private void init() {
        this.attacher = new PhotoViewAttacher2(this);
        PhotoView.super.setScaleType(ImageView.ScaleType.MATRIX);
        if (this.pendingScaleType != null) {
            setScaleType(this.pendingScaleType);
            this.pendingScaleType = null;
        }
    }

    public PhotoViewAttacher2 getAttacher() {
        return this.attacher;
    }

    public void getDisplayMatrix(Matrix matrix) {
        this.attacher.getDisplayMatrix(matrix);
    }

    public RectF getDisplayRect() {
        return this.attacher.getDisplayRect();
    }

    public Matrix getImageMatrix() {
        return this.attacher.getImageMatrix();
    }

    public float getMaximumScale() {
        return this.attacher.getMaximumScale();
    }

    public float getMediumScale() {
        return this.attacher.getMediumScale();
    }

    public float getMinimumScale() {
        return this.attacher.getMinimumScale();
    }

    public float getScale() {
        return this.attacher.getScale();
    }

    public ImageView.ScaleType getScaleType() {
        return this.attacher.getScaleType();
    }

    public void getSuppMatrix(Matrix matrix) {
        this.attacher.getSuppMatrix(matrix);
    }

    public boolean isZoomable() {
        return this.attacher.isZoomable();
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.attacher.setAllowParentInterceptOnEdge(z);
    }

    public boolean setDisplayMatrix(Matrix matrix) {
        return this.attacher.setDisplayMatrix(matrix);
    }

    /* access modifiers changed from: protected */
    public boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = PhotoView.super.setFrame(i, i2, i3, i4);
        if (frame) {
            this.attacher.update();
        }
        return frame;
    }

    public void setImageDrawable(Drawable drawable) {
        PhotoView.super.setImageDrawable(drawable);
        if (this.attacher != null) {
            this.attacher.update();
        }
    }

    public void setImageResource(int i) {
        PhotoView.super.setImageResource(i);
        if (this.attacher != null) {
            this.attacher.update();
        }
    }

    public void setImageURI(Uri uri) {
        PhotoView.super.setImageURI(uri);
        if (this.attacher != null) {
            this.attacher.update();
        }
    }

    public void setMaximumScale(float f) {
        this.attacher.setMaximumScale(f);
    }

    public void setMediumScale(float f) {
        this.attacher.setMediumScale(f);
    }

    public void setMinimumScale(float f) {
        this.attacher.setMinimumScale(f);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.attacher.setOnClickListener(onClickListener);
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.attacher.setOnDoubleTapListener(onDoubleTapListener);
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.attacher.setOnLongClickListener(onLongClickListener);
    }

    public void setOnMatrixChangeListener(OnMatrixChangedListener onMatrixChangedListener) {
        this.attacher.setOnMatrixChangeListener(onMatrixChangedListener);
    }

    public void setOnOutsidePhotoTapListener(OnOutsidePhotoTapListener onOutsidePhotoTapListener) {
        this.attacher.setOnOutsidePhotoTapListener(onOutsidePhotoTapListener);
    }

    public void setOnPhotoTapListener(OnPhotoTapListener onPhotoTapListener) {
        this.attacher.setOnPhotoTapListener(onPhotoTapListener);
    }

    public void setOnScaleChangeListener(OnScaleChangedListener2 onScaleChangedListener2) {
        this.attacher.setOnScaleChangeListener(onScaleChangedListener2);
    }

    public void setOnSingleFlingListener(OnSingleFlingListener onSingleFlingListener) {
        this.attacher.setOnSingleFlingListener(onSingleFlingListener);
    }

    public void setOnViewDragListener(OnViewDragListener onViewDragListener) {
        this.attacher.setOnViewDragListener(onViewDragListener);
    }

    public void setOnViewTapListener(OnViewTapListener2 onViewTapListener2) {
        this.attacher.setOnViewTapListener(onViewTapListener2);
    }

    public void setRotationBy(float f) {
        this.attacher.setRotationBy(f);
    }

    public void setRotationTo(float f) {
        this.attacher.setRotationTo(f);
    }

    public void setScale(float f) {
        this.attacher.setScale(f);
    }

    public void setScale(float f, float f2, float f3, boolean z) {
        this.attacher.setScale(f, f2, f3, z);
    }

    public void setScale(float f, boolean z) {
        this.attacher.setScale(f, z);
    }

    public void setScaleLevels(float f, float f2, float f3) {
        this.attacher.setScaleLevels(f, f2, f3);
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        if (this.attacher == null) {
            this.pendingScaleType = scaleType;
        } else {
            this.attacher.setScaleType(scaleType);
        }
    }

    public boolean setSuppMatrix(Matrix matrix) {
        return this.attacher.setDisplayMatrix(matrix);
    }

    public void setZoomTransitionDuration(int i) {
        this.attacher.setZoomTransitionDuration(i);
    }

    public void setZoomable(boolean z) {
        this.attacher.setZoomable(z);
    }
}
