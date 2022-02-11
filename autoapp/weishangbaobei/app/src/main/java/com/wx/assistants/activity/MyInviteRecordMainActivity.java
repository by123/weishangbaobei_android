package com.wx.assistants.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.athbk.ultimatetablayout.OnClickTabListener;
import com.athbk.ultimatetablayout.UltimateTabLayout;
import com.google.gson.Gson;
import com.stub.StubApp;
import com.wx.assistants.adapter.MyInviteRecordMainAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.SPUtils;
import java.util.ArrayList;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class MyInviteRecordMainActivity extends BaseActivity {
    private MyInviteRecordMainAdapter adapter;
    @BindView(2131296731)
    LinearLayout graphicExplanationLayout;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
    @BindView(2131297452)
    UltimateTabLayout tabLayout;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;
    @BindView(2131297639)
    ViewPager viewPager;

    static {
        StubApp.interface11(9054);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public void initView() {
        this.navTitle.setText("邀请记录");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getUser();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.MyInviteRecordMainActivity] */
    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        try {
            String str = (String) SPUtils.get(this, "ws_baby_user_info", "");
            if (str != null && !"".equals(str)) {
                setUserInfo(str);
            }
        } catch (Exception unused) {
        }
    }

    public void setUserInfo(String str) {
        UserInfoBean.UserBean userBean = (UserInfoBean.UserBean) new Gson().fromJson(str, UserInfoBean.UserBean.class);
        if (userBean != null) {
            try {
                String userId = userBean.getUserId();
                if (userId != null && !"".equals(userId)) {
                    SPUtils.put(MyApplication.getConText(), "ws_baby_user_id", userId);
                }
            } catch (Exception unused) {
            }
            String str2 = userBean.getInviteNum() + "";
            ArrayList arrayList = new ArrayList();
            arrayList.add("直推用户(" + str2 + ")");
            arrayList.add("间推用户(" + (userBean.getInviteTwoNum() + "") + ")");
            this.adapter = new MyInviteRecordMainAdapter(getSupportFragmentManager(), arrayList);
            this.viewPager.setAdapter(this.adapter);
            this.tabLayout.setOnClickTabListener(new OnClickTabListener() {
                public void onClickTab(int i) {
                    MyInviteRecordMainActivity.this.viewPager.setCurrentItem(i);
                    if (i == 1) {
                        MyInviteRecordMainActivity.this.tabLayout.setNumberBadge(i, 0);
                    } else {
                        MyInviteRecordMainActivity.this.tabLayout.setNumberBadge(i, 1);
                    }
                }
            });
            this.tabLayout.setViewPager(this.viewPager, this.adapter);
            this.viewPager.setOffscreenPageLimit(1);
        }
    }

    public void getUser() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: Can't wrap try/catch for region: R(6:11|12|13|14|15|25) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x003d */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFinish(com.wx.assistants.bean.ConmdBean r3) {
                /*
                    r2 = this;
                    if (r3 == 0) goto L_0x0054
                    int r0 = r3.getCode()     // Catch:{ Exception -> 0x004f }
                    r1 = 1
                    if (r0 != r1) goto L_0x0021
                    java.lang.String r0 = r3.getMessage()     // Catch:{ Exception -> 0x004f }
                    if (r0 == 0) goto L_0x0021
                    java.lang.String r3 = r3.getMessage()     // Catch:{ Exception -> 0x004f }
                    java.lang.String r0 = "请重新登录"
                    boolean r3 = r3.contains(r0)     // Catch:{ Exception -> 0x004f }
                    if (r3 == 0) goto L_0x0054
                    com.wx.assistants.activity.MyInviteRecordMainActivity r3 = com.wx.assistants.activity.MyInviteRecordMainActivity.this     // Catch:{ Exception -> 0x004f }
                    r3.defaultSetting()     // Catch:{ Exception -> 0x004f }
                    goto L_0x0054
                L_0x0021:
                    int r0 = r3.getCode()     // Catch:{ Exception -> 0x004f }
                    if (r0 != 0) goto L_0x0043
                    com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch:{ Exception -> 0x004f }
                    r0.<init>()     // Catch:{ Exception -> 0x004f }
                    java.lang.Object r3 = r3.getData()     // Catch:{ Exception -> 0x004f }
                    java.lang.String r3 = r0.toJson(r3)     // Catch:{ Exception -> 0x004f }
                    android.content.Context r0 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x003d }
                    java.lang.String r1 = "ws_baby_user_info"
                    com.wx.assistants.utils.SPUtils.put(r0, r1, r3)     // Catch:{ Exception -> 0x003d }
                L_0x003d:
                    com.wx.assistants.activity.MyInviteRecordMainActivity r0 = com.wx.assistants.activity.MyInviteRecordMainActivity.this     // Catch:{ Exception -> 0x004f }
                    r0.setUserInfo(r3)     // Catch:{ Exception -> 0x004f }
                    goto L_0x0054
                L_0x0043:
                    int r3 = r3.getCode()     // Catch:{ Exception -> 0x004f }
                    if (r3 != r1) goto L_0x0054
                    com.wx.assistants.activity.MyInviteRecordMainActivity r3 = com.wx.assistants.activity.MyInviteRecordMainActivity.this     // Catch:{ Exception -> 0x004f }
                    r3.defaultSetting()     // Catch:{ Exception -> 0x004f }
                    goto L_0x0054
                L_0x004f:
                    com.wx.assistants.activity.MyInviteRecordMainActivity r3 = com.wx.assistants.activity.MyInviteRecordMainActivity.this
                    r3.defaultSetting()
                L_0x0054:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.MyInviteRecordMainActivity.AnonymousClass2.onFinish(com.wx.assistants.bean.ConmdBean):void");
            }

            public void onFailure(FailureModel failureModel) {
                MyInviteRecordMainActivity.this.defaultSetting();
            }
        });
    }

    public void defaultSetting() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("直推用户");
        arrayList.add("间推用户");
        this.adapter = new MyInviteRecordMainAdapter(getSupportFragmentManager(), arrayList);
        this.viewPager.setAdapter(this.adapter);
        this.tabLayout.setOnClickTabListener(new OnClickTabListener() {
            public void onClickTab(int i) {
                MyInviteRecordMainActivity.this.viewPager.setCurrentItem(i);
                if (i == 1) {
                    MyInviteRecordMainActivity.this.tabLayout.setNumberBadge(i, 0);
                } else {
                    MyInviteRecordMainActivity.this.tabLayout.setNumberBadge(i, 1);
                }
            }
        });
        this.tabLayout.setViewPager(this.viewPager, this.adapter);
        this.viewPager.setOffscreenPageLimit(1);
    }

    @OnClick({2131297049})
    public void onViewClicked(View view) {
        if (view.getId() == 2131297049) {
            back();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }
}
