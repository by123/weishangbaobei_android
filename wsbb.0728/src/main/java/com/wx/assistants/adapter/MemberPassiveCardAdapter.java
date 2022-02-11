package com.wx.assistants.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.luck.picture.lib.R;
import com.wx.assistants.bean.MemberPriceAdapterBean;
import com.wx.assistants.utils.MoneyUtils;
import java.util.List;

public class MemberPassiveCardAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<MemberPriceAdapterBean> list;
    /* access modifiers changed from: private */
    public OnClick onClick;
    private int thisPosotion;

    public interface OnClick {
        void click(int i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView describe;
        TextView discount;
        TextView memberNameText;
        LinearLayout shadowLinearLayout;
        TextView wxAmount;
        TextView yuanTv;

        public ViewHolder(View view) {
            super(view);
            this.memberNameText = (TextView) view.findViewById(2131297006);
            this.describe = (TextView) view.findViewById(2131296586);
            this.wxAmount = (TextView) view.findViewById(2131297688);
            this.discount = (TextView) view.findViewById(2131296602);
            this.yuanTv = (TextView) view.findViewById(2131297698);
            this.shadowLinearLayout = (LinearLayout) view.findViewById(2131297351);
        }
    }

    public MemberPassiveCardAdapter(List<MemberPriceAdapterBean> list2, Context context2) {
        this.list = list2;
        this.context = context2;
    }

    public int getItemCount() {
        return this.list.size();
    }

    public int getThisPosotion() {
        return this.thisPosotion;
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String removeDecimalPoints = MoneyUtils.removeDecimalPoints(this.list.get(i).getWechatAmount() + "");
        String describes = this.list.get(i).getDescribes();
        String typeName = this.list.get(i).getTypeName();
        if (removeDecimalPoints != null) {
            viewHolder.wxAmount.setText(removeDecimalPoints);
        }
        if (describes != null) {
            viewHolder.describe.setText(describes);
        }
        viewHolder.memberNameText.setText(typeName);
        String isCumulative = this.list.get(i).getIsCumulative();
        if (isCumulative == null || "".equals(isCumulative) || "0.0".equals(isCumulative) || "0".equals(isCumulative)) {
            viewHolder.discount.setVisibility(8);
        } else if ("1.0".equals(isCumulative) || "1".equals(isCumulative)) {
            viewHolder.discount.setVisibility(0);
        } else {
            viewHolder.discount.setVisibility(8);
        }
        if (i == getThisPosotion()) {
            viewHolder.shadowLinearLayout.setBackgroundResource(2131231073);
            viewHolder.yuanTv.setTextColor(this.context.getResources().getColor(R.color.main_color));
            viewHolder.wxAmount.setTextColor(this.context.getResources().getColor(R.color.main_color));
        } else {
            viewHolder.shadowLinearLayout.setBackgroundResource(2131231072);
            viewHolder.yuanTv.setTextColor(this.context.getResources().getColor(2131099686));
            viewHolder.wxAmount.setTextColor(this.context.getResources().getColor(2131099686));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MemberPassiveCardAdapter.this.onClick != null) {
                    MemberPassiveCardAdapter.this.onClick.click(i);
                }
            }
        });
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(2131427585, (ViewGroup) null));
    }

    public void setOnClick(OnClick onClick2) {
        this.onClick = onClick2;
    }

    public void setThisPosotion(int i) {
        this.thisPosotion = i;
    }
}
