package com.luck.picture.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.dialog.CustomDialog;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.photoview.OnViewTapListener;
import com.luck.picture.lib.photoview.PhotoView;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.luck.picture.lib.tools.ScreenUtils;
import com.luck.picture.lib.tools.ToastManage;
import com.luck.picture.lib.widget.PreviewViewPager;
import com.luck.picture.lib.widget.longimage.ImageSource;
import com.luck.picture.lib.widget.longimage.ImageViewState;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.stub.StubApp;
import com.yalantis.ucrop.view.CropImageView;
import com.youth.banner.BannerConfig;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PictureExternalPreviewActivity extends PictureBaseActivity implements View.OnClickListener {
    private SimpleFragmentAdapter adapter;
    /* access modifiers changed from: private */
    public String directory_path;
    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 200) {
                Context context = PictureExternalPreviewActivity.this.mContext;
                ToastManage.s(context, PictureExternalPreviewActivity.this.getString(R.string.picture_save_success) + "\n" + ((String) message.obj));
                PictureExternalPreviewActivity.this.dismissDialog();
            }
        }
    };
    /* access modifiers changed from: private */
    public List<LocalMedia> images = new ArrayList();
    /* access modifiers changed from: private */
    public LayoutInflater inflater;
    private ImageButton left_back;
    /* access modifiers changed from: private */
    public loadDataThread loadDataThread;
    private int position = 0;
    /* access modifiers changed from: private */
    public RxPermissions rxPermissions;
    /* access modifiers changed from: private */
    public TextView tv_title;
    private PreviewViewPager viewPager;

    public class SimpleFragmentAdapter extends PagerAdapter {
        public SimpleFragmentAdapter() {
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public int getCount() {
            return PictureExternalPreviewActivity.this.images.size();
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            int i2 = 8;
            View inflate = PictureExternalPreviewActivity.this.inflater.inflate(R.layout.picture_image_preview, viewGroup, false);
            final PhotoView photoView = (PhotoView) inflate.findViewById(R.id.preview_image);
            final SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) inflate.findViewById(R.id.longImg);
            LocalMedia localMedia = (LocalMedia) PictureExternalPreviewActivity.this.images.get(i);
            if (localMedia != null) {
                String pictureType = localMedia.getPictureType();
                final String compressPath = (!localMedia.isCut() || localMedia.isCompressed()) ? (localMedia.isCompressed() || (localMedia.isCut() && localMedia.isCompressed())) ? localMedia.getCompressPath() : localMedia.getPath() : localMedia.getCutPath();
                if (PictureMimeType.isHttp(compressPath)) {
                    PictureExternalPreviewActivity.this.showPleaseDialog();
                }
                boolean isGif = PictureMimeType.isGif(pictureType);
                final boolean isLongImg = PictureMimeType.isLongImg(localMedia);
                photoView.setVisibility((!isLongImg || isGif) ? 0 : 8);
                if (isLongImg && !isGif) {
                    i2 = 0;
                }
                subsamplingScaleImageView.setVisibility(i2);
                if (!isGif || localMedia.isCompressed()) {
                    Glide.with(PictureExternalPreviewActivity.this).load(compressPath).asBitmap().into(new SimpleTarget<Bitmap>(480, BannerConfig.DURATION) {
                        public void onLoadFailed(Exception exc, Drawable drawable) {
                            SimpleFragmentAdapter.super.onLoadFailed(exc, drawable);
                            PictureExternalPreviewActivity.this.dismissDialog();
                        }

                        public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                            PictureExternalPreviewActivity.this.dismissDialog();
                            if (isLongImg) {
                                PictureExternalPreviewActivity.this.displayLongPic(bitmap, subsamplingScaleImageView);
                            } else {
                                photoView.setImageBitmap(bitmap);
                            }
                        }
                    });
                } else {
                    Glide.with(PictureExternalPreviewActivity.this).load(compressPath).asGif().override(480, BannerConfig.DURATION).priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE).listener(new RequestListener<String, GifDrawable>() {
                        public boolean onException(Exception exc, String str, Target<GifDrawable> target, boolean z) {
                            PictureExternalPreviewActivity.this.dismissDialog();
                            return false;
                        }

                        public boolean onResourceReady(GifDrawable gifDrawable, String str, Target<GifDrawable> target, boolean z, boolean z2) {
                            PictureExternalPreviewActivity.this.dismissDialog();
                            return false;
                        }
                    }).into(photoView);
                }
                photoView.setOnViewTapListener(new OnViewTapListener() {
                    public void onViewTap(View view, float f, float f2) {
                        PictureExternalPreviewActivity.this.finish();
                        PictureExternalPreviewActivity.this.overridePendingTransition(0, R.anim.a3);
                    }
                });
                subsamplingScaleImageView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        PictureExternalPreviewActivity.this.finish();
                        PictureExternalPreviewActivity.this.overridePendingTransition(0, R.anim.a3);
                    }
                });
                photoView.setOnLongClickListener(new View.OnLongClickListener() {
                    /* JADX WARNING: type inference failed for: r2v2, types: [com.luck.picture.lib.PictureExternalPreviewActivity, android.app.Activity] */
                    public boolean onLongClick(View view) {
                        if (PictureExternalPreviewActivity.this.rxPermissions == null) {
                            RxPermissions unused = PictureExternalPreviewActivity.this.rxPermissions = new RxPermissions(PictureExternalPreviewActivity.this);
                        }
                        PictureExternalPreviewActivity.this.rxPermissions.request("android.permission.WRITE_EXTERNAL_STORAGE").subscribe(new Observer<Boolean>() {
                            public void onComplete() {
                            }

                            public void onError(Throwable th) {
                            }

                            public void onNext(Boolean bool) {
                                if (bool.booleanValue()) {
                                    PictureExternalPreviewActivity.this.showDownLoadDialog(compressPath);
                                } else {
                                    ToastManage.s(PictureExternalPreviewActivity.this.mContext, PictureExternalPreviewActivity.this.getString(R.string.picture_jurisdiction));
                                }
                            }

                            public void onSubscribe(Disposable disposable) {
                            }
                        });
                        return true;
                    }
                });
            }
            viewGroup.addView(inflate, 0);
            return inflate;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    public class loadDataThread extends Thread {
        private String path;

        public loadDataThread(String str) {
            this.path = str;
        }

        public void run() {
            try {
                PictureExternalPreviewActivity.this.showLoadingImage(this.path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static {
        StubApp.interface11(5579);
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

    private void initViewPageAdapterData() {
        TextView textView = this.tv_title;
        textView.setText((this.position + 1) + "/" + this.images.size());
        this.adapter = new SimpleFragmentAdapter();
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.setCurrentItem(this.position);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                TextView access$100 = PictureExternalPreviewActivity.this.tv_title;
                access$100.setText((i + 1) + "/" + PictureExternalPreviewActivity.this.images.size());
            }
        });
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.luck.picture.lib.PictureExternalPreviewActivity] */
    /* access modifiers changed from: private */
    public void showDownLoadDialog(final String str) {
        final CustomDialog customDialog = new CustomDialog(this, (ScreenUtils.getScreenWidth(this) * 3) / 4, ScreenUtils.getScreenHeight(this) / 4, R.layout.picture_wind_base_dialog_xml, R.style.Theme_dialog);
        ((TextView) customDialog.findViewById(R.id.tv_title)).setText(getString(R.string.picture_prompt));
        ((TextView) customDialog.findViewById(R.id.tv_content)).setText(getString(R.string.picture_prompt_content));
        ((Button) customDialog.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });
        ((Button) customDialog.findViewById(R.id.btn_commit)).setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: type inference failed for: r0v4, types: [android.content.Context, com.luck.picture.lib.PictureExternalPreviewActivity] */
            public void onClick(View view) {
                PictureExternalPreviewActivity.this.showPleaseDialog();
                if (PictureMimeType.isHttp(str)) {
                    loadDataThread unused = PictureExternalPreviewActivity.this.loadDataThread = new loadDataThread(str);
                    PictureExternalPreviewActivity.this.loadDataThread.start();
                } else {
                    try {
                        ? r0 = PictureExternalPreviewActivity.this;
                        String createDir = PictureFileUtils.createDir(r0, System.currentTimeMillis() + PictureMimeType.PNG, PictureExternalPreviewActivity.this.directory_path);
                        PictureFileUtils.copyFile(str, createDir);
                        Context context = PictureExternalPreviewActivity.this.mContext;
                        ToastManage.s(context, PictureExternalPreviewActivity.this.getString(R.string.picture_save_success) + "\n" + createDir);
                        PictureExternalPreviewActivity.this.dismissDialog();
                    } catch (IOException e) {
                        Context context2 = PictureExternalPreviewActivity.this.mContext;
                        ToastManage.s(context2, PictureExternalPreviewActivity.this.getString(R.string.picture_save_error) + "\n" + e.getMessage());
                        PictureExternalPreviewActivity.this.dismissDialog();
                        e.printStackTrace();
                    }
                }
                customDialog.dismiss();
            }
        });
        customDialog.show();
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0, R.anim.a3);
    }

    public void onClick(View view) {
        finish();
        overridePendingTransition(0, R.anim.a3);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.loadDataThread != null) {
            this.handler.removeCallbacks(this.loadDataThread);
            this.loadDataThread = null;
        }
    }

    /* JADX WARNING: type inference failed for: r12v0, types: [android.content.Context, com.luck.picture.lib.PictureExternalPreviewActivity] */
    public void showLoadingImage(String str) {
        int i = 0;
        try {
            URL url = new URL(str);
            String createDir = PictureFileUtils.createDir(this, System.currentTimeMillis() + PictureMimeType.PNG, this.directory_path);
            byte[] bArr = new byte[8192];
            long currentTimeMillis = System.currentTimeMillis();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(createDir));
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read > -1) {
                    bufferedOutputStream.write(bArr, 0, read);
                    i += read;
                    long currentTimeMillis2 = ((long) i) / (System.currentTimeMillis() - currentTimeMillis);
                } else {
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    Message obtainMessage = this.handler.obtainMessage();
                    obtainMessage.what = 200;
                    obtainMessage.obj = createDir;
                    this.handler.sendMessage(obtainMessage);
                    return;
                }
            }
        } catch (IOException e) {
            Context context = this.mContext;
            ToastManage.s(context, getString(R.string.picture_save_error) + "\n" + e.getMessage());
            e.printStackTrace();
        }
    }
}
