package com.wx.assistants.adapter;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class 87<T> extends RecyclerView.Adapter<SmartViewHolder> implements ListAdapter {
    private final DataSetObservable mDataSetObservable = new DataSetObservable();
    private int mLastPosition = -1;
    private final int mLayoutId;
    private List<T> mList;
    protected AdapterView.OnItemClickListener mListener;
    private boolean mOpenAnimationEnable = true;

    public boolean areAllItemsEnabled() {
        return true;
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public boolean isEnabled(int i) {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract void onBindViewHolder(SmartViewHolder smartViewHolder, T t, int i);

    public BaseRecyclerAdapter(@LayoutRes int i) {
        setHasStableIds(false);
        this.mList = new ArrayList();
        this.mLayoutId = i;
    }

    public BaseRecyclerAdapter(Collection<T> collection, @LayoutRes int i) {
        setHasStableIds(false);
        this.mList = new ArrayList(collection);
        this.mLayoutId = i;
    }

    public BaseRecyclerAdapter(Collection<T> collection, @LayoutRes int i, AdapterView.OnItemClickListener onItemClickListener) {
        setHasStableIds(false);
        setOnItemClickListener(onItemClickListener);
        this.mList = new ArrayList(collection);
        this.mLayoutId = i;
    }

    public void setData(Collection<T> collection) {
        this.mList = new ArrayList(collection);
    }

    private void addAnimate(SmartViewHolder smartViewHolder, int i) {
        if (this.mOpenAnimationEnable && this.mLastPosition < i) {
            smartViewHolder.itemView.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
            smartViewHolder.itemView.animate().alpha(1.0f).start();
            this.mLastPosition = i;
        }
    }

    public SmartViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SmartViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(this.mLayoutId, viewGroup, false), this.mListener);
    }

    public void onBindViewHolder(SmartViewHolder smartViewHolder, int i) {
        onBindViewHolder(smartViewHolder, i < this.mList.size() ? this.mList.get(i) : null, i);
    }

    public int getItemCount() {
        return this.mList.size();
    }

    public void onViewAttachedToWindow(SmartViewHolder smartViewHolder) {
        BaseRecyclerAdapter.super.onViewAttachedToWindow(smartViewHolder);
        addAnimate(smartViewHolder, smartViewHolder.getLayoutPosition());
    }

    public void setOpenAnimationEnable(boolean z) {
        this.mOpenAnimationEnable = z;
    }

    public T get(int i) {
        return this.mList.get(i);
    }

    public BaseRecyclerAdapter<T> setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
        return this;
    }

    public BaseRecyclerAdapter<T> refresh(Collection<T> collection) {
        this.mList.clear();
        this.mList.addAll(collection);
        notifyDataSetChanged();
        notifyListDataSetChanged();
        this.mLastPosition = -1;
        return this;
    }

    public BaseRecyclerAdapter<T> loadMore(Collection<T> collection) {
        this.mList.addAll(collection);
        notifyDataSetChanged();
        notifyListDataSetChanged();
        return this;
    }

    public BaseRecyclerAdapter<T> insert(Collection<T> collection) {
        this.mList.addAll(0, collection);
        notifyDataSetChanged();
        notifyListDataSetChanged();
        return this;
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.mDataSetObservable.registerObserver(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.mDataSetObservable.unregisterObserver(dataSetObserver);
    }

    public void notifyListDataSetChanged() {
        this.mDataSetObservable.notifyChanged();
    }

    public void notifyDataSetInvalidated() {
        this.mDataSetObservable.notifyInvalidated();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        SmartViewHolder smartViewHolder;
        if (view != null) {
            smartViewHolder = (SmartViewHolder) view.getTag();
        } else {
            smartViewHolder = onCreateViewHolder(viewGroup, getItemViewType(i));
            view = smartViewHolder.itemView;
            view.setTag(smartViewHolder);
        }
        smartViewHolder.setPosition(i);
        onBindViewHolder(smartViewHolder, i);
        addAnimate(smartViewHolder, i);
        return view;
    }

    public boolean isEmpty() {
        return getCount() == 0;
    }

    public Object getItem(int i) {
        return this.mList.get(i);
    }

    public int getCount() {
        return this.mList.size();
    }
}
