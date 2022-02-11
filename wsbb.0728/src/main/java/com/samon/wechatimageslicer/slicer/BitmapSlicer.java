package com.samon.wechatimageslicer.slicer;

import android.graphics.Bitmap;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

public abstract class BitmapSlicer {
    private static final int PIC_BORDER_LEN = 667;
    private static final int PIC_DIVIDER_LEN = 24;
    private int mAspectX;
    private int mAspectY;
    private BitmapSliceListener mListener;
    private Bitmap srcBitmap;

    public interface BitmapSliceListener {
        void onSliceFailed();

        void onSliceSuccess(Bitmap bitmap, List<Bitmap> list);
    }

    protected BitmapSlicer() {
        int horizontalPicNumber = getHorizontalPicNumber();
        int verticalPicNumber = getVerticalPicNumber();
        this.mAspectX = ((horizontalPicNumber - 1) * 24) + (horizontalPicNumber * PIC_BORDER_LEN);
        this.mAspectY = (verticalPicNumber * PIC_BORDER_LEN) + ((verticalPicNumber - 1) * 24);
    }

    private int compareRate(int i, int i2) {
        double aspectX = (double) getAspectX();
        Double.isNaN(aspectX);
        double aspectY = (double) getAspectY();
        Double.isNaN(aspectY);
        double d = (aspectX * 1.0d) / aspectY;
        double d2 = (double) i;
        Double.isNaN(d2);
        double d3 = (double) i2;
        Double.isNaN(d3);
        double d4 = (d2 * 1.0d) / d3;
        if (d < d4) {
            return 1;
        }
        return d > d4 ? -1 : 0;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public List<Bitmap> doSlice(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int horizontalPicNumber = getHorizontalPicNumber();
        int verticalPicNumber = getVerticalPicNumber();
        int i = (width * PIC_BORDER_LEN) / this.mAspectX;
        int i2 = (height * PIC_BORDER_LEN) / this.mAspectY;
        int i3 = (width * 24) / this.mAspectX;
        int i4 = (height * 24) / this.mAspectY;
        ArrayList arrayList = new ArrayList();
        for (int i5 = 0; i5 < verticalPicNumber; i5++) {
            for (int i6 = 0; i6 < horizontalPicNumber; i6++) {
                arrayList.add(Bitmap.createBitmap(bitmap, (i + i3) * i6, (i2 + i4) * i5, i, i2));
            }
        }
        return arrayList;
    }

    public static /* synthetic */ void lambda$slice$0(BitmapSlicer bitmapSlicer, List list) throws Exception {
        if (bitmapSlicer.mListener == null) {
            return;
        }
        if (list != null) {
            bitmapSlicer.mListener.onSliceSuccess(bitmapSlicer.srcBitmap, list);
        } else {
            bitmapSlicer.mListener.onSliceFailed();
        }
    }

    public static /* synthetic */ void lambda$slice$1(BitmapSlicer bitmapSlicer, Throwable th) throws Exception {
        th.printStackTrace();
        if (bitmapSlicer.mListener != null) {
            bitmapSlicer.mListener.onSliceFailed();
        }
    }

    public final int calculateOutputX(int i, int i2) {
        switch (compareRate(i, i2)) {
            case -1:
            case 0:
                return i;
            case 1:
                return (getAspectX() * i2) / getAspectY();
            default:
                return 0;
        }
    }

    public final int calculateOutputY(int i, int i2) {
        switch (compareRate(i, i2)) {
            case -1:
                return (getAspectY() * i) / getAspectX();
            case 0:
            case 1:
                return i2;
            default:
                return 0;
        }
    }

    public final int getAspectX() {
        return this.mAspectX;
    }

    public final int getAspectY() {
        return this.mAspectY;
    }

    /* access modifiers changed from: protected */
    public abstract int getHorizontalPicNumber();

    /* access modifiers changed from: protected */
    public abstract int getVerticalPicNumber();

    public final BitmapSlicer registerListener(BitmapSliceListener bitmapSliceListener) {
        this.mListener = bitmapSliceListener;
        return this;
    }

    public final BitmapSlicer setSrcBitmap(Bitmap bitmap) {
        this.srcBitmap = bitmap;
        return this;
    }

    @MainThread
    public final void slice() {
        Observable.just(this.srcBitmap).map(new Function() {
            public final Object apply(Object obj) {
                return BitmapSlicer.this.doSlice((Bitmap) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public final void accept(Object obj) {
                BitmapSlicer.lambda$slice$0(BitmapSlicer.this, (List) obj);
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                BitmapSlicer.lambda$slice$1(BitmapSlicer.this, (Throwable) obj);
            }
        });
    }
}
