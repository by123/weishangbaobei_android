package com.wx.assistants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.Enity.TagPeoplesBean;
import java.util.ArrayList;
import java.util.List;

public class TagMemberListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<String> labList = new ArrayList();

    public static class MyViewHolder {
        TextView label_name_position;
        LinearLayout list_item_layout;
        TextView mTextView;
    }

    public TagMemberListAdapter(List<TagPeoplesBean> list, Context context) {
        this.labList.clear();
        if (list != null && list.size() > 0) {
            String wxPeoples = list.get(0).getWxPeoples();
            if (wxPeoples.contains(";")) {
                String[] split = wxPeoples.split(";");
                for (String add : split) {
                    this.labList.add(add);
                }
            } else {
                this.labList.add(wxPeoples);
            }
        }
        this.inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.labList.size();
    }

    public Object getItem(int i) {
        return this.labList.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder;
        if (view == null) {
            view = this.inflater.inflate(2131427710, (ViewGroup) null);
            myViewHolder = new MyViewHolder();
            myViewHolder.list_item_layout = (LinearLayout) view.findViewById(2131296955);
            myViewHolder.mTextView = (TextView) view.findViewById(2131296887);
            myViewHolder.label_name_position = (TextView) view.findViewById(2131296888);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        if (this.labList != null && this.labList.size() > 0) {
            myViewHolder.mTextView.setText(this.labList.get(i));
            TextView textView = myViewHolder.label_name_position;
            textView.setText((i + 1) + "");
        }
        return view;
    }
}
