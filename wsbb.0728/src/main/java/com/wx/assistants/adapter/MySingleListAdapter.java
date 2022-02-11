package com.wx.assistants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.bean.LabelBean;
import java.util.ArrayList;
import java.util.List;

public class MySingleListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    /* access modifiers changed from: private */
    public List<LabelBean> labList = new ArrayList();
    private Context mContext;
    /* access modifiers changed from: private */
    public OnClickTagNameListener onClickTagNameListener;
    /* access modifiers changed from: private */
    public OnSelectLabelListener onSelectLabelListener;

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

    public interface OnSelectLabelListener {
        void nuSelect(LabelBean labelBean);

        void select(LabelBean labelBean);
    }

    public MySingleListAdapter(List<LabelBean> list, LabelBean labelBean, Context context) {
        this.mContext = context;
        this.labList.clear();
        this.labList = list;
        this.inflater = LayoutInflater.from(context);
        if (list != null && list.size() > 0 && labelBean != null) {
            for (int i = 0; i < list.size(); i++) {
                LabelBean labelBean2 = list.get(i);
                if (labelBean == null || !labelBean2.getLabelName().equals(labelBean.getLabelName())) {
                    this.labList.get(i).setSelected(false);
                } else {
                    this.labList.get(i).setSelected(true);
                }
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
            myViewHolder.mCheckBox = (CheckBox) view.findViewById(2131296482);
            myViewHolder.showMemberText = (TextView) view.findViewById(2131297374);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        final LabelBean labelBean = (LabelBean) getItem(i);
        myViewHolder.mTextView.setText(labelBean.getLabelName());
        myViewHolder.label_name_text.setText(labelBean.getLabelNameText());
        myViewHolder.list_item_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                myViewHolder.mCheckBox.performClick();
            }
        });
        myViewHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int i = 0;
                while (i < MySingleListAdapter.this.labList.size()) {
                    try {
                        if (i != i) {
                            ((LabelBean) MySingleListAdapter.this.labList.get(i)).setSelected(false);
                        }
                        i++;
                    } catch (Exception e) {
                        return;
                    }
                }
                if (((LabelBean) MySingleListAdapter.this.labList.get(i)).isSelected()) {
                    ((LabelBean) MySingleListAdapter.this.labList.get(i)).setSelected(false);
                    if (MySingleListAdapter.this.onSelectLabelListener != null) {
                        MySingleListAdapter.this.onSelectLabelListener.nuSelect(labelBean);
                    }
                } else {
                    ((LabelBean) MySingleListAdapter.this.labList.get(i)).setSelected(true);
                    if (MySingleListAdapter.this.onSelectLabelListener != null) {
                        MySingleListAdapter.this.onSelectLabelListener.select(labelBean);
                    }
                }
                MySingleListAdapter.this.notifyDataSetChanged();
            }
        });
        myViewHolder.list_item_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                myViewHolder.mCheckBox.performClick();
            }
        });
        myViewHolder.mCheckBox.setChecked(this.labList.get(i).isSelected());
        myViewHolder.showMemberText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MySingleListAdapter.this.onClickTagNameListener != null) {
                    MySingleListAdapter.this.onClickTagNameListener.click(i);
                }
            }
        });
        return view;
    }

    public void setOnClickTagNameListener(OnClickTagNameListener onClickTagNameListener2) {
        this.onClickTagNameListener = onClickTagNameListener2;
    }

    public void setOnSelectLabelListener(OnSelectLabelListener onSelectLabelListener2) {
        this.onSelectLabelListener = onSelectLabelListener2;
    }
}
