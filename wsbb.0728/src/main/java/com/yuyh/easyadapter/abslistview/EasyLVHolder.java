package com.yuyh.easyadapter.abslistview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.socialize.ShareContent;
import com.yuyh.easyadapter.helper.ViewHelper;

public class EasyLVHolder implements ViewHelper.AbsListView<EasyLVHolder> {
    protected Context mContext;
    private View mConvertView;
    private SparseArray<View> mConvertViews = new SparseArray<>();
    protected int mLayoutId;
    protected int mPosition;
    private SparseArray<View> mViews = new SparseArray<>();

    protected EasyLVHolder() {
    }

    protected EasyLVHolder(Context context, int i, ViewGroup viewGroup, int i2) {
        this.mConvertView = this.mConvertViews.get(i2);
        this.mPosition = i;
        this.mContext = context;
        this.mLayoutId = i2;
        if (this.mConvertView == null) {
            this.mConvertView = LayoutInflater.from(context).inflate(i2, viewGroup, false);
            this.mConvertViews.put(i2, this.mConvertView);
            this.mConvertView.setTag(this);
        }
    }

    public <BVH extends EasyLVHolder> BVH get(Context context, int i, View view, ViewGroup viewGroup, int i2) {
        if (view == null) {
            return new EasyLVHolder(context, i, viewGroup, i2);
        }
        BVH bvh = (EasyLVHolder) view.getTag();
        if (bvh.mLayoutId != i2) {
            return new EasyLVHolder(context, i, viewGroup, i2);
        }
        bvh.setPosition(i);
        return bvh;
    }

    public View getConvertView() {
        return this.mConvertViews.valueAt(0);
    }

    public View getConvertView(int i) {
        return this.mConvertViews.get(i);
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

    public EasyLVHolder setAlpha(int i, float f) {
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

    public EasyLVHolder setBackgroundColor(int i, int i2) {
        getView(i).setBackgroundColor(i2);
        return this;
    }

    public EasyLVHolder setBackgroundColorRes(int i, int i2) {
        getView(i).setBackgroundResource(i2);
        return this;
    }

    public EasyLVHolder setChecked(int i, boolean z) {
        ((Checkable) getView(i)).setChecked(z);
        return this;
    }

    public EasyLVHolder setImageBitmap(int i, Bitmap bitmap) {
        ((ImageView) getView(i)).setImageBitmap(bitmap);
        return this;
    }

    public EasyLVHolder setImageDrawable(int i, Drawable drawable) {
        ((ImageView) getView(i)).setImageDrawable(drawable);
        return this;
    }

    public EasyLVHolder setImageDrawableRes(int i, int i2) {
        return setImageDrawable(i, this.mContext.getResources().getDrawable(i2, (Resources.Theme) null));
    }

    public EasyLVHolder setImageResource(int i, int i2) {
        ((ImageView) getView(i)).setImageResource(i2);
        return this;
    }

    public EasyLVHolder setImageUrl(int i, String str) {
        return this;
    }

    public EasyLVHolder setOnClickListener(int i, View.OnClickListener onClickListener) {
        getView(i).setOnClickListener(onClickListener);
        return this;
    }

    public void setPosition(int i) {
        this.mPosition = i;
    }

    public EasyLVHolder setTag(int i, int i2, Object obj) {
        getView(i).setTag(i2, obj);
        return this;
    }

    public EasyLVHolder setTag(int i, Object obj) {
        getView(i).setTag(obj);
        return this;
    }

    public EasyLVHolder setText(int i, String str) {
        ((TextView) getView(i)).setText(str);
        return this;
    }

    public EasyLVHolder setTextColor(int i, int i2) {
        ((TextView) getView(i)).setTextColor(i2);
        return this;
    }

    public EasyLVHolder setTextColorRes(int i, int i2) {
        ((TextView) getView(i)).setTextColor(this.mContext.getResources().getColor(i2, (Resources.Theme) null));
        return this;
    }

    public EasyLVHolder setTypeface(int i, Typeface typeface) {
        TextView textView = (TextView) getView(i);
        textView.setTypeface(typeface);
        textView.setPaintFlags(textView.getPaintFlags() | ShareContent.MINAPP_STYLE);
        return this;
    }

    public EasyLVHolder setTypeface(Typeface typeface, int... iArr) {
        for (int view : iArr) {
            TextView textView = (TextView) getView(view);
            textView.setTypeface(typeface);
            textView.setPaintFlags(textView.getPaintFlags() | ShareContent.MINAPP_STYLE);
        }
        return this;
    }

    public EasyLVHolder setVisible(int i, int i2) {
        getView(i).setVisibility(i2);
        return this;
    }

    public EasyLVHolder setVisible(int i, boolean z) {
        getView(i).setVisibility(z ? 0 : 8);
        return this;
    }
}
