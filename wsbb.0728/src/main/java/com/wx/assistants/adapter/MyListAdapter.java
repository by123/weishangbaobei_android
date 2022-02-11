package com.wx.assistants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.bean.LabelBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<LabelBean> labList = new ArrayList();
    public Map<Integer, Boolean> map = new HashMap();
    /* access modifiers changed from: private */
    public OnClickTagNameListener onClickTagNameListener;

    public static class MyViewHolder {
        TextView label_name_text;
        LinearLayout list_item_layout;
        CheckBox mCheckBox;
        TextView mTextView;
        TextView showMemberText;
    }

    public interface OnClickTagNameListener {
        void click(int i);
    }

    public MyListAdapter(List<LabelBean> list, Map<Integer, Boolean> map2, Context context) {
        this.labList.clear();
        this.labList = list;
        this.inflater = LayoutInflater.from(context);
        if (map2 != null && map2.size() > 0) {
            this.map = map2;
        } else if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                this.map.put(Integer.valueOf(i), false);
            }
        }
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

    public View getView(final int i, View view, ViewGroup viewGroup) {
        final MyViewHolder myViewHolder;
        if (view == null) {
            view = this.inflater.inflate(2131427569, (ViewGroup) null);
            myViewHolder = new MyViewHolder();
            myViewHolder.list_item_layout = (LinearLayout) view.findViewById(2131296955);
            myViewHolder.mTextView = (TextView) view.findViewById(2131296887);
            myViewHolder.label_name_text = (TextView) view.findViewById(2131296889);
            myViewHolder.showMemberText = (TextView) view.findViewById(2131297374);
            myViewHolder.mCheckBox = (CheckBox) view.findViewById(2131296482);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        if (this.labList != null && this.labList.size() > 0) {
            LabelBean labelBean = this.labList.get(i);
            myViewHolder.mTextView.setText(labelBean.getLabelName());
            myViewHolder.label_name_text.setText(labelBean.getLabelNameText());
        }
        myViewHolder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    MyListAdapter.this.map.put(Integer.valueOf(i), true);
                } else {
                    MyListAdapter.this.map.put(Integer.valueOf(i), false);
                }
            }
        });
        myViewHolder.list_item_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                myViewHolder.mCheckBox.performClick();
            }
        });
        if (this.map != null && this.map.size() > 0) {
            myViewHolder.mCheckBox.setChecked(this.map.get(Integer.valueOf(i)).booleanValue());
        }
        myViewHolder.showMemberText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MyListAdapter.this.onClickTagNameListener != null) {
                    MyListAdapter.this.onClickTagNameListener.click(i);
                }
            }
        });
        return view;
    }

    public void setOnClickTagNameListener(OnClickTagNameListener onClickTagNameListener2) {
        this.onClickTagNameListener = onClickTagNameListener2;
    }

    public void setSelectMap(Map<Integer, Boolean> map2) {
        this.map = new HashMap();
        this.map = map2;
    }
}
