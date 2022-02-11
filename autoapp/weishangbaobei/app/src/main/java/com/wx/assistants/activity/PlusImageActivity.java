package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.stub.StubApp;
import com.wx.assistants.adapter.PublicViewPagerAdapter;
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

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
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

    /* JADX WARNING: type inference failed for: r1v2, types: [android.content.Context] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void deletePic() {
        /*
            r8 = this;
            java.util.ArrayList<java.lang.String> r0 = r8.mImaList     // Catch:{ Exception -> 0x002a }
            int r1 = r8.mPosition     // Catch:{ Exception -> 0x002a }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ Exception -> 0x002a }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x002a }
            java.lang.String r1 = ".mp4"
            boolean r0 = r0.endsWith(r1)     // Catch:{ Exception -> 0x002a }
            r0 = r0 ^ 1
            java.lang.String r2 = "删除提示"
            if (r0 == 0) goto L_0x0019
            java.lang.String r0 = "要删除这张图片吗？"
            goto L_0x001b
        L_0x0019:
            java.lang.String r0 = "要删除这个视频吗？"
        L_0x001b:
            r3 = r0
            java.lang.String r4 = "取消"
            java.lang.String r5 = "确定"
            r6 = 0
            com.wx.assistants.activity.PlusImageActivity$1 r7 = new com.wx.assistants.activity.PlusImageActivity$1     // Catch:{ Exception -> 0x002a }
            r7.<init>()     // Catch:{ Exception -> 0x002a }
            r1 = r8
            com.wx.assistants.utils.DialogUIUtils.dialogDefault(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x002a }
        L_0x002a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.PlusImageActivity.deletePic():void");
    }

    /* access modifiers changed from: private */
    public void setPosition() {
        TextView textView = this.mPositionTv;
        textView.setText((this.mPosition + 1) + "/" + this.mImaList.size());
        this.mViewPager.setCurrentItem(this.mPosition);
        this.mAdapter.notifyDataSetChanged();
    }

    private void back() {
        Intent intent = getIntent();
        intent.putStringArrayListExtra("img_list", this.mImaList);
        setResult(11, intent);
        finish();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == 2131296369) {
            back();
        } else if (id == 2131296584) {
            deletePic();
        }
    }

    public void onPageSelected(int i) {
        this.mPosition = i;
        TextView textView = this.mPositionTv;
        textView.setText((i + 1) + "/" + this.mImaList.size());
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return PlusImageActivity.super.onKeyDown(i, keyEvent);
        }
        back();
        return true;
    }
}
