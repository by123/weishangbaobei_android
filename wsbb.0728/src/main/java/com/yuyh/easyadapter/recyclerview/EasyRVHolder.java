package com.yuyh.easyadapter.recyclerview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.socialize.ShareContent;
import com.yuyh.easyadapter.helper.ViewHelper;

public class EasyRVHolder extends RecyclerView.ViewHolder implements ViewHelper.RecyclerView<EasyRVHolder> {
    protected Context mContext;
    private View mConvertView;
    private int mLayoutId;
    private SparseArray<View> mViews = new SparseArray<>();

    public EasyRVHolder(Context context, int i, View view) {
        super(view);
        this.mContext = context;
        this.mLayoutId = i;
        this.mConvertView = view;
        this.mConvertView.setTag(this);
    }

    public View getItemView() {
        return this.mConvertView;
    }

    public int getLayoutId() {
        return this.mLayoutId;
    }

    public <V extends View> V getView(int i) {
        V v = (View) this.mViews.get(i);
        if (v != null) {
            return v;
        }
        V findViewById = this.mConvertView.findViewById(i);
        this.mViews.put(i, findViewById);
        return findViewById;
    }

    public EasyRVHolder setAlpha(int i, float f) {
        if (Build.VERSION.SDK_INT >= 11) {
            getView(i).setAlpha(f);
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(f, f);
            alphaAnimation.setDuration(0);
            alphaAnimation.setFillAfter(true);
            getView(i).startAnimation(alphaAnimation);
        }
        return this;
    }

    public EasyRVHolder setBackgroundColor(int i, int i2) {
        getView(i).setBackgroundColor(i2);
        return this;
    }

    public EasyRVHolder setBackgroundColorRes(int i, int i2) {
        getView(i).setBackgroundResource(i2);
        return this;
    }

    public EasyRVHolder setChecked(int i, boolean z) {
        ((Checkable) getView(i)).setChecked(z);
        return this;
    }

    public EasyRVHolder setImageBitmap(int i, Bitmap bitmap) {
        ((ImageView) getView(i)).setImageBitmap(bitmap);
        return this;
    }

    public EasyRVHolder setImageDrawable(int i, Drawable drawable) {
        ((ImageView) getView(i)).setImageDrawable(drawable);
        return this;
    }

    public EasyRVHolder setImageDrawableRes(int i, int i2) {
        return setImageDrawable(i, this.mContext.getResources().getDrawable(i2, (Resources.Theme) null));
    }

    public EasyRVHolder setImageResource(int i, int i2) {
        ((ImageView) getView(i)).setImageResource(i2);
        return this;
    }

    public EasyRVHolder setImageUrl(int i, String str) {
        return this;
    }

    public EasyRVHolder setOnClickListener(int i, View.OnClickListener onClickListener) {
        getView(i).setOnClickListener(onClickListener);
        return this;
    }

    public EasyRVHolder setOnItemViewClickListener(View.OnClickListener onClickListener) {
        this.mConvertView.setOnClickListener(onClickListener);
        return this;
    }

    public EasyRVHolder setTag(int i, int i2, Object obj) {
        getView(i).setTag(i2, obj);
        return this;
    }

    public EasyRVHolder setTag(int i, Object obj) {
        getView(i).setTag(obj);
        return this;
    }

    public EasyRVHolder setText(int i, String str) {
        ((TextView) getView(i)).setText(str);
        return this;
    }

    public EasyRVHolder setTextColor(int i, int i2) {
        ((TextView) getView(i)).setTextColor(i2);
        return this;
    }

    public EasyRVHolder setTextColorRes(int i, int i2) {
        ((TextView) getView(i)).setTextColor(this.mContext.getResources().getColor(i2, (Resources.Theme) null));
        return this;
    }

    public EasyRVHolder setTypeface(int i, Typeface typeface) {
        TextView textView = (TextView) getView(i);
        textView.setTypeface(typeface);
        textView.setPaintFlags(textView.getPaintFlags() | ShareContent.MINAPP_STYLE);
        return this;
    }

    public EasyRVHolder setTypeface(Typeface typeface, int... iArr) {
        for (int view : iArr) {
            TextView textView = (TextView) getView(view);
            textView.setTypeface(typeface);
            textView.setPaintFlags(textView.getPaintFlags() | ShareContent.MINAPP_STYLE);
        }
        return this;
    }

    public EasyRVHolder setVisible(int i, int i2) {
        getView(i).setVisibility(i2);
        return this;
    }

    public EasyRVHolder setVisible(int i, boolean z) {
        getView(i).setVisibility(z ? 0 : 8);
        return this;
    }
}
