package com.luck.picture.lib.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.R;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.DateUtils;
import com.luck.picture.lib.tools.StringUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GridImageAdapter extends RecyclerView.Adapter<ViewHolder> {
    public static final int TYPE_CAMERA = 1;
    public static final int TYPE_PICTURE = 2;
    private Context context;
    /* access modifiers changed from: private */
    public List<LocalMedia> list = new ArrayList();
    private LayoutInflater mInflater;
    protected OnItemClickListener mItemClickListener;
    /* access modifiers changed from: private */
    public onAddPicClickListener mOnAddPicClickListener;
    protected OnSelectNumChangeListener onSelectNumChangeListener;
    private int selectMax = 9;

    public interface OnItemClickListener {
        void onItemClick(int i, View view);
    }

    public interface OnSelectNumChangeListener {
        void onSelectNumChange(int i);
    }

    public interface onAddPicClickListener {
        void onAddPicClick();
    }

    public GridImageAdapter(Context context2, onAddPicClickListener onaddpicclicklistener) {
        this.context = context2;
        this.mInflater = LayoutInflater.from(context2);
        this.mOnAddPicClickListener = onaddpicclicklistener;
    }

    public void setSelectMax(int i) {
        this.selectMax = i;
    }

    public void setList(List<LocalMedia> list2) {
        this.list = list2;
    }

    public List<LocalMedia> getList() {
        return this.list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_del;
        ImageView mImg;
        TextView tv_duration;

        public ViewHolder(View view) {
            super(view);
            this.mImg = (ImageView) view.findViewById(R.id.fiv);
            this.ll_del = (LinearLayout) view.findViewById(R.id.ll_del);
            this.tv_duration = (TextView) view.findViewById(R.id.tv_duration);
        }
    }

    public int getItemCount() {
        return this.list.size();
    }

    public int getItemViewType(int i) {
        return isShowAddItem(i) ? 1 : 2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this.mInflater.inflate(R.layout.gv_filter_image, viewGroup, false));
    }

    private boolean isShowAddItem(int i) {
        return i == (this.list.size() == 0 ? 0 : this.list.size());
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        String str;
        if (this.list != null && this.list.size() != 0) {
            if (getItemViewType(i) == 1) {
                viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        GridImageAdapter.this.mOnAddPicClickListener.onAddPicClick();
                    }
                });
                viewHolder.ll_del.setVisibility(4);
                return;
            }
            viewHolder.ll_del.setVisibility(0);
            viewHolder.ll_del.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int adapterPosition = viewHolder.getAdapterPosition();
                    if (adapterPosition != -1) {
                        GridImageAdapter.this.list.remove(adapterPosition);
                        GridImageAdapter.this.notifyItemRemoved(adapterPosition);
                        GridImageAdapter.this.notifyItemRangeChanged(adapterPosition, GridImageAdapter.this.list.size());
                        GridImageAdapter.this.onSelectNumChangeListener.onSelectNumChange(GridImageAdapter.this.list.size());
                    }
                }
            });
            LocalMedia localMedia = this.list.get(i);
            int mimeType = localMedia.getMimeType();
            if (localMedia.isCut() && !localMedia.isCompressed()) {
                str = localMedia.getCutPath();
            } else if (localMedia.isCompressed() || (localMedia.isCut() && localMedia.isCompressed())) {
                str = localMedia.getCompressPath();
            } else {
                str = localMedia.getPath();
            }
            if (localMedia.isCompressed()) {
                Log.i("compress image result:", (new File(localMedia.getCompressPath()).length() / ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS) + "k");
                Log.i("压缩地址::", localMedia.getCompressPath());
            }
            Log.i("原图地址::", localMedia.getPath());
            int isPictureType = PictureMimeType.isPictureType(localMedia.getPictureType());
            if (localMedia.isCut()) {
                Log.i("裁剪地址::", localMedia.getCutPath());
            }
            long duration = localMedia.getDuration();
            viewHolder.tv_duration.setVisibility(isPictureType == 2 ? 0 : 8);
            if (mimeType == PictureMimeType.ofAudio()) {
                viewHolder.tv_duration.setVisibility(0);
                StringUtils.modifyTextViewDrawable(viewHolder.tv_duration, ContextCompat.getDrawable(this.context, R.drawable.picture_audio), 0);
            } else {
                StringUtils.modifyTextViewDrawable(viewHolder.tv_duration, ContextCompat.getDrawable(this.context, R.drawable.video_icon), 0);
            }
            viewHolder.tv_duration.setText(DateUtils.timeParse(duration));
            if (mimeType == PictureMimeType.ofAudio()) {
                viewHolder.mImg.setImageResource(R.drawable.audio_placeholder);
            } else {
                Glide.with(viewHolder.itemView.getContext()).load(str).centerCrop().placeholder(R.color.color_f6).into(viewHolder.mImg);
            }
            if (this.mItemClickListener != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        GridImageAdapter.this.mItemClickListener.onItemClick(viewHolder.getAdapterPosition(), view);
                    }
                });
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void setSelectNumChangeListener(OnSelectNumChangeListener onSelectNumChangeListener2) {
        this.onSelectNumChangeListener = onSelectNumChangeListener2;
    }
}
