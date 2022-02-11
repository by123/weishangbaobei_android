package com.wx.assistants.activity;

import android.content.Intent;
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
import com.wx.assistants.fragment.InviteFriendFragment;
import com.wx.assistants.fragment.MaterialForwardFragment;

public class GoInviteActivity extends BaseActivity {
    @BindView(2131296353)
    LinearLayout arrowBack;
    /* access modifiers changed from: private */
    public FragmentTransaction fragmentTransaction;
    @BindView(2131296823)
    LinearLayout inviteRewardLayout;
    @BindView(2131297045)
    FrameLayout myFragment;
    /* access modifiers changed from: private */
    public InviteFriendFragment myIndentFragment;
    /* access modifiers changed from: private */
    public MaterialForwardFragment myMemberFragment;
    @BindView(2131297306)
    SegmentControl segmentControl;
    /* access modifiers changed from: private */
    public FragmentManager supportFragmentManager;

    static {
        StubApp.interface11(8622);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    private void initView() {
        this.myIndentFragment = new InviteFriendFragment();
        this.myMemberFragment = new MaterialForwardFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isDeleteNavBar", true);
        this.myMemberFragment.setArguments(bundle);
        this.supportFragmentManager = getSupportFragmentManager();
        this.fragmentTransaction = this.supportFragmentManager.beginTransaction();
        this.fragmentTransaction.add(2131297045, this.myIndentFragment);
        this.fragmentTransaction.commit();
        this.segmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            public void onSegmentControlClick(int i) {
                FragmentTransaction unused = GoInviteActivity.this.fragmentTransaction = GoInviteActivity.this.supportFragmentManager.beginTransaction();
                switch (i) {
                    case 0:
                        GoInviteActivity.this.fragmentTransaction.replace(2131297045, GoInviteActivity.this.myIndentFragment);
                        break;
                    case 1:
                        GoInviteActivity.this.fragmentTransaction.replace(2131297045, GoInviteActivity.this.myMemberFragment);
                        break;
                }
                GoInviteActivity.this.fragmentTransaction.commit();
            }
        });
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.GoInviteActivity] */
    @OnClick({2131296353, 2131296823})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131296353) {
            back();
        } else if (id == 2131296823) {
            startActivity(new Intent(this, InviteRecordActivity.class));
        }
    }
}
