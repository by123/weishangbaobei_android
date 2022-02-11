package com.luck.picture.lib.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.luck.picture.lib.R;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;
import java.util.ArrayList;
import java.util.List;

public class PictureAlbumDirectoryAdapter extends RecyclerView.Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public List<LocalMediaFolder> folders = new ArrayList();
    /* access modifiers changed from: private */
    public Context mContext;
    private int mimeType;
    /* access modifiers changed from: private */
    public OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(String str, List<LocalMedia> list);
    }

    public PictureAlbumDirectoryAdapter(Context context) {
        this.mContext = context;
    }

    public void bindFolderData(List<LocalMediaFolder> list) {
        this.folders = list;
        notifyDataSetChanged();
    }

    public void setMimeType(int i) {
        this.mimeType = i;
    }

    public List<LocalMediaFolder> getFolderData() {
        if (this.folders == null) {
            this.folders = new ArrayList();
        }
        return this.folders;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.picture_album_folder_item, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final LocalMediaFolder localMediaFolder = this.folders.get(i);
        String name = localMediaFolder.getName();
        int imageNum = localMediaFolder.getImageNum();
        String firstImagePath = localMediaFolder.getFirstImagePath();
        boolean isChecked = localMediaFolder.isChecked();
        localMediaFolder.getCheckedNum();
        viewHolder.itemView.setSelected(isChecked);
        if (this.mimeType == PictureMimeType.ofAudio()) {
            viewHolder.first_image.setImageResource(R.drawable.audio_placeholder);
        } else {
            Glide.with(viewHolder.itemView.getContext()).load(firstImagePath).asBitmap().centerCrop().placeholder(R.drawable.ic_placeholder).sizeMultiplier(0.5f).override(160, 160).into(new BitmapImageViewTarget(viewHolder.first_image) {
                /* access modifiers changed from: protected */
                public void setResource(Bitmap bitmap) {
                    RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(PictureAlbumDirectoryAdapter.this.mContext.getResources(), bitmap);
                    create.setCornerRadius(8.0f);
                    viewHolder.first_image.setImageDrawable(create);
                }
            });
        }
        TextView textView = viewHolder.image_num;
        textView.setText(imageNum + "");
        viewHolder.tv_folder_name.setText(name);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (PictureAlbumDirectoryAdapter.this.onItemClickListener != null) {
                    for (LocalMediaFolder checked : PictureAlbumDirectoryAdapter.this.folders) {
                        checked.setChecked(false);
                    }
                    localMediaFolder.setChecked(true);
                    PictureAlbumDirectoryAdapter.this.notifyDataSetChanged();
                    PictureAlbumDirectoryAdapter.this.onItemClickListener.onItemClick(localMediaFolder.getName(), localMediaFolder.getImages());
                }
            }
        });
    }

    public int getItemCount() {
        return this.folders.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView first_image;
        TextView image_num;
        TextView tv_folder_name;
        TextView tv_sign;

        public ViewHolder(View view) {
            super(view);
            this.first_image = (ImageView) view.findViewById(R.id.first_image);
            this.tv_folder_name = (TextView) view.findViewById(R.id.tv_folder_name);
            this.image_num = (TextView) view.findViewById(R.id.image_num);
            this.tv_sign = (TextView) view.findViewById(R.id.tv_sign);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }
}
