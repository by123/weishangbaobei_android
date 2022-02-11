package com.meiqia.meiqiasdk.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.luck.picture.lib.config.PictureMimeType;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.imageloader.MQImage;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.meiqia.meiqiasdk.third.photoview.PhotoViewAttacher;
import com.meiqia.meiqiasdk.util.MQAsyncTask;
import com.meiqia.meiqiasdk.util.MQBrowserPhotoViewAttacher;
import com.meiqia.meiqiasdk.util.MQSavePhotoTask;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.meiqia.meiqiasdk.widget.MQHackyViewPager;
import com.meiqia.meiqiasdk.widget.MQImageView;
import com.stub.StubApp;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.util.ArrayList;

public class MQPhotoPreviewActivity extends Activity implements PhotoViewAttacher.OnViewTapListener, View.OnClickListener, MQAsyncTask.Callback<Void> {
    private static final String EXTRA_CURRENT_POSITION = "EXTRA_CURRENT_POSITION";
    private static final String EXTRA_IS_SINGLE_PREVIEW = "EXTRA_IS_SINGLE_PREVIEW";
    private static final String EXTRA_PHOTO_PATH = "EXTRA_PHOTO_PATH";
    private static final String EXTRA_PREVIEW_IMAGES = "EXTRA_PREVIEW_IMAGES";
    private static final String EXTRA_SAVE_IMG_DIR = "EXTRA_SAVE_IMG_DIR";
    private MQHackyViewPager mContentHvp;
    private ImageView mDownloadIv;
    /* access modifiers changed from: private */
    public boolean mIsHidden = false;
    private boolean mIsSinglePreview;
    private long mLastShowHiddenTime;
    /* access modifiers changed from: private */
    public ArrayList<String> mPreviewImages;
    private File mSaveImgDir;
    /* access modifiers changed from: private */
    public MQSavePhotoTask mSavePhotoTask;
    private RelativeLayout mTitleRl;
    private TextView mTitleTv;

