package com.wx.assistants.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class HeaderViewRecyclerAdapter extends RecyclerView.Adapter {
    private static final int STATE_FOOTER = -1;
    private static final int STATE_HEADER = 1;
    private int footerIndex = 0;
    private int headerIndex = 0;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<View> mFooterViews;
    private ArrayList<View> mHeaderViews;

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View view) {
            super(view);
        }
    }

    public HeaderViewRecyclerAdapter(ArrayList<View> arrayList, ArrayList<View> arrayList2, RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
        if (arrayList == null) {
            this.mHeaderViews = new ArrayList<>();
        } else {
            this.mHeaderViews = arrayList;
        }
        if (arrayList2 == null) {
            this.mFooterViews = new ArrayList<>();
        } else {
            this.mFooterViews = arrayList2;
        }
    }

    public int getFootersCount() {
        return this.mFooterViews.size();
    }

    public int getHeadersCount() {
        return this.mHeaderViews.size();
    }

    public int getItemCount() {
        return this.mAdapter != null ? getFootersCount() + getHeadersCount() + this.mAdapter.getItemCount() : getFootersCount() + getHeadersCount();
    }

    public int getItemViewType(int i) {
        int headersCount = getHeadersCount();
        if (i < headersCount) {
            return 1;
        }
        int i2 = i - headersCount;
        if (this.mAdapter == null || i2 >= this.mAdapter.getItemCount()) {
            return -1;
        }
        return this.mAdapter.getItemViewType(i2);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int headersCount = getHeadersCount();
        if (i >= headersCount) {
            int i2 = i - headersCount;
            if (this.mAdapter != null && i2 < this.mAdapter.getItemCount()) {
                this.mAdapter.onBindViewHolder(viewHolder, i2);
            }
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            ArrayList<View> arrayList = this.mHeaderViews;
            int i2 = this.headerIndex;
            this.headerIndex = i2 + 1;
            return new HeaderViewHolder(arrayList.get(i2));
        } else if (i != -1) {
            return this.mAdapter.onCreateViewHolder(viewGroup, i);
        } else {
            ArrayList<View> arrayList2 = this.mFooterViews;
            int i3 = this.footerIndex;
            this.footerIndex = i3 + 1;
            return new HeaderViewHolder(arrayList2.get(i3));
        }
    }
}
