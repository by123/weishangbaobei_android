package com.meiqia.meiqiasdk.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.imageloader.MQImage;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.meiqia.meiqiasdk.third.photoview.PhotoViewAttacher;
import com.meiqia.meiqiasdk.util.MQBrowserPhotoViewAttacher;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.meiqia.meiqiasdk.widget.MQHackyViewPager;
import com.meiqia.meiqiasdk.widget.MQImageView;
import com.stub.StubApp;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

public class MQPhotoPickerPreviewActivity extends Activity implements View.OnClickListener, PhotoViewAttacher.OnViewTapListener {
    private static final String EXTRA_CURRENT_POSITION = "EXTRA_CURRENT_POSITION";
    private static final String EXTRA_IS_FROM_TAKE_PHOTO = "EXTRA_IS_FROM_TAKE_PHOTO";
    private static final String EXTRA_MAX_CHOOSE_COUNT = "EXTRA_MAX_CHOOSE_COUNT";
    private static final String EXTRA_PREVIEW_IMAGES = "EXTRA_PREVIEW_IMAGES";
    private static final String EXTRA_SELECTED_IMAGES = "EXTRA_SELECTED_IMAGES";
    private static final String EXTRA_TOP_RIGHT_BTN_TEXT = "EXTRA_TOP_RIGHT_BTN_TEXT";
    /* access modifiers changed from: private */
    public RelativeLayout mChooseRl;
    private TextView mChooseTv;
    private MQHackyViewPager mContentHvp;
    private boolean mIsFromTakePhoto;
    /* access modifiers changed from: private */
    public boolean mIsHidden = false;
    private long mLastShowHiddenTime;
    private int mMaxChooseCount = 1;
    /* access modifiers changed from: private */
    public ArrayList<String> mPreviewImages;
    private ArrayList<String> mSelectedImages;
    private TextView mSubmitTv;
    private RelativeLayout mTitleRl;
    private TextView mTitleTv;
    private String mTopRightBtnText;

