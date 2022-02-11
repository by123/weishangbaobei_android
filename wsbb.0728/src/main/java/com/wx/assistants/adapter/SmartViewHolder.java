package com.wx.assistants.adapter;

import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class SmartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final AdapterView.OnItemClickListener mListener;
    private int mPosition = -1;

    public SmartViewHolder(View view, AdapterView.OnItemClickListener onItemClickListener) {
        super(view);
        this.mListener = onItemClickListener;
        view.setOnClickListener(this);
        if (view.getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = view.getContext().getTheme();
            int paddingTop = view.getPaddingTop();
            int paddingBottom = view.getPaddingBottom();
            int paddingLeft = view.getPaddingLeft();
            int paddingRight = view.getPaddingRight();
            if (theme.resolveAttribute(16843534, typedValue, true)) {
                view.setBackgroundResource(typedValue.resourceId);
            }
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
    }

    private View findViewById(int i) {
        return i == 0 ? this.itemView : this.itemView.findViewById(i);
    }

    public SmartViewHolder adapter(int i, BaseAdapter baseAdapter) {
        View findViewById = findViewById(i);
        if (findViewById instanceof GridView) {
            ((GridView) findViewById).setAdapter(baseAdapter);
        }
        return this;
    }

    public SmartViewHolder click(int i, View.OnClickListener onClickListener) {
        findViewById(i).setOnClickListener(onClickListener);
        return this;
    }

    public SmartViewHolder enable(int i, boolean z) {
        findViewById(i).setEnabled(z);
        return this;
    }

    public SmartViewHolder gone(int i) {
        View findViewById = findViewById(i);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        return this;
    }

    public SmartViewHolder image(int i, int i2) {
        View findViewById = findViewById(i);
        if (findViewById instanceof ImageView) {
            ((ImageView) findViewById).setImageResource(i2);
        }
        return this;
    }

    public void onClick(View view) {
        if (this.mListener != null) {
            int adapterPosition = getAdapterPosition();
            if (adapterPosition >= 0) {
                this.mListener.onItemClick((AdapterView) null, view, adapterPosition, getItemId());
            } else if (this.mPosition > -1) {
                this.mListener.onItemClick((AdapterView) null, view, this.mPosition, getItemId());
            }
        }
    }

    public void setPosition(int i) {
        this.mPosition = i;
    }

    public SmartViewHolder text(int i, @StringRes int i2) {
        View findViewById = findViewById(i);
        if (findViewById instanceof TextView) {
            ((TextView) findViewById).setText(i2);
        }
        return this;
    }

    public SmartViewHolder text(int i, CharSequence charSequence) {
        View findViewById = findViewById(i);
        if (findViewById instanceof TextView) {
            ((TextView) findViewById).setText(charSequence);
        }
        return this;
    }

    public SmartViewHolder textColorId(int i, int i2) {
        View findViewById = findViewById(i);
        if (findViewById instanceof TextView) {
            ((TextView) findViewById).setTextColor(ContextCompat.getColor(findViewById.getContext(), i2));
        }
        return this;
    }

    public SmartViewHolder visible(int i) {
        View findViewById = findViewById(i);
        if (findViewById != null) {
            findViewById.setVisibility(0);
        }
        return this;
    }
}