    static {
        StubApp.interface11(6120);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public static Intent newIntent(Context context, File file, ArrayList<String> arrayList, int i) {
        Intent intent = new Intent(context, MQPhotoPreviewActivity.class);
        intent.putExtra(EXTRA_SAVE_IMG_DIR, file);
        intent.putStringArrayListExtra(EXTRA_PREVIEW_IMAGES, arrayList);
        intent.putExtra(EXTRA_CURRENT_POSITION, i);
        intent.putExtra(EXTRA_IS_SINGLE_PREVIEW, false);
        return intent;
    }

    public static Intent newIntent(Context context, File file, String str) {
        Intent intent = new Intent(context, MQPhotoPreviewActivity.class);
        intent.putExtra(EXTRA_SAVE_IMG_DIR, file);
        intent.putExtra(EXTRA_PHOTO_PATH, str);
        intent.putExtra(EXTRA_CURRENT_POSITION, 0);
        intent.putExtra(EXTRA_IS_SINGLE_PREVIEW, true);
        return intent;
    }

    private void initView() {
        setContentView(R.layout.mq_activity_photo_preview);
        this.mTitleRl = (RelativeLayout) findViewById(R.id.title_rl);
        this.mTitleTv = (TextView) findViewById(R.id.title_tv);
        this.mDownloadIv = (ImageView) findViewById(R.id.download_iv);
        this.mContentHvp = (MQHackyViewPager) findViewById(R.id.content_hvp);
    }

    private void initListener() {
        findViewById(R.id.back_iv).setOnClickListener(this);
        this.mDownloadIv.setOnClickListener(this);
        this.mContentHvp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int i) {
                MQPhotoPreviewActivity.this.renderTitleTv();
            }
        });
    }

    private void processLogic(Bundle bundle) {
        this.mSaveImgDir = (File) getIntent().getSerializableExtra(EXTRA_SAVE_IMG_DIR);
        if (this.mSaveImgDir == null) {
            this.mDownloadIv.setVisibility(4);
        }
        this.mPreviewImages = getIntent().getStringArrayListExtra(EXTRA_PREVIEW_IMAGES);
        this.mIsSinglePreview = getIntent().getBooleanExtra(EXTRA_IS_SINGLE_PREVIEW, false);
        if (this.mIsSinglePreview) {
            this.mPreviewImages = new ArrayList<>();
            this.mPreviewImages.add(getIntent().getStringExtra(EXTRA_PHOTO_PATH));
        }
        int intExtra = getIntent().getIntExtra(EXTRA_CURRENT_POSITION, 0);
        this.mContentHvp.setAdapter(new ImagePageAdapter());
        this.mContentHvp.setCurrentItem(intExtra);
        renderTitleTv();
        this.mTitleRl.postDelayed(new Runnable() {
            public void run() {
                MQPhotoPreviewActivity.this.hiddenTitlebar();
            }
        }, 2000);
    }

    /* access modifiers changed from: private */
    public void renderTitleTv() {
        if (this.mIsSinglePreview) {
            this.mTitleTv.setText(R.string.mq_view_photo);
            return;
        }
        TextView textView = this.mTitleTv;
        textView.setText((this.mContentHvp.getCurrentItem() + 1) + "/" + this.mPreviewImages.size());
    }

    public void onViewTap(View view, float f, float f2) {
        if (System.currentTimeMillis() - this.mLastShowHiddenTime > 500) {
            this.mLastShowHiddenTime = System.currentTimeMillis();
            if (this.mIsHidden) {
                showTitlebar();
            } else {
                hiddenTitlebar();
            }
        }
    }

    private void showTitlebar() {
        ViewCompat.animate(this.mTitleRl).translationY(CropImageView.DEFAULT_ASPECT_RATIO).setInterpolator(new DecelerateInterpolator(2.0f)).setListener(new ViewPropertyAnimatorListenerAdapter() {
            public void onAnimationEnd(View view) {
                boolean unused = MQPhotoPreviewActivity.this.mIsHidden = false;
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public void hiddenTitlebar() {
        ViewCompat.animate(this.mTitleRl).translationY((float) (-this.mTitleRl.getHeight())).setInterpolator(new DecelerateInterpolator(2.0f)).setListener(new ViewPropertyAnimatorListenerAdapter() {
            public void onAnimationEnd(View view) {
                boolean unused = MQPhotoPreviewActivity.this.mIsHidden = true;
            }
        }).start();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.back_iv) {
            onBackPressed();
        } else if (view.getId() == R.id.download_iv && this.mSavePhotoTask == null) {
            savePic();
        }
    }

    private synchronized void savePic() {
        if (this.mSavePhotoTask == null) {
            String str = this.mPreviewImages.get(this.mContentHvp.getCurrentItem());
            if (str.startsWith("file")) {
                File file = new File(str.replace("file://", ""));
                if (file.exists()) {
                    MQUtils.showSafe((Context) this, (CharSequence) getString(R.string.mq_save_img_success_folder, new Object[]{file.getParentFile().getAbsolutePath()}));
                    return;
                }
            }
            String str2 = MQUtils.stringToMD5(str) + PictureMimeType.PNG;
            File file2 = new File(this.mSaveImgDir, str2);
            if (file2.exists()) {
                MQUtils.showSafe((Context) this, (CharSequence) getString(R.string.mq_save_img_success_folder, new Object[]{this.mSaveImgDir.getAbsolutePath()}));
                return;
            }
            if (Build.VERSION.SDK_INT >= 29) {
                File externalFilesDir = getExternalFilesDir("mq");
                if (!externalFilesDir.exists()) {
                    externalFilesDir.mkdirs();
                }
                File file3 = new File(externalFilesDir, str2);
                if (file3.exists()) {
                    MQUtils.showSafe((Context) this, (CharSequence) getString(R.string.mq_save_img_success_folder, new Object[]{externalFilesDir.getAbsolutePath()}));
                    return;
                }
                file2 = file3;
            }
            this.mSavePhotoTask = new MQSavePhotoTask(this, this, file2);
            MQImage.downloadImage(this, str, new MQImageLoader.MQDownloadImageListener() {
                public void onSuccess(String str, Bitmap bitmap) {
                    if (MQPhotoPreviewActivity.this.mSavePhotoTask != null) {
                        MQPhotoPreviewActivity.this.mSavePhotoTask.setBitmapAndPerform(bitmap);
                    }
                }

                public void onFailed(String str) {
                    MQSavePhotoTask unused = MQPhotoPreviewActivity.this.mSavePhotoTask = null;
                    MQUtils.showSafe((Context) MQPhotoPreviewActivity.this, R.string.mq_save_img_failure);
                }
            });
        }
    }

    public void onPostExecute(Void voidR) {
        this.mSavePhotoTask = null;
    }

    public void onTaskCancelled() {
        this.mSavePhotoTask = null;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mSavePhotoTask != null) {
            this.mSavePhotoTask.cancelTask();
            this.mSavePhotoTask = null;
        }
        super.onDestroy();
    }

    private class ImagePageAdapter extends PagerAdapter {
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        private ImagePageAdapter() {
        }

        public int getCount() {
            return MQPhotoPreviewActivity.this.mPreviewImages.size();
        }

        public View instantiateItem(ViewGroup viewGroup, int i) {
            final MQImageView mQImageView = new MQImageView(viewGroup.getContext());
            viewGroup.addView(mQImageView, -1, -1);
            final MQBrowserPhotoViewAttacher mQBrowserPhotoViewAttacher = new MQBrowserPhotoViewAttacher(mQImageView);
            mQBrowserPhotoViewAttacher.setOnViewTapListener(MQPhotoPreviewActivity.this);
            mQImageView.setDrawableChangedCallback(new MQImageView.OnDrawableChangedCallback() {
                public void onDrawableChanged(Drawable drawable) {
                    if (drawable == null || drawable.getIntrinsicHeight() <= drawable.getIntrinsicWidth() || drawable.getIntrinsicHeight() <= MQUtils.getScreenHeight(mQImageView.getContext())) {
                        mQBrowserPhotoViewAttacher.update();
                        return;
                    }
                    mQBrowserPhotoViewAttacher.setIsSetTopCrop(true);
                    mQBrowserPhotoViewAttacher.setUpdateBaseMatrix();
                }
            });
            MQImage.displayImage(MQPhotoPreviewActivity.this, mQImageView, (String) MQPhotoPreviewActivity.this.mPreviewImages.get(i), R.drawable.mq_ic_holder_dark, R.drawable.mq_ic_holder_dark, MQUtils.getScreenWidth(MQPhotoPreviewActivity.this), MQUtils.getScreenHeight(MQPhotoPreviewActivity.this), (MQImageLoader.MQDisplayImageListener) null);
            return mQImageView;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }
}
