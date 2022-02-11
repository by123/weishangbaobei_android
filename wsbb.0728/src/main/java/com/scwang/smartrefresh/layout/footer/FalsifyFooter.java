package com.scwang.smartrefresh.layout.footer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.scwang.smartrefresh.layout.R;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.util.DensityUtil;

public class FalsifyFooter extends InternalAbstract implements RefreshFooter {
    private RefreshKernel mRefreshKernel;

    public FalsifyFooter(Context context) {
        this(context, (AttributeSet) null);
    }

    public FalsifyFooter(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FalsifyFooter(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int dp2px = DensityUtil.dp2px(5.0f);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(-858993460);
            paint.setStrokeWidth((float) DensityUtil.dp2px(1.0f));
            float f = (float) dp2px;
            paint.setPathEffect(new DashPathEffect(new float[]{f, f, f, f}, 1.0f));
            canvas.drawRect(f, f, (float) (getWidth() - dp2px), (float) (getBottom() - dp2px), paint);
            TextView textView = new TextView(getContext());
            textView.setText(getResources().getString(R.string.srl_component_falsify, new Object[]{getClass().getSimpleName(), Float.valueOf(DensityUtil.px2dp(getHeight()))}));
            textView.setTextColor(-858993460);
            textView.setGravity(17);
            textView.measure(View.MeasureSpec.makeMeasureSpec(getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getHeight(), 1073741824));
            textView.layout(0, 0, getWidth(), getHeight());
            textView.draw(canvas);
        }
    }

    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
        this.mRefreshKernel = refreshKernel;
        refreshKernel.getRefreshLayout().setEnableAutoLoadMore(false);
    }

    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        if (this.mRefreshKernel != null) {
            this.mRefreshKernel.setState(RefreshState.None);
            this.mRefreshKernel.setState(RefreshState.LoadFinish);
        }
    }

    public boolean setNoMoreData(boolean z) {
        return false;
    }
}
