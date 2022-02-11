package com.wx.assistants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.bean.DeviceBean;
import java.util.ArrayList;
import java.util.List;

public class ChangeDeviceAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    /* access modifiers changed from: private */
    public List<DeviceBean> mDatas = new ArrayList();
    /* access modifiers changed from: private */
    public OnSelectDeviceListener onSelectDeviceListener;

    public static class MyViewHolder {
        TextView label_name_text;
        LinearLayout list_item_layout;
        CheckBox mCheckBox;
        TextView mTextView;
    }

    public interface OnSelectDeviceListener {
        void select(DeviceBean deviceBean);
    }

    public ChangeDeviceAdapter(Context context, List<DeviceBean> list) {
        this.mDatas.clear();
        this.mDatas = list;
        this.inflater = LayoutInflater.from(context);
        for (int i = 0; i < this.mDatas.size(); i++) {
            DeviceBean deviceBean = this.mDatas.get(i);
            if (i == 0) {
                deviceBean.setSelected(true);
            } else {
                deviceBean.setSelected(false);
            }
        }
    }

    public int getCount() {
        return this.mDatas.size();
    }

    public Object getItem(int i) {
        return this.mDatas.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        final MyViewHolder myViewHolder;
        if (view == null) {
            view = this.inflater.inflate(2131427495, (ViewGroup) null);
            myViewHolder = new MyViewHolder();
            myViewHolder.list_item_layout = (LinearLayout) view.findViewById(2131296955);
            myViewHolder.mTextView = (TextView) view.findViewById(2131296887);
            myViewHolder.label_name_text = (TextView) view.findViewById(2131296889);
            myViewHolder.mCheckBox = (CheckBox) view.findViewById(2131296482);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        final DeviceBean deviceBean = (DeviceBean) getItem(i);
        myViewHolder.mTextView.setText(deviceBean.getEquipment());
        String member = deviceBean.getMember();
        if ("1".equals(member) || "1.0".equals(member)) {
            myViewHolder.label_name_text.setText("月度会员（30天）");
        } else if ("2".equals(member) || SocializeConstants.PROTOCOL_VERSON.equals(member)) {
            myViewHolder.label_name_text.setText("年度会员（365天）");
        } else if ("3".equals(member) || "3.0".equals(member)) {
            myViewHolder.label_name_text.setText("三年会员（1095天）");
        } else if ("4".equals(member) || "4.0".equals(member)) {
            myViewHolder.label_name_text.setText("半年会员（180天）");
        } else if ("5".equals(member) || "5.0".equals(member)) {
            myViewHolder.label_name_text.setText("年度会员（365天）");
        } else if ("10".equals(member) || "10.0".equals(member)) {
            myViewHolder.label_name_text.setText("永久会员");
        } else if ("11".equals(member) || "11.0".equals(member)) {
            myViewHolder.label_name_text.setText("永久会员");
        } else {
            myViewHolder.label_name_text.setText("体验会员");
        }
        myViewHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                for (DeviceBean selected : ChangeDeviceAdapter.this.mDatas) {
                    selected.setSelected(false);
                }
                ((DeviceBean) ChangeDeviceAdapter.this.mDatas.get(i)).setSelected(true);
                if (ChangeDeviceAdapter.this.onSelectDeviceListener != null) {
                    ChangeDeviceAdapter.this.onSelectDeviceListener.select(deviceBean);
                }
                ChangeDeviceAdapter.this.notifyDataSetChanged();
            }
        });
        myViewHolder.list_item_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                myViewHolder.mCheckBox.performClick();
            }
        });
        myViewHolder.mCheckBox.setChecked(this.mDatas.get(i).isSelected());
        return view;
    }

    public void setOnSelectDeviceListener(OnSelectDeviceListener onSelectDeviceListener2) {
        this.onSelectDeviceListener = onSelectDeviceListener2;
    }
}
