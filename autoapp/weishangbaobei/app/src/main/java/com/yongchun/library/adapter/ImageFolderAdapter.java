package com.yongchun.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.yongchun.library.R;
import com.yongchun.library.model.LocalMedia;
import com.yongchun.library.model.LocalMediaFolder;
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

    public ImageFolderAdapter(Context context2) {
        this.context = context2;
    }

    public void bindFolder(List<LocalMediaFolder> list) {
        this.folders = list;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_folder, viewGroup, false));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        r0 = r7.folders.get(r9);
     */
    @android.annotation.SuppressLint({"StringFormatMatches"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(com.yongchun.library.adapter.ImageFolderAdapter.ViewHolder r8, final int r9) {
        /*
            r7 = this;
            java.util.List<com.yongchun.library.model.LocalMediaFolder> r0 = r7.folders
            if (r0 == 0) goto L_0x0094
            java.util.List<com.yongchun.library.model.LocalMediaFolder> r0 = r7.folders
            int r0 = r0.size()
            if (r0 > r9) goto L_0x000e
            goto L_0x0094
        L_0x000e:
            java.util.List<com.yongchun.library.model.LocalMediaFolder> r0 = r7.folders
            java.lang.Object r0 = r0.get(r9)
            com.yongchun.library.model.LocalMediaFolder r0 = (com.yongchun.library.model.LocalMediaFolder) r0
            if (r0 == 0) goto L_0x0093
            java.lang.String r1 = r0.getFirstImagePath()
            if (r1 != 0) goto L_0x001f
            goto L_0x0093
        L_0x001f:
            android.content.Context r1 = r7.context     // Catch:{ Exception -> 0x0047 }
            com.bumptech.glide.RequestManager r1 = com.bumptech.glide.Glide.with(r1)     // Catch:{ Exception -> 0x0047 }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0047 }
            java.lang.String r3 = r0.getFirstImagePath()     // Catch:{ Exception -> 0x0047 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0047 }
            com.bumptech.glide.DrawableTypeRequest r1 = r1.load(r2)     // Catch:{ Exception -> 0x0047 }
            int r2 = com.yongchun.library.R.mipmap.ic_placeholder     // Catch:{ Exception -> 0x0047 }
            com.bumptech.glide.DrawableRequestBuilder r1 = r1.placeholder(r2)     // Catch:{ Exception -> 0x0047 }
            int r2 = com.yongchun.library.R.mipmap.ic_placeholder     // Catch:{ Exception -> 0x0047 }
            com.bumptech.glide.DrawableRequestBuilder r1 = r1.error(r2)     // Catch:{ Exception -> 0x0047 }
            com.bumptech.glide.DrawableRequestBuilder r1 = r1.centerCrop()     // Catch:{ Exception -> 0x0047 }
            android.widget.ImageView r2 = r8.firstImage     // Catch:{ Exception -> 0x0047 }
            r1.into(r2)     // Catch:{ Exception -> 0x0047 }
        L_0x0047:
            android.widget.TextView r1 = r8.folderName
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r0.getName()
            r2.append(r3)
            java.lang.String r3 = ""
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.setText(r2)
            android.widget.TextView r1 = r8.imageNum
            android.content.Context r2 = r7.context
            int r3 = com.yongchun.library.R.string.num_postfix
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]
            int r5 = r0.getImageNum()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r6 = 0
            r4[r6] = r5
            java.lang.String r2 = r2.getString(r3, r4)
            r1.setText(r2)
            android.widget.ImageView r1 = r8.isSelected
            int r2 = r7.checkedIndex
            if (r2 != r9) goto L_0x0083
            goto L_0x0085
        L_0x0083:
            r6 = 8
        L_0x0085:
            r1.setVisibility(r6)
            android.view.View r8 = r8.contentView
            com.yongchun.library.adapter.ImageFolderAdapter$1 r1 = new com.yongchun.library.adapter.ImageFolderAdapter$1
            r1.<init>(r9, r0)
            r8.setOnClickListener(r1)
            return
        L_0x0093:
            return
        L_0x0094:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yongchun.library.adapter.ImageFolderAdapter.onBindViewHolder(com.yongchun.library.adapter.ImageFolderAdapter$ViewHolder, int):void");
    }

    public int getItemCount() {
        return this.folders.size();
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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }
}
