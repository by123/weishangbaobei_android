package com.yuyh.easyadapter.abslistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.yuyh.easyadapter.helper.DataHelper;
import java.util.List;

public abstract class EasyLVAdapter<T> extends BaseAdapter implements DataHelper<T> {
    protected EasyLVHolder holder = new EasyLVHolder();
    protected int[] layoutIds;
    protected Context mContext;
    protected LayoutInflater mLInflater;
    protected List<T> mList;

    public abstract void convert(EasyLVHolder easyLVHolder, int i, T t);

    public long getItemId(int i) {
        return (long) i;
    }

    public int getLayoutId(int i, T t) {
        return 0;
    }

    public int getLayoutIndex(int i, T t) {
        return 0;
    }

    public EasyLVAdapter(Context context, List<T> list, int... iArr) {
        this.mContext = context;
        this.mList = list;
        this.layoutIds = iArr;
        this.mLInflater = LayoutInflater.from(this.mContext);
    }

    public EasyLVAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mList = list;
        this.mLInflater = LayoutInflater.from(this.mContext);
    }

    public int getCount() {
        if (this.mList == null) {
            return 0;
        }
        return this.mList.size();
    }

    public Object getItem(int i) {
        if (this.mList == null) {
            return null;
        }
        return this.mList.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int viewCheckLayoutId = getViewCheckLayoutId(i);
        this.holder = this.holder.get(this.mContext, i, view, viewGroup, viewCheckLayoutId);
        convert(this.holder, i, this.mList.get(i));
        return this.holder.getConvertView(viewCheckLayoutId);
    }

    private int getViewCheckLayoutId(int i) {
        if (this.layoutIds == null || this.layoutIds.length == 0) {
            return getLayoutId(i, this.mList.get(i));
        }
        return this.layoutIds[getLayoutIndex(i, this.mList.get(i))];
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
