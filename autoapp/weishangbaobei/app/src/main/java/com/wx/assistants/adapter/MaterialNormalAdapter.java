package com.wx.assistants.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.wx.assistants.activity.PreviewMultiImageActivity;
import com.wx.assistants.adapter.MaterialImageAdapter;
import com.wx.assistants.bean.MaterialBean;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.ClipboardUtils;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.Global;
import com.wx.assistants.view.GridItemDecoration;
import com.wx.assistants.view.RecyclerViewDivider;
import java.util.ArrayList;
import java.util.List;

public class MaterialNormalAdapter extends RecyclerView.Adapter<VH> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    /* access modifiers changed from: private */
    public Activity activity;
    private View mHeaderView;
    /* access modifiers changed from: private */
    public List<MaterialBean> materialBeans;
    /* access modifiers changed from: private */
    public OnStartWxListener onStartWxListener;
    private ArrayList<String> picItemUrls = new ArrayList<>();

    public interface OnStartWxListener {
        void materialImagesSave(int i);

        void materialPosition(int i);
    }

    public void addHeaderView(View view) {
        this.mHeaderView = view;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return this.mHeaderView;
    }

    public int getItemViewType(int i) {
        return (this.mHeaderView != null && i == 0) ? 0 : 1;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        MaterialNormalAdapter.super.onAttachedToRecyclerView(recyclerView);
        GridLayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                public int getSpanSize(int i) {
                    if (MaterialNormalAdapter.this.getItemViewType(i) == 0) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    public void onViewAttachedToWindow(VH vh) {
        MaterialNormalAdapter.super.onViewAttachedToWindow(vh);
        StaggeredGridLayoutManager.LayoutParams layoutParams = vh.itemView.getLayoutParams();
        if (layoutParams != null && (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams)) {
            layoutParams.setFullSpan(vh.getLayoutPosition() == 0);
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder viewHolder) {
        int layoutPosition = viewHolder.getLayoutPosition();
        return this.mHeaderView == null ? layoutPosition : layoutPosition - 1;
    }

    public int getItemCount() {
        return this.mHeaderView == null ? this.materialBeans.size() : this.materialBeans.size() + 1;
    }

    public void setOnStartWxListener(OnStartWxListener onStartWxListener2) {
        this.onStartWxListener = onStartWxListener2;
    }

    public void setData(List<MaterialBean> list) {
        if (list != null && list.size() > 0) {
            this.materialBeans = list;
        }
    }

    public static class VH extends RecyclerView.ViewHolder {
        public final RecyclerView commonRV;
        public final TextView copyBtn;
        public final TextView createDayTime;
        public final TextView createMonthTime;
        public final TextView downBtn;
        public FrameLayout imgFrameLayout;
        public final LinearLayout relayCircleBtn;
        public final TextView sendText;
        public ImageView singleImg;

        public VH(View view) {
            super(view);
            this.downBtn = (TextView) view.findViewById(2131296609);
            this.copyBtn = (TextView) view.findViewById(2131296549);
            this.relayCircleBtn = (LinearLayout) view.findViewById(2131297230);
            this.createDayTime = (TextView) view.findViewById(2131296553);
            this.createMonthTime = (TextView) view.findViewById(2131296554);
            this.sendText = (TextView) view.findViewById(2131297343);
            this.commonRV = view.findViewById(2131296523);
            this.singleImg = (ImageView) view.findViewById(2131297385);
            this.imgFrameLayout = (FrameLayout) view.findViewById(2131296776);
        }
    }

    public MaterialNormalAdapter(Activity activity2, List<MaterialBean> list) {
        this.activity = activity2;
        this.materialBeans = list;
    }

    public void onBindViewHolder(VH vh, int i) {
        if (getItemViewType(i) != 0) {
            final int realPosition = getRealPosition(vh);
            final MaterialBean materialBean = this.materialBeans.get(realPosition);
            String convertDate2String = DateUtils.convertDate2String(materialBean.getCreateTime(), "MM-dd");
            if (convertDate2String.contains("-")) {
                String[] split = convertDate2String.split("-");
                vh.createDayTime.setText(split[1]);
                vh.createMonthTime.setText(split[0] + "月");
            }
            vh.sendText.setText(materialBean.getContent());
            vh.downBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MaterialNormalAdapter.this.onStartWxListener.materialImagesSave(realPosition);
                }
            });
            vh.copyBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ClipboardUtils.setClipboardText(MaterialNormalAdapter.this.activity, materialBean.getContent(), false);
                }
            });
            vh.relayCircleBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MaterialNormalAdapter.this.onStartWxListener.materialPosition(realPosition);
                }
            });
            this.picItemUrls.clear();
            if (materialBean.getPicUrlJson() != null && !"".equals(materialBean.getPicUrlJson())) {
                String[] strArr = (String[]) new Gson().fromJson(materialBean.getPicUrlJson(), new TypeToken<String[]>() {
                }.getType());
                for (String str : strArr) {
                    ArrayList<String> arrayList = this.picItemUrls;
                    arrayList.add(QBangApis.IMG_PREFIX_URL + str);
                }
            }
            if (this.picItemUrls.size() <= 0) {
                vh.imgFrameLayout.setVisibility(8);
                vh.commonRV.setVisibility(8);
            } else if (this.picItemUrls.size() == 1) {
                vh.imgFrameLayout.setVisibility(0);
                vh.commonRV.setVisibility(8);
                String str2 = this.picItemUrls.get(0);
                if (!str2.endsWith(PictureFileUtils.POST_VIDEO) && !str2.endsWith(".MP4")) {
                    Global.init(this.activity);
                    Global.displayBitmap(str2, vh.singleImg);
                    vh.imgFrameLayout.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            ArrayList arrayList = new ArrayList();
                            MaterialBean materialBean = (MaterialBean) MaterialNormalAdapter.this.materialBeans.get(realPosition);
                            if (materialBean.getPicUrlJson() != null && !"".equals(materialBean.getPicUrlJson())) {
                                String[] strArr = (String[]) new Gson().fromJson(materialBean.getPicUrlJson(), new TypeToken<String[]>() {
                                }.getType());
                                for (String str : strArr) {
                                    arrayList.add(QBangApis.IMG_PREFIX_URL + str);
                                }
                            }
                            Intent intent = new Intent(MaterialNormalAdapter.this.activity, PreviewMultiImageActivity.class);
                            intent.putStringArrayListExtra("mPictures", arrayList);
                            intent.putExtra("position", 0);
                            MaterialNormalAdapter.this.activity.startActivity(intent);
                        }
                    });
                }
            } else {
                vh.imgFrameLayout.setVisibility(8);
                vh.commonRV.setVisibility(0);
                MaterialImageAdapter materialImageAdapter = new MaterialImageAdapter(this.picItemUrls, this.activity);
                vh.commonRV.setAdapter(materialImageAdapter);
                vh.commonRV.addItemDecoration(new RecyclerViewDivider(this.activity, 1, 0, ContextCompat.getColor(this.activity, 2131099799)));
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this.activity, 2);
                vh.commonRV.addItemDecoration(new GridItemDecoration.Builder(this.activity).setHorizontalSpan(2131165672).setVerticalSpan(2131165672).setColorResource(2131100063).setShowLastLine(false).build());
                vh.commonRV.setLayoutManager(gridLayoutManager);
                materialImageAdapter.setOnClick(new MaterialImageAdapter.OnClick() {
                    public void click(int i) {
                        ArrayList arrayList = new ArrayList();
                        MaterialBean materialBean = (MaterialBean) MaterialNormalAdapter.this.materialBeans.get(realPosition);
                        if (materialBean.getPicUrlJson() != null && !"".equals(materialBean.getPicUrlJson())) {
                            String[] strArr = (String[]) new Gson().fromJson(materialBean.getPicUrlJson(), new TypeToken<String[]>() {
                            }.getType());
                            for (String str : strArr) {
                                arrayList.add(QBangApis.IMG_PREFIX_URL + str);
                            }
                        }
                        Intent intent = new Intent(MaterialNormalAdapter.this.activity, PreviewMultiImageActivity.class);
                        intent.putStringArrayListExtra("mPictures", arrayList);
                        intent.putExtra("position", i);
                        MaterialNormalAdapter.this.activity.startActivity(intent);
                    }
                });
                vh.commonRV.setNestedScrollingEnabled(false);
            }
        }
    }

    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.mHeaderView == null || i != 0) {
            return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(2131427582, viewGroup, false));
        }
        return new VH(this.mHeaderView);
    }
}
