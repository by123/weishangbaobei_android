package com.wx.assistants.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.wx.assistants.bean.HomeItemBean;
import java.util.ArrayList;
import java.util.List;

public class HomeItemAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<HomeItemBean> list;
    /* access modifiers changed from: private */
    public OnClick onClick;
    private int thisPosition;

    public interface OnClick {
        void click(int i);
    }

    public int getThisPosition() {
        return this.thisPosition;
    }

    public void setThisPosition(int i) {
        this.thisPosition = i;
    }

    public HomeItemAdapter(ArrayList<HomeItemBean> arrayList, Context context2) {
        this.list = arrayList;
        this.context = context2;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(2131427571, (ViewGroup) null));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        HomeItemBean homeItemBean = this.list.get(i);
        viewHolder.linearLayout.setBackgroundResource(homeItemBean.getDrawableBgId());
        viewHolder.imgView.setBackgroundResource(homeItemBean.getDrawableId());
        viewHolder.textView.setText(homeItemBean.getName());
        viewHolder.freeTextView.setVisibility(homeItemBean.isFree() ? 0 : 4);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (HomeItemAdapter.this.onClick != null) {
                    HomeItemAdapter.this.onClick.click(i);
                }
            }
        });
    }

    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView freeTextView;
        ImageView imgView;
        FrameLayout linearLayout;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.imgView = (ImageView) view.findViewById(2131296783);
            this.textView = (TextView) view.findViewById(2131297472);
            this.freeTextView = (TextView) view.findViewById(2131296701);
            this.linearLayout = (FrameLayout) view.findViewById(2131296931);
        }
    }

    public void setOnClick(OnClick onClick2) {
        this.onClick = onClick2;
    }
}
