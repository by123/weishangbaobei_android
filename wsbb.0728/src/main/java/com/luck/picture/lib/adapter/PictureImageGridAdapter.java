package com.luck.picture.lib.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.R;
import com.luck.picture.lib.anim.OptAnimationLoader;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.PictureSelectionConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.DateUtils;
import com.luck.picture.lib.tools.StringUtils;
import com.luck.picture.lib.tools.ToastManage;
import com.luck.picture.lib.tools.VoiceUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PictureImageGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int DURATION = 450;
    private Animation animation;
    private PictureSelectionConfig config;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public boolean enablePreview;
    /* access modifiers changed from: private */
    public boolean enablePreviewAudio = false;
    /* access modifiers changed from: private */
    public boolean enablePreviewVideo = false;
    private boolean enableVoice;
    /* access modifiers changed from: private */
    public OnPhotoSelectChangedListener imageSelectChangedListener;
    private List<LocalMedia> images = new ArrayList();
    private boolean isGo;
    private boolean is_checked_num;
    private int maxSelectNum;
    /* access modifiers changed from: private */
    public int mimeType;
    private int overrideHeight;
    private int overrideWidth;
    private List<LocalMedia> selectImages = new ArrayList();
    /* access modifiers changed from: private */
    public int selectMode = 2;
    /* access modifiers changed from: private */
    public boolean showCamera = true;
    private float sizeMultiplier;
    private boolean zoomAnim;

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        View headerView;
        TextView tv_title_camera;

        public HeaderViewHolder(View view) {
            super(view);
            this.headerView = view;
            this.tv_title_camera = (TextView) view.findViewById(R.id.tv_title_camera);
            this.tv_title_camera.setText(PictureImageGridAdapter.this.mimeType == PictureMimeType.ofAudio() ? PictureImageGridAdapter.this.context.getString(R.string.picture_tape) : PictureImageGridAdapter.this.context.getString(R.string.picture_take_picture));
        }
    }

    public interface OnPhotoSelectChangedListener {
        void onChange(List<LocalMedia> list);

        void onPictureClick(LocalMedia localMedia, int i);

        void onTakePhoto();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView check;
        View contentView;
        ImageView iv_picture;
        LinearLayout ll_check;
        TextView tv_duration;
        TextView tv_isGif;
        TextView tv_long_chart;

        public ViewHolder(View view) {
            super(view);
            this.contentView = view;
            this.iv_picture = (ImageView) view.findViewById(R.id.iv_picture);
            this.check = (TextView) view.findViewById(R.id.check);
            this.ll_check = (LinearLayout) view.findViewById(R.id.ll_check);
            this.tv_duration = (TextView) view.findViewById(R.id.tv_duration);
            this.tv_isGif = (TextView) view.findViewById(R.id.tv_isGif);
            this.tv_long_chart = (TextView) view.findViewById(R.id.tv_long_chart);
        }
    }

    public PictureImageGridAdapter(Context context2, PictureSelectionConfig pictureSelectionConfig) {
        this.context = context2;
        this.config = pictureSelectionConfig;
        this.selectMode = pictureSelectionConfig.selectionMode;
        this.showCamera = pictureSelectionConfig.isCamera;
        this.maxSelectNum = pictureSelectionConfig.maxSelectNum;
        this.enablePreview = pictureSelectionConfig.enablePreview;
        this.enablePreviewVideo = pictureSelectionConfig.enPreviewVideo;
        this.enablePreviewAudio = pictureSelectionConfig.enablePreviewAudio;
        this.is_checked_num = pictureSelectionConfig.checkNumMode;
        this.overrideWidth = pictureSelectionConfig.overrideWidth;
        this.overrideHeight = pictureSelectionConfig.overrideHeight;
        this.enableVoice = pictureSelectionConfig.openClickSound;
        this.sizeMultiplier = pictureSelectionConfig.sizeMultiplier;
        this.mimeType = pictureSelectionConfig.mimeType;
        this.zoomAnim = pictureSelectionConfig.zoomAnim;
        this.animation = OptAnimationLoader.loadAnimation(context2, R.anim.modal_in);
    }

    /* access modifiers changed from: private */
    public void changeCheckboxState(ViewHolder viewHolder, LocalMedia localMedia) {
        boolean isSelected = viewHolder.check.isSelected();
        String pictureType = this.selectImages.size() > 0 ? this.selectImages.get(0).getPictureType() : "";
        if (this.selectImages.size() >= this.maxSelectNum) {
            ToastManage.s(this.context, pictureType.startsWith("image") ? this.context.getString(R.string.picture_message_max_num, new Object[]{Integer.valueOf(this.maxSelectNum)}) : this.context.getString(R.string.picture_message_video_max_num, new Object[]{Integer.valueOf(this.maxSelectNum)}));
            return;
        }
        if (isSelected) {
            Iterator<LocalMedia> it = this.selectImages.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getPath().equals(localMedia.getPath())) {
                        subSelectPosition();
                        disZoom(viewHolder.iv_picture);
                        break;
                    }
                } else {
                    break;
                }
            }
        } else {
            if (this.selectMode == 1) {
                singleRadioMediaImage();
            }
            localMedia.setNum(this.selectImages.size());
            VoiceUtils.playVoice(this.context, this.enableVoice);
            zoom(viewHolder.iv_picture);
        }
        this.selectImages.add(localMedia);
        notifyItemChanged(viewHolder.getAdapterPosition());
        selectImage(viewHolder, !isSelected, true);
        if (this.imageSelectChangedListener != null) {
            this.imageSelectChangedListener.onChange(this.selectImages);
        }
    }

    private void disZoom(ImageView imageView) {
        if (this.zoomAnim) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(imageView, "scaleX", new float[]{1.12f, 1.0f}), ObjectAnimator.ofFloat(imageView, "scaleY", new float[]{1.12f, 1.0f})});
            animatorSet.setDuration(450);
            animatorSet.start();
        }
    }

    private void notifyCheckChanged(ViewHolder viewHolder, LocalMedia localMedia) {
        viewHolder.check.setText("");
        for (LocalMedia next : this.selectImages) {
            if (next.getPath().equals(localMedia.getPath())) {
                localMedia.setNum(next.getNum());
                next.setPosition(localMedia.getPosition());
                viewHolder.check.setText(String.valueOf(localMedia.getNum()));
            }
        }
    }

    private void singleRadioMediaImage() {
        if (this.selectImages != null && this.selectImages.size() > 0) {
            this.isGo = true;
            LocalMedia localMedia = this.selectImages.get(0);
            notifyItemChanged((!this.config.isCamera && !this.isGo) ? localMedia.position > 0 ? localMedia.position - 1 : 0 : localMedia.position);
            this.selectImages.clear();
        }
    }

    private void subSelectPosition() {
        if (this.is_checked_num) {
            int size = this.selectImages.size();
            int i = 0;
            while (i < size) {
                LocalMedia localMedia = this.selectImages.get(i);
                i++;
                localMedia.setNum(i);
                notifyItemChanged(localMedia.position);
            }
        }
    }

    private void zoom(ImageView imageView) {
        if (this.zoomAnim) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(imageView, "scaleX", new float[]{1.0f, 1.12f}), ObjectAnimator.ofFloat(imageView, "scaleY", new float[]{1.0f, 1.12f})});
            animatorSet.setDuration(450);
            animatorSet.start();
        }
    }

    public void bindImagesData(List<LocalMedia> list) {
        this.images = list;
        notifyDataSetChanged();
    }

    public void bindSelectImages(List<LocalMedia> list) {
        ArrayList arrayList = new ArrayList();
        for (LocalMedia add : list) {
            arrayList.add(add);
        }
        this.selectImages = arrayList;
        subSelectPosition();
        if (this.imageSelectChangedListener != null) {
            this.imageSelectChangedListener.onChange(this.selectImages);
        }
    }

    public List<LocalMedia> getImages() {
        if (this.images == null) {
            this.images = new ArrayList();
        }
        return this.images;
    }

    public int getItemCount() {
        return this.showCamera ? this.images.size() + 1 : this.images.size();
    }

    public int getItemViewType(int i) {
        return (!this.showCamera || i != 0) ? 2 : 1;
    }

    public List<LocalMedia> getSelectedImages() {
        if (this.selectImages == null) {
            this.selectImages = new ArrayList();
        }
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

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int i2 = 0;
        if (getItemViewType(i) == 1) {
            ((HeaderViewHolder) viewHolder).headerView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (PictureImageGridAdapter.this.imageSelectChangedListener != null) {
                        PictureImageGridAdapter.this.imageSelectChangedListener.onTakePhoto();
                    }
                }
            });
            return;
        }
        final ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        final LocalMedia localMedia = this.images.get(this.showCamera ? i - 1 : i);
        localMedia.position = viewHolder2.getAdapterPosition();
        final String path = localMedia.getPath();
        String pictureType = localMedia.getPictureType();
        if (this.is_checked_num) {
            notifyCheckChanged(viewHolder2, localMedia);
        }
        selectImage(viewHolder2, isSelected(localMedia), false);
        final int isPictureType = PictureMimeType.isPictureType(pictureType);
        viewHolder2.tv_isGif.setVisibility(PictureMimeType.isGif(pictureType) ? 0 : 8);
        if (this.mimeType == PictureMimeType.ofAudio()) {
            viewHolder2.tv_duration.setVisibility(0);
            StringUtils.modifyTextViewDrawable(viewHolder2.tv_duration, ContextCompat.getDrawable(this.context, R.drawable.picture_audio), 0);
        } else {
            StringUtils.modifyTextViewDrawable(viewHolder2.tv_duration, ContextCompat.getDrawable(this.context, R.drawable.video_icon), 0);
            viewHolder2.tv_duration.setVisibility(isPictureType == 2 ? 0 : 8);
        }
        boolean isLongImg = PictureMimeType.isLongImg(localMedia);
        TextView textView = viewHolder2.tv_long_chart;
        if (!isLongImg) {
            i2 = 8;
        }
        textView.setVisibility(i2);
        viewHolder2.tv_duration.setText(DateUtils.timeParse(localMedia.getDuration()));
        if (this.mimeType == PictureMimeType.ofAudio()) {
            viewHolder2.iv_picture.setImageResource(R.drawable.audio_placeholder);
        } else if (this.overrideWidth > 0 || this.overrideHeight > 0) {
            Glide.with(this.context).load(path).asBitmap().centerCrop().override(this.overrideWidth, this.overrideHeight).placeholder(R.drawable.image_placeholder).into(viewHolder2.iv_picture);
        } else {
            Glide.with(this.context).load(path).asBitmap().centerCrop().sizeMultiplier(this.sizeMultiplier).placeholder(R.drawable.image_placeholder).into(viewHolder2.iv_picture);
        }
        if (this.enablePreview || this.enablePreviewVideo || this.enablePreviewAudio) {
            viewHolder2.ll_check.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!new File(path).exists()) {
                        ToastManage.s(PictureImageGridAdapter.this.context, PictureMimeType.s(PictureImageGridAdapter.this.context, isPictureType));
                    } else {
                        PictureImageGridAdapter.this.changeCheckboxState(viewHolder2, localMedia);
                    }
                }
            });
        }
        final String str = path;
        final int i3 = isPictureType;
        final int i4 = i;
        final LocalMedia localMedia2 = localMedia;
        final ViewHolder viewHolder3 = viewHolder2;
        viewHolder2.contentView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean z = true;
                if (!new File(str).exists()) {
                    ToastManage.s(PictureImageGridAdapter.this.context, PictureMimeType.s(PictureImageGridAdapter.this.context, i3));
                    return;
                }
                int i = PictureImageGridAdapter.this.showCamera ? i4 - 1 : i4;
                if ((i3 != 1 || !PictureImageGridAdapter.this.enablePreview) && ((i3 != 2 || (!PictureImageGridAdapter.this.enablePreviewVideo && PictureImageGridAdapter.this.selectMode != 1)) && (i3 != 3 || (!PictureImageGridAdapter.this.enablePreviewAudio && PictureImageGridAdapter.this.selectMode != 1)))) {
                    z = false;
                }
                if (z) {
                    PictureImageGridAdapter.this.imageSelectChangedListener.onPictureClick(localMedia2, i);
                } else {
                    PictureImageGridAdapter.this.changeCheckboxState(viewHolder3, localMedia2);
                }
            }
        });
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return i == 1 ? new HeaderViewHolder(LayoutInflater.from(this.context).inflate(R.layout.picture_item_camera, viewGroup, false)) : new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.picture_image_grid_item, viewGroup, false));
    }

    public void selectImage(ViewHolder viewHolder, boolean z, boolean z2) {
        viewHolder.check.setSelected(z);
        if (z) {
            if (z2 && this.animation != null) {
                viewHolder.check.startAnimation(this.animation);
            }
            viewHolder.iv_picture.setColorFilter(ContextCompat.getColor(this.context, R.color.image_overlay_false), PorterDuff.Mode.SRC_ATOP);
            return;
        }
        viewHolder.iv_picture.setColorFilter(ContextCompat.getColor(this.context, R.color.image_overlay_false), PorterDuff.Mode.SRC_ATOP);
    }

    public void setOnPhotoSelectChangedListener(OnPhotoSelectChangedListener onPhotoSelectChangedListener) {
        this.imageSelectChangedListener = onPhotoSelectChangedListener;
    }

    public void setShowCamera(boolean z) {
        this.showCamera = z;
    }

    public void updateSelectedImages(List<LocalMedia> list) {
        if (this.selectImages != null) {
            this.selectImages.clear();
            this.selectImages.addAll(list);
        }
    }
}
