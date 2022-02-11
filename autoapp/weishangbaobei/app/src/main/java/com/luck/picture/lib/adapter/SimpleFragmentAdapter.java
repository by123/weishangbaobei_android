package com.luck.picture.lib.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.luck.picture.lib.PictureVideoPlayActivity;
import com.luck.picture.lib.R;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.photoview.OnViewTapListener;
import com.luck.picture.lib.photoview.PhotoView;
import com.luck.picture.lib.widget.longimage.ImageSource;
import com.luck.picture.lib.widget.longimage.ImageViewState;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.yalantis.ucrop.view.CropImageView;
import com.youth.banner.BannerConfig;
import java.util.List;

public class SimpleFragmentAdapter extends PagerAdapter {
    private List<LocalMedia> images;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public OnCallBackActivity onBackPressed;

    public interface OnCallBackActivity {
        void onActivityBackPressed();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public SimpleFragmentAdapter(List<LocalMedia> list, Context context, OnCallBackActivity onCallBackActivity) {
        this.images = list;
        this.mContext = context;
        this.onBackPressed = onCallBackActivity;
    }

    public int getCount() {
        if (this.images != null) {
            return this.images.size();
        }
        return 0;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        String compressPath;
        ViewGroup viewGroup2 = viewGroup;
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.picture_image_preview, viewGroup2, false);
        PhotoView photoView = (PhotoView) inflate.findViewById(R.id.preview_image);
        SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) inflate.findViewById(R.id.longImg);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_play);
        LocalMedia localMedia = this.images.get(i);
        if (localMedia != null) {
            String pictureType = localMedia.getPictureType();
            int i2 = 8;
            imageView.setVisibility(pictureType.startsWith(PictureConfig.VIDEO) ? 0 : 8);
            if (localMedia.isCut() && !localMedia.isCompressed()) {
                compressPath = localMedia.getCutPath();
            } else if (localMedia.isCompressed() || (localMedia.isCut() && localMedia.isCompressed())) {
                compressPath = localMedia.getCompressPath();
            } else {
                compressPath = localMedia.getPath();
            }
            final String str = compressPath;
            boolean isGif = PictureMimeType.isGif(pictureType);
            final boolean isLongImg = PictureMimeType.isLongImg(localMedia);
            photoView.setVisibility((!isLongImg || isGif) ? 0 : 8);
            if (isLongImg && !isGif) {
                i2 = 0;
            }
            subsamplingScaleImageView.setVisibility(i2);
            if (!isGif || localMedia.isCompressed()) {
                BitmapTypeRequest asBitmap = Glide.with(inflate.getContext()).load(str).asBitmap();
                final SubsamplingScaleImageView subsamplingScaleImageView2 = subsamplingScaleImageView;
                AnonymousClass1 r9 = r0;
                final PhotoView photoView2 = photoView;
                AnonymousClass1 r0 = new SimpleTarget<Bitmap>(480, BannerConfig.DURATION) {
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        if (isLongImg) {
                            SimpleFragmentAdapter.this.displayLongPic(bitmap, subsamplingScaleImageView2);
                        } else {
                            photoView2.setImageBitmap(bitmap);
                        }
                    }
                };
                asBitmap.into(r9);
            } else {
                Glide.with(inflate.getContext()).load(str).asGif().override(480, BannerConfig.DURATION).priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE).into(photoView);
            }
            photoView.setOnViewTapListener(new OnViewTapListener() {
                public void onViewTap(View view, float f, float f2) {
                    if (SimpleFragmentAdapter.this.onBackPressed != null) {
                        SimpleFragmentAdapter.this.onBackPressed.onActivityBackPressed();
                    }
                }
            });
            subsamplingScaleImageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SimpleFragmentAdapter.this.onBackPressed != null) {
                        SimpleFragmentAdapter.this.onBackPressed.onActivityBackPressed();
                    }
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("video_path", str);
                    intent.putExtras(bundle);
                    intent.setClass(SimpleFragmentAdapter.this.mContext, PictureVideoPlayActivity.class);
                    SimpleFragmentAdapter.this.mContext.startActivity(intent);
                }
            });
        }
        viewGroup2.addView(inflate, 0);
        return inflate;
    }

    /* access modifiers changed from: private */
    public void displayLongPic(Bitmap bitmap, SubsamplingScaleImageView subsamplingScaleImageView) {
        subsamplingScaleImageView.setQuickScaleEnabled(true);
        subsamplingScaleImageView.setZoomEnabled(true);
        subsamplingScaleImageView.setPanEnabled(true);
        subsamplingScaleImageView.setDoubleTapZoomDuration(100);
        subsamplingScaleImageView.setMinimumScaleType(2);
        subsamplingScaleImageView.setDoubleTapZoomDpi(2);
        subsamplingScaleImageView.setImage(ImageSource.cachedBitmap(bitmap), new ImageViewState(CropImageView.DEFAULT_ASPECT_RATIO, new PointF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO), 0));
    }
}
