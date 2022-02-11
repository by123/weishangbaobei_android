package com.wx.assistants.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import com.wx.assistants.adapter.HeaderViewRecyclerAdapter;
import java.util.ArrayList;

public class WrapRecycleView extends RecyclerView {
    private RecyclerView.Adapter mAdapter;
    private ArrayList<View> mFooterViews = new ArrayList<>();
    private ArrayList<View> mHeaderViews = new ArrayList<>();

    public WrapRecycleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void addHeaderView(View view) {
        this.mHeaderViews.add(view);
        if (this.mAdapter != null && !(this.mAdapter instanceof HeaderViewRecyclerAdapter)) {
            this.mAdapter = new HeaderViewRecyclerAdapter(this.mHeaderViews, this.mFooterViews, this.mAdapter);
        }
    }

    public void addFooterView(View view) {
        this.mFooterViews.add(view);
        if (this.mAdapter != null && !(this.mAdapter instanceof HeaderViewRecyclerAdapter)) {
            this.mAdapter = new HeaderViewRecyclerAdapter(this.mHeaderViews, this.mFooterViews, this.mAdapter);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (this.mHeaderViews.size() > 0 || this.mFooterViews.size() > 0) {
            this.mAdapter = new HeaderViewRecyclerAdapter(this.mHeaderViews, this.mFooterViews, adapter);
        } else {
            this.mAdapter = adapter;
        }
        WrapRecycleView.super.setAdapter(this.mAdapter);
    }
}
