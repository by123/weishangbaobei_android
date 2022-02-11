package com.wx.assistants.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.bean.MemberPriceAdapterBean;
import com.wx.assistants.utils.MoneyUtils;
import java.util.List;

public class MemberPriceRecylerviewListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<MemberPriceAdapterBean> list;
    /* access modifiers changed from: private */
    public OnClick onClick;
    private int thisPosotion;

    public interface OnClick {
        void click(int i);
    }

    public int getThisPosotion() {
        return this.thisPosotion;
    }

    public void setThisPosotion(int i) {
        this.thisPosotion = i;
    }

    public MemberPriceRecylerviewListAdapter(List<MemberPriceAdapterBean> list2, Context context2) {
        this.list = list2;
        this.context = context2;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(2131427584, (ViewGroup) null));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String removeDecimalPoints = MoneyUtils.removeDecimalPoints(this.list.get(i).getWechatAmount() + "");
        String describes = this.list.get(i).getDescribes();
        String typeName = this.list.get(i).getTypeName();
        this.list.get(i).getDiscount() + "";
        if (removeDecimalPoints != null) {
            viewHolder.wxAmount.setText(removeDecimalPoints);
        }
        if (describes != null) {
            viewHolder.describe.setText(describes);
        }
        if (typeName.contains("月")) {
            viewHolder.memberNameText.setText("1 个月");
        } else if (typeName.contains("半年")) {
            viewHolder.memberNameText.setText("6 个月");
        } else if (typeName.contains("年")) {
            viewHolder.memberNameText.setText("12 个月");
        } else if (typeName.contains("三")) {
            viewHolder.memberNameText.setText("36 个月");
        } else if (typeName.contains("永")) {
            viewHolder.memberNameText.setText("永久会员");
        }
        viewHolder.originAmount.setText(new SpannableString("¥" + MoneyUtils.removeDecimalPoints(this.list.get(i).getAmount() + "")));
        viewHolder.originAmount.getPaint().setAntiAlias(true);
        viewHolder.originAmount.getPaint().setFlags(17);
        String isCumulative = this.list.get(i).getIsCumulative();
        if (isCumulative == null || "".equals(isCumulative) || "0.0".equals(isCumulative) || "0".equals(isCumulative)) {
            viewHolder.discount.setVisibility(8);
        } else if ("1.0".equals(isCumulative) || "1".equals(isCumulative)) {
            viewHolder.discount.setVisibility(0);
        } else {
            viewHolder.discount.setVisibility(8);
        }
        if (i == getThisPosotion()) {
            if (i == 0) {
                viewHolder.shadowLinearLayout.setBackground(this.context.getResources().getDrawable(2131231285));
            } else if (i == this.list.size() - 1) {
                viewHolder.shadowLinearLayout.setBackground(this.context.getResources().getDrawable(2131231283));
            } else {
                viewHolder.shadowLinearLayout.setBackground(this.context.getResources().getDrawable(2131231284));
            }
            viewHolder.typeImage.setBackgroundResource(2131558648);
        } else {
            viewHolder.shadowLinearLayout.setBackgroundResource(2131100063);
            viewHolder.typeImage.setBackgroundResource(2131231287);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MemberPriceRecylerviewListAdapter.this.onClick != null) {
                    MemberPriceRecylerviewListAdapter.this.onClick.click(i);
                }
            }
        });
    }

    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView describe;
        TextView discount;
        TextView memberNameText;
        TextView originAmount;
        LinearLayout shadowLinearLayout;
        TextView typeImage;
        TextView wxAmount;

        public ViewHolder(View view) {
            super(view);
            this.originAmount = (TextView) view.findViewById(2131297097);
            this.typeImage = (TextView) view.findViewById(2131296781);
            this.memberNameText = (TextView) view.findViewById(2131297006);
            this.describe = (TextView) view.findViewById(2131296586);
            this.wxAmount = (TextView) view.findViewById(2131297688);
            this.discount = (TextView) view.findViewById(2131296602);
            this.shadowLinearLayout = (LinearLayout) view.findViewById(2131297351);
        }
    }

    public void setOnClick(OnClick onClick2) {
        this.onClick = onClick2;
    }
}
