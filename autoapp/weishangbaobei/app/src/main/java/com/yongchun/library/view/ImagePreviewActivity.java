package com.yongchun.library.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.stub.StubApp;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.yongchun.library.R;
import com.yongchun.library.model.LocalMedia;
import com.yongchun.library.widget.PreviewViewPager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImagePreviewActivity extends AppCompatActivity {
    public static final String EXTRA_MAX_SELECT_NUM = "maxSelectNum";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_PREVIEW_LIST = "previewList";
    public static final String EXTRA_PREVIEW_SELECT_LIST = "previewSelectList";
    public static final String OUTPUT_ISDONE = "isDone";
    public static final String OUTPUT_LIST = "outputList";
    public static final int REQUEST_PREVIEW = 68;
    private LinearLayout barLayout;
    /* access modifiers changed from: private */
    public CheckBox checkboxSelect;
    private TextView doneText;
    /* access modifiers changed from: private */
    public List<LocalMedia> images = new ArrayList();
    private boolean isShowBar = true;
    /* access modifiers changed from: private */
    public int maxSelectNum;
    private int position;
    private RelativeLayout selectBarLayout;
    /* access modifiers changed from: private */
    public List<LocalMedia> selectImages = new ArrayList();
    /* access modifiers changed from: private */
    public Toolbar toolbar;
    /* access modifiers changed from: private */
    public PreviewViewPager viewPager;

    static {
        StubApp.interface11(12652);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public static void startPreview(Activity activity, int i, int i2) {
        Intent intent = new Intent(activity, ImagePreviewActivity.class);
        intent.putExtra("position", i2);
        intent.putExtra(EXTRA_MAX_SELECT_NUM, i);
        activity.startActivityForResult(intent, 68);
    }

    public void initView() {
        this.images = ImageSelectorActivity.previewImages;
        this.selectImages = ImageSelectorActivity.selectImages;
        this.maxSelectNum = getIntent().getIntExtra(EXTRA_MAX_SELECT_NUM, 9);
        this.position = getIntent().getIntExtra("position", 1);
        this.barLayout = (LinearLayout) findViewById(R.id.bar_layout);
        this.selectBarLayout = (RelativeLayout) findViewById(R.id.select_bar_layout);
        this.toolbar = findViewById(R.id.toolbar);
        Toolbar toolbar2 = this.toolbar;
        toolbar2.setTitle((this.position + 1) + "/" + this.images.size());
        setSupportActionBar(this.toolbar);
        this.toolbar.setNavigationIcon(R.mipmap.ic_back);
        this.doneText = (TextView) findViewById(R.id.done_text);
        onSelectNumChange();
        this.checkboxSelect = (CheckBox) findViewById(R.id.checkbox_select);
        onImageSwitch(this.position);
        this.viewPager = (PreviewViewPager) findViewById(R.id.preview_pager);
        this.viewPager.setAdapter(new SimpleFragmentAdapter(getSupportFragmentManager()));
        this.viewPager.setCurrentItem(this.position);
    }

    public void registerListener() {
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                Toolbar access$100 = ImagePreviewActivity.this.toolbar;
                access$100.setTitle((i + 1) + "/" + ImagePreviewActivity.this.images.size());
                ImagePreviewActivity.this.onImageSwitch(i);
            }
        });
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImagePreviewActivity.this.onDoneClick(false);
            }
        });
        this.checkboxSelect.setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: type inference failed for: r5v12, types: [android.content.Context, com.yongchun.library.view.ImagePreviewActivity] */
            public void onClick(View view) {
                boolean isChecked = ImagePreviewActivity.this.checkboxSelect.isChecked();
                if (ImagePreviewActivity.this.selectImages.size() < ImagePreviewActivity.this.maxSelectNum || !isChecked) {
                    LocalMedia localMedia = (LocalMedia) ImagePreviewActivity.this.images.get(ImagePreviewActivity.this.viewPager.getCurrentItem());
                    if (!isChecked) {
                        Iterator it = ImagePreviewActivity.this.selectImages.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            LocalMedia localMedia2 = (LocalMedia) it.next();
                            if (localMedia2.getPath().equals(localMedia.getPath())) {
                                ImagePreviewActivity.this.selectImages.remove(localMedia2);
                                break;
                            }
                        }
                    } else {
                        ImagePreviewActivity.this.selectImages.add(localMedia);
                    }
                    ImagePreviewActivity.this.onSelectNumChange();
                    return;
                }
                Toast.makeText(ImagePreviewActivity.this, ImagePreviewActivity.this.getString(R.string.message_max_num), 1).show();
                ImagePreviewActivity.this.checkboxSelect.setChecked(false);
            }
        });
        this.doneText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImagePreviewActivity.this.onDoneClick(true);
            }
        });
    }

    public class SimpleFragmentAdapter extends FragmentPagerAdapter {
        public SimpleFragmentAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return ImagePreviewFragment.getInstance(((LocalMedia) ImagePreviewActivity.this.images.get(i)).getPath());
        }

        public int getCount() {
            return ImagePreviewActivity.this.images.size();
        }
    }

    public void onSelectNumChange() {
        boolean z = this.selectImages.size() != 0;
        this.doneText.setEnabled(z);
        if (z) {
            this.doneText.setText(getString(R.string.done_num, new Object[]{Integer.valueOf(this.selectImages.size()), Integer.valueOf(this.maxSelectNum)}));
        } else {
            this.doneText.setText(R.string.done);
        }
    }

    public void onImageSwitch(int i) {
        this.checkboxSelect.setChecked(isSelected(this.images.get(i)));
    }

    public boolean isSelected(LocalMedia localMedia) {
        for (LocalMedia path : this.selectImages) {
            if (path.getPath().equals(localMedia.getPath())) {
                return true;
            }
        }
        return false;
    }

    private void hideStatusBar() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.flags |= WXMediaMessage.DESCRIPTION_LENGTH_LIMIT;
        getWindow().setAttributes(attributes);
    }

    private void showStatusBar() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.flags &= -1025;
        getWindow().setAttributes(attributes);
    }

    public void switchBarVisibility() {
        int i = 0;
        this.barLayout.setVisibility(this.isShowBar ? 8 : 0);
        this.toolbar.setVisibility(this.isShowBar ? 8 : 0);
        RelativeLayout relativeLayout = this.selectBarLayout;
        if (this.isShowBar) {
            i = 8;
        }
        relativeLayout.setVisibility(i);
        if (this.isShowBar) {
            hideStatusBar();
        } else {
            showStatusBar();
        }
        this.isShowBar = !this.isShowBar;
    }

    public void onDoneClick(boolean z) {
        Intent intent = new Intent();
        ImageSelectorActivity.selectImages = this.selectImages;
        intent.putExtra(OUTPUT_ISDONE, z);
        setResult(-1, intent);
        finish();
    }
}
