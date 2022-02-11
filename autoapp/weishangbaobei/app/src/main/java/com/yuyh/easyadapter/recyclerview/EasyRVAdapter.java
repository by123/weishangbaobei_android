package com.yuyh.easyadapter.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.yuyh.easyadapter.helper.DataHelper;
import java.util.List;

public abstract class EasyRVAdapter<T> extends RecyclerView.Adapter<EasyRVHolder> implements DataHelper<T> {
    protected int[] layoutIds;
    protected Context mContext;
    private SparseArray<View> mConvertViews = new SparseArray<>();
    protected LayoutInflater mLInflater;
    protected List<T> mList;

    public int getLayoutIndex(int i, T t) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public abstract void onBindData(EasyRVHolder easyRVHolder, int i, T t);

    public EasyRVAdapter(Context context, List<T> list, int... iArr) {
        this.mContext = context;
        this.mList = list;
        this.layoutIds = iArr;
        this.mLInflater = LayoutInflater.from(this.mContext);
    }

    public EasyRVHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i < 0 || i > this.layoutIds.length) {
            throw new ArrayIndexOutOfBoundsException("layoutIndex");
        } else if (this.layoutIds.length != 0) {
            int i2 = this.layoutIds[i];
            View view = this.mConvertViews.get(i2);
            if (view == null) {
                view = this.mLInflater.inflate(i2, viewGroup, false);
            }
            EasyRVHolder easyRVHolder = (EasyRVHolder) view.getTag();
            if (easyRVHolder == null || easyRVHolder.getLayoutId() != i2) {
                return new EasyRVHolder(this.mContext, i2, view);
            }
            return easyRVHolder;
        } else {
            throw new IllegalArgumentException("not layoutId");
        }
    }

    public void onBindViewHolder(EasyRVHolder easyRVHolder, int i) {
        onBindData(easyRVHolder, i, this.mList.get(i));
    }

    public int getItemCount() {
        if (this.mList == null) {
            return 0;
        }
        return this.mList.size();
    }

    public int getItemViewType(int i) {
        return getLayoutIndex(i, this.mList.get(i));
    }

    public boolean addAll(List<T> list) {
        boolean addAll = this.mList.addAll(list);
        notifyDataSetChanged();
        return addAll;
    }

    public boolean addAll(int i, List list) {
        boolean addAll = this.mList.addAll(i, list);
        notifyDataSetChanged();
        return addAll;
    }

    public void add(T t) {
        this.mList.add(t);
        notifyDataSetChanged();
    }

    public void add(int i, T t) {
        this.mList.add(i, t);
        notifyDataSetChanged();
    }

    public void clear() {
        this.mList.clear();
        notifyDataSetChanged();
    }

    public boolean contains(T t) {
        return this.mList.contains(t);
    }

    public T getData(int i) {
        return this.mList.get(i);
    }

    public void modify(T t, T t2) {
        modify(this.mList.indexOf(t), t2);
    }

    public void modify(int i, T t) {
        this.mList.set(i, t);
        notifyDataSetChanged();
    }

    public boolean remove(T t) {
        boolean remove = this.mList.remove(t);
        notifyDataSetChanged();
        return remove;
    }

    public void remove(int i) {
        this.mList.remove(i);
        notifyDataSetChanged();
    }
}