    static {
        StubApp.interface11(6112);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public static Intent newIntent(Context context, int i, ArrayList<String> arrayList, int i2, String str, boolean z) {
        Intent intent = new Intent(context, MQPhotoPickerPreviewActivity.class);
        intent.putStringArrayListExtra(EXTRA_SELECTED_IMAGES, arrayList);
        intent.putExtra(EXTRA_MAX_CHOOSE_COUNT, i);
        intent.putExtra(EXTRA_CURRENT_POSITION, i2);
        intent.putExtra(EXTRA_TOP_RIGHT_BTN_TEXT, str);
        intent.putExtra(EXTRA_IS_FROM_TAKE_PHOTO, z);
        return intent;
    }

    public static ArrayList<String> getSelectedImages(Intent intent) {
        return intent.getStringArrayListExtra(EXTRA_SELECTED_IMAGES);
    }

    public static boolean getIsFromTakePhoto(Intent intent) {
        return intent.getBooleanExtra(EXTRA_IS_FROM_TAKE_PHOTO, false);
    }

    private void initView() {
        setContentView(R.layout.mq_activity_photo_picker_preview);
        this.mTitleRl = (RelativeLayout) findViewById(R.id.title_rl);
        this.mTitleTv = (TextView) findViewById(R.id.title_tv);
        this.mSubmitTv = (TextView) findViewById(R.id.submit_tv);
        this.mContentHvp = (MQHackyViewPager) findViewById(R.id.content_hvp);
        this.mChooseRl = (RelativeLayout) findViewById(R.id.choose_rl);
        this.mChooseTv = (TextView) findViewById(R.id.choose_tv);
    }

    private void initListener() {
        findViewById(R.id.back_iv).setOnClickListener(this);
        this.mSubmitTv.setOnClickListener(this);
        this.mChooseTv.setOnClickListener(this);
        this.mContentHvp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int i) {
                MQPhotoPickerPreviewActivity.this.handlePageSelectedStatus();
            }
        });
    }

    private void processLogic(Bundle bundle) {
        this.mMaxChooseCount = getIntent().getIntExtra(EXTRA_MAX_CHOOSE_COUNT, 1);
        if (this.mMaxChooseCount < 1) {
            this.mMaxChooseCount = 1;
        }
        this.mSelectedImages = getIntent().getStringArrayListExtra(EXTRA_SELECTED_IMAGES);
        this.mPreviewImages = MQPhotoPickerActivity.sPreviewImages;
        if (TextUtils.isEmpty(this.mPreviewImages.get(0))) {
            this.mPreviewImages.remove(0);
        }
        this.mIsFromTakePhoto = getIntent().getBooleanExtra(EXTRA_IS_FROM_TAKE_PHOTO, false);
        if (this.mIsFromTakePhoto) {
            this.mChooseRl.setVisibility(4);
        }
        this.mTopRightBtnText = getIntent().getStringExtra(EXTRA_TOP_RIGHT_BTN_TEXT);
        int intExtra = getIntent().getIntExtra(EXTRA_CURRENT_POSITION, 0);
        this.mContentHvp.setAdapter(new ImagePageAdapter());
        this.mContentHvp.setCurrentItem(intExtra);
        handlePageSelectedStatus();
        renderTopRightBtn();
        this.mTitleRl.postDelayed(new Runnable() {
            public void run() {
                MQPhotoPickerPreviewActivity.this.hiddenTitlebarAndChoosebar();
            }
        }, 2000);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.back_iv) {
            onBackPressed();
        } else if (view.getId() == R.id.submit_tv) {
            Intent intent = new Intent();
            intent.putStringArrayListExtra(EXTRA_SELECTED_IMAGES, this.mSelectedImages);
            intent.putExtra(EXTRA_IS_FROM_TAKE_PHOTO, this.mIsFromTakePhoto);
            setResult(-1, intent);
            finish();
        } else if (view.getId() == R.id.choose_tv) {
            String str = this.mPreviewImages.get(this.mContentHvp.getCurrentItem());
            if (this.mSelectedImages.contains(str)) {
                this.mSelectedImages.remove(str);
                this.mChooseTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mq_ic_cb_normal, 0, 0, 0);
                renderTopRightBtn();
            } else if (this.mMaxChooseCount == 1) {
                this.mSelectedImages.clear();
                this.mSelectedImages.add(str);
                this.mChooseTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mq_ic_cb_checked, 0, 0, 0);
                renderTopRightBtn();
            } else if (this.mMaxChooseCount == this.mSelectedImages.size()) {
                MQUtils.show((Context) this, (CharSequence) getString(R.string.mq_toast_photo_picker_max, new Object[]{Integer.valueOf(this.mMaxChooseCount)}));
            } else {
                this.mSelectedImages.add(str);
                this.mChooseTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mq_ic_cb_checked, 0, 0, 0);
                renderTopRightBtn();
            }
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putStringArrayListExtra(EXTRA_SELECTED_IMAGES, this.mSelectedImages);
        intent.putExtra(EXTRA_IS_FROM_TAKE_PHOTO, this.mIsFromTakePhoto);
        setResult(0, intent);
        finish();
    }

    /* access modifiers changed from: private */
    public void handlePageSelectedStatus() {
        TextView textView = this.mTitleTv;
        textView.setText((this.mContentHvp.getCurrentItem() + 1) + "/" + this.mPreviewImages.size());
        if (this.mSelectedImages.contains(this.mPreviewImages.get(this.mContentHvp.getCurrentItem()))) {
            this.mChooseTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mq_ic_cb_checked, 0, 0, 0);
        } else {
            this.mChooseTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mq_ic_cb_normal, 0, 0, 0);
        }
    }

    private void renderTopRightBtn() {
        if (this.mIsFromTakePhoto) {
            this.mSubmitTv.setEnabled(true);
            this.mSubmitTv.setText(this.mTopRightBtnText);
        } else if (this.mSelectedImages.size() == 0) {
            this.mSubmitTv.setEnabled(false);
            this.mSubmitTv.setText(this.mTopRightBtnText);
        } else {
            this.mSubmitTv.setEnabled(true);
            TextView textView = this.mSubmitTv;
            textView.setText(this.mTopRightBtnText + "(" + this.mSelectedImages.size() + "/" + this.mMaxChooseCount + ")");
        }
    }

    public void onViewTap(View view, float f, float f2) {
        if (System.currentTimeMillis() - this.mLastShowHiddenTime > 500) {
            this.mLastShowHiddenTime = System.currentTimeMillis();
            if (this.mIsHidden) {
                showTitlebarAndChoosebar();
            } else {
                hiddenTitlebarAndChoosebar();
            }
        }
    }

    private void showTitlebarAndChoosebar() {
        ViewCompat.animate(this.mTitleRl).translationY(CropImageView.DEFAULT_ASPECT_RATIO).setInterpolator(new DecelerateInterpolator(2.0f)).setListener(new ViewPropertyAnimatorListenerAdapter() {
            public void onAnimationEnd(View view) {
                boolean unused = MQPhotoPickerPreviewActivity.this.mIsHidden = false;
            }
        }).start();
        if (!this.mIsFromTakePhoto) {
            this.mChooseRl.setVisibility(0);
            ViewCompat.setAlpha(this.mChooseRl, CropImageView.DEFAULT_ASPECT_RATIO);
            ViewCompat.animate(this.mChooseRl).alpha(1.0f).setInterpolator(new DecelerateInterpolator(2.0f)).start();
        }
    }

    /* access modifiers changed from: private */
    public void hiddenTitlebarAndChoosebar() {
        ViewCompat.animate(this.mTitleRl).translationY((float) (-this.mTitleRl.getHeight())).setInterpolator(new DecelerateInterpolator(2.0f)).setListener(new ViewPropertyAnimatorListenerAdapter() {
            public void onAnimationEnd(View view) {
                boolean unused = MQPhotoPickerPreviewActivity.this.mIsHidden = true;
                MQPhotoPickerPreviewActivity.this.mChooseRl.setVisibility(4);
            }
        }).start();
        if (!this.mIsFromTakePhoto) {
            ViewCompat.animate(this.mChooseRl).alpha(CropImageView.DEFAULT_ASPECT_RATIO).setInterpolator(new DecelerateInterpolator(2.0f)).start();
        }
    }

    private class ImagePageAdapter extends PagerAdapter {
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        private ImagePageAdapter() {
        }

        public int getCount() {
            return MQPhotoPickerPreviewActivity.this.mPreviewImages.size();
        }

        public View instantiateItem(ViewGroup viewGroup, int i) {
            final MQImageView mQImageView = new MQImageView(viewGroup.getContext());
            viewGroup.addView(mQImageView, -1, -1);
            final MQBrowserPhotoViewAttacher mQBrowserPhotoViewAttacher = new MQBrowserPhotoViewAttacher(mQImageView);
            mQBrowserPhotoViewAttacher.setOnViewTapListener(MQPhotoPickerPreviewActivity.this);
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
            MQImage.displayImage(MQPhotoPickerPreviewActivity.this, mQImageView, (String) MQPhotoPickerPreviewActivity.this.mPreviewImages.get(i), R.drawable.mq_ic_holder_dark, R.drawable.mq_ic_holder_dark, MQUtils.getScreenWidth(MQPhotoPickerPreviewActivity.this), MQUtils.getScreenHeight(MQPhotoPickerPreviewActivity.this), (MQImageLoader.MQDisplayImageListener) null);
            return mQImageView;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }
}
