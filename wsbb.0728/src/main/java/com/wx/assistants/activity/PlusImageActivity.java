package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.meiqia.meiqiasdk.R;
import com.stub.StubApp;
import com.wx.assistants.adapter.PublicViewPagerAdapter;
import com.wx.assistants.utils.DialogUIUtils;
import java.util.ArrayList;

public class PlusImageActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private PublicViewPagerAdapter mAdapter;
    /* access modifiers changed from: private */
    public ArrayList<String> mImaList;
    /* access modifiers changed from: private */
    public int mPosition;
    private TextView mPositionTv;
    private ViewPager mViewPager;

    static {
        StubApp.interface11(9352);
    }

    private void back() {
        Intent intent = getIntent();
        intent.putStringArrayListExtra("img_list", this.mImaList);
        setResult(11, intent);
        finish();
    }

    /* JADX WARNING: type inference failed for: r0v6, types: [android.content.Context] */
    /* JADX WARNING: Multi-variable type inference failed */
    private void deletePic() {
        try {
            DialogUIUtils.dialogDefault(this, "删除提示", this.mImaList.get(this.mPosition).endsWith(PictureFileUtils.POST_VIDEO) ^ true ? "要删除这张图片吗？" : "要删除这个视频吗？", "取消", "确定", (View.OnClickListener) null, new View.OnClickListener() {
                public void onClick(View view) {
                    PlusImageActivity.this.mImaList.remove(PlusImageActivity.this.mPosition);
                    PlusImageActivity.this.setPosition();
                }
            });
        } catch (Exception e) {
        }
    }

    private void init() {
        this.mImaList = getIntent().getStringArrayListExtra("img_list");
        this.mPosition = getIntent().getIntExtra("position", 0);
        initWidget();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, android.view.View$OnClickListener, com.wx.assistants.activity.PlusImageActivity, android.support.v4.view.ViewPager$OnPageChangeListener] */
    private void initWidget() {
        this.mViewPager = findViewById(2131297639);
        this.mPositionTv = (TextView) findViewById(2131297152);
        findViewById(R.id.back_iv).setOnClickListener(this);
        findViewById(2131296584).setOnClickListener(this);
        this.mViewPager.addOnPageChangeListener(this);
        this.mAdapter = new PublicViewPagerAdapter(this, this.mImaList);
        this.mViewPager.setAdapter(this.mAdapter);
        TextView textView = this.mPositionTv;
        textView.setText((this.mPosition + 1) + "/" + this.mImaList.size());
        this.mViewPager.setCurrentItem(this.mPosition);
    }

    /* access modifiers changed from: private */
    public void setPosition() {
        TextView textView = this.mPositionTv;
        textView.setText((this.mPosition + 1) + "/" + this.mImaList.size());
        this.mViewPager.setCurrentItem(this.mPosition);
        this.mAdapter.notifyDataSetChanged();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == 2131296369) {
            back();
        } else if (id == 2131296584) {
            deletePic();
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return PlusImageActivity.super.onKeyDown(i, keyEvent);
        }
        back();
        return true;
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.mPosition = i;
        TextView textView = this.mPositionTv;
        textView.setText((i + 1) + "/" + this.mImaList.size());
    }
}
