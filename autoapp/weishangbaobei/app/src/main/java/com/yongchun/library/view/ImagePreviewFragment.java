package com.yongchun.library.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yongchun.library.R;
import com.yongchun.library.view.photoview.OnViewTapListener2;
import com.yongchun.library.view.photoview.PhotoViewAttacher2;
import com.youth.banner.BannerConfig;
import java.io.File;

public class ImagePreviewFragment extends Fragment {
    public static final String PATH = "path";

    public static ImagePreviewFragment getInstance(String str) {
        ImagePreviewFragment imagePreviewFragment = new ImagePreviewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PATH, str);
        imagePreviewFragment.setArguments(bundle);
        return imagePreviewFragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_image_preview, viewGroup, false);
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.preview_image);
        PhotoViewAttacher2 photoViewAttacher2 = new PhotoViewAttacher2(imageView);
        final PhotoViewAttacher2 photoViewAttacher22 = photoViewAttacher2;
        Glide.with(viewGroup.getContext()).load(new File(getArguments().getString(PATH))).asBitmap().into(new SimpleTarget<Bitmap>(480, BannerConfig.DURATION) {
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                imageView.setImageBitmap(bitmap);
                photoViewAttacher22.update();
            }
        });
        photoViewAttacher2.setOnViewTapListener(new OnViewTapListener2() {
            public void onViewTap(View view, float f, float f2) {
                ImagePreviewFragment.this.getActivity().switchBarVisibility();
            }
        });
        return inflate;
    }
}
