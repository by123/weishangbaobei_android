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
import com.bumptech.glide.Glide;
import com.luck.picture.lib.tools.PictureFileUtils;
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

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        View headerView;

        public HeaderViewHolder(View view) {
            super(view);
            this.headerView = view;
        }
    }

    public interface OnImageSelectChangedListener {
        void onChange(List<LocalMedia> list);

        void onPictureClick(LocalMedia localMedia, int i);

        void onTakePhoto();
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

    public ImageListAdapter(Activity activity, int i, int i2, boolean z, boolean z2) {
        this.context = activity;
        this.selectMode = i2;
        this.maxSelectNum = i;
        this.showCamera = z;
        this.enablePreview = z2;
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

    /* access modifiers changed from: private */
    public long getVideoDuration(String str) {
        try {
            RxFFmpegInvoke.getInstance().setDebug(false);
            String mediaInfo = RxFFmpegInvoke.getInstance().getMediaInfo(str);
            PrintStream printStream = System.out;
            printStream.println("BIG_VIDEO_INFO_" + mediaInfo);
            return MediaInfo.parse(mediaInfo).duration;
        } catch (Exception e) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            return (long) Integer.parseInt(mediaMetadataRetriever.extractMetadata(9));
        }
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

    public String formatTime(long j) {
        String str;
        String str2;
        try {
            long j2 = j / 1000;
            String str3 = "" + (j2 / 3600);
            String str4 = "" + ((j2 % 3600) / 60);
            String str5 = "" + ((j2 % 3600) % 60);
            if (str3.length() < 2) {
                str = "0" + str3;
            } else {
                str = str3;
            }
            if (str4.equals("0")) {
                str4 = "0";
            }
            if (str5.length() < 2) {
                str5 = "0" + str5;
            }
            String str6 = "";
            if (!str.equals("00")) {
                str6 = "" + str + ":";
            }
            if (!str4.equals("0")) {
                str2 = str6 + str4 + ":";
            } else {
                str2 = str6 + "0:";
            }
            return str2 + str5;
        } catch (Exception e) {
            return "0:00";
        }
    }

    public List<LocalMedia> getImages() {
        return this.images;
    }

    public int getItemCount() {
        return this.showCamera ? this.images.size() + 1 : this.images.size();
    }

    public int getItemViewType(int i) {
        return (!this.showCamera || i != 0) ? 2 : 1;
    }

    public List<LocalMedia> getSelectedImages() {
        return this.selectImages;
    }

    public boolean isSelected(LocalMedia localMedia) {
        for (LocalMedia path : this.selectImages) {
            if (path.getPath().equals(localMedia.getPath())) {
                return true;
            }
        }
        return false;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (getItemViewType(i) == 1) {
            ((HeaderViewHolder) viewHolder).headerView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ImageListAdapter.this.imageSelectChangedListener != null) {
                        ImageListAdapter.this.imageSelectChangedListener.onTakePhoto();
                    }
                }
            });
            return;
        }
        final ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        final LocalMedia localMedia = this.images.get(this.showCamera ? i - 1 : i);
        try {
            Glide.with(this.context).load(new File(localMedia.getPath())).centerCrop().thumbnail(0.5f).placeholder(R.drawable.image_placeholder).error(R.drawable.image_placeholder).dontAnimate().into(viewHolder2.picture);
        } catch (Exception e) {
        }
        try {
            if (localMedia.getPath().endsWith(PictureFileUtils.POST_VIDEO)) {
                viewHolder2.videoImg.setVisibility(0);
                viewHolder2.duration.setVisibility(0);
                long j = 1000;
                try {
                    j = localMedia.getDuration();
                } catch (Exception e2) {
                }
                viewHolder2.duration.setText(formatTime(localMedia.getDuration()));
                if (j < 999) {
                    sysDuration(localMedia, viewHolder2.duration);
                } else {
                    viewHolder2.duration.setText(formatTime(localMedia.getDuration()));
                }
            } else {
                viewHolder2.videoImg.setVisibility(8);
                viewHolder2.duration.setVisibility(8);
            }
        } catch (Exception e3) {
        }
        if (this.selectMode == 2) {
            viewHolder2.check.setVisibility(8);
        }
        selectImage(viewHolder2, isSelected(localMedia));
        if (this.enablePreview) {
            viewHolder2.check.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ImageListAdapter.this.changeCheckboxState(viewHolder2, localMedia);
                }
            });
        }
        viewHolder2.contentView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if ((ImageListAdapter.this.selectMode != 2 && !ImageListAdapter.this.enablePreview) || ImageListAdapter.this.imageSelectChangedListener == null) {
                    ImageListAdapter.this.changeCheckboxState(viewHolder2, localMedia);
                } else if (localMedia.getPath().endsWith(PictureFileUtils.POST_VIDEO)) {
                    long access$400 = ImageListAdapter.this.getVideoDuration(localMedia.getPath());
                    PrintStream printStream = System.out;
                    StringBuilder sb = new StringBuilder();
                    sb.append("WS_BABY_DURATION.");
                    long j = access$400 / 1000;
                    sb.append(j);
                    printStream.println(sb.toString());
                    if (j >= ((long) ImageListAdapter.max_duration)) {
                        Activity access$500 = ImageListAdapter.this.context;
                        Toast.makeText(access$500, "时长太长,最长支持" + (ImageListAdapter.max_duration / 60) + "分钟", 0).show();
                        return;
                    }
                    ImageListAdapter.this.imageSelectChangedListener.onPictureClick(localMedia, ImageListAdapter.this.showCamera ? i - 1 : i);
                } else {
                    ImageListAdapter.this.imageSelectChangedListener.onPictureClick(localMedia, ImageListAdapter.this.showCamera ? i - 1 : i);
                }
            }
        });
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return i == 1 ? new HeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_camera, viewGroup, false)) : new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_picture, viewGroup, false));
    }

    public void selectImage(ViewHolder viewHolder, boolean z) {
        viewHolder.check.setSelected(z);
        if (z) {
            viewHolder.picture.setColorFilter(this.context.getResources().getColor(R.color.image_overlay2), PorterDuff.Mode.SRC_ATOP);
        } else {
            viewHolder.picture.setColorFilter(this.context.getResources().getColor(R.color.image_overlay), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public void setOnImageSelectChangedListener(OnImageSelectChangedListener onImageSelectChangedListener) {
        this.imageSelectChangedListener = onImageSelectChangedListener;
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
}
