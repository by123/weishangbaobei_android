package com.yongchun.library.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.PorterDuff;
import android.media.MediaMetadataRetriever;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.yongchun.library.R;
import com.yongchun.library.model.LocalMedia;
import com.yongchun.library.model.MediaInfo;
import com.yongchun.library.utils.SPUtils;
import io.microshow.rxffmpeg.RxFFmpegInvoke;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_CAMERA = 1;
    public static final int TYPE_PICTURE = 2;
    public static int max_duration = 300;
    /* access modifiers changed from: private */
    public Activity context;
    /* access modifiers changed from: private */
    public boolean enablePreview = true;
    /* access modifiers changed from: private */
    public OnImageSelectChangedListener imageSelectChangedListener;
    private List<LocalMedia> images = new ArrayList();
    private int maxSelectNum;
    private List<LocalMedia> selectImages = new ArrayList();
    /* access modifiers changed from: private */
    public int selectMode = 1;
    /* access modifiers changed from: private */
    public boolean showCamera = true;

    public interface OnImageSelectChangedListener {
        void onChange(List<LocalMedia> list);

        void onPictureClick(LocalMedia localMedia, int i);

        void onTakePhoto();
    }

    public ImageListAdapter(Activity activity, int i, int i2, boolean z, boolean z2) {
        this.context = activity;
        this.selectMode = i2;
        this.maxSelectNum = i;
        this.showCamera = z;
        this.enablePreview = z2;
    }

    public void bindImages(List<LocalMedia> list) {
        this.images = list;
        notifyDataSetChanged();
    }

    public void bindSelectImages(List<LocalMedia> list) {
        this.selectImages = list;
        notifyDataSetChanged();
        if (this.imageSelectChangedListener != null) {
            this.imageSelectChangedListener.onChange(this.selectImages);
        }
    }

    public int getItemViewType(int i) {
        return (!this.showCamera || i != 0) ? 2 : 1;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new HeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_camera, viewGroup, false));
        }
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_picture, viewGroup, false));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:15|16|17|18|19|20|21|(1:23)(1:24)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0079 */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008c A[Catch:{ Exception -> 0x00aa }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0092 A[Catch:{ Exception -> 0x00aa }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder r8, final int r9) {
        /*
            r7 = this;
            int r0 = r7.getItemViewType(r9)
            r1 = 1
            if (r0 != r1) goto L_0x0015
            com.yongchun.library.adapter.ImageListAdapter$HeaderViewHolder r8 = (com.yongchun.library.adapter.ImageListAdapter.HeaderViewHolder) r8
            android.view.View r8 = r8.headerView
            com.yongchun.library.adapter.ImageListAdapter$1 r9 = new com.yongchun.library.adapter.ImageListAdapter$1
            r9.<init>()
            r8.setOnClickListener(r9)
            goto L_0x00d3
        L_0x0015:
            com.yongchun.library.adapter.ImageListAdapter$ViewHolder r8 = (com.yongchun.library.adapter.ImageListAdapter.ViewHolder) r8
            java.util.List<com.yongchun.library.model.LocalMedia> r0 = r7.images
            boolean r1 = r7.showCamera
            if (r1 == 0) goto L_0x0020
            int r1 = r9 + -1
            goto L_0x0021
        L_0x0020:
            r1 = r9
        L_0x0021:
            java.lang.Object r0 = r0.get(r1)
            com.yongchun.library.model.LocalMedia r0 = (com.yongchun.library.model.LocalMedia) r0
            android.app.Activity r1 = r7.context     // Catch:{ Exception -> 0x0059 }
            com.bumptech.glide.RequestManager r1 = com.bumptech.glide.Glide.with(r1)     // Catch:{ Exception -> 0x0059 }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0059 }
            java.lang.String r3 = r0.getPath()     // Catch:{ Exception -> 0x0059 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0059 }
            com.bumptech.glide.DrawableTypeRequest r1 = r1.load(r2)     // Catch:{ Exception -> 0x0059 }
            com.bumptech.glide.DrawableRequestBuilder r1 = r1.centerCrop()     // Catch:{ Exception -> 0x0059 }
            r2 = 1056964608(0x3f000000, float:0.5)
            com.bumptech.glide.DrawableRequestBuilder r1 = r1.thumbnail(r2)     // Catch:{ Exception -> 0x0059 }
            int r2 = com.yongchun.library.R.drawable.image_placeholder     // Catch:{ Exception -> 0x0059 }
            com.bumptech.glide.DrawableRequestBuilder r1 = r1.placeholder(r2)     // Catch:{ Exception -> 0x0059 }
            int r2 = com.yongchun.library.R.drawable.image_placeholder     // Catch:{ Exception -> 0x0059 }
            com.bumptech.glide.DrawableRequestBuilder r1 = r1.error(r2)     // Catch:{ Exception -> 0x0059 }
            com.bumptech.glide.DrawableRequestBuilder r1 = r1.dontAnimate()     // Catch:{ Exception -> 0x0059 }
            android.widget.ImageView r2 = r8.picture     // Catch:{ Exception -> 0x0059 }
            r1.into(r2)     // Catch:{ Exception -> 0x0059 }
        L_0x0059:
            r1 = 8
            java.lang.String r2 = r0.getPath()     // Catch:{ Exception -> 0x00aa }
            java.lang.String r3 = ".mp4"
            boolean r2 = r2.endsWith(r3)     // Catch:{ Exception -> 0x00aa }
            if (r2 == 0) goto L_0x00a0
            android.widget.ImageView r2 = r8.videoImg     // Catch:{ Exception -> 0x00aa }
            r3 = 0
            r2.setVisibility(r3)     // Catch:{ Exception -> 0x00aa }
            android.widget.TextView r2 = r8.duration     // Catch:{ Exception -> 0x00aa }
            r2.setVisibility(r3)     // Catch:{ Exception -> 0x00aa }
            r2 = 1000(0x3e8, double:4.94E-321)
            long r4 = r0.getDuration()     // Catch:{ Exception -> 0x0079 }
            r2 = r4
        L_0x0079:
            android.widget.TextView r4 = r8.duration     // Catch:{ Exception -> 0x00aa }
            long r5 = r0.getDuration()     // Catch:{ Exception -> 0x00aa }
            java.lang.String r5 = r7.formatTime(r5)     // Catch:{ Exception -> 0x00aa }
            r4.setText(r5)     // Catch:{ Exception -> 0x00aa }
            r4 = 999(0x3e7, double:4.936E-321)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x0092
            android.widget.TextView r2 = r8.duration     // Catch:{ Exception -> 0x00aa }
            r7.sysDuration(r0, r2)     // Catch:{ Exception -> 0x00aa }
            goto L_0x00aa
        L_0x0092:
            android.widget.TextView r2 = r8.duration     // Catch:{ Exception -> 0x00aa }
            long r3 = r0.getDuration()     // Catch:{ Exception -> 0x00aa }
            java.lang.String r3 = r7.formatTime(r3)     // Catch:{ Exception -> 0x00aa }
            r2.setText(r3)     // Catch:{ Exception -> 0x00aa }
            goto L_0x00aa
        L_0x00a0:
            android.widget.ImageView r2 = r8.videoImg     // Catch:{ Exception -> 0x00aa }
            r2.setVisibility(r1)     // Catch:{ Exception -> 0x00aa }
            android.widget.TextView r2 = r8.duration     // Catch:{ Exception -> 0x00aa }
            r2.setVisibility(r1)     // Catch:{ Exception -> 0x00aa }
        L_0x00aa:
            int r2 = r7.selectMode
            r3 = 2
            if (r2 != r3) goto L_0x00b4
            android.widget.ImageView r2 = r8.check
            r2.setVisibility(r1)
        L_0x00b4:
            boolean r1 = r7.isSelected(r0)
            r7.selectImage(r8, r1)
            boolean r1 = r7.enablePreview
            if (r1 == 0) goto L_0x00c9
            android.widget.ImageView r1 = r8.check
            com.yongchun.library.adapter.ImageListAdapter$2 r2 = new com.yongchun.library.adapter.ImageListAdapter$2
            r2.<init>(r8, r0)
            r1.setOnClickListener(r2)
        L_0x00c9:
            android.view.View r1 = r8.contentView
            com.yongchun.library.adapter.ImageListAdapter$3 r2 = new com.yongchun.library.adapter.ImageListAdapter$3
            r2.<init>(r0, r9, r8)
            r1.setOnClickListener(r2)
        L_0x00d3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yongchun.library.adapter.ImageListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void");
    }

    public void sysDuration(final LocalMedia localMedia, final TextView textView) {
        try {
            new Thread(new Runnable() {
                public void run() {
                    String mediaInfo = RxFFmpegInvoke.getInstance().getMediaInfo(localMedia.getPath());
                    PrintStream printStream = System.out;
                    printStream.println("BIG_VIDEO_INFO_" + mediaInfo);
                    if (mediaInfo != null && mediaInfo.length() > 50) {
                        try {
                            final MediaInfo parse = MediaInfo.parse(mediaInfo);
                            if (parse != null) {
                                ImageListAdapter.this.context.runOnUiThread(new Runnable() {
                                    public void run() {
                                        long j = parse.duration;
                                        textView.setText(ImageListAdapter.this.formatTime(j));
                                        try {
                                            File file = new File(localMedia.getPath());
                                            SPUtils.put(ImageListAdapter.this.context, file.getName().substring(0, file.getName().lastIndexOf(".")), Long.valueOf(j));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            textView.setText("0:00");
        }
    }

    /* access modifiers changed from: private */
    public long getVideoDuration(String str) {
        try {
            RxFFmpegInvoke.getInstance().setDebug(false);
            String mediaInfo = RxFFmpegInvoke.getInstance().getMediaInfo(str);
            PrintStream printStream = System.out;
            printStream.println("BIG_VIDEO_INFO_" + mediaInfo);
            return MediaInfo.parse(mediaInfo).duration;
        } catch (Exception unused) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            return (long) Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
        }
    }

    public int getItemCount() {
        return this.showCamera ? this.images.size() + 1 : this.images.size();
    }

    /* access modifiers changed from: private */
    @SuppressLint({"StringFormatInvalid"})
    public void changeCheckboxState(ViewHolder viewHolder, LocalMedia localMedia) {
        boolean isSelected = viewHolder.check.isSelected();
        if (this.selectImages.size() < this.maxSelectNum || isSelected) {
            if (isSelected) {
                Iterator<LocalMedia> it = this.selectImages.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    LocalMedia next = it.next();
                    if (next.getPath().equals(localMedia.getPath())) {
                        this.selectImages.remove(next);
                        break;
                    }
                }
            } else {
                this.selectImages.add(localMedia);
            }
            selectImage(viewHolder, !isSelected);
            if (this.imageSelectChangedListener != null) {
                this.imageSelectChangedListener.onChange(this.selectImages);
                return;
            }
            return;
        }
        Toast.makeText(this.context, this.context.getString(R.string.message_max_num, new Object[]{Integer.valueOf(this.maxSelectNum)}), 1).show();
    }

    public List<LocalMedia> getSelectedImages() {
        return this.selectImages;
    }

    public List<LocalMedia> getImages() {
        return this.images;
    }

    public boolean isSelected(LocalMedia localMedia) {
        for (LocalMedia path : this.selectImages) {
            if (path.getPath().equals(localMedia.getPath())) {
                return true;
            }
        }
        return false;
    }

    public void selectImage(ViewHolder viewHolder, boolean z) {
        viewHolder.check.setSelected(z);
        if (z) {
            viewHolder.picture.setColorFilter(this.context.getResources().getColor(R.color.image_overlay2), PorterDuff.Mode.SRC_ATOP);
        } else {
            viewHolder.picture.setColorFilter(this.context.getResources().getColor(R.color.image_overlay), PorterDuff.Mode.SRC_ATOP);
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        View headerView;

        public HeaderViewHolder(View view) {
            super(view);
            this.headerView = view;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView check;
        View contentView;
        TextView duration;
        ImageView picture;
        ImageView videoImg;

        public ViewHolder(View view) {
            super(view);
            this.contentView = view;
            this.picture = (ImageView) view.findViewById(R.id.picture);
            this.check = (ImageView) view.findViewById(R.id.check);
            this.videoImg = (ImageView) view.findViewById(R.id.videoImg);
            this.duration = (TextView) view.findViewById(R.id.duration);
        }
    }

    public void setOnImageSelectChangedListener(OnImageSelectChangedListener onImageSelectChangedListener) {
        this.imageSelectChangedListener = onImageSelectChangedListener;
    }

    public String formatTime(long j) {
        String str;
        try {
            long j2 = j / 1000;
            String str2 = "" + (j2 / 3600);
            String str3 = "" + ((j2 % 3600) / 60);
            String str4 = "" + ((j2 % 3600) % 60);
            if (str2.length() < 2) {
                str2 = "0" + str2;
            }
            if (str3.equals("0")) {
                str3 = "0";
            }
            if (str4.length() < 2) {
                str4 = "0" + str4;
            }
            String str5 = "";
            if (!str2.equals("00")) {
                str5 = str5 + str2 + ":";
            }
            if (!str3.equals("0")) {
                str = str5 + str3 + ":";
            } else {
                str = str5 + "0:";
            }
            return str + str4;
        } catch (Exception unused) {
            return "0:00";
        }
    }
}
