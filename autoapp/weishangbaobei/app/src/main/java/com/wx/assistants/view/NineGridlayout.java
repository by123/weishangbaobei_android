package com.wx.assistants.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wx.assistants.application.MyApplication;
import java.util.List;

public class NineGridlayout extends ViewGroup {
    private int columns;
    private int gap = 10;
    private List<String> listData;
    /* access modifiers changed from: private */
    public OnClickPicListener onClickPicListener;
    private int rows;
    private int totalWidth;

    public interface OnClickPicListener {
        void click(int i);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public void setOnClickPicListener(OnClickPicListener onClickPicListener2) {
        this.onClickPicListener = onClickPicListener2;
    }

    public NineGridlayout(Context context) {
        super(context);
    }

    public NineGridlayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ScreenTools instance = ScreenTools.instance(getContext());
        this.totalWidth = instance.getScreenWidth() - instance.dip2px(75);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    private void layoutChildrenView() {
        int size = this.listData.size();
        int i = (this.totalWidth - (this.gap * 2)) / 3;
        double d = (double) i;
        Double.isNaN(d);
        int i2 = (int) (d * 1.8d);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = (this.rows * i2) + (this.gap * (this.rows - 1));
        setLayoutParams(layoutParams);
        for (int i3 = 0; i3 < size; i3++) {
            ImageView imageView = (ImageView) getChildAt(i3);
            if (imageView != null) {
                Glide.with(MyApplication.getConText()).load(this.listData.get(i3)).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(new ColorDrawable(Color.parseColor("#f5f5f5"))).into(imageView);
                int[] findPosition = findPosition(i3);
                int i4 = (this.gap + i) * findPosition[1];
                int i5 = (this.gap + i2) * findPosition[0];
                imageView.layout(i4, i5, i4 + i, i5 + i2);
            }
        }
    }

    private int[] findPosition(int i) {
        int[] iArr = new int[2];
        for (int i2 = 0; i2 < this.rows; i2++) {
            int i3 = 0;
            while (true) {
                if (i3 >= this.columns) {
                    break;
                } else if ((this.columns * i2) + i3 == i) {
                    iArr[0] = i2;
                    iArr[1] = i3;
                    break;
                } else {
                    i3++;
                }
            }
        }
        return iArr;
    }

    public int getGap() {
        return this.gap;
    }

    public void setGap(int i) {
        this.gap = i;
    }

    public void setImagesData(List<String> list) {
        if (list != null && !list.isEmpty()) {
            generateChildrenLayout(list.size());
            for (int i = 0; i < list.size(); i++) {
                addView(generateImageView(i), generateDefaultLayoutParams());
            }
            this.listData = list;
            layoutChildrenView();
        }
    }

    private void generateChildrenLayout(int i) {
        if (i <= 3) {
            this.rows = 1;
            this.columns = i;
        } else if (i <= 6) {
            this.rows = 2;
            this.columns = 3;
            if (i == 4) {
                this.columns = 2;
            }
        } else {
            this.rows = 3;
            this.columns = 3;
        }
    }

    private ImageView generateImageView(final int i) {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NineGridlayout.this.onClickPicListener.click(i);
            }
        });
        imageView.setBackgroundColor(Color.parseColor("#f5f5f5"));
        return imageView;
    }
}
