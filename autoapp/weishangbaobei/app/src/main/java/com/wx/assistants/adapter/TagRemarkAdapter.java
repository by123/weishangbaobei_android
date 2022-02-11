package com.wx.assistants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import com.wx.assistants.adapter.TagSelectAdapter;
import java.util.List;

public class TagRemarkAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public long getItemId(int i) {
        return (long) i;
    }

    public TagRemarkAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    public int getCount() {
        return this.mList.size();
    }

    public String getItem(int i) {
        return this.mList.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        TagSelectAdapter.ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(2131427711, (ViewGroup) null);
            viewHolder = new TagSelectAdapter.ViewHolder();
            viewHolder.tagBtn = (Button) view.findViewById(2131297458);
            view.setTag(viewHolder);
        } else {
            viewHolder = (TagSelectAdapter.ViewHolder) view.getTag();
        }
        viewHolder.tagBtn.setText(getItem(i));
        return view;
    }

    static class ViewHolder {
        Button tagBtn;

        ViewHolder() {
        }
    }
}
