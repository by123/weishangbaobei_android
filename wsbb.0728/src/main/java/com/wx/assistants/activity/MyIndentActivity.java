package com.wx.assistants.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.sevenheaven.segmentcontrol.SegmentControl;
import com.stub.StubApp;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.fragment.MyIndentFragment;
import com.wx.assistants.fragment.MyMemberCardFragment;

public class MyIndentActivity extends BaseActivity {
    @BindView(2131296353)
    LinearLayout arrowBack;
    /* access modifiers changed from: private */
    public FragmentTransaction fragmentTransaction;
    @BindView(2131297045)
    FrameLayout myFragment;
    /* access modifiers changed from: private */
    public MyIndentFragment myIndentFragment;
    /* access modifiers changed from: private */
    public MyMemberCardFragment myMemberFragment;
    @BindView(2131297306)
    SegmentControl segmentControl;
    /* access modifiers changed from: private */
    public FragmentManager supportFragmentManager;

    static {
        StubApp.interface11(9037);
    }

    private void initView() {
        this.myIndentFragment = new MyIndentFragment();
        this.myMemberFragment = new MyMemberCardFragment();
        this.supportFragmentManager = getSupportFragmentManager();
        this.fragmentTransaction = this.supportFragmentManager.beginTransaction();
        this.fragmentTransaction.add(2131297045, this.myIndentFragment);
        this.fragmentTransaction.commit();
        this.segmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            public void onSegmentControlClick(int i) {
                FragmentTransaction unused = MyIndentActivity.this.fragmentTransaction = MyIndentActivity.this.supportFragmentManager.beginTransaction();
                switch (i) {
                    case 0:
                        MyIndentActivity.this.fragmentTransaction.replace(2131297045, MyIndentActivity.this.myIndentFragment);
                        break;
                    case 1:
                        MyIndentActivity.this.fragmentTransaction.replace(2131297045, MyIndentActivity.this.myMemberFragment);
                        break;
                }
                MyIndentActivity.this.fragmentTransaction.commit();
            }
        });
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    @OnClick({2131296353})
    public void onViewClicked(View view) {
        if (view.getId() == 2131296353) {
            back();
        }
    }
}
