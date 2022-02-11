package com.wx.assistants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.fileutil.FileBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CopyLocalFileAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    /* access modifiers changed from: private */
    public List<FileBean> labList = new ArrayList();
    /* access modifiers changed from: private */
    public OnSelectLabelListener onSelectLabelListener;

    public static class MyViewHolder {
        TextView fileDate;
        TextView fileName;
        LinearLayout list_item_layout;
        CheckBox mCheckBox;
    }

    public interface OnSelectLabelListener {
        void nuSelect(FileBean fileBean);

        void select(FileBean fileBean);
    }

    public CopyLocalFileAdapter(List<FileBean> list, Context context) {
        this.labList.clear();
        this.labList = list;
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

    public View getView(final int i, View view, ViewGroup viewGroup) {
        final MyViewHolder myViewHolder;
        if (view == null) {
            view = this.inflater.inflate(2131427472, (ViewGroup) null);
            myViewHolder = new MyViewHolder();
            myViewHolder.list_item_layout = (LinearLayout) view.findViewById(2131296955);
            myViewHolder.fileName = (TextView) view.findViewById(2131296663);
            myViewHolder.fileDate = (TextView) view.findViewById(2131296662);
            myViewHolder.mCheckBox = (CheckBox) view.findViewById(2131296482);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        final FileBean fileBean = (FileBean) getItem(i);
        String[] split = fileBean.getFileName().split("#_#");
        String str = split[1];
        String str2 = split[2];
        if (str2.contains(".txt")) {
            str2 = DateUtils.convertDate2String(new Date(Long.parseLong(str2.substring(0, str2.indexOf(".txt")))), "yyyy-MM-dd HH:mm:ss");
        }
        myViewHolder.fileName.setText(str);
        myViewHolder.fileDate.setText(str2);
        myViewHolder.list_item_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                myViewHolder.mCheckBox.performClick();
            }
        });
        myViewHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                for (int i = 0; i < CopyLocalFileAdapter.this.labList.size(); i++) {
                    if (i != i) {
                        ((FileBean) CopyLocalFileAdapter.this.labList.get(i)).setSelected(false);
                    }
                }
                if (((FileBean) CopyLocalFileAdapter.this.labList.get(i)).isSelected()) {
                    ((FileBean) CopyLocalFileAdapter.this.labList.get(i)).setSelected(false);
                    if (CopyLocalFileAdapter.this.onSelectLabelListener != null) {
                        CopyLocalFileAdapter.this.onSelectLabelListener.nuSelect(fileBean);
                    }
                } else {
                    ((FileBean) CopyLocalFileAdapter.this.labList.get(i)).setSelected(true);
                    if (CopyLocalFileAdapter.this.onSelectLabelListener != null) {
                        CopyLocalFileAdapter.this.onSelectLabelListener.select(fileBean);
                    }
                }
                CopyLocalFileAdapter.this.notifyDataSetChanged();
            }
        });
        myViewHolder.list_item_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                myViewHolder.mCheckBox.performClick();
            }
        });
        myViewHolder.mCheckBox.setChecked(this.labList.get(i).isSelected());
        return view;
    }

    public void setOnSelectLabelListener(OnSelectLabelListener onSelectLabelListener2) {
        this.onSelectLabelListener = onSelectLabelListener2;
    }
}
