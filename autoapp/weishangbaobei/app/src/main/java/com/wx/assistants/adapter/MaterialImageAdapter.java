package com.wx.assistants.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.wx.assistants.application.MyApplication;
import java.util.ArrayList;
import java.util.List;

public class MaterialImageAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<String> list;
    /* access modifiers changed from: private */
    public OnClick onClick;

    public interface OnClick {
        void click(int i);
    }

    public MaterialImageAdapter(ArrayList<String> arrayList, Context context2) {
        this.list = arrayList;
        this.context = context2;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(2131427581, (ViewGroup) null));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(MyApplication.getConText()).load(this.list.get(i)).into(viewHolder.imgView);
        viewHolder.imgView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MaterialImageAdapter.this.onClick != null) {
                    MaterialImageAdapter.this.onClick.click(i);
                }
            }
        });
    }

    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;

        public ViewHolder(View view) {
            super(view);
            this.imgView = (ImageView) view.findViewById(2131296783);
        }
    }

    public void setOnClick(OnClick onClick2) {
        this.onClick = onClick2;
    }
}
