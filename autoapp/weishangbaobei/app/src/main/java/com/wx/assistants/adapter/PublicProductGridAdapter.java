package com.wx.assistants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.wx.assistants.utils.glide.GlideUtils;
import com.wx.assistants.view.CustomGridView;
import java.io.PrintStream;
import java.util.List;

public class PublicProductGridAdapter extends BaseAdapter {
    private Context context;
    private List<String> imgList;
    /* access modifiers changed from: private */
    public OnDeleteListener listener;
    private final LayoutInflater mInflater;
    private int maxNum = 6;
    private int picVideoType = 0;
    /* access modifiers changed from: private */
    public OnDeleteUrlListener urlListener;

    public interface OnDeleteListener {
        void delete(int i);
    }

    public interface OnDeleteUrlListener {
        void delete(String str);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public PublicProductGridAdapter(Context context2, List<String> list, int i, int i2) {
        this.context = context2;
        this.imgList = list;
        this.maxNum = i;
        this.mInflater = LayoutInflater.from(context2);
        this.picVideoType = i2;
    }

    public void setMaxNum(int i) {
        this.maxNum = i;
    }

    public void setDeleteListener(OnDeleteListener onDeleteListener) {
        this.listener = onDeleteListener;
    }

    public void setDeleteUrlListener(OnDeleteUrlListener onDeleteUrlListener) {
        this.urlListener = onDeleteUrlListener;
    }

    public int getCount() {
        int i = 1;
        if (this.imgList != null) {
            i = 1 + this.imgList.size();
        }
        return i > this.maxNum ? this.imgList.size() : i;
    }

    public Object getItem(int i) {
        return this.imgList.get(i);
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.mInflater.inflate(2131427686, viewGroup, false);
        }
        ImageView imageView = (ImageView) view.findViewById(2131297133);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(2131296321);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(2131296508);
        TextView textView = (TextView) view.findViewById(2131296320);
        if (this.picVideoType == 0) {
            textView.setText("添加图片");
        } else if (this.picVideoType == 1) {
            textView.setText("添加视频");
        } else {
            textView.setText("添加图片/视频");
        }
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (PublicProductGridAdapter.this.listener != null) {
                    PublicProductGridAdapter.this.listener.delete(i);
                }
            }
        });
        PrintStream printStream = System.out;
        printStream.println("WWWWWW-----WWWW---" + i + ListUtils.DEFAULT_JOIN_SEPARATOR + this.imgList.size());
        if (!((CustomGridView) viewGroup).isMeasure()) {
            if (i < this.imgList.size()) {
                final String str = this.imgList.get(i);
                imageView.setVisibility(0);
                linearLayout.setVisibility(8);
                linearLayout2.setVisibility(0);
                GlideUtils.showImageViewGone(MyApplication.getConText(), imageView, str, new GlideUtils.OnLoadImgListener() {
                    public void loadResult(int i) {
                        PrintStream printStream = System.out;
                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                        if (i == 1 && PublicProductGridAdapter.this.urlListener != null) {
                            PublicProductGridAdapter.this.urlListener.delete(str);
                        }
                    }
                });
            } else {
                imageView.setVisibility(8);
                linearLayout.setVisibility(0);
                linearLayout2.setVisibility(8);
            }
        }
        return view;
    }
}
