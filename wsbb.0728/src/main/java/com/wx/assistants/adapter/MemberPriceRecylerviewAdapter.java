package com.wx.assistants.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.bean.MemberPriceAdapterBean;
import com.wx.assistants.utils.MoneyUtils;
import java.util.List;

public class MemberPriceRecylerviewAdapter extends RecyclerView.Adapter<ViewHolder> {
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
        TextView memberNameText;
        TextView originAmount;
        ImageView seckill;
        LinearLayout shadowLinearLayout;
        TextView wxAmount;
        TextView wxYuan;

        public ViewHolder(View view) {
            super(view);
            this.memberNameText = (TextView) view.findViewById(2131297006);
            this.describe = (TextView) view.findViewById(2131296586);
            this.seckill = (ImageView) view.findViewById(2131297302);
            this.wxYuan = (TextView) view.findViewById(2131297694);
            this.wxAmount = (TextView) view.findViewById(2131297688);
            this.shadowLinearLayout = (LinearLayout) view.findViewById(2131297363);
            this.originAmount = (TextView) view.findViewById(2131297097);
        }
    }

    public MemberPriceRecylerviewAdapter(List<MemberPriceAdapterBean> list2, Context context2) {
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
        if (typeName != null) {
            viewHolder.memberNameText.setText(typeName);
        }
        String isCumulative = this.list.get(i).getIsCumulative();
        if (isCumulative == null || "".equals(isCumulative) || "0.0".equals(isCumulative) || "0".equals(isCumulative)) {
            viewHolder.seckill.setVisibility(8);
        } else if ("1.0".equals(isCumulative) || "1".equals(isCumulative)) {
            viewHolder.seckill.setVisibility(0);
        } else {
            viewHolder.seckill.setVisibility(8);
        }
        String removeDecimalPoints2 = MoneyUtils.removeDecimalPoints(this.list.get(i).getAmount() + "");
        SpannableString spannableString = new SpannableString("￥" + removeDecimalPoints2);
        spannableString.setSpan(new RelativeSizeSpan(0.7f), 0, 1, 33);
        viewHolder.originAmount.setText(spannableString);
        viewHolder.originAmount.getPaint().setAntiAlias(true);
        viewHolder.originAmount.getPaint().setFlags(17);
        if (i == getThisPosotion()) {
            viewHolder.shadowLinearLayout.setBackground(this.context.getResources().getDrawable(2131231070));
            viewHolder.memberNameText.setTextColor(this.context.getResources().getColor(2131100063));
            viewHolder.wxYuan.setTextColor(this.context.getResources().getColor(2131100063));
            viewHolder.wxAmount.setTextColor(this.context.getResources().getColor(2131100063));
            viewHolder.originAmount.setTextColor(this.context.getResources().getColor(2131099756));
        } else {
            viewHolder.shadowLinearLayout.setBackground(this.context.getResources().getDrawable(2131231070));
            viewHolder.memberNameText.setTextColor(this.context.getResources().getColor(2131099744));
            viewHolder.wxYuan.setTextColor(this.context.getResources().getColor(2131099744));
            viewHolder.wxAmount.setTextColor(this.context.getResources().getColor(2131099744));
            viewHolder.originAmount.setTextColor(this.context.getResources().getColor(2131099750));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MemberPriceRecylerviewAdapter.this.onClick != null) {
                    MemberPriceRecylerviewAdapter.this.onClick.click(i);
                }
            }
        });
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(2131427583, (ViewGroup) null));
    }

    public void setOnClick(OnClick onClick2) {
        this.onClick = onClick2;
    }

    public void setThisPosotion(int i) {
        this.thisPosotion = i;
    }
}
