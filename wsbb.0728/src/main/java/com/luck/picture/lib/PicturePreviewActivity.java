package com.luck.picture.lib;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.luck.picture.lib.adapter.SimpleFragmentAdapter;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.EventEntity;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.rxbus2.RxBus;
import com.luck.picture.lib.rxbus2.Subscribe;
import com.luck.picture.lib.rxbus2.ThreadMode;
import com.luck.picture.lib.tools.ToastManage;
import com.luck.picture.lib.widget.PreviewViewPager;
import com.stub.StubApp;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropMulti;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PicturePreviewActivity extends PictureBaseActivity implements View.OnClickListener, Animation.AnimationListener, SimpleFragmentAdapter.OnCallBackActivity {
    private SimpleFragmentAdapter adapter;
    /* access modifiers changed from: private */
    public Animation animation;
    /* access modifiers changed from: private */
    public TextView check;
    private LinearLayout id_ll_ok;
    /* access modifiers changed from: private */
    public List<LocalMedia> images = new ArrayList();
    /* access modifiers changed from: private */
    public int index;
    private LinearLayout ll_check;
    private Handler mHandler;
    private ImageView picture_left_back;
    /* access modifiers changed from: private */
    public int position;
    private boolean refresh;
    private int screenWidth;
    /* access modifiers changed from: private */
    public List<LocalMedia> selectImages = new ArrayList();
    private TextView tv_img_num;
    private TextView tv_ok;
    /* access modifiers changed from: private */
    public TextView tv_title;
    /* access modifiers changed from: private */
    public PreviewViewPager viewPager;

    static {
        StubApp.interface11(5589);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.luck.picture.lib.PicturePreviewActivity, com.luck.picture.lib.adapter.SimpleFragmentAdapter$OnCallBackActivity] */
    private void initViewPageAdapterData() {
        TextView textView = this.tv_title;
        textView.setText((this.position + 1) + "/" + this.images.size());
        this.adapter = new SimpleFragmentAdapter(this.images, this, this);
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.setCurrentItem(this.position);
        onSelectNumChange(false);
        onImageChecked(this.position);
        if (this.images.size() > 0) {
            LocalMedia localMedia = this.images.get(this.position);
            this.index = localMedia.getPosition();
            if (this.config.checkNumMode) {
                this.tv_img_num.setSelected(true);
                TextView textView2 = this.check;
                textView2.setText(localMedia.getNum() + "");
                notifyCheckChanged(localMedia);
            }
        }
    }

    /* access modifiers changed from: private */
    public void isPreviewEggs(boolean z, int i, int i2) {
        if (z && this.images.size() > 0 && this.images != null) {
            if (i2 < this.screenWidth / 2) {
                LocalMedia localMedia = this.images.get(i);
                this.check.setSelected(isSelected(localMedia));
                if (this.config.checkNumMode) {
                    int num = localMedia.getNum();
                    TextView textView = this.check;
                    textView.setText(num + "");
                    notifyCheckChanged(localMedia);
                    onImageChecked(i);
                    return;
                }
                return;
            }
            int i3 = i + 1;
            LocalMedia localMedia2 = this.images.get(i3);
            this.check.setSelected(isSelected(localMedia2));
            if (this.config.checkNumMode) {
                int num2 = localMedia2.getNum();
                TextView textView2 = this.check;
                textView2.setText(num2 + "");
                notifyCheckChanged(localMedia2);
                onImageChecked(i3);
            }
        }
    }

    /* access modifiers changed from: private */
    public void notifyCheckChanged(LocalMedia localMedia) {
        if (this.config.checkNumMode) {
            this.check.setText("");
            for (LocalMedia next : this.selectImages) {
                if (next.getPath().equals(localMedia.getPath())) {
                    localMedia.setNum(next.getNum());
                    this.check.setText(String.valueOf(localMedia.getNum()));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void singleRadioMediaImage() {
        if (this.selectImages != null && this.selectImages.size() > 0) {
            RxBus.getDefault().post(new EventEntity(PictureConfig.UPDATE_FLAG, this.selectImages, this.selectImages.get(0).getPosition()));
            this.selectImages.clear();
        }
    }

    /* access modifiers changed from: private */
    public void subSelectPosition() {
        int size = this.selectImages.size();
        int i = 0;
        while (i < size) {
            i++;
            this.selectImages.get(i).setNum(i);
        }
    }

    private void updateSelector(boolean z) {
        if (z) {
            RxBus.getDefault().post(new EventEntity(PictureConfig.UPDATE_FLAG, this.selectImages, this.index));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBus(EventEntity eventEntity) {
        if (eventEntity.what == 2770) {
            dismissDialog();
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    PicturePreviewActivity.this.onBackPressed();
                }
            }, 150);
        }
    }

    public boolean isSelected(LocalMedia localMedia) {
        for (LocalMedia path : this.selectImages) {
            if (path.getPath().equals(localMedia.getPath())) {
                return true;
            }
        }
        return false;
    }

    public void onActivityBackPressed() {
        onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            if (i == 69) {
                if (intent != null) {
                    setResult(-1, intent);
                }
                finish();
            } else if (i == 609) {
                setResult(-1, new Intent().putExtra("com.yalantis.ucrop.OutputUriList", (Serializable) UCropMulti.getOutput(intent)));
                finish();
            }
        } else if (i2 == 96) {
            ToastManage.s(this.mContext, ((Throwable) intent.getSerializableExtra(UCrop.EXTRA_ERROR)).getMessage());
        }
    }

    public void onAnimationEnd(Animation animation2) {
        updateSelector(this.refresh);
    }

    public void onAnimationRepeat(Animation animation2) {
    }

    public void onAnimationStart(Animation animation2) {
    }

    public void onBackPressed() {
        closeActivity();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.picture_left_back) {
            onBackPressed();
        }
        if (id == R.id.id_ll_ok) {
            int size = this.selectImages.size();
            LocalMedia localMedia = this.selectImages.size() > 0 ? this.selectImages.get(0) : null;
            String pictureType = localMedia != null ? localMedia.getPictureType() : "";
            if (this.config.minSelectNum > 0 && size < this.config.minSelectNum && this.config.selectionMode == 2) {
                ToastManage.s(this.mContext, pictureType.startsWith("image") ? getString(R.string.picture_min_img_num, new Object[]{Integer.valueOf(this.config.minSelectNum)}) : getString(R.string.picture_min_video_num, new Object[]{Integer.valueOf(this.config.minSelectNum)}));
            } else if (!this.config.enableCrop || !pictureType.startsWith("image")) {
                onResult(this.selectImages);
            } else if (this.config.selectionMode == 1) {
                this.originalPath = localMedia.getPath();
                startCrop(this.originalPath);
            } else {
                ArrayList arrayList = new ArrayList();
                for (LocalMedia path : this.selectImages) {
                    arrayList.add(path.getPath());
                }
                startCrop((ArrayList<String>) arrayList);
            }
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(@Nullable Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().unregister(this);
        }
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages((Object) null);
            this.mHandler = null;
        }
        if (this.animation != null) {
            this.animation.cancel();
            this.animation = null;
        }
    }

    public void onImageChecked(int i) {
        if (this.images == null || this.images.size() <= 0) {
            this.check.setSelected(false);
        } else {
            this.check.setSelected(isSelected(this.images.get(i)));
        }
    }

    public void onResult(List<LocalMedia> list) {
        RxBus.getDefault().post(new EventEntity((int) PictureConfig.PREVIEW_DATA_FLAG, list));
        if (!this.config.isCompress) {
            onBackPressed();
        } else {
            showPleaseDialog();
        }
    }

    public void onSelectNumChange(boolean z) {
        this.refresh = z;
        if (this.selectImages.size() != 0) {
            this.tv_ok.setSelected(true);
            this.id_ll_ok.setEnabled(true);
            if (this.numComplete) {
                this.tv_ok.setText(getString(R.string.picture_done_front_num, new Object[]{Integer.valueOf(this.selectImages.size()), Integer.valueOf(this.config.selectionMode == 1 ? 1 : this.config.maxSelectNum)}));
            } else {
                if (this.refresh) {
                    this.tv_img_num.startAnimation(this.animation);
                }
                this.tv_img_num.setVisibility(0);
                this.tv_img_num.setText(String.valueOf(this.selectImages.size()));
                this.tv_ok.setText(getString(R.string.picture_completed));
            }
        } else {
            this.id_ll_ok.setEnabled(false);
            this.tv_ok.setSelected(false);
            if (this.numComplete) {
                this.tv_ok.setText(getString(R.string.picture_done_front_num, new Object[]{0, Integer.valueOf(this.config.selectionMode == 1 ? 1 : this.config.maxSelectNum)}));
            } else {
                this.tv_img_num.setVisibility(4);
                this.tv_ok.setText(getString(R.string.picture_please_select));
            }
        }
        updateSelector(this.refresh);
    }
}
