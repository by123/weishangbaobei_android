package com.yongchun.library.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.yongchun.library.R;
import com.yongchun.library.model.LocalMedia;
import com.yongchun.library.model.LocalMediaFolder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageFolderAdapter extends RecyclerView.Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public int checkedIndex = 0;
    private Context context;
    private List<LocalMediaFolder> folders = new ArrayList();
    /* access modifiers changed from: private */
    public OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(String str, List<LocalMedia> list);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View contentView;
        ImageView firstImage;
        TextView folderName;
        TextView imageNum;
        ImageView isSelected;

        public ViewHolder(View view) {
            super(view);
            this.contentView = view;
            this.firstImage = (ImageView) view.findViewById(R.id.first_image);
            this.folderName = (TextView) view.findViewById(R.id.folder_name);
            this.imageNum = (TextView) view.findViewById(R.id.image_num);
            this.isSelected = (ImageView) view.findViewById(R.id.is_selected);
        }
    }

    public ImageFolderAdapter(Context context2) {
        this.context = context2;
    }

    public void bindFolder(List<LocalMediaFolder> list) {
        this.folders = list;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.folders.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        r0 = r7.folders.get(r9);
     */
    @SuppressLint({"StringFormatMatches"})
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final LocalMediaFolder localMediaFolder;
        int i2 = 0;
        if (this.folders != null && this.folders.size() > i && localMediaFolder != null && localMediaFolder.getFirstImagePath() != null) {
            try {
                Glide.with(this.context).load(new File(localMediaFolder.getFirstImagePath())).placeholder(R.mipmap.ic_placeholder).error(R.mipmap.ic_placeholder).centerCrop().into(viewHolder.firstImage);
            } catch (Exception e) {
            }
            TextView textView = viewHolder.folderName;
            textView.setText(localMediaFolder.getName() + "");
            viewHolder.imageNum.setText(this.context.getString(R.string.num_postfix, new Object[]{Integer.valueOf(localMediaFolder.getImageNum())}));
            ImageView imageView = viewHolder.isSelected;
            if (this.checkedIndex != i) {
                i2 = 8;
            }
            imageView.setVisibility(i2);
            viewHolder.contentView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ImageFolderAdapter.this.onItemClickListener != null) {
                        int unused = ImageFolderAdapter.this.checkedIndex = i;
                        ImageFolderAdapter.this.notifyDataSetChanged();
                        ImageFolderAdapter.this.onItemClickListener.onItemClick(localMediaFolder.getName(), localMediaFolder.getImages());
                    }
                }
            });
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_folder, viewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }
}
