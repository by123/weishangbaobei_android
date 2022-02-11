package com.wx.assistants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class LabelRemarkSelectAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    static class ViewHolder {
        TextView tagBtn;

        ViewHolder() {
        }
    }

    public LabelRemarkSelectAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    public int getCount() {
        return this.mList.size();
    }

    public String getItem(int i) {
        return this.mList.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(2131427762, (ViewGroup) null);
            ViewHolder viewHolder2 = new ViewHolder();
            viewHolder2.tagBtn = (TextView) view.findViewById(2131297458);
            view.setTag(viewHolder2);
            viewHolder = viewHolder2;
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tagBtn.setText(getItem(i));
        return view;
    }
}
